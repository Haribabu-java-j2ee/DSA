package dsapatterns.twopointer;

/**
 * =====================================================================================
 * GET THE MAXIMUM SCORE - Two Pointer Approach
 * =====================================================================================
 * LeetCode: https://leetcode.com/problems/get-the-maximum-score/
 *
 * =====================================================================================
 * INTUITION:
 * =====================================================================================
 * Two sorted arrays can be traversed like merging. Track cumulative sums for each path.
 * At common elements (intersection points), switch to path with higher sum.
 * Use two pointers to process elements in ascending order.
 *
 * =====================================================================================
 * EXAMPLE: nums1=[2,4,5,8,10], nums2=[4,6,8,9] → 30
 * =====================================================================================
 *
 *   p1 | p2 | sum1 | sum2 | Action
 *   ---|----|----- |------|------------------------------------------
 *    0 |  0 |   0  |   0  | 2 < 4: sum1 += 2 = 2
 *    1 |  0 |   2  |   0  | 4 = 4: merge! max(2,0)+4 = 6, both paths = 6
 *    2 |  1 |   6  |   6  | 5 < 6: sum1 += 5 = 11
 *    3 |  1 |  11  |   6  | 6 < 8: sum2 += 6 = 12
 *    3 |  2 |  11  |  12  | 8 = 8: merge! max(11,12)+8 = 20
 *    4 |  3 |  20  |  20  | 9 < 10: sum2 += 9 = 29
 *    4 |  4 |  20  |  29  | p2 done, sum1 += 10 = 30
 *    5 |  4 |  30  |  29  | Both done, max(30,29) = 30
 *
 *   Best path: 2 → 4 → 6 → 8 → 10 = 30
 *
 * =====================================================================================
 * COMPLEXITY:
 * =====================================================================================
 *   Time:  O(m + n) - Single pass through both arrays
 *   Space: O(1) - Only tracking two sums and pointers
 *
 * =====================================================================================
 * EDGE CASES:
 * =====================================================================================
 *   1. No common elements → take max of both complete paths
 *   2. All elements common → paths are identical
 *   3. One array empty → return sum of other array
 *   4. Result overflow → use modulo 10^9+7
 *
 * =====================================================================================
 * OPTIMIZATION NOTE: Code is optimal. Linear time with constant space.
 * Note: The modulo is applied only at the end which may cause overflow for large sums.
 * For stricter requirements, apply modulo during accumulation.
 * =====================================================================================
 */
public class MaximumScore {
    public static void main(String[] args) {
        int[] nums1 = new int[]{2, 4, 5, 8, 10};
        int[] nums2 = new int[]{4, 6, 8, 9};
        MaximumScore obj = new MaximumScore();
        int maxScore = obj.maxSum(nums1, nums2);
        System.out.println("Maximum Score: " + maxScore);
    }
    
    public int maxSum(int[] nums1, int[] nums2) {
        int MOD = 1000_000_007;
        int len1 = nums1.length;
        int len2 = nums2.length;
        int pointer1 = 0, pointer2 = 0;
        long sumPath1 = 0, sumPath2 = 0;  // Use long to avoid overflow
        
        while (pointer1 < len1 || pointer2 < len2) {
            if (pointer1 < len1 && (pointer2 == len2 || nums1[pointer1] < nums2[pointer2])) {
                // nums1 element is smaller, add to path1
                sumPath1 += nums1[pointer1++];
            } else if (pointer2 < len2 && (pointer1 == len1 || nums1[pointer1] > nums2[pointer2])) {
                // nums2 element is smaller, add to path2
                sumPath2 += nums2[pointer2++];
            } else {
                // Common element found - merge paths and add common element
                sumPath1 = sumPath2 = Math.max(sumPath1, sumPath2) + nums1[pointer1];
                pointer1++;
                pointer2++;
            }
        }
        
        return (int) (Math.max(sumPath1, sumPath2) % MOD);
    }
}
