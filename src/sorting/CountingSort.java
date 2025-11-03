package sorting;

import java.util.*;

public class CountingSort {
    public static void main(String[] args) {
        int[] arr = {5, 8, 3, 9, 4, 1, 7, 1};
        int k = arr.length;
        int[] countArr = new int[10];
        for (int i = 0; i < k; i++) {
            countArr[arr[i]]++;
        }
        for (int i = 0; i < countArr.length; i++) {
            while (countArr[i] > 0) {
                System.out.print(i+" ");
                countArr[i]--;
            }
        }
        System.out.println("\n===========");
        ArrayList<Integer> arrayList = new ArrayList<>();
        arrayList.add(5);
        arrayList.add(8);
        arrayList.add(3);
        arrayList.add(9);
        arrayList.add(4);
        arrayList.add(1);
        arrayList.add(7);
        counting_sort(arrayList);
        arrayList.forEach(s->System.out.print(s+" "));
        System.out.println("\n===========+");
        countingSort1(arr).stream().forEach(s->System.out.print(s + " "));
        System.out.println("\nsorting String===========");
        System.out.println(countSort("edsab"));
    }

    //O(n+k) where k is range
    public static List<Integer> countingSort1(int[] arr){
        OptionalInt optionalMax= Arrays.stream(arr).max();
        int maxElement = optionalMax.isPresent()?optionalMax.getAsInt():Integer.MIN_VALUE;

        int[] countArr=new int[maxElement+1];
        for(int i=0;i< arr.length;i++){
            countArr[arr[i]]++;
        }

        List<Integer> result=new ArrayList();
        for(int i=0;i<=maxElement;i++){
            while(countArr[i]>0){
                result.add(i);
                countArr[i]--;
            }
        }
        return result;
    }

    public static ArrayList<Integer> counting_sort(ArrayList<Integer> arr) {
        int n = arr.size();

        // Find the minimum and maximum elements in the array
        int minElement = arr.get(0), maxElement = arr.get(0);
        for (int i = 1; i < n; i++) {
            int currentElement = arr.get(i);
            minElement = Math.min(minElement, currentElement);
            maxElement = Math.max(maxElement, currentElement);
        }

        // Create a frequency map to count the occurrences of each element
        Map<Integer, Integer> frequency = new HashMap<>();
        for (int i = 0; i < n; i++) {
            int currentElement = arr.get(i);
            frequency.put(currentElement, frequency.getOrDefault(currentElement, 0) + 1);
        }

        // Fill in the sorted array based on the frequencies
        int arrIndex = 0;
        for (int i = minElement; i <= maxElement; i++) {
            int currentFrequency = frequency.getOrDefault(i, 0);
            while (currentFrequency > 0) {
                arr.set(arrIndex++, i);
                currentFrequency--;
            }
        }

        return arr;
    }


    static ArrayList<Integer> counting_sort1(ArrayList<Integer> arr) {
        int n = arr.size();
        int min = arr.get(0), max = arr.get(0);
        for (int i = 1; i < n; i++) {
            min = Math.min(arr.get(i), min);
            max = Math.max(arr.get(i), max);
        }

        Map<Integer, Integer> countMap = new HashMap<>();
        for (int i = 0; i < n; i++) {
            countMap.put(arr.get(i), countMap.getOrDefault(arr.get(i), 0) + 1);
        }
        int j = 0;
        for (int i = min; i <= max; i++) {
            int curElement = countMap.getOrDefault(i, 0);
            while (curElement > 0) {
                arr.set(j++, i);
                curElement--;
            }
        }
        return arr;
    }


    //t(n) = O(n)
    //s(n) = O(n) for string stringbuilder output
    public static String countSort(String s) {
        // code here
        int[] count=new int[256];
        for(char ch:s.toCharArray()){
            count[ch]++;
        }
        StringBuilder sb=new StringBuilder();
        for(int i=0;i<count.length;i++){
            while(count[i]>0){
                sb.append((char)i);
                count[i]--;
            }
        }
        return String.valueOf(sb);
    }
}
