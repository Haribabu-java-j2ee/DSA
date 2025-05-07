package recursion.problemset;

import java.util.*;

public class PrintSubSetEqualsTarget {
    public static void main(String[] args) {
        int[] arr = {42, 68, 35, 1, 70, 25, 79, 59, 63, 65, 6, 46, 82, 28, 62, 92, 96, 43, 28, 37, 92, 5, 3, 54, 93};
        int target = 83;
        ArrayList<Integer> arrList = new ArrayList<>();
        Arrays.stream(arr).forEach(arr1 -> arrList.add(arr1));
        generate_all_combinations1(arrList, target).forEach(System.out::println);
    }

    //working but need to be optimised
    static ArrayList<ArrayList<Integer>> generate_all_combinations(ArrayList<Integer> arr, Integer target) {
        // Write your code here.
        ArrayList<ArrayList<Integer>> result = new ArrayList<>();
        ArrayList<Integer> current = new ArrayList<>();
        Set<List<Integer>> set = new HashSet<>();
        generate_all_combinations_util(result, current, arr, target,arr.size() - 1,set);

        return result;
    }

    static void generate_all_combinations_util(ArrayList<ArrayList<Integer>> result,
                                               ArrayList<Integer> current, ArrayList<Integer> arr, Integer target,  int n,Set<List<Integer>> set) {
        if (target == 0 && !current.isEmpty() && !set.contains(current)) {
            result.add(new ArrayList(current));
            set.add(new ArrayList(current));
        }

        if (n < 0) {
            return;
        }

        current.add(arr.get(n));

        generate_all_combinations_util(result, current, arr, target - arr.get(n),  n - 1,set);
        current.remove(arr.get(n));

        generate_all_combinations_util(result, current, arr, target, n - 1,set);

    }

    //more optimal solution
    static ArrayList<ArrayList<Integer>> generate_all_combinations1(ArrayList<Integer> arr, Integer target) {
        ArrayList<ArrayList<Integer>> result = new ArrayList<>();
        Collections.sort(arr);
        ArrayList<Integer> current = new ArrayList<>();
        generate_all_combinations1_util(result, arr, target,current,0);
        return result;
    }

    private static void generate_all_combinations1_util(ArrayList<ArrayList<Integer>> result, ArrayList<Integer> arr, Integer target, ArrayList<Integer> current, int index) {
        if(target == 0 ) {
            result.add(new ArrayList(current));
            return;
        }

        if(index == arr.size() || target < 0) {
            return;
        }
        int duplicateCounter=index;
        while(duplicateCounter<arr.size() && arr.get(duplicateCounter).equals( arr.get(index))) {
            duplicateCounter++;
        }
        generate_all_combinations1_util(result, arr, target, current, duplicateCounter);

        int count=1;
        while(count<=duplicateCounter-index){
            current.add(arr.get(index));
            generate_all_combinations1_util(result, arr, target-count*arr.get(index), current, duplicateCounter);
            count++;
        }
        count=1;
        while(count<=duplicateCounter-index){
            current.remove(arr.get(index));
            count++;
        }
    }

}
