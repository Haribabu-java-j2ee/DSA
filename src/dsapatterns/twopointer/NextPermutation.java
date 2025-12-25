package dsapatterns.twopointer;

//https://leetcode.com/problems/next-permutation/
public class NextPermutation {
    public static void main(String[] args) {
        int[] nums=new int[]{2, 4, 1, 7, 5, 0};
        NextPermutation obj=new NextPermutation();
        obj.nextPermutation(nums);
        for(int num:nums){
            System.out.print(num+" ");
        }
    }
    public void nextPermutation(int[] nums) {
        int n=nums.length;
        int pivot=-1;

        for(int i=n-2;i>=0;i--){
            if(nums[i]<nums[i+1]){
                pivot=i;
                break;
            }
        }

        if(pivot==-1){
            reverse(nums,0,n-1);
            return;
        }

        for(int i=n-1;i>pivot;i--){
            if(nums[i]>nums[pivot]){
                swap(nums,i,pivot);
                break;
            }
        }
        reverse(nums,pivot+1,n-1);
    }

    private void reverse(int[] nums, int start, int end){
        while(start<end){
            swap(nums, start++,end--);
        }
    }

    private void swap(int[] nums, int start, int end){
        int temp=nums[start];
        nums[start]=nums[end];
        nums[end]=temp;
    }
}
