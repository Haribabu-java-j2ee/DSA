package dsapatterns.twopointer;

/**
 * =====================================================================================
 * MINIMUM MOVES TO MAKE PALINDROME - Two Pointer + Greedy
 * =====================================================================================
 * LeetCode: https://leetcode.com/problems/minimum-number-of-moves-to-make-palindrome/
 *
 * =====================================================================================
 * INTUITION:
 * =====================================================================================
 * Use two pointers from ends. For each left char, find its match from right side.
 * Swap the match towards its correct position. If no match exists (odd char),
 * move it one position towards center and retry. Greedy: local optimal = global optimal.
 *
 * =====================================================================================
 * EXAMPLE: "aabb" → 2 moves
 * =====================================================================================
 *
 *   Initial: "aabb", result=0
 *   
 *   left=0, right=3:
 *     l=0, r=3: 'a'≠'b' → r--
 *     r=2: 'a'≠'b' → r--
 *     r=1: 'a'='a' ✓
 *     l≠r, so swap r towards right:
 *       swap(1,2): "abab", result=1
 *       swap(2,3): "abba", result=2
 *     left++, right--
 *
 *   left=1, right=2: 'b'='b' (l meets r after moving)
 *
 *   Result: 2 moves
 *
 * =====================================================================================
 * COMPLEXITY:
 * =====================================================================================
 *   Time:  O(n²) - For each of n/2 positions, may scan/swap O(n)
 *   Space: O(n) - Character array for swapping
 *
 * =====================================================================================
 * EDGE CASES:
 * =====================================================================================
 *   1. Already palindrome → 0 moves
 *   2. Odd length with one unpaired char → l==r case, swap towards center
 *   3. Problem guarantees palindrome is possible
 *
 * =====================================================================================
 */
public class MinMovesToMakePalindrome {
    public static void main(String[] args) {
        String s = "skwhhaaunskegmdtutlgtteunmuuludii";
        MinMovesToMakePalindrome obj = new MinMovesToMakePalindrome();
        System.out.println(obj.minMovesToMakePalindrome(s));
    }
    public int minMovesToMakePalindrome(String s) {
        char[] charArr=s.toCharArray();
        int left=0;
        int right=charArr.length-1;
        int result=0;
        while(left<right){
            int l=left;
            int r=right;
            while(charArr[l]!=charArr[r]){
                r--;
            }
            if(l==r){
                swap(charArr,r,r+1);
                result++;
                continue;
            }else{
                while(r<right){
                    swap(charArr,r,r+1);
                    result++;
                    r++;
                }
            }
            left++;
            right--;
        }
        return result;
    }

    private void swap(char[] charArr, int i, int j){
        char temp=charArr[i];
        charArr[i]=charArr[j];
        charArr[j]=temp;
    }
}
