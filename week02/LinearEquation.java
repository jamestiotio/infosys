public class LinearEquation {

  private double a;
  private double b;
  private double c;
  private double d;
  private double e;
  private double f;

  LinearEquation(double sA, double sB, double sC, double sD, double sE, double sF) {
    this.a = sA;
    this.b = sB;
    this.c = sC;
    this.d = sD;
    this.e = sE;
    this.f = sF;
  }

  public double getA() {
    return this.a;
  }

  public double getB() {
    return this.b;
  }

  public double getC() {
    return this.c;
  }

  public double getD() {
    return this.d;
  }

  public double getE() {
    return this.e;
  }

  public double getF() {
    return this.f;
  }

  public boolean isSolvable() {
    return ((this.a * this.d - this.b * this.c) != 0);
  }

  public double getX() {
    return ((this.d * this.e - this.b * this.f) / (this.a * this.d - this.b * this.c));
  }

  public double getY() {
    return ((this.a * this.f - this.c * this.e) / (this.a * this.d - this.b * this.c));
  }

}