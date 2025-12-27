package dsapatterns.twopointer;

import java.util.Arrays;

/**
 * =====================================================================================
 * MERGE SORTED ARRAY - Two Pointer Approach (Reverse Direction)
 * =====================================================================================
 * LeetCode: https://leetcode.com/problems/merge-sorted-array/
 *
 * =====================================================================================
 * INTUITION:
 * =====================================================================================
 * Merge from the END to avoid overwriting. nums1 has extra space at the end.
 * Compare largest elements from both arrays, place at the back of nums1.
 *
 * =====================================================================================
 * EXAMPLE: nums1=[1,2,3,0,0,0], m=3, nums2=[2,5,6], n=3 → [1,2,2,3,5,6]
 * =====================================================================================
 *
 *   Step | m | n | lastIdx | Compare        | Action
 *   -----|---|---|---------|----------------|---------------------------
 *     1  | 3 | 3 |    5    | 3 vs 6         | nums1[5]=6, n--
 *     2  | 3 | 2 |    4    | 3 vs 5         | nums1[4]=5, n--
 *     3  | 3 | 1 |    3    | 3 vs 2         | nums1[3]=3, m--
 *     4  | 2 | 1 |    2    | 2 vs 2         | nums1[2]=2, m--
 *     5  | 1 | 1 |    1    | 1 vs 2         | nums1[1]=2, n--
 *     6  | 1 | 0 |    0    | n=0, STOP
 *
 *   Remaining nums2 elements copied (none left in this case)
 *   Result: [1,2,2,3,5,6]
 *
 * =====================================================================================
 * COMPLEXITY:
 * =====================================================================================
 *   Time:  O(m+n) - Single pass through both arrays
 *   Space: O(1) - In-place merge
 *
 * =====================================================================================
 * EDGE CASES:
 * =====================================================================================
 *   1. nums2 is empty (n=0) → no-op
 *   2. nums1 is empty (m=0) → copy all of nums2
 *   3. All nums2 elements smaller → copy all at beginning
 *
 * =====================================================================================
 * OPTIMIZATION NOTE: Code is optimal. Merging from end is key insight.
 * The second while loop handles leftover nums2 elements.
 * =====================================================================================
 */
public class mergeSortedArr {
    public static void main(String[] args) {
        int[] nums1 = {0};
        int[] nums2 = {1};
        int m = 0, n = 1;
        mergeSortedArr obj = new mergeSortedArr();
        obj.merge(nums1, m, nums2, n);
        Arrays.stream(nums1).forEach(num -> System.out.print(num + " "));
    }

    public void merge(int[] nums1, int m, int[] nums2, int n) {
        if (n == 0) {
            return;
        }
        
        int lastIndex = m + n - 1;
        
        while (m > 0 && n > 0) {
            if (nums1[m - 1] >= nums2[n - 1]) {
                nums1[lastIndex] = nums1[m - 1];
                m--;
            } else {
                nums1[lastIndex] = nums2[n - 1];
                n--;
            }
            lastIndex--;
        }

        // Copy remaining elements from nums2 (if any)
        while (n > 0) {
            nums1[lastIndex] = nums2[n - 1];
            n--;
            lastIndex--;
        }
    }
}
