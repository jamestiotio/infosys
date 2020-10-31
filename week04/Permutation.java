import java.util.ArrayList;

public class Permutation {
  private final String in;
  private ArrayList<String> a = new ArrayList<>();

  Permutation(final String str) {
    this.in = str;
  }

  public void permute() {
    // Base case: add character to result list
    if (in.length() <= 1) {
      a.add(in);
    } else {
      // Recursive case: go through each character as the first character entry of the permutation
      // set
      for (int i = 0; i < in.length(); i++) {
        String s = in.substring(0, i) + in.substring(i + 1); // Extract substring without current
                                                             // character in consideration
        Permutation subset = new Permutation(s);
        subset.permute(); // Permute the substring

        for (String subPerm : subset.getA()) {
          a.add(in.charAt(i) + subPerm); // Add all the permutations of the substring with the
                                         // current character as the first entry
        }
      }
    }
  }

  public ArrayList<String> getA() {
    return this.a;
  }
}
