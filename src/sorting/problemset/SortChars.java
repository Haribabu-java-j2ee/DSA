package sorting.problemset;

import java.util.ArrayList;

public class SortChars {
    public static void main(String[] args) {
        char[] chars = {'a', 's', 'd', 'f', 'g', '*', '&', '!', 'z', 'y'};
        ArrayList<Character> arr=new ArrayList<>();
        for(char c:chars){
            arr.add(c);
        }
        sort_array(arr);
        arr.forEach(System.out::println);
    }
    static ArrayList<Character> sort_array(ArrayList<Character> arr) {
        int n=arr.size();
        int[] count=new int[126];
        int[] output=new int[n];
        int tempChar = 0;
        for(int i=0;i<n;i++){
            tempChar=arr.get(i);
            count[tempChar]++;
        }

        for(int i=1;i<126;i++){
            count[i]+=count[i-1];
        }

        for(int i=n-1;i>=0;i--){
            tempChar=arr.get(i);
            output[count[tempChar]-1]=arr.get(i);
            count[tempChar]--;
        }

        for(int i=0;i<n;i++){
            char temp= (char) output[i];
            arr.set(i,temp);
        }

        return arr;
    }

}
