package dsapatterns.mergeintervals;

import java.util.Arrays;
import java.util.Comparator;

//https://leetcode.com/problems/non-overlapping-intervals/
// we need see for a solution on quick sort if possible to beat the time
public class NonOverlappingIntervals {
    public static void main(String[] args) {
        NonOverlappingIntervals obj = new NonOverlappingIntervals();
        int[][] intervals = {{1, 2}, {2, 3}, {3, 4}, {1, 3}};
        System.out.println(obj.eraseOverlapIntervals(intervals));
        System.out.println(obj.eraseOverlapIntervalsWithEndintervalSorting(intervals));

    }

    public int eraseOverlapIntervals(int[][] intervals) {
        int count = 0;
        Arrays.sort(intervals, Comparator.comparingInt(a -> a[0]));
        int end = intervals[0][1];
        for (int i = 1; i < intervals.length; i++) {
            if (intervals[i][0] < end) {
                count++;
                end = Math.min(end, intervals[i][1]);
            } else {
                end = intervals[i][1];
            }
        }
        return count;
    }


    public int eraseOverlapIntervalsWithEndintervalSorting(int[][] intervals) {
        int count = 0;
        Arrays.sort(intervals, Comparator.comparingInt(a -> a[1]));
        int end = intervals[0][1];
        for (int i = 1; i < intervals.length; i++) {
            if (intervals[i][0] < end) {
                count++;
            } else {
                end = intervals[i][1];
            }
        }
        return count;
    }
}
