package dsapatterns.twopointer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//https://leetcode.com/problems/3sum/
//Time Complexity: O(n^2)
//Space Complexity: O(1) ignoring the space required for the output
public class TrippletSum {
    public static void main(String[] args) {
        int[] nums = new int[]{-1,-1,2,-1,-1,2};
        TrippletSum obj = new TrippletSum();
        System.out.println(obj.threeSum(nums));
    }
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> result=new ArrayList();
        int n=nums.length;
        if(n<3){
            return result;
        }

        Arrays.sort(nums);

        for(int i=0;i<n-2;i++){
            //same numbers form duplicate , no required
            if(i>0 && nums[i]==nums[i-1]){
                continue;
            }
            //we have sorted the arr and hence the positive number wont form a triplet
            if(nums[i]>0){
                break;
            }
            int left=i+1, right=n-1;
            int target=-nums[i];
            while(left<right){
                int sum=nums[left]+nums[right];
                if(sum==target){
                    result.add(Arrays.asList(nums[i],nums[left],nums[right]));
                    left++;
                    right--;
                    while(left<right && nums[left]==nums[left-1]){
                        left++;
                    }
                    while(left<right && nums[right]==nums[right+1]){
                        right--;
                    }
                }else if(sum<target){
                    left++;
                }else{
                    right--;
                }
            }
        }
        return result;
    }
}
