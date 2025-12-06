package dsapatterns.prefixsum;

//https://leetcode.com/problems/range-sum-query-immutable/
public class RangeSumQueryImmutable {
    public static void main(String[] args) {
        int[] nums = {-2, 0, 3, -5, 2, -1};
        NumArray obj = new NumArray(nums);
        System.out.println(obj.sumRange(0, 2));
        System.out.println(obj.sumRange(2, 5));
        System.out.println(obj.sumRange(0, 5));
    }
}
class NumArray {
    private int[] prefixSum;

    public NumArray(int[] nums) {
        prefixSum=new int[nums.length+1];
        for(int i=1; i<=nums.length;i++){
            prefixSum[i]=prefixSum[i-1]+nums[i-1];
        }
    }
    public int sumRange(int i, int j) {
        return prefixSum[j+1]-prefixSum[i];
    }
}
