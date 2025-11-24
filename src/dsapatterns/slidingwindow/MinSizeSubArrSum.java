package dsapatterns.slidingwindow;

//https://leetcode.com/problems/minimum-size-subarray-sum/description/
public class MinSizeSubArrSum {
    public static void main(String[] args) {
        int[] nums={2,3,1,2,4,3};
        int target=7;
        MinSizeSubArrSum obj=new MinSizeSubArrSum();
        System.out.println(obj.minSubArrayLen(target, nums));
    }
    public int minSubArrayLen(int target, int[] nums) {
        int n=nums.length;
        int left=0;
        int sum=0;
        int minLength=Integer.MAX_VALUE;
        for(int right=0;right<n;right++){
            sum+=nums[right];
            while(sum>=target){
                if(right-left+1<minLength){
                    minLength=right-left+1;
                }
                sum-=nums[left];
                left++;
            }
        }
        return minLength!=Integer.MAX_VALUE?minLength:0;
    }
}
