package sorting;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class CountingSort {
    public static void main(String[] args) {
        int[] arr = {5, 8, 3, 9, 4, 1, 7, 1};
        int k = arr.length;

        int[] countArr = new int[10];
        for (int i = 0; i < k; i++) {
            countArr[arr[i]]++;
        }
        for (int i = 0; i < countArr.length; i++) {
            while (countArr[i] > 0) {
                System.out.println(i);
                countArr[i]--;
            }
        }
        System.out.println("===========");
        ArrayList<Integer> arrayList = new ArrayList<>();
        arrayList.add(5);
        arrayList.add(8);
        arrayList.add(3);
        arrayList.add(9);
        arrayList.add(4);
        arrayList.add(1);
        arrayList.add(7);
        counting_sort1(arrayList);
        arrayList.forEach(System.out::println);
    }

    public static ArrayList<Integer> counting_sort(ArrayList<Integer> arr) {
        int n = arr.size();

        // Find the minimum and maximum elements in the array
        int minElement = arr.get(0), maxElement = arr.get(0);
        for (int i = 1; i < n; i++) {
            int currentElement = arr.get(i);
            minElement = Math.min(minElement, currentElement);
            maxElement = Math.max(maxElement, currentElement);
        }

        // Create a frequency map to count the occurrences of each element
        Map<Integer, Integer> frequency = new HashMap<>();
        for (int i = 0; i < n; i++) {
            int currentElement = arr.get(i);
            frequency.put(currentElement, frequency.getOrDefault(currentElement, 0) + 1);
        }

        // Fill in the sorted array based on the frequencies
        int arrIndex = 0;
        for (int i = minElement; i <= maxElement; i++) {
            int currentFrequency = frequency.getOrDefault(i, 0);
            while (currentFrequency > 0) {
                arr.set(arrIndex++, i);
                currentFrequency--;
            }
        }

        return arr;
    }

    static ArrayList<Integer> counting_sort1(ArrayList<Integer> arr) {
        int n = arr.size();
        int min = arr.get(0), max = arr.get(0);
        for (int i = 1; i < n; i++) {
            min = Math.min(arr.get(i), min);
            max = Math.max(arr.get(i), max);
        }

        Map<Integer, Integer> countMap = new HashMap<>();
        for (int i = 0; i < n; i++) {
            countMap.put(arr.get(i), countMap.getOrDefault(arr.get(i), 0) + 1);
        }
        int j = 0;
        for (int i = min; i <= max; i++) {
            int curElement = countMap.getOrDefault(i, 0);
            while (curElement > 0) {
                arr.set(j++, i);
                curElement--;
            }
        }
        return arr;
    }
}
