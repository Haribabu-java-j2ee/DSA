package dsapatterns.twopointer;

//https://leetcode.com/problems/sort-colors
public class SortColors {
    public static void main(String[] args) {
        int[] colors = new int[]{2, 0, 2, 1, 1, 0};
        SortColors obj = new SortColors();
        int[] sortedColors = obj.sortColors(colors);
        for (int color : sortedColors) {
            System.out.print(color + " ");
        }
    }

    public int[] sortColors(int[] colors) {
        int i = 0;
        int blueIndex = colors.length - 1;
        int redIndex = 0;
        while (i <= blueIndex) {
            if (colors[i] == 0) {
                swap(colors, i, redIndex);
                redIndex++;
                i++;
            } else if (colors[i] == 1) {
                i++;
            } else {
                swap(colors, i, blueIndex);
                blueIndex--;
            }
        }
        return colors;
    }

    private void swap(int[] colors, int i, int j) {
        int temp = colors[i];
        colors[i] = colors[j];
        colors[j] = temp;
    }
}
