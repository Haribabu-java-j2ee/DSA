package dsapatterns.twopointer;

/**
 * =====================================================================================
 * STROBOGRAMMATIC NUMBER - Two Pointer Approach
 * =====================================================================================
 * Educative: https://www.educative.io/interview-prep/coding/strobogrammatic-number
 *
 * =====================================================================================
 * INTUITION:
 * =====================================================================================
 * A strobogrammatic number looks the same when rotated 180°. Valid pairs: (0,0),
 * (1,1), (6,9), (8,8), (9,6). Use two pointers to verify each pair matches.
 *
 * =====================================================================================
 * EXAMPLE: "69" → true (6 rotated = 9, 9 rotated = 6)
 * =====================================================================================
 *
 *   Rotation mapping: 0→0, 1→1, 6→9, 8→8, 9→6
 *   Invalid digits: 2,3,4,5,7 (marked as -1)
 *
 *   Check "69":
 *   Step | left | right | digit[l] | digit[r] | rotation[l] == r?
 *   -----|------|-------|----------|----------|-------------------
 *     1  |  0   |   1   |    6     |    9     | rotation[6]=9 ✓
 *
 *   Result: true (69 rotated 180° is 69)
 *
 *   Check "68":
 *   Step | left | right | digit[l] | digit[r] | rotation[l] == r?
 *   -----|------|-------|----------|----------|-------------------
 *     1  |  0   |   1   |    6     |    8     | rotation[6]=9 ≠ 8 ✗
 *
 *   Result: false
 *
 * =====================================================================================
 * COMPLEXITY:
 * =====================================================================================
 *   Time:  O(n) - Single pass with two pointers
 *   Space: O(1) - Fixed 10-element mapping array
 *
 * =====================================================================================
 * EDGE CASES:
 * =====================================================================================
 *   1. Single digit → only 0, 1, 8 are valid
 *   2. Contains 2,3,4,5,7 → always false (no valid rotation)
 *   3. Odd length with middle digit → middle must be 0, 1, or 8
 *   4. Empty string → typically true (vacuously)
 *
 * =====================================================================================
 * OPTIMIZATION NOTE: Code is optimal. Using array for mapping is faster than HashMap.
 * =====================================================================================
 */
public class StrobogramaticNumber {
    public static void main(String[] args) {
        String num = "69";
        boolean ans = isStrobogrammatic(num);
        System.out.println("Is strobogrammatic number: " + ans);
    }
    
    public static boolean isStrobogrammatic(String num) {
        // Mapping: index = digit, value = rotated digit (-1 = invalid)
        int[] rotationMapping = new int[]{0, 1, -1, -1, -1, -1, 9, -1, 8, 6};
        
        int left = 0;
        int right = num.length() - 1;
        
        while (left <= right) {
            int leftDigit = num.charAt(left) - '0';
            int rightDigit = num.charAt(right) - '0';

            if (rotationMapping[leftDigit] != rightDigit) {
                return false;
            }
            
            left++;
            right--;
        }
        
        return true;
    }
}
