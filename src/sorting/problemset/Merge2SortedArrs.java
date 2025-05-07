package sorting.problemset;

import java.util.ArrayList;
import java.util.Collections;
/**
 *
 * First array has n positive numbers, and they are sorted in the non-descending order.
 *
 * Second array has 2n numbers: first n are also positive and sorted in the same way but the last n are all zeroes.
 *
 * Merge the first array into the second and return the latter. You should get 2n positive integers sorted in the non-descending order.
 *
 * INPUT:
 *  {
 * "first": [1, 3, 5],
 * "second": [2, 4, 6, 0, 0, 0]
 * }
 *
 * Output:
 *
 * [1, 2, 3, 4, 5, 6]
 * */
public class Merge2SortedArrs {
    public static void main(String[] args) {

        int[] first={11, 14, 24, 34, 44, 45, 45, 47, 61, 64, 72, 82, 103, 104, 122, 122, 131, 138, 145, 148, 150, 151, 185, 198};
        int[] second={6, 9, 17, 19, 28, 33, 35, 36, 64, 76, 87, 89, 107, 110, 112, 121, 129, 132, 139, 146, 151, 152, 154, 175, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
        ArrayList<Integer> first1 = new ArrayList<>();
       for(int i:first){
           first1.add(i);
       }
        ArrayList<Integer> second1 = new ArrayList<>();
        for(int i:second){
            second1.add(i);
        }
        merge_one_into_another(first1,second1);
        second1.stream().forEach(s->System.out.print(s+","));
    }


    static void merge_one_into_another(ArrayList<Integer> first, ArrayList<Integer> second) {
        int n=second.size();
        for(int i=0;i<n/2;i++){
            Collections.swap(second,i,(n/2)+i);
        }
        int n1=first.size();
        int n2=second.size();

        int i=0,j=n/2;
        int k=0;
        while(i<n1 && j<n2){
            if(first.get(i)<=second.get(j)){
                second.set(k,first.get(i));
                i++;
            }else{
                second.set(k,second.get(j));
                second.set(j,0);
                j++;
            }
            k++;
        }
    }
}
