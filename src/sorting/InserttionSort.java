package sorting;

import java.util.ArrayList;
import java.util.Arrays;

public class InserttionSort {
    public static void main(String[] args) {
        int[] arr = {5, 4, 3, 2, 1};
        int pivot,j;
        for(int i=1;i<arr.length;i++){
            pivot = arr[i];
            j = i-1;
            while(j>=0 && arr[j]>pivot){
                arr[j+1] = arr[j];
                j--;
            }
            arr[j+1]=pivot;
        }
        Arrays.stream(arr).forEach(System.out::println);
    }

    static ArrayList<Integer> insertion_sort(ArrayList<Integer> arr) {
        // Write your code here.
        int j;
        int pivot;
        for(int i=1;i<arr.size();i++){
            pivot=arr.get(i);
            j=i-1;
            while(j>=0 &&  arr.get(j)>pivot){
                arr.set(j+1, arr.get(j));
                j--;
            }
            arr.set(j+1,pivot);
        }
        return arr;
    }



}
