interface Visitable {
  public void accept(Visitor v);
}


interface Visitor {
  public void visit(Car c);

  public void visit(Alcohol a);

  public void visit(Chocolate c);
}


public class TaxNormal implements Visitor {
  private double total;
  private String name;

  public TaxNormal(String name) {
    this.name = name;
  }

  public double getTotal() {
    return this.total;
  }

  @Override
  public void visit(Car c) {
    this.total += c.getTax() * Car.NORMAL_FACTOR;
  }

  @Override
  public void visit(Alcohol a) {
    this.total += a.getTax() * Alcohol.NORMAL_FACTOR;
  }

  @Override
  public void visit(Chocolate c) {
    this.total += c.getTax() * Chocolate.NORMAL_FACTOR;
  }
}


public class TaxHoliday implements Visitor {
  private double total;
  private String name;

  public TaxNormal(String name) {
    this.name = name;
  }

  public double getTotal() {
    return this.total;
  }

  @Override
  public void visit(Car c) {
    this.total += c.getTax() * Car.HOLIDAY_FACTOR;
  }

  @Override
  public void visit(Alcohol a) {
    this.total += a.getTax() * Alcohol.HOLIDAY_FACTOR;
  }

  @Override
  public void visit(Chocolate c) {
    this.total += c.getTax() * Chocolate.HOLIDAY_FACTOR;
  }
}


public class Car implements Visitable {
  private double tax;
  public static final double NORMAL_FACTOR = 0.3;
  public static final double HOLIDAY_FACTOR = 0.4;

  public Car(double tax) {
    this.tax = tax;
  }

  public double getTax() {
    return this.tax;
  }

  @Override
  public void accept(Visitor v) {
    v.visit(this);
  }
}


public class Alcohol implements Visitable {
  private double tax;
  public static final double NORMAL_FACTOR = 0.5;
  public static final double HOLIDAY_FACTOR = 0.8;

  public Alcohol(double tax) {
    this.tax = tax;
  }

  public double getTax() {
    return this.tax;
  }

  @Override
  public void accept(Visitor v) {
    v.visit(this);
  }
}


public class Chocolate implements Visitable {
  private double tax;
  public static final double NORMAL_FACTOR = 0.1;
  public static final double HOLIDAY_FACTOR = 0.2;

  public Chocolate(double tax) {
    this.tax = tax;
  }

  public double getTax() {
    return this.tax;
  }

  @Override
  public void accept(Visitor v) {
    v.visit(this);
  }
}
