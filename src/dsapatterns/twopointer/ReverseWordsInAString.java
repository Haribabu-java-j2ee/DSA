package dsapatterns.twopointer;

/**
 * =====================================================================================
 * REVERSE WORDS IN A STRING - Two Pointer Approach
 * =====================================================================================
 * LeetCode: https://leetcode.com/problems/reverse-words-in-a-string/
 *
 * =====================================================================================
 * INTUITION:
 * =====================================================================================
 * Method 1 (Split): Split by spaces, reverse word array using two pointers, join.
 * Method 2 (In-place): Reverse entire string, then reverse each word individually.
 * Method 2 is more optimal for interviews (O(1) extra space).
 *
 * =====================================================================================
 * EXAMPLE: "  hello world  " → "world hello"
 * =====================================================================================
 *
 * Method 1 (Split approach):
 *   words = ["hello", "world"]
 *   Reverse: ["world", "hello"]
 *   Join: "world hello"
 *
 * Method 2 (In-place approach):
 *   Step 1: Reverse entire string
 *           "  hello world  " → "  dlrow olleh  "
 *   Step 2: Process each word, reverse it, compact spaces
 *           "world hello"
 *
 * =====================================================================================
 * COMPLEXITY:
 * =====================================================================================
 *   reverseWords (split method):
 *     Time:  O(n)
 *     Space: O(n) - String array for words
 *
 *   reverseWords1 (in-place method):
 *     Time:  O(n)
 *     Space: O(n) - char array (could be O(1) in languages with mutable strings)
 *
 * =====================================================================================
 * EDGE CASES:
 * =====================================================================================
 *   1. Leading/trailing spaces → must be trimmed
 *   2. Multiple spaces between words → reduce to single space
 *   3. Single word → return as-is
 *   4. Empty string → return empty
 *
 * =====================================================================================
 * OPTIMIZATION NOTE: reverseWords1 is more interview-friendly as it demonstrates
 * in-place manipulation. In languages with mutable strings, it achieves true O(1) space.
 * =====================================================================================
 */
public class ReverseWordsInAString {
    public static void main(String[] args) {
        String sentence = "  hello world  ";
        ReverseWordsInAString obj = new ReverseWordsInAString();
        String reversedSentence = obj.reverseWords(sentence);
        System.out.println(reversedSentence);
        System.out.println(obj.reverseWords1(sentence));
    }
    
    /**
     * Method 1: Split, reverse array, join
     */
    public String reverseWords(String sentence) {
        String[] words = sentence.trim().split("\\s+");
        int left = 0;
        int right = words.length - 1;
        
        while (left < right) {
            swap(words, left, right);
            left++;
            right--;
        }

        return String.join(" ", words);
    }
    
    private void swap(String[] words, int i, int j) {
        String temp = words[i];
        words[i] = words[j];
        words[j] = temp;
    }

    /**
     * Method 2: In-place - reverse all, then reverse each word
     */
    public String reverseWords1(String s) {
        int n = s.length();
        char[] charArr = s.toCharArray();
        
        // Step 1: Reverse entire string
        reverse(charArr, 0, n - 1);
        
        int i = 0;      // Position to write next character
        int right = 0;
        
        for (int left = 0; left < n; ++left) {
            if (charArr[left] != ' ') {
                // Add space before word (except first word)
                if (i != 0) {
                    charArr[i++] = ' ';
                }
                
                // Copy word and track its start
                right = left;
                while (right < n && charArr[right] != ' ') {
                    charArr[i++] = charArr[right++];
                }
                
                // Reverse the word we just copied
                reverse(charArr, i - (right - left), i - 1);
                left = right;
            }
        }
        
        return new String(charArr, 0, i);
    }
    
    private void reverse(char[] charArr, int left, int right) {
        while (left < right) {
            char temp = charArr[left];
            charArr[left++] = charArr[right];
            charArr[right--] = temp;
        }
    }
}
