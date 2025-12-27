package dsapatterns.twopointer;

/**
 * =====================================================================================
 * APPEND CHARACTERS TO STRING TO MAKE SUBSEQUENCE - Two Pointer Approach
 * =====================================================================================
 * LeetCode: https://leetcode.com/problems/append-characters-to-string-to-make-subsequence/
 *
 * =====================================================================================
 * INTUITION:
 * =====================================================================================
 * Find longest prefix of t that is already a subsequence of s.
 * Remaining characters of t need to be appended. Use two pointers: move t pointer
 * only when characters match, always move s pointer.
 *
 * =====================================================================================
 * EXAMPLE: s="coaching", t="coding" → 4
 * =====================================================================================
 *
 *   sIdx | tIdx | s[sIdx] | t[tIdx] | Match? | Action
 *   -----|------|---------|---------|--------|--------
 *    0   |  0   |   'c'   |   'c'   |   ✓    | tIdx++
 *    1   |  1   |   'o'   |   'o'   |   ✓    | tIdx++
 *    2   |  2   |   'a'   |   'd'   |   ✗    | skip
 *    3   |  2   |   'c'   |   'd'   |   ✗    | skip
 *    4   |  2   |   'h'   |   'd'   |   ✗    | skip
 *    5   |  2   |   'i'   |   'd'   |   ✗    | skip
 *    6   |  2   |   'n'   |   'd'   |   ✗    | skip
 *    7   |  2   |   'g'   |   'd'   |   ✗    | skip
 *    s exhausted
 *
 *   tIdx = 2, tLength = 6
 *   Remaining to append = 6 - 2 = 4 characters ("ding")
 *
 *   Result: 4
 *
 * =====================================================================================
 * COMPLEXITY:
 * =====================================================================================
 *   Time:  O(m + n) - Single pass through both strings
 *   Space: O(1) - Only pointer variables
 *
 * =====================================================================================
 * EDGE CASES:
 * =====================================================================================
 *   1. t is already subsequence of s → return 0
 *   2. No characters match → return length of t
 *   3. s is empty → return length of t
 *   4. t is empty → return 0
 *
 * =====================================================================================
 * OPTIMIZATION NOTE: Code is optimal. Linear time with constant space.
 * =====================================================================================
 */
public class AppendCharsToMakeSubsequence {
    public static void main(String[] args) {
        String s = "coahing";
        String t = "ocding";
        AppendCharsToMakeSubsequence obj = new AppendCharsToMakeSubsequence();
        System.out.println(obj.appendCharacters(s, t));
    }
    
    public int appendCharacters(String s, String t) {
        int sIndex = 0, tIndex = 0;
        int sLength = s.length(), tLength = t.length();
        
        while (sIndex < sLength && tIndex < tLength) {
            if (s.charAt(sIndex) == t.charAt(tIndex)) {
                tIndex++;
            }
            sIndex++;
        }
        
        return tLength - tIndex;
    }
}
