package graphs.practice;

import java.util.*;

public class AlienDictionary {
    public static void main(String[] args) {

        String[] words = {"baa", "abcd", "abca", "cab", "cad"};
        ArrayList<String> wordsList = new ArrayList<>();
        Arrays.stream(words).forEach(wordsList::add);
        System.out.println(find_order(wordsList));

        String[] words1 = {"wrt", "wrf", "er", "ett", "rftt"};
        wordsList = new ArrayList<>();
        Arrays.stream(words1).forEach(wordsList::add);
        System.out.println(find_order(wordsList));

        String[] words2 = {"z", "x"};
        wordsList = new ArrayList<>();
        Arrays.stream(words2).forEach(wordsList::add);
        System.out.println(find_order(wordsList));

        String[] words3 = {"z", "x", "z"};
        wordsList = new ArrayList<>();
        Arrays.stream(words3).forEach(wordsList::add);
        System.out.println(find_order(wordsList));

        String[] words4 = {"abc", "ab"};
        wordsList = new ArrayList<>();
        Arrays.stream(words4).forEach(wordsList::add);
        System.out.println(find_order(wordsList));
    }

    static String find_order(ArrayList<String> words) {
        // Write your code here.
        Map<Character, List<Character>> adjMap = new HashMap();
        for (String word : words) {
            for (int i = 0; i < word.length(); i++) {
                adjMap.put(word.charAt(i), new ArrayList<>());
            }
        }
        for (int i = 0; i < words.size() - 1; i++) {
            if (words.get(i).length() > words.get(i + 1).length() && words.get(i).startsWith(words.get(i + 1))) {
                return "";
            }
            for(int j=0;j<Math.min(words.get(i).length(), words.get(i + 1).length());j++){
                if(words.get(i+1).charAt(j)!=words.get(i).charAt(j)){
                    adjMap.get(words.get(i+1).charAt(j)).add(words.get(i).charAt(j));
                    break;
                }
            }

        }
        Map<Character, Boolean> visited = new HashMap();
        StringBuilder topologicalOrder = new StringBuilder();
        for (Character charKey : adjMap.keySet()) {
            if (!visited.containsKey(charKey)) {
                if (dfs(charKey, adjMap, visited, topologicalOrder)) {
                    return "";
                }
            }
        }

        return topologicalOrder.toString();
    }

    private static boolean dfs(Character charKey, Map<Character, List<Character>> adjMap, Map<Character, Boolean> visited, StringBuilder topologicalOrder) {
        if (visited.containsKey(charKey)) {
            return visited.get(charKey);
        }
        visited.put(charKey, true);
        for (Character neighbor : adjMap.get(charKey)) {
            if (dfs(neighbor, adjMap, visited, topologicalOrder)) {
                return true;
            }

        }
        visited.put(charKey, false);
        topologicalOrder.append(charKey);
        return false;
    }



}
