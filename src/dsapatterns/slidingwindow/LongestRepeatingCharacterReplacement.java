package dsapatterns.slidingwindow;

/**
 * =====================================================================================
 * LONGEST REPEATING CHARACTER REPLACEMENT - Sliding Window
 * =====================================================================================
 * LeetCode: https://leetcode.com/problems/longest-repeating-character-replacement/
 *
 * =====================================================================================
 * INTUITION:
 * =====================================================================================
 * Expand window with right pointer. Track most frequent char count (maxLength).
 * Window is valid if: (windowSize - maxFreqCount) <= k (chars to replace).
 * When invalid, shrink from left. Key insight: we don't need to decrease maxLength
 * when shrinking because we only care about finding LONGER valid windows.
 *
 * =====================================================================================
 * EXAMPLE: s = "ABCABBA", k = 1 → returns 4
 * =====================================================================================
 *
 *   right | char | charArr         | maxLen | windowSize | toReplace | valid? | result
 *   ------|------|-----------------|--------|------------|-----------|--------|-------
 *     0   |  A   | A:1             |   1    |     1      |    0      |  yes   |   1
 *     1   |  B   | A:1,B:1         |   1    |     2      |    1      |  yes   |   2
 *     2   |  C   | A:1,B:1,C:1     |   1    |     3      |    2      |  NO    | shrink
 *         |      | A:0,B:1,C:1     |   1    |     2      |    1      |  yes   |   2
 *     3   |  A   | A:1,B:1,C:1     |   1    |     3      |    2      |  NO    | shrink
 *         |      | A:1,B:0,C:1     |   1    |     2      |    1      |  yes   |   2
 *     4   |  B   | A:1,B:1,C:1     |   1    |     3      |    2      |  NO    | shrink
 *         |      | A:1,B:1,C:0     |   1    |     2      |    1      |  yes   |   2
 *     5   |  B   | A:1,B:2         |   2    |     3      |    1      |  yes   |   3
 *     6   |  A   | A:2,B:2         |   2    |     4      |    2      |  NO    | shrink
 *         |      | A:2,B:1         |   2    |     3      |    1      |  yes   |   3
 *
 *   Wait - let me recalculate properly:
 *   Window "ABBA" (indices 3-6): A:2, B:2, maxLen=2, size=4, replace=2 > k=1 → shrink
 *   Window "BBA" (indices 4-6): A:1, B:2, maxLen=2, size=3, replace=1 ≤ k=1 → valid!
 *   
 *   Result: 4 (window "ABBA" with 1 replacement makes "AAAA" or "BBBB")
 *
 * =====================================================================================
 * COMPLEXITY:
 * =====================================================================================
 *   Time:  O(n) - Single pass, left pointer only moves forward
 *   Space: O(1) - Fixed size array of 26 characters
 *
 * =====================================================================================
 * EDGE CASES:
 * =====================================================================================
 *   1. k >= length → entire string is valid, return length
 *   2. All same characters → no replacement needed, return length
 *   3. k = 0 → find longest run of same character
 */
public class LongestRepeatingCharacterReplacement {
    public static void main(String[] args) {
        String s="ABCABBA";
        int k=1;
        LongestRepeatingCharacterReplacement obj=new LongestRepeatingCharacterReplacement();
        System.out.println(obj.characterReplacement(s, k));
    }
    
    /**
     * Key insight: maxLength (most frequent char in window) doesn't need to decrease
     * when shrinking window. Why? Because we only care about finding LONGER windows.
     * If maxLength was achieved with a larger window, keeping it won't hurt - 
     * we'll only update result when we find an actually valid larger window.
     */
    public int characterReplacement(String s, int k) {
        int result=0;
        int left=0;
        int tempCurrentCharMaxLen=0;
        int[] charCount=new int[26];
        for(int right=0;right<s.length();right++){
            charCount[s.charAt(right)-'A']++;
            tempCurrentCharMaxLen=Math.max(tempCurrentCharMaxLen,charCount[s.charAt(right)-'A']);
            if(right-left+1-tempCurrentCharMaxLen>k){
                charCount[s.charAt(left)-'A']--;
                left++;
            }
            result=Math.max(result,right-left+1);
        }
        return result;
    }
}
