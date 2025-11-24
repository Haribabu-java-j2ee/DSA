package dsapatterns.twopointer;
//https://leetcode.com/problems/squares-of-a-sorted-array/description/
public class SquareAndSort {
    public static void main(String[] args) {
        int[] nums= {-7,-3,2,3,11};
        SquareAndSort obj=new SquareAndSort();
        int[] result=obj.sortedSquares(nums);
        for(int num:result){
            System.out.print(num+" ");
        }
    }
    public int[] sortedSquares(int[] nums) {
        int n=nums.length;
        if(n==0){
            return nums;
        }
        int left=0;
        int right=n-1;
        int i=n-1;
        int[] result=new int[n];
        int leftElement=0,rightElement=0;
        while(left<=right){
            leftElement=nums[left]*nums[left];
            rightElement=nums[right]*nums[right];
            if(leftElement>rightElement){
                result[i--]=leftElement;
                left++;
            }else{
                result[i--]=rightElement;
                right--;
            }


        }
        return result;
    }
}
