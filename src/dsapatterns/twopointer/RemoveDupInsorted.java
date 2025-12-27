package dsapatterns.twopointer;

import java.util.Arrays;

/**
 * =====================================================================================
 * REMOVE DUPLICATES FROM SORTED ARRAY - Two Pointer Approach
 * =====================================================================================
 * LeetCode: https://leetcode.com/problems/remove-duplicates-from-sorted-array/
 *
 * =====================================================================================
 * INTUITION:
 * =====================================================================================
 * Use slow pointer (j) to track position of last unique element.
 * Fast pointer (i) scans ahead. When new unique element found, place it at j+1.
 *
 * =====================================================================================
 * EXAMPLE: [1,2,2,2,2,3,4,5] → k=5, nums=[1,2,3,4,5,...]
 * =====================================================================================
 *
 *   Step | i | j | nums[j] | nums[i+1] | Action
 *   -----|---|---|---------|-----------|----------------------------
 *     1  | 0 | 0 |    1    |     2     | 1≠2, j++, nums[1]=2
 *     2  | 1 | 1 |    2    |     2     | 2=2, skip
 *     3  | 2 | 1 |    2    |     2     | 2=2, skip
 *     4  | 3 | 1 |    2    |     2     | 2=2, skip
 *     5  | 4 | 1 |    2    |     3     | 2≠3, j++, nums[2]=3
 *     6  | 5 | 2 |    3    |     4     | 3≠4, j++, nums[3]=4
 *     7  | 6 | 3 |    4    |     5     | 4≠5, j++, nums[4]=5
 *
 *   Result: j+1 = 5 unique elements
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
 *   1. Empty array → return 0
 *   2. Single element → return 1
 *   3. All same elements → return 1
 *   4. All unique → return n
 *
 * =====================================================================================
 * OPTIMIZATION NOTE: Code is optimal. Classic two-pointer for in-place deduplication.
 * =====================================================================================
 */
public class RemoveDupInsorted {
    public static void main(String[] args) {
        int[] nums = new int[]{1,2,2,2,2,3,4,5};
        RemoveDupInsorted obj = new RemoveDupInsorted();
        int k = obj.removeDuplicates(nums);
        System.out.println("Length after removing duplicates: " + k);
        System.out.println();
        Arrays.stream(nums).forEach(n -> System.out.print(n + " "));
    }

    public int removeDuplicates(int[] nums) {
        int n = nums.length;
        
        if (n == 0) {
            return 0;
        } else if (n == 1) {
            return 1;
        }
        
        int j = 0;
        
        for (int i = 0; i < n - 1; i++) {
            if (nums[j] != nums[i + 1]) {
                nums[++j] = nums[i + 1];
            }
        }

        return j + 1;
    }
}
