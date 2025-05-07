package graphs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class CourseCompleteOrder {

    public static void main(String[] args) {
        int[][] prerequisites = {{1, 0}, {2, 0}, {3, 1}, {3, 2}};
        Arrays.stream(findOrder(4, prerequisites)).forEach(System.out::println);
    }

    public static int[] findOrder(int numCourses, int[][] prerequisites) {
        boolean[] arrival = new boolean[numCourses];
        boolean[] departure = new boolean[numCourses];
        ArrayList<ArrayList<Integer>> adjList = new ArrayList<>();
        for (int i = 0; i < numCourses; i++) {
            adjList.add(new ArrayList());
        }
        for (int[] prerequisite : prerequisites) {
            int start = prerequisite[0];
            int end = prerequisite[1];
            adjList.get(end).add(start);
        }

        ArrayList<Integer> result = new ArrayList<>();
        for (int i = 0; i < numCourses; i++) {
            if (!arrival[i] && isCycle(i, result, arrival, departure, adjList)) {
                return new int[0];
            }
        }

        if (!result.isEmpty()) {
            Collections.reverse(result);
            return result.stream().mapToInt(Integer::intValue).toArray();

        }
        return new int[0];
    }

    static boolean isCycle(int src, ArrayList<Integer> result, boolean[] arrival, boolean[] departure, ArrayList<ArrayList<Integer>> adjList) {
        arrival[src] = true;

        for (int neighbor : adjList.get(src)) {
            if (arrival[neighbor] && !departure[neighbor]) {
                return true;
            } else if (!arrival[neighbor] && isCycle(neighbor, result, arrival, departure, adjList)) {
                return true;
            }
        }
        departure[src] = true;
        result.add(src);
        return false;
    }
}
