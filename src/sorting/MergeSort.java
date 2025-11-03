package sorting;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

//t(n)=O(nlogn)
public class MergeSort {
    public static void main(String[] args) {
        int[] arr = {5, 4, 3, 2, 1};
        MergeSort m = new MergeSort();
        m.helper(arr);
        Arrays.stream(arr).forEach(System.out::println);

        ArrayList<Integer> arrayList= new ArrayList<>();
        arrayList.add(5);
        arrayList.add(4);
        arrayList.add(3);
        arrayList.add(2);
        arrayList.add(1);
        merge_sort(arrayList);
        arrayList.forEach(System.out::println);
    }

    private void helper(int[] arr) {
        this.mergeSort(arr, 0, arr.length-1);
    }

    private void mergeSort(int[] arr, int start, int end) {
        if (start < end) {

            int mid = start + (end - start) / 2;
            mergeSort(arr, start, mid);
            mergeSort(arr, mid + 1, end);

            int n1 = mid - start + 1;
            int n2 = end - mid;

            int[] left = new int[n1];
            int[] right = new int[n2];

            for (int i = 0; i < n1; ++i) {
                left[i] = arr[start + i];
            }

            for (int i = 0; i < n2; ++i) {
                right[i] = arr[mid + 1 + i];
            }

            int k = start;
            int i = 0, j = 0;
            while (i < n1 && j < n2) {
                if (left[i] <= right[j]) {
                    arr[k] = left[i];
                    i++;
                } else {
                    arr[k] = right[j];
                    j++;
                }
                k++;
            }

            while (i < n1) {
                arr[k] = left[i];
                i++;
                k++;
            }
            while (j < n2) {
                arr[k] = right[j];
                j++;
                k++;
            }
        }
    }



    static ArrayList<Integer> merge_sort(ArrayList<Integer> arr){
        if(Objects.isNull(arr) || arr.isEmpty()){
            return arr;
        }
        merge_sort_helper(arr,0,arr.size()-1);
        return arr;
    }

    static void merge_sort_helper(ArrayList<Integer> arr, int start, int end){
        if(start<end){
            int mid=start+(end-start)/2;
            merge_sort_helper(arr,start,mid);
            merge_sort_helper(arr,mid+1,end);

            int n1= mid-start+1;
            int n2=end-mid;
            int[] left=new int[n1];
            int[] right=new int[n2];

            for(int i=0;i<n1;i++){
                left[i]=arr.get(start+i);
            }
            for(int i=0;i<n2;i++){
                right[i]=arr.get(mid+1+i);
            }

            int k=start;
            int i=0,j=0;

            while(i<n1 && j<n2){
                if(left[i]<=right[j]){
                    arr.set(k,left[i]);
                    i++;
                }else{
                    arr.set(k,right[j]);
                    j++;
                }
                k++;
            }

            while(i<n1){
                arr.set(k,left[i]);
                i++;
                k++;
            }
            while(j<n2){
                arr.set(k,right[j]);
                j++;
                k++;
            }

        }
    }
}
