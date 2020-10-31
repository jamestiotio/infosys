import java.util.ArrayList;

public class GetPath {

  public static boolean getPath(int r, int c, ArrayList<Point> path, final int[][] grid) {
    // Sanity check
    if (grid[r][c] != 0)
      return false;
    if ((r < 0) || (c < 0))
      return false;

    // Base case: we have arrived at the destination spot
    if ((r == 0) && (c == 0)) {
      path.add(new Point(0, 0));
      return true;
    }

    // Recursive case: traverse recursive subgrids from original (r,c) to original (0,0)
    // Case 1: Upward, Case 2: Leftward
    // The statements' ordering for the OR operator is important so that the path is the same as
    // specified in the Problem Set handout
    if (((r > 0) && (getPath(r - 1, c, path, grid)))
        || ((c > 0) && (getPath(r, c - 1, path, grid)))) {
      path.add(new Point(r, c));
      return true;
    }

    return false;
  }

}


class Point {
  int x;
  int y;

  Point(int x, int y) {
    this.x = x;
    this.y = y;
  }

  public String toString() {
    return "(" + x + "," + y + ")";
  }
}
