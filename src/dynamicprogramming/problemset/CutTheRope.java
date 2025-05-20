package dynamicprogramming.problemset;

import java.util.ArrayList;
import java.util.Arrays;

public class CutTheRope {
    public static void main(String[] args) {
        System.out.println(max_product_from_cut_pieces(8));
        System.out.println(max_product_from_cut_pieces1(8));
        System.out.println(max_product_from_cut_pieces2(8));
    }

    static Long max_product_from_cut_pieces(Integer n) {
        // Write your code here.
        if (n == 0 || n == 1) {
            return 0l;
        }
        long maxProduct = 1;
        for (int i = 1; i <= n; i++) {
            maxProduct = Math.max(maxProduct, Math.max(i * (n - i), max_product_from_cut_pieces(n - i) * i));
        }
        return maxProduct;
    }

    static Long max_product_from_cut_pieces2(Integer n) {
        // Write your code here.
        if (n <= 1) {
            return 0l;
        }
        long[] dp = new long[n + 1];
        dp[1] = 1;
        for (int i = 2; i <= n; i++) {
            for (int j = 1; j <= i; j++) {
                dp[i] = Math.max(dp[i], Math.max(j * dp[i - j], j * (i - j)));

            }
        }
        return dp[n];
    }


    static Long max_product_from_cut_pieces1(Integer n) {
        // Write your code here.
        if (n == 2 || n == 3) {
            return Long.valueOf(n - 1);
        }
        long res = 1l;
        while (n > 4) {
            n -= 3;
            res *= 3;
        }

        return n * res;
    }


}
