package dsapatterns.twopointer;

/**
 * =====================================================================================
 * VALID PALINDROME II (Delete At Most One Character) - Two Pointer Approach
 * =====================================================================================
 * LeetCode: https://leetcode.com/problems/valid-palindrome-ii/
 *
 * =====================================================================================
 * INTUITION:
 * =====================================================================================
 * Use two pointers from both ends. If mismatch found, try skipping either left OR
 * right character. If either skip results in palindrome, return true.
 * Only one deletion allowed.
 *
 * =====================================================================================
 * EXAMPLE: "abca" → true (delete 'b' or 'c')
 * =====================================================================================
 *
 *   Step | left | right | char[l] | char[r] | Action
 *   -----|------|-------|---------|---------|------------------------
 *     1  |  0   |   3   |   'a'   |   'a'   | Match, move both
 *     2  |  1   |   2   |   'b'   |   'c'   | MISMATCH!
 *
 *   Try 1: Skip left (check "ca") → 'c'≠'a' → NOT palindrome
 *   Try 2: Skip right (check "bc") → 'b'≠'c' → NOT palindrome
 *
 *   Wait, let's re-check the indices:
 *   Skip left: isPalindrome(s, 2, 2) → "c" → single char → TRUE!
 *
 *   Actually: Skip left checks s[2..2] = "c" ✓
 *   
 *   Result: true (can form palindrome by deleting one char)
 *
 * =====================================================================================
 * COMPLEXITY:
 * =====================================================================================
 *   Time:  O(n) - At most 2 passes (main + one helper call)
 *   Space: O(1) - Only pointer variables
 *
 * =====================================================================================
 * EDGE CASES:
 * =====================================================================================
 *   1. Empty string → true
 *   2. Single character → true
 *   3. Already palindrome → true (no deletion needed)
 *   4. Two mismatches → false (can only delete one)
 *
 * =====================================================================================
 * OPTIMIZATION NOTE: Code is optimal. Key insight: only need to check ONE skip
 * direction at a time using OR logic.
 * =====================================================================================
 */
public class ValidPalindromeII {
    public static void main(String[] args) {
        String s = "abca";
        ValidPalindromeII obj = new ValidPalindromeII();
        System.out.println(obj.validPalindrome(s));
    }
    
    public boolean validPalindrome(String s) {
        int left = 0;
        int right = s.length() - 1;
        
        while (left < right) {
            if (s.charAt(left) != s.charAt(right)) {
                // Try skipping either left or right character
                return isPalindrome(s, left + 1, right) || isPalindrome(s, left, right - 1);
            }
            left++;
            right--;
        }
        
        return true;
    }

    private boolean isPalindrome(String s, int left, int right) {
        while (left < right) {
            if (s.charAt(left) != s.charAt(right)) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }
}
