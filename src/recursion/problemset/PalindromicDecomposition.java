package recursion.problemset;

import java.util.ArrayList;

public class PalindromicDecomposition {
    public static void main(String[] args) {
        String input = "abracadabra";
        generate_palindromic_decompositions(input).forEach(System.out::println);
    }

    static ArrayList<String> generate_palindromic_decompositions(String s) {
        // Write your code here.
        ArrayList<String> result=new ArrayList<>();
        generatePalindromicDecompositionsUtil(result,s,0,"");
        return result;
    }

    static void generatePalindromicDecompositionsUtil(ArrayList<String> result, String s, int pos, String lastString){
        if(pos==s.length()){
            result.add(lastString);
        }else{
            for(int i=pos;i<s.length();i++){
                if(isPalindrom(s,pos,i)){
                    if(pos==0){
                        generatePalindromicDecompositionsUtil(result,s,i+1,s.substring(pos,i+1));
                    }else{
                        generatePalindromicDecompositionsUtil(result,s,i+1,lastString+"|"+s.substring(pos,i+1));
                    }
                }
            }
        }
    }

    static boolean isPalindrom(String s, int left, int right){
        while(left<right){
            if(s.charAt(left)!=s.charAt(right)){
                return false;
            }
            left++;
            right--;
        }
        return true;
    }
}
