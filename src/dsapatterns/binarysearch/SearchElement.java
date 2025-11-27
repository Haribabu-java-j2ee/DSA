package dsapatterns.binarysearch;

//https://leetcode.com/problems/binary-search
public class SearchElement {
    public static void main(String[] args) {
        SearchElement obj=new SearchElement();
        int[] nums={-1,0,3,5,9,12};
        int target=9;
        System.out.println(obj.search(nums, target));
    }
    public int search(int[] nums, int target) {
        int left=0;
        int right=nums.length-1;

        int mid=0;
        while(left<=right){
            mid=left+(right-left)/2;
            if(target==nums[mid]){
                return mid;
            }else if(target>nums[mid]){
                left=mid+1;
            }else{
                right=mid-1;
            }
        }
        return -1;
    }
}
