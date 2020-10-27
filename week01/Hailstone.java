import java.util.ArrayList;

public class Hailstone {

  public static ArrayList<Integer> hailstone(int n) {
    int element = n;
    ArrayList<Integer> sequence = new ArrayList<Integer>();

    sequence.add(element);

    while (element != 1) {
      if (element % 2 == 0) {
        element /= 2;
      } else {
        element = (3 * element + 1);
      }

      sequence.add(element);
    }

    return sequence;
  }

}