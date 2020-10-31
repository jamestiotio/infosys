import java.util.Arrays;

public class Palindrome {

  public static boolean isPalindrome(char[] s) {
    int numOfChars = s.length;

    // Base case
    if (numOfChars == 0 || numOfChars == 1) {
      return true;
    }

    // Recursive case
    if (s[0] == s[numOfChars - 1]) {
      char[] newS = Arrays.copyOfRange(s, 1, numOfChars - 1);
      return isPalindrome(newS);
    }

    return false;
  }

}
