package dsapatterns.twopointer;

import java.util.Arrays;
//https://leetcode.com/problems/move-zeroes/
public class Arrange0sLast {
    public static void main(String[] args) {
        int[] nums= {0,1,0,3,12};
        Arrange0sLast obj=new Arrange0sLast();
        obj.moveZeroes(nums);
        Arrays.stream(nums).forEach(i->System.out.print(i+" "));
    }

    public void moveZeroes(int[] nums) {
        int n=nums.length;
        if(n==0)
            return;
        int i=0,j=0,k=0;
        while(k<n){
            if(nums[k]==0){
                j++;
            }else{
                swap(i,j,nums);
                i++;
                j++;
            }
            k++;
        }
    }

    private void swap(int i, int j, int[] nums){
        int temp=nums[i];
        nums[i]=nums[j];
        nums[j]=temp;
    }
}
