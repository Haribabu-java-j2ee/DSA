package dsapatterns.twopointer;

/**
 * =====================================================================================
 * REMOVE ELEMENT - Two Pointer Approach
 * =====================================================================================
 * LeetCode: https://leetcode.com/problems/remove-element/
 *
 * =====================================================================================
 * INTUITION:
 * =====================================================================================
 * Swap target elements to the end. Left pointer finds targets, right pointer
 * provides non-target elements to swap. Count = final left position.
 *
 * =====================================================================================
 * EXAMPLE: nums=[2,3,3,2], val=3 → k=2, nums=[2,2,...]
 * =====================================================================================
 *
 *   Step | left | right | nums[left] | Action           | Array
 *   -----|------|-------|------------|------------------|----------------
 *     1  |  0   |   3   |     2      | 2≠3, left++      | [2,3,3,2]
 *     2  |  1   |   3   |     3      | 3=3, swap, r--   | [2,2,3,3]
 *     3  |  1   |   2   |     2      | 2≠3, left++      | [2,2,3,3]
 *     4  |  2   |   2   |     3      | 3=3, swap, r--   | [2,2,3,3]
 *     5  |  2   |   1   | left>right | STOP
 *
 *   Result: k=2 (elements before position 2 are non-val)
 *
 * =====================================================================================
 * COMPLEXITY:
 * =====================================================================================
 *   Time:  O(n) - Single pass
 *   Space: O(1) - In-place removal
 *
 * =====================================================================================
 * EDGE CASES:
 * =====================================================================================
 *   1. Empty array → return 0
 *   2. All elements equal to val → return 0
 *   3. No elements equal to val → return n
 *   4. Single element → return 0 or 1
 *
 * =====================================================================================
 * OPTIMIZATION NOTE: Code is optimal. Note: This approach doesn't preserve order.
 * If order preservation is needed, use slow-fast pointer approach instead.
 * =====================================================================================
 */
public class RemoveTargetElement {
    public static void main(String[] args) {
        int[] nums = {2,3,3,2};
        int val = 3;
        RemoveTargetElement obj = new RemoveTargetElement();
        int length = obj.removeElement(nums, val);
        System.out.println("Length: " + length);
        for (int i = 0; i < length; i++) {
            System.out.print(nums[i] + " ");
        }
    }
    
    public int removeElement(int[] nums, int val) {
        int left = 0;
        int right = nums.length - 1;

        while (left <= right) {
            if (nums[left] == val) {
                swap(left, right, nums);
                right--;
            } else {
                left++;
            }
        }
        
        return left;
    }

    private void swap(int left, int right, int[] nums) {
        int temp = nums[left];
        nums[left] = nums[right];
        nums[right] = temp;
    }
}
