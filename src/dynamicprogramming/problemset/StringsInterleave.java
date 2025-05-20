package dynamicprogramming.problemset;

public class StringsInterleave {
    public static void main(String[] args) {
        String s1 = "ABC";
        String s2 = "D";
        String s3 = "DABC";
        System.out.println(do_strings_interleave(s1, s2, s3));
        System.out.println(do_strings_interleave1(s1, s2, s3));
    }


    static Boolean do_strings_interleave(String a, String b, String i) {
        int lenA = a.length();
        int lenB = b.length();
        int lenI = i.length();
        if (lenI != lenA + lenB) {
            return false;
        }
        return helper(a, b, i, 0, 0);
    }

    private static Boolean helper(String s1, String s2, String s3, int i, int j) {
        if (i == s1.length() && j == s2.length()) {
            return true;
        }

        if (i < s1.length() && j < s2.length() && s1.charAt(i) == s2.charAt(j) && s2.charAt(j) == s3.charAt(i + j)) {
            return helper(s1, s2, s3, i + 1, j) || helper(s1, s2, s3, i, j + 1);
        } else if (i < s1.length() && s1.charAt(i) == s3.charAt(i + j)) {
            return helper(s1, s2, s3, i + 1, j);
        } else if (j < s2.length() && s2.charAt(j) == s3.charAt(i + j)) {
            return helper(s1, s2, s3, i, j + 1);
        } else {
            return false;
        }
    }

    static Boolean do_strings_interleave1(String a, String b, String i) {
        int n = a.length();
        int m = b.length();
        if (i.length() != n + m) {
            return false;
        }
        boolean[][] dp = new boolean[n + 1][m + 1];
        dp[0][0] = true;
        for (int indexI = 0; indexI < i.length(); indexI++) {
            for (int indexA = 0; indexA < n; indexA++) {
                if (a.charAt(indexA) == i.charAt(indexI)) {
                    int aLen = indexA;
                    int bLen = indexI - indexA;
                    if (bLen >= 0 && bLen <= b.length() && dp[aLen][bLen]) {
                        dp[aLen + 1][bLen] = true;
                    }
                }
            }
            for (int indexB = 0; indexB < m; indexB++) {
                if (b.charAt(indexB) == i.charAt(indexI)) {
                    int aLen = indexI - indexB;
                    int bLen = indexB;
                    if (aLen >= 0 && aLen <= a.length() && dp[aLen][bLen]) {
                        dp[aLen][bLen + 1] = true;
                    }
                }
            }
        }
        return dp[n][m];
    }

}
