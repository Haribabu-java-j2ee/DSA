package sorting;

import java.util.*;

public class QuickSort {
    public static void main(String[] args) {
        int[] arr = {5, 4, 3, 2, 1};
        QuickSort qSort = new QuickSort();
        // qSort.quickSort(arr,0,arr.length-1);
        // Arrays.stream(arr).forEach(System.out::println);
        ArrayList<Integer> arrayList = new ArrayList<>();
        arrayList.add(5);
        arrayList.add(4);
        arrayList.add(3);
        arrayList.add(2);
        arrayList.add(1);
        quick_sort1(arrayList);
        arrayList.forEach(System.out::println);
    }

    private static void quick_sort1(ArrayList<Integer> arrayList) {
        quickSort(arrayList, 0, arrayList.size() - 1);
    }

    private static void quickSort(ArrayList<Integer> arrayList, int start, int end) {
        if (start < end) {
            int pivot = partition(arrayList, start, end);
            quickSort(arrayList, start, pivot - 1);
            quickSort(arrayList, pivot + 1, end);
        }
    }

    private static int partition(ArrayList<Integer> arrayList, int start, int end) {
        int randomIndex = start + new Random().nextInt(end - start + 1);
        Collections.swap(arrayList, randomIndex, end);

        int pivot = arrayList.get(end);
        int i = start - 1;
        for (int j = start; j < end; j++) {
            if (arrayList.get(j) < pivot) {
                i++;
                Collections.swap(arrayList, i, j);
            }
        }
        Collections.swap(arrayList, i + 1, end);
        return i + 1;
    }

    private void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }


    //IK starts here

    public static int randomPartition(List<Integer> arr, int low, int high) {
        // Swapping arr[high] with any random element in the array and then partitioning around
        // pivot_element = arr[high].
        int randomIndex = low + new Random().nextInt(high - low + 1);
        Collections.swap(arr, randomIndex, high);

        int pivot = low;
        int pivotElement = arr.get(high);

        for (int i = low; i < high; i++) {
            if (arr.get(i) < pivotElement) {
                Collections.swap(arr, i, pivot);
                pivot++;
            }
        }

        // Taking pivot_element to pivot position.
        Collections.swap(arr, pivot, high);
        return pivot;
    }

    // Recursive helper function that sorts arr[low ... high].
    public static void quickSortHelper(List<Integer> arr, int low, int high) {
        if (low >= high) {
            return;
        }

        int pivot = randomPartition(arr, low, high);

        quickSortHelper(arr, low, pivot - 1);
        quickSortHelper(arr, pivot + 1, high);
    }

    public static ArrayList<Integer> quick_sort(ArrayList<Integer> arr) {
        quickSortHelper(arr, 0, arr.size() - 1);
        return arr;
    }
}
