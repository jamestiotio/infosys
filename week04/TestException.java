public class TestException {

  public static String tstException(int idx, String[] y) {
    try {
      return y[idx];
    }
    catch(ArrayIndexOutOfBoundsException e) {
      return "Out of Bounds";
    }
  }

}
