package recursion.problemset;

import java.util.ArrayList;

public class LetterCasePermutation {
    public static void main(String[] args) {
        String s = "a1z";
        letter_case_permutations1(s).forEach(System.out::println);
    }

    //O(length * 2^length)
    static ArrayList<String> letter_case_permutations(String s) {
        // Write your code here.
        ArrayList<String> result = new ArrayList();
        generateCasePermutations(s, result, "", 0);
        return result;
    }

    static void generateCasePermutations(String s, ArrayList<String> result, String temp, int begin) {
        if (begin >= s.length()) {
            result.add(temp);
            return;
        }

        if (Character.isDigit(s.charAt(begin))) {
            generateCasePermutations(s, result, temp + s.charAt(begin), begin + 1);

        } else {
            generateCasePermutations(s, result, temp + Character.toUpperCase(s.charAt(begin)), begin + 1);
            generateCasePermutations(s, result, temp + Character.toLowerCase(s.charAt(begin)), begin + 1);
        }

    }

    //optimised space with string builder
    static ArrayList<String> letter_case_permutations1(String s) {
        // Write your code here.
        ArrayList<String> result = new ArrayList();
        generateCasePermutations1(s, result, new StringBuilder(""), 0);
        return result;
    }

    static void generateCasePermutations1(String s, ArrayList<String> result, StringBuilder temp, int begin) {
        if (begin >= s.length()) {
            result.add(temp.toString());
            return;
        }

        if (Character.isDigit(s.charAt(begin))) {
            temp.append(s.charAt(begin));
            generateCasePermutations1(s, result, temp, begin + 1);
            temp.deleteCharAt(temp.length() - 1);
        } else {
            temp.append(Character.toUpperCase(s.charAt(begin)));
            generateCasePermutations1(s, result, temp, begin + 1);
            temp.deleteCharAt(temp.length() - 1);

            
            temp.append(Character.toLowerCase(s.charAt(begin)));
            generateCasePermutations1(s, result, temp, begin + 1);
            temp.deleteCharAt(temp.length() - 1);

        }

    }

}
