package graphs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class GraphIsValidTree {
    public static void main(String[] args) {
        int[][] edges = {{0, 1}, {0, 2}, {0, 3}, {1, 4}};
        int n = 5;
        System.out.println(isValidTree(n, edges));
        int[][] edges1 = {{0, 1}, {0, 2}, {1, 2}, {0,3},{3,4}};
        System.out.println(isValidTree(n, edges1));
        int[][] edges2 = {{0, 1}, {0, 2}, {0,3},{3,4}};
        System.out.println(isValidTree(n, edges2));
        int[][] edges3 = {{1, 4}, {2, 4}, {3, 1}, {3, 2}};
        System.out.println(isValidTree(n, edges3));
    }

    private static boolean isValidTree(int n, int[][] edges) {
        ArrayList<ArrayList<Integer>> adjList = new ArrayList<>();
        Set<Integer> visited = new HashSet<>();
        int[] parent = new int[n];
        Arrays.fill(parent, -1);
        for (int i = 0; i < n; i++) {
            adjList.add(new ArrayList<>());
        }
        for (int[] edge : edges) {
            int src = edge[0];
            int dest = edge[1];
            adjList.get(src).add(dest);
            adjList.get(dest).add(src);
        }
        boolean isCycle = checkCycle(0, visited, adjList, parent);
        boolean singleComponent = true;
        if (visited.size() != n) {
            singleComponent = false;
        }
        if (!isCycle && singleComponent) {
            return true;
        }
        return false;
    }

    private static boolean checkCycle(int src, Set<Integer> visited, ArrayList<ArrayList<Integer>> adjList, int[] parent) {
        visited.add(src);
        ArrayList<Integer> neighbours = adjList.get(src);
        for (Integer neighbour : neighbours) {
            if (!visited.contains(neighbour)) {
                visited.add(neighbour);
                parent[neighbour] = src;
                checkCycle(neighbour, visited, adjList, parent);
            } else if (parent[src] != neighbour) {
                return true;
            }
        }
        return false;
    }
}
