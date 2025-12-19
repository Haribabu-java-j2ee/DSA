package dsapatterns.twopointer;

//https://leetcode.com/problems/remove-element/description/
public class RemoveTargetElement {
    public static void main(String[] args) {
        int[] nums= {2,3,3,2};
        int val=3;
        RemoveTargetElement obj=new RemoveTargetElement();
        int length=obj.removeElement(nums, val);
        System.out.println("Length: "+length);
        for(int i=0;i<length;i++){
            System.out.print(nums[i]+" ");
        }
    }
    public int removeElement(int[] nums, int val) {
        int left = 0;
        int right = nums.length - 1;

        while (left <= right) {
            if (nums[left] == val) {
                // Swap with right and decrease right
               swap(left,right,nums);
                right--;
            } else {
                left++;
            }
        }
        return left; // left points to the count of non-val elements
    }

    private void swap(int left, int right, int[] nums){
        int temp=nums[left];
        nums[left]=nums[right];
        nums[right]=temp;
    }
}
