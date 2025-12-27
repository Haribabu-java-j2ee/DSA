package dsapatterns.twopointer;

/**
 * =====================================================================================
 * REVERSE STRING - Two Pointer Approach
 * =====================================================================================
 * LeetCode: https://leetcode.com/problems/reverse-string/
 *
 * =====================================================================================
 * INTUITION:
 * =====================================================================================
 * Use two pointers at both ends. Swap characters and move inward until they meet.
 * Classic two-pointer in-place reversal.
 *
 * =====================================================================================
 * EXAMPLE: ['h','e','l','l','o'] → ['o','l','l','e','h']
 * =====================================================================================
 *
 *   Step | left | right | Array Before    | After Swap
 *   -----|------|-------|-----------------|----------------
 *     1  |  0   |   4   | [h,e,l,l,o]     | [o,e,l,l,h]
 *     2  |  1   |   3   | [o,e,l,l,h]     | [o,l,l,e,h]
 *     3  |  2   |   2   | left >= right   | STOP
 *
 *   Result: ['o','l','l','e','h']
 *
 * =====================================================================================
 * COMPLEXITY:
 * =====================================================================================
 *   Time:  O(n) - n/2 swaps
 *   Space: O(1) - In-place reversal
 *
 * =====================================================================================
 * EDGE CASES:
 * =====================================================================================
 *   1. Empty array → no-op
 *   2. Single character → no swap needed
 *
 * =====================================================================================
 * OPTIMIZATION NOTE: Code is optimal. This is the standard in-place O(1) solution.
 * =====================================================================================
 */
public class ReverseString {
    public static void main(String[] args) {
        char[] s = {'h','e','l','l','o'};
        ReverseString rs = new ReverseString();
        rs.reverseString(s);
        System.out.println(s);
    }

    public void reverseString(char[] s) {
        int left = 0;
        int right = s.length - 1;
        
        if (right <= 0) {
            return;
        }
        
        while (left < right) {
            swap(left, right, s);
            left++;
            right--;
        }
    }
    
    private void swap(int left, int right, char[] s) {
        char temp = s[left];
        s[left] = s[right];
        s[right] = temp;
    }
}
