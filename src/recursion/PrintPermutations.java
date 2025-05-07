package recursion;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class PrintPermutations {
    public static void main(String[] args) {
        int[] arr = {1, 2, 3};
        ArrayList<Integer> list = new ArrayList<>();
        Arrays.stream(arr).forEach(list::add);
        ArrayList<Integer> list1 = new ArrayList<>();
        Arrays.stream(arr).forEach(list1::add);
        //get_permutations(list).forEach(System.out::println);
        get_permutations1(list1).forEach(System.out::println);
    }


    static ArrayList<ArrayList<Integer>> get_permutations(ArrayList<Integer> arr) {
        ArrayList<ArrayList<Integer>> result = new ArrayList<>();
        getPermutationsHelper(result, arr,0);
        return result;
    }

    private static void getPermutationsHelper(ArrayList<ArrayList<Integer>> result, ArrayList<Integer> arr, int fixedIndex) {
        if(fixedIndex>=arr.size()) {
            result.add(new ArrayList<>(arr));
        }else{
            for(int i=fixedIndex;i<arr.size();i++) {
                Collections.swap(arr, fixedIndex, i);
                getPermutationsHelper(result, arr, fixedIndex+1);
                Collections.swap(arr, fixedIndex, i);

            }
        }
    }

    //understandable solution
    static ArrayList<ArrayList<Integer>> get_permutations1(ArrayList<Integer> arr) {
        ArrayList<ArrayList<Integer>> result = new ArrayList<>();
        getPermutationsHelper1(result, arr,new ArrayList<>());
        return result;
    }

    private static void getPermutationsHelper1(ArrayList<ArrayList<Integer>> result, ArrayList<Integer> arr,ArrayList<Integer> slate) {
        if(arr.size()==0) {
            result.add(new ArrayList<>(slate));
        }else{
            for(int i=0;i<arr.size();i++) {
                ArrayList<Integer> tempList = new ArrayList<>(arr);
                tempList.remove(i);
                ArrayList<Integer> newSlate = new ArrayList<>(slate);
                newSlate.add(arr.get(i));

                getPermutationsHelper1(result, tempList,newSlate);
            }
        }
    }





}
