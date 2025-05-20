package dynamicprogramming.problemset;

import java.util.ArrayList;
import java.util.Arrays;

public class MatrixChainMultiplication {
    public static void main(String[] args) {
        int[] matrixSizes = {10, 30, 5, 60};
        ArrayList<Integer> matrixList = new ArrayList<>();
        Arrays.stream(matrixSizes).forEach(matrixList::add);
        System.out.println(minimum_multiplication_cost(matrixList));
    }

    static Integer minimum_multiplication_cost(ArrayList<Integer> matrix_sizes) {
        int n = matrix_sizes.size();
        int[][] tabledp = new int[n][n];
        for (int i = 0; i < n; i++) {
            Arrays.fill(tabledp[i], Integer.MAX_VALUE);
        }
        return helper(0, n - 1, matrix_sizes, tabledp);
    }

    private static Integer helper(int start, int end, ArrayList<Integer> matrixSizes, int[][] tabledp) {
        if (tabledp[start][end] != Integer.MAX_VALUE) {
            return tabledp[start][end];
        }
        if (start + 1 == end) {
            return 0;
        }
        int min = Integer.MAX_VALUE;
        for (int i = start + 1; i < end; i++) {
            int cost = matrixSizes.get(i) * matrixSizes.get(start) * matrixSizes.get(end) +
                    helper(start, i, matrixSizes, tabledp) + helper(i, end, matrixSizes, tabledp);
            min = Math.min(min, cost);
        }
        return tabledp[start][end] = min;
    }
}
