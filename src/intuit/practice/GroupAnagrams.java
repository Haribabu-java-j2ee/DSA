package intuit.practice;

import java.util.*;

public class GroupAnagrams {
    public static void main(String[] args) {
        String[] words = {"eat", "tea", "tan", "ate", "nat"};
        GroupAnagrams solution = new GroupAnagrams();
        solution.groupAnagrams(words).stream().forEach(System.out::println);
        System.out.println("--------");
        solution.groupAnagrams1(words).stream().forEach(System.out::println);

    }

    public List<List<String>> groupAnagrams(String[] words) {
        if (Objects.isNull(words) || words.length == 0) {
            return new ArrayList<>();
        }
        Map<String, List<String>> map = new HashMap<>();
        for (String word : words) {
            char[] chars = word.toCharArray();
            Arrays.sort(chars);
            String key = String.valueOf(chars);
            if (!map.containsKey(key)) {
                map.put(key, new ArrayList<>());
            }
            map.get(key).add(word);
        }
        return new ArrayList<>(map.values());
    }

    public List<List<String>> groupAnagrams1(String[] words) {
        if (Objects.isNull(words) || words.length == 0) {
            return new ArrayList<>();
        }
        Map<String, List<String>> map = new HashMap<>();
        for (String word : words) {
            int[] count = new int[26];
            for (char c : word.toCharArray()) {
                count[c - 'a']++;
            }
            StringBuilder sb = new StringBuilder();
            for (int num : count) {
                //if count more than 10 ex: bdddddddddd, it will mess up digit count forming
                sb.append(num).append("#");
            }
            String key = String.valueOf(sb);
            if (!map.containsKey(key)) {
                map.put(key, new ArrayList<>());
            }
            map.get(key).add(word);
        }
        return new ArrayList<>(map.values());
    }
}
