package dsapatterns.mergeintervals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

//https://leetcode.com/problems/merge-intervals/
public class MergeIntervals {
    public static void main(String[] args) {
        MergeIntervals obj=new MergeIntervals();
        int[][] intervals={{1,3},{2,6},{8,10},{15,18}};
        Arrays.stream(obj.merge(intervals)).forEach(interval->{
            System.out.println();
            Arrays.stream(interval).forEach(e-> System.out.print(e+" "));});
        System.out.println("method 2");
        Arrays.stream(obj.mergeWithCollection(intervals)).forEach(interval->{
            System.out.println();
            Arrays.stream(interval).forEach(e-> System.out.print(e+" "));});
    }

    public int[][] merge(int[][] intervals) {
        Arrays.sort(intervals, Comparator.comparingInt(a -> a[0]));
        int[][] result=new int[intervals.length][2];
        result[0]=intervals[0];
        int j=0;
        for(int i=1;i<intervals.length;i++){
            int[] last=result[j];
            int[] current=intervals[i];
            if(current[0]<=last[1]){
                result[j]=new int[]{last[0],current[1]};
            }else{
                result[++j]=current;
            }
        }
        return result;
    }

    public int[][] mergeWithCollection(int[][] intervals) {
        Arrays.sort(intervals, Comparator.comparingInt(a -> a[0]));
        // here we dont need secondary comparision , but incase if needed below is the code
       // Arrays.sort(intervals, Comparator.comparingInt((int[] a) -> a[0]).thenComparingInt(a -> a[1]));
        List<int[]> result=new ArrayList<>();
        result.add(intervals[0]);

        for(int i=1;i<intervals.length;i++){
            int[] last=result.get(result.size()-1);
            int[] current=intervals[i];
            if(current[0]<=last[1]){
                last[1]=Math.max(last[1],current[1]);
            }else{
                result.add(current);
            }
        }
        return result.toArray(new int[result.size()][]);
    }
}
