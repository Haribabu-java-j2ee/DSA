package dsapatterns.slidingwindow;

import java.util.HashSet;
import java.util.Set;

/**
 * =====================================================================================
 * LONGEST SUBSTRING WITHOUT REPEATING CHARACTERS - Sliding Window
 * =====================================================================================
 * LeetCode: https://leetcode.com/problems/longest-substring-without-repeating-characters/
 *
 * =====================================================================================
 * INTUITION:
 * =====================================================================================
 * Use sliding window with two pointers. Track last seen index of each character.
 * When duplicate found, jump left pointer to position after the previous occurrence.
 * This avoids shrinking one-by-one.
 *
 * =====================================================================================
 * EXAMPLE: s = "pwwkew" → returns 3 ("wke" or "kew")
 * =====================================================================================
 *
 *   Method 1 (Optimized - Index Map):
 *   right | char | charIndexMap[c] | left | maxLength
 *   ------|------|-----------------|------|----------
 *     0   |  p   |       0         |  0   |    1
 *     1   |  w   |       0         |  0   |    2
 *     2   |  w   |       2 (jump!) |  2   |    2     ← left jumps to index 2
 *     3   |  k   |       0         |  2   |    2
 *     4   |  e   |       0         |  2   |    3
 *     5   |  w   |       3         |  3   |    3     ← left jumps to index 3
 *
 *   Result: 3
 *
 * =====================================================================================
 * THREE APPROACHES PROVIDED:
 * =====================================================================================
 *   1. lengthOfLongestSubstring()  - O(n) time, O(128) space - Index jump (optimal)
 *   2. lengthOfLongestSubstring1() - O(n) time, O(min(n,26)) - HashSet shrink one-by-one
 *   3. lengthOfLongestSubstring2() - O(n) time, O(128) space - Count array shrink
 *
 * =====================================================================================
 * COMPLEXITY:
 * =====================================================================================
 *   Time:  O(n) - Each character visited at most twice (once by right, once by left)
 *   Space: O(1) - Fixed 128-size array for ASCII characters
 *
 * =====================================================================================
 * EDGE CASES:
 * =====================================================================================
 *   1. Empty string → return 0
 *   2. All unique characters → return length
 *   3. All same characters → return 1
 *   4. Single character → return 1
 */
public class LongestSubStringWORepeastingChars {
    public static void main(String[] args) {
        String s="pwwkew";
        LongestSubStringWORepeastingChars obj=new LongestSubStringWORepeastingChars();
        System.out.println(obj.lengthOfLongestSubstring(s));
        System.out.println(obj.lengthOfLongestSubstring1(s));
    }
    
    /**
     * OPTIMAL: Store last index+1 of each char. When duplicate found, jump left directly.
     * charIndexMap[c] stores: "next valid left position if c is encountered again"
     */
    public int lengthOfLongestSubstring(String s) {
        int n=s.length();
        int left=0;
        int maxLength=0;
        int[] charIndexMap=new int[128];
        for(int right=0;right<n;right++){
            char currentChar=s.charAt(right);
            left=Math.max(charIndexMap[currentChar],left);
            maxLength=Math.max(maxLength,right-left+1);
            charIndexMap[currentChar]=right+1;
        }
        return maxLength;
    }
    public int lengthOfLongestSubstring1(String s) {
        if(s.length()==0){
            return 0;
        }
        int maxLength=0;
        int left=0;
        Set<Character> charSet=new HashSet();
        for(int right=0;right<s.length();right++){
            while(charSet.contains(s.charAt(right))){
                charSet.remove(s.charAt(left));
                left++;
            }
            charSet.add(s.charAt(right));
            maxLength=Math.max(maxLength,right-left+1);
        }
        return maxLength;
    }

    // Optimized version using array instead of HashSet
    public int lengthOfLongestSubstring2(String s) {
        int[] charCount=new int[128];
        int n=s.length();
        int maxLength=0;
        int j=0;
        for(int i=0;i<n;i++){
            charCount[s.charAt(i)]++;
            while(charCount[s.charAt(i)]>1){
                charCount[s.charAt(j++)]--;
            }
            maxLength=Math.max(maxLength,i-j+1);
        }
        return maxLength;
    }
}
