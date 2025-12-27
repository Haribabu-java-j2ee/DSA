package dsapatterns.twopointer;

/**
 * =====================================================================================
 * VALID WORD ABBREVIATION - Two Pointer Approach
 * =====================================================================================
 * Educative: https://www.educative.io/interview-prep/coding/valid-word-abbreviation
 *
 * =====================================================================================
 * INTUITION:
 * =====================================================================================
 * Use two pointers for word and abbreviation. If abbr char is digit, parse full number
 * and skip that many chars in word. If letter, compare directly. Watch for leading zeros.
 *
 * =====================================================================================
 * EXAMPLE: word="internationalization", abbr="i12iz4n" → true
 * =====================================================================================
 *
 *   wIdx | aIdx | abbr[aIdx] | Action                    | wIdx after
 *   -----|------|------------|---------------------------|----------
 *    0   |  0   |    'i'     | 'i'='i' ✓, both++         | 1
 *    1   |  1   |    '1'     | Parse "12", wIdx += 12    | 13
 *   13   |  3   |    'i'     | 'i'='i' ✓, both++         | 14
 *   14   |  4   |    'z'     | 'z'='z' ✓, both++         | 15
 *   15   |  5   |    '4'     | Parse "4", wIdx += 4      | 19
 *   19   |  6   |    'n'     | 'n'='n' ✓, both++         | 20
 *
 *   Both exhausted → return true
 *
 *   word:  i n t e r n a t i o n a l i z a t i o n
 *   index: 0 1 2 3 4 5 6 7 8 9 ...               19
 *          ↑                         ↑     ↑       ↑
 *          i         (12 chars)      i z   (4)     n
 *
 * =====================================================================================
 * COMPLEXITY:
 * =====================================================================================
 *   Time:  O(n) - Single pass through abbreviation
 *   Space: O(1) - Only pointer and counter variables
 *
 * =====================================================================================
 * EDGE CASES:
 * =====================================================================================
 *   1. Leading zero in number (e.g., "a01b") → INVALID (must reject)
 *   2. Number larger than remaining word → INVALID
 *   3. Empty abbreviation with empty word → valid
 *   4. Abbreviation is just a number → must equal word length
 *
 * =====================================================================================
 * OPTIMIZATION NOTE: Code is optimal. The leading zero check is CRITICAL for
 * interview - missing it is an instant rejection.
 * =====================================================================================
 */
public class ValidWordAbbrevation {
    public static void main(String[] args) {
        String word = "internationalization";
        String abbr = "i12iz4n";
        System.out.println(validWordAbbreviation(word, abbr));
    }

    public static boolean validWordAbbreviation(String word, String abbr) {
        int wordIndex = 0, abbrIndex = 0;
        
        while (abbrIndex < abbr.length()) {
            if (Character.isDigit(abbr.charAt(abbrIndex))) {
                // CRITICAL: Leading zeros are invalid
                if (abbr.charAt(abbrIndex) == '0') {
                    return false;
                }
                
                // Parse the complete number
                int num = 0;
                while (abbrIndex < abbr.length() && Character.isDigit(abbr.charAt(abbrIndex))) {
                    num = num * 10 + (abbr.charAt(abbrIndex) - '0');
                    abbrIndex++;
                }
                
                wordIndex += num;
            } else {
                // Character must match
                if (wordIndex >= word.length() || word.charAt(wordIndex) != abbr.charAt(abbrIndex)) {
                    return false;
                }
                wordIndex++;
                abbrIndex++;
            }
        }
        
        return wordIndex == word.length() && abbrIndex == abbr.length();
    }
}
