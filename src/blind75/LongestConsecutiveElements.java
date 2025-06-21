package blind75;

import java.util.Arrays;

public class LongestConsecutiveElements {
    public static void main(String[] args) {
        int[] nums={-8,-4,9,9,4,6,1,-4,-1,6,8};
        System.out.println(longestConsecutive(nums));
    }
    public static int longestConsecutive(int[] nums) {
        int n=nums.length;
        Arrays.sort(nums);
        int count=1;
        int maxCount=0;
        if(n==0 || n==1){
            return n;
        }
        for(int i=0;i<n-1;i++){
            if(nums[i]+1==nums[i+1]){
                count++;
            }else if(nums[i]==nums[i+1]){
                continue;
            }
            else{
                maxCount=Math.max(count,maxCount);
                count=1;
            }
        }

        return Math.max(count,maxCount);
    }
}
