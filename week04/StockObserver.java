import java.util.ArrayList;

interface Observer {
  void update(String message);
}


interface Subject {
  void register(Observer o);

  void unregister(Observer o);

  void notifyObservers(String s);
}


public class StockObserver implements Observer {
  private String name;

  StockObserver(StockGrabber stock, String name) {
    this.name = name;
    stock.register(this);
  }

  @Override
  public void update(String message) {
    System.out.println("Message received by " + name + ": " + message);
  }
}


public class StockGrabber implements Subject {
  private ArrayList<Observer> observerList = new ArrayList<>();

  @Override
  public void register(Observer o) {
    this.observerList.add(o);
  }

  @Override
  public void unregister(Observer o) {
    this.observerList.remove(o);
  }

  @Override
  public void notifyObservers(String s) {
    for (Observer o : observerList) {
      o.update(s);
    }
  }
}
