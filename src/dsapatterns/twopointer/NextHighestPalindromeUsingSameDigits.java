package dsapatterns.twopointer;

/**
 * =====================================================================================
 * NEXT HIGHER PALINDROMIC NUMBER USING SAME DIGITS - Two Pointer Approach
 * =====================================================================================
 * GeeksforGeeks: https://www.geeksforgeeks.org/dsa/next-higher-palindromic-number-using-set-digits/
 *
 * PROBLEM: Given a palindromic number, find the next higher palindrome using the same digits.
 *          If not possible, return empty string.
 *
 * APPROACH: Similar to "Next Permutation" but applied to first half of palindrome,
 *           then mirror changes to second half.
 *
 * =====================================================================================
 * ALGORITHM:
 * =====================================================================================
 * 1. Work only with the FIRST HALF of the palindrome (excluding middle for odd length)
 * 2. Find rightmost digit in first half where nums[i] < nums[i+1] (break point)
 * 3. Find the SMALLEST digit greater than nums[i] in range [i+1, mid]
 * 4. Swap nums[i] with that smallest digit
 * 5. MIRROR the swap in the second half (to maintain palindrome property)
 * 6. Reverse the remaining portion of first half (from i+1 to mid)
 * 7. Reverse the corresponding portion of second half
 *
 * =====================================================================================
 * 100% CODE COVERAGE EXAMPLE: "14322341" (8-digit even-length palindrome)
 * =====================================================================================
 * 
 * This example covers ALL code paths:
 *   ✓ n > 3 check                    - 8 > 3, proceeds
 *   ✓ for loop to find i             - Runs 3 iterations (i=2,1,0)
 *   ✓ i >= 0 (valid break point)     - i=0 found
 *   ✓ for loop to find smallest      - Runs 2 iterations (j=2,3), updates smallest twice
 *   ✓ First swap (i and smallest)    - Swap positions 0 and 3
 *   ✓ Mirror swap                    - Swap positions 7 and 4
 *   ✓ First reverse                  - Reverse positions 1 to 3
 *   ✓ n%2==0 branch (even length)    - Reverse positions 4 to 6
 *
 * For ODD-LENGTH coverage, also test: "1432341" (7-digit palindrome)
 *   ✓ n%2!=0 branch (odd length)     - Reverse positions 4 to 5
 *
 * =====================================================================================
 * STEP-BY-STEP TRACE FOR "14322341":
 * =====================================================================================
 *
 * INITIAL STATE:
 * ┌─────────────────────────────────────────────────────────────────────────┐
 * │  Array:  [1] [4] [3] [2] [2] [3] [4] [1]                                │
 * │  Index:   0   1   2   3   4   5   6   7                                 │
 * │                                                                         │
 * │  n = 8, mid = n/2 - 1 = 3                                               │
 * │  First Half: [1][4][3][2]  (indices 0-3)                                │
 * │  Second Half: [2][3][4][1] (indices 4-7, mirror of first half)          │
 * └─────────────────────────────────────────────────────────────────────────┘
 *
 * ─────────────────────────────────────────────────────────────────────────────────────
 * STEP 1: Find break point (rightmost i where nums[i] < nums[i+1])
 * ─────────────────────────────────────────────────────────────────────────────────────
 *   Searching from mid-1 = 2 down to 0:
 *
 *   ┌─────┬─────────────────────────────────────────────┐
 *   │  i  │  nums[i] < nums[i+1]?                       │
 *   ├─────┼─────────────────────────────────────────────┤
 *   │  2  │  '3' < '2'?  NO  (3 > 2)  → continue        │
 *   │  1  │  '4' < '3'?  NO  (4 > 3)  → continue        │
 *   │  0  │  '1' < '4'?  YES! ✓       → break, i = 0    │
 *   └─────┴─────────────────────────────────────────────┘
 *
 *   Result: i = 0 (break point found at index 0)
 *
 * ─────────────────────────────────────────────────────────────────────────────────────
 * STEP 2: Find smallest digit greater than nums[i] in range [i+1, mid]
 * ─────────────────────────────────────────────────────────────────────────────────────
 *   i = 0, nums[i] = '1'
 *   Initial smallest = i + 1 = 1 (nums[1] = '4')
 *   Search range: j from i+2=2 to mid=3
 *
 *   ┌─────┬───────────────────────────────────────────────────────────────┐
 *   │  j  │  Condition Check                                             │
 *   ├─────┼───────────────────────────────────────────────────────────────┤
 *   │  2  │  nums[2]='3' > nums[0]='1'? YES                              │
 *   │     │  nums[2]='3' <= nums[smallest=1]='4'? YES                    │
 *   │     │  → smallest = 2                                               │
 *   ├─────┼───────────────────────────────────────────────────────────────┤
 *   │  3  │  nums[3]='2' > nums[0]='1'? YES                              │
 *   │     │  nums[3]='2' <= nums[smallest=2]='3'? YES                    │
 *   │     │  → smallest = 3                                               │
 *   └─────┴───────────────────────────────────────────────────────────────┘
 *
 *   Result: smallest = 3 (the smallest digit '2' greater than '1')
 *
 * ─────────────────────────────────────────────────────────────────────────────────────
 * STEP 3: Swap nums[i] with nums[smallest] in first half
 * ─────────────────────────────────────────────────────────────────────────────────────
 *   Swap positions 0 and 3:
 *
 *   Before: [1] [4] [3] [2] [2] [3] [4] [1]
 *            ↑           ↑
 *          i=0      smallest=3
 *
 *   After:  [2] [4] [3] [1] [2] [3] [4] [1]
 *            ↑           ↑
 *          '2'         '1'  (swapped)
 *
 * ─────────────────────────────────────────────────────────────────────────────────────
 * STEP 4: Mirror the swap in second half (to maintain palindrome property)
 * ─────────────────────────────────────────────────────────────────────────────────────
 *   Mirror positions: n-i-1 = 7, n-smallest-1 = 4
 *   Swap positions 7 and 4:
 *
 *   Before: [2] [4] [3] [1] [2] [3] [4] [1]
 *                          ↑           ↑
 *                        4=n-3-1    7=n-0-1
 *
 *   After:  [2] [4] [3] [1] [1] [3] [4] [2]
 *                          ↑           ↑
 *                        '1'         '2'  (swapped)
 *
 * ─────────────────────────────────────────────────────────────────────────────────────
 * STEP 5: Reverse first half from i+1 to mid
 * ─────────────────────────────────────────────────────────────────────────────────────
 *   Reverse positions 1 to 3:
 *
 *   Before: [2] [4] [3] [1] [1] [3] [4] [2]
 *               └───────┘
 *              [4] [3] [1]
 *
 *   After:  [2] [1] [3] [4] [1] [3] [4] [2]
 *               └───────┘
 *              [1] [3] [4]  (reversed)
 *
 * ─────────────────────────────────────────────────────────────────────────────────────
 * STEP 6: Reverse second half (n%2==0, even length)
 * ─────────────────────────────────────────────────────────────────────────────────────
 *   Since n=8 is EVEN, use: reverse(mid+1, n-i-2) = reverse(4, 6)
 *
 *   Before: [2] [1] [3] [4] [1] [3] [4] [2]
 *                          └───────┘
 *                         [1] [3] [4]
 *
 *   After:  [2] [1] [3] [4] [4] [3] [1] [2]
 *                          └───────┘
 *                         [4] [3] [1]  (reversed)
 *
 * ─────────────────────────────────────────────────────────────────────────────────────
 * FINAL RESULT:
 * ─────────────────────────────────────────────────────────────────────────────────────
 *
 *   Original:  14322341
 *   Result:    21344312  ✓
 *
 *   Verification:
 *   ├─ Is 21344312 a palindrome?  2-1-3-4-4-3-1-2 → YES ✓
 *   └─ Is 21344312 > 14322341?    21344312 > 14322341 → YES ✓
 *
 * =====================================================================================
 * VISUAL TRACE SUMMARY:
 * =====================================================================================
 *
 *   Step 0: [1][4][3][2][2][3][4][1]   Original palindrome
 *            ↑                 ↑
 *          i=0            smallest=3  (after search)
 *
 *   Step 1: [2][4][3][1][2][3][4][1]   After first swap (0,3)
 *            ↑     ↑
 *
 *   Step 2: [2][4][3][1][1][3][4][2]   After mirror swap (7,4)
 *                       ↑     ↑
 *
 *   Step 3: [2][1][3][4][1][3][4][2]   After reverse(1,3)
 *               └─────┘
 *
 *   Step 4: [2][1][3][4][4][3][1][2]   After reverse(4,6) - FINAL!
 *                       └─────┘
 *
 *           2 1 3 4 4 3 1 2  ← PALINDROME! 🎉
 *
 * =====================================================================================
 * ODD-LENGTH EXAMPLE: "1432341" (7-digit palindrome)
 * =====================================================================================
 *
 *   Original:  [1][4][3][2][3][4][1]
 *   n=7, mid=2, middle element at index 3 (not touched)
 *
 *   Step 1: Find i → i=0 (same as above)
 *   Step 2: Find smallest → smallest=2 (nums[2]='3')
 *   Step 3: Swap(0,2):    [3][4][1][2][3][4][1]
 *   Step 4: Mirror(6,4):  [3][4][1][2][1][4][3]
 *   Step 5: Reverse(1,2): [3][1][4][2][1][4][3]
 *   Step 6: n%2!=0, reverse(mid+2=4, n-i-2=5): [3][1][4][2][4][1][3]
 *
 *   Result: 3142413 (palindrome, > 1432341) ✓
 *
 * =====================================================================================
 * CODE PATH COVERAGE TABLE:
 * =====================================================================================
 *
 *   | Code Section                          | Covered By      | When             |
 *   |---------------------------------------|-----------------|------------------|
 *   | if(n<=3) return ""                    | Edge case tests | "121", "12"      |
 *   | for(i=mid-1; i>=0; i--)               | "14322341"      | i=2,1,0          |
 *   | if(nums[i]<nums[i+1]) break           | "14322341"      | i=0              |
 *   | if(i<0) return ""                     | "9779", "4321"  | Decreasing order |
 *   | for(j=i+2; j<=mid; j++) find smallest | "14322341"      | j=2,3            |
 *   | if(nums[j]>nums[i] && <=smallest)     | "14322341"      | Updates twice    |
 *   | Swap first half (i, smallest)         | Both examples   | Step 3           |
 *   | Swap second half (mirror)             | Both examples   | Step 4           |
 *   | reverse(i+1, mid)                     | Both examples   | Step 5           |
 *   | if(n%2==0) reverse even               | "14322341"      | Step 6 (even)    |
 *   | else reverse odd                      | "1432341"       | Step 6 (odd)     |
 *
 * =====================================================================================
 * EDGE CASES:
 * =====================================================================================
 *
 *   | Input      | Output | Reason                                         |
 *   |------------|--------|------------------------------------------------|
 *   | "121"      | ""     | n <= 3, too short to rearrange                 |
 *   | "12"       | ""     | n <= 3, too short                              |
 *   | "9779"     | ""     | First half "97" is decreasing, no next exists  |
 *   | "4321234"  | ""     | First half "432" is decreasing                 |
 *   | "12321"    | "21312"| Simple case, works                             |
 *
 * =====================================================================================
 * COMPLEXITY ANALYSIS:
 * =====================================================================================
 *   Time:  O(n) - Linear scan to find i, linear scan to find smallest, O(n) reverse
 *   Space: O(n) - Converting string to char array
 *
 * =====================================================================================
 * WHY THIS ALGORITHM WORKS:
 * =====================================================================================
 *
 *   For a palindrome, the first half determines the entire number.
 *   
 *   To get the NEXT higher palindrome:
 *   1. Apply "next permutation" logic to the first half only
 *   2. Mirror the changes to maintain palindrome property
 *   
 *   The key insight is that changes to first half must be reflected in second half.
 *   
 *   Example: 14322341
 *            ────┬───
 *            First Half = 1432
 *            Next permutation of first half = 2134 (conceptually)
 *            But we must maintain palindrome = 21344312
 *
 * =====================================================================================
 */
public class NextHighestPalindromeUsingSameDigits {
    public static void main(String[] args) {
        // Primary test case for 100% coverage (even length)
        System.out.println("=== 100% COVERAGE TEST CASES ===\n");
        
        // Test 1: Even length - covers n%2==0 branch
        String test1 = "14322341";
        System.out.println("Input:  " + test1);
        System.out.println("Output: " + findNextPalindrome(test1));
        System.out.println("Expected: 21344312\n");
        
        // Test 2: Odd length - covers n%2!=0 branch (else)
        String test2 = "1432341";
        System.out.println("Input:  " + test2);
        System.out.println("Output: " + findNextPalindrome(test2));
        System.out.println("Expected: 3142413\n");
        
        // Test 3: Simple case
        String test3 = "12321";
        System.out.println("Input:  " + test3);
        System.out.println("Output: " + findNextPalindrome(test3));
        System.out.println("Expected: 21312\n");
        
        // Edge cases
        System.out.println("=== EDGE CASES ===\n");
        
        // Edge case 1: n <= 3
        String edge1 = "121";
        System.out.println("Input:  " + edge1 + " (n<=3)");
        System.out.println("Output: \"" + findNextPalindrome(edge1) + "\"");
        System.out.println("Expected: \"\" (too short)\n");
        
        // Edge case 2: Decreasing order - no next palindrome exists
        String edge2 = "9779";
        System.out.println("Input:  " + edge2 + " (decreasing first half)");
        System.out.println("Output: \"" + findNextPalindrome(edge2) + "\"");
        System.out.println("Expected: \"\" (no higher palindrome)\n");
    }
    
    /**
     * Main algorithm: Find next higher palindrome using same digits.
     * 
     * @param numStr The input palindromic number as a string
     * @return The next higher palindrome, or empty string if not possible
     */
    public static String findNextPalindrome(String numStr) {
        char[] nums = numStr.toCharArray();
        int n = nums.length;
        
        // Edge case: Too short to rearrange
        if (n <= 3) {
            return "";
        }
        
        int i, j;
        int mid = n / 2 - 1;  // Last index of first half (excluding middle for odd)
        
        // STEP 1: Find the rightmost position in first half where nums[i] < nums[i+1]
        // This is the "break point" - similar to next permutation algorithm
        for (i = mid - 1; i >= 0; i--) {
            if (nums[i] < nums[i + 1]) {
                break;  // Found break point
            }
        }
        
        // If no break point found, first half is in decreasing order
        // No higher palindrome possible with same digits
        if (i < 0) {
            return "";
        }
        
        // STEP 2: Find the smallest digit greater than nums[i] in range [i+1, mid]
        int smallest = i + 1;
        for (j = i + 2; j <= mid; j++) {
            if (nums[j] > nums[i] && nums[j] <= nums[smallest]) {
                smallest = j;
            }
        }
        
        // STEP 3: Swap in first half
        char temp = nums[i];
        nums[i] = nums[smallest];
        nums[smallest] = temp;
        
        // STEP 4: Mirror the swap in second half (to maintain palindrome)
        temp = nums[n - i - 1];
        nums[n - i - 1] = nums[n - smallest - 1];
        nums[n - smallest - 1] = temp;
        
        // STEP 5: Reverse remaining portion of first half
        reverse(nums, i + 1, mid);
        
        // STEP 6: Reverse corresponding portion of second half
        if (n % 2 == 0) {
            // Even length: mirror positions directly
            reverse(nums, mid + 1, n - i - 2);
        } else {
            // Odd length: skip the middle element
            reverse(nums, mid + 2, n - i - 2);
        }
        
        return new String(nums);
    }

    /**
     * Helper method to reverse a portion of the array.
     * 
     * @param nums The character array
     * @param i Start index (inclusive)
     * @param j End index (inclusive)
     */
    private static void reverse(char[] nums, int i, int j) {
        while (i < j) {
            char temp = nums[i];
            nums[i++] = nums[j];
            nums[j--] = temp;
        }
    }
}
