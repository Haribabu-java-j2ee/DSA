package dsapatterns.slidingwindow;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

//https://leetcode.com/problems/permutation-in-string/
public class PermutaionInString {
    public static void main(String[] args) {
        String s1="ab";
        String s2="eidbaooo";
        PermutaionInString obj=new PermutaionInString();
        System.out.println(obj.checkInclusion(s1, s2));
    }
    public boolean checkInclusion(String s1, String s2) {
        int n = s1.length();
        int m = s2.length();
        if (m<n){
            return false;
        }
        int map1[] = new int[26];
        int map2[] = new int[26];
        for (int i=0;i<n;i++){
            map1[s1.charAt(i)-'a']++;
            map2[s2.charAt(i)-'a']++;
        }
        if (isMatched(map1,map2)){
            return true;
        }
        System.out.println();
        for(int i=1;i<=m-n;i++){
            map2[s2.charAt(i-1)-'a']--;
            map2[s2.charAt(n+i-1)-'a']++;
            if (isMatched(map1,map2)){
                return true;
            }
        }
        return false;
    }
    private boolean isMatched(int[] map1, int[] map2){
        for(int i=0;i<26;i++){
            if(map1[i]!=map2[i])
                return false;
        }
        return true;
    }
}
