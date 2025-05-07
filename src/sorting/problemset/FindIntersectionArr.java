package sorting.problemset;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class FindIntersectionArr {
    public static void main(String[] args) {
        int[] arr1 = {1, 2, 2, 2, 9};
        int[] arr2 = {1, 1, 2, 2};
        int[] arr3 = {1, 1, 1, 2, 2, 2};
        ArrayList<Integer> arrayList1 = new ArrayList<>();
        Arrays.stream(arr1).forEach(arrayList1::add);
        ArrayList<Integer> arrayList2 = new ArrayList<>();
        Arrays.stream(arr2).forEach(arrayList2::add);
        ArrayList<Integer> arrayList3 = new ArrayList<>();
        Arrays.stream(arr3).forEach(arrayList3::add);
        find_intersection2(arrayList1, arrayList2, arrayList3).forEach(System.out::println);
    }

    //not fully working , since not able to handle duplicates
    static ArrayList<Integer> find_intersection(ArrayList<Integer> arr1, ArrayList<Integer> arr2, ArrayList<Integer> arr3) {
        // Write your code here.
        Map<Integer, Integer> map = new HashMap<>();

        for (int i : arr1) {
            map.put(i, 1);
        }

        for (int i : arr2) {
            map.put(i, map.getOrDefault(i, 0) != 0 ? 2 : 1);
        }

        for (int i : arr3) {
            map.put(i, map.getOrDefault(i, 0) >= 2 ? 3 : 1);
        }

        ArrayList<Integer> result = new ArrayList<>();
        for (Map.Entry<Integer, Integer> entrySet : map.entrySet()) {
            if (entrySet.getValue() == 3) {
                result.add(entrySet.getKey());
            }
        }
        if (result.isEmpty()) {
            result.add(-1);
        }
        return result;
    }

    static ArrayList<Integer> find_intersection1(ArrayList<Integer> arr1, ArrayList<Integer> arr2, ArrayList<Integer> arr3) {
        ArrayList<Integer> result = new ArrayList<>();
        int j, k;
        for (int i = 0; i < arr1.size(); i++) {
            int count = 0;
            for (j = 0; j < arr2.size(); j++) {
                if (arr1.get(i) == arr2.get(j)) {
                    count++;
                    break;
                }
            }
            for (k = 0; k < arr3.size(); k++) {
                if (arr1.get(i) == arr3.get(k)) {
                    count++;
                    break;
                }
            }

            if (count == 2) {
                result.add(arr1.get(i));
                arr2.set(j, -1);
                arr3.set(k, -1);
            }

        }

        if (result.isEmpty()) {
            result.add(-1);
        }
        return result;
    }

    static ArrayList<Integer> find_intersection2(ArrayList<Integer> arr1, ArrayList<Integer> arr2, ArrayList<Integer> arr3) {
        ArrayList<Integer> result = new ArrayList<>();

        int i = 0, j = 0, k = 0;
        while (i < arr1.size() && j < arr2.size() && k < arr3.size()) {
            if (arr1.get(i) == arr2.get(j) && arr2.get(j) == arr3.get(k)) {
                result.add(arr1.get(i));
            }

            int mini = Math.min(arr1.get(i), arr2.get(j));
            mini = Math.min(mini, arr3.get(k));

            if (mini == arr1.get(i)) {
                i++;
            }
            if (mini == arr2.get(j)) {
                j++;
            }
            if (mini == arr3.get(k)) {
                k++;
            }
        }
        if (result.isEmpty()) {
            result.add(-1);
        }
        return result;
    }

}
