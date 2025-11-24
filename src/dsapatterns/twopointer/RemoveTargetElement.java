package dsapatterns.twopointer;

//https://leetcode.com/problems/remove-element/description/
public class RemoveTargetElement {
    public static void main(String[] args) {
        int[] nums= {3,2,2,3};
        int val=3;
        RemoveTargetElement obj=new RemoveTargetElement();
        int length=obj.removeElement(nums, val);
        System.out.println("Length: "+length);
        for(int i=0;i<length;i++){
            System.out.print(nums[i]+" ");
        }
    }
    public int removeElement(int[] nums, int val) {
        int n=nums.length;
        if(n==0){
            return 0;
        }
        if(n==1 && nums[0]==val){
            return 0;
        }
        int left=0;
        int right=n-1;

        while(left<=right){
            if(nums[right]==val){
                right--;
                continue;
            }
            if(nums[left]==val){
                swap(left,right,nums);
                right--;
            }
            left++;
        }

        return left;
    }

    private void swap(int left, int right, int[] nums){
        int temp=nums[left];
        nums[left]=nums[right];
        nums[right]=temp;
    }
}
