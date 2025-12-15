package dsapatterns.slidingwindow;

//https://leetcode.com/problems/longest-repeating-character-replacement/
public class LongestRepeatingCharacterReplacement {
    public static void main(String[] args) {
        String s="ABCABBA";
        int k=1;
        LongestRepeatingCharacterReplacement obj=new LongestRepeatingCharacterReplacement();
        System.out.println(obj.characterReplacement(s, k));
    }
    public int characterReplacement(String s, int k) {
        int left=0;
        int result=0;
        int maxLength=0;
        int[] charArr=new int[26];
        for(int right=0;right<s.length();right++){
            charArr[s.charAt(right)-'A']++;
            maxLength=Math.max(maxLength,charArr[s.charAt(right)-'A']);
            if(right-left+1-maxLength>k){
                charArr[s.charAt(left)-'A']--;
                left++;
            }
            result=Math.max(result,right-left+1);
        }
        return result;
    }
}
