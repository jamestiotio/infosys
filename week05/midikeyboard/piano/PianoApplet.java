/**
 * Author: dnj 6.005 Elements of Software Construction (c) 2008-9, MIT and Daniel Jackson
 */
package piano;

import java.applet.Applet;
import java.awt.Color;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import music.Pitch;

/**
 * An applet which listens for key presses and calls a set of corresponding functions in
 * PianoMachine.
 * 
 * You can run this class from Eclipse using Run -> Run As -> Java Applet
 */
public class PianoApplet extends Applet {

    private static final long serialVersionUID = -580070854133088915L;

    @Override
    public void init() {
        setBackground(Color.green);
        final PianoMachine piano = new PianoMachine();

        // this is a standard pattern for associating method calls with GUI
        // events
        // the call to the constructor of KeyAdapter creates an object of an
        // anonymous subclass of KeyAdapter, whose keyPressed method is called
        // when a key is pressed in the GUI
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                // The following check prevents us from executing a KeyEvent that happened
                // in the past within the specified threshold. This is to prevent us from
                // executing a whole bunch of backed-up Events at once as a single chord
                // when keys are pressed during a playback/recording.
                long timeoutThreshold = 1000;
                if (System.currentTimeMillis() - e.getWhen() > timeoutThreshold) {
                    return;
                }
                System.out.println("key pressed");
                char key = (char) e.getKeyCode();
                switch (key) {
                    case '1':
                        System.out.println("key '1' pressed");
                    case '2':
                    case '3':
                    case '4':
                    case '5':
                    case '6':
                    case '7':
                    case '8':
                    case '9':
                        piano.beginNote(new Pitch(key - '1'));
                        return;
                    case '0':
                        piano.beginNote(new Pitch(9));
                        return;
                    case '-':
                        piano.beginNote(new Pitch(10));
                        return;
                    case '=':
                        piano.beginNote(new Pitch(11));
                        return;
                    case 'r':
                    case 'R':
                        boolean isRecording = piano.toggleRecording();
                        if (isRecording) {
                            setBackground(Color.red);
                        } else {
                            setBackground(Color.green);
                        }
                        return;
                    case 'p':
                    case 'P':
                        piano.playback();
                        return;
                    case 'i':
                    case 'I':
                        piano.changeInstrument();
                        return;
                    case 'c':
                    case 'C':
                        piano.shiftDown();
                        return;
                    case 'v':
                    case 'V':
                        piano.shiftUp();
                        return;
                }
            }
        });

        addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                System.out.println("key released");
                char key = (char) e.getKeyCode();
                switch (key) {
                    case '1':
                    case '2':
                    case '3':
                    case '4':
                    case '5':
                    case '6':
                    case '7':
                    case '8':
                    case '9':
                        piano.endNote(new Pitch(key - '1'));
                        return;
                    case '0':
                        piano.endNote(new Pitch(9));
                        return;
                    case '-':
                        piano.endNote(new Pitch(10));
                        return;
                    case '=':
                        piano.endNote(new Pitch(11));
                        return;
                }
            }
        });

    }

}
