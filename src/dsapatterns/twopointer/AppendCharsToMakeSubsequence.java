package dsapatterns.twopointer;

//https://leetcode.com/problems/append-characters-to-string-to-make-subsequence/
public class AppendCharsToMakeSubsequence {
    public static void main(String[] args) {
        String s="coaching";
        String t="coding";
        AppendCharsToMakeSubsequence obj=new AppendCharsToMakeSubsequence();
        System.out.println(obj.appendCharacters(s, t));
    }
    public int appendCharacters(String s, String t) {
        int sIndex=0,tIndex=0;
        int sLength=s.length(), tLength=t.length();
        while(sIndex<sLength && tIndex<tLength){
            if(s.charAt(sIndex)==t.charAt(tIndex)){
                tIndex++;
            }
            sIndex++;
        }
        return tLength-tIndex;
    }
}
