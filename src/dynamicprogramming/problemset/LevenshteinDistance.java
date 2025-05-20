package dynamicprogramming.problemset;

import java.util.Arrays;

public class LevenshteinDistance {
    public static void main(String[] args) {
        String s1 = "horse";
        String s2 = "ros";
        System.out.println(levenshtein_distance(s1, s2));
        System.out.println(levenshtein_distance2(s1, s2));
        s1="pizza";
        s2="yolo";
        System.out.println(levenshtein_distance(s1, s2));
        System.out.println(levenshtein_distance2(s1, s2));
    }

    static Integer levenshtein_distance(String word1, String word2) {
        int[][] memo = new int[word1.length() + 1][word2.length() + 1];
        for (int i = 0; i <= word1.length(); i++) {
            Arrays.fill(memo[i], -1);
        }
        return levenshtein_distance_util(0, 0, word1, word2, memo);
    }

    private static int levenshtein_distance_util(int i, int j, String word1, String word2, int[][] memo) {
        if (i == word1.length()) {
            return word2.length() - j;
        }
        if (j == word2.length()) {
            return word1.length() - i;
        }
        if (memo[i][j] != -1) {
            return memo[i][j];
        }
        int ans;
        if (word1.charAt(i) == word2.charAt(j)) {
            ans = levenshtein_distance_util(i + 1, j + 1, word1, word2, memo);
        } else {
            ans = 1 + Math.min(levenshtein_distance_util(i + 1, j, word1, word2, memo),
                    Math.min(levenshtein_distance_util(i, j + 1, word1, word2, memo), levenshtein_distance_util(i + 1, j + 1, word1, word2, memo)));
        }
        return memo[i][j] = ans;
    }

    static Integer levenshtein_distance2(String word1, String word2) {
        int[][] dp=new int[word1.length()+1][word2.length()+1];
        dp[0][0]=0;
        for(int i=1;i<=word1.length();i++){
            dp[i][0]=i;
        }
        for(int i=1;i<=word2.length();i++){
            dp[0][i]=i;
        }
        for (int i = 1; i <= word1.length(); i++) {
            for (int j = 1; j <= word2.length(); j++) {
                if(word1.charAt(i-1)==word2.charAt(j-1)){
                    dp[i][j]=dp[i-1][j-1];
                }else{
                    dp[i][j]=1+Math.min(dp[i-1][j],Math.min(dp[i][j-1],dp[i-1][j-1]));
                }
            }
        }
        return dp[word1.length()][word2.length()];
    }
}
