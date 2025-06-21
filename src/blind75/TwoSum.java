package blind75;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class TwoSum {
    public static void main(String[] args) {
        int[] nums={3,2,4};
        int target=6;
        Arrays.stream(twoSum(nums,target)).forEach(System.out::println);
    }
    public static int[] twoSum(int[] nums, int target) {
        Map<Integer,Integer> map=new HashMap();

        for(int i=0;i<nums.length;i++){
            int find=target-nums[i];
            if(map.containsKey(find)){
                return new int[]{i,map.get(find)};
            }
            map.put(nums[i],i);
        }
        return null;
    }
}
