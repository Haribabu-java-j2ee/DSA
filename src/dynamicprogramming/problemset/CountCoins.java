package dynamicprogramming.problemset;

import java.util.ArrayList;
import java.util.Arrays;

public class CountCoins {
    public static void main(String[] args) {
        ArrayList<Integer> coins=new ArrayList<>();
        coins.add(1);
        coins.add(3);
        coins.add(5);
        System.out.println(minimum_coins(coins,9));
    }

    static Integer minimum_coins(ArrayList<Integer> coins, Integer value) {
        // Write your code here.
        int[] dp=new int[value+1];
        Arrays.fill(dp,Integer.MAX_VALUE);
        dp[0]=0;
        for(int i=1;i<=value;i++){
            for(int coin:coins){
                if(i-coin>=0){
                    dp[i]=Math.min(dp[i],dp[i-coin]);
                }
            }
            dp[i]++;
        }
        return dp[value];
    }

}
