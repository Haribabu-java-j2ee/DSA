package dynamicprogramming;

import java.util.Arrays;

public class NChooseRTabulo {
    static int P = 1000000007;
    public static void main(String[] args) {
        System.out.println(ncrTabulisation(3,5));
        System.out.println(ncrTabulisation(5,3));
        System.out.println(ncrTabulisation(200,100));

        //recursive top down
        System.out.println(ncrTopDown(3,5));
        System.out.println(ncrTopDown(5,3));
        System.out.println(ncrTopDown(200,100));

        //basic recursive
        System.out.println(ncrRecursive(3,5));
        System.out.println(ncrRecursive(5,3));
        //regular recursion taking more time to compute hence reducing the range
        System.out.println(ncrRecursive(20,10));

    }

    private static int ncrTopDown(int n, int r) {
        if(n<r){
            return 0;
        }
        int[][] dp = new int[n + 1][r + 1];
        for(int i=0;i<=n;i++){
            Arrays.fill(dp[i],-1);
        }
        recursiveHelper(n,r,dp);
        return dp[n][r];
    }

    private static int recursiveHelper(int n, int r, int[][] dp) {
        if(r==0||n==r){
            return 1;
        }
        if(dp[n][r]!=-1){
            return dp[n][r];
        }
        dp[n][r]=(recursiveHelper(n-1,r,dp)+recursiveHelper(n-1,r-1,dp))%P;
        return dp[n][r];
    }

    static Integer ncrTabulisation(Integer n, Integer r) {
        // Write your code here.

        if(n<r){
            return 0;
        }
        if(r==0 || n==r){
            return 1;
        }
        int[][] ncrTable=new int[n+1][r+1];
        for(int row=0;row<=n;row++){
            ncrTable[row][0]=1;
        }
        for(int col=0;col<=r;col++){
            ncrTable[col][col]=1;
        }

        for(int row=2;row<=n;row++){
            for(int col=1;col<=Math.min(row,r);col++){
                ncrTable[row][col]=(ncrTable[row-1][col-1]+ncrTable[row-1][col])%P;
            }
        }
        return ncrTable[n][r];
    }

    static Integer ncrRecursive(Integer n, Integer r) {
        if(n<r){
            return 0;
        }
        if(r==0|| n==r){
            return 1;
        }
        return (ncrRecursive(n-1,r)+ncrRecursive(n-1,r-1))%P;
    }

}
