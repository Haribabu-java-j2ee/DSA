package dsapatterns.twopointer;

/**
 * =====================================================================================
 * CONTAINER WITH MOST WATER - Two Pointer Approach
 * =====================================================================================
 * LeetCode: https://leetcode.com/problems/container-with-most-water/
 *
 * =====================================================================================
 * INTUITION:
 * =====================================================================================
 * Start with widest container (left=0, right=n-1). Area = width × min(heights).
 * Move the SHORTER side inward - only way to potentially find larger area.
 * Moving taller side can never increase area (limited by shorter side).
 *
 * =====================================================================================
 * EXAMPLE: [1,8,6,2,5,4,8,3,7] → Output: 49
 * =====================================================================================
 *
 *   Step | left | right | height[l] | height[r] | Area        | Move
 *   -----|------|-------|-----------|-----------|-------------|-------
 *     1  |  0   |   8   |     1     |     7     | 8×1 = 8     | l++ (1<7)
 *     2  |  1   |   8   |     8     |     7     | 7×7 = 49 ★  | r-- (8>7)
 *     3  |  1   |   7   |     8     |     3     | 6×3 = 18    | r-- (8>3)
 *     4  |  1   |   6   |     8     |     8     | 5×8 = 40    | r-- (8>=8)
 *     5  |  1   |   5   |     8     |     4     | 4×4 = 16    | r--
 *     ...continues until left >= right...
 *
 *   Max Area = 49 (between indices 1 and 8)
 *
 * =====================================================================================
 * COMPLEXITY:
 * =====================================================================================
 *   Time:  O(n) - Single pass with two pointers
 *   Space: O(1) - Only three variables used
 *
 * =====================================================================================
 * EDGE CASES:
 * =====================================================================================
 *   1. Empty array → return 0
 *   2. Single element → return 0 (no container possible)
 *   3. All same heights → any container works, still O(n)
 *
 * =====================================================================================
 * OPTIMIZATION NOTE: Code is optimal. No improvements needed.
 * =====================================================================================
 */
public class ContainerWithMostWater {
    public static void main(String[] args) {
        int[] height = new int[]{1,8,6,2,5,4,8,3,7};
        ContainerWithMostWater obj = new ContainerWithMostWater();
        System.out.println(obj.maxArea(height));
    }
    
    public int maxArea(int[] height) {
        int n = height.length;
        int maxArea = 0;
        
        if (n == 0) {
            return maxArea;
        }
        
        int left = 0;
        int right = n - 1;
        
        while (left < right) {
            maxArea = Math.max(maxArea, (right - left) * Math.min(height[left], height[right]));
            
            if (height[left] < height[right]) {
                left++;
            } else {
                right--;
            }
        }
        
        return maxArea;
    }
}
