package sorting;

import java.util.Arrays;

public class Test {
    public static void main(String[] args) {
        int[] arr = {5, 4, 3, 2, 1};
        helper(arr);
        Arrays.stream(arr).forEach(System.out::println);

    }

    static void helper(int[] arr) {
        mergeSort(arr, 0, arr.length - 1);
    }

    static void mergeSort(int[] arr, int start, int end) {
        if (start < end) {
            int mid = start + (end - start) / 2;
            mergeSort(arr, start, mid);
            mergeSort(arr, mid + 1, end);

            int n1 = mid - start + 1;
            int n2 = end - mid;

            int[] left = new int[n1];
            int[] right = new int[n2];

            for (int i = 0; i < n1; i++) {
                left[i] = arr[start + i];
            }

            for (int i = 0; i < n2; i++) {
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
}
