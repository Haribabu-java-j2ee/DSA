import java.util.*;

public class SortCheck {
    public static void main(String[] args) {
        int[] nums = {1};
        int k=1;
        SortCheck obj = new SortCheck();
        int result = obj.findKthLargest(nums,k);
        System.out.println(result);
    }

    public int findKthLargest(int[] nums, int k) {
        if (nums == null || nums.length < k) {
            return -1;
        }
        int low = 0;
        int high = nums.length - 1;
        while (low <= high) {
            int partition = getRandomPartition(nums, low, high);
            if (partition == nums.length - k) {
                return nums[partition];
            } else if (partition < nums.length - k) {
                low = partition + 1;
            } else {
                high = partition - 1;
            }
        }
        return -1;
    }

    private int getRandomPartition(int[] nums, int low, int high){
        int randomIndex=low+ new Random().nextInt(high-low+1);
        swap(nums, randomIndex, high);
        int i=low;
        int pivot=nums[high];
        for(int j=low;j<high;j++){
            if(nums[j]<pivot){
                swap(nums, i, j);
                i++;
            }
        }
        swap(nums,i,high);
        return i;
    }

    private void swap(int[] nums, int low, int high){
        int temp=nums[low];
        nums[low]=nums[high];
        nums[high]=temp;
    }

}
