package graphs;

import java.util.ArrayList;
import java.util.Arrays;

public class BuildAdjMatrix {
    public static void main(String[] args) {
        int[][] edges = {
                {0, 1},
                {1, 4},
                {1, 2},
                {1, 3},
                {3, 4}
        };
        ArrayList<ArrayList<Integer>> edgeLst = new ArrayList<>();
        Arrays.stream(edges).forEach(e -> {
            ArrayList<Integer> edge = new ArrayList<>();
            Arrays.stream(e).forEach(edge::add);
            edgeLst.add(edge);
        });
        convert_edge_list_to_adjacency_matrix(5, edgeLst).forEach(System.out::println);
    }

    static ArrayList<ArrayList<Boolean>> convert_edge_list_to_adjacency_matrix(Integer n, ArrayList<ArrayList<Integer>> edges) {
        // Write your code here.
        ArrayList<ArrayList<Boolean>> adjMatrix = new ArrayList<>();


        for (int i = 0; i < n; i++) {
            adjMatrix.add(new ArrayList<>());
            for (int j = 0; j < n; j++) {
                adjMatrix.get(i).add(false);
            }
        }

        for (ArrayList<Integer> edge : edges) {
            int i = edge.get(0);
            int j = edge.get(1);
            adjMatrix.get(i).set(j, true);
            adjMatrix.get(j).set(i, true);
        }
        return adjMatrix;
    }

}
