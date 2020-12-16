/*
 * QUESTION 3 Part-B
 * 
 * In Vocareum:
 * - Click BUILD to compile
 * - RUN to execute the main function
 * - SUBMIT to submit your answer
 */

public class RecurSum {
    private static int[][] table; // Use this table data structure (2D array) for memoization

    public static void main(String[] args) {
        int n, k;

        n = 8;
        k = 5;

        assert computeNumOfSumPattern(n, k) == 18;
    }

    public static int computeNumOfSumPattern(int n, int k) {
        RecurSum.table = new int[n][k];
        return computeNumOfIntegerPartitions(n, k);
    }

    // We solve recursively utilizing top-down dynamic programming (DP) so that we can achieve
    // better time complexity
    // DP greatly reduces/decreases time complexity from exponential to pseudo-polynomial
    public static int computeNumOfIntegerPartitions(int n, int k) {
        if (n == k) {
            return 1 + computeNumOfIntegerPartitions(n, k - 1); // computeNumOfIntegerPartitions(0,
                                                                // k) == 1 always for k >= 0
        }

        if ((k == 0) || (n < 0)) {
            return 0;
        }

        if ((n == 0) || (k == 1)) {
            return 1;
        }

        if (RecurSum.table[n - 1][k - 1] == 0) {
            RecurSum.table[n - 1][k - 1] = computeNumOfIntegerPartitions(n, k - 1)
                    + computeNumOfIntegerPartitions(n - k, k); // (number of combinations whose
                                                               // first element is less than k) +
                                                               // (number of combinations whose
                                                               // first element is equal to k)
        }

        return RecurSum.table[n - 1][k - 1];
    }
}
