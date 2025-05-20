package dynamicprogramming.problemset;

import java.util.ArrayList;
import java.util.Arrays;

public class LargestSqMatrix {
    public static void main(String[] args) {
        int[][] matrix = {
                {1, 0, 0},
                {0, 1, 0},
                {0, 0, 1}
        };

        ArrayList<ArrayList<Integer>> matrixList = new ArrayList<>();
        Arrays.stream(matrix).forEach(arr -> {
            ArrayList<Integer> list = new ArrayList<>();
            Arrays.stream(arr).forEach(list::add);
            matrixList.add(list);
        });
        System.out.println(largest_sub_square_matrix(3, 3, matrixList));

        System.out.println(largest_sub_square_matrix1(3, 3, matrixList));

        System.out.println(largest_sub_square_matrix3(3, 3, matrixList));
    }

    static Integer largest_sub_square_matrix(Integer n, Integer m, ArrayList<ArrayList<Integer>> mat) {
        int maxSize = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (mat.get(i).get(j) == 0) {
                    continue;
                }
                boolean flag = false;
                for (int size = 0; (i + size) < n && (j + size) < m; size++) {
                    if (flag) {
                        break;
                    }
                    for (int col = j; col <= (j + size); col++) {
                        if (mat.get(i + size).get(col) == 0) {
                            flag = true;
                            break;
                        }
                    }

                    for (int row = i; row <= (i + size); row++) {
                        if (mat.get(row).get(j + size) == 0) {
                            flag = true;
                            break;
                        }
                    }
                    if (!flag) {
                        maxSize = Math.max(maxSize, size + 1);
                    }
                }

            }
        }
        return maxSize;
    }


    static Integer largest_sub_square_matrix1(Integer n, Integer m, ArrayList<ArrayList<Integer>> mat) {
        ArrayList<Integer> dp = new ArrayList<>();
        int maxSize = 0;

        for (int i = 0; i < m; i++) {
            dp.add(mat.get(0).get(i));
            maxSize = Math.max(maxSize, dp.get(i));
        }

        int prev = 0;
        int diagnol = 0;
        for (int i = 1; i < n; i++) {
            for (int j = 0; j < m; j++) {
                int tmp = dp.get(j);
                if (mat.get(i).get(j) == 1) {
                    if (j != 0) {
                        prev = dp.get(j - 1);
                    } else {
                        prev = 0;
                    }
                    dp.set(j, Math.min(Math.min(prev, tmp), diagnol) + 1);
                } else {
                    dp.set(j, 0);
                }
                diagnol = tmp;
                maxSize = Math.max(maxSize, dp.get(j));
            }
        }
        return maxSize;
    }


    static Integer largest_sub_square_matrix3(Integer n, Integer m, ArrayList<ArrayList<Integer>> mat) {
        int maxSize = 0;
        for (int i = 0; i < n; i++) {
            maxSize |= mat.get(i).get(0);
        }
        for (int i = 0; i < m; i++) {
            maxSize |= mat.get(0).get(i);
        }

        for (int i = 1; i < n; i++) {
            for (int j = 1; j < m; j++) {
                if (mat.get(i).get(j) == 1) {
                    int minValue = Math.min(mat.get(i - 1).get(j), Math.min(mat.get(i).get(j - 1), mat.get(i - 1).get(j - 1))) + 1;
                    mat.get(i).set(j, minValue);
                    maxSize = Math.max(maxSize, mat.get(i).get(j));
                }
            }
        }
        return maxSize;
    }

}
