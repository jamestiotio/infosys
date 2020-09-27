public class Triangle extends GeometricObject {

  private double side1 = 1.0;
  private double side2 = 1.0;
  private double side3 = 1.0;

  Triangle() {
    this.side1 = side1;
    this.side2 = side2;
    this.side3 = side3;
  }

  Triangle(double sSide1, double sSide2, double sSide3) {
    this.side1 = sSide1;
    this.side2 = sSide2;
    this.side3 = sSide3;
  }

  public double getArea() {
    double s = (this.side1 + this.side2 + this.side3) / 2;
    return Math.sqrt(s * (s - this.side1) * (s - this.side2) * (s - this.side3));
  }

  public double getPerimeter() {
    return (this.side1 + this.side2 + this.side3);
  }

  public String toString() {
    return String.format("Triangle: side1 = %.1f side2 = %.1f side3 = %.1f", side1, side2, side3);
  }

}