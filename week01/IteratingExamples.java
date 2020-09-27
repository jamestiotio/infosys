import java.util.List;
import java.util.Iterator;

public class IteratingExamples {

  public static int Act2Iterator(List<Integer> integers) {
    int sum = 0;
    Iterator<Integer> iter = integers.iterator();

    while (iter.hasNext()) {
      sum += iter.next();
    }

    return sum;
  }

  public static int Act2ForEach(List<Integer> integers) {
    int sum = 0;

    for (Integer i: integers) {
      sum += i;
    }

    return sum;
  }

}