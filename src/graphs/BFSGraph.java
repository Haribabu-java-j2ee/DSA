package graphs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class BFSGraph {
    public static void main(String[] args) {
        int[][] edges = {
                {0, 1},
                {1, 4},
                {2, 3},
                {5, 6},
                {6, 7},
                {5, 7}
        };
        ArrayList<ArrayList<Integer>> edgeLst = new ArrayList<>();
        Arrays.stream(edges).forEach(e -> {
            ArrayList<Integer> edge = new ArrayList<>();
            Arrays.stream(e).forEach(edge::add);
            edgeLst.add(edge);
        });
        System.out.println(bfs_traversal(8, edgeLst));
    }


    static ArrayList<Integer> bfs_traversal(Integer n, ArrayList<ArrayList<Integer>> edges) {
        // Write your code here.
        ArrayList<ArrayList<Integer>> adjList = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            adjList.add(new ArrayList());
        }
        for (ArrayList<Integer> edge : edges) {
            adjList.get(edge.get(0)).add(edge.get(1));
            adjList.get(edge.get(1)).add(edge.get(0));
        }
        ArrayList<Integer> visited = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            if (!visited.contains(i)) {
                bfs(i, visited, adjList);
            }
        }

        return visited;
    }

    static void bfs(int src, ArrayList<Integer> visited, ArrayList<ArrayList<Integer>> adjList) {
        visited.add(src);

        Queue<Integer> queue = new LinkedList<>();
        queue.add(src);
        while (!queue.isEmpty()) {
            int current = queue.poll();
            ArrayList<Integer> neighbors = adjList.get(current);
            for (int neighbor : neighbors) {
                if (!visited.contains(neighbor)) {
                    queue.add(neighbor);
                    visited.add(neighbor);

                }
            }
        }

    }

}
