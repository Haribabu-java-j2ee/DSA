package dsapatterns.slidingwindow;

/**
 * =====================================================================================
 * MINIMUM WINDOW SUBSTRING - Sliding Window with Character Count
 * =====================================================================================
 * LeetCode: https://leetcode.com/problems/minimum-window-substring/
 *
 * =====================================================================================
 * INTUITION:
 * =====================================================================================
 * Track required character counts from t. Expand window until all chars satisfied.
 * Then shrink from left to find minimum. Use 'required' counter to track how many
 * chars still needed. When charCount becomes 0→negative, that char is satisfied.
 *
 * =====================================================================================
 * EXAMPLE: s = "ADOBECODEBANC", t = "ABC" → returns "BANC"
 * =====================================================================================
 *
 *   Initial charCountMap: A:1, B:1, C:1, required=3
 *
 *   Expand phase (finding first valid window):
 *   r | char | charCount[c] | required | window
 *   --|------|--------------|----------|---------------
 *   0 |  A   |    0 (was 1) |    2     | A
 *   1 |  D   |   -1         |    2     | AD
 *   2 |  O   |   -1         |    2     | ADO
 *   3 |  B   |    0 (was 1) |    1     | ADOB
 *   4 |  E   |   -1         |    1     | ADOBE
 *   5 |  C   |    0 (was 1) |    0     | ADOBEC ← VALID! required=0
 *
 *   Shrink phase (minimizing window):
 *   l | char | charCount[c] | required | window    | minLen
 *   --|------|--------------|----------|-----------|-------
 *   0 |  A   |    1 (was 0) |    1     | DOBEC     | 6
 *   ...continue expanding and shrinking...
 *
 *   Final: "BANC" (length 4)
 *
 * =====================================================================================
 * COMPLEXITY:
 * =====================================================================================
 *   Time:  O(m + n) - m = len(s), n = len(t)
 *   Space: O(1) - Fixed 128-size array
 *
 * =====================================================================================
 * EDGE CASES:
 * =====================================================================================
 *   1. t longer than s → return ""
 *   2. t has duplicate chars → charCount handles frequencies
 *   3. No valid window → return ""
 *   4. s == t → return s
 */
public class MinWindowSubstring {
    public static void main(String[] args) {
        String s="ADOBECODEBANC";
        String t="ABC";
        MinWindowSubstring obj=new MinWindowSubstring();
        System.out.println(obj.minWindow(s, t));
    }
    
    /**
     * Key insight: charCountMap[c] > 0 means we still NEED that char.
     * charCountMap[c] <= 0 means we have enough (or it's not in t).
     * 'required' tracks total unfulfilled character count.
     */
    public String minWindow(String s, String t) {
        int[] charCountMap=new int[128];
        for(char c:t.toCharArray()){
            charCountMap[c]++;
        }
        int left=0;
        int right=0;
        int required=t.length();
        int minLength=Integer.MAX_VALUE;
        int minLeft=0;
        while(right<s.length()){
            char rightChar=s.charAt(right);
            if(charCountMap[rightChar]>0){
                required--;
            }
            charCountMap[rightChar]--;
            while(required==0){
                if(right-left+1<minLength){
                    minLength=right-left+1;
                    minLeft=left;
                }
                char leftChar=s.charAt(left);
                charCountMap[leftChar]++;
                if(charCountMap[leftChar]>0){
                    required++;
                }
                left++;
            }
            right++;
        }
        return minLength==Integer.MAX_VALUE?"":s.substring(minLeft,minLeft+minLength);
    }

}
