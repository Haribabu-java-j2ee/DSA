package recursion;

import java.util.Arrays;
//https://www.geeksforgeeks.org/0-1-knapsack-problem-dp-10/
//check the last approach for optimised space
public class Knapsack0_1 {
    public static void main(String[] args) {
        int[] val = {1, 2, 3};
        int[] wt = {4, 5, 1};
        int W = 4;
        System.out.println(knapsackWithMem(W, val, wt));
    }

    //O(2^n) Time and O(n) Space
    static int knapsack(int W, int[] val, int[] wt){
        return maxProfit(W,val,wt,val.length);
    }

    static int maxProfit(int W, int[] val, int[] wt, int n){
        if(n==0||W==0){
            return 0;
        }
        int pick=0;
        if(wt[n-1]<=W){
            pick=val[n-1]+maxProfit(W-wt[n-1],val,wt,n-1);
        }
        int notPick=maxProfit(W,val,wt,n-1);
        return Math.max(pick,notPick);
    }

    //knapsack with memoization
    //O(n x W) Time and Space
    static int knapsackWithMem(int W, int[] val, int[] wt){
        int mem[][]=new int[val.length+1][W+1];
        for(int[] i:mem) {
            Arrays.fill(i, -1);
        }
        return maxProfit(W,val,wt,mem,val.length);
    }

    static int maxProfit(int W, int[] val, int[] wt,int[][] mem, int n){
        if(n==0||W==0){
            return 0;
        }

        if(mem[n][W]!=-1){
            return mem[n][W];
        }

        int pick=0;
        if(wt[n-1]<=W){
            pick=val[n-1]+maxProfit(W-wt[n-1],val,wt,mem,n-1);
        }

        int notPick=maxProfit(W,val,wt,mem,n-1);
        mem[n][W]=Math.max(pick,notPick);
        return mem[n][W];
    }
}
