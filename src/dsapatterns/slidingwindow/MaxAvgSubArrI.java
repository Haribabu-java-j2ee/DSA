package dsapatterns.slidingwindow;

/**
 * =====================================================================================
 * MAXIMUM AVERAGE SUBARRAY I - Fixed Size Sliding Window
 * =====================================================================================
 * LeetCode: https://leetcode.com/problems/maximum-average-subarray-i/
 *
 * =====================================================================================
 * INTUITION:
 * =====================================================================================
 * Fixed window of size k. Compute initial sum of first k elements.
 * Slide window: add new element, remove leftmost element.
 * Track maximum sum, return maxSum/k at end.
 *
 * =====================================================================================
 * EXAMPLE: nums = [1,12,-5,-6,50,3], k = 4 → returns 12.75
 * =====================================================================================
 *
 *   Step 1: Initial window [1,12,-5,-6], sum = 2, maxSum = 2
 *   
 *   i=4: Add nums[4]=50, Remove nums[0]=1
 *        sum = 2 + 50 - 1 = 51
 *        Window: [12,-5,-6,50], maxSum = 51
 *   
 *   i=5: Add nums[5]=3, Remove nums[1]=12
 *        sum = 51 + 3 - 12 = 42
 *        Window: [-5,-6,50,3], maxSum = 51
 *   
 *   Result: 51 / 4 = 12.75
 *
 * =====================================================================================
 * COMPLEXITY:
 * =====================================================================================
 *   Time:  O(n) - Single pass through array
 *   Space: O(1) - Only variables for sum tracking
 *
 * =====================================================================================
 * EDGE CASES:
 * =====================================================================================
 *   1. k == n → only one window, return sum/k
 *   2. All negative numbers → still works, finds least negative average
 *   3. Integer overflow → sum can overflow for large arrays (consider using long)
 *
 * =====================================================================================
 * OPTIMIZATION NOTE:
 * =====================================================================================
 * For very large arrays with large values, consider using `long sum` to prevent overflow.
 */
public class MaxAvgSubArrI {
    public static void main(String[] args) {
        int[] nums={1,12,-5,-6,50,3};
        int k=4;
        MaxAvgSubArrI obj=new MaxAvgSubArrI();
        double result=obj.findMaxAverage(nums,k);
        System.out.println(result);
    }
    public double findMaxAverage(int[] nums, int k) {
        int n=nums.length;
        int sum=0;
        for(int i=0;i<k;i++){
            sum+=nums[i];
        }
        int maxSum=sum;
        for(int i=k;i<n ;i++){
            sum+=nums[i]-nums[i-k];
            maxSum=Math.max(sum,maxSum);
        }

        return (double)maxSum/k;
    }
}
