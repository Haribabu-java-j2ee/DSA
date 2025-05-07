package sorting.problemset;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.PriorityQueue;

public class FindKthLargestInStream {
    public static void main(String[] args) {
        int[] initialArr = {4, 6};
        int[] streamArr = {5, 2, 20};
        ArrayList<Integer> initialArrList = new ArrayList<>();
        Arrays.stream(initialArr).forEach(initialArrList::add);
        ArrayList<Integer> streamArrList = new ArrayList<>();
        Arrays.stream(streamArr).forEach(streamArrList::add);
        kth_largest(2, initialArrList, streamArrList).forEach(System.out::println);
       // kth_largest2(2, initialArrList, streamArrList).forEach(System.out::println);
    }

    //brute force O((m+n)*log(m+n))
    // extra space for append_stream result
    static ArrayList<Integer> kth_largest(Integer k, ArrayList<Integer> initial_stream, ArrayList<Integer> append_stream) {
        // Write your code here.
        Collections.sort(initial_stream);

        ArrayList<Integer> result = new ArrayList<>();
        for (int i : append_stream) {
            initial_stream.add(i);
            Collections.sort(initial_stream);
            result.add(initial_stream.get(initial_stream.size() - k));
        }
        return result;
    }

//max heap
    static ArrayList<Integer> kth_largest1(Integer k, ArrayList<Integer> initial_stream, ArrayList<Integer> append_stream) {
        // Write your code here.
        PriorityQueue<Integer> pq=new PriorityQueue<>();

        for(int i:initial_stream){
            pq.add(i);
            if(pq.size()>k){
                pq.poll();
            }
        }
        ArrayList<Integer> result=new ArrayList<>();
        for(int i:append_stream){
            pq.add(i);
            if(pq.size()>k){
                pq.poll();
            }
            result.add(pq.peek());
        }
        return result;
    }



}
