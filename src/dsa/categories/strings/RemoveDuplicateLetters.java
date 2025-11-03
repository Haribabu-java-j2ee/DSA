package dsa.categories.strings;

import java.util.*;

public class RemoveDuplicateLetters {
    public static void main(String[] args) {
        RemoveDuplicateLetters rd=new RemoveDuplicateLetters();
        System.out.println(rd.removeDuplicateLetters("bcabc"));
    }
    public String removeDuplicateLetters(String s) {
        Map<Character,Integer> lastOccurance=new HashMap<>();
        for(int i=0;i<s.length();i++){
            lastOccurance.put(s.charAt(i),i);
        }
        Stack<Character> stack=new Stack<>();
        Set<Character> visited=new HashSet<>();
        for(int i=0;i<s.length();i++){
           if(visited.contains(s.charAt(i))){
               continue;
           }
           while(!stack.isEmpty() && s.charAt(i)<stack.peek() && i<lastOccurance.getOrDefault(stack.peek(),-1)){
               visited.remove(stack.pop());
           }
           stack.add(s.charAt(i));
           visited.add(s.charAt(i));
        }
        StringBuilder result=new StringBuilder();
        for(char ch:stack){
            result.append(ch);
        }
        return result.toString();
    }
}
