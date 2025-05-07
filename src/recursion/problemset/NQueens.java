package recursion.problemset;

import java.util.ArrayList;
import java.util.Arrays;

public class NQueens {
    public static void main(String[] args) {
        int n = 4;
        //findAllSolutions(n).forEach(System.out::println);
        findAllArrangements(n).forEach(System.out::println);
    }

    private static final char queen = 'q';
    private static final char no_queen = '-';


    static ArrayList<ArrayList<String>> findAllSolutions(int n) {
        ArrayList<ArrayList<String>> solutions = new ArrayList<>();
        StringBuilder candidate = new StringBuilder();
        ArrayList<StringBuilder> candidates = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            candidate.append(no_queen);
        }

        for (int i = 0; i < n; i++) {
            candidates.add(new StringBuilder(candidate));
        }

        findAllSolutionsUtil(solutions, candidates, n, 0);
        return solutions;
    }

    static void findAllSolutionsUtil(ArrayList<ArrayList<String>> solutions, ArrayList<StringBuilder> candidates, int n, int row) {

        if (row == n) {
            ArrayList<String> solution = new ArrayList<>();
            for (StringBuilder sb : candidates) {
                solution.add(sb.toString());
            }
            solutions.add(solution);
            return;
        }

        for (int col = 0; col < n; col++) {
            if (isSafe(candidates, row, col, n)) {
                candidates.get(row).setCharAt(col, queen);
                findAllSolutionsUtil(solutions, candidates, n, row + 1);
                candidates.get(row).setCharAt(col, no_queen);
            }
        }

    }

    static boolean isSafe(ArrayList<StringBuilder> candidates, int row, int col, int n) {

        int currRow = row;
        int curCol = col;

        //backslash
        while (currRow >= 0 && curCol >= 0) {
            if (candidates.get(currRow).charAt(curCol) == queen) {
                return false;
            }
            currRow--;
            curCol--;
        }
        currRow = row;
        while (currRow >= 0) {
            if (candidates.get(currRow).charAt(col) == queen) {
                return false;
            }
            currRow--;
        }

        currRow = row;
        curCol = col;
        while (currRow >= 0 && curCol < n) {
            if (candidates.get(currRow).charAt(curCol) == queen) {
                return false;
            }
            curCol++;
            currRow--;
        }
        return true;
    }

    //more optimised solution
    static ArrayList<ArrayList<String>> findAllArrangements(int n) {
        ArrayList<ArrayList<String>> solutions = new ArrayList<>();

        char[][] chessBoard = new char[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                chessBoard[i][j] = no_queen;
            }
        }

        boolean[] colOccupied = new boolean[n];
        Arrays.fill(colOccupied, false);
        boolean[] slashDiagnol = new boolean[2 * n - 1];
        Arrays.fill(slashDiagnol, false);
        boolean[] backSlashDiagnol = new boolean[2 * n - 1];
        Arrays.fill(backSlashDiagnol, false);

        findAllArrangementsUtil(chessBoard, 0, n, solutions, colOccupied, slashDiagnol, backSlashDiagnol);
        return solutions;
    }

    static void findAllArrangementsUtil(char[][] candidate, int row, int n, ArrayList<ArrayList<String>> solutions, boolean[] col_occupied, boolean[] slash_diagonal_occupied, boolean[] backslash_diagonal_occupied) {
        if (row == n) {
            ArrayList<String> solution = new ArrayList<>();
            for (char[] c : candidate) {
                solution.add(new String(c));
            }
            solutions.add(solution);
            return;
        }

        for (int col = 0; col < n; col++) {
            if (isSafe2(row, col, col_occupied, slash_diagonal_occupied, backslash_diagonal_occupied, n)) {
                candidate[row][col] = queen;
                col_occupied[col] = true;
                slash_diagonal_occupied[row + col] = true;
                backslash_diagonal_occupied[row - col + n - 1] = true;
                findAllArrangementsUtil(candidate, row + 1, n, solutions, col_occupied, slash_diagonal_occupied, backslash_diagonal_occupied);
                candidate[row][col] = no_queen;
                col_occupied[col] = false;
                slash_diagonal_occupied[row + col] = false;
                backslash_diagonal_occupied[row - col + n - 1] = false;
            }
        }
    }

    static boolean isSafe2(int row, int col, boolean[] col_occupied, boolean[] slash_diagonal_occupied, boolean[] backslash_diagonal_occupied, int n) {
        return !(col_occupied[col] || slash_diagonal_occupied[row + col] || backslash_diagonal_occupied[row - col + n - 1]);
    }
}
