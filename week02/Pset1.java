import java.util.Arrays;

public class Pset1 {

  private static final int ASCII_CHARS_COUNT = 128;

  // Using boolean array costs us a time complexity of O(n)
  public static boolean isAllCharacterUnique(String sIn) {
    if (sIn.length() > ASCII_CHARS_COUNT) {
      return false;
    }

    boolean[] chars = new boolean[ASCII_CHARS_COUNT];
    Arrays.fill(chars, false);

    for (int i = 0; i < sIn.length(); i++) {
      int index = (int)sIn.charAt(i);

      if (chars[index]) {
        return false;
      }

      chars[index] = true;
    }

    return true;
  }

  // Using a single count array costs us a time complexity of O(n)
	public static boolean isPermutation(String sIn1, String sIn2) {
	  if (sIn1.length() != sIn2.length()) {
      return false;
    }

    int[] count = new int[ASCII_CHARS_COUNT];
    Arrays.fill(count, 0);
    int i;

    for (i = 0; i < sIn1.length(); i++) {
      count[(int)sIn1.charAt(i)]++;
      count[(int)sIn2.charAt(i)]--;
    }

    for (i = 0; i < ASCII_CHARS_COUNT; i++) {
      if (count[i] != 0) {
        return false;
      }
    }

    return true;
  }

}