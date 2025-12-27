package dsapatterns.twopointer;

/**
 * =====================================================================================
 * ROTATE ARRAY BY K - Two Pointer Reversal Approach
 * =====================================================================================
 * LeetCode: https://leetcode.com/problems/rotate-array/
 *
 * =====================================================================================
 * INTUITION:
 * =====================================================================================
 * Three reversals: reverse first part, reverse second part, reverse entire array.
 * This rotates elements k positions to the right. Key: k %= n (handle k > n).
 *
 * =====================================================================================
 * EXAMPLE: [1,2,3,4,5,6,7], k=3 → [5,6,7,1,2,3,4]
 * =====================================================================================
 *
 *   Original: [1,2,3,4,5,6,7], k=3
 *
 *   Step 1: Reverse first (n-k) elements [0..3]
 *           [1,2,3,4] → [4,3,2,1]
 *           Array: [4,3,2,1,5,6,7]
 *
 *   Step 2: Reverse last k elements [4..6]
 *           [5,6,7] → [7,6,5]
 *           Array: [4,3,2,1,7,6,5]
 *
 *   Step 3: Reverse entire array [0..6]
 *           [4,3,2,1,7,6,5] → [5,6,7,1,2,3,4]
 *
 *   Result: [5,6,7,1,2,3,4]
 *
 * =====================================================================================
 * COMPLEXITY:
 * =====================================================================================
 *   Time:  O(n) - Each element is reversed twice
 *   Space: O(1) - In-place reversal
 *
 * =====================================================================================
 * EDGE CASES:
 * =====================================================================================
 *   1. k = 0 → no rotation needed
 *   2. k = n → same as k = 0
 *   3. k > n → use k % n
 *   4. Single element → no change
 *
 * =====================================================================================
 * OPTIMIZATION NOTE: Code is optimal. The three-reversal trick is the standard
 * O(n) time, O(1) space solution. Alternative: cyclic replacements (same complexity).
 * =====================================================================================
 */
public class RotateArrayByk {
    public static void main(String[] args) {
        int[] nums = new int[]{1,2,3,4,5,6,7};
        int k = 3;
        RotateArrayByk obj = new RotateArrayByk();
        obj.rotate(nums, k);
        for (int num : nums) {
            System.out.print(num + " ");
        }
    }
    
    public void rotate(int[] nums, int k) {
        int n = nums.length;
        k %= n; // Handle k > n
        
        reverse(nums, 0, n - k - 1);     // Reverse first part
        reverse(nums, n - k, n - 1);     // Reverse second part
        reverse(nums, 0, n - 1);          // Reverse entire array
    }

    private void reverse(int[] nums, int start, int end) {
        while (start < end) {
            int temp = nums[start];
            nums[start++] = nums[end];
            nums[end--] = temp;
        }
    }
}
