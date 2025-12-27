package dsapatterns.twopointer;

/**
 * =====================================================================================
 * STRING COMPRESSION - Two Pointer Approach
 * =====================================================================================
 * LeetCode: https://leetcode.com/problems/string-compression/
 *
 * =====================================================================================
 * INTUITION:
 * =====================================================================================
 * Use read pointer to count consecutive chars, write pointer to place compressed result.
 * For each group: write char, then count (if > 1). Count digits written separately.
 *
 * =====================================================================================
 * EXAMPLE: ['a','a','b','b','c','c','c'] → ['a','2','b','2','c','3'], return 6
 * =====================================================================================
 *
 *   Read | Char | Count | Write Position | Array State
 *   -----|------|-------|----------------|---------------------------
 *    0-1 |  a   |   2   | 0,1            | [a,2,b,b,c,c,c]
 *    2-3 |  b   |   2   | 2,3            | [a,2,b,2,c,c,c]
 *    4-6 |  c   |   3   | 4,5            | [a,2,b,2,c,3,c]
 *
 *   Result: length = 6, array = [a,2,b,2,c,3,...]
 *
 * =====================================================================================
 * COMPLEXITY:
 * =====================================================================================
 *   Time:  O(n) - Single pass through array
 *   Space: O(1) - In-place compression (O(log count) for count digits conversion)
 *
 * =====================================================================================
 * EDGE CASES:
 * =====================================================================================
 *   1. Single character → no count written (just 'a', not 'a1')
 *   2. Count > 9 → multi-digit count ('a','1','2' for 12 a's)
 *   3. All same characters → single char + count
 *   4. All unique characters → same as input
 *
 * =====================================================================================
 * OPTIMIZATION NOTE: Code is correct. The String.valueOf(count).toCharArray() creates
 * temporary strings. Alternative: manually extract digits using % 10 and / 10.
 * However, this is minor for interview purposes.
 * =====================================================================================
 */
public class StringCompression {
    public static void main(String[] args) {
        char[] chars = new char[]{'a','a','b','b','c','c','c'};
        StringCompression obj = new StringCompression();
        int len = obj.compress(chars);
        System.out.println("Length after compression: " + len);
        System.out.println();
        for (int i = 0; i < len; i++) {
            System.out.print(chars[i] + " ");
        }
    }
    
    public int compress(char[] chars) {
        int ans = 0;  // Write pointer
        int n = chars.length;
        
        for (int i = 0; i < n; ) {
            char letter = chars[i];
            int count = 0;
            
            // Count consecutive occurrences
            while (i < n && letter == chars[i]) {
                count++;
                i++;
            }
            
            // Write character
            chars[ans++] = letter;
            
            // Write count (only if > 1)
            if (count > 1) {
                for (char ch : String.valueOf(count).toCharArray()) {
                    chars[ans++] = ch;
                }
            }
        }
        
        return ans;
    }
}
