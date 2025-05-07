package recursion.problemset;

import java.util.ArrayList;
import java.util.List;
//Time complexity: O(2^n)
//Auxiliary space: O(n)
//count is similar to nth catalan number , need to try
//space has to be optimised by adding string buffer
public class GenerateParanthesis {
    public static void main(String[] args) {
        int n = 3;
        //AllParenthesis(n).forEach(System.out::println);
        generateParanthesis(n).forEach(System.out::println);
    }

    private static ArrayList<String> generateParanthesis(int n) {
        ArrayList<String> result = new ArrayList<>();
        ArrayList<String> leftParanthesis = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            leftParanthesis.add("(");
        }
        ArrayList<String> rightParanthesis = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            rightParanthesis.add(")");
        }
        generateParanthesis(result, leftParanthesis, rightParanthesis, "");

        return result;
    }

    private static void generateParanthesisRecur(ArrayList<String> result, ArrayList<String> leftParanthesis, ArrayList<String> rightParanthesis, String s) {
        if (leftParanthesis.isEmpty() && rightParanthesis.isEmpty()) {
                result.add(s);
            return;
        }
        if (!leftParanthesis.isEmpty()) {
            ArrayList<String> newLeftParanthesis = new ArrayList<>(leftParanthesis);
            newLeftParanthesis.remove(0);
            generateParanthesisRecur(result, newLeftParanthesis, rightParanthesis, s + "(");
        }
        if (rightParanthesis.size() > leftParanthesis.size()) {
            ArrayList<String> newRightParanthesis = new ArrayList<>(rightParanthesis);
            newRightParanthesis.remove(0);
            generateParanthesisRecur(result, leftParanthesis, newRightParanthesis, s + ")");
        }
    }


    static void generateParanthesis1(ArrayList<String> result,ArrayList<String> leftParanthesis,ArrayList<String> rightParanthesis,String temp){
        if(leftParanthesis.size()==0 && rightParanthesis.size()==0){
                result.add(temp);
            return;
        }
        if(!leftParanthesis.isEmpty()){
            ArrayList<String> newleftParanthesis=new ArrayList<>(leftParanthesis);
            newleftParanthesis.remove(0);
            generateParanthesis(result,newleftParanthesis,rightParanthesis,temp+"(");
        }
        if(rightParanthesis.size()>leftParanthesis.size()){
            ArrayList<String> newrightParanthesis=new ArrayList<>(rightParanthesis);
            newrightParanthesis.remove(0);
            generateParanthesis(result,leftParanthesis,newrightParanthesis,temp+")");
        }
    }

    static void generateParanthesis(ArrayList<String> result,ArrayList<String> leftParanthesis,ArrayList<String> rightParanthesis,String temp){
        if(leftParanthesis.size()==0 && rightParanthesis.size()==0){
            result.add(temp);
            return;
        }
        if(!leftParanthesis.isEmpty()){
            leftParanthesis.remove(0);
            generateParanthesis(result,leftParanthesis,rightParanthesis,temp+"(");
        }
        if(rightParanthesis.size()>leftParanthesis.size()){
            rightParanthesis.remove(0);
            generateParanthesis(result,leftParanthesis,rightParanthesis,temp+")");
        }
    }

    static void genParenthesisUtil(int n, int open, int close,
                                   String s, List<String> ans) {

        // If the count of both open and close parentheses
        // reaches n, it means we have generated a valid parentheses.
        if (open == n && close == n) {
            ans.add(s);
            return;
        }

        // At any index i in the generation of the string s,
        // we can put an open parentheses only if its count
        // until that time is less than n.
        if (open < n) {
            genParenthesisUtil(n, open + 1, close, s + "{", ans);
        }

        // At any index i in the generation of the string s,
        // we can put a closed parentheses only if its count until
        // that time is less than the count of open parentheses.
        if (close < open) {
            genParenthesisUtil(n, open, close + 1, s + "}", ans);
        }
    }

    static List<String> AllParenthesis(int n) {

        // List for storing the answer
        List<String> ans = new ArrayList<>();
        if (n > 0) {
            genParenthesisUtil(n, 0, 0, "", ans);
        }
        return ans;
    }
}
