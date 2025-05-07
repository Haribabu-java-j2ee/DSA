package sorting;

import java.util.ArrayList;
import java.util.Arrays;
//sorting by comparing adjacent element
//i.e., swap only happens at adjacent element at 1 time
public class BubbleSort {
    public static void main(String[] args) {
        int[] arr = {5, 4, 3, 2, 1};
        boolean swapped = false;
        int temp;
        for (int i = 0; i < arr.length-1; i++) {
            for(int j=0;j<arr.length-i-1;j++){
                if(arr[j]>arr[j+1]){
                    temp = arr[j];
                    arr[j] = arr[j+1];
                    arr[j+1] = temp;
                    swapped=true;
                }
            }
            if(!swapped){
                break;
            }
        }
        Arrays.stream(arr).forEach(System.out::println);
    }

    static ArrayList<Integer> bubble_sort(ArrayList<Integer> arr) {
        boolean swapped=false;
        int temp;
        for(int i=0;i<arr.size()-1;i++){
            for(int j=0;j<arr.size()-i-1;j++){
                if(arr.get(j)>arr.get(j+1)){
                    temp=arr.get(j);
                    arr.set(j,arr.get(j+1));
                    arr.set(j+1,temp);
                    swapped=true;
                }
            }
            if(!swapped){
                break;
            }
        }
        return arr;
    }


}
