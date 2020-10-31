public class Octagon implements Comparable<Octagon> {

  private double side;

  public Octagon(double side) {
    this.side = side;
  }

  public double getSide() {
    return side;
  }

  public double getPerimeter() {
    return 8 * this.getSide();
  }

  @Override
  public int compareTo(Octagon o) {
    if (this.getPerimeter() > o.getPerimeter()) {
      return 1;
    } else if (this.getPerimeter() < o.getPerimeter()) {
      return -1;
    } else {
      return 0;
    }
  }

}
