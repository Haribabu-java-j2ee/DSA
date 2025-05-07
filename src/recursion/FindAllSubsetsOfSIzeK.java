package recursion;

import java.util.ArrayList;

/*
   Asymptotic complexity in terms of `n` and 'k':
   * Time: O(2^n * k).
   * Auxiliary space: O(n).
   * Total space: O(nCk * k).
   */
public class FindAllSubsetsOfSIzeK {
    public static void main(String[] args) {
        find_combinatins(5, 3).forEach(System.out::println);
    }

    static ArrayList<ArrayList<Integer>> find_combinatins(Integer n, Integer k) {
        ArrayList<Integer> current = new ArrayList<>();
        ArrayList<ArrayList<Integer>> result = new ArrayList<>();

        combinations_recursive(1, n, k, current, result);
        return result;
    }

    static void combinations_recursive(int currentNumber, int n, int k, ArrayList<Integer> current, ArrayList<ArrayList<Integer>> result) {
        if (n - currentNumber + 1 + current.size() == k) {
            current.add(currentNumber);
        }

        if (current.size() == k) {
            result.add(new ArrayList<>(current));
            return;
        }

        if (currentNumber == n + 1) {
            return;
        }

        current.add(currentNumber);
        combinations_recursive(currentNumber + 1, n, k, current, result);
        current.remove(current.size() - 1);
        combinations_recursive(currentNumber + 1, n, k, current, result);
    }
}
