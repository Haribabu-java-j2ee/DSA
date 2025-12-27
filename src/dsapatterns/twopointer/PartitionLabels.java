package dsapatterns.twopointer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * =====================================================================================
 * PARTITION LABELS - Two Pointer / Greedy Approach
 * =====================================================================================
 * LeetCode: https://leetcode.com/problems/partition-labels/
 *
 * =====================================================================================
 * INTUITION:
 * =====================================================================================
 * Precompute last occurrence of each character. Greedily expand partition to include
 * all occurrences of seen characters. When current index equals partition end,
 * close partition and start new one.
 *
 * =====================================================================================
 * EXAMPLE: "ababcbacadefegdehijhklij" → [9,7,8]
 * =====================================================================================
 *
 *   Last occurrence: a=8, b=5, c=7, d=14, e=15, f=11, g=13, h=19, i=22, j=23, k=20, l=21
 *
 *   Step | i | char | end  | Partition? | partitions
 *   -----|---|------|------|------------|------------
 *     0  | 0 |  a   |  8   |            |
 *     1  | 1 |  b   |  8   |            |
 *     2  | 2 |  a   |  8   |            |
 *     3  | 3 |  b   |  8   |            |
 *     4  | 4 |  c   |  8   |            |
 *     5  | 5 |  b   |  8   |            |
 *     6  | 6 |  a   |  8   |            |
 *     7  | 7 |  c   |  8   |            |
 *     8  | 8 |  a   |  8   | i=end ✓    | [9]
 *     9  | 9 |  d   | 14   |            |
 *    ... continues...
 *    15  |15 |  e   | 15   | i=end ✓    | [9,7]
 *    ...
 *    23  |23 |  j   | 23   | i=end ✓    | [9,7,8]
 *
 *   Result: [9,7,8]
 *
 * =====================================================================================
 * COMPLEXITY:
 * =====================================================================================
 *   Time:  O(n) - Two passes (last occurrence + partition finding)
 *   Space: O(1) - Fixed 26-char array
 *
 * =====================================================================================
 * EDGE CASES:
 * =====================================================================================
 *   1. Single character → [1]
 *   2. All same characters → [n]
 *   3. All unique characters → [1,1,1,...,1]
 *
 * =====================================================================================
 * OPTIMIZATION NOTE: Code is optimal. O(n) time with O(1) space (26 is constant).
 * =====================================================================================
 */
public class PartitionLabels {
    public static void main(String[] args) {
        String s = "ababcbacadefegdehijhklij";
        PartitionLabels obj = new PartitionLabels();
        System.out.println(obj.partitionLabels(s));
    }
    
    public List<Integer> partitionLabels(String s) {
        int[] lastIndexChar = new int[26];
        int end = -1;
        List<Integer> result = new ArrayList<>();
        
        Arrays.fill(lastIndexChar, -1);
        
        // First pass: record last occurrence of each character
        for (int i = 0; i < s.length(); i++) {
            lastIndexChar[s.charAt(i) - 'a'] = i;
        }
        
        int j = 0; // Start of current partition
        
        // Second pass: find partitions
        for (int i = 0; i < s.length(); i++) {
            end = Math.max(end, lastIndexChar[s.charAt(i) - 'a']);
            
            if (end == i) {
                result.add(i + 1 - j);
                j = i + 1;
            }
        }
        
        return result;
    }
}
