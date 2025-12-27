package dsapatterns.twopointer;

/**
 * =====================================================================================
 * NEXT PERMUTATION - Two Pointer Approach
 * =====================================================================================
 * LeetCode: https://leetcode.com/problems/next-permutation/
 *
 * =====================================================================================
 * INTUITION:
 * =====================================================================================
 * 1. Find rightmost pair where nums[i] < nums[i+1] (pivot point)
 * 2. Find rightmost element > pivot, swap them
 * 3. Reverse everything after pivot position
 * If no pivot found (array is descending), reverse entire array.
 *
 * =====================================================================================
 * EXAMPLE: [2,4,1,7,5,0] → [2,4,5,0,1,7]
 * =====================================================================================
 *
 *   Step 1: Find pivot (rightmost i where nums[i] < nums[i+1])
 *           i=4: 5 < 0? NO
 *           i=3: 7 < 5? NO
 *           i=2: 1 < 7? YES! pivot=2
 *
 *   Step 2: Find rightmost element > nums[pivot=2]=1
 *           j=5: 0 > 1? NO
 *           j=4: 5 > 1? YES! swap positions 2 and 4
 *           [2,4,1,7,5,0] → [2,4,5,7,1,0]
 *
 *   Step 3: Reverse from pivot+1 to end
 *           Reverse positions 3 to 5: [7,1,0] → [0,1,7]
 *           [2,4,5,7,1,0] → [2,4,5,0,1,7]
 *
 *   Result: [2,4,5,0,1,7]
 *
 * =====================================================================================
 * COMPLEXITY:
 * =====================================================================================
 *   Time:  O(n) - Two passes at most
 *   Space: O(1) - In-place modification
 *
 * =====================================================================================
 * EDGE CASES:
 * =====================================================================================
 *   1. Single element → no change
 *   2. Descending order [3,2,1] → reverse to [1,2,3] (smallest permutation)
 *   3. Ascending order [1,2,3] → [1,3,2]
 *   4. All same elements → no change
 *
 * =====================================================================================
 * OPTIMIZATION NOTE: Code is optimal. This is the standard O(n) time, O(1) space
 * solution for next permutation.
 * =====================================================================================
 */
public class NextPermutation {
    public static void main(String[] args) {
        int[] nums = new int[]{2, 4, 1, 7, 5, 0};
        NextPermutation obj = new NextPermutation();
        obj.nextPermutation(nums);
        for (int num : nums) {
            System.out.print(num + " ");
        }
    }
    
    public void nextPermutation(int[] nums) {
        int n = nums.length;
        int pivot = -1;

        // Step 1: Find pivot (rightmost i where nums[i] < nums[i+1])
        for (int i = n - 2; i >= 0; i--) {
            if (nums[i] < nums[i + 1]) {
                pivot = i;
                break;
            }
        }

        // If no pivot found, array is descending - reverse entire array
        if (pivot == -1) {
            reverse(nums, 0, n - 1);
            return;
        }

        // Step 2: Find rightmost element greater than pivot and swap
        for (int i = n - 1; i > pivot; i--) {
            if (nums[i] > nums[pivot]) {
                swap(nums, i, pivot);
                break;
            }
        }
        
        // Step 3: Reverse everything after pivot
        reverse(nums, pivot + 1, n - 1);
    }

    private void reverse(int[] nums, int start, int end) {
        while (start < end) {
            swap(nums, start++, end--);
        }
    }

    private void swap(int[] nums, int start, int end) {
        int temp = nums[start];
        nums[start] = nums[end];
        nums[end] = temp;
    }
}
