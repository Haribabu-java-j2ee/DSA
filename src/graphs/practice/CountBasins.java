package graphs.practice;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;

public class CountBasins {
    public static void main(String[] args) {
        /*int[][] grid = {{3, 3, 4},
                {2, 1, 3},
                {2, 3, 0}};*/

        int[][] grid = {
                {1, 5, 2},
                {2, 4, 7},
                {3, 6, 9}
        };

        /*int[][] grid = {
                {0, 2, 1, 3},
                {2, 1, 0, 4},
                {3, 3, 3, 3},
                {5, 5, 2, 1}
        };*/

       /* int[][] grid = {
                {3, 4, 4},
                {2, 1, 3},
                {2, 3, 0}
        };*/

        ArrayList<ArrayList<Integer>> heightList = new ArrayList<>();
        Arrays.stream(grid).forEach(arr -> {
            ArrayList<Integer> list = new ArrayList<>();
            Arrays.stream(arr).forEach(list::add);
            heightList.add(list);
        });
        System.out.println(find_basins(heightList));
    }
    /*
     * Asymptotic complexity in terms of the total number of regions in `matrix`(total_rows * total_columns) `n`::
     * Time: O(n ^ 2).
     * Auxiliary space: O(n).
     * Total space: O(n).
     */


    static void count_sort(ArrayList<Integer> arr) {
        int max = Collections.max(arr);
        int min = Collections.min(arr);
        int range = max - min + 1;

        ArrayList<Integer> count = new ArrayList<>(Collections.nCopies(range, 0));
        ArrayList<Integer> output = new ArrayList<>(Collections.nCopies(arr.size(), 0));

        for (int i = 0; i < arr.size(); i++) {
            count.set(arr.get(i) - min, count.get(arr.get(i) - min) + 1);
        }

        for (int i = 1; i < range; i++) {
            count.set(i, count.get(i) + count.get(i - 1));
        }

        for (int i = arr.size() - 1; i >= 0; i--) {
            output.set(count.get(arr.get(i) - min) - 1, arr.get(i));
            count.set(arr.get(i) - min, count.get(arr.get(i) - min) - 1);
        }

        for (int i = 0; i < arr.size(); i++) {
            arr.set(i, output.get(i));
        }
    }

    static int get_sink(ArrayList<ArrayList<Integer>> matrix, ArrayList<ArrayList<Integer>> basins, int row, int column,int[][] priorBasins) {

        if(priorBasins[row][column]!=-1){
            return priorBasins[row][column];
        }
        int min_row = row, min_column = column;
        // Check element which is at left
        if (column > 0 && matrix.get(row).get(column - 1) < matrix.get(min_row).get(min_column)) {
            min_column = column - 1;
            min_row = row;
        }
        // Check element which is at up.
        if (row > 0 && matrix.get(row - 1).get(column) < matrix.get(min_row).get(min_column)) {
            min_column = column;
            min_row = row - 1;
        }
        // Check element which is at down.
        if (row < matrix.size() - 1 && matrix.get(row + 1).get(column) < matrix.get(min_row).get(min_column)) {
            min_column = column;
            min_row = row + 1;
        }
        // Check element which is at right.
        if (column < matrix.get(0).size() - 1 && matrix.get(row).get(column + 1) < matrix.get(min_row).get(min_column)) {
            min_column = column + 1;
            min_row = row;
        }

        if (row == min_row && column == min_column) {
            // If we reached at sink.
            return priorBasins[row][column]=basins.get(min_row).get(min_column);
        }

        // Recursively call to get sink for element matrix[min_row][min_col].
        return priorBasins[row][column]=get_sink(matrix, basins, min_row, min_column,priorBasins);
    }

    static ArrayList<Integer> find_basins(ArrayList<ArrayList<Integer>> matrix) {

        int row_count = matrix.size();
        int column_count = matrix.get(0).size();
        int[][] priorBasins=new int[row_count][column_count];
        for(int i=0;i<row_count;i++){
            Arrays.fill(priorBasins[i],-1);
        }
        // To maintain ids of each element of matrix.
        ArrayList<ArrayList<Integer>> basins = new ArrayList<>();
        for (int i = 0; i < row_count; i++) {
            basins.add(new ArrayList<>());
        }

        int index = 0;

        for (int i = 0; i < row_count; i++) {
            for (int j = 0; j < column_count; j++) {
                basins.get(i).add(index++);
            }
        }

        // To maintain id wise count of elements which will sink in that id.
        HashMap<Integer, Integer> basin_indexes = new HashMap<>();

        for (int i = 0; i < row_count; i++) {
            for (int j = 0; j < column_count; j++) {
                int sink = get_sink(matrix, basins, i, j,priorBasins);
                basin_indexes.put(sink, basin_indexes.getOrDefault(sink, 0) + 1);
            }
        }

        ArrayList<Integer> basin_sizes = new ArrayList<>();

        for (int pair : basin_indexes.keySet()) {
            basin_sizes.add(basin_indexes.get(pair));
        }

        // Sorting by size of sinks.
        count_sort(basin_sizes);

        return basin_sizes;
    }

}
