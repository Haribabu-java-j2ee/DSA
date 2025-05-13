package dynamicprogramming;

import java.util.ArrayList;
import java.util.Arrays;

public class MaxPathSum {
    public static void main(String[] args) {
        int[][] grid = {
                {4, 5, 8},
                {3, 6, 4},
                {2, 4, 7}
        };
        ArrayList<ArrayList<Integer>> gridList = new ArrayList<>();
        Arrays.stream(grid).forEach(e -> {
            ArrayList<Integer> gridRow = new ArrayList<>();
            Arrays.stream(e).forEach(gridRow::add);
            gridList.add(gridRow);
        });
        System.out.println(maximum_path_sum(gridList));
        System.out.println(maximum_path_sum2(gridList));

        int[][] grid1 = {
                {1, 2, 1},
                {1, 2, 1},
                {1, 9, 1}
        };
        ArrayList<ArrayList<Integer>> gridList1 = new ArrayList<>();
        Arrays.stream(grid1).forEach(e -> {
            ArrayList<Integer> gridRow = new ArrayList<>();
            Arrays.stream(e).forEach(gridRow::add);
            gridList1.add(gridRow);
        });
        System.out.println(min_path_sum(gridList1));

    }


    static Integer maximum_path_sum(ArrayList<ArrayList<Integer>> grid) {
        // Write your code here.
        int n = grid.size();
        int m = grid.get(0).size();
        int[][] dp = new int[n][m];
        dp[0][0] = grid.get(0).get(0);
        for (int i = 1; i < n; i++) {
            dp[i][0] = dp[i - 1][0] + grid.get(i).get(0);
        }
        for (int j = 1; j < m; j++) {
            dp[0][j] = dp[0][j - 1] + grid.get(0).get(j);
        }

        for (int i = 1; i < n; i++) {
            for (int j = 1; j < m; j++) {
                dp[i][j] = grid.get(i).get(j) + Math.max(dp[i - 1][j], dp[i][j - 1]);
            }
        }
        return dp[n - 1][m - 1];
    }

    static Integer min_path_sum(ArrayList<ArrayList<Integer>> grid) {
        // Write your code here.
        int n = grid.size();
        int m = grid.get(0).size();
        int[][] dp = new int[n][m];
        dp[0][0] = grid.get(0).get(0);
        for (int i = 1; i < n; i++) {
            dp[i][0] = dp[i - 1][0] + grid.get(i).get(0);
        }
        for (int j = 1; j < m; j++) {
            dp[0][j] = dp[0][j - 1] + grid.get(0).get(j);
        }

        for (int i = 1; i < n; i++) {
            for (int j = 1; j < m; j++) {
                dp[i][j] = grid.get(i).get(j) + Math.min(dp[i - 1][j], dp[i][j - 1]);
            }
        }
        return dp[n - 1][m - 1];
    }

    //recursive approach
    static Integer maximum_path_sum2(ArrayList<ArrayList<Integer>> grid) {
        // Write your code here.
        int n = grid.size();
        int m = grid.get(0).size();
        int[][] dp = new int[n][m];
        for (int i = 0; i < n; i++) {
            Arrays.fill(dp[i], -1);
        }
        Arrays.fill(dp[0], -1);
        return maximum_path_sum2_recursive(n - 1, m - 1, grid, dp);

    }

    private static int maximum_path_sum2_recursive(int i, int j, ArrayList<ArrayList<Integer>> grid, int[][] dp) {

        if (i == 0 && j == 0) {
            dp[i][j] = grid.get(i).get(j);
            return dp[i][j];
        }
        if (dp[i][j] != -1) {
            return dp[i][j];
        }
        if (i == 0) {
            dp[i][j] = grid.get(i).get(j) + maximum_path_sum2_recursive(i, j - 1, grid, dp);
            return dp[i][j];
        } else if (j == 0) {
            dp[i][j] = grid.get(i).get(j) + maximum_path_sum2_recursive(i - 1, j, grid, dp);
            return dp[i][j];
        } else {
            dp[i][j] = grid.get(i).get(j) + Math.max(maximum_path_sum2_recursive(i, j - 1, grid, dp), maximum_path_sum2_recursive(i - 1, j, grid, dp));
        }


        return dp[i][j];
    }

}
