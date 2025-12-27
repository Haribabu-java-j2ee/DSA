package dsapatterns.twopointer;

/**
 * =====================================================================================
 * SORT COLORS (DUTCH NATIONAL FLAG) - Three Pointer Approach
 * =====================================================================================
 * LeetCode: https://leetcode.com/problems/sort-colors/
 *
 * =====================================================================================
 * INTUITION:
 * =====================================================================================
 * Partition array into three regions: [0s | 1s | unsorted | 2s].
 * Use three pointers: redIndex (end of 0s), i (current), blueIndex (start of 2s).
 * Swap 0s to left, 2s to right, 1s stay in middle.
 *
 * =====================================================================================
 * EXAMPLE: [2,0,2,1,1,0] → [0,0,1,1,2,2]
 * =====================================================================================
 *
 *   Step | Array         | i | red | blue | Action
 *   -----|---------------|---|-----|------|---------------------------
 *   Init | [2,0,2,1,1,0] | 0 |  0  |  5   | colors[0]=2, swap with blue
 *     1  | [0,0,2,1,1,2] | 0 |  0  |  4   | colors[0]=0, swap with red, i++
 *     2  | [0,0,2,1,1,2] | 1 |  1  |  4   | colors[1]=0, swap with red, i++
 *     3  | [0,0,2,1,1,2] | 2 |  2  |  4   | colors[2]=2, swap with blue
 *     4  | [0,0,1,1,2,2] | 2 |  2  |  3   | colors[2]=1, i++
 *     5  | [0,0,1,1,2,2] | 3 |  2  |  3   | colors[3]=1, i++
 *     6  | i > blue, STOP
 *
 *   Result: [0,0,1,1,2,2]
 *
 * =====================================================================================
 * COMPLEXITY:
 * =====================================================================================
 *   Time:  O(n) - Single pass through array
 *   Space: O(1) - In-place sorting
 *
 * =====================================================================================
 * EDGE CASES:
 * =====================================================================================
 *   1. Empty array → no-op
 *   2. Single element → already sorted
 *   3. All same color → no swaps needed
 *   4. Already sorted → algorithm still runs in O(n)
 *
 * =====================================================================================
 * OPTIMIZATION NOTE: This is the optimal one-pass solution. 
 * Note: When swapping with blueIndex, don't increment i (swapped element needs check).
 * =====================================================================================
 */
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
                // Note: Don't increment i here - need to check swapped element
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
