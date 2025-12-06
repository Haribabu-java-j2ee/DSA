package dsapatterns.prefixsum;

//https://leetcode.com/problems/range-sum-query-2d-immutable/
public class RangeSum2DArr {
    public static void main(String[] args) {
        int[][] matrix = {
                {3, 0, 1, 4, 2},
                {5, 6, 3, 2, 1},
                {1, 2, 0, 1, 5},
                {4, 1, 0, 1, 7},
                {1, 0, 3, 0, 5}};
        NumMatrix numMatrix = new NumMatrix(matrix);

        System.out.println(numMatrix.sumRegion(2,1,4,3));
        System.out.println(numMatrix.sumRegion(1,1,2,2));

        System.out.println(numMatrix.sumRegion(1,2,2,4));


    }
}

class NumMatrix {
    int[][] dp;

    public NumMatrix(int[][] matrix) {
        dp = new int[matrix.length + 1][matrix[0].length + 1];
        for (int i = 1; i < dp.length; i++) {
            for (int j = 1; j < dp[0].length; j++) {
                dp[i][j] = matrix[i - 1][j - 1] + dp[i - 1][j] + dp[i][j - 1] - dp[i - 1][j - 1];
            }
        }
    }

    public int sumRegion(int row1, int col1, int row2, int col2) {
        int result = dp[row2 + 1][col2 + 1];
        if (row1 > 0) {
            result -= dp[row1][col2 + 1];
        }
        if (col1 > 0) {
            result -= dp[row2 + 1][col1];
        }
        if (row1 > 0 && col1 > 0) {
            result += dp[row1][col1];
        }
        return result;
    }
}
