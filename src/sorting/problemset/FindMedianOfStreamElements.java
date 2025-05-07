package sorting.problemset;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.PriorityQueue;

public class FindMedianOfStreamElements {
    public static void main(String[] args) {
        int[] arr = {3, 8, 5, 2};
        ArrayList<Integer> list = new ArrayList<>();
        Arrays.stream(arr).forEach(list::add);
        //online_median(list).forEach(System.out::println);
        //online_median1(list).forEach(System.out::println);
        online_median2(list).forEach(System.out::println);
    }


    static ArrayList<Integer> online_median(ArrayList<Integer> stream) {
        // Write your code here.
        ArrayList<Integer> temp = new ArrayList<>();
        ArrayList<Integer> result = new ArrayList<>();
        for (int i : stream) {
            temp.add(i);
            Collections.sort(temp);
            int length = temp.size() / 2;
            if (temp.size() % 2 == 0) {
                result.add((temp.get(length - 1) + temp.get(length)) / 2);
            } else {
                result.add(temp.get(length));
            }
        }
        return result;
    }

    //heap solution
    static ArrayList<Integer> online_median1(ArrayList<Integer> stream) {
        // Write your code here.
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());

        ArrayList<Integer> median = new ArrayList<>();
        for (int i : stream) {
            maxHeap.add(i);
            minHeap.add(maxHeap.poll());

            if (minHeap.size() > maxHeap.size()) {
                maxHeap.add(minHeap.poll());
            }

            if (minHeap.size() == maxHeap.size()) {
                median.add((minHeap.peek() + maxHeap.peek()) / 2);
            } else {
                median.add(maxHeap.peek());
            }
        }

        return median;
    }


    static ArrayList<Integer> online_median2(ArrayList<Integer> stream) {
        // Write your code here.

        ArrayList<Integer> median = new ArrayList<>();

        for (int i = 0; i < stream.size(); i++) {
            for (int j = i - 1; j >= 0; j--) {
                if (stream.get(j + 1) < stream.get(j)) {
                    Collections.swap(stream, j + 1, j);
                } else {
                    break;
                }
            }
            int length = i + 1;
            if (length % 2 == 0) {
                median.add((stream.get(length / 2) + stream.get(length / 2 - 1)) / 2);
            } else {
                median.add(stream.get(length / 2));
            }

        }

        return median;
    }

}
