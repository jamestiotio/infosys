import java.util.ArrayList;

interface Observer {
  void update(double airPollutionIndex);
}


class Subscriber implements Observer {
  private Subject subject;
  private String observerId;
  public static String outputMessage = "";

  public Subscriber(String observerId, Subject subject) {
    this.subject = subject;
    this.observerId = observerId;
    this.subject.register(this); // register itself to the subject
  }

  @Override
  public void update(double airPollutionIndex) {
    String s = this.observerId + " received notification: " + airPollutionIndex;
    System.out.println(s);
    outputMessage += (s + " ");
  }
}


interface Subject {
  void register(Observer o);

  void unregister(Observer o);

  void notifyObservers();
}


class AirPollutionAlert implements Subject {
  private double airPollutionIndex;
  private ArrayList<Observer> observers = null;

  public void setAirPollutionIndex(double airPollutionIndex) {
    this.airPollutionIndex = airPollutionIndex;
    if (this.airPollutionIndex > 100) {
      this.notifyObservers();
    }
  }

  AirPollutionAlert() {
    observers = new ArrayList<>();
  }

  @Override
  public void register(Observer o) {
    observers.add(o);
  }

  @Override
  public void unregister(Observer o) {
    observers.remove(o);
  }

  @Override
  public void notifyObservers() {
    for (Observer o : observers) {
      o.update(this.airPollutionIndex);
    }
  }
}
