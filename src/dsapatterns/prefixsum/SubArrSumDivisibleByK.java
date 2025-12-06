package dsapatterns.prefixsum;

import java.util.HashMap;
import java.util.Map;

//https://leetcode.com/problems/continuous-subarray-sum/
public class SubArrSumDivisibleByK {
    public static void main(String[] args) {
        SubArrSumDivisibleByK obj=new SubArrSumDivisibleByK();
        int[] nums={23,2,4,6,7};
        int k=6;
        System.out.println(obj.checkSubarraySum(nums, k));
    }
    public boolean checkSubarraySum(int[] nums, int k) {
        int sum=0;
        Map<Integer,Integer> reminderMap=new HashMap();
        reminderMap.put(0,-1);

        for(int i=0;i<nums.length;i++){
            sum+=nums[i];
            int rem=sum%k;
            Integer prev=reminderMap.putIfAbsent(rem,i);
            if(prev!=null && i-prev>1) return true;
        }
        return false;
    }

    //n^2 time complexity approach
    public boolean checkSubarraySum1(int[] nums, int k) {
        int sum=nums[0];

        for(int i=1;i<nums.length;i++){
            if(nums[i-1]==0 && nums[i]==0){
                return true;
            }
            sum+=nums[i];
            if(sum%k==0){
                return true;
            }
            int j=0;
            int tempSum=sum;
            while(i-j>1 && tempSum>k){
                tempSum-=nums[j];
                j++;
                if(tempSum%k==0){
                    return true;
                }
            }
        }
        return false;
    }
}
