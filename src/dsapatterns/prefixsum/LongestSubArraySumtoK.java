package dsapatterns.prefixsum;

import java.util.HashMap;
import java.util.Map;

//https://www.geeksforgeeks.org/dsa/longest-sub-array-sum-k/
public class LongestSubArraySumtoK {
    public int longestSubarray1(int[] arr, int k) {
        int result=0;
        for(int i=0;i<arr.length;i++){
            int prefixSum=0;
            for(int j=i;j<arr.length;j++){
                prefixSum+=arr[j];
                if(prefixSum==k){
                    result=Math.max(result,j-i+1);
                }
            }
        }
        return result;
    }

    public int longestSubarray2(int[] arr, int k) {
        int result=0;
        int prefixSum=0;
        Map<Integer,Integer> prefixSumMap=new HashMap<>();
        for(int i=0;i<arr.length;i++){
            prefixSum+=arr[i];
            if(prefixSum==k){
                result=i+1;
            }
            else if(prefixSumMap.containsKey(prefixSum-k)){
                result=Math.max(result,i-prefixSumMap.get(prefixSum-k));
            }
            prefixSumMap.putIfAbsent(prefixSum,i);

        }
        return result;
    }

    public static void main(String[] args) {
        int[] arr = {1,1,3,1,0,0,0,0,0,5};
        int k = 5;
        LongestSubArraySumtoK longestSubArraySumtoK = new LongestSubArraySumtoK();
        System.out.println(longestSubArraySumtoK.longestSubarray1(arr, k));
        System.out.println(longestSubArraySumtoK.longestSubarray2(arr, k));
    }
}
