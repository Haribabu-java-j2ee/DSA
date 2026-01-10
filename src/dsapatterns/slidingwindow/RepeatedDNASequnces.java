package dsapatterns.slidingwindow;

import java.util.*;

/**
 * =====================================================================================
 * REPEATED DNA SEQUENCES - Sliding Window + Rolling Hash (Rabin-Karp)
 * =====================================================================================
 * LeetCode: https://leetcode.com/problems/repeated-dna-sequences
 *
 * =====================================================================================
 * PROBLEM:
 * =====================================================================================
 * Find all 10-letter-long sequences that occur more than once in a DNA string.
 * DNA string consists only of characters: 'A', 'C', 'G', 'T'
 *
 * =====================================================================================
 * THREE APPROACHES (Best to Worst Performance):
 * =====================================================================================
 *   3. findRepeatedDnaSequencesBitManipulation() - Bit manipulation + Array O(1) lookup ⚡ FASTEST
 *   2. findRepeatedDnaSequences1() - Rolling Hash (Rabin-Karp) O(n) space for hashes
 *   1. findRepeatedDnaSequences()  - Simple HashMap with string keys O(n×k) space
 */
public class RepeatedDNASequnces {
    public static void main(String[] args) {
        String s = "AAAAACCCCCAAAAACCCCCCAAAAAGGGTTT";
        RepeatedDNASequnces obj = new RepeatedDNASequnces();
        
        System.out.println("Approach 1 (HashMap):        " + obj.findRepeatedDnaSequences(s));
        System.out.println("Approach 2 (Rolling Hash):   " + findRepeatedDnaSequences1(s));
        System.out.println("Approach 3 (Bit Manipulation): " + obj.findRepeatedDnaSequencesBitManipulation(s));
        
        // Additional test cases for 100% coverage
        System.out.println("\n--- Edge Case Tests ---");
        System.out.println("Short string (<10):  " + obj.findRepeatedDnaSequencesBitManipulation("ACGT"));
        System.out.println("Exactly 10 chars:    " + obj.findRepeatedDnaSequencesBitManipulation("ACGTACGTAC"));
        System.out.println("All same chars:      " + obj.findRepeatedDnaSequencesBitManipulation("AAAAAAAAAAAAA"));
        System.out.println("No repeats:          " + obj.findRepeatedDnaSequencesBitManipulation("ACGTACGTACG"));
        System.out.println("3+ occurrences:      " + obj.findRepeatedDnaSequencesBitManipulation("AAAAAAAAAAAAAAAAAAAAAA"));
    }
    
    /**
     * Approach 1: Simple HashMap with String keys
     * Time: O(n × k), Space: O(n × k)
     */
    public List<String> findRepeatedDnaSequences(String s) {
        Map<String, Integer> sequencesCount = new HashMap<>();
        List<String> resultSequences = new ArrayList<>();
        for (int i = 0; i <= s.length() - 10; i++) {
            String subSequence = s.substring(i, i + 10);
            sequencesCount.put(subSequence, sequencesCount.getOrDefault(subSequence, 0) + 1);
            if (sequencesCount.getOrDefault(subSequence, 0) == 2) {
                resultSequences.add(subSequence);
            }
        }
        return resultSequences;
    }

    /**
     * =====================================================================================
     * APPROACH 2: ROLLING HASH (RABIN-KARP) - Space Optimized
     * =====================================================================================
     *
     * =====================================================================================
     * INTUITION:
     * =====================================================================================
     * Instead of storing full 10-char strings as HashMap keys, encode each sequence
     * as a unique integer using base-4 representation (since only 4 DNA chars exist).
     * Use rolling hash to compute next window's hash in O(1) by:
     *   - Removing contribution of leftmost character
     *   - Adding contribution of new rightmost character
     *
     * =====================================================================================
     * ENCODING: Treat DNA string as base-4 number
     * =====================================================================================
     *   A=0, C=1, G=2, T=3
     *
     *   "ACGT" = 0×4³ + 1×4² + 2×4¹ + 3×4⁰ = 0 + 16 + 8 + 3 = 27
     *
     * =====================================================================================
     * ROLLING HASH FORMULA:
     * =====================================================================================
     *   newHash = oldHash × base - leftChar × base^k + rightChar
     *
     *   Where:
     *     - base = 4 (number of possible characters)
     *     - k = 10 (window size)
     *     - base^k = 4^10 = 1048576 (precomputed)
     *
     * =====================================================================================
     * EXAMPLE: s = "AAAAACCCCC" (length=10, then add one more char)
     * =====================================================================================
     *
     *   Step 1: Encode string to integers
     *   ─────────────────────────────────────────────────────────────────────────────────
     *   String:    A  A  A  A  A  C  C  C  C  C
     *   Encoded:   0  0  0  0  0  1  1  1  1  1
     *   Index:     0  1  2  3  4  5  6  7  8  9
     *
     *   Step 2: Build initial hash for first window [0..9]
     *   ─────────────────────────────────────────────────────────────────────────────────
     *   hash = 0×4⁹ + 0×4⁸ + 0×4⁷ + 0×4⁶ + 0×4⁵ + 1×4⁴ + 1×4³ + 1×4² + 1×4¹ + 1×4⁰
     *        = 0 + 0 + 0 + 0 + 0 + 256 + 64 + 16 + 4 + 1
     *        = 341
     *
     *   Also compute: base^k = 4^10 = 1048576
     *
     *   Step 3: Add hash to seenHashes = {341}
     *
     * =====================================================================================
     * EXAMPLE WITH SLIDING: s = "AAAAACCCCCG" (length=11)
     * =====================================================================================
     *
     *   Window 1 [0..9]: "AAAAACCCCC" → hash = 341
     *   seenHashes = {341}
     *
     *   Window 2 [1..10]: "AAAACCCCCC" + "G"
     *   ─────────────────────────────────────────────────────────────────────────────────
     *   Rolling hash update:
     *     newHash = oldHash × 4 - leftChar × 4^10 + rightChar
     *             = 341 × 4 - 0 × 1048576 + 2
     *             = 1364 - 0 + 2
     *             = 1366
     *
     *   Check: 1366 in seenHashes? No → Add 1366
     *   seenHashes = {341, 1366}
     *
     * =====================================================================================
     * FULL TRACE: s = "AAAAACCCCCAAAAACCCCCC" (has repeated "AAAAACCCCC")
     * =====================================================================================
     *
     *   Encoded: [0,0,0,0,0,1,1,1,1,1,0,0,0,0,0,1,1,1,1,1,1]
     *            \_________window1_________/
     *               \_________window2_________/
     *                             \_________window11________/
     *
     *   Window 1 [0..9]:  "AAAAACCCCC" hash=341, seenHashes={341}
     *   Window 2 [1..10]: "AAAACCCCCA" hash=1364, seenHashes={341,1364}
     *   ...
     *   Window 11 [10..19]: "AAAAACCCCC" hash=341 ← FOUND IN seenHashes!
     *                       Add "AAAAACCCCC" to output
     *
     * =====================================================================================
     * COMPLEXITY:
     * =====================================================================================
     *   Time:  O(n) for hash computation, O(k) for substring on match
     *          Worst case: O(n × k) if many matches
     *   Space: O(n) for hash storage (integers, not strings!)
     *
     * =====================================================================================
     * EDGE CASES:
     * =====================================================================================
     *   1. String length <= 10 → No repeated sequence possible, return empty
     *   2. All same characters → Multiple overlapping matches
     *   3. Hash collision possible (different strings, same hash) → Use Set<String> for output
     *
     * =====================================================================================
     * WHY ROLLING HASH IS BETTER FOR SPACE:
     * =====================================================================================
     *   HashMap with strings: O(n × 10) = O(10n) bytes for string keys
     *   HashSet with integers: O(n × 4) = O(4n) bytes for integer hashes
     *   ~2.5x space savings!
     */
    public static List<String> findRepeatedDnaSequences1(String s) {
        
        // =========================================================================
        // STEP 1: Create character-to-integer mapping for base-4 encoding
        // =========================================================================
        // DNA has only 4 characters, so we can represent each as 0,1,2,3
        // This allows us to treat a DNA sequence as a base-4 number
        Map<Character, Integer> charToDigit = new HashMap<>() {{
            put('A', 0);
            put('C', 1);
            put('G', 2);
            put('T', 3);
        }};
        
        // =========================================================================
        // STEP 2: Encode entire string as list of integers
        // =========================================================================
        // "ACGT" → [0, 1, 2, 3]
        List<Integer> encodedString = new ArrayList<>();
        for (char c : s.toCharArray()) {
            encodedString.add(charToDigit.get(c));
        }

        final int WINDOW_SIZE = 10;      // Fixed window size (10-letter sequences)
        final int stringLength = s.length();

        // =========================================================================
        // EDGE CASE: String too short to have any 10-letter sequence
        // =========================================================================
        if (stringLength <= WINDOW_SIZE) {
            return new ArrayList<>();
        }

        final int BASE = 4;              // Base for our number system (4 DNA chars)
        int currentHash = 0;             // Rolling hash value for current window
        Set<Integer> seenHashes = new HashSet<>();   // Stores hashes we've seen
        Set<String> repeatedSequences = new HashSet<>();  // Stores actual repeated strings
        int basePowerK = 1;              // Will hold BASE^WINDOW_SIZE = 4^10

        // =========================================================================
        // STEP 3: Build initial hash for first window [0..WINDOW_SIZE-1]
        // =========================================================================
        // Hash = d[0]×4^9 + d[1]×4^8 + ... + d[9]×4^0
        // We build this incrementally: hash = hash × 4 + nextDigit
        // Also compute 4^10 for use in rolling hash formula
        for (int i = 0; i < WINDOW_SIZE; i++) {
            currentHash = currentHash * BASE + encodedString.get(i);
            basePowerK *= BASE;  // After loop: basePowerK = 4^10 = 1048576
        }

        // Add first window's hash to seen set
        seenHashes.add(currentHash);

        // =========================================================================
        // STEP 4: Slide window and compute rolling hash for each position
        // =========================================================================
        // For each new window starting at 'windowStart':
        //   - Remove contribution of character leaving the window (leftmost)
        //   - Add contribution of character entering the window (rightmost)
        for (int windowStart = 1; windowStart <= stringLength - WINDOW_SIZE; windowStart++) {
            
            // Get the digit that's leaving (left side) and entering (right side)
            int leavingDigit = encodedString.get(windowStart - 1);
            int enteringDigit = encodedString.get(windowStart + WINDOW_SIZE - 1);
            
            // =====================================================================
            // ROLLING HASH FORMULA:
            // =====================================================================
            // newHash = oldHash × BASE - leavingDigit × BASE^k + enteringDigit
            //
            // Why this works:
            //   oldHash = d[0]×4^9 + d[1]×4^8 + ... + d[9]×4^0
            //
            //   oldHash × 4 = d[0]×4^10 + d[1]×4^9 + ... + d[9]×4^1
            //                 ↑ This needs to be removed
            //
            //   - d[0] × 4^10 removes the leftmost digit's contribution
            //   + d[10] adds the new rightmost digit
            //
            //   Result = d[1]×4^9 + d[2]×4^8 + ... + d[10]×4^0
            // =====================================================================
            currentHash = currentHash * BASE - leavingDigit * basePowerK + enteringDigit;

            // Check if we've seen this hash before
            if (seenHashes.contains(currentHash)) {
                // Hash match! Extract actual string and add to results
                // Using Set<String> handles both:
                //   1. Same sequence appearing 3+ times (only added once)
                //   2. Potential hash collisions (different strings verified)
                String sequence = s.substring(windowStart, windowStart + WINDOW_SIZE);
                repeatedSequences.add(sequence);
            } else {
                // New hash, add to seen set
                seenHashes.add(currentHash);
            }
        }

        return new ArrayList<>(repeatedSequences);
    }
    
    /**
     * =====================================================================================
     * APPROACH 3: BIT MANIPULATION - FASTEST ⚡
     * =====================================================================================
     *
     * =====================================================================================
     * INTUITION:
     * =====================================================================================
     * Each DNA character needs only 2 bits (4 chars = 2² possibilities):
     *   A = 00, C = 01, G = 10, T = 11
     *
     * A 10-character sequence = 10 × 2 = 20 bits = fits in an integer!
     * Total possible sequences = 2²⁰ = 1,048,576
     *
     * Use a fixed-size array of 2²⁰ elements for O(1) GUARANTEED lookup.
     * No hashing overhead, no collision handling needed!
     *
     * =====================================================================================
     * BIT OPERATIONS EXPLAINED:
     * =====================================================================================
     *
     *   1. (hash << 2) | encode(char)
     *      ─────────────────────────────────────────────────────────────────────────────
     *      Shift hash left by 2 bits, add new character's 2-bit encoding
     *
     *      Example: hash = 0b1001 (binary), new char = 'G' (encode = 10)
     *      hash << 2     = 0b100100
     *      | encode('G') = 0b100100 | 0b10 = 0b100110
     *
     *   2. hash & ((1 << 20) - 1)
     *      ─────────────────────────────────────────────────────────────────────────────
     *      Keep only the last 20 bits (mask out older characters)
     *
     *      (1 << 20)     = 0b100000000000000000000 (21 bits, single 1)
     *      (1 << 20) - 1 = 0b011111111111111111111 (20 bits, all 1s) = 1048575
     *
     *      This is the BITMASK that removes the leftmost character automatically!
     *
     * =====================================================================================
     * EXAMPLE: s = "AAAAACCCCCAAAAA" (has repeated "AAAAACCCCC")
     * =====================================================================================
     *
     *   Encoding: A=00, C=01, G=10, T=11
     *
     *   Building hash for first 10 chars "AAAAACCCCC":
     *   ─────────────────────────────────────────────────────────────────────────────────
     *   i=0: 'A' → hash = (0 << 2) | 00 = 0b00
     *   i=1: 'A' → hash = (0b00 << 2) | 00 = 0b0000
     *   i=2: 'A' → hash = (0b0000 << 2) | 00 = 0b000000
     *   i=3: 'A' → hash = 0b00000000
     *   i=4: 'A' → hash = 0b0000000000
     *   i=5: 'C' → hash = (0b0000000000 << 2) | 01 = 0b000000000001
     *   i=6: 'C' → hash = 0b00000000000101
     *   i=7: 'C' → hash = 0b0000000000010101
     *   i=8: 'C' → hash = 0b000000000001010101
     *   i=9: 'C' → hash = 0b00000000000101010101 = 341 (decimal)
     *
     *   i=9 (first complete window): hash = 341, seen[341] = 0 → 1
     *
     *   Sliding to next window "AAAACCCCCA":
     *   ─────────────────────────────────────────────────────────────────────────────────
     *   i=10: 'A' → hash = (341 << 2) | 00 = 1364
     *         hash & mask = 1364 & 1048575 = 1364 (no overflow yet)
     *         seen[1364] = 0 → 1
     *
     *   ... continue sliding ...
     *
     *   When we reach second "AAAAACCCCC":
     *   hash = 341, seen[341] = 1 → FOUND! Add to result
     *   seen[341] = 2 (won't add again for 3+ occurrences)
     *
     * =====================================================================================
     * WHY THIS IS FASTEST:
     * =====================================================================================
     *
     *   | Operation        | Approach 2 (Rolling Hash) | Approach 3 (Bit Manipulation) |
     *   |------------------|---------------------------|-------------------------------|
     *   | Hash update      | Multiply, subtract, add   | Shift, OR, AND (faster!)      |
     *   | Lookup           | HashSet.contains() O(1)*  | Array[index] O(1) guaranteed  |
     *   | Collision check  | Possible, need handling   | Impossible - direct mapping   |
     *   | Memory locality  | Scattered (hash buckets)  | Contiguous (array)            |
     *
     *   * HashSet is O(1) average, but has overhead for hash function and collision handling
     *
     * =====================================================================================
     * COMPLEXITY:
     * =====================================================================================
     *   Time:  O(n) - Single pass, O(1) operations per character
     *   Space: O(2²⁰) = O(1) - Fixed 4MB array (1,048,576 integers × 4 bytes)
     *          + O(result) for output list
     *
     * =====================================================================================
     * EDGE CASES:
     * =====================================================================================
     *   1. String length < 10 → No 10-char sequence possible, return empty
     *   2. String length = 10 → One sequence, can't repeat, return empty
     *   3. Sequence appears 3+ times → Only add once (when seen[hash] == 1)
     *   4. All same characters → Multiple overlapping repeats
     *
     * =====================================================================================
     * TRADE-OFF:
     * =====================================================================================
     *   Pros: Fastest lookup, no hash collisions, simple bit operations
     *   Cons: Always allocates 4MB regardless of input size
     *         (For very small inputs, HashMap might use less memory)
     */
    public List<String> findRepeatedDnaSequencesBitManipulation(String s) {
        List<String> result = new ArrayList<>();
        
        // =========================================================================
        // EDGE CASE: Need at least 11 characters to have a repeated 10-char sequence
        // =========================================================================
        if (s.length() < 10) {
            return result;
        }
        
        // =========================================================================
        // FIXED-SIZE ARRAY: 2^20 = 1,048,576 possible 10-char DNA sequences
        // =========================================================================
        // Each element tracks count: 0 = unseen, 1 = seen once, 2+ = repeated
        // Using array instead of HashSet gives O(1) GUARANTEED lookup
        final int TOTAL_POSSIBLE_SEQUENCES = 1 << 20;  // 2^20 = 1048576
        int[] seenCount = new int[TOTAL_POSSIBLE_SEQUENCES];
        
        // =========================================================================
        // BITMASK: Keeps only last 20 bits (removes leftmost character)
        // =========================================================================
        // (1 << 20) - 1 = 0b11111111111111111111 (20 ones)
        final int TWENTY_BIT_MASK = (1 << 20) - 1;
        
        int hash = 0;  // Rolling hash using bit manipulation
        
        for (int i = 0; i < s.length(); i++) {
            // =================================================================
            // STEP 1: Add new character to hash (shift left 2, add 2-bit encoding)
            // =================================================================
            // hash << 2: Make room for 2 new bits
            // | encode(): Add the new character's encoding
            hash = (hash << 2) | encodeChar(s.charAt(i));
            
            // =================================================================
            // STEP 2: Process when we have at least 10 characters
            // =================================================================
            if (i >= 9) {
                // =============================================================
                // STEP 2a: Keep only last 20 bits (removes 11th+ character)
                // =============================================================
                // This is the "sliding" part - automatically removes leftmost char
                hash = hash & TWENTY_BIT_MASK;
                
                // =============================================================
                // STEP 2b: Check if this sequence was seen exactly once before
                // =============================================================
                // Only add to result when count transitions from 1 to 2
                // This ensures each repeated sequence is added exactly once
                if (seenCount[hash] == 1) {
                    // Extract the actual 10-character substring
                    result.add(s.substring(i - 9, i + 1));
                }
                
                // =============================================================
                // STEP 2c: Increment the count for this hash
                // =============================================================
                seenCount[hash]++;
            }
        }
        
        return result;
    }
    
    /**
     * Encodes a DNA character to its 2-bit representation.
     *
     *   A = 00 (0)
     *   C = 01 (1)
     *   G = 10 (2)
     *   T = 11 (3)
     *
     * Using 2 bits per character allows 10 characters to fit in 20 bits.
     */
    private int encodeChar(char c) {
        if (c == 'A') return 0;  // 00
        if (c == 'C') return 1;  // 01
        if (c == 'G') return 2;  // 10
        return 3;                // 11 for 'T'
    }
}
