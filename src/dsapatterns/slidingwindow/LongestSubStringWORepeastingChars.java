package dsapatterns.slidingwindow;

import java.util.HashSet;
import java.util.Set;

//https://leetcode.com/problems/longest-substring-without-repeating-characters/description/
public class LongestSubStringWORepeastingChars {
    public static void main(String[] args) {
        String s="pwwkew";
        LongestSubStringWORepeastingChars obj=new LongestSubStringWORepeastingChars();
        System.out.println(obj.lengthOfLongestSubstring(s));
        System.out.println(obj.lengthOfLongestSubstring1(s));
    }
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
