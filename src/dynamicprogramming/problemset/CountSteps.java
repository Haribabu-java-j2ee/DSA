package dynamicprogramming.problemset;

import java.util.ArrayList;

public class CountSteps {
    public static void main(String[] args) {
        ArrayList<Integer> arr = new ArrayList<>();
        arr.add(1);
        arr.add(2);
        arr.add(3);
        System.out.println(count_ways_to_climb(arr,7));
    }
    static Long count_ways_to_climb(ArrayList<Integer> steps, Integer n) {
        // Write your code here.
        long[] count=new long[n+1];
        count[0]=1;
        for(int i=1;i<=n;i++){
            for(int step:steps){
                int previousStep=i-step;
                if(previousStep>=0){
                    count[i]+=count[previousStep];
                }
            }
        }
        return count[n];
    }
}
