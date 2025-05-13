package recursion;

import java.util.Arrays;

public class FibonacciSeries {
    public static void main(String[] args) {
        System.out.println(find_fibonacci(5));
        System.out.println(find_fibonacci1(2));
    }


    //2^n solution
    static Integer find_fibonacci(Integer n) {
        // Write your code here.
        if (n == 0) {
            return 0;
        } else if (n == 1) {
            return 1;
        }
        return find_fibonacci(n - 1) + find_fibonacci(n - 2);
    }


    static Integer find_fibonacci1(Integer n) {
        // Write your code here.
        int[] dp = new int[50];
        Arrays.fill(dp, -1);
        return find_fibonacci_helper(n, dp);

    }

    static Integer find_fibonacci_helper(Integer n, int[] dp) {

        if (dp[n] != -1) {
            return dp[n];
        }
        if (n == 0) {
            return 0;
        } else if (n == 1) {
            return 1;
        }

        dp[n] = find_fibonacci_helper(n - 1, dp) + find_fibonacci_helper(n - 2, dp);

        return dp[n];
    }

}
