import java.util.Comparator;

public class OctagonComparator implements Comparator<Octagon> {

  @Override
  public int compare(Octagon a, Octagon b) {
    return (int) (a.getSide() - b.getSide());
  }

}
