package dynamicprogramming.problemset;

import java.util.ArrayList;
import java.util.Arrays;

public class NoOfWaystoMakeChange {
    static int MOD=1000000007;
    public static void main(String[] args) {
        int[] denominations = {1, 2, 3};
        int amount=3;
        ArrayList<Integer> denominationList=new ArrayList<>();
        Arrays.stream(denominations).forEach(denominationList::add);
        System.out.println(number_of_ways(denominationList, amount));
        System.out.println(number_of_ways_dp(denominationList, amount));
        System.out.println(number_of_ways_dp_iterative(denominationList, amount));

    }
    static Integer number_of_ways(ArrayList<Integer> coins, Integer amount) {
        return number_of_ways_recursive(coins,coins.size()-1,amount);
    }

    private static Integer number_of_ways_recursive(ArrayList<Integer> coins, int index, Integer amount) {
        if(amount<0){
            return 0;
        }
        if(index<0){
            if(amount==0){
                return 1;
            }else{
                return 0;
            }
        }
        int ways=number_of_ways_recursive(coins,index,amount-coins.get(index));
        ways=ways+number_of_ways_recursive(coins,index-1,amount);
        return ways;
    }

    static Integer number_of_ways_dp(ArrayList<Integer> coins, Integer amount) {
        int[][] dp=new int[coins.size()+1][amount+1];
        for(int i=0;i<coins.size();i++){
            Arrays.fill(dp[i],-1);
        }
        return number_of_ways_recursive_dp(coins,coins.size()-1,amount,dp);
    }

    private static Integer number_of_ways_recursive_dp(ArrayList<Integer> coins, int index, Integer amount,int[][] dp) {
        if(amount<0){
            return 0;
        }
        if(index<0){
            if(amount==0){
                return 1;
            }else{
                return 0;
            }
        }
        if(dp[index][amount]!=-1){
            return dp[index][amount];
        }
        int ways=number_of_ways_recursive_dp(coins,index,amount-coins.get(index),dp);
        ways=(ways+number_of_ways_recursive_dp(coins,index-1,amount,dp))%MOD;
        return dp[index][amount]=ways;
    }


    static Integer number_of_ways_dp_iterative(ArrayList<Integer> coins, Integer amount) {
        int[][] dp=new int[2][amount+1];

        for(int i=0;i<coins.size();i++){
            for(int j=0;j<amount+1;j++){
                if(j==0){
                    dp[i%2][j]=1;
                }else{
                    if(i>0){
                        dp[i%2][j]=dp[(i-1)%2][j];
                    }

                    if(j-coins.get(i)>=0){
                        dp[i%2][j]=(dp[i%2][j]+dp[i%2][j-coins.get(i)])%MOD;
                    }
                }
            }
        }
        return dp[(coins.size()-1)%2][amount];
    }

}
