package dsapatterns.prefixsum;

import java.util.HashMap;
import java.util.Map;

//https://leetcode.com/problems/subarray-sum-equals-k
public class SubArrSumEqualsK {
    public static void main(String[] args) {
        SubArrSumEqualsK obj = new SubArrSumEqualsK();
        int[] nums = {1, 1, 1};
        int k = 2;
        System.out.println(obj.subarraySum(nums, k));
        System.out.println(obj.subarraySum1(nums, k));
    }

    //n^2 approach using prefix sum
    public int subarraySum(int[] nums, int k) {
        int count = 0;

        int[] sum = new int[nums.length + 1];
        sum[0] = 0;
        for (int i = 1; i <= nums.length; i++)
            sum[i] = sum[i - 1] + nums[i - 1];

        for (int start = 0; start < sum.length; start++) {
            for (int end = start + 1; end < sum.length; end++) {
                if (sum[end] - sum[start] == k)
                    count++;
            }
        }

        return count;
    }

    public int subarraySum1(int[] nums, int k) {
        int count = 0, sum = 0;
        Map< Integer, Integer > map = new HashMap< >();
        map.put(0, 1);

        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            if (map.containsKey(sum - k))
                count += map.get(sum - k);
            map.put(sum, map.getOrDefault(sum, 0) + 1);
        }
        return count;
    }
}

