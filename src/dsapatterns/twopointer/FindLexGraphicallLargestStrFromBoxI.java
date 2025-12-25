package dsapatterns.twopointer;

//https://leetcode.com/problems/find-the-lexicographically-largest-string-from-the-box-i/
public class FindLexGraphicallLargestStrFromBoxI {
    public static void main(String[] args) {
        String word="dbca";
        int numFriends=2;
        FindLexGraphicallLargestStrFromBoxI obj=new FindLexGraphicallLargestStrFromBoxI();
        String ans=obj.answerString(word,numFriends);
        System.out.println("Lexicographically largest string: "+ans);
    }
    public String answerString(String word, int numFriends) {
        if(numFriends==1){
            return word;
        }
        int wordLength=word.length();
        String maxSubstring="";
        for(int i=0;i<wordLength;i++){
            int maxEndLength=Math.min(wordLength,i+(wordLength-(numFriends-1)));
            String currentSubstring=word.substring(i,maxEndLength);
            if(maxSubstring.compareTo(currentSubstring)<0){
                maxSubstring=currentSubstring;
            }
        }
        return maxSubstring;
    }
}
