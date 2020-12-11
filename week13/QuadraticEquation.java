import java.security.SecureRandom;

public class QuadraticEquation {
  private int aMax;
  private int cMax;
  
  static class QuadraticCoefficient {
    private int a;
    private int b;
    private int c;

    QuadraticCoefficient(int a, int b, int c) {
      this.a = a;
      this.b = b;
      this.c = c;
    }

    public int getA() {
      return a;
    }

    public int getB() {
      return b;
    }

    public int getC() {
      return c;
    }

    @Override
    public String toString() {
      return "y = " + a + "x^2 + " + b + "x + " + c;
    }
  }

  private QuadraticEquation(int aMax, int cMax) {
    this.aMax = aMax;
    this.cMax = cMax;
  }

  public static QuadraticEquation getEquationGenerator(int aMax, int cMax) {
    return new QuadraticEquation(aMax, cMax);
  }

  public QuadraticCoefficient getTwoRoots() {
    SecureRandom rng = new SecureRandom();

    int genA = rng.nextInt(this.aMax) + 1;
    int genC = rng.nextInt(this.cMax) + 1;

    int genB = rng.nextInt(100);

    // Check determinant to verify existence of two real roots
    while ((Math.pow(genB, 2) - (4 * genA * genC)) < 0) {
      genB = rng.nextInt(100);
    }

    return new QuadraticCoefficient(genA, genB, genC);
  }
}