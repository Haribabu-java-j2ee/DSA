package graphs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class CheckEulerianPath {
    public static void main(String[] args) {
        int n = 4;
        int[][] edges = {
                {0, 1},
                {1, 2},
                {1, 3},
                {2, 0},
                {3, 2}
        };
        ArrayList<ArrayList<Integer>> edgeLst = new ArrayList<>();
        Arrays.stream(edges).forEach(e -> {
            ArrayList<Integer> edge = new ArrayList<>();
            Arrays.stream(e).forEach(edge::add);
            edgeLst.add(edge);
        });
        System.out.println(check_if_eulerian_path_exists(n, edgeLst));
    }

    static Boolean check_if_eulerian_path_exists(Integer n, ArrayList<ArrayList<Integer>> edges) {
        // Write your code here.
        if (edges.isEmpty()) {
            return true;
        }
        Map<Integer, Integer> node_degeree = new HashMap<>();
        for (ArrayList<Integer> edge : edges) {
            node_degeree.put(edge.get(0), node_degeree.getOrDefault(edge.get(0), 0) + 1);
            node_degeree.put(edge.get(1), node_degeree.getOrDefault(edge.get(1), 0) + 1);
        }

        int oddDegreeCount = 0;
        for (Map.Entry<Integer, Integer> entry : node_degeree.entrySet()) {
            if (entry.getValue() % 2 != 0) {
                oddDegreeCount++;
            }
        }
        return (oddDegreeCount == 2 || oddDegreeCount == 0);
    }

}
