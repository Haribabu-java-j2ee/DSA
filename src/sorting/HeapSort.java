package sorting;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class HeapSort {
    public static void main(String[] args) {
        int[] arr = {5, 4, 3, 2, 1};
        heapSort(arr);
        Arrays.stream(arr).forEach(System.out::println);
    }


    private static void heapSort(int[] arr) {
        int n = arr.length;

        for (int i = n / 2 - 1; i >= 0; i--) {
            heapify(arr, n, i);
        }
        int temp;
        for (int i = n - 1; i > 0; i--) {
            temp = arr[0];
            arr[0] = arr[i];
            arr[i] = temp;
            heapify(arr, i, 0);
        }
    }

    private static void heapify(int[] arr, int n, int i) {
        int largest = i;

        int left = 2 * i + 1;
        int right = 2 * i + 2;

        if (left < n && arr[left] > arr[largest]) {
            largest = left;
        }

        if (right < n && arr[right] > arr[largest]) {
            largest = right;
        }
        int temp;
        if (largest != i) {
            temp = arr[i];
            arr[i] = arr[largest];
            arr[largest] = temp;
            heapify(arr, n, largest);
        }

    }



    static ArrayList<Integer> heap_sort(ArrayList<Integer> arr) {
        int n=arr.size();
        for(int i=n/2-1;i>=0;i--){
            heapify(arr,n,i);
        }
        int temp;
        for(int i=n-1;i>0;i--){
            Collections.swap(arr,0,i);
            heapify(arr,i,0);
        }
        return arr;
    }

    static void heapify(ArrayList<Integer> arr, int n , int i){
        int largest=i;

        int left=2*i+1;
        int right=2*i+2;

        if(left<n && arr.get(left)>arr.get(largest)){
            largest=left;
        }

        if(right<n && arr.get(right)>arr.get(largest)){
            largest=right;
        }

        if(largest!=i){
            Collections.swap(arr,largest,i);
            heapify(arr,n,largest);
        }
    }

}
