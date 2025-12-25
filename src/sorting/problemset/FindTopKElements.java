package sorting.problemset;

import java.util.*;
import java.util.stream.Collectors;

public class FindTopKElements {
    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 2, 4, 3, 1};
        ArrayList<Integer> list = new ArrayList<>();
        Arrays.stream(arr).forEach(s -> list.add(s));
        System.out.println(find_top_k_frequent_elements(list, 2));
        System.out.println(find_top_k_frequent_elements1(list, 2));
        System.out.println(find_top_k_frequent_elements2(list, 2));
    }

    //hashing
    //O(nlogn) for sorting based on counter time complexity
    //O(n+k) including output
    static ArrayList<Integer> find_top_k_frequent_elements(ArrayList<Integer> arr, Integer k) {
        // Write your code here.
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < arr.size(); i++) {
            map.put(arr.get(i), map.getOrDefault(arr.get(i), 0) + 1);
        }
        HashMap<Integer, Integer> temp = map.entrySet().stream().sorted((i1, i2) ->
                i2.getValue().compareTo(i1.getValue())).collect(
                Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue,
                        (e1, e2) -> e1, LinkedHashMap::new));

        ArrayList<Integer> out = new ArrayList<>();
        for (Map.Entry<Integer, Integer> entry : temp.entrySet()) {
            if (k == 0) {
                break;
            }
            out.add(entry.getKey());
            k--;
        }
        return out;
    }

    //min heap
//nlogn time
    //
    static ArrayList<Integer> find_top_k_frequent_elements1(ArrayList<Integer> arr, Integer k) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i : arr) {
            map.put(i, map.getOrDefault(i, 0) + 1);
        }
        PriorityQueue<Pair> pq = new PriorityQueue<>();
        for (int element : map.keySet()) {
            int count = map.get(element);
            pq.add(new Pair(element, count));
            if (pq.size() > k) {
                pq.poll();
            }
        }
        ArrayList<Integer> out = new ArrayList<>();
        for (int i = 0; i < k; i++) {
            out.add(pq.peek().element);
            pq.poll();
        }
        return out;
    }

    static class Pair implements Comparable<Pair> {
        int element;
        int count;

        public int compareTo(Pair o) {
            return this.count - o.count;
        }

        public Pair(int element, int count) {
            this.element = element;
            this.count = count;
        }
    }


    //quick select algo
    /*
     * Asymptotic complexity in terms of size of `arr` `n` and `k`:
     * Time: O(n^2).
     * Auxiliary space: O(n).
     * Total space: O(n + k).
     */
    static ArrayList<Integer> find_top_k_frequent_elements2(ArrayList<Integer> arr, Integer k) {
        HashMap<Integer, Integer> frequency = new HashMap<>();
        for (int i : arr) {
            frequency.put(i, frequency.getOrDefault(i, 0) + 1);
        }
        List<Integer> uniqueKeys = new ArrayList<>();
        for (int element : frequency.keySet()) {
            uniqueKeys.add(element);
        }
        ArrayList<Integer> out = new ArrayList<>();
        quick_select(uniqueKeys, k, frequency);
        for (int i = uniqueKeys.size() - k; i < uniqueKeys.size(); i++) {
            out.add(uniqueKeys.get(i));
        }
        return out;
    }

    private static void quick_select(List<Integer> uniqueKeys, Integer k, HashMap<Integer, Integer> frequency) {
        int low = 0;
        int high = uniqueKeys.size() - 1;
        while (low < high) {
            int pivot = quick_select_sort(uniqueKeys, low, high, frequency);
            if (pivot == uniqueKeys.size() - k) {
                return;
            } else if (pivot > uniqueKeys.size() - k) {
                high = pivot - 1;
            } else {
                low = pivot + 1;
            }
        }
    }

    private static int quick_select_sort(List<Integer> uniqueKeys, int low, int high, HashMap<Integer, Integer> frequency) {
        int pivot = low;
        int pivotIndex = (int) (Math.random() * (high - low + 1) + low);
        int pivotValue = frequency.get(uniqueKeys.get(pivotIndex));
        Collections.swap(uniqueKeys, pivotIndex, high);

        for (int i = low; i < high; i++) {
            if (frequency.get(uniqueKeys.get(i)) <= pivotValue) {
                Collections.swap(uniqueKeys, pivot, i);
                pivot++;
            }
        }
        Collections.swap(uniqueKeys, pivot, high);
        return pivot;
    }

    //https://leetcode.com/problems/top-k-frequent-elements/
    public int[] topKFrequent(int[] nums, int k) {
        int[] result=new int[k];
        PriorityQueue<int[]> topKelements=new PriorityQueue<>((a,b)->a[0]==b[0]?a[1]-b[1]:a[0]-b[0]);
        Map<Integer,Integer> frequency=new HashMap<>();
        for(int element:nums){
            frequency.put(element, frequency.getOrDefault(element,0)+1);
        }

        for(Map.Entry<Integer,Integer> freqEntry:frequency.entrySet()){
            topKelements.add(new int[]{freqEntry.getValue(),freqEntry.getKey()});
            if(topKelements.size()>k){
                topKelements.poll();
            }
        }
        int i=0;
        while(!topKelements.isEmpty()){
            result[i++]=topKelements.poll()[1];
        }
        return result;
    }
}
