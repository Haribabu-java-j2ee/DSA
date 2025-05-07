package recursion.problemset;

import java.util.ArrayList;

/*
 * Asymptotic complexity in terms of number of unfilled cells `k` and number of rows(or columns) in `board` `n`:
 * Time: O(9^k).
 * Auxiliary space: O(k).
 * Total space: O(n^2).
 */
public class SudokuSolver {
    public static void main(String[] args) {
        int[][] mat = {
                {3, 0, 6, 5, 0, 8, 4, 0, 0},
                {5, 2, 0, 0, 0, 0, 0, 0, 0},
                {0, 8, 7, 0, 0, 0, 0, 3, 1},
                {0, 0, 3, 0, 1, 0, 0, 8, 0},
                {9, 0, 0, 8, 6, 3, 0, 0, 5},
                {0, 5, 0, 0, 9, 0, 6, 0, 0},
                {1, 3, 0, 0, 0, 0, 2, 5, 0},
                {0, 0, 0, 0, 0, 0, 0, 7, 4},
                {0, 0, 5, 2, 0, 6, 3, 0, 0}
        };

        ArrayList<ArrayList<Integer>> board = new ArrayList<>();

        for (int i = 0; i < 9; i++) {
            board.add(new ArrayList<>());
            for (int j = 0; j < 9; j++) {
                board.get(i).add(mat[i][j]);
            }
        }
        solve_sudoku_puzzle(board);
        for (int i = 0; i < board.size(); i++) {
            for (int j = 0; j < board.get(i).size(); j++)
                System.out.print(board.get(i).get(j) + " ");
            System.out.println();
        }
        solveSudoku(mat);

        for (int i = 0; i < mat.length; i++) {
            for (int j = 0; j < mat[i].length; j++)
                System.out.print(mat[i][j] + " ");
            System.out.println();
        }
    }

    static void solveSudoku(int[][] mat) {
        solveSudokuRecur(mat, 0, 0);
    }

    static boolean solveSudokuRecur(int[][] mat, int row, int col) {
        if (row == 8 && col == 9) {
            return true;
        }

        if (col == 9) {
            row++;
            col = 0;
        }
        if (mat[row][col] != 0)
            return solveSudokuRecur(mat, row, col + 1);
        for (int num = 1; num <= 9; num++) {
            if (isSafe(mat, row, col, num)) {
                mat[row][col] = num;
                if (solveSudokuRecur(mat, row, col + 1)) {
                    return true;
                }
                mat[row][col] = 0;
            }
        }
        return false;
    }


    static boolean isSafe(int[][] mat, int row, int col, int num) {
        for (int i = 0; i < 9; i++) {
            if (mat[i][col] == num) {
                return false;
            }
        }

        for (int i = 0; i < 9; i++) {
            if (mat[row][i] == num) {
                return false;
            }
        }

        int rowSub = row - (row % 3);
        int colSub = col - (col % 3);

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (mat[i + rowSub][j + colSub] == num) {
                    return false;
                }
            }
        }
        return true;
    }


    static ArrayList<ArrayList<Integer>> solve_sudoku_puzzle(ArrayList<ArrayList<Integer>> board) {
        // Write your code here.
        solveSudokuRecur(board);
        return board;
    }

    static boolean solveSudokuRecur(ArrayList<ArrayList<Integer>> board) {
        int row = 0;
        int col = 0;
        boolean fondUnfilled = false;
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (board.get(i).get(j) == 0) {
                    row = i;
                    col = j;
                    fondUnfilled = true;
                    break;
                }
            }
            if (fondUnfilled) {
                break;
            }
        }
        if (!fondUnfilled) {
            return true;
        }

        for (int num = 1; num <= 9; num++) {
            if (isSafe(board, row, col, num)) {
                board.get(row).set(col, num);

                if (solveSudokuRecur(board)) {
                    return true;
                } else {
                    board.get(row).set(col, 0);
                }
            }
        }
        return false;
    }

    static boolean isSafe(ArrayList<ArrayList<Integer>> board, int row, int col, int num) {

        for (int i = 0; i < 9; i++) {
            if (board.get(i).get(col) == num) {
                return false;
            }
        }

        for (int i = 0; i < 9; i++) {
            if (board.get(row).get(i) == num) {
                return false;
            }
        }

        int rowStart = row - (row % 3);
        int colStart = col - (col % 3);

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board.get(i + rowStart).get(j + colStart) == num) {
                    return false;
                }
            }
        }
        return true;
    }
}
