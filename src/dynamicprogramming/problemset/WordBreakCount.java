package dynamicprogramming.problemset;

import java.util.ArrayList;
import java.util.Arrays;

//tries implementation pending
public class WordBreakCount {
    public static void main(String[] args) {
        String[] dictionary = {"kick", "start", "kickstart", "is", "awe", "some", "awesome"};
        String text = "kickstartisawesome";
        ArrayList<String> dictionaryList = new ArrayList<>();
        Arrays.stream(dictionary).forEach(d -> dictionaryList.add(d));
        System.out.println(word_break_count(dictionaryList,text));
        System.out.println(word_break_count2(dictionaryList,text));
    }


    static int MOD=1000000007;
    static int wordBreakCount;
    static Integer word_break_count(ArrayList<String> dictionary, String txt) {
        // Write your code here.
         word_break_count_util(txt,dictionary,"");
        return wordBreakCount;
    }

    private static void word_break_count_util(String txt, ArrayList<String> dictionary, String trailingPrefix) {
        int len=txt.length();
        if(len==0){
            wordBreakCount++;
            if(wordBreakCount>MOD){
                wordBreakCount%=MOD;
            }
            return;
        }
        for(int i=0;i<len;i++){
            String segment=txt.substring(0,i+1);
            int lastindex=dictionary.lastIndexOf(segment);
            if(lastindex!=-1){
                trailingPrefix+=segment+" ";
                segment=txt.substring(i+1,len);
                word_break_count_util(segment,dictionary,trailingPrefix);
            }
        }
    }


    static Integer word_break_count2(ArrayList<String> dictionary, String txt) {
        // Write your code here.
        int[] dp=new int[txt.length()];
        Arrays.fill(dp,-1);
        return word_break_count_util(0,dictionary,txt,dp);
    }

    static int word_break_count_util(int index,ArrayList<String> dictionary, String txt, int[] dp){
        int len=txt.length();

        if(index==len){
            return 1;
        }

        if(dp[index]!=-1){
            return dp[index];
        }

        long result=0l;

        String segment="";
        for(int i=index;i<len;i++){
            segment+=""+txt.charAt(i);
            if(dictionary.contains(segment)){
               long no_of_arrangements=word_break_count_util(i+1,dictionary,txt,dp);
               result+=no_of_arrangements;
               if(result>MOD){
                    result%=MOD;
                }
            }
        }

        return dp[index]=(int)result;

    }


}
