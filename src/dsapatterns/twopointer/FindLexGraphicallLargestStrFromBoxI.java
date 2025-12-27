package dsapatterns.twopointer;

/**
 * =====================================================================================
 * FIND LEXICOGRAPHICALLY LARGEST STRING FROM BOX I - Two Pointer / Greedy
 * =====================================================================================
 * LeetCode: https://leetcode.com/problems/find-the-lexicographically-largest-string-from-the-box-i/
 *
 * =====================================================================================
 * INTUITION:
 * =====================================================================================
 * Split string into k parts, find largest substring. Max substring length is
 * n - (k-1) (one char for each other part). For each starting position,
 * find max allowed substring and compare lexicographically.
 *
 * =====================================================================================
 * EXAMPLE: word="dbca", numFriends=2 → "dbc"
 * =====================================================================================
 *
 *   n=4, k=2, maxLen = 4 - (2-1) = 3
 *
 *   i | maxEndLength      | substring | Compare with max
 *   --|-------------------|-----------|------------------
 *   0 | min(4, 0+3) = 3   | "dbc"     | "" < "dbc" → max="dbc"
 *   1 | min(4, 1+3) = 4   | "bca"     | "dbc" > "bca" → keep
 *   2 | min(4, 2+3) = 4   | "ca"      | "dbc" > "ca" → keep
 *   3 | min(4, 3+3) = 4   | "a"       | "dbc" > "a" → keep
 *
 *   Result: "dbc"
 *
 * =====================================================================================
 * COMPLEXITY:
 * =====================================================================================
 *   Time:  O(n² × m) where m = max substring length - substring comparison is O(m)
 *   Space: O(n) - For storing substrings
 *
 * =====================================================================================
 * EDGE CASES:
 * =====================================================================================
 *   1. numFriends = 1 → return entire string
 *   2. numFriends = n → each friend gets 1 char, return max char
 *   3. All same characters → any substring works
 *
 * =====================================================================================
 * OPTIMIZATION NOTE: This is O(n²) brute force. For better performance, see
 * FindLexGraphicallLargestStrFromBoxII which uses optimized two-pointer comparison.
 * =====================================================================================
 */
public class FindLexGraphicallLargestStrFromBoxI {
    public static void main(String[] args) {
        String word = "dbca";
        int numFriends = 2;
        FindLexGraphicallLargestStrFromBoxI obj = new FindLexGraphicallLargestStrFromBoxI();
        String ans = obj.answerString(word, numFriends);
        System.out.println("Lexicographically largest string: " + ans);
    }
    
    public String answerString(String word, int numFriends) {
        if (numFriends == 1) {
            return word;
        }
        
        int wordLength = word.length();
        String maxSubstring = "";
        
        for (int i = 0; i < wordLength; i++) {
            // Maximum end position considering k-1 chars for other friends
            int maxEndLength = Math.min(wordLength, i + (wordLength - (numFriends - 1)));
            String currentSubstring = word.substring(i, maxEndLength);
            
            if (maxSubstring.compareTo(currentSubstring) < 0) {
                maxSubstring = currentSubstring;
            }
        }
        
        return maxSubstring;
    }
}
