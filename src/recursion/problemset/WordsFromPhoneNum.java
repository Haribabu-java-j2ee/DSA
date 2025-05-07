package recursion.problemset;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class WordsFromPhoneNum {
    public static void main(String[] args) {
        String input = "1234567";
        get_words_from_phone_number(input).forEach(System.out::println);

    }

    static ArrayList<String> get_words_from_phone_number(String phone_number) {
        // Write your code here.
        Map<Character,String> map=new HashMap<>();
        map.put('2',"abc");
        map.put('3',"def");
        map.put('4',"ghi");
        map.put('5',"jkl");
        map.put('6',"mno");
        map.put('7', "pqrs");
        map.put('8',"tuv");
        map.put('9',"wxyz");
        phone_number=phone_number.replace("1","").replace("0","");
        ArrayList<String> result = new ArrayList<>();
      if(phone_number.isBlank()){
          result.add("");
          return result;
      }else{
          generateSubsets(phone_number,map, result, 0, new StringBuilder());
      }
        return result;
    }

    static void generateSubsets(String phone_number,Map<Character,String> map, ArrayList<String> result, int begin, StringBuilder temp) {
        if (begin >= phone_number.length()) {
            result.add(temp.toString());
            return;
        }
        String char_to_be_aded=map.get(phone_number.charAt(begin));
        for(char c:char_to_be_aded.toCharArray()) {
            generateSubsets(phone_number, map, result, begin + 1, temp .append(c));
            temp.deleteCharAt(begin);
        }
    }
}
