package piano;

import javax.sound.midi.MidiUnavailableException;

import midi.Midi;
import music.Pitch;
import midi.Instrument;
import music.NoteEvent;
import java.util.ArrayDeque;
import java.util.LinkedHashSet;
import java.util.Iterator;

public class PianoMachine {

    private Midi midi;
    private Instrument currentInstrument = Midi.DEFAULT_INSTRUMENT;
    private int semitoneShift = 0;
    private boolean isRecording = false;
    private LinkedHashSet<Pitch> currentlyPlayingNotes = new LinkedHashSet<>();
    private ArrayDeque<NoteEvent> recordingSequence = new ArrayDeque<>();

    /**
     * constructor for PianoMachine.
     * 
     * initialize midi device and any other state that we're storing.
     */
    public PianoMachine() {
        try {
            midi = Midi.getInstance();
        }

        catch (MidiUnavailableException e1) {
            System.err.println("Could not initialize midi device");
            e1.printStackTrace();
        }
    }

    /**
     * Adds a NoteEvent with the specified actualPitch and type to the recordingSequence.
     * 
     * @param actualPitch - the actual transposed final pitch to be added to the recordingSequence
     * @param type        - a NoteEvent.Kind object to specify the type of NoteEvent to be added to
     *                    the recordingSequence (start / stop)
     * @modifies this.recordingSequence
     */
    private void addNoteEventToRecordingSequence(Pitch actualPitch, NoteEvent.Kind type) {
        if (this.isRecording) {
            this.recordingSequence.addLast(new NoteEvent(actualPitch, System.currentTimeMillis(),
                    this.currentInstrument, type));
        }
    }

    /**
     * Begins playing a note if the note is not already playing. If isRecording is turned on, add a
     * start NoteEvent to the recordingSequence.
     * 
     * @param rawPitch - the note to be played
     * @modifies this.currentlyPlayingNotes
     */
    public void beginNote(Pitch rawPitch) {
        Pitch actualPitch = rawPitch.transpose(this.semitoneShift);

        if (!this.currentlyPlayingNotes.contains(actualPitch)) {
            this.midi.beginNote(actualPitch.toMidiFrequency(), this.currentInstrument);
            this.currentlyPlayingNotes.add(actualPitch);
        }

        this.addNoteEventToRecordingSequence(actualPitch, NoteEvent.Kind.start);
    }

    /**
     * Stops playing a note if the note is not already playing. If isRecording is turned on, add a
     * stop NoteEvent to the recordingSequence.
     * 
     * @param rawPitch - the note to be stopped
     * @modifies this.currentlyPlayingNotes
     */
    public void endNote(Pitch rawPitch) {
        Pitch actualPitch = rawPitch.transpose(this.semitoneShift);

        if (this.currentlyPlayingNotes.contains(actualPitch)) {
            this.midi.endNote(actualPitch.toMidiFrequency(), this.currentInstrument);
            this.currentlyPlayingNotes.remove(actualPitch);
        }

        this.addNoteEventToRecordingSequence(actualPitch, NoteEvent.Kind.stop);
    }

    /**
     * Pauses all of the currently-playing notes stored and copied in the temp LinkedHashSet.
     * 
     * @param temp - the temporary LinkedHashSet containing all of the currently playing notes
     */
    private void pauseAllCurrentNotes(LinkedHashSet<Pitch> temp) {
        if (!this.currentlyPlayingNotes.isEmpty()) {
            Iterator<Pitch> endIter = temp.iterator();

            while (endIter.hasNext()) {
                this.endNote(endIter.next());
            }
        }
    }

    /**
     * Restarts all of the currently-playing notes stored and copied in the temp LinkedHashSet.
     * 
     * @param temp - the temporary LinkedHashSet containing all of the currently playing notes
     */
    private void restartAllCurrentNotes(LinkedHashSet<Pitch> temp) {
        if (!temp.isEmpty()) {
            Iterator<Pitch> beginIter = temp.iterator();

            while (beginIter.hasNext()) {
                this.beginNote(beginIter.next());
            }
        }
    }

    /**
     * Changes to the next instrument in the standard ordering of the Instrument enum list (or the
     * first in the ordering if the current instrument is the last). If any notes are currently
     * being played on the current instrument, those notes will be stopped and restarted on the new
     * instrument.
     * 
     * @modifies this.currentInstrument
     */
    public void changeInstrument() {
        LinkedHashSet<Pitch> temp = new LinkedHashSet<>(this.currentlyPlayingNotes);

        this.pauseAllCurrentNotes(temp);

        this.currentInstrument = this.currentInstrument.next();

        this.restartAllCurrentNotes(temp);
    }

    /**
     * Shifts the notes that the keys play by one octave (12 semitones) up. The maximum final shift
     * is two octaves up from the starting pitches. Also shifts all currently playing notes to the
     * new octave.
     * 
     * @modifies this.semitoneShift
     */
    public void shiftUp() {
        if (this.semitoneShift < 24) {
            LinkedHashSet<Pitch> temp = new LinkedHashSet<>(this.currentlyPlayingNotes);

            this.pauseAllCurrentNotes(temp);

            this.semitoneShift += Pitch.OCTAVE;

            this.restartAllCurrentNotes(temp);
        }
    }

    /**
     * Shifts the notes that the keys play by one octave (12 semitones) down. The maximum final
     * shift is two octaves down from the starting pitches. Also shifts all currently playing notes
     * to the new octave.
     * 
     * @modifies this.semitoneShift
     */
    public void shiftDown() {
        if (this.semitoneShift > -24) {
            LinkedHashSet<Pitch> temp = new LinkedHashSet<>(this.currentlyPlayingNotes);

            this.pauseAllCurrentNotes(temp);

            this.semitoneShift -= Pitch.OCTAVE;

            this.restartAllCurrentNotes(temp);
        }
    }

    /**
     * Start/stop recording the notes played. Toggles the current isRecording state, unless it's in
     * playback mode.
     * 
     * @modifies this.isRecording
     * @return this.isRecording - True if recording is turned on when method returns, false
     *         otherwise.
     */
    public boolean toggleRecording() {
        this.isRecording = !this.isRecording;

        if (this.isRecording) {
            this.recordingSequence.clear();
        }

        return this.isRecording;
    }

    /**
     * Plays back each note stored in recordingSequence. If nothing is stored, nothing is played. A
     * playback cannot be executed if a recording is currently taking place. If the note is not the
     * first in the sequence, put a rest time right before it. This method is considered to be
     * atomic. Temporarily pauses all the current notes being played in the current instrument.
     * 
     * Future work: add a "play along with a playback" feature by parsing this.recordingSequence
     * into a list of instruments and spawn all of them, each populated with their own list of
     * NoteEvents, such that the user is able to play along during playback. Also consider utilizing
     * multithreading and redesigning the whole class. Need to also consider a possible conflict
     * between a currently-being-played note on the current instrument and a toggle of that
     * instrument during playback (i.e. does "multiple keypresses/beginNote() to play the same note
     * on the same instrument before any endNote() is ever called" make sense?). Need to consider
     * whether one PianoMachine can only have one of each instrument or multiple ones like a whole
     * orchestra. The possibility of this extra test case is not specified anywhere (in both SUTD
     * 50.001 and MIT 6.005), so it will not be considered in any official academic test suite. It's
     * just a nifty and nice feature to have.
     */
    public void playback() {
        if (!this.isRecording && !this.recordingSequence.isEmpty()) {
            LinkedHashSet<Pitch> temp = new LinkedHashSet<>(this.currentlyPlayingNotes);

            this.pauseAllCurrentNotes(temp);

            long timestamp = 0;
            Iterator<NoteEvent> note = this.recordingSequence.iterator();

            while (note.hasNext()) {
                NoteEvent event = note.next();

                if (timestamp > 0) {
                    Midi.rest((int) Math.round((event.getTime() - timestamp) / 10.0));
                }

                timestamp = event.getTime();

                if (event.getKind() == NoteEvent.Kind.start) {
                    this.midi.beginNote(event.getPitch().toMidiFrequency(), event.getInstr());
                }

                else if (event.getKind() == NoteEvent.Kind.stop) {
                    this.midi.endNote(event.getPitch().toMidiFrequency(), event.getInstr());
                }
            }

            this.restartAllCurrentNotes(temp);
        }
    }

}
