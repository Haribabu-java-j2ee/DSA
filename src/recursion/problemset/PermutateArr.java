package recursion.problemset;

import java.util.*;

public class PermutateArr {
    public static void main(String[] args) {
        int[] arr = {1, 2, 2};
        ArrayList<Integer> list = new ArrayList<>();
        Arrays.stream(arr).forEach(list::add);
        get_permutations(list).forEach(System.out::println);
    }


    static ArrayList<ArrayList<Integer>> get_permutations(ArrayList<Integer> arr) {
        // Write your code here.
        ArrayList<ArrayList<Integer>> solutions=new ArrayList<>();
        get_permutations_util(solutions, arr, 0);
        return solutions;
    }

    static void get_permutations_util(ArrayList<ArrayList<Integer>> solutions,ArrayList<Integer> arr, int fixedIndex ){
        if(fixedIndex>=arr.size()){
            solutions.add(new ArrayList(arr));
            return;
        }

        Set<Integer> isPlaced=new HashSet<>();
        for(int i=fixedIndex;i<arr.size();i++){
            if(!isPlaced.contains(arr.get(i))){
                isPlaced.add(arr.get(i));
                Collections.swap(arr,fixedIndex,i);
                get_permutations_util(solutions, arr, fixedIndex+1);
                Collections.swap(arr,fixedIndex,i);

            }
        }
    }


}
