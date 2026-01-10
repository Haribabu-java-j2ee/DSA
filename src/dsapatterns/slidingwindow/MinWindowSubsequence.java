package dsapatterns.slidingwindow;

import java.util.*;

/**
 * =====================================================================================
 * MINIMUM WINDOW SUBSEQUENCE - Two-Pointer with Forward-Backward Scan
 * =====================================================================================
 * LeetCode: https://leetcode.com/problems/minimum-window-subsequence/ (Premium)
 * Educative: https://www.educative.io/interview-prep/coding/solution-minimum-window-subsequence
 * Difficulty: Hard
 *
 * =====================================================================================
 * PROBLEM:
 * =====================================================================================
 * Given strings str1 and str2, find the minimum contiguous substring of str1 such that
 * str2 is a SUBSEQUENCE of this substring.
 *
 * A subsequence maintains relative order but doesn't need to be contiguous.
 * Example: "bde" is a subsequence of "abcdebdde" (a[b]c[d][e]bdde)
 *
 * If no such window exists, return empty string "".
 * If multiple windows have the same minimum length, return the one with the leftmost start.
 *
 * =====================================================================================
 * KEY DIFFERENCE: Subsequence vs Substring
 * =====================================================================================
 *
 *   str1 = "abcdebdde", str2 = "bde"
 *
 *   Subsequence: Characters appear in ORDER, but NOT necessarily CONTIGUOUS
 *   ─────────────────────────────────────────────────────────────────────────────
 *   a b c d e b d d e
 *     ↑   ↑ ↑           "bde" found at positions 1,3,4 → window "bcde"
 *           ↑   ↑   ↑   "bde" found at positions 5,6,8 → window "bdde"
 *
 *   Substring: Characters are CONTIGUOUS (no gaps allowed)
 *   ─────────────────────────────────────────────────────────────────────────────
 *   "bde" as substring? NOT found in "abcdebdde"
 *
 * =====================================================================================
 * INTUITION:
 * =====================================================================================
 *
 *   1. FORWARD PASS: Find a complete subsequence match
 *      - Scan str1 left to right, matching characters of str2 in order
 *      - When all characters of str2 are matched, we have the END of a window
 *
 *   2. BACKWARD PASS: Minimize the window
 *      - From the END, scan backwards to find the shortest window
 *      - This finds the rightmost possible START for this particular END
 *
 *   3. REPEAT: Continue from START+1 to find potentially shorter windows
 *
 *   Why backward pass helps:
 *   ─────────────────────────────────────────────────────────────────────────────
 *   str1 = "abcbde", str2 = "bd"
 *
 *   Forward finds: a[b]c[b][d]e → match ends at 'd' (index 4)
 *   Window from start: "abcbd" (length 5)
 *
 *   Backward from 'd' finds: abc[b][d]e → 'b' at index 3, 'd' at index 4
 *   Minimized window: "bd" (length 2) ← Much shorter!
 *
 * =====================================================================================
 * ALGORITHM STEPS:
 * =====================================================================================
 *
 *   1. Initialize: indexS1 = 0, indexS2 = 0, minSubsequence = ""
 *
 *   2. FORWARD SCAN (find subsequence):
 *      While indexS1 < str1.length:
 *        - If str1[indexS1] == str2[indexS2]: move indexS2 forward
 *        - If indexS2 == str2.length: found complete match! → Go to backward scan
 *        - Move indexS1 forward
 *
 *   3. BACKWARD SCAN (minimize window):
 *      - Set end = indexS1 (last matched position)
 *      - Set start = indexS1
 *      - While indexS2 >= 0:
 *          - If str1[start] == str2[indexS2]: move indexS2 backward
 *          - Move start backward
 *      - Adjust start += 1 (went one too far)
 *
 *   4. UPDATE RESULT:
 *      - If (end - start + 1) < minLength: update minSubsequence
 *
 *   5. RESET & CONTINUE:
 *      - Set indexS1 = start + 1, indexS2 = 0
 *      - Continue forward scan to find next potential window
 *
 * =====================================================================================
 * EXAMPLE 1: str1 = "abcdebdde", str2 = "bde"
 * =====================================================================================
 *
 *   Index:     0   1   2   3   4   5   6   7   8
 *   str1:      a   b   c   d   e   b   d   d   e
 *   str2:      b   d   e
 *
 *   ─────────────────────────────────────────────────────────────────────────────────
 *   FORWARD PASS 1: Find first subsequence match
 *   ─────────────────────────────────────────────────────────────────────────────────
 *
 *   indexS1 │ str1[i] │ indexS2 │ str2[j] │ Match? │ Action
 *   ────────┼─────────┼─────────┼─────────┼────────┼──────────────────────
 *      0    │   'a'   │    0    │   'b'   │   NO   │ indexS1++
 *      1    │   'b'   │    0    │   'b'   │  YES   │ indexS2++, indexS1++
 *      2    │   'c'   │    1    │   'd'   │   NO   │ indexS1++
 *      3    │   'd'   │    1    │   'd'   │  YES   │ indexS2++, indexS1++
 *      4    │   'e'   │    2    │   'e'   │  YES   │ indexS2++ → indexS2=3=len(str2)
 *           │         │         │         │        │ ★ COMPLETE MATCH! Go backward
 *
 *   ─────────────────────────────────────────────────────────────────────────────────
 *   BACKWARD PASS 1: Minimize window ending at index 4
 *   ─────────────────────────────────────────────────────────────────────────────────
 *
 *   end = 4, start = 4, indexS2 = 2
 *
 *   start │ str1[s] │ indexS2 │ str2[j] │ Match? │ Action
 *   ──────┼─────────┼─────────┼─────────┼────────┼──────────────────────
 *     4   │   'e'   │    2    │   'e'   │  YES   │ indexS2--, start--
 *     3   │   'd'   │    1    │   'd'   │  YES   │ indexS2--, start--
 *     2   │   'c'   │    0    │   'b'   │   NO   │ start--
 *     1   │   'b'   │    0    │   'b'   │  YES   │ indexS2-- → indexS2=-1, start--
 *     0   │  (exit) │   -1    │    -    │    -   │ Loop exits
 *
 *   start = 0 + 1 = 1
 *   Window: str1[1..4] = "bcde", length = 4
 *   minSubsequence = "bcde" ✓
 *
 *   Reset: indexS1 = 1 + 1 = 2, indexS2 = 0
 *
 *   ─────────────────────────────────────────────────────────────────────────────────
 *   FORWARD PASS 2: Find next subsequence match (starting from index 2)
 *   ─────────────────────────────────────────────────────────────────────────────────
 *
 *   indexS1 │ str1[i] │ indexS2 │ str2[j] │ Match? │ Action
 *   ────────┼─────────┼─────────┼─────────┼────────┼──────────────────────
 *      2    │   'c'   │    0    │   'b'   │   NO   │ indexS1++
 *      3    │   'd'   │    0    │   'b'   │   NO   │ indexS1++
 *      4    │   'e'   │    0    │   'b'   │   NO   │ indexS1++
 *      5    │   'b'   │    0    │   'b'   │  YES   │ indexS2++, indexS1++
 *      6    │   'd'   │    1    │   'd'   │  YES   │ indexS2++, indexS1++
 *      7    │   'd'   │    2    │   'e'   │   NO   │ indexS1++
 *      8    │   'e'   │    2    │   'e'   │  YES   │ indexS2++ → indexS2=3=len(str2)
 *           │         │         │         │        │ ★ COMPLETE MATCH! Go backward
 *
 *   ─────────────────────────────────────────────────────────────────────────────────
 *   BACKWARD PASS 2: Minimize window ending at index 8
 *   ─────────────────────────────────────────────────────────────────────────────────
 *
 *   end = 8, start = 8, indexS2 = 2
 *
 *   start │ str1[s] │ indexS2 │ str2[j] │ Match? │ Action
 *   ──────┼─────────┼─────────┼─────────┼────────┼──────────────────────
 *     8   │   'e'   │    2    │   'e'   │  YES   │ indexS2--, start--
 *     7   │   'd'   │    1    │   'd'   │  YES   │ indexS2--, start--
 *     6   │   'd'   │    0    │   'b'   │   NO   │ start--
 *     5   │   'b'   │    0    │   'b'   │  YES   │ indexS2-- → indexS2=-1, start--
 *     4   │  (exit) │   -1    │    -    │    -   │ Loop exits
 *
 *   start = 4 + 1 = 5
 *   Window: str1[5..8] = "bdde", length = 4
 *   4 < 4? NO → minSubsequence unchanged = "bcde"
 *
 *   Reset: indexS1 = 5 + 1 = 6, indexS2 = 0
 *
 *   ─────────────────────────────────────────────────────────────────────────────────
 *   FORWARD PASS 3: (starting from index 6)
 *   ─────────────────────────────────────────────────────────────────────────────────
 *
 *   indexS1 │ str1[i] │ indexS2 │ str2[j] │ Match?
 *   ────────┼─────────┼─────────┼─────────┼────────
 *      6    │   'd'   │    0    │   'b'   │   NO   │ indexS1++
 *      7    │   'd'   │    0    │   'b'   │   NO   │ indexS1++
 *      8    │   'e'   │    0    │   'b'   │   NO   │ indexS1++
 *      9    │  (end)  │    -    │    -    │    -   │ Exit loop
 *
 *   No more complete matches found.
 *
 *   ─────────────────────────────────────────────────────────────────────────────────
 *   FINAL RESULT: "bcde"
 *   ─────────────────────────────────────────────────────────────────────────────────
 *
 * =====================================================================================
 * EXAMPLE 2: str1 = "fgrqsqsnodwmxzkzxwqegkndaa", str2 = "kzed"
 * =====================================================================================
 *
 *   str1: f g r q s q s n o d w m x z k z x w q e g k n d a a
 *   Index:0 1 2 3 4 5 6 7 8 9 ...
 *
 *   Forward finds: ...x[z][k]z[x]w[q][e]g[k]n[d]...
 *                        ↑     Wait, let's trace properly
 *
 *   str2 = "k z e d"
 *
 *   Match 1: k(14), z(15), e(19), d(22) → Window: str1[14..22] = "kzxwqegknd" (9 chars)
 *   Backward: Find shortest... might find k(21) closer to d(22)
 *
 *   (Detailed trace would show finding minimum window)
 *
 * =====================================================================================
 * VISUAL: FORWARD AND BACKWARD PASSES
 * =====================================================================================
 *
 *   str1 = "abcdebdde", str2 = "bde"
 *
 *   FORWARD (finding "bde"):
 *   ┌───────────────────────────────────────────────────────────────────────────┐
 *   │  a   b   c   d   e   b   d   d   e                                        │
 *   │      →       →   →                    (arrows show matching direction)    │
 *   │      b       d   e                    (characters being matched)          │
 *   │              end=4                                                        │
 *   └───────────────────────────────────────────────────────────────────────────┘
 *
 *   BACKWARD (minimizing window):
 *   ┌───────────────────────────────────────────────────────────────────────────┐
 *   │  a   b   c   d   e   b   d   d   e                                        │
 *   │      ←   ←   ←   ←                    (arrows show backward scan)         │
 *   │      b       d   e                    (finding closest matches)           │
 *   │   start=1    end=4                                                        │
 *   │      └───────┘                                                            │
 *   │      Window: "bcde"                                                       │
 *   └───────────────────────────────────────────────────────────────────────────┘
 *
 * =====================================================================================
 * WHY BACKWARD PASS IS NECESSARY:
 * =====================================================================================
 *
 *   str1 = "axxxbxxxxbde", str2 = "bde"
 *
 *   Forward finds first 'b' at index 1:
 *   a[x][x][x][b]xxxxbde → Matches b(1), then d(9), e(10)
 *   Window: indices 1-10 = "xxxbxxxxbd e" (length 10)
 *
 *   Backward from index 10:
 *   axxxbxxxx[b][d][e] → Finds b(8), d(9), e(10)
 *   Minimized window: indices 8-10 = "bde" (length 3)
 *
 *   Without backward pass, we'd return "xxxbxxxxbde" instead of "bde"!
 *
 * =====================================================================================
 * COMPLEXITY:
 * =====================================================================================
 *
 *   Let:
 *     n = length of str1
 *     m = length of str2
 *
 *   ─────────────────────────────────────────────────────────────────────────────────
 *   TIME COMPLEXITY: O(n × m)
 *   ─────────────────────────────────────────────────────────────────────────────────
 *
 *   Analysis:
 *   ┌─────────────────────────────────────────────────────────────────────────────┐
 *   │ Pass                  │ Complexity         │ Explanation                    │
 *   ├─────────────────────────────────────────────────────────────────────────────┤
 *   │ Each forward pass     │ O(n)               │ Scans portion of str1          │
 *   │ Each backward pass    │ O(m + window)      │ At most m matches + extra      │
 *   │ Number of windows     │ O(n/m) worst case  │ Each window covers ~m chars    │
 *   │ Total                 │ O(n × m)           │ Worst case with many matches   │
 *   └─────────────────────────────────────────────────────────────────────────────┘
 *
 *   Worst case example: str1 = "aaaa...a" (n times), str2 = "aa"
 *   - Many overlapping matches
 *   - Each backward pass scans backward to find 'a's
 *
 *   Best case: O(n + m) when no match exists or single match
 *
 *   Average case: O(n × m) with a small constant factor
 *
 *   ─────────────────────────────────────────────────────────────────────────────────
 *   SPACE COMPLEXITY: O(1)
 *   ─────────────────────────────────────────────────────────────────────────────────
 *
 *   ┌─────────────────────────────────────────────────────────────────────────────┐
 *   │ Variable              │ Space              │ Notes                          │
 *   ├─────────────────────────────────────────────────────────────────────────────┤
 *   │ indexS1, indexS2      │ O(1)               │ Pointers                       │
 *   │ start, end            │ O(1)               │ Window boundaries              │
 *   │ length                │ O(1)               │ Current min length             │
 *   │ minSubsequence        │ O(min window)      │ Result string                  │
 *   └─────────────────────────────────────────────────────────────────────────────┘
 *
 *   No extra arrays or data structures needed!
 *   This is a major advantage over the DP approach which uses O(n × m) space.
 *
 * =====================================================================================
 * COMPARISON: TWO-POINTER vs DYNAMIC PROGRAMMING
 * =====================================================================================
 *
 *   ┌─────────────────────────────────────────────────────────────────────────────┐
 *   │ Aspect                │ Two-Pointer (this) │ Dynamic Programming            │
 *   ├─────────────────────────────────────────────────────────────────────────────┤
 *   │ Time Complexity       │ O(n × m)           │ O(n × m)                       │
 *   │ Space Complexity      │ O(1) ✅            │ O(n × m) ❌                    │
 *   │ Implementation        │ Moderate           │ More complex                   │
 *   │ Cache efficiency      │ Good               │ Poor (large 2D array)          │
 *   │ Early termination     │ Possible           │ Must fill entire table         │
 *   └─────────────────────────────────────────────────────────────────────────────┘
 *
 *   VERDICT: Two-pointer is preferred due to O(1) space!
 *
 * =====================================================================================
 * EDGE CASES:
 * =====================================================================================
 *
 *   1. str2 not a subsequence of str1 → return ""
 *      Example: str1 = "abc", str2 = "xyz"
 *
 *   2. str2 is empty → return "" (or could be interpreted as any empty substring)
 *
 *   3. str1 is empty → return ""
 *
 *   4. str2 longer than str1 → return ""
 *
 *   5. Perfect match (str1 == str2) → return str1
 *
 *   6. Single character str2 → return first occurrence of that character
 *
 *   7. Multiple windows of same length → return leftmost (this algo naturally does)
 *
 *   8. Entire str1 is the minimum window → return str1
 *
 * =====================================================================================
 * ALTERNATIVE APPROACHES:
 * =====================================================================================
 *
 *   1. Dynamic Programming: O(n × m) time, O(n × m) space
 *      - dp[i][j] = start index of subsequence matching str2[0..j-1] ending at str1[i-1]
 *      - More space, but easier to understand
 *
 *   2. Binary Search + Preprocessing: O(n + m log n) time, O(n) space
 *      - Build index lists for each character in str1
 *      - Binary search for next valid character position
 *      - Better for very long str2
 *
 *   3. Sliding Window (invalid for this problem)
 *      - Doesn't work because we need SUBSEQUENCE, not all characters in window
 */
public class MinWindowSubsequence {
    
    public static void main(String[] args) {
        String string1 = "abcd";
        System.out.println(string1.substring(0, 4)); // "bcd"
        MinWindowSubsequence obj = new MinWindowSubsequence();
        
        System.out.println("╔══════════════════════════════════════════════════════════════════════════╗");
        System.out.println("║          MINIMUM WINDOW SUBSEQUENCE - THREE APPROACHES                   ║");
        System.out.println("╚══════════════════════════════════════════════════════════════════════════╝\n");
        
        // Test cases with all three approaches
        String[][] testCases = {
            {"abcdebdde", "bde", "bcde"},
            {"abcbde", "bd", "bd"},
            {"abc", "xyz", ""},
            {"abcde", "c", "c"},
            {"abc", "abc", "abc"},
            {"ab", "abc", ""},
            {"aaaaaab", "ab", "ab"},
            {"xxxbde", "bde", "bde"},
            {"bdexxx", "bde", "bde"},
            {"axxxbxxxxbde", "bde", "bde"}
        };
        
        System.out.println("┌──────────────────────────────────────────────────────────────────────────┐");
        System.out.println("│  Testing all three approaches with the same inputs                       │");
        System.out.println("└──────────────────────────────────────────────────────────────────────────┘\n");
        
        int testNum = 1;
        for (String[] test : testCases) {
            String str1 = test[0];
            String str2 = test[1];
            String expected = test[2];
            
            String result2 = obj.minWindowOptimized(str1, str2);


            System.out.println("  Input:    str1=\"" + str1 + "\", str2=\"" + str2 + "\"");
            System.out.println("  Expected: \"" + expected + "\"");
            System.out.println("  Approach 2 (Optimized):    \"" + result2 + "\"");
            System.out.println();
            testNum++;
        }
        
        System.out.println("┌──────────────────────────────────────────────────────────────────────────┐");
        System.out.println("│  Performance comparison (large input)                                    │");
        System.out.println("└──────────────────────────────────────────────────────────────────────────┘\n");
        
        // Generate large test case
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 10000; i++) {
            sb.append("abcdefghij");
        }
        String largeStr1 = sb.toString();  // 100,000 characters
        String str2 = "aej";               // Sparse pattern
        
        System.out.println("Large input: str1.length=" + largeStr1.length() + ", str2=\"" + str2 + "\"");
        

        // Time Approach 2
        long start2 = System.nanoTime();
        String r2 = obj.minWindowOptimized(largeStr1, str2);
        long time2 = System.nanoTime() - start2;
        

        System.out.println("\nResults (all should be same):");
        System.out.println("  Approach 2 (Optimized):    \"" + r2 + "\" - " + (time2/1_000_000.0) + " ms");

        System.out.println("\n╔══════════════════════════════════════════════════════════════════════════╗");
        System.out.println("║                        ALL TESTS COMPLETE                                ║");
        System.out.println("╚══════════════════════════════════════════════════════════════════════════╝");
    }
    

    /**
     * =====================================================================================
     * APPROACH 2: OPTIMIZED TWO-POINTER (All Minor + Moderate Optimizations)
     * =====================================================================================
     *
     * Optimizations applied:
     * 1. Use int instead of float for minLength
     * 2. Store indices instead of substring (create substring only once at end)
     * 3. Early termination when window length equals str2 length (can't be shorter)
     *
     * Time:  O(n × m) - Same as original
     * Space: O(1) - Same as original, but fewer String object creations
     */
    public String minWindowOptimized(String str1, String str2) {
        int n = str1.length();
        int m = str2.length();
        
        // =========================================================================
        // EDGE CASES
        // =========================================================================
        if (m > n) return "";
        if (m == 0) return "";
        
        // =========================================================================
        // OPTIMIZATION 1: Use int instead of float
        // =========================================================================
        int minLength = n + 1;  // Any valid window must be <= n
        
        // =========================================================================
        // OPTIMIZATION 2: Store indices, not substring
        // =========================================================================
        int resultStart = -1;
        int resultEnd = -1;
        
        int indexS1 = 0;
        int indexS2 = 0;
        
        while (indexS1 < n) {
            // Forward pass: find matching character
            if (str1.charAt(indexS1) == str2.charAt(indexS2)) {
                indexS2++;
                
                // Complete match found
                if (indexS2 == m) {
                    int end = indexS1;
                    int start = indexS1;
                    indexS2--;
                    
                    // Backward pass: minimize window
                    while (indexS2 >= 0) {
                        if (str1.charAt(start) == str2.charAt(indexS2)) {
                            indexS2--;
                        }
                        start--;
                    }
                    start++;
                    
                    // Update minimum if current window is smaller
                    int windowLen = end - start + 1;
                    if (windowLen < minLength) {
                        minLength = windowLen;
                        resultStart = start;
                        resultEnd = end;
                        
                        // =============================================================
                        // OPTIMIZATION 3: Early termination
                        // =============================================================
                        // If window length equals str2 length, we found the minimum possible!
                        // str2 must appear contiguously in str1, can't get any shorter
                        if (minLength == m) {
                            return str1.substring(resultStart, resultEnd + 1);
                        }
                    }
                    
                    // Reset for next search
                    indexS1 = start;
                    indexS2 = 0;
                }
            }
            indexS1++;
        }
        
        // =========================================================================
        // OPTIMIZATION 2: Create substring only once at the end
        // =========================================================================
        return resultStart == -1 ? "" : str1.substring(resultStart, resultEnd + 1);
    }

}
