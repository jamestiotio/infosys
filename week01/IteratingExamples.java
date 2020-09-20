import java.util.List;
import java.util.Iterator;

public class IteratingExamples {

  public static int Act2Iterator(List<Integer> integers) {
    int sum = 0;
    Iterator<Integer> iterator = integers.iterator();

    while (iterator.hasNext()) {
      sum += iterator.next();
    }

    return sum;
  }

  public static int Act2ForEach(List<Integer> integers) {
    int sum = 0;

    for (int i = 0; i < integers.size(); i++) {
      sum += integers.get(i);
    }

    return sum;
  }

}