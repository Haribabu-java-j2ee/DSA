package dsapatterns.binarysearch;

//https://leetcode.com/problems/search-insert-position/description/
public class SearchInsertPosition {
    public static void main(String[] args) {
        SearchInsertPosition obj = new SearchInsertPosition();
        int[] nums = {1, 3, 5, 6};
        int target = 2;
        System.out.println(obj.searchInsert(nums, target));
    }
    public int searchInsert(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;

        int mid = 0;
        while (left <= right) {
            mid = left + (right - left) / 2;
            if (target == nums[mid]) {
                return mid;
            } else if (target > nums[mid]) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return left;
    }
}
