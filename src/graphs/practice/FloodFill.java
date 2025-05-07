package graphs.practice;

import java.util.ArrayList;
import java.util.Arrays;

public class FloodFill {
    public static void main(String[] args) {
        int[][] grid = {{5, 6, 3, 1, 2, 1}};
        ArrayList<ArrayList<Integer>> image = new ArrayList<>();
        Arrays.stream(grid).forEach(row -> {
            ArrayList<Integer> colors = new ArrayList<>();
            Arrays.stream(row).forEach(colors::add);
            image.add(colors);
        });
        flood_fill(0, 3, 1000, image);
        image.forEach(System.out::println);
    }


    static ArrayList<ArrayList<Integer>> flood_fill(Integer pixel_row, Integer pixel_column, Integer new_color, ArrayList<ArrayList<Integer>> image) {
        // Write your code here.
        int currentColor = image.get(pixel_row).get(pixel_column);
        if (image.get(pixel_row).get(pixel_column) != new_color) {
            changeColor(pixel_row, pixel_column, currentColor, new_color, image);
        }
        return image;
    }

    static void changeColor(int i, int j, int currentColor, int newColor, ArrayList<ArrayList<Integer>> image) {
        if (i < 0 || j < 0 || i >= image.size() || j >= image.get(i).size() || !image.get(i).get(j).equals(currentColor)) {
            return;
        }
        image.get(i).set(j, newColor);
        changeColor(i + 1, j, currentColor, newColor, image);

        changeColor(i - 1, j, currentColor, newColor, image);

        changeColor(i, j + 1, currentColor, newColor, image);
        changeColor(i, j - 1, currentColor, newColor, image);
    }


}
