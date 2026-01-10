package dsapatterns.slidingwindow;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * =====================================================================================
 * PERMUTATION IN STRING - Fixed Size Sliding Window
 * =====================================================================================
 * LeetCode: https://leetcode.com/problems/permutation-in-string/
 *
 * =====================================================================================
 * INTUITION:
 * =====================================================================================
 * A permutation has exact same character frequencies. Use fixed window of size len(s1).
 * Compare character frequency maps of s1 and current window.
 * Slide window one position at a time, updating counts incrementally.
 *
 * =====================================================================================
 * EXAMPLE: s1 = "ab", s2 = "eidbaooo" → returns true (window "ba" at index 3-4)
 * =====================================================================================
 *
 *   map1 (s1="ab"): a:1, b:1
 *
 *   Window | map2              | match?
 *   -------|-------------------|--------
 *   "ei"   | e:1, i:1          | no
 *   "id"   | i:1, d:1          | no
 *   "db"   | d:1, b:1          | no
 *   "ba"   | b:1, a:1          | YES! ← permutation found
 *
 *   Result: true
 *
 * =====================================================================================
 * TWO APPROACHES:
 * =====================================================================================
 *   1. checkInclusion()  - Correct: Fixed window, compare frequency maps
 *   2. checkInclusion1() - INCORRECT: Only checks if s1 chars exist in s2, ignores order/window
 *
 * =====================================================================================
 * COMPLEXITY:
 * =====================================================================================
 *   Time:  O(26 * (m - n)) = O(m) where m = len(s2), n = len(s1)
 *   Space: O(1) - Two fixed 26-size arrays
 *
 * =====================================================================================
 * EDGE CASES:
 * =====================================================================================
 *   1. s1 longer than s2 → return false
 *   2. s1 == s2 → return true
 *   3. s1 at very end of s2 → still detected
 *
 * =====================================================================================
 * OPTIMIZATION NOTE:
 * =====================================================================================
 * Instead of comparing full 26-element arrays each time, can track number of
 * matching character counts. When matches == 26, return true. O(1) per comparison.
 */
public class PermutaionInString {
    public static void main(String[] args) {
        String s1="ab";
        String s2="eidbaooo";
        PermutaionInString obj=new PermutaionInString();
        System.out.println(obj.checkInclusion(s1, s2));
        System.out.println(obj.checkInclusion1(s1, s2));


    }
    
    /**
     * CORRECT APPROACH: Fixed window of size n, slide and compare frequency maps.
     */
    public boolean checkInclusion(String s1, String s2) {
        int n = s1.length();
        int m = s2.length();
        if (m<n){
            return false;
        }
        int map1[] = new int[26];
        int map2[] = new int[26];
        for (int i=0;i<n;i++){
            map1[s1.charAt(i)-'a']++;
            map2[s2.charAt(i)-'a']++;
        }
        if (isMatched(map1,map2)){
            return true;
        }
        System.out.println();
        for(int i=1;i<=m-n;i++){
            map2[s2.charAt(i-1)-'a']--;
            map2[s2.charAt(n+i-1)-'a']++;
            if (isMatched(map1,map2)){
                return true;
            }
        }
        return false;
    }
    private boolean isMatched(int[] map1, int[] map2){
        for(int i=0;i<26;i++){
            if(map1[i]!=map2[i])
                return false;
        }
        return true;
    }


    // this wont work since exact permutation is required, no additional characters allowed, i.e., for ab, ba is allowed , but boa or any other is not allowed
    public boolean checkInclusion1(String s1, String s2) {
        if(s2.length() <s1.length()){
            return false;
        }
        int[] charSetCount=new int[128];
        for(int i=0;i<s2.length();i++){
            charSetCount[s2.charAt(i)]++;
        }

        for(int i=0;i<s1.length();i++){
            int currentCount=charSetCount[s1.charAt(i)]--;
            if(currentCount<0){
                return false;
            }
        }
        return true;
    }

}
