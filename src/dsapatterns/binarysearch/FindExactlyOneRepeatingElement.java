package dsapatterns.binarysearch;

//most optimal is approach2, first solution is also good
public class FindExactlyOneRepeatingElement {
    public static void main(String[] args) {
        int[] nums = {1, 3, 21, 30, 41, 67, 98, 98, 99};
        System.out.println("Duplicate element: " + findDuplicate(nums));
    }

    public static int findDuplicate(int[] nums) {
        int start = 0;
        int end = nums.length - 1;

        while (start < end) {
            int mid = start + (end - start) / 2;

            // Check if duplicate is at mid
            if (mid > 0 && nums[mid] == nums[mid - 1]) {
                return nums[mid];
            }
            if (mid < nums.length - 1 && nums[mid] == nums[mid + 1]) {
                return nums[mid];
            }

            // Count how many elements should be <= nums[mid]
            // In a sorted array without duplicates: nums[mid] - nums[0] + 1 elements
            // But we have indices: mid + 1 elements

            // If nums[mid] - nums[start] < mid - start, duplicate is on left
            // If nums[mid] - nums[start] == mid - start, duplicate is on right

            if (nums[mid] - nums[start] < mid - start) {
                // Duplicate is in left half
                end = mid;
            } else {
                // Duplicate is in right half
                start = mid + 1;
            }
        }

        return nums[start];
    }

    public static int findDuplicate1(int[] nums) {
        int start = 0;
        int end = nums.length - 1;

        while (start <= end) {
            int mid = start + (end - start) / 2;

            // Check if duplicate is at mid or adjacent
            if (mid > 0 && nums[mid] == nums[mid - 1]) {
                return nums[mid];
            }
            if (mid < nums.length - 1 && nums[mid] == nums[mid + 1]) {
                return nums[mid];
            }

            // Key insight: In sorted array without duplicates,
            // nums[i] - nums[0] should equal i
            // If nums[mid] - nums[0] > mid, duplicate is on left
            // If nums[mid] - nums[0] == mid, duplicate is on right

            if (nums[mid] - nums[0] > mid) {
                // More gap than expected, duplicate already passed on left
                end = mid - 1;
            } else {
                // Gap is as expected, duplicate is on right
                start = mid + 1;
            }
        }

        return -1; // Should never reach here if exactly one duplicate exists
    }

    public static int findDuplicate2(int[] nums) {
        int start = 0;
        int end = nums.length - 1;

        while (start < end) {
            int mid = start + (end - start) / 2;

            // Direct check
            if (nums[mid] == nums[mid + 1]) {
                return nums[mid];
            }

            // Check if the duplicate is before mid
            // Count elements from start to mid
            int expectedCount = nums[mid] - nums[start] + 1;
            int actualCount = mid - start + 1;

            if (actualCount > expectedCount) {
                // More elements than unique values, duplicate is on left
                end = mid;
            } else {
                // Duplicate is on right
                start = mid + 1;
            }
        }

        return nums[start];
    }
}
