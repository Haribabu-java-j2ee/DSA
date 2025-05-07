package sorting.problemset;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Given an array sorted in non-decreasing order and a target number,
 * find the indices of the two values from the array that sum up to the given target number.
 * <p>
 * Input:
 * <p>
 * {
 * "numbers": [1, 2, 3, 5, 10],
 * "target": 7
 * }
 * <p>
 * Output:
 * [1, 3]
 */
public class FindSumInSortedArr {
    public static void main(String[] args) {
        int[] arr = {-10, -7, -5, 0, 8, 10};
        int target = 3;
        boolean foundIndex = false;
        for (int i = 0; i < arr.length; i++) {
            if (foundIndex) {
                break;
            }
            for (int j = i + 1; j < arr.length; j++) {
                int jIndex = binarySearch(arr, j, target - arr[i]);
                if (jIndex != -1) {
                    System.out.println(i + " " + jIndex);
                    foundIndex = true;
                    break;
                }
            }
        }

        ArrayList<Integer> list = new ArrayList<>();
        Arrays.stream(arr).forEach(list::add);
        findSumIndex(list, target).forEach(System.out::println);

        //unsorted
        int[] numbers = {-100000, -5, 6, 100000, 5, 9, 10};
        target=-10;
        list = new ArrayList<>();
        Arrays.stream(numbers).forEach(list::add);
        two_sum(list,target).forEach(System.out::println);
    }

    static int binarySearch(int[] arr, int low, int target) {

        int high = arr.length - 1;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (arr[mid] == target) {
                return mid;
            } else if (arr[mid] < target) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return -1;
    }

    static ArrayList<Integer> findSumIndex(ArrayList<Integer> numbers, int target) {
        ArrayList<Integer> result = new ArrayList<>();
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < numbers.size(); i++) {
            int difference = target - numbers.get(i);
            if (map.containsKey(difference)) {
                result.add(map.get(difference));
                result.add(i);
                return result;
            }
            map.put(numbers.get(i), i);
        }
        result.add(-1);
        result.add(-1);
        return result;
    }


    static ArrayList<Integer> two_sum(ArrayList<Integer> numbers, Integer target) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < numbers.size(); i++) {
            map.put(numbers.get(i), i);
        }
        ArrayList<Integer> result = new ArrayList<>();
        for (int i = 0; i < numbers.size(); i++) {
            int difference = target - numbers.get(i);
            if (map.containsKey(difference) && map.get(difference)!=i) {
                result.add(i);
                result.add(map.get(difference));
                return result;
            }
        }
        result.add(-1);
        result.add(-1);
        return result;
    }


}
