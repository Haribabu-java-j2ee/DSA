package dsapatterns.slidingwindow;

import java.util.Arrays;

/**
 * =====================================================================================
 * FREQUENCY OF THE MOST FREQUENT ELEMENT - Sliding Window + Sorting
 * =====================================================================================
 * LeetCode: https://leetcode.com/problems/frequency-of-the-most-frequent-element/
 *
 * =====================================================================================
 * PROBLEM:
 * =====================================================================================
 * Given an array of integers nums and an integer k, you can increment any element
 * by 1 in one operation. You can perform at most k operations total.
 * Return the maximum possible frequency of any element after performing at most k operations.
 *
 * =====================================================================================
 * INTUITION:
 * =====================================================================================
 * 1. Sort the array - elements closer in value need fewer operations to become equal
 * 2. Use sliding window where ALL elements will be made equal to the rightmost (target)
 * 3. The cost to make all window elements equal to target:
 *        cost = (windowSize × target) - windowSum
 * 4. If cost > k, shrink window from left (remove smallest elements first)
 * 5. Track maximum valid window size
 *
 * =====================================================================================
 * WHY SORTING WORKS:
 * =====================================================================================
 * After sorting: [1, 2, 4, 6]
 *
 *   - We can only INCREMENT (not decrement), so we make smaller elements equal to larger ones
 *   - The rightmost element in any window is the TARGET (all others increment to match it)
 *   - Adjacent elements in sorted array are closest in value → minimum operations needed
 *
 *   Example: To make [1,2,4] all equal to 4:
 *     Cost = (4-1) + (4-2) + (4-4) = 3 + 2 + 0 = 5
 *     Formula: (3 × 4) - (1+2+4) = 12 - 7 = 5 ✓
 *
 * =====================================================================================
 * KEY FORMULA:
 * =====================================================================================
 *
 *   cost = (windowSize × target) - windowSum
 *
 *   Where:
 *     - windowSize = right - left + 1
 *     - target = nums[right] (rightmost element after sorting)
 *     - windowSum = sum of all elements in window
 *
 *   This works because:
 *     - If all elements were equal to target: idealSum = windowSize × target
 *     - Actual sum = windowSum
 *     - Operations needed = idealSum - windowSum
 *
 * =====================================================================================
 * EXAMPLE: nums = [1, 2, 4], k = 5 → returns 3
 * =====================================================================================
 *
 *   After sorting: [1, 2, 4]
 *
 *   right | nums[r] | windowSum | window    | target | cost formula              | cost | valid? | maxFreq
 *   ------|---------|-----------|-----------|--------|---------------------------|------|--------|--------
 *     0   |    1    |     1     | [1]       |   1    | (1×1) - 1 = 0             |  0   |  YES   |   1
 *     1   |    2    |     3     | [1,2]     |   2    | (2×2) - 3 = 1             |  1   |  YES   |   2
 *     2   |    4    |     7     | [1,2,4]   |   4    | (3×4) - 7 = 5             |  5   |  YES   |   3
 *
 *   Result: 3 (all elements can be made equal to 4 with 5 operations)
 *
 *   Verification: [1,2,4] → [4,4,4]
 *     - 1 → 4: 3 operations
 *     - 2 → 4: 2 operations
 *     - 4 → 4: 0 operations
 *     - Total: 5 operations ≤ k=5 ✓
 *
 * =====================================================================================
 * EXAMPLE 2: nums = [1, 4, 8, 13], k = 5 → returns 2
 * =====================================================================================
 *
 *   After sorting: [1, 4, 8, 13]
 *
 *   right | nums[r] | windowSum | window      | target | cost              | valid? | action       | maxFreq
 *   ------|---------|-----------|-------------|--------|-------------------|--------|--------------|--------
 *     0   |    1    |     1     | [1]         |   1    | (1×1)-1=0         |  YES   |     -        |   1
 *     1   |    4    |     5     | [1,4]       |   4    | (2×4)-5=3         |  YES   |     -        |   2
 *     2   |    8    |    13     | [1,4,8]     |   8    | (3×8)-13=11       |   NO   | shrink       |   2
 *         |         |    12     | [4,8]       |   8    | (2×8)-12=4        |  YES   |     -        |   2
 *     3   |   13    |    25     | [4,8,13]    |  13    | (3×13)-25=14      |   NO   | shrink       |   2
 *         |         |    21     | [8,13]      |  13    | (2×13)-21=5       |  YES   |     -        |   2
 *
 *   Result: 2 (best we can do is make 2 elements equal)
 *
 * =====================================================================================
 * VISUAL REPRESENTATION:
 * =====================================================================================
 *
 *   Sorted array: [1, 4, 8, 13]
 *
 *   Window [1,4,8] with target=8:
 *   ┌───────────────────────────────────────────────┐
 *   │  8 ─────────────────────────┬─────────┬───    │  ← Target level
 *   │  7                          │         │       │
 *   │  6                          │         │       │
 *   │  5                          │         │       │
 *   │  4 ─────────────────┬───    │    +4   │       │  ← 4 needs +4 to reach 8
 *   │  3                  │       │         │       │
 *   │  2                  │       │         │       │
 *   │  1 ─────┬───        │  +7   │         │       │  ← 1 needs +7 to reach 8
 *   │  0      │           │       │         │       │
 *   └─────────┴───────────┴───────┴─────────┴───────┘
 *            [1]         [4]      [8]
 *
 *   Total cost = 7 + 4 + 0 = 11 > k=5 → INVALID, shrink window
 *
 *   Window [4,8] with target=8:
 *   ┌───────────────────────────────────────────────┐
 *   │  8 ─────────────────┬───────┬───              │  ← Target level
 *   │  7                  │       │                 │
 *   │  6                  │       │                 │
 *   │  5                  │       │                 │
 *   │  4 ─────┬───        │  +4   │                 │  ← 4 needs +4 to reach 8
 *   │  3      │           │       │                 │
 *   │  2      │           │       │                 │
 *   │  1      │           │       │                 │
 *   │  0      │           │       │                 │
 *   └─────────┴───────────┴───────┴─────────────────┘
 *            [4]         [8]
 *
 *   Total cost = 4 + 0 = 4 ≤ k=5 → VALID, window size = 2
 *
 * =====================================================================================
 * WHY USE LONG FOR windowSum:
 * =====================================================================================
 *
 *   nums[i] can be up to 10^5, array length up to 10^5
 *   Max possible sum = 10^5 × 10^5 = 10^10 > Integer.MAX_VALUE (≈2×10^9)
 *   So we use long to prevent overflow!
 *
 * =====================================================================================
 * COMPLEXITY:
 * =====================================================================================
 *   Time:  O(n log n) - Sorting dominates; sliding window is O(n)
 *   Space: O(1) - Only pointers and sum variable (ignoring sort's internal space)
 *
 * =====================================================================================
 * EDGE CASES:
 * =====================================================================================
 *   1. Single element → return 1 (frequency is always at least 1)
 *   2. All elements same → return n (all already equal)
 *   3. k = 0 → return max frequency of any element in original array
 *   4. k is very large → can make all elements equal to max, return n
 */
public class FrequencyOfMostFrequentElement {
    public static void main(String[] args) {
        FrequencyOfMostFrequentElement obj = new FrequencyOfMostFrequentElement();
        
        // Test case 1: All elements can be made equal
        int[] nums1 = {1, 2, 4};
        int k1 = 5;
        System.out.println("Test 1: " + obj.maxFrequency(nums1, k1));  // Expected: 3
        
        // Test case 2: Only 2 elements can be made equal
        int[] nums2 = {1, 4, 8, 13};
        int k2 = 5;
        System.out.println("Test 2: " + obj.maxFrequency(nums2, k2));  // Expected: 2
        
        // Test case 3: Large gap between elements
        int[] nums3 = {3, 9, 6};
        int k3 = 2;
        System.out.println("Test 3: " + obj.maxFrequency(nums3, k3));  // Expected: 1
        
        // Test case 4: Already equal elements
        int[] nums4 = {5, 5, 5, 5};
        int k4 = 0;
        System.out.println("Test 4: " + obj.maxFrequency(nums4, k4));  // Expected: 4
    }
    
    /**
     * Finds the maximum possible frequency of any element after at most k increments.
     *
     * Algorithm:
     * 1. Sort array (smaller elements can be incremented to match larger ones)
     * 2. Slide window: try to make all elements equal to rightmost (target)
     * 3. If cost > k, shrink window from left
     * 4. Track maximum valid window size
     *
     * @param nums array of integers
     * @param k maximum number of increment operations allowed
     * @return maximum possible frequency of any element
     */
    public int maxFrequency(int[] nums, int k) {
        // =========================================================================
        // STEP 1: Sort array - adjacent elements need fewer operations to equalize
        // =========================================================================
        Arrays.sort(nums);
        
        int left = 0;       // Left pointer of sliding window
        int maxFreq = 0;    // Maximum frequency found
        long windowSum = 0; // Sum of elements in current window (use long to prevent overflow)

        // =========================================================================
        // STEP 2: Expand window with right pointer
        // =========================================================================
        for (int right = 0; right < nums.length; right++) {
            
            // Target = rightmost element (all others will be incremented to this value)
            long target = nums[right];
            
            // Add new element to window sum
            windowSum += target;

            // =================================================================
            // STEP 3: Shrink window if cost exceeds k
            // =================================================================
            // Cost formula: (windowSize × target) - windowSum
            // If cost > k, window is invalid → shrink from left
            while ((right - left + 1) * target > windowSum + k) {
                windowSum -= nums[left];  // Remove leftmost element from sum
                left++;                    // Shrink window
            }

            // =================================================================
            // STEP 4: Update maximum frequency (valid window size)
            // =================================================================
            maxFreq = Math.max(maxFreq, right - left + 1);
        }

        return maxFreq;
    }
}
