package recursion.problemset;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class AchiveSum {
    public static void main(String[] args) {
        //long[] arr = {-3, -3, -3, -3};
        //long k = -12;
        long[] arr = {1};
        long k = 0;
        ArrayList<Long> arrlist = new ArrayList<>();
        Arrays.stream(arr).forEach(arrlist::add);
        System.out.println(check_if_sum_possible(arrlist, k));
        System.out.println(subarraySum(arrlist, k));

    }


    static void get_combinations(ArrayList<Integer> arr, int index, int target, ArrayList<Integer> current,
                                 ArrayList<ArrayList<Integer>> combinations) {
        if (target == 0) {
            combinations.add(new ArrayList<>(current));
            return;
        }

        if (index == arr.size() || target < 0) {
            return;
        }

        // arr[index] is in index range [index, end).
        int end = index;
        while (end < arr.size() && arr.get(end).equals(arr.get(index))) {
            end++;
        }

        // Skipping the current element.
        get_combinations(arr, end, target, current, combinations);

        // Current element can be present 1, 2 .... (end - index) number of times in a combination.
        int count = 1;
        while (count <= end - index) {
            current.add(arr.get(index));
            get_combinations(arr, end, target - count * arr.get(index), current, combinations);
            count++;
        }

        // Backtrack to convert the list "current" back to its previous state.
        count = 1;
        while (count <= end - index) {
            current.remove(current.size() - 1);
            count++;
        }
    }

    static ArrayList<ArrayList<Integer>> generate_all_combinations(ArrayList<Integer> arr, int target) {
        ArrayList<ArrayList<Integer>> combinations = new ArrayList<>();
        ArrayList<Integer> current = new ArrayList<>();

        arr.sort(Integer::compareTo);

        get_combinations(arr, 0, target, current, combinations);

        return combinations;
    }

    static Boolean check_if_sum_possible(ArrayList<Long> arr, Long k) {
        // Write your code here.

        return checkSum(arr, k, arr.size(), new ArrayList<>());
    }

    static boolean checkSum(ArrayList<Long> arr, Long k, int n, ArrayList<Long> current) {

        if (k == 0 && current.size() >0) {
            return true;
        }
        if (n == 0) {
            return false;
        }

        boolean flag1, flag2;
        current.add(arr.get(n - 1));
        flag1 = checkSum(arr, k - arr.get(n - 1), n - 1, current);
        current.remove(arr.get(n - 1));
        flag2 = checkSum(arr, k, n - 1, current);
        return flag1 || flag2;
    }


    static boolean subarraySum(ArrayList<Long> arr, Long k) {
        HashMap<Long, Long> map = new HashMap<>();
        map.put(0l, 1l);
        long sum = 0;
        for (int i = 0; i < arr.size(); i++) {
            sum += arr.get(i);
            if (map.containsKey(sum - k)) {
                return true;
            }
            map.put(sum, map.getOrDefault(sum, 0l) + 1);
        }
        return false;
    }


}
