public class BinarySearch {

  public static int binSearch(int[] a, int target, int l, int r) {
    int mid = (l + r) / 2;

    // Base case
    if (l > r) {
      if (a[l] == target)
        return l;
      else
        return -1; // not found
    }

    // Recursive step
    if (a[mid] == target) {
      return mid;
    } else {
      if (a[mid] < target) {
        return binSearch(a, target, mid + 1, r); // right-hand side
      } else {
        return binSearch(a, target, l, mid - 1); // left-hand side
      }
    }
  }

}
