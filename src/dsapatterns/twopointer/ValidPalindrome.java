package dsapatterns.twopointer;

/**
 * =====================================================================================
 * VALID PALINDROME - Two Pointer Approach
 * =====================================================================================
 * LeetCode: https://leetcode.com/problems/valid-palindrome/
 *
 * =====================================================================================
 * INTUITION:
 * =====================================================================================
 * Compare characters from both ends, skip non-alphanumeric chars.
 * If all pairs match, it's a palindrome. Use two pointers moving inward.
 *
 * =====================================================================================
 * EXAMPLE: "A man, a plan, a canal: Panama" → true
 * =====================================================================================
 *
 *   After preprocessing: "amanaplanacanalpanama"
 *
 *   Step | left | right | char[l] | char[r] | Match?
 *   -----|------|-------|---------|---------|--------
 *     1  |  0   |  20   |   'a'   |   'a'   |  ✓
 *     2  |  1   |  19   |   'm'   |   'm'   |  ✓
 *     3  |  2   |  18   |   'a'   |   'a'   |  ✓
 *     ...continues until left >= right...
 *
 *   All pairs match → return true
 *
 * =====================================================================================
 * COMPLEXITY:
 * =====================================================================================
 *   isPalindrome (replaceAll version):
 *     Time:  O(n) - replaceAll + single pass
 *     Space: O(n) - Creates new string after replaceAll
 *
 *   isPalindromeMoreOptimal (skip in-place):
 *     Time:  O(n) - Single pass with skipping
 *     Space: O(1) - No extra string created (preferred in interviews)
 *
 * =====================================================================================
 * EDGE CASES:
 * =====================================================================================
 *   1. Empty string → return true (vacuously true)
 *   2. Single char → return true
 *   3. Only special chars ".,!?" → return true (empty after filter)
 *   4. Mixed case "Aa" → return true
 *
 * =====================================================================================
 * OPTIMIZATION NOTE:
 * =====================================================================================
 * isPalindromeMoreOptimal is preferred - avoids O(n) space from replaceAll.
 * The second method skips non-alphanumeric in-place with O(1) space.
 * =====================================================================================
 */
public class ValidPalindrome {
    public static void main(String[] args) {
        String s = "A man, a plan, a canal: Panama";
        ValidPalindrome obj = new ValidPalindrome();
        System.out.println(obj.isPalindrome(s));
    }
    
    /**
     * Version 1: Uses replaceAll (O(n) space)
     */
    public boolean isPalindrome(String s) {
        s = s.toLowerCase().replaceAll("[^a-z0-9]", "");
        
        if (s.length() == 0) {
            return true;
        }
        
        int left = 0;
        int right = s.length() - 1;
        
        while (left < right) {
            if (s.charAt(left) != s.charAt(right)) {
                return false;
            }
            left++;
            right--;
        }
        
        return true;
    }

    /**
     * Version 2: Skip non-alphanumeric in-place (O(1) space - PREFERRED)
     */
    public boolean isPalindromeMoreOptimal(String s) {
        s = s.toLowerCase();
        int n = s.length();
        
        if (n == 1) {
            return true;
        }
        
        int left = 0;
        int right = n - 1;
        
        while (left < right) {
            if (!Character.isLetterOrDigit(s.charAt(left))) {
                left++;
                continue;
            }
            if (!Character.isLetterOrDigit(s.charAt(right))) {
                right--;
                continue;
            }
            if (s.charAt(left) != s.charAt(right)) {
                return false;
            }
            left++;
            right--;
        }
        
        return true;
    }
}
