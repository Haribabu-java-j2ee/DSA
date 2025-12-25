package dsapatterns.twopointer;
//https://leetcode.com/problems/rotate-array/
public class RotateArrayByk {
    public static void main(String[] args) {
        int[] nums=new int[]{1,2,3,4,5,6,7};
        int k=3;
        RotateArrayByk obj=new RotateArrayByk();
        obj.rotate(nums,k);
        for(int num:nums){
            System.out.print(num+" ");
        }
    }
    public void rotate(int[] nums, int k) {
        int n=nums.length;
        k%=n;
        reverse(nums,0,n-k-1);
        reverse(nums,n-k,n-1);
        reverse(nums,0,n-1);
    }

    private void reverse(int[] nums, int start, int end){
        while(start<end){
            int temp=nums[start];
            nums[start++]=nums[end];
            nums[end--]=temp;
        }
    }
}
