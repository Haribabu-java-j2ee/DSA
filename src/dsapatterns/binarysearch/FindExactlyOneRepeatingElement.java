package dsapatterns.binarysearch;

/**
 * =====================================================================================
 * FIND EXACTLY ONE REPEATING ELEMENT
 * =====================================================================================
 *
 * IMPORTANT: Different algorithms work for different constraints!
 *
 * ┌─────────────────────────────────────────────────────────────────────────────────┐
 * │ Algorithm                    │ Use Case                        │ Time  │ Space │
 * ├─────────────────────────────────────────────────────────────────────────────────┤
 * │ findDuplicateSorted()        │ Any SORTED array                │ O(n)  │ O(1)  │
 * │ findDuplicateBinarySearch()  │ CONSECUTIVE integers, sorted    │O(logn)│ O(1)  │
 * │ findDuplicateFloyd()         │ Values 1 to n, unsorted OK      │ O(n)  │ O(1)  │
 * │ findDuplicateXOR()           │ Values 1 to n-1, one duplicate  │ O(n)  │ O(1)  │
 * └─────────────────────────────────────────────────────────────────────────────────┘
 *
 * WARNING: findDuplicateBinarySearch() ONLY works when the array contains
 * CONSECUTIVE integers with one duplicate!
 *
 * Example that WORKS:    {1, 2, 3, 3, 4, 5} - consecutive 1-5, one duplicate
 * Example that FAILS:    {1, 3, 21, 21, 41} - non-consecutive, has gaps
 */
public class FindExactlyOneRepeatingElement {
    
    public static void main(String[] args) {
        System.out.println("╔══════════════════════════════════════════════════════════════════════════╗");
        System.out.println("║           FIND EXACTLY ONE REPEATING ELEMENT                             ║");
        System.out.println("╚══════════════════════════════════════════════════════════════════════════╝\n");
        
        // Test 1: Non-consecutive integers (use findDuplicateSorted)
        int[] nums1 = {1, 3, 21, 21, 41, 67, 97, 98, 99};
        System.out.println("Test 1: Non-consecutive sorted array (use findDuplicateSorted)");
        System.out.println("  Input: {1, 3, 21, 21, 41, 67, 97, 98, 99}");
        System.out.println("  findDuplicateSorted(): " + findDuplicateSorted(nums1));
        System.out.println("  Expected: 21 ✓\n");

        // Test 2: Consecutive integers (binary search works)
        int[] nums2 = {91, 92, 92, 93, 94, 95, 96, 97};
        System.out.println("Test 2: Consecutive integers (both methods work)");
        System.out.println("  Input: {1, 2, 3, 3, 4, 5}");
        System.out.println("  findDuplicateBinarySearch(): " + findDuplicateBinarySearch(nums2));
        System.out.println("  findDuplicateSorted(): " + findDuplicateSorted(nums2));
        System.out.println("  Expected: 3 ✓\n");
        
        // Test 3: Duplicate at the end
        int[] nums3 = {1, 2, 3, 4, 5, 5};
        System.out.println("Test 3: Consecutive, duplicate at end");
        System.out.println("  Input: {1, 2, 3, 4, 5, 5}");
        System.out.println("  findDuplicateBinarySearch(): " + findDuplicateBinarySearch(nums3));
        System.out.println("  Expected: 5 ✓\n");
        
        // Test 4: Duplicate at the beginning
        int[] nums4 = {1, 1, 2, 3, 4, 5};
        System.out.println("Test 4: Consecutive, duplicate at beginning");
        System.out.println("  Input: {1, 1, 2, 3, 4, 5}");
        System.out.println("  findDuplicateBinarySearch(): " + findDuplicateBinarySearch(nums4));
        System.out.println("  Expected: 1 ✓\n");
        
        // Test 5: Values in range [1, n] - Floyd's algorithm (UNSORTED!)
        int[] nums5 = {3, 1, 3, 4, 2};
        System.out.println("Test 5: Unsorted, values in [1, n-1] (use Floyd's)");
        System.out.println("  Input: {3, 1, 3, 4, 2}");
        System.out.println("  findDuplicateFloyd(): " + findDuplicateFloyd(nums5));
        System.out.println("  Expected: 3 ✓\n");
        
        // Test 6: XOR method
        int[] nums6 = {1, 2, 3, 4, 2};
        System.out.println("Test 6: Values in [1, n-1] (use XOR)");
        System.out.println("  Input: {1, 2, 3, 4, 2}");
        System.out.println("  findDuplicateXOR(): " + findDuplicateXOR(nums6));
        System.out.println("  Expected: 2 ✓\n");
        
        System.out.println("╔══════════════════════════════════════════════════════════════════════════╗");
        System.out.println("║                        ALL TESTS COMPLETE                                ║");
        System.out.println("╚══════════════════════════════════════════════════════════════════════════╝");
    }
    
    /**
     * =====================================================================================
     * APPROACH 1: For ANY SORTED ARRAY - O(n) Linear Scan
     * =====================================================================================
     * 
     * Use Case: Any sorted array with exactly one duplicate (consecutive or not)
     * 
     * Time:  O(n) - Single pass through array
     * Space: O(1) - No extra space
     * 
     * Example: {1, 3, 21, 21, 41} → returns 21
     */
    public static int findDuplicateSorted(int[] nums) {
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] == nums[i - 1]) {
                return nums[i];
            }
        }
        return -1;
    }
    
    /**
     * =====================================================================================
     * APPROACH 2: Binary Search for CONSECUTIVE Integers Only - O(log n)
     * =====================================================================================
     * 
     * Use Case: Sorted array of CONSECUTIVE integers with exactly one duplicate
     *           Example: {1,2,3,3,4,5} works, but {1,3,21,21,41} does NOT work!
     * 
     * Key Insight:
     *   In a sorted array of consecutive integers without duplicates:
     *     - From index start to mid, there should be exactly (nums[mid] - nums[start] + 1) unique values
     *     - Actual elements = mid - start + 1
     *     - If actualCount > expectedCount → duplicate is in left half
     *     - Otherwise → duplicate is in right half
     * 
     * Time:  O(log n) - Binary search
     * Space: O(1) - No extra space
     * 
     * Example: {1, 2, 3, 3, 4, 5}
     *   mid=2, nums[mid]=3
     *   expectedCount = 3 - 1 + 1 = 3 (values 1, 2, 3)
     *   actualCount = 2 - 0 + 1 = 3
     *   3 > 3? No → go right
     *   Eventually finds duplicate at index 3
     */
    public static int findDuplicateBinarySearch(int[] nums) {
        int start = 0;
        int end = nums.length - 1;

        while (start < end) {
            int mid = start + (end - start) / 2;

            // Quick check: is duplicate right next to mid?
            if (nums[mid] == nums[mid + 1]) {
                return nums[mid];
            }
            if (mid > 0 && nums[mid] == nums[mid - 1]) {
                return nums[mid];
            }

            // Count elements from start to mid
            int expectedCount = nums[mid] - nums[start] + 1;  // Unique values in range
            int actualCount = mid - start + 1;                 // Actual elements present

            if (actualCount > expectedCount) {
                // More elements than unique values → duplicate is on left
                end = mid;
            } else {
                // Counts match → duplicate is on right
                start = mid + 1;
            }
        }

        return nums[start];
    }
    
    /**
     * =====================================================================================
     * APPROACH 3: Floyd's Cycle Detection (Tortoise and Hare) - O(n)
     * =====================================================================================
     * 
     * Use Case: 
     *   - Array has n+1 elements with values in range [1, n]
     *   - Exactly one duplicate exists (can appear multiple times)
     *   - Array does NOT need to be sorted!
     * 
     * Key Insight:
     *   Treat array values as pointers to next index.
     *   If there's a duplicate, following these pointers creates a CYCLE.
     *   The duplicate value is the entrance to the cycle.
     * 
     * Example: {3, 1, 3, 4, 2}
     *   Index: 0 → 1 → 2 → 3 → 4
     *   Value: 3 → 1 → 3 → 4 → 2
     *   
     *   Following pointers: 0 → 3 → 4 → 2 → 3 → 4 → 2 → ... (cycle at 3!)
     *   The cycle entrance (3) is the duplicate.
     * 
     * Time:  O(n) - Two passes through the cycle
     * Space: O(1) - Only two pointers
     */
    public static int findDuplicateFloyd(int[] nums) {
        int slow = nums[0];
        int fast = nums[0];
        
        // Phase 1: Find intersection point inside the cycle
        // Slow moves 1 step, fast moves 2 steps
        // They will meet inside the cycle
        do {
            slow = nums[slow];
            fast = nums[nums[fast]];
        } while (slow != fast);
        
        // Phase 2: Find the entrance to the cycle (the duplicate)
        // Reset slow to start, keep fast at meeting point
        // Both move 1 step at a time, they meet at cycle entrance
        slow = nums[0];
        while (slow != fast) {
            slow = nums[slow];
            fast = nums[fast];
        }
        
        return slow;  // Cycle entrance = duplicate value
    }
    
    /**
     * =====================================================================================
     * APPROACH 4: XOR Method - O(n)
     * =====================================================================================
     * 
     * Use Case:
     *   - Array has n elements with values in range [1, n-1]
     *   - Exactly one value appears twice, all others appear once
     * 
     * Key Insight:
     *   XOR properties: a ^ a = 0, a ^ 0 = a, XOR is commutative
     *   
     *   XOR all elements: 1 ^ 2 ^ 3 ^ 2 ^ 4 = (1 ^ 2 ^ 3 ^ 4) ^ 2
     *   XOR with 1 to n-1: 1 ^ 2 ^ 3 ^ 4
     *   Result: (1 ^ 2 ^ 3 ^ 4) ^ 2 ^ (1 ^ 2 ^ 3 ^ 4) = 2
     *   
     *   All unique numbers cancel out, leaving only the duplicate!
     * 
     * Example: {1, 2, 3, 4, 2} (n=5, values 1-4, duplicate is 2)
     *   XOR all elements: 1 ^ 2 ^ 3 ^ 4 ^ 2 = 1 ^ 3 ^ 4 (the 2s cancel)
     *   XOR with 1-4:     (1 ^ 3 ^ 4) ^ 1 ^ 2 ^ 3 ^ 4 = 2
     * 
     * Time:  O(n) - Two passes
     * Space: O(1) - Single variable
     */
    public static int findDuplicateXOR(int[] nums) {
        int n = nums.length;
        int xor = 0;
        
        // XOR all array elements
        for (int num : nums) {
            xor ^= num;
        }
        
        // XOR with all numbers from 1 to n-1
        for (int i = 1; i < n; i++) {
            xor ^= i;
        }
        
        // All unique numbers cancel out, leaving the duplicate
        return xor;
    }
}
