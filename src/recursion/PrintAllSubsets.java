package recursion;

import java.util.ArrayList;

public class PrintAllSubsets {
    public static void main(String[] args) {
        String s="xy";
        generate_all_subsets(s).forEach(System.out::println);
    }

    static ArrayList<String> generate_all_subsets(String s) {
        // Write your code here.
        ArrayList<String> result=new ArrayList<>();
        String temp="";
        generateSubsets(s,result,0,temp);
        return result;
    }

    static void generateSubsets(String str,ArrayList<String> result,int begin,String temp){
        if(begin>=str.length()){
            result.add(temp);
        }else{
            generateSubsets(str,result,begin+1,temp);
            generateSubsets(str,result,begin+1,temp+(str.charAt(begin)));
        }
    }



}
