package dsapatterns.fastslowpointer;

/**
 * =====================================================================================
 * CIRCULAR ARRAY LOOP - Fast/Slow Pointer Pattern
 * =====================================================================================
 * LeetCode: https://leetcode.com/problems/circular-array-loop/
 *
 * =====================================================================================
 * INTUITION:
 * =====================================================================================
 * Treat array as linked list where nums[i] = step to next node. Use Floyd's cycle
 * detection. Valid cycle must: (1) have same direction throughout, (2) length > 1.
 * Mark visited paths with 0 to avoid re-processing.
 *
 * =====================================================================================
 * EXAMPLE: nums = [2, -1, 1, 2, 2] → true (cycle at indices 0→2→3→0)
 * =====================================================================================
 *
 *   i=0: direction=2 (positive), slow=0, fast=getNext(0)=2
 *   
 *   Iteration 1: slow=0, fast=2
 *     - nums[2]=1 > 0 ✓, nums[getNext(2)]=nums[3]=2 > 0 ✓
 *     - slow != fast, continue
 *     - slow = getNext(0) = 2
 *     - fast = getNext(getNext(2)) = getNext(3) = 0
 *   
 *   Iteration 2: slow=2, fast=0
 *     - nums[0]=2 > 0 ✓, nums[getNext(0)]=nums[2]=1 > 0 ✓
 *     - slow != fast, continue
 *     - slow = getNext(2) = 3
 *     - fast = getNext(getNext(0)) = getNext(2) = 3
 *   
 *   Iteration 3: slow=3, fast=3
 *     - slow == fast! Check: slow != getNext(slow)? 3 != 0 ✓
 *     - Return TRUE (valid cycle found)
 *
 * =====================================================================================
 * COMPLEXITY:
 * =====================================================================================
 *   Time:  O(n) - Each element visited at most twice (once for detection, once for marking)
 *   Space: O(1) - Only pointers, modifies input array
 *
 * =====================================================================================
 * EDGE CASES:
 * =====================================================================================
 *   1. Single element cycle (self-loop) → invalid, must have length > 1
 *   2. Mixed directions in cycle → invalid, must be all positive or all negative
 *   3. Already visited (nums[i]=0) → skip to avoid infinite loop
 */
public class CircularArrayLoop {

    public static void main(String[] args) {
        CircularArrayLoop solution = new CircularArrayLoop();

        int[] nums1 = {2, -1, 1, 2, 2};
        System.out.println(solution.circularArrayLoop(nums1)); // true

        int[] nums2 = {-1,-2,-3,-4,-5,6};
        System.out.println(solution.circularArrayLoop(nums2)); // false

        int[] nums3 = {1,-1,5,1,4};
        System.out.println(solution.circularArrayLoop(nums3)); // false
    }

    /**
     * =====================================================================================
     * WHILE LOOP CONDITION EXPLAINED:
     * =====================================================================================
     * 
     * while (nums[fast] * direction > 0 && nums[getNext(nums, fast, n)] * direction > 0)
     * 
     * This checks TWO things before each iteration:
     * 
     * ┌─────────────────────────────────────────────────────────────────────────────────┐
     * │ CONDITION 1: nums[fast] * direction > 0                                         │
     * ├─────────────────────────────────────────────────────────────────────────────────┤
     * │ "Is the element at FAST's current position moving in SAME direction as start?" │
     * │                                                                                 │
     * │ WHY MULTIPLY?                                                                   │
     * │   - direction > 0 (positive, moving forward)                                    │
     * │   - direction < 0 (negative, moving backward)                                   │
     * │   - nums[fast] * direction > 0 means BOTH have SAME SIGN                        │
     * │                                                                                 │
     * │ EXAMPLE:                                                                        │
     * │   direction = 2 (positive), nums[fast] = 3 (positive)                           │
     * │   2 * 3 = 6 > 0 ✓ SAME direction, continue                                      │
     * │                                                                                 │
     * │   direction = 2 (positive), nums[fast] = -1 (negative)                          │
     * │   2 * (-1) = -2 < 0 ✗ DIFFERENT direction, STOP loop                            │
     * │                                                                                 │
     * │   direction = 2 (positive), nums[fast] = 0 (already visited)                    │
     * │   2 * 0 = 0, NOT > 0 ✗ Dead end, STOP loop                                      │
     * └─────────────────────────────────────────────────────────────────────────────────┘
     * 
     * ┌─────────────────────────────────────────────────────────────────────────────────┐
     * │ CONDITION 2: nums[getNext(nums, fast, n)] * direction > 0                       │
     * ├─────────────────────────────────────────────────────────────────────────────────┤
     * │ "Is the element at FAST's NEXT position ALSO moving in SAME direction?"        │
     * │                                                                                 │
     * │ WHY CHECK NEXT TOO?                                                             │
     * │   Because fast moves 2 steps per iteration (fast = getNext(getNext(fast)))      │
     * │   We must ensure BOTH steps are valid before moving.                            │
     * │                                                                                 │
     * │ VISUALIZATION:                                                                  │
     * │                                                                                 │
     * │   Before move:    [... fast ...]                                                │
     * │   After move:     [... fast+1 ... fast+2 ...]                                   │
     * │                        ↑           ↑                                            │
     * │                   check this   AND this                                         │
     * │                                                                                 │
     * │ If we don't check next, fast could jump to opposite direction or 0,             │
     * │ making the cycle detection invalid.                                             │
     * └─────────────────────────────────────────────────────────────────────────────────┘
     * 
     * ┌─────────────────────────────────────────────────────────────────────────────────┐
     * │ COMPLETE EXAMPLE: nums = [-1, 2, -3, 4]                                         │
     * ├─────────────────────────────────────────────────────────────────────────────────┤
     * │ Starting at i=1: direction = 2 (positive)                                       │
     * │                                                                                 │
     * │ Index:    0    1    2    3                                                      │
     * │ Value:   -1    2   -3    4                                                      │
     * │                ↑                                                                │
     * │              start                                                              │
     * │                                                                                 │
     * │ fast = getNext(1) = (1+2)%4 = 3                                                 │
     * │                                                                                 │
     * │ Check: nums[3] * direction = 4 * 2 = 8 > 0 ✓                                    │
     * │ Check: nums[getNext(3)] * direction                                             │
     * │        getNext(3) = (3+4)%4 = 3  (wraps around)                                 │
     * │        nums[3] * 2 = 4 * 2 = 8 > 0 ✓                                            │
     * │                                                                                 │
     * │ Both conditions pass → continue loop                                            │
     * │                                                                                 │
     * │ BUT if nums[3] was -4:                                                          │
     * │ Check: nums[3] * direction = (-4) * 2 = -8 < 0 ✗                                │
     * │ Loop STOPS because direction changed!                                           │
     * └─────────────────────────────────────────────────────────────────────────────────┘
     */
    public boolean circularArrayLoop(int[] nums) {
        int n = nums.length;

        // Try starting from each index
        for (int i = 0; i < n; i++) {
            // Skip already visited elements (marked as 0)
            if (nums[i] == 0) continue;

            // Cache the direction from starting point
            // Positive = moving forward (right), Negative = moving backward (left)
            int direction = nums[i];
            
            // Floyd's: slow starts at i, fast starts one step ahead
            int slow = i;
            int fast = getNext(nums, i, n);

            // Continue ONLY if fast and its next position are in SAME direction as start
            // nums[x] * direction > 0 means: both positive OR both negative (same sign)
            while (nums[fast] * direction > 0 &&
                    nums[getNext(nums, fast, n)] * direction > 0) {
                
                // Cycle detected! slow and fast meet
                if (slow == fast) {
                    // But is it a VALID cycle? Must have length > 1
                    // If slow == getNext(slow), it's a self-loop (single element pointing to itself)
                    if (slow != getNext(nums, slow, n))
                        return true;  // Valid cycle with length > 1
                    break;  // Invalid self-loop, break and mark path
                }
                
                // Move slow by 1 step
                slow = getNext(nums, slow, n);
                // Move fast by 2 steps
                fast = getNext(nums, getNext(nums, fast, n), n);
            }

            // Mark all visited elements in this path as 0
            // This avoids re-processing and proves no valid cycle exists from these nodes
            int idx = i;
            while (nums[idx] * direction > 0) {
                int next = getNext(nums, idx, n);  // Get next BEFORE zeroing
                nums[idx] = 0;  // Mark as visited
                idx = next;
            }
        }
        return false;  // No valid cycle found
    }

    /**
     * =====================================================================================
     * GET NEXT INDEX - Circular Array Navigation
     * =====================================================================================
     * 
     * Formula: ((i + nums[i]) % n + n) % n
     * 
     * ┌─────────────────────────────────────────────────────────────────────────────────┐
     * │ STEP 1: i + nums[i]                                                             │
     * ├─────────────────────────────────────────────────────────────────────────────────┤
     * │ Calculate raw next position (can be negative or > n)                            │
     * │                                                                                 │
     * │ Example: n=5, i=1, nums[1]=3  →  1 + 3 = 4      (valid)                          │
     * │ Example: n=5, i=4, nums[4]=2  →  4 + 2 = 6      (exceeds array, need wrap)       │
     * │ Example: n=5, i=1, nums[1]=-3 →  1 + (-3) = -2  (negative, need wrap)            │
     * └─────────────────────────────────────────────────────────────────────────────────┘
     * 
     * ┌─────────────────────────────────────────────────────────────────────────────────┐
     * │ STEP 2: (i + nums[i]) % n                                                       │
     * ├─────────────────────────────────────────────────────────────────────────────────┤
     * │ Wrap around for positive overflow, BUT Java % can return NEGATIVE!              │
     * │                                                                                 │
     * │ Example: 6 % 5 = 1       ✓ (works for positive)                                 │
     * │ Example: (-2) % 5 = -2   ✗ (still negative in Java!)                            │
     * └─────────────────────────────────────────────────────────────────────────────────┘
     * 
     * ┌─────────────────────────────────────────────────────────────────────────────────┐
     * │ STEP 3: ((i + nums[i]) % n + n) % n                                             │
     * ├─────────────────────────────────────────────────────────────────────────────────┤
     * │ Add n to handle negative, then % n again to normalize                           │
     * │                                                                                 │
     * │ Example: (-2 % 5) = -2  →  (-2 + 5) % 5 = 3 % 5 = 3  ✓                           │
     * │ Example: (1 % 5) = 1    →  (1 + 5) % 5 = 6 % 5 = 1   ✓ (no change for positive)  │
     * └─────────────────────────────────────────────────────────────────────────────────┘
     * 
     * ┌─────────────────────────────────────────────────────────────────────────────────┐
     * │ VISUAL: Circular Array with n=5                                                 │
     * ├─────────────────────────────────────────────────────────────────────────────────┤
     * │                                                                                 │
     * │            0                                                                    │
     * │          ╱   ╲                                                                  │
     * │        4       1      nums[i] > 0 → move clockwise (forward)                    │
     * │        │       │      nums[i] < 0 → move counter-clockwise (backward)           │
     * │        3 ───── 2                                                                │
     * │                                                                                 │
     * │   From index 1, step -3: 1 → 0 → 4 → 3 (lands at index 3)                       │
     * │   Formula: ((1 + (-3)) % 5 + 5) % 5 = ((-2) % 5 + 5) % 5 = (-2 + 5) % 5 = 3     │
     * └─────────────────────────────────────────────────────────────────────────────────┘
     */
    private int getNext(int[] nums, int i, int n) {
        return ((i + nums[i]) % n + n) % n;
    }

}
