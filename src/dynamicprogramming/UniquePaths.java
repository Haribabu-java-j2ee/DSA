package dynamicprogramming;

import java.util.Arrays;

public class UniquePaths {
    static int P = 1000000007;

    public static void main(String[] args) {
        System.out.println(unique_paths(3, 2));
        System.out.println(unique_paths(10, 14));

        System.out.println(unique_paths2(3, 2));
        System.out.println(unique_paths2(999, 1000));
    }

    private static int unique_paths(int n, int m) {
        int[][] dp = new int[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (i == 0 || j == 0) {
                    dp[i][j] = 1;
                } else {
                    dp[i][j] = (dp[i - 1][j] + dp[i][j - 1]) % P;
                }
            }
        }
        return dp[n - 1][m - 1];
    }

    private static int unique_paths2(int n, int m) {

        int[][] dp = new int[n + 1][m + 1];
        for (int i = 1; i <= n; i++) {
            Arrays.fill(dp[i], -1);
        }
        unique_paths2_helper(n, m, dp);
        return dp[n][m];
    }

    private static int unique_paths2_helper(int n, int m, int[][] dp) {
        if (n == 1 || m == 1) {
            dp[n][m] = 1;
            return dp[n][m];
        }
        if (dp[n][m] != -1) {
            return dp[n][m];
        }
        dp[n][m] = (unique_paths2_helper(n - 1, m, dp) % P + unique_paths2_helper(n, m - 1, dp) % P) % P;
        return dp[n][m] % P;
    }
}
