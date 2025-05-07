package recursion.problemset;

import java.util.ArrayList;

public class EvalExpToTarget {
    public static void main(String[] args) {
        String s = "202";
        long target = 4;
        generateAllExpressions(s, target).forEach(System.out::println);
    }

    /*
    Asymptotic complexity in terms of `n` = size of the input string:
    * Time: O(n * 3^n).
    * Auxiliary space: O(n * 3^n).
    * Total space: O(n * 3^n).
    */

    static ArrayList<String> generateAllExpressions(String s, Long target) {
        // Write your code here.
        ArrayList<String> allExp = new ArrayList<>();
        generate_all_expressions_util(allExp, s, target, s.length(), "", 0, 0l, 0l);
        return allExp;
    }

    static void generate_all_expressions_util(ArrayList<String> allExp, String s, long target,
                                              int n, String curr_exp, int pos, long curr_exp_val, long curr_exp_val_after_right_most_add) {

        if (pos == n) {
            if (curr_exp_val == target) {
                allExp.add(curr_exp);
            }
            return;
        }

        for (int i = pos; i < n; i++) {
            String str_to_be_added = s.substring(pos, i + 1);
            long num_to_be_added = Long.parseLong(str_to_be_added);
            if (pos == 0) {
                generate_all_expressions_util(allExp, s, target, n, str_to_be_added, i + 1, num_to_be_added, num_to_be_added);
            } else {
                generate_all_expressions_util(allExp, s, target, n, curr_exp + '+' + str_to_be_added, i + 1, curr_exp_val + num_to_be_added, num_to_be_added);
                generate_all_expressions_util(allExp, s, target, n, curr_exp + '*' + str_to_be_added, i + 1,
                        (curr_exp_val - curr_exp_val_after_right_most_add + (curr_exp_val_after_right_most_add * num_to_be_added)), curr_exp_val_after_right_most_add * num_to_be_added);
            }
        }
    }


}
