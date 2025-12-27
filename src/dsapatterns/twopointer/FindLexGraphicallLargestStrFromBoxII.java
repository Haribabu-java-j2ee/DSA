package dsapatterns.twopointer;

/**
 * =====================================================================================
 * FIND LEXICOGRAPHICALLY LARGEST STRING FROM BOX II - Optimized Two Pointer
 * =====================================================================================
 * AlgoMonster: https://algo.monster/liteproblems/3406
 * Educative: https://www.educative.io/interview-prep/coding/find-the-lexicographically-largest-string-from-box-ii
 *
 * =====================================================================================
 * INTUITION:
 * =====================================================================================
 * Instead of comparing all substrings (O(n²)), use two pointers to find the
 * lexicographically largest starting position efficiently. Compare character by
 * character, skip redundant comparisons when a better start is found.
 *
 * =====================================================================================
 * EXAMPLE: word="abaaaaaaa", numFriends=3 → "baaaaaaa"
 * =====================================================================================
 *
 *   n=9, k=3, maxLen = 9 - 3 + 1 = 7
 *
 *   i=0, j=1:
 *     k=0: 'a' == 'b'? No, 'a' < 'b' → i becomes j=1, j moves to max(2, 0+1)=2
 *
 *   i=1, j=2:
 *     k=0: 'b' == 'a'? No, 'b' > 'a' → j = 2+0+1 = 3
 *
 *   i=1, j=3:
 *     k=0: 'b' == 'a'? No, 'b' > 'a' → j = 3+0+1 = 4
 *     ...continues until j >= n
 *
 *   Best starting position: i=1
 *   Substring from 1 with length 7: "baaaaaaa"
 *
 *   Result: "baaaaaaa"
 *
 * =====================================================================================
 * COMPLEXITY:
 * =====================================================================================
 *   Time:  O(n) - Each character compared at most twice
 *   Space: O(1) - Only pointer variables (plus O(n) for result substring)
 *
 * =====================================================================================
 * EDGE CASES:
 * =====================================================================================
 *   1. numFriends = 1 → return entire string
 *   2. All characters same → return first n-k+1 characters
 *   3. Descending order → first char wins
 *
 * =====================================================================================
 * OPTIMIZATION NOTE: This is the optimal O(n) solution compared to O(n²) brute force.
 * The key insight is using two-pointer comparison to skip redundant checks.
 * =====================================================================================
 */
public class FindLexGraphicallLargestStrFromBoxII {
    public static void main(String[] args) {
        FindLexGraphicallLargestStrFromBoxII obj = new FindLexGraphicallLargestStrFromBoxII();
        String word = "abaaaaaaa";
        int numFriends = 3;
        String result = obj.answerString(word, numFriends);
        System.out.println("The lexicographically largest substring is: " + result);
    }

    public String answerString(String word, int numFriends) {
        // If there's only one friend, return the entire string
        if (numFriends == 1) {
            return word;
        }

        int n = word.length();
        int i = 0, j = 1;  // i: current best start, j: candidate start

        while (j < n) {
            int k = 0;
            
            // Compare characters at i+k and j+k while they're equal
            while (j + k < n && word.charAt(i + k) == word.charAt(j + k)) {
                k++;
            }

            // If candidate at j is lexicographically larger, switch
            if (j + k < n && word.charAt(i + k) < word.charAt(j + k)) {
                int tempIndex = i;
                i = j;
                j = Math.max(j + 1, tempIndex + k + 1);
            } else {
                // Otherwise, skip the compared section
                j = j + k + 1;
            }
        }

        // Return substring of required length: n - numFriends + 1
        return word.substring(i, Math.min(n, i + n - numFriends + 1));
    }
}
