package dsapatterns.twopointer;

/**
 * =====================================================================================
 * SQUARES OF A SORTED ARRAY - Two Pointer Approach
 * =====================================================================================
 * LeetCode: https://leetcode.com/problems/squares-of-a-sorted-array/
 *
 * =====================================================================================
 * INTUITION:
 * =====================================================================================
 * Input is sorted, but negatives when squared become large. Largest squares are
 * at either end. Use two pointers, compare squares, fill result from back.
 *
 * =====================================================================================
 * EXAMPLE: [-7,-3,2,3,11] → [4,9,9,49,121]
 * =====================================================================================
 *
 *   Step | left | right | left² | right² | Fill Position | Result
 *   -----|------|-------|-------|--------|---------------|----------------
 *     1  |  0   |   4   |  49   |  121   | result[4]=121 | [_,_,_,_,121]
 *     2  |  0   |   3   |  49   |    9   | result[3]=49  | [_,_,_,49,121]
 *     3  |  1   |   3   |   9   |    9   | result[2]=9   | [_,_,9,49,121]
 *     4  |  1   |   2   |   9   |    4   | result[1]=9   | [_,9,9,49,121]
 *     5  |  2   |   2   |   4   |    4   | result[0]=4   | [4,9,9,49,121]
 *
 *   Result: [4,9,9,49,121]
 *
 * =====================================================================================
 * COMPLEXITY:
 * =====================================================================================
 *   Time:  O(n) - Single pass through array
 *   Space: O(n) - Result array (required by problem)
 *
 * =====================================================================================
 * EDGE CASES:
 * =====================================================================================
 *   1. Empty array → return empty
 *   2. All non-negative → squares maintain order
 *   3. All negative → squares reverse order
 *   4. Single element → return [element²]
 *
 * =====================================================================================
 * OPTIMIZATION NOTE: Code is optimal. Cannot do O(1) space as we need to return
 * new sorted array.
 * =====================================================================================
 */
public class SquareAndSort {
    public static void main(String[] args) {
        int[] nums = {-7,-3,2,3,11};
        SquareAndSort obj = new SquareAndSort();
        int[] result = obj.sortedSquares(nums);
        for (int num : result) {
            System.out.print(num + " ");
        }
    }
    
    public int[] sortedSquares(int[] nums) {
        int n = nums.length;
        
        if (n == 0) {
            return nums;
        }
        
        int left = 0;
        int right = n - 1;
        int i = n - 1;
        int[] result = new int[n];
        int leftElement = 0, rightElement = 0;
        
        while (left <= right) {
            leftElement = nums[left] * nums[left];
            rightElement = nums[right] * nums[right];
            
            if (leftElement > rightElement) {
                result[i--] = leftElement;
                left++;
            } else {
                result[i--] = rightElement;
                right--;
            }
        }
        
        return result;
    }
}
