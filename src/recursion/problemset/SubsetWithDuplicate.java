package recursion.problemset;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;

public class SubsetWithDuplicate {
    public static void main(String[] args) {
        String s = "aab";
        get_distinct_subsets(s).forEach(System.out::println);
    }

    //working solution but space almost equals to 2^n
    static ArrayList<String> get_distinct_subsets(String s) {
        // Write your code here.
        ArrayList<String> result = new ArrayList<>();
        char[] strArr=s.toCharArray();
        Arrays.sort(strArr);

        get_distinct_subsets_recur(strArr, result, "", 0);
        result=result.stream().distinct().collect(Collectors.toCollection(ArrayList::new));
        return result;
    }

    private static void get_distinct_subsets_recur(char[] strArr, ArrayList<String> result, String temp, int index) {
        if (index >= strArr.length) {
            result.add(temp);
            return;
        }
            get_distinct_subsets_recur(strArr, result, temp + strArr[index], index + 1);
            get_distinct_subsets_recur(strArr, result, temp, index + 1);
    }

}
