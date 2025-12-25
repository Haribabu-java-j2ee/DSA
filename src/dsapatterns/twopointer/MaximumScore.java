package dsapatterns.twopointer;

//https://leetcode.com/problems/get-the-maximum-score/
public class MaximumScore {
    public static void main(String[] args) {
        int[] nums1 = new int[]{2, 4, 5, 8, 10};
        int[] nums2 = new int[]{4, 6, 8, 9};
        MaximumScore obj = new MaximumScore();
        int maxScore = obj.maxSum(nums1, nums2);
        System.out.println("Maximum Score: " + maxScore);
    }
    public int maxSum(int[] nums1, int[] nums2) {
        int MOD = 1000_000_007;
        int len1 = nums1.length;
        int len2 = nums2.length;
        int pointer1 = 0, pointer2 = 0;
        int sumPath1 = 0, sumPath2 = 0;
        while (pointer1 < len1 || pointer2 < len2) {
            if (pointer1 < len1 && (pointer2 == len2 || nums1[pointer1] < nums2[pointer2])) {
                sumPath1 += nums1[pointer1++];
            } else if (pointer2 < len2 && (pointer1 == len1 || nums1[pointer1] > nums2[pointer2])) {
                sumPath2 += nums2[pointer2++];
            } else {
                sumPath1 = sumPath2 = Math.max(sumPath1, sumPath2) + nums1[pointer1];
                pointer1++;
                pointer2++;
            }
        }
        return (int) (Math.max(sumPath1, sumPath2) % MOD);
    }
}
