package dsapatterns.mergeintervals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

//https://leetcode.com/problems/insert-interval/
public class InsertInterval {
    public static void main(String[] args) {
        InsertInterval obj=new InsertInterval();
        int[][] intervals={{1,3},{6,9}};
        int[] newInterval={2,5};
        Arrays.stream(obj.insert(intervals,newInterval)).forEach(interval->{
            System.out.println();
            Arrays.stream(interval).forEach(e-> System.out.print(e+" "));});
        System.out.println();

        System.out.println("method 2");
        Arrays.stream(obj.insertOptimised(intervals,newInterval)).forEach(interval->{
            System.out.println();
            Arrays.stream(interval).forEach(e-> System.out.print(e+" "));});
    }
    public int[][] insert(int[][] intervals, int[] newInterval) {
        List<int[]> result=new ArrayList<>();
        int[][] mergedIntervals=new int[intervals.length+1][2];
        for(int i=0;i<intervals.length;i++){
            mergedIntervals[i]=intervals[i];
        }
        mergedIntervals[intervals.length]=newInterval;
        Arrays.sort(mergedIntervals, Comparator.comparingInt(a->a[0]));
        result.add(mergedIntervals[0]);
        for(int i=1;i<mergedIntervals.length;i++){
            int[] last=result.get(result.size()-1);
            int[] current=mergedIntervals[i];
            if(current[0]<=last[1]){
                last[1]=Math.max(last[1],current[1]);
            }else{
                result.add(current);
            }
        }
        return result.toArray(new int[0][]);
    }


    public int[][] insertOptimised(int[][] intervals, int[] newInterval) {
        List<int[]> result=new ArrayList<>();
        int i=0;
        int n=intervals.length;
        while(i<n && intervals[i][1]<newInterval[0]){
            result.add(intervals[i]);
            i++;
        }
        while(i<n && intervals[i][0]<=newInterval[1]){
            newInterval[0]=Math.min(intervals[i][0],newInterval[0]);
            newInterval[1]=Math.max(intervals[i][1],newInterval[1]);
            i++;
        }
        result.add(newInterval);
        while(i<n){
            result.add(intervals[i]);
            i++;
        }
        return result.toArray(new int[0][]);
    }
}
