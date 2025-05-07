package sorting;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
//sorting happens by comparing the index
public class SelectionSort {
    public static void main(String[] args) {
        int[] arr = {5, 4, 3, 2, 1};
        int temp;
        for (int i = 0; i < arr.length; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[i] > arr[j]) {
                    temp = arr[i];
                    arr[i] = arr[j];
                    arr[j] = temp;
                }
            }
        }
        Arrays.stream(arr).forEach(System.out::print);
        SelectionSort selectionSort = new SelectionSort();
        List<Integer> arrList = List.of(5, 8, 3, 9, 4, 1, 7);

        //practice program
        List<Integer> arrList2= selectionSort.selection_sort(arrList);
        System.out.println(arrList2);
    }

    static ArrayList<Integer> selection_sort(List<Integer> arr) {
        int temp;
        int[] arrIn=arr.stream().mapToInt(i->i).toArray();
        for(int i=0;i<arrIn.length;i++){
            for(int j=i+1;j<arrIn.length;j++){
                if(arrIn[i]>arrIn[j]){
                    temp=arrIn[i];
                    arrIn[i]=arrIn[j];
                    arrIn[j]=temp;
                }
            }
        }

        return new ArrayList(Arrays.asList(arrIn));
    }
}
