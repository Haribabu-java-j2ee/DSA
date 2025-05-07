package recursion.problemset;

import java.util.ArrayList;

public class StringsFromWildCard {
    public static void main(String[] args) {
        String input = "1?0?";
        find_all_possibilities(input).forEach(System.out::println);
    }

    static ArrayList<String> find_all_possibilities(String s) {
        // Write your code here.
        ArrayList<String> result=new ArrayList<>();
        generateStrings(s,result,"",0,s.length());
        return result;
    }

    static void generateStrings(String s,ArrayList<String> result,String temp,int start, int end){
        if(start>=end){
            result.add(temp);
            return;
        }
        if(s.charAt(start)=='?') {
            generateStrings(s, result, s.charAt(start) == '?' ? (temp + "0") : (temp + s.charAt(start)), start + 1, end);
            generateStrings(s, result, s.charAt(start) == '?' ? (temp + "1") : (temp + s.charAt(start)), start + 1, end);
        }else{
            generateStrings(s, result, temp + s.charAt(start), start + 1, end);
        }
    }

}
