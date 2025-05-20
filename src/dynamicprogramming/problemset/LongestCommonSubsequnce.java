package dynamicprogramming.problemset;

public class LongestCommonSubsequnce {
    public static void main(String[] args) {
        String s1 = "abcde";
        String s2 = "ace";

        System.out.println(lcs(s1, s2));
        System.out.println(lcs1(s1, s2));

        s1="ABCDE";
        s2="AECBD";
        System.out.println(lcs(s1, s2));
        System.out.println(lcs1(s1, s2));
    }

    static String lcs(String a, String b) {
        // Write your code here.
        String[][] memo = new String[a.length() + 1][b.length() + 1];
        return lcsUtil(0, 0, a, b, memo);
    }

    private static String lcsUtil(int i, int j, String a, String b, String[][] memo) {
        if (i >= a.length() || j >= b.length()) {
            return "";
        }
        if (memo[i][j] != null) {
            return memo[i][j];
        }
        String ans;
        if (a.charAt(i) == b.charAt(j)) {
            String excludingBoth = lcsUtil(i + 1, j + 1, a, b, memo);
            ans = a.charAt(i) + excludingBoth;
        } else {
            String excludeith = lcsUtil(i + 1, j, a, b, memo);
            String excludejth = lcsUtil(i, j + 1, a, b, memo);
            ans = excludeith.length() > excludejth.length() ? excludeith : excludejth;
        }
        return memo[i][j] = ans;
    }

    static String lcs1(String a, String b) {
        int[][] dp = new int[a.length() + 1][b.length() + 1];

        for (int i = 0; i <= a.length(); i++) {
            for (int j = 0; j <= b.length(); j++) {
                if (i == 0 || j == 0) {
                    dp[i][j] = 0;
                }
                else if (a.charAt(i - 1) == b.charAt(j - 1)) {
                    dp[i][j] = 1 + dp[i - 1][j - 1];
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }
       int index=dp[a.length()][b.length()];
        char[] lcs = new char[index+1];
        lcs[index] = ' ';
        int i=a.length();
        int j=b.length();

        while(i>0 && j>0) {
            if (a.charAt(i-1) == b.charAt(j-1)) {
                lcs[index] = a.charAt(i-1);
                i--;
                j--;
                index--;
            }else if(dp[i-1][j]>dp[i][j-1]){
                i--;
            }else{
                j--;
            }
        }
        String res = new String(lcs);

        return res.trim();
    }
}
