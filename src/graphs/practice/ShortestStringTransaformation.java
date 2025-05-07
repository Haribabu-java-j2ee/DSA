package graphs.practice;

import java.util.*;

public class ShortestStringTransaformation {
    public static void main(String[] args) {
        String[] words={"cat", "hat", "bad", "had"};
        ArrayList<String> wordsList=new ArrayList<>();
        Arrays.stream(words).forEach(wordsList::add);
        System.out.println(string_transformation(wordsList,"bat","had"));

        wordsList=new ArrayList<>();
        System.out.println(string_transformation(wordsList,"bbb","bbc"));

        System.out.println(string_transformation(wordsList,"zzzzzz","zzzzzz"));

    }
    static int length, no_of_words;
    static Queue<Integer> bfs_q;
    static ArrayList<Boolean> visited;
    static Map<String,Integer> position;
    static Map<Integer,Integer> parent;

    public static ArrayList<String> string_transformation(ArrayList<String> words, String start, String stop){

        if(words.contains(stop)){
            words.remove(stop);
        }

        words.add(stop);
        bfs_q=new LinkedList<>();
        length=start.length();
        no_of_words=words.size();
        visited=new ArrayList<>(Collections.nCopies(no_of_words,false));
        parent=new HashMap<>();
        position=new HashMap();

        bfs(words,start,stop);

        int stop_index=no_of_words-1;
        ArrayList<String> result=new ArrayList();

        if(!parent.containsKey(stop_index)){
            result.add("-1");
            return result;
        }

        while(stop_index!=-1){
            result.add(words.get(stop_index));
            stop_index=parent.get(stop_index);
        }

        result.add(start);
        Collections.reverse(result);

        return result;
    }

    static void bfs(ArrayList<String> words, String start, String stop){
        if(no_of_words>length*26){
            for(int i=0;i<words.size();i++){
                position.put(words.get(i),i);
            }
        }

        bfs_q.add(-1);
        while(!bfs_q.isEmpty()){
            int index=bfs_q.poll();
            String current_word;
            if(index==-1){
                current_word=start;
            }else{
                current_word=words.get(index);

            }

            if(no_of_words<=length*26){
                add_neighbors_with_word_compare(words,current_word,index);
            }else{
                add_neighbors_with_all_aplphabet_compare(current_word,index);
            }

        }
    }

    static void add_neighbors_with_word_compare(ArrayList<String> words, String current_word, int index){
        for(int i=0;i<no_of_words;i++){
            if(!visited.get(i) && is_one_char_difference(length,current_word, words.get(i))){
                visited.set(i,true);
                parent.put(i,index);
                if(i==no_of_words-1){
                    return;
                }
                bfs_q.add(i);
            }
        }
    }

    static boolean is_one_char_difference(int length, String current_word, String word){
        int diff=0;
        for(int i=0;i<length;i++){
            if(current_word.charAt(i)!=word.charAt(i)){
                if(diff==1){
                    return false;
                }
                diff++;
            }

        }
        return diff==1;
    }

    static void add_neighbors_with_all_aplphabet_compare(String current_word, int index){
        StringBuilder temp_str=new StringBuilder(current_word);
        for(int i=0;i<length;i++){
            for(char ch='a'; ch<='z';ch++){
                if(ch==current_word.charAt(i)){
                    continue;
                }
                char original_character= temp_str.charAt(i);
                temp_str.setCharAt(i,ch);
                int temp_index=position.getOrDefault(temp_str.toString(),-1);
                if(temp_index!=-1){
                    if(!visited.get(temp_index)){
                        visited.set(temp_index,true);
                        parent.put(temp_index,index);
                        if(temp_index==no_of_words-1){
                            return;
                        }
                        bfs_q.add(temp_index);
                    }
                }
                temp_str.setCharAt(i,original_character);
            }

        }
    }

}
