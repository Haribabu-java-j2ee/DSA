package sorting.problemset;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.PriorityQueue;

public class FindKthLargestInArr {
    public static void main(String[] args) {
        int[] arr= {5, 1, 10, 3, 2};
        int k=2;
        ArrayList<Integer> arrList=new ArrayList<>();
        Arrays.stream(arr).forEach(arrList::add);
        System.out.println(kth_largest_in_an_array1(arrList,k));
    }

    //O(nlongn)
    static Integer kth_largest_in_an_array(ArrayList<Integer> numbers, Integer k) {
        // Write your code here.
        Collections.sort(numbers);

        return numbers.get(numbers.size()-k);
    }


    //Min Heap
    static Integer kth_largest_in_an_array1(ArrayList<Integer> numbers, Integer k) {
        // Write your code here.
        PriorityQueue<Integer> pq=new PriorityQueue<>();
        for(int i:numbers){
            pq.add(i);
            if(pq.size()>k){
                pq.poll();
            }
        }
        return  pq.peek();
    }

    static Integer kth_largest_in_an_array2(ArrayList<Integer> numbers, Integer k) {
        int low=0;
        int high=numbers.size()-1;
        while(low<high){
            int pivot=partition(numbers,low,high);
            if(pivot==numbers.size()-k){
                return numbers.get(pivot);
            }else if(pivot<numbers.size()-k){
                low=pivot+1;
            }else{
                high=pivot-1;
            }
        }
        return -1;
    }

    private static int partition(ArrayList<Integer> numbers, int low, int high) {
        int randomIndex=(int)(Math.random()*(high-low+1)+low);
        int pivot=low;
        Collections.swap(numbers,randomIndex,high);
        int pivotValue=numbers.get(high);
        for(int i=low;i<high;i++){
            if(numbers.get(i)<=pivotValue){
                Collections.swap(numbers,i,pivot);
                pivot++;
            }
        }
        Collections.swap(numbers,pivot,high);
        return pivot;
    }
}
