public class Fibonacci{

  public static String fibonacci(int n) {
    int a = 0;
    int b = 1;
    String result = "";

    if (n == 1) {
      result = Integer.toString(a);
    }
    else if (n == 2) {
      result = Integer.toString(a) + "," + Integer.toString(b);
    }
    else {
      result = Integer.toString(a) + "," + Integer.toString(b);

      for (int i = 3; i <= n; i++) {
        int c = a + b;
        a = b;
        b = c;
        result += ("," + Integer.toString(b));
      }
    }

    return result;
  }

}