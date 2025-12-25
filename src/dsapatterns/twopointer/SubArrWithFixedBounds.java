package dsapatterns.twopointer;

//https://leetcode.com/problems/count-subarrays-with-fixed-bounds/
public class SubArrWithFixedBounds {
    public static void main(String[] args) {
        int[] nums = new int[]{1,3,5,2,7,5};
        int minK = 1;
        int maxK = 5;
        SubArrWithFixedBounds obj = new SubArrWithFixedBounds();
        long result = obj.countSubarrays(nums, minK, maxK);
        System.out.println("Number of subarrays with fixed bounds: " + result);
    }
    public long countSubarrays(int[] nums, int minK, int maxK) {
        long totalCount = 0;

        // Track the most recent positions of important elements
        int lastMinPosition = -1;  // Last position where we found minK
        int lastMaxPosition = -1;  // Last position where we found maxK
        int lastInvalidPosition = -1;  // Last position where we found an element outside [minK, maxK]

        // Iterate through each element in the array
        for (int currentIndex = 0; currentIndex < nums.length; currentIndex++) {
            // Check if current element is outside the valid range [minK, maxK]
            if (nums[currentIndex] < minK || nums[currentIndex] > maxK) {
                lastInvalidPosition = currentIndex;
            }

            // Update position if we found minK
            if (nums[currentIndex] == minK) {
                lastMinPosition = currentIndex;
            }

            // Update position if we found maxK
            if (nums[currentIndex] == maxK) {
                lastMaxPosition = currentIndex;
            }

            // Calculate valid subarrays ending at current position
            // We need both minK and maxK to appear after the last invalid element
            // The number of valid subarrays is determined by the leftmost valid starting position
            int leftmostValidStart = Math.min(lastMinPosition, lastMaxPosition);
            int validSubarraysCount = Math.max(0, leftmostValidStart - lastInvalidPosition);

            totalCount += validSubarraysCount;
        }

        return totalCount;
    }
}
