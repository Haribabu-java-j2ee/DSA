package sorting.problemset;

import java.util.Arrays;

//https://leetcode.com/problems/sort-even-and-odd-indices-independently/
public class SortEvenOddIncides {
    public static void main(String[] args) {
        int[] nums={4,1,2,3};
        SortEvenOddIncides obj=new SortEvenOddIncides();
        int[] result=obj.sortEvenOdd(nums);
        Arrays.stream(result).forEach(num->System.out.print(num+" "));
    }
    public int[] sortEvenOdd(int[] nums) {
        int n=nums.length;
        int evenLength=n/2;
        if(n%2!=0){
            evenLength=(n+1)/2;
        }
        int[] even=new int[evenLength];
        int[] odd=new int[n/2];

        for(int i=0;i<n;i++){
            if(i%2==0){
                even[i/2]=nums[i];
            }else{
                odd[i/2]=nums[i];
            }
        }

        Arrays.sort(even);
        Arrays.sort(odd);
        int[] result=new int[n];
        int k=0;
        for(int i=0;i<even.length;i++){
            result[k]=even[i];
            k+=2;
        }
        k=1;
        for(int i=0;i<odd.length;i++){
            result[k]=odd[odd.length-i-1];
            k+=2;
        }
        return result;
    }
}
