package dynamicprogramming;

import java.util.Arrays;

public class KnapSack {
    public static void main(String[] args) {
        int capacity = 4;
        int val[] = {1, 2, 3};
        int wt[] = {4, 5, 1};
        System.out.println(knapsack(capacity, val, wt));

        System.out.println(knapsackWithDP(capacity, val, wt));

        System.out.println(knapsackWithTabularDP(capacity, val, wt));
    }



    private static int knapsackWithTabularDP(int capacity, int[] val, int[] wt) {
        int[][] dp = new int[wt.length + 1][capacity + 1];

            for (int i = 1; i <= wt.length; i++) {
                for (int j = 1; j <= capacity; j++) {
                    int pick = 0, notpick = 0;
                    if (wt[i - 1] <= j)
                        pick = val[i - 1] + dp[i - 1][j - wt[i - 1]];

                    notpick = dp[i][j] = dp[i - 1][j];

                    dp[i][j] = Math.max(pick, notpick);
                }
            }

        return dp[wt.length][capacity];
    }

    static int knapsack(int capacity, int val[], int wt[]) {
        // code here
        return recursiveHelper(capacity, val, wt, wt.length);
    }

    private static int recursiveHelper(int capacity, int[] val, int[] wt, int n) {
        if (capacity <= 0 || n == 0) {
            return 0;
        }
        int pick = 0;
        if (wt[n - 1] <= capacity) {
            pick = val[n - 1] + recursiveHelper(capacity - wt[n - 1], val, wt, n - 1);
        }
        int notPick = recursiveHelper(capacity, val, wt, n - 1);
        return Math.max(pick, notPick);
    }

    static int knapsackWithDP(int capacity, int val[], int wt[]) {
        // code here
        int[][] dp = new int[capacity + 1][wt.length + 1];
        for (int i = 0; i <= capacity; i++) {
            Arrays.fill(dp[i], -1);
        }

        return recursiveHelperWithDP(capacity, val, wt, wt.length, dp);
    }

    private static int recursiveHelperWithDP(int capacity, int[] val, int[] wt, int n, int[][] dp) {
        if (capacity <= 0 || n == 0) {
            dp[capacity][n] = 0;
            return dp[capacity][n];
        }
        if (dp[capacity][n] != -1) {
            return dp[capacity][n];
        }
        int pick = 0;
        if (wt[n - 1] <= capacity) {
            pick = val[n - 1] + recursiveHelperWithDP(capacity - wt[n - 1], val, wt, n - 1, dp);
        }
        int notPick = recursiveHelperWithDP(capacity, val, wt, n - 1, dp);
        dp[capacity][n] = Math.max(pick, notPick);
        return dp[capacity][n];
    }
}
