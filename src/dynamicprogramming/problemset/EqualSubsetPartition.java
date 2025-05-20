package dynamicprogramming.problemset;

import java.util.*;

public class EqualSubsetPartition {
    public static void main(String[] args) {
        int[] arr = {10, -3, 7, 2, 1, 3};
        ArrayList<Integer> list = new ArrayList();
        Arrays.stream(arr).forEach(list::add);
        System.out.println(equal_subset_sum_partition(list));
        System.out.println(equal_subset_sum_partition2(list));
    }

    static boolean reachedSum = false;

    static ArrayList<Boolean> equal_subset_sum_partition(ArrayList<Integer> list) {
        int sum = 0;
        for (int i = 0; i < list.size(); i++) {
            sum += list.get(i);
        }
        if (sum % 2 != 0) {
            return new ArrayList<>();
        }
        sum = sum / 2;
        ArrayList<Integer> temp = new ArrayList<>();
        equal_subset_sum_partition_recursive(list, sum, temp, 0);
        if (!temp.isEmpty()) {
            ArrayList<Boolean> result = new ArrayList<>();
            for (int i = 0; i < list.size(); i++) {
                if (temp.contains(list.get(i))) {
                    result.add(true);
                    temp.remove(list.get(i));
                } else {
                    result.add(false);
                }
            }
            return result;
        }
        return new ArrayList<>();
    }

    private static void equal_subset_sum_partition_recursive(ArrayList<Integer> list, int sum, ArrayList<Integer> temp, int index) {
        if ((sum == 0 && !temp.isEmpty())) {
            reachedSum = true;
            return;
        }
        if (index >= list.size() - 1) {
            return;
        }
        if (!reachedSum) {
            temp.add(list.get(index));
            equal_subset_sum_partition_recursive(list, sum - list.get(index), temp, index + 1);
        }
        if (!reachedSum) {
            temp.remove(temp.size() - 1);
            equal_subset_sum_partition_recursive(list, sum, temp, index + 1);
        }
    }


    static ArrayList<Boolean> equal_subset_sum_partition2(ArrayList<Integer> list) {
        int n = list.size();
        int sumNegative = 0;
        int sumPositive = 0;
        for (int i = 0; i < n; i++) {
            if (list.get(i) >= 0) {
                sumPositive += list.get(i);
            } else {
                sumNegative += list.get(i);
            }
        }
        int sum = sumPositive + sumNegative;
        if ((sum & 1) > 0) {
            return new ArrayList<>();
        }
        Map<Integer, Boolean>[] dp = new HashMap[n];
        for (int i = 0; i < n; i++) {
            dp[i] = new HashMap<>();
        }
        dp[0].put(list.get(0), true);
        for (int i = 1; i < n; i++) {
            for (int val = sumNegative; val <= sumPositive; val++) {
                dp[i].put(val, dp[i - 1].getOrDefault(val, false));

                if (val == list.get(i)) {
                    dp[i].put(val, true);
                } else if (val - list.get(i) >= sumNegative) {
                    dp[i].put(val, dp[i].getOrDefault(val, false) || dp[i - 1].getOrDefault(val - list.get(i), false));
                }
            }
        }
        int requiredSum = sum / 2;
        int index = n - 1;
        if (!dp[index].getOrDefault(requiredSum, false)) {
            return new ArrayList<>();
        }
        ArrayList<Boolean> result = new ArrayList<>(Collections.nCopies(n, false));
        int count = 0;
        while (index >= 0) {
            if (index != 0) {
                if (dp[index].getOrDefault(requiredSum, false) && !dp[index - 1].getOrDefault(requiredSum, false)) {
                    count++;
                    result.set(index, true);
                    requiredSum -= list.get(index);
                    if (requiredSum == 0) {
                        break;
                    }
                }
            } else {
                result.set(index, true);
                count++;
            }
            index--;
        }

        if (count == n) {
            return new ArrayList<>();
        }
        return result;
    }
}
