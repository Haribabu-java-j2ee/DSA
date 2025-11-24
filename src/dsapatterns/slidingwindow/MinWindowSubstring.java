package dsapatterns.slidingwindow;

//https://leetcode.com/problems/minimum-window-substring/description/
public class MinWindowSubstring {
    public static void main(String[] args) {
        String s="ADOBECODEBANC";
        String t="ABC";
        MinWindowSubstring obj=new MinWindowSubstring();
        System.out.println(obj.minWindow(s, t));
    }
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
