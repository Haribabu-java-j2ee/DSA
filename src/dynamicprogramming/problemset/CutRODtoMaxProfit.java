package dynamicprogramming.problemset;

import java.util.ArrayList;
import java.util.Arrays;

public class CutRODtoMaxProfit {
    public static void main(String[] args) {
        int[] prices = {1, 5, 8, 9};
        ArrayList<Integer> priceList = new ArrayList<>();
        Arrays.stream(prices).forEach(priceList::add);
        System.out.println(get_maximum_profit(priceList));
        System.out.println(get_maximum_profit_dp(priceList));
        System.out.println(get_maximum_profit_iterative(priceList));
    }

    static Integer get_maximum_profit(ArrayList<Integer> price) {
        return get_maximum_profit_recursive(price, price.size());
    }

    private static Integer get_maximum_profit_recursive(ArrayList<Integer> price, int size) {
        if (size <= 0) {
            return 0;
        }
        int maxProfit = 0;
        for (int i = 1; i <=size; i++) {
            maxProfit=Math.max(maxProfit,price.get(i-1)+get_maximum_profit_recursive(price, size-i));
        }
        return maxProfit;
    }


    static Integer get_maximum_profit_dp(ArrayList<Integer> price) {
        // Write your code here.
        int n=price.size();
        int[] dp=new int[n];
        Arrays.fill(dp,-1);
        get_maximum_profit_recursive_dp(price,n,dp);
        return dp[n-1];
    }

    static int get_maximum_profit_recursive_dp(ArrayList<Integer> price, int rodLength,int[] dp){
        if(rodLength<=0){
            return 0;
        }
        if(dp[rodLength-1]!=-1){
            return dp[rodLength-1];
        }
        int maxProfit=0;
        for(int i=1;i<=rodLength;i++){
            maxProfit=Math.max(maxProfit,price.get(i-1)+get_maximum_profit_recursive_dp(price,rodLength-i,dp));

        }
        return dp[rodLength-1]=maxProfit;
    }


    static Integer get_maximum_profit_iterative(ArrayList<Integer> price) {
        // Write your code here.
        int n=price.size();
        int[] dp=new int[n+1];

        for(int i=1;i<=n;i++){
            dp[i]=price.get(i-1);
            for(int j=1;j<i;j++){
                dp[i]=Math.max(dp[i],price.get(j-1)+dp[i-j]);
            }
        }
        return dp[n];
    }



}
