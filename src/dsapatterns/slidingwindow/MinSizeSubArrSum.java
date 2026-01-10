package dsapatterns.slidingwindow;

/**
 * =====================================================================================
 * MINIMUM SIZE SUBARRAY SUM - Variable Size Sliding Window
 * =====================================================================================
 * LeetCode: https://leetcode.com/problems/minimum-size-subarray-sum/
 *
 * =====================================================================================
 * INTUITION:
 * =====================================================================================
 * Expand window with right pointer, adding to sum.
 * When sum >= target, shrink from left while maintaining validity.
 * Track minimum length of valid windows.
 *
 * =====================================================================================
 * EXAMPLE: nums = [2,3,1,2,4,3], target = 7 → returns 2
 * =====================================================================================
 *
 *   right | nums[r] | sum | sum>=7? | shrink        | minLen
 *   ------|---------|-----|---------|---------------|-------
 *     0   |    2    |  2  |   no    |      -        |  MAX
 *     1   |    3    |  5  |   no    |      -        |  MAX
 *     2   |    1    |  6  |   no    |      -        |  MAX
 *     3   |    2    |  8  |  YES    | len=4,sum=6   |   4
 *         |         |  6  |   no    |      -        |   4
 *     4   |    4    | 10  |  YES    | len=4,sum=7   |   4
 *         |         |  7  |  YES    | len=3,sum=6   |   3
 *         |         |  6  |   no    |      -        |   3
 *     5   |    3    |  9  |  YES    | len=3,sum=7   |   3
 *         |         |  7  |  YES    | len=2,sum=4   |   2
 *         |         |  4  |   no    |      -        |   2
 *
 *   Result: 2 (subarray [4,3])
 *
 * =====================================================================================
 * COMPLEXITY:
 * =====================================================================================
 *   Time:  O(n) - Each element added once, removed once
 *   Space: O(1) - Only pointers and sum variable
 *
 * =====================================================================================
 * EDGE CASES:
 * =====================================================================================
 *   1. No valid subarray exists → return 0
 *   2. Single element >= target → return 1
 *   3. Entire array needed → return n
 */
public class MinSizeSubArrSum {
    public static void main(String[] args) {
        int[] nums={2,3,1,2,4,3};
        int target=7;
        MinSizeSubArrSum obj=new MinSizeSubArrSum();
        System.out.println(obj.minSubArrayLen(target, nums));
    }
    public int minSubArrayLen(int target, int[] nums) {
        int n=nums.length;
        int left=0;
        int sum=0;
        int minLength=Integer.MAX_VALUE;
        for(int right=0;right<n;right++){
            sum+=nums[right];
            while(sum>=target){
                if(right-left+1<minLength){
                    minLength=right-left+1;
                }
                sum-=nums[left];
                left++;
            }
        }
        return minLength!=Integer.MAX_VALUE?minLength:0;
    }
}
