package dynamicprogramming;

import java.util.ArrayList;

public class MinCostClimbingStairs {
    public static void main(String[] args) {
        ArrayList<Integer> arr = new ArrayList<>();
        arr.add(1);
        arr.add(1);
        arr.add(2);
        System.out.println(min_cost_climbing_stairs(arr));

        System.out.println(min_cost_climbing_stairs1(arr));

    }

    static Integer min_cost_climbing_stairs(ArrayList<Integer> cost) {
        // Write your code here.
        int n = cost.size();
        int[] dp = new int[n];

        for (int i = 0; i < n; i++) {
            if (i < 2) {
                dp[i] = cost.get(i);
            } else {
                dp[i] = cost.get(i) + Math.min(dp[i - 1], dp[i - 2]);
            }
        }
        return Math.min(dp[n - 1], dp[n - 2]);
    }


    //space optimised
    static Integer min_cost_climbing_stairs1(ArrayList<Integer> cost) {
        // Write your code here.

        int n = cost.size();
        int[] dp = new int[n];

        int first=cost.get(0);
        int second=cost.get(1);

        for (int i = 2; i < n; i++) {
            if(i%2==0){
                first=cost.get(i) + Math.min(first,second);
            }else{
                second=cost.get(i) + Math.min(first,second);
            }



        }
        return Math.min(first,second);
    }


}
