package dsapatterns.slidingwindow;

/**
 * =====================================================================================
 * SUBARRAYS WITH K DIFFERENT INTEGERS - Sliding Window with "At Most K" Trick
 * =====================================================================================
 * LeetCode: https://leetcode.com/problems/subarrays-with-k-different-integers/
 * Difficulty: Hard
 *
 * =====================================================================================
 * PROBLEM:
 * =====================================================================================
 * Given an integer array nums and an integer k, return the number of GOOD subarrays.
 * A good subarray is a subarray where the number of DISTINCT integers is EXACTLY k.
 *
 * Example: nums = [1,2,1,2,3], k = 2
 * Good subarrays: [1,2], [2,1], [1,2], [2,3], [1,2,1], [2,1,2], [1,2,1,2]
 * Answer: 7
 *
 * =====================================================================================
 * KEY INSIGHT: "Exactly K" = "At Most K" - "At Most K-1"
 * =====================================================================================
 *
 * Counting subarrays with EXACTLY K distinct integers is hard because:
 * - When we add an element, distinct count can increase
 * - When we remove an element, distinct count can decrease
 * - Maintaining EXACTLY K while sliding is tricky
 *
 * TRICK: Transform the problem!
 * ─────────────────────────────────────────────────────────────────────────────────
 * 
 * exactly(K) = atMost(K) - atMost(K-1)
 *
 * Why this works:
 * - atMost(K) counts subarrays with 1, 2, 3, ..., K distinct integers
 * - atMost(K-1) counts subarrays with 1, 2, 3, ..., K-1 distinct integers
 * - Subtracting removes all subarrays with < K distinct integers
 * - Only subarrays with EXACTLY K remain!
 *
 * Visual:
 * ┌─────────────────────────────────────────────────────────────────────────────┐
 * │  atMost(K):     [1] [2] ... [K-1] [K]                                       │
 * │  atMost(K-1):   [1] [2] ... [K-1]                                           │
 * │  ───────────────────────────────────────                                    │
 * │  Difference:                      [K]    ← Only exactly K distinct!         │
 * └─────────────────────────────────────────────────────────────────────────────┘
 *
 * =====================================================================================
 * ALGORITHM: Counting Subarrays with At Most K Distinct
 * =====================================================================================
 *
 * For each right position, count how many valid subarrays END at right:
 *
 * 1. Expand window by adding nums[right]
 * 2. If distinct count > k, shrink from left until distinct count ≤ k
 * 3. All subarrays from left to right (inclusive) are valid!
 *    Count = right - left + 1
 *
 * Why (right - left + 1)?
 * ─────────────────────────────────────────────────────────────────────────────────
 * Window: [left ... right]
 * Valid subarrays ending at right:
 *   - [right]
 *   - [right-1, right]
 *   - [right-2, right-1, right]
 *   - ...
 *   - [left, left+1, ..., right]
 *
 * Total = right - left + 1 subarrays
 *
 * =====================================================================================
 * EXAMPLE: nums = [1, 2, 1, 2, 3], k = 2
 * =====================================================================================
 *
 * STEP 1: Calculate atMost(2)
 * ─────────────────────────────────────────────────────────────────────────────────
 *
 * right│nums[r]│ window │distinct│ k  │ action      │subarrays ending at r│ count
 * ─────┼───────┼────────┼────────┼────┼─────────────┼─────────────────────┼──────
 *   0  │   1   │ [1]    │   1    │ 2→1│ add 1       │ [1]                 │  1
 *   1  │   2   │ [1,2]  │   2    │ 1→0│ add 2       │ [2],[1,2]           │  2
 *   2  │   1   │ [1,2,1]│   2    │  0 │ 1 exists    │ [1],[2,1],[1,2,1]   │  3
 *   3  │   2   │[1,2,1,2]│  2    │  0 │ 2 exists    │ [2],[1,2],[2,1,2],  │  4
 *      │       │        │        │    │             │ [1,2,1,2]           │
 *   4  │   3   │[1,2,1,2,3]│ 3   │0→-1│ k<0! shrink │                     │
 *      │       │[2,1,2,3]│  3    │ -1 │ still k<0   │                     │
 *      │       │[1,2,3] │  3    │ -1 │ still k<0   │                     │
 *      │       │[2,3]   │  2    │-1→0│ k=0 now     │ [3],[2,3]           │  2
 * ─────┴───────┴────────┴────────┴────┴─────────────┴─────────────────────┴──────
 *                                                           Total atMost(2) = 12
 *
 * STEP 2: Calculate atMost(1)
 * ─────────────────────────────────────────────────────────────────────────────────
 *
 * right│nums[r]│ window │distinct│ k  │ action      │subarrays ending at r│ count
 * ─────┼───────┼────────┼────────┼────┼─────────────┼─────────────────────┼──────
 *   0  │   1   │ [1]    │   1    │ 1→0│ add 1       │ [1]                 │  1
 *   1  │   2   │ [1,2]  │   2    │0→-1│ k<0! shrink │                     │
 *      │       │ [2]    │   1    │-1→0│ k=0 now     │ [2]                 │  1
 *   2  │   1   │ [2,1]  │   2    │0→-1│ k<0! shrink │                     │
 *      │       │ [1]    │   1    │-1→0│ k=0 now     │ [1]                 │  1
 *   3  │   2   │ [1,2]  │   2    │0→-1│ k<0! shrink │                     │
 *      │       │ [2]    │   1    │-1→0│ k=0 now     │ [2]                 │  1
 *   4  │   3   │ [2,3]  │   2    │0→-1│ k<0! shrink │                     │
 *      │       │ [3]    │   1    │-1→0│ k=0 now     │ [3]                 │  1
 * ─────┴───────┴────────┴────────┴────┴─────────────┴─────────────────────┴──────
 *                                                           Total atMost(1) = 5
 *
 * STEP 3: Calculate exactly(2)
 * ─────────────────────────────────────────────────────────────────────────────────
 * exactly(2) = atMost(2) - atMost(1) = 12 - 5 = 7 ✓
 *
 * =====================================================================================
 * WHY count += right - left + 1?
 * =====================================================================================
 *
 * At each step, we count subarrays ENDING at current right position:
 *
 * Example: window = [1, 2, 1] (indices 0, 1, 2)
 * ┌─────────────────────────────────────────────────────────────────────────────┐
 * │  left=0              right=2                                                │
 * │    ↓                   ↓                                                    │
 * │  [ 1 , 2 , 1 ]                                                              │
 * │                                                                             │
 * │  Subarrays ending at right=2:                                               │
 * │    [1]       → starts at index 2                                            │
 * │    [2,1]     → starts at index 1                                            │
 * │    [1,2,1]   → starts at index 0 (left)                                     │
 * │                                                                             │
 * │  Count = 3 = right - left + 1 = 2 - 0 + 1 ✓                                 │
 * └─────────────────────────────────────────────────────────────────────────────┘
 *
 * =====================================================================================
 * VISUAL: THE "AT MOST K" TRICK
 * =====================================================================================
 *
 * nums = [1, 2, 1, 2, 3], k = 2
 *
 * All subarrays with ≤ 2 distinct (atMost(2) = 12):
 * ┌─────────────────────────────────────────────────────────────────────────────┐
 * │ 1 distinct: [1], [2], [1], [2], [3]                            = 5          │
 * │ 2 distinct: [1,2], [2,1], [1,2], [2,3], [1,2,1], [2,1,2],      = 7          │
 * │             [1,2,1,2]                                                       │
 * │                                                                Total = 12   │
 * └─────────────────────────────────────────────────────────────────────────────┘
 *
 * All subarrays with ≤ 1 distinct (atMost(1) = 5):
 * ┌─────────────────────────────────────────────────────────────────────────────┐
 * │ 1 distinct: [1], [2], [1], [2], [3]                            = 5          │
 * │                                                                Total = 5    │
 * └─────────────────────────────────────────────────────────────────────────────┘
 *
 * Exactly 2 distinct = 12 - 5 = 7 ✓
 *
 * =====================================================================================
 * COMPLEXITY:
 * =====================================================================================
 *
 *   ─────────────────────────────────────────────────────────────────────────────────
 *   TIME COMPLEXITY: O(n)
 *   ─────────────────────────────────────────────────────────────────────────────────
 *
 *   ┌─────────────────────────────────────────────────────────────────────────────┐
 *   │ Operation              │ Times Called       │ Total                         │
 *   ├─────────────────────────────────────────────────────────────────────────────┤
 *   │ atMostK() called       │ 2 times            │ O(2n) = O(n)                  │
 *   │ Each element visited   │ At most 2× per call│ Once by right, once by left  │
 *   │ Frequency updates      │ O(1) each          │ O(n) total                    │
 *   └─────────────────────────────────────────────────────────────────────────────┘
 *
 *   ─────────────────────────────────────────────────────────────────────────────────
 *   SPACE COMPLEXITY: O(n)
 *   ─────────────────────────────────────────────────────────────────────────────────
 *
 *   ┌─────────────────────────────────────────────────────────────────────────────┐
 *   │ Data Structure         │ Size               │ Notes                         │
 *   ├─────────────────────────────────────────────────────────────────────────────┤
 *   │ frequency[] array      │ O(n+1)             │ Elements are 1 to n           │
 *   │ Pointers (left, right) │ O(1)               │ Constants                     │
 *   └─────────────────────────────────────────────────────────────────────────────┘
 *
 *   Note: If elements can be larger than n, use HashMap instead of array.
 *
 * =====================================================================================
 * EDGE CASES:
 * =====================================================================================
 *
 *   1. k = 0 → return 0 (no subarray can have 0 distinct integers)
 *   2. k > n → return 0 (impossible to have more distinct than array length)
 *   3. k = 1 → count subarrays with only one unique element
 *   4. All elements same → if k=1, return n*(n+1)/2; if k>1, return 0
 *   5. All elements distinct → if k=n, return 1; count based on k
 *   6. k < 0 → return 0 (invalid input)
 *
 * =====================================================================================
 * ALTERNATIVE: Using HashMap (for larger element values)
 * =====================================================================================
 *
 *   If nums[i] can be > n, replace frequency array with HashMap:
 *
 *   Map<Integer, Integer> freq = new HashMap<>();
 *   freq.merge(nums[right], 1, Integer::sum);
 *   if (freq.get(nums[right]) == 1) k--;  // New distinct element
 *
 *   freq.merge(nums[left], -1, Integer::sum);
 *   if (freq.get(nums[left]) == 0) {
 *       freq.remove(nums[left]);
 *       k++;  // Lost a distinct element
 *   }
 */
public class SubArraysWithKdiffInteger {
    
    public static void main(String[] args) {
        SubArraysWithKdiffInteger solution = new SubArraysWithKdiffInteger();
        
        System.out.println("╔══════════════════════════════════════════════════════════════════════════╗");
        System.out.println("║        SUBARRAYS WITH K DIFFERENT INTEGERS                               ║");
        System.out.println("╚══════════════════════════════════════════════════════════════════════════╝\n");
        
        // Test Case 1: Basic example
        int[] nums1 = {1, 2, 1, 2, 3};
        int k1 = 2;
        System.out.println("Test 1: nums = [1,2,1,2,3], k = 2");
        System.out.println("  atMost(2) = " + solution.atMostK(nums1, 2));
        System.out.println("  atMost(1) = " + solution.atMostK(nums1, 1));
        System.out.println("  exactly(2) = " + solution.subarraysWithKDistinct(nums1, k1));
        System.out.println("  Expected: 7\n");
        
        // Test Case 2: k = 1
        int[] nums2 = {1, 2, 1, 3, 4};
        int k2 = 1;
        System.out.println("Test 2: nums = [1,2,1,3,4], k = 1");
        System.out.println("  Result: " + solution.subarraysWithKDistinct(nums2, k2));
        System.out.println("  Expected: 5 (each single element)\n");
        
        // Test Case 3: All same elements
        int[] nums3 = {1, 1, 1, 1};
        int k3 = 1;
        System.out.println("Test 3: nums = [1,1,1,1], k = 1");
        System.out.println("  Result: " + solution.subarraysWithKDistinct(nums3, k3));
        System.out.println("  Expected: 10 (n*(n+1)/2 = 4*5/2 = 10)\n");
        
        // Test Case 4: All distinct elements
        int[] nums4 = {1, 2, 3, 4};
        int k4 = 4;
        System.out.println("Test 4: nums = [1,2,3,4], k = 4");
        System.out.println("  Result: " + solution.subarraysWithKDistinct(nums4, k4));
        System.out.println("  Expected: 1 (only the entire array)\n");
        
        // Test Case 5: k = 3
        int[] nums5 = {1, 2, 1, 2, 3};
        int k5 = 3;
        System.out.println("Test 5: nums = [1,2,1,2,3], k = 3");
        System.out.println("  Result: " + solution.subarraysWithKDistinct(nums5, k5));
        System.out.println("  Expected: 3 ([1,2,3], [2,1,2,3], [1,2,1,2,3])\n");
        
        // Test Case 6: k > number of distinct elements
        int[] nums6 = {1, 2, 1};
        int k6 = 5;
        System.out.println("Test 6: nums = [1,2,1], k = 5");
        System.out.println("  Result: " + solution.subarraysWithKDistinct(nums6, k6));
        System.out.println("  Expected: 0 (impossible)\n");
        
        System.out.println("╔══════════════════════════════════════════════════════════════════════════╗");
        System.out.println("║                        ALL TESTS COMPLETE                                ║");
        System.out.println("╚══════════════════════════════════════════════════════════════════════════╝");
    }
    
    /**
     * Counts subarrays with EXACTLY k distinct integers.
     *
     * Uses the transformation: exactly(K) = atMost(K) - atMost(K-1)
     *
     * @param nums input array of integers
     * @param k exact number of distinct integers required
     * @return count of subarrays with exactly k distinct integers
     */
    public int subarraysWithKDistinct(int[] nums, int k) {
        // =========================================================================
        // EDGE CASE: k cannot be negative or zero
        // =========================================================================
        if (k <= 0) {
            return 0;
        }
        
        // =========================================================================
        // KEY INSIGHT: exactly(K) = atMost(K) - atMost(K-1)
        // =========================================================================
        // atMost(K) counts all subarrays with 1, 2, ..., K distinct elements
        // atMost(K-1) counts all subarrays with 1, 2, ..., K-1 distinct elements
        // The difference gives us subarrays with EXACTLY K distinct elements
        return atMostK(nums, k) - atMostK(nums, k - 1);
    }

    /**
     * Counts subarrays with AT MOST k distinct integers.
     *
     * Algorithm:
     * 1. Use sliding window with two pointers (left, right)
     * 2. Expand right to add elements
     * 3. When distinct count > k, shrink from left
     * 4. At each step, count subarrays ending at right = (right - left + 1)
     *
     * @param nums input array of integers
     * @param k maximum number of distinct integers allowed
     * @return count of subarrays with at most k distinct integers
     */
    int atMostK(int[] nums, int k) {
        int left = 0;
        int right = 0;
        int count = 0;
        int n = nums.length;
        
        // =========================================================================
        // FREQUENCY ARRAY: Track count of each element in current window
        // =========================================================================
        // Size is n+1 because problem states: 1 <= nums[i] <= n
        // frequency[x] = count of element x in current window
        int[] frequency = new int[n + 1];
        
        // =========================================================================
        // MAIN SLIDING WINDOW LOOP
        // =========================================================================
        while (right < n) {
            
            // =================================================================
            // STEP 1: ADD nums[right] to the window
            // =================================================================
            frequency[nums[right]]++;
            
            // =================================================================
            // STEP 2: Check if we added a NEW distinct element
            // =================================================================
            // If frequency becomes 1, this is the first occurrence in window
            // Decrement k to track that we used one of our "distinct slots"
            if (frequency[nums[right]] == 1) {
                k--;  // One less distinct element allowed
            }
            
            // =================================================================
            // STEP 3: SHRINK window if we exceeded k distinct elements
            // =================================================================
            // k < 0 means we have more than k distinct elements
            // Keep shrinking from left until we're back to valid state
            while (k < 0) {
                // Remove leftmost element from window
                frequency[nums[left]]--;
                
                // If frequency becomes 0, we lost a distinct element
                // Increment k because we now have room for another distinct
                if (frequency[nums[left]] == 0) {
                    k++;  // Regained one distinct slot
                }
                
                left++;  // Shrink window from left
            }
            
            // =================================================================
            // STEP 4: COUNT valid subarrays ending at current right
            // =================================================================
            // All subarrays from any index in [left, right] to right are valid
            // Because they all have ≤ k distinct elements
            // 
            // Subarrays: [right], [right-1,right], ..., [left,...,right]
            // Count = right - left + 1
            count += right - left + 1;
            
            // Move right pointer to expand window
            right++;
        }
        
        return count;
    }
}
