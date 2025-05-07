package recursion;

import java.util.ArrayList;
import java.util.List;

public class PrintAllBinaryofLenN {
    public static void main(String[] args) {
        int n = 3;
        //get_binary_strings(n).forEach(System.out::println);
        get_binary_strings1(n).forEach(System.out::println);
    }

    //depth first search
    static ArrayList<String> get_binary_strings(Integer n) {
        // Write your code here.
        ArrayList<String> result = new ArrayList<>();
        getBinaryStringsHelper(n, "", result);
        return result;
    }

    static void getBinaryStringsHelper(Integer n, String output, ArrayList<String> result) {
        if (n == 0) {
            result.add(output);
        } else {
            getBinaryStringsHelper(n - 1, output + "0", result);
            getBinaryStringsHelper(n - 1, output + "1", result);
        }
    }

    //bfs
    static ArrayList<String> get_binary_strings1(Integer n) {
       return getBinaryStringsHelper1(n);
    }

    static ArrayList<String> getBinaryStringsHelper1(Integer n) {
        if (n == 1) {
            ArrayList<String> result = new ArrayList<>();
            result.add("0");
            result.add("1");
            return result;
        } else {
            ArrayList<String> prev=getBinaryStringsHelper1(n-1);
            ArrayList<String> result = new ArrayList<>();
            for(String s:prev) {
                result.add(s+"0");
                result.add(s+"1");
            }
            return result;
        }
    }
}
