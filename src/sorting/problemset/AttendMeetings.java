package sorting.problemset;

import java.util.*;

/**
 * Given a list of meeting intervals where each interval consists of a start and an end time,
 * check if a person can attend all the given meetings such that only one meeting can be attended at a time.
 * <p>
 * input:
 * {
 * "intervals": [
 * [1, 5],
 * [5, 8],
 * [10, 15]
 * ]
 * }
 * <p>
 * Output:
 * 1
 */
public class AttendMeetings {
    public static void main(String[] args) {
        int[][] arr = {
                {1, 5},
                {10, 15},
                {5, 8}
        };

        ArrayList<ArrayList<Integer>> intervals = new ArrayList<>();
        for (int i = 0; i < arr.length; i++) {
            ArrayList<Integer> interval = new ArrayList<>();
            for (int j = 0; j < arr[i].length; j++) {
                interval.add(arr[i][j]);
            }
            intervals.add(interval);
        }
        System.out.println(can_attend_all_meetings(intervals));
    }

    //O(n)
    static Integer can_attend_all_meetings(ArrayList<ArrayList<Integer>> intervals) {
        Collections.sort(intervals, (a, b) -> {
            if (a.get(0).equals(b.get(0))) {
                return a.get(1) - b.get(1);
            } else return a.get(0) - b.get(0);
        });
        for (int i = 0; i < intervals.size() - 1; i++) {
            int end_time_current_interval = intervals.get(i).get(1);
            int start_time_next_interval = intervals.get(i + 1).get(0);

            // If overlap found, return 0.
            if (end_time_current_interval > start_time_next_interval)
                return 0;
        }
        return 1;
    }

}
