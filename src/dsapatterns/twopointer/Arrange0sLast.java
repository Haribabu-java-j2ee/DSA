package dsapatterns.twopointer;

import java.util.Arrays;

/**
 * =====================================================================================
 * MOVE ZEROES - Two Pointer Approach
 * =====================================================================================
 * LeetCode: https://leetcode.com/problems/move-zeroes/
 *
 * =====================================================================================
 * INTUITION:
 * =====================================================================================
 * Use slow pointer (i) for position of next non-zero. Fast pointer (j/k) scans ahead.
 * When non-zero found, swap with slow pointer position. This preserves relative order.
 * KEY: Don't use right pointer swap (would disrupt order).
 *
 * =====================================================================================
 * EXAMPLE: [0,1,0,3,12] → [1,3,12,0,0]
 * =====================================================================================
 *
 *   Step | k | i | j | nums[k] | Action         | Array
 *   -----|---|---|---|---------|----------------|----------------
 *     1  | 0 | 0 | 0 |    0    | 0=0, j++       | [0,1,0,3,12]
 *     2  | 1 | 0 | 1 |    1    | 1≠0, swap(0,1) | [1,0,0,3,12]
 *     3  | 2 | 1 | 2 |    0    | 0=0, j++       | [1,0,0,3,12]
 *     4  | 3 | 1 | 3 |    3    | 3≠0, swap(1,3) | [1,3,0,0,12]
 *     5  | 4 | 2 | 4 |   12    | 12≠0, swap(2,4)| [1,3,12,0,0]
 *
 *   Result: [1,3,12,0,0]
 *
 * =====================================================================================
 * COMPLEXITY:
 * =====================================================================================
 *   Time:  O(n) - Single pass through array
 *   Space: O(1) - In-place modification
 *
 * =====================================================================================
 * EDGE CASES:
 * =====================================================================================
 *   1. Empty array → no-op
 *   2. No zeros → no swaps needed
 *   3. All zeros → no swaps needed (all stay in place)
 *   4. Single element → no-op
 *
 * =====================================================================================
 * OPTIMIZATION NOTE: Code is correct but could be simplified. Alternative approach:
 *   int insertPos = 0;
 *   for (int num : nums) if (num != 0) nums[insertPos++] = num;
 *   while (insertPos < n) nums[insertPos++] = 0;
 * This avoids unnecessary swaps when elements are already in position.
 * =====================================================================================
 */
public class Arrange0sLast {
    public static void main(String[] args) {
        int[] nums = {0,1,0,3,12};
        Arrange0sLast obj = new Arrange0sLast();
        obj.moveZeroes(nums);
        Arrays.stream(nums).forEach(i -> System.out.print(i + " "));
    }

    public void moveZeroes(int[] nums) {
        int n = nums.length;
        
        if (n == 0)
            return;
        
        int i = 0, j = 0, k = 0;
        
        while (k < n) {
            if (nums[k] == 0) {
                j++;
            } else {
                swap(i, j, nums);
                i++;
                j++;
            }
            k++;
        }
    }

    private void swap(int i, int j, int[] nums) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}
