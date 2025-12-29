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

    public boolean circularArrayLoop(int[] nums) {
        int n = nums.length;

        for (int i = 0; i < n; i++) {
            if (nums[i] == 0) continue;

            int direction = nums[i];  // ✅ Cache direction
            int slow = i;
            int fast = getNext(nums, i, n);

            while (nums[fast] * direction > 0 &&
                    nums[getNext(nums, fast, n)] * direction > 0) {
                if (slow == fast) {
                    if (slow != getNext(nums, slow, n))
                        return true;
                    break;
                }
                slow = getNext(nums, slow, n);
                fast = getNext(nums, getNext(nums, fast, n), n);
            }

            // ✅ Fixed marking loop
            int idx = i;
            while (nums[idx] * direction > 0) {
                int next = getNext(nums, idx, n);  // Get next BEFORE zeroing
                nums[idx] = 0;
                idx = next;
            }
        }
        return false;
    }

    private int getNext(int[] nums, int i, int n) {
        return ((i + nums[i]) % n + n) % n;
    }

}
