package dsapatterns.slidingwindow;

//https://leetcode.com/problems/maximum-average-subarray-i/description/
public class MaxAvgSubArrI {
    public static void main(String[] args) {
        int[] nums={1,12,-5,-6,50,3};
        int k=4;
        MaxAvgSubArrI obj=new MaxAvgSubArrI();
        double result=obj.findMaxAverage(nums,k);
        System.out.println(result);
    }
    public double findMaxAverage(int[] nums, int k) {
        int n=nums.length;
        int sum=0;
        for(int i=0;i<k;i++){
            sum+=nums[i];
        }
        int maxSum=sum;
        for(int i=k;i<n ;i++){
            sum+=nums[i]-nums[i-k];
            maxSum=Math.max(sum,maxSum);
        }

        return (double)maxSum/k;
    }
}
