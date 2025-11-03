package sorting.problemset;

import java.util.Arrays;
/**
 * Given an array of numbers, rearrange them in-place so that even numbers appear before odd ones.
 * INPUT
 * {
 * "numbers": [1, 2, 3, 4]
 * }
 *
 * OUTPUT
 *
 * [4, 2, 3, 1]
 * t(n)=O(n)
 * */

public class ArrangeOddEven {
    public static void main(String[] args) {
        int[] arr={1, 2, 3, 4};
        int n=arr.length;
        int left=0,right=n-1;
        while(left<=right){
            if(arr[left]%2!=0){
                swap(arr,left,right);
                right--;
            }else{
                left++;
            }
        }
        Arrays.stream(arr).forEach(System.out::println);
    }

    private static void swap(int[] arr, int i, int j){
        int temp=arr[i];
        arr[i]=arr[j];
        arr[j]=temp;
    }
}
