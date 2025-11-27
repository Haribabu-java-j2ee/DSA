package dsapatterns.binarysearch;

//https://leetcode.com/problems/find-peak-element/
public class FindPeakElement {
    public static void main(String[] args) {
        FindPeakElement obj = new FindPeakElement();
        int[] nums = {1, 2, 1, 3, 5, 6, 4};
        System.out.println(obj.findPeakElement(nums));
    }

    public int findPeakElement(int[] nums) {
        int n = nums.length;
        if (n == 1) {
            return 0;
        }
        if (nums[0] > nums[1]) {
            return 0;
        }
        if (nums[n - 1] > nums[n - 2]) {
            return n - 1;
        }
        int left = 1;
        int right = n - 2;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] > nums[mid - 1] && nums[mid] > nums[mid + 1]) {
                return mid;
            } else if (nums[mid] < nums[mid - 1]) {
                right = mid - 1;
            } else if (nums[mid] < nums[mid + 1]) {
                left = mid + 1;
            }
        }
        return -1;
    }
}
