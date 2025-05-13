package dynamicprogramming;

//https://leetcode.com/problems/unique-paths-ii/
public class UniquePathsWIthObstacles {
    public static void main(String[] args) {
        int[][] obstacleGrid={{0,0,0},{0,1,0},{0,0,0}};
        System.out.println(uniquePathsWithObstacles(obstacleGrid));
    }
    public static int uniquePathsWithObstacles(int[][] obstacleGrid) {
        if (obstacleGrid == null || obstacleGrid[0][0] == 1) {
            return 0;
        }
        int n=obstacleGrid.length;
        int m=obstacleGrid[0].length;
        int[][] dp = new int[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if(obstacleGrid[i][j]==1){
                    dp[i][j] = 0;
                }else if(i==0 && j>0 && dp[i][j-1]==0){
                    dp[i][j] = 0;
                }
                else if(j==0 && i>0 && dp[i-1][j]==0){
                    dp[i][j] = 0;
                }
                else if (i == 0 || j == 0) {
                    dp[i][j] = 1;
                }
                else {
                    dp[i][j] = (dp[i - 1][j] + dp[i][j - 1]);
                }
            }
        }
        return dp[n - 1][m - 1];
    }
}
