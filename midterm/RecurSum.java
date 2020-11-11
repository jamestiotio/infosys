/*
 * QUESTION 3 Part-B
 * 
 * In Vocareum:
 * - Click BUILD to compile
 * - RUN to execute the main function
 * - SUBMIT to submit your answer
 */

public class RecurSum {
    private static int[][] table;

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
        if (k == 0) {
            return 0;
        }

        if (n == 0) {
            return 1;
        }

        if (n < 0) {
            return 0;
        }

        if (table[n - 1][k - 1] == 0) {
            table[n - 1][k - 1] = computeNumOfIntegerPartitions(n, k - 1)
                    + computeNumOfIntegerPartitions(n - k, k);
        }

        return table[n - 1][k - 1];
    }
}
