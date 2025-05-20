package dynamicprogramming.problemset;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class WordWrap {
    public static void main(String[] args) {
        String[] words = {"abcdefghijkl", "abcdefg", "abcdefgh", "abcdefghijklmnopqrstuv"};
        int limit = 23;
        List<String> list = new ArrayList<>();
        Arrays.stream(words).forEach(list::add);
        System.out.println(solve_balanced_line_breaks(list, limit));
        System.out.println(solve_balanced_line_breaks1(list, limit));
        System.out.println(solve_balanced_line_breaks2(list, limit));
    }

    static Long solve_balanced_line_breaks(List<String> words, int limit) {
        return helper(words, limit, 0);
    }

    private static Long helper(List<String> words, int limit, int start) {
        int n = words.size();
        if (start >= n) {
            return 0L;
        }
        long result = Long.MAX_VALUE;
        int countNoOfChars = 0;
        int countNoOfWords = 0;
        int countTotalNoOfChars = 0;
        long cost = 0;
        for (int i = start; i < n; i++) {
            countNoOfChars += words.get(i).length();
            countNoOfWords++;
            countTotalNoOfChars = countNoOfChars + countNoOfWords - 1;
            if (countTotalNoOfChars > limit) {
                break;
            }
            cost = (i == n - 1) ? 0 : (limit - countTotalNoOfChars);
            cost = (long) Math.pow(cost, 3);
            result = Math.min(result, cost + helper(words, limit, i + 1));
        }
        return result;
    }

    static Long solve_balanced_line_breaks1(List<String> words, int limit) {
        long[] dp = new long[words.size() + 1];
        Arrays.fill(dp, -1);
        return helper1(words, limit, 0, dp);
    }

    private static Long helper1(List<String> words, int limit, int start, long[] dp) {
        int n = words.size();
        if (start >= n) {
            return 0L;
        }
        if (dp[start] != -1) {
            return dp[start];
        }
        long result = Long.MAX_VALUE;
        int countNoOfChars = 0;
        int countNoOfWords = 0;
        int countTotalNoOfChars = 0;
        long cost = 0;
        for (int i = start; i < n; i++) {
            countNoOfChars += words.get(i).length();
            countNoOfWords++;
            countTotalNoOfChars = countNoOfChars + countNoOfWords - 1;
            if (countTotalNoOfChars > limit) {
                break;
            }
            cost = (i == n - 1) ? 0 : (limit - countTotalNoOfChars);
            cost = (long) Math.pow(cost, 3);
            result = Math.min(result, cost + helper1(words, limit, i + 1, dp));
        }
        return dp[start] = result;
    }

    static Long solve_balanced_line_breaks2(List<String> words, int limit) {
        int n = words.size();
        long[] dp = new long[n + 1];
        int countNoOfChars = 0;
        int countNoOfWords = 0;
        int countTotalNoOfChars = 0;
        long cost = 0;
        for (int i = n - 1; i >= 0; i--) {
            dp[i] = Long.MAX_VALUE;
            countNoOfChars=0;
            countNoOfWords=0;
            for (int j = i; j < n; j++) {
                countNoOfChars+= words.get(j).length();
                countNoOfWords++;
                countTotalNoOfChars = countNoOfChars + countNoOfWords - 1;
                if (countTotalNoOfChars > limit) {
                    break;
                }
                cost = (i == n - 1) ? 0 : (limit - countTotalNoOfChars);
                cost = (long) Math.pow(cost, 3);
                dp[i] = Math.min(cost+dp[j+1], dp[i]);
            }
        }
        return dp[0];
    }
}
