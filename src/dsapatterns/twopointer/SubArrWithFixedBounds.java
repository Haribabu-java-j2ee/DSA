package dsapatterns.twopointer;

/**
 * =====================================================================================
 * COUNT SUBARRAYS WITH FIXED BOUNDS - Sliding Window / Two Pointer Approach
 * =====================================================================================
 * LeetCode: https://leetcode.com/problems/count-subarrays-with-fixed-bounds/
 *
 * =====================================================================================
 * INTUITION:
 * =====================================================================================
 * Track three positions: last minK, last maxK, last invalid (outside [minK,maxK]).
 * Valid subarrays end at current index and start after lastInvalid, containing both
 * minK and maxK. Count = min(lastMin, lastMax) - lastInvalid (if positive).
 *
 * =====================================================================================
 * EXAMPLE: nums=[1,3,5,2,7,5], minK=1, maxK=5 → 2
 * =====================================================================================
 *
 *   idx | nums[i] | lastMin | lastMax | lastInvalid | validCount | Total
 *   ----|---------|---------|---------|-------------|------------|------
 *    0  |    1    |    0    |   -1    |     -1      | min(0,-1)-(-1)=0 | 0
 *    1  |    3    |    0    |   -1    |     -1      | 0          | 0
 *    2  |    5    |    0    |    2    |     -1      | min(0,2)-(-1)=1 | 1
 *    3  |    2    |    0    |    2    |     -1      | 1          | 2
 *    4  |    7    |    0    |    2    |      4      | 0 (invalid)| 2
 *    5  |    5    |    0    |    5    |      4      | 0 (need new min) | 2
 *
 *   Result: 2 valid subarrays: [1,3,5] and [1,3,5,2]
 *
 * =====================================================================================
 * COMPLEXITY:
 * =====================================================================================
 *   Time:  O(n) - Single pass through array
 *   Space: O(1) - Only tracking three positions
 *
 * =====================================================================================
 * EDGE CASES:
 * =====================================================================================
 *   1. minK = maxK → need exact value in subarray
 *   2. No valid subarrays → return 0
 *   3. All elements outside range → return 0
 *   4. Single element = minK = maxK → return 1
 *
 * =====================================================================================
 * OPTIMIZATION NOTE: Code is optimal. This is the standard O(n) solution using
 * position tracking instead of nested loops.
 * =====================================================================================
 */
public class SubArrWithFixedBounds {
    public static void main(String[] args) {
        int[] nums = new int[]{1,3,5,2,7,5};
        int minK = 1;
        int maxK = 5;
        SubArrWithFixedBounds obj = new SubArrWithFixedBounds();
        long result = obj.countSubarrays(nums, minK, maxK);
        System.out.println("Number of subarrays with fixed bounds: " + result);
    }
    
    public long countSubarrays(int[] nums, int minK, int maxK) {
        long totalCount = 0;

        // Track the most recent positions of important elements
        int lastMinPosition = -1;     // Last position where we found minK
        int lastMaxPosition = -1;     // Last position where we found maxK
        int lastInvalidPosition = -1; // Last position where element outside [minK, maxK]

        for (int currentIndex = 0; currentIndex < nums.length; currentIndex++) {
            // Check if current element is outside the valid range
            if (nums[currentIndex] < minK || nums[currentIndex] > maxK) {
                lastInvalidPosition = currentIndex;
            }

            // Update position if we found minK
            if (nums[currentIndex] == minK) {
                lastMinPosition = currentIndex;
            }

            // Update position if we found maxK
            if (nums[currentIndex] == maxK) {
                lastMaxPosition = currentIndex;
            }

            // Calculate valid subarrays ending at current position
            // Need both minK and maxK to appear after last invalid element
            int leftmostValidStart = Math.min(lastMinPosition, lastMaxPosition);
            int validSubarraysCount = Math.max(0, leftmostValidStart - lastInvalidPosition);

            totalCount += validSubarraysCount;
        }

        return totalCount;
    }
}
