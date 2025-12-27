package dsapatterns.twopointer;

/**
 * =====================================================================================
 * TRAPPING RAIN WATER - Two Pointer Approach
 * =====================================================================================
 * LeetCode: https://leetcode.com/problems/trapping-rain-water/
 *
 * =====================================================================================
 * INTUITION:
 * =====================================================================================
 * Water at any position = min(leftMax, rightMax) - height[i].
 * Process from smaller side: if height[left] < height[right], water at left is
 * bounded by leftMax (rightMax is guaranteed larger). Move inward from smaller side.
 *
 * =====================================================================================
 * EXAMPLE: [0,1,0,2,1,0,1,3,2,1,2,1] → Output: 6
 * =====================================================================================
 *
 *   Step | left | right | leftMax | rightMax | Water Added | Total
 *   -----|------|-------|---------|----------|-------------|------
 *     1  |  0   |  11   |    0    |    1     | 0-0=0       | 0
 *     2  |  1   |  11   |    1    |    1     | 1-1=0       | 0
 *     3  |  2   |  11   |    1    |    1     | 1-0=1       | 1
 *     4  |  3   |  11   |    2    |    1     | 1-1=0       | 1
 *     5  |  3   |  10   |    2    |    2     | 2-2=0       | 1
 *     ...continues...
 *     Final: Total water trapped = 6
 *
 * =====================================================================================
 * COMPLEXITY:
 * =====================================================================================
 *   Time:  O(n) - Single pass with two pointers
 *   Space: O(1) - Only four variables used
 *
 * =====================================================================================
 * EDGE CASES:
 * =====================================================================================
 *   1. Empty array → return 0
 *   2. Less than 3 elements → return 0 (cannot trap water)
 *   3. Monotonic increasing/decreasing → return 0
 *
 * =====================================================================================
 * OPTIMIZATION NOTE: This is the optimal O(n) time, O(1) space solution.
 * Alternative: Prefix arrays (O(n) space) or Stack-based (O(n) space).
 * =====================================================================================
 */
public class TrappingRainWater {
    public static void main(String[] args) {
        int[] input = new int[]{0,1,0,2,1,0,1,3,2,1,2,1};
        TrappingRainWater obj = new TrappingRainWater();
        int result = obj.trap(input);
        System.out.println("Total trapped water: " + result);
    }
    
    public int trap(int[] height) {
        int n = height.length;
        int left = 0;
        int right = n - 1;
        int totalWater = 0;
        int leftMax = 0;
        int rightMax = 0;
        
        while (left < right) {
            if (height[left] < height[right]) {
                leftMax = Math.max(leftMax, height[left]);
                totalWater += leftMax - height[left];
                left++;
            } else {
                rightMax = Math.max(rightMax, height[right]);
                totalWater += rightMax - height[right];
                right--;
            }
        }
        
        return totalWater;
    }
}
