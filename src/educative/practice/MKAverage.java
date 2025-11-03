package educative.practice;

import java.util.*;

public class MKAverage {
    public static void main(String[] args) {

        MKAverage mkAverage = new MKAverage();
        String[] cmds = {"MKAverage", "addElement", "addElement", "calculateMKAverage", "addElement", "calculateMKAverage", "addElement", "addElement", "addElement", "calculateMKAverage"};
        int[][] arr = {{3, 1}, {3}, {1}, {}, {10}, {}, {5}, {5}, {5}, {}};
        int counter = 0;
        for (String cmd : cmds) {
            switch (cmd) {
                case "addElement":
                    mkAverage.addElement(arr[counter][0]);
                    System.out.println("null");
                    break;
                case "MKAverage":
                    new MKAverage(arr[counter][0], arr[counter][1]);
                    System.out.println("null");

                    break;
                case "calculateMKAverage":
                    System.out.println(mkAverage.calculateMKAverage());
                    break;
            }
            counter++;
        }

    }

    List<Integer> numsStream = new ArrayList<>();
    static Map<Integer, Integer> map = new TreeMap<>();
    static int sizeM;
    static int sizeK;
    static int sum = 0;

    public MKAverage() {
    }

    public MKAverage(int m, int k) {
        sizeM = m;
        sizeK = k;
        // Write your code here
    }

    public void addElement(int num) {
        map.put(num, map.getOrDefault(num, 0) + 1);
        numsStream.add(num);
        sum += num;
        if (numsStream.size() > sizeK) {
            int element = numsStream.remove(0);
            sum -= element;
            if (map.get(element) == 0) {
                map.remove(element);
            } else {
                map.put(element, map.get(element) - 1);
            }
        }
        // Write your code here
    }

    public int calculateMKAverage() {
        if(numsStream.size()<sizeM){
            return -1;
        }
        
        return sum/numsStream.size();
    }


}