package dsapatterns.slidingwindow;

import java.util.*;

/**
 * =====================================================================================
 * SUBSTRING WITH CONCATENATION OF ALL WORDS - Sliding Window with Word-Level Movement
 * =====================================================================================
 * LeetCode: https://leetcode.com/problems/substring-with-concatenation-of-all-words/
 * Difficulty: Hard
 *
 * =====================================================================================
 * PROBLEM:
 * =====================================================================================
 * Given a string s and an array of strings words (all words are same length),
 * find all starting indices of substrings in s that are a concatenation of each
 * word in words exactly once, in any order, without any intervening characters.
 *
 * =====================================================================================
 * INTUITION:
 * =====================================================================================
 * 1. All words have the SAME length → we can treat the string as blocks of wordLen
 * 2. Use sliding window at WORD level (not character level)
 * 3. Key insight: We need to try wordLen different starting positions!
 *    - Start at 0: process positions 0, wordLen, 2*wordLen, ...
 *    - Start at 1: process positions 1, wordLen+1, 2*wordLen+1, ...
 *    - ...
 *    - Start at wordLen-1: covers all remaining positions
 *
 * 4. For each starting position:
 *    - Expand window by adding words from the right
 *    - If word count exceeds required → shrink from left
 *    - If window size = totalLen → found valid concatenation!
 *
 * =====================================================================================
 * WHY wordLen DIFFERENT STARTING POSITIONS?
 * =====================================================================================
 *
 *   Example: s = "barfoothefoobarman", wordLen = 3
 *
 *   Start i=0: |bar|foo|the|foo|bar|man|     ← Aligned at positions 0,3,6,9,12,15
 *   Start i=1:  b|arf|oot|hef|oob|arm|an     ← Aligned at positions 1,4,7,10,13,16
 *   Start i=2:  ba|rfo|oth|efo|oba|rma|n     ← Aligned at positions 2,5,8,11,14,17
 *
 *   Each starting position creates a different word alignment!
 *   We must check ALL alignments to find all valid substrings.
 *
 * =====================================================================================
 * ALGORITHM STEPS:
 * =====================================================================================
 *
 *   1. Build wordCount map: count frequency of each word in words[]
 *   2. For each starting offset i from 0 to wordLen-1:
 *      a. Initialize sliding window: left=i, right=i, seen={}
 *      b. While right + wordLen <= s.length():
 *         - Extract word at [right, right+wordLen)
 *         - Move right by wordLen
 *         - If word is in wordCount:
 *             * Add to seen map
 *             * While seen[word] > wordCount[word]: shrink from left
 *             * If window size == totalLen: add left to result
 *         - Else (invalid word):
 *             * Clear seen map
 *             * Move left to right (reset window)
 *
 * =====================================================================================
 * EXAMPLE 1: s = "barfoothefoobarman", words = ["foo", "bar"]
 * =====================================================================================
 *
 *   wordLen = 3, numWords = 2, totalLen = 6
 *   wordCount = {"foo": 1, "bar": 1}
 *
 *   Starting offset i = 0:
 *   ─────────────────────────────────────────────────────────────────────────────────
 *   String blocks: |bar|foo|the|foo|bar|man|
 *   Positions:      0   3   6   9   12  15
 *
 *   right | word  | seen              | left | windowLen | action
 *   ------|-------|-------------------|------|-----------|---------------------------
 *     0   | "bar" | {bar:1}           |  0   |     3     | valid word, add to seen
 *     3   | "foo" | {bar:1, foo:1}    |  0   |     6     | windowLen=6=totalLen ✓
 *         |       |                   |      |           | → ADD 0 TO RESULT
 *     6   | "the" | -                 |  9   |     0     | invalid word, reset
 *     9   | "foo" | {foo:1}           |  9   |     3     | valid word
 *    12   | "bar" | {foo:1, bar:1}    |  9   |     6     | windowLen=6=totalLen ✓
 *         |       |                   |      |           | → ADD 9 TO RESULT
 *    15   | "man" | -                 | 18   |     0     | invalid word, reset
 *
 *   Result from i=0: [0, 9]
 *
 *   Starting offset i = 1: (positions 1, 4, 7, 10, 13, 16)
 *   ─────────────────────────────────────────────────────────────────────────────────
 *   String blocks: b|arf|oot|hef|oob|arm|an
 *   Words: "arf", "oot", "hef", "oob", "arm" → None in wordCount
 *   Result from i=1: []
 *
 *   Starting offset i = 2: (positions 2, 5, 8, 11, 14, 17)
 *   ─────────────────────────────────────────────────────────────────────────────────
 *   String blocks: ba|rfo|oth|efo|oba|rma|n
 *   Words: "rfo", "oth", "efo", "oba", "rma" → None in wordCount
 *   Result from i=2: []
 *
 *   FINAL RESULT: [0, 9]
 *
 * =====================================================================================
 * EXAMPLE 2: s = "wordgoodgoodgoodbestword", words = ["word","good","best","word"]
 * =====================================================================================
 *
 *   wordLen = 4, numWords = 4, totalLen = 16
 *   wordCount = {"word": 2, "good": 1, "best": 1}
 *
 *   Starting offset i = 0:
 *   ─────────────────────────────────────────────────────────────────────────────────
 *   String blocks: |word|good|good|good|best|word|
 *   Positions:      0    4    8    12   16   20
 *
 *   right | word   | seen                     | left | windowLen | action
 *   ------|--------|--------------------------|------|-----------|-------------------
 *     0   | "word" | {word:1}                 |  0   |     4     | add
 *     4   | "good" | {word:1,good:1}          |  0   |     8     | add
 *     8   | "good" | {word:1,good:2}          |  0   |    12     | good:2 > 1, SHRINK!
 *         |        | {good:2}                 |  4   |     8     | removed "word"
 *         |        | {good:1}                 |  8   |     4     | removed "good", now good:1≤1
 *    12   | "good" | {good:2}                 |  8   |     8     | good:2 > 1, SHRINK!
 *         |        | {good:1}                 | 12   |     4     | removed "good"
 *    16   | "best" | {good:1,best:1}          | 12   |     8     | add
 *    20   | "word" | {good:1,best:1,word:1}   | 12   |    12     | add (need 16, only 12)
 *
 *   No valid window found from i=0.
 *
 *   FINAL RESULT: [] (no valid concatenation)
 *
 * =====================================================================================
 * EXAMPLE 3: s = "barfoofoobarthefoobarman", words = ["bar","foo","the"]
 * =====================================================================================
 *
 *   wordLen = 3, numWords = 3, totalLen = 9
 *   wordCount = {"bar": 1, "foo": 1, "the": 1}
 *
 *   Starting offset i = 0:
 *   ─────────────────────────────────────────────────────────────────────────────────
 *   |bar|foo|foo|bar|the|foo|bar|man|
 *    0   3   6   9   12  15  18  21
 *
 *   right | word  | seen                  | left | windowLen | action
 *   ------|-------|----------------------|------|-----------|------------------------
 *     0   | "bar" | {bar:1}               |  0   |     3     | add
 *     3   | "foo" | {bar:1,foo:1}         |  0   |     6     | add
 *     6   | "foo" | {bar:1,foo:2}         |  0   |     9     | foo:2>1, SHRINK!
 *         |       | {foo:2}               |  3   |     6     | removed "bar"
 *         |       | {foo:1}               |  6   |     3     | removed "foo", now ok
 *     9   | "bar" | {foo:1,bar:1}         |  6   |     6     | add
 *    12   | "the" | {foo:1,bar:1,the:1}   |  6   |     9     | windowLen=9=totalLen ✓
 *         |       |                       |      |           | → ADD 6 TO RESULT
 *    15   | "foo" | {...,foo:2}           |  6   |    12     | foo:2>1, SHRINK!
 *         |       | {bar:1,the:1,foo:1}   |  9   |     9     | windowLen=9=totalLen ✓
 *         |       |                       |      |           | → ADD 9 TO RESULT
 *    18   | "bar" | {...,bar:2}           |  9   |    12     | bar:2>1, SHRINK!
 *         |       | {the:1,foo:1,bar:1}   | 12   |     9     | windowLen=9=totalLen ✓
 *         |       |                       |      |           | → ADD 12 TO RESULT
 *    21   | "man" | -                     | 24   |     0     | invalid, reset
 *
 *   FINAL RESULT: [6, 9, 12]
 *
 * =====================================================================================
 * VISUAL: WINDOW SLIDING AT WORD LEVEL
 * =====================================================================================
 *
 *   s = "barfoothefoobarman", words = ["foo", "bar"]
 *
 *   Step 1: Window covers [bar][foo] at positions 0-5
 *   ┌───────────────────────────────────────────────────────────────┐
 *   │  b a r f o o t h e f o o b a r m a n                         │
 *   │  └─────────┘                                                 │
 *   │  [bar][foo]  ← Valid! Contains "foo" and "bar" exactly once  │
 *   │  left=0, right=6, result=[0]                                 │
 *   └───────────────────────────────────────────────────────────────┘
 *
 *   Step 2: "the" is not in words → reset window
 *   ┌───────────────────────────────────────────────────────────────┐
 *   │  b a r f o o t h e f o o b a r m a n                         │
 *   │              └─────┘                                         │
 *   │              [the] ← Not in words! Clear seen, reset         │
 *   │              left=9, right=9                                 │
 *   └───────────────────────────────────────────────────────────────┘
 *
 *   Step 3: Window covers [foo][bar] at positions 9-14
 *   ┌───────────────────────────────────────────────────────────────┐
 *   │  b a r f o o t h e f o o b a r m a n                         │
 *   │                    └─────────┘                               │
 *   │                    [foo][bar] ← Valid! result=[0, 9]         │
 *   └───────────────────────────────────────────────────────────────┘
 *
 * =====================================================================================
 * COMPLEXITY:
 * =====================================================================================
 *
 *   Let:
 *     n = length of string s
 *     m = number of words in words[]
 *     k = length of each word (wordLen)
 *     totalLen = m × k (length of valid concatenation)
 *
 *   ─────────────────────────────────────────────────────────────────────────────────
 *   TIME COMPLEXITY: O(n × k)
 *   ─────────────────────────────────────────────────────────────────────────────────
 *
 *   Breakdown:
 *   ┌─────────────────────────────────────────────────────────────────────────────┐
 *   │ Operation                      │ Complexity       │ Explanation             │
 *   ├─────────────────────────────────────────────────────────────────────────────┤
 *   │ Build wordCount map            │ O(m × k)         │ m words, each length k  │
 *   │ Outer for loop                 │ O(k) iterations  │ k different offsets     │
 *   │ Inner while loop (per offset)  │ O(n/k) iterations│ n/k words per offset    │
 *   │ substring() per iteration      │ O(k)             │ Extract word of len k   │
 *   │ HashMap operations             │ O(k) amortized   │ Hash computation        │
 *   └─────────────────────────────────────────────────────────────────────────────┘
 *
 *   Total: O(k) offsets × O(n/k) words × O(k) per word = O(n × k)
 *
 *   Note: Each position in s is visited exactly once across all iterations.
 *         The k offsets partition the string into k disjoint sets of positions.
 *
 *   Example: s = "barfoothefoobarman" (n=18), k=3
 *   ─────────────────────────────────────────────────────────────────────────────
 *   Offset 0: positions 0,3,6,9,12,15    → 6 words processed
 *   Offset 1: positions 1,4,7,10,13,16   → 6 words processed
 *   Offset 2: positions 2,5,8,11,14,17   → 6 words processed
 *                                          ─────────────────
 *   Total: 18 words = n/k × k = n word extractions
 *
 *   ─────────────────────────────────────────────────────────────────────────────────
 *   SPACE COMPLEXITY: O(m × k + n)
 *   ─────────────────────────────────────────────────────────────────────────────────
 *
 *   Breakdown:
 *   ┌─────────────────────────────────────────────────────────────────────────────┐
 *   │ Data Structure                 │ Space            │ Explanation             │
 *   ├─────────────────────────────────────────────────────────────────────────────┤
 *   │ wordCount HashMap              │ O(m × k)         │ m unique words, len k   │
 *   │ seenWord HashMap               │ O(m × k)         │ At most m words in map  │
 *   │ result ArrayList               │ O(n / totalLen)  │ At most n/totalLen hits │
 *   │ Temporary substring            │ O(k)             │ One word at a time      │
 *   └─────────────────────────────────────────────────────────────────────────────┘
 *
 *   Dominant term: O(m × k) for the HashMaps
 *
 *   Note: If all words are unique, m × k could equal totalLen.
 *         In worst case where result contains many indices: O(n / totalLen) results.
 *
 *   ─────────────────────────────────────────────────────────────────────────────────
 *   COMPARISON WITH NAIVE APPROACH:
 *   ─────────────────────────────────────────────────────────────────────────────────
 *
 *   Naive approach (check every position):
 *     - For each of n positions, check if next totalLen chars form valid concat
 *     - Extract m words, check all in HashMap
 *     - Time: O(n × m × k)
 *
 *   Our sliding window approach:
 *     - Reuses work: don't re-extract words already in window
 *     - Time: O(n × k)
 *     - Speedup: O(m) times faster!
 *
 *   Example speedup:
 *     n = 100,000, m = 100, k = 10
 *     Naive:  100,000 × 100 × 10 = 100,000,000 operations
 *     Ours:   100,000 × 10       =   1,000,000 operations (100× faster!)
 *
 * =====================================================================================
 * EDGE CASES:
 * =====================================================================================
 *   1. s is shorter than totalLen → return empty list
 *   2. Empty words array → return empty list (or handle as edge case)
 *   3. Duplicate words in words[] → wordCount handles frequency correctly
 *   4. No valid concatenation → return empty list
 *   5. Entire string is one valid concatenation → return [0]
 *   6. Overlapping valid windows → all starting indices included
 *   7. Single word in words[] → find all occurrences of that word
 *
 * =====================================================================================
 * KEY INSIGHT: WHY SHRINK ONLY WHEN seen[word] > wordCount[word]?
 * =====================================================================================
 *
 *   We maintain the invariant: seen[w] ≤ wordCount[w] for all words
 *
 *   When we add a word and seen[word] > wordCount[word]:
 *   - We have too many occurrences of this word in our window
 *   - We must remove words from the left until seen[word] ≤ wordCount[word]
 *   - This might remove other words too, but that's okay - they'll be re-added
 *
 *   When we encounter an invalid word (not in wordCount):
 *   - The window can't possibly be valid
 *   - Reset completely: clear seen, move left to right
 */
public class SubstringWithConcatOfAllWords {
    
    public static void main(String[] args) {
        SubstringWithConcatOfAllWords obj = new SubstringWithConcatOfAllWords();
        
        System.out.println("============ TEST CASES ============\n");
        
        // Test 1: Basic case - two matches
        String s1 = "barfoothefoobarman";
        String[] words1 = {"foo", "bar"};
        System.out.println("Test 1: Basic case with two matches");
        System.out.println("Input: s=\"" + s1 + "\", words=[\"foo\",\"bar\"]");
        System.out.println("Output: " + obj.findSubstring(s1, words1));
        System.out.println("Expected: [0, 9]\n");
        
        // Test 2: No matches found
        String s2 = "wordgoodgoodgoodbestword";
        String[] words2 = {"word", "good", "best", "word"};
        System.out.println("Test 2: No valid concatenation (duplicate word with insufficient occurrences)");
        System.out.println("Input: s=\"" + s2 + "\", words=[\"word\",\"good\",\"best\",\"word\"]");
        System.out.println("Output: " + obj.findSubstring(s2, words2));
        System.out.println("Expected: []\n");
        
        // Test 3: Multiple overlapping matches
        String s3 = "barfoofoobarthefoobarman";
        String[] words3 = {"bar", "foo", "the"};
        System.out.println("Test 3: Multiple overlapping matches");
        System.out.println("Input: s=\"" + s3 + "\", words=[\"bar\",\"foo\",\"the\"]");
        System.out.println("Output: " + obj.findSubstring(s3, words3));
        System.out.println("Expected: [6, 9, 12]\n");
        
        // Test 4: Single word
        String s4 = "foobarfoo";
        String[] words4 = {"foo"};
        System.out.println("Test 4: Single word in words array");
        System.out.println("Input: s=\"" + s4 + "\", words=[\"foo\"]");
        System.out.println("Output: " + obj.findSubstring(s4, words4));
        System.out.println("Expected: [0, 6]\n");
        
        // Test 5: String shorter than totalLen
        String s5 = "ab";
        String[] words5 = {"abc", "def"};
        System.out.println("Test 5: String shorter than totalLen");
        System.out.println("Input: s=\"" + s5 + "\", words=[\"abc\",\"def\"]");
        System.out.println("Output: " + obj.findSubstring(s5, words5));
        System.out.println("Expected: []\n");
        
        // Test 6: Entire string is one valid concatenation
        String s6 = "foobar";
        String[] words6 = {"foo", "bar"};
        System.out.println("Test 6: Entire string is one valid concatenation");
        System.out.println("Input: s=\"" + s6 + "\", words=[\"foo\",\"bar\"]");
        System.out.println("Output: " + obj.findSubstring(s6, words6));
        System.out.println("Expected: [0]\n");
        
        // Test 7: Duplicate words in words array
        String s7 = "wordgoodgoodgoodbestword";
        String[] words7 = {"word", "good", "best", "good"};
        System.out.println("Test 7: Duplicate words in words array");
        System.out.println("Input: s=\"" + s7 + "\", words=[\"word\",\"good\",\"best\",\"good\"]");
        System.out.println("Output: " + obj.findSubstring(s7, words7));
        System.out.println("Expected: [8]\n");
        
        // Test 8: All same words
        String s8 = "aaaaaaaa";
        String[] words8 = {"aa", "aa", "aa"};
        System.out.println("Test 8: All same words");
        System.out.println("Input: s=\"" + s8 + "\", words=[\"aa\",\"aa\",\"aa\"]");
        System.out.println("Output: " + obj.findSubstring(s8, words8));
        System.out.println("Expected: [0, 2]\n");
        
        // Test 9: Non-aligned match (needs offset > 0)
        String s9 = "xbarfoo";
        String[] words9 = {"bar", "foo"};
        System.out.println("Test 9: Match not at position 0 (offset matters)");
        System.out.println("Input: s=\"" + s9 + "\", words=[\"bar\",\"foo\"]");
        System.out.println("Output: " + obj.findSubstring(s9, words9));
        System.out.println("Expected: [1]\n");
        
        // Test 10: Long words
        String s10 = "lingmindraboofooowingdingbarrwhingmindraboofooowingdingbarrwede";
        String[] words10 = {"]]fooo", "barr", "wing", "ding", "wing"};
        System.out.println("Test 10: Longer words");
        System.out.println("Input: s=\"lingmindra...\", words=[\"fooo\",\"barr\",\"wing\",\"ding\",\"wing\"]");
        System.out.println("Output: " + obj.findSubstring(s10, words10));
        System.out.println("Expected: [13]\n");
        
        System.out.println("============ ALL TESTS COMPLETE ============");
    }
    
    /**
     * Finds all starting indices of substrings that are concatenations of all words.
     *
     * Algorithm:
     * 1. Build frequency map of required words
     * 2. Try wordLen different starting offsets (0 to wordLen-1)
     * 3. For each offset, use sliding window at word-level
     * 4. When window contains exact word frequencies → record start index
     *
     * @param s the input string to search in
     * @param words array of words (all same length) to concatenate
     * @return list of starting indices of valid concatenation substrings
     */
    public List<Integer> findSubstring(String s, String[] words) {

        List<Integer> result = new ArrayList<>();
        
        // =========================================================================
        // EDGE CASE: Handle empty or invalid inputs
        // =========================================================================
        if (s == null || s.isEmpty() || words == null || words.length == 0) {
            return result;
        }
        
        int wordLen = words[0].length();   // Length of each word (all same)
        int numWords = words.length;        // Number of words to concatenate
        int totalLen = wordLen * numWords;  // Total length of valid substring
        
        // =========================================================================
        // EDGE CASE: String too short to contain concatenation
        // =========================================================================
        if (s.length() < totalLen) {
            return result;
        }
        
        // =========================================================================
        // STEP 1: Build word frequency map
        // =========================================================================
        // wordCount[word] = how many times this word appears in words[]
        Map<String, Integer> wordCount = new HashMap<>();
        for (String w : words) {
            wordCount.merge(w, 1, Integer::sum);
        }

        // =========================================================================
        // STEP 2: Try each starting offset from 0 to wordLen-1
        // =========================================================================
        // This ensures we check ALL possible word alignments
        for (int i = 0; i < wordLen; i++) {
            int left = i;   // Left boundary of window
            int right = i;  // Right boundary of window
            
            // seen[word] = how many times we've seen this word in current window
            Map<String, Integer> seen = new HashMap<>();

            // =================================================================
            // STEP 3: Slide window at word-level
            // =================================================================
            while (right + wordLen <= s.length()) {
                
                // Extract the word at current right position
                String word = s.substring(right, right + wordLen);
                right += wordLen;  // Move right by one word

                // =============================================================
                // CASE 1: Word is in our required words
                // =============================================================
                if (wordCount.containsKey(word)) {
                    // Add word to seen count
                    seen.merge(word, 1, Integer::sum);

                    // =========================================================
                    // SHRINK: If we have too many of this word, shrink from left
                    // =========================================================
                    while (seen.get(word) > wordCount.get(word)) {
                        String leftWord = s.substring(left, left + wordLen);
                        seen.merge(leftWord, -1, Integer::sum);
                        if (seen.get(leftWord) == 0) {
                            seen.remove(leftWord);  // Clean up zero counts
                        }
                        left += wordLen;  // Move left by one word
                    }

                    // =========================================================
                    // CHECK: If window size equals totalLen, found valid match!
                    // =========================================================
                    if (right - left == totalLen) {
                        result.add(left);
                    }
                    
                // =============================================================
                // CASE 2: Word is NOT in our required words
                // =============================================================
                } else {
                    // Invalid word breaks the chain - reset window completely
                    seen.clear();
                    left = right;  // Move left to right (empty window)
                }
            }
        }

        return result;
    }
}
