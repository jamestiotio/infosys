import java.util.Arrays;

public class Pset1 {

  // Using boolean array costs us a time complexity of O(n)
  public static boolean isAllCharacterUnique(String sIn) {
    if (sIn.length() > 128) {
      return false;
    }

    boolean[] chars = new boolean[128];
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

    int count[] = new int[128];
    Arrays.fill(count, 0);
    int i;

    for (i = 0; i < sIn1.length(); i++) {
      count[(int)sIn1.charAt(i)]++;
      count[(int)sIn2.charAt(i)]--;
    }

    for (i = 0; i < 128; i++) {
      if (count[i] != 0) {
        return false;
      }
    }

    return true;
  }

}