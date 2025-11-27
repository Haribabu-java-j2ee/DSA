package dsapatterns.binarysearch;

public class FindMinInRotatedArr {
    public static void main(String[] args) {
        FindMinInRotatedArr obj = new FindMinInRotatedArr();
        int[] nums = {4, 5, 6, 7, 0, 1, 2};
        System.out.println(obj.findMin(nums));
    }

    public int findMin(int[] nums) {
        int left=0;
        int right=nums.length-1;
        while(left<right){
            int mid=left+(right-left)/2;
            if(nums[mid]<nums[right]){
                right=mid;
            }else{
                left=mid+1;
            }
        }
        return nums[left];
    }
}
