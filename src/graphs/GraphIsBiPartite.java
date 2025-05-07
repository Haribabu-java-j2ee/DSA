package graphs;

import java.util.*;

public class GraphIsBiPartite {
    public static void main(String[] args) {
        int[][] adj = {{1, 2, 3}, {0, 2}, {0, 1, 3}, {0, 2}};
        int n = adj.length;

        ArrayList<ArrayList<Integer>> adjList = new ArrayList<>();
        Arrays.stream(adj).forEach(arr -> {
            ArrayList<Integer> list = new ArrayList<>();
            Arrays.stream(arr).forEach(list::add);
            adjList.add(list);
        });
        System.out.println(isBipartite(adjList, n));
        int[][] adj1 = {{1, 3}, {0, 2}, {1, 3}, {0, 2}};
        n = adj1.length;
        ArrayList<ArrayList<Integer>> adjList1 = new ArrayList<>();
        Arrays.stream(adj1).forEach(arr -> {
            ArrayList<Integer> list = new ArrayList<>();
            Arrays.stream(arr).forEach(list::add);
            adjList1.add(list);
        });
        System.out.println(isBipartite(adjList1, n));
    }

    private static boolean isBipartite(ArrayList<ArrayList<Integer>> adjList, int n) {
        Set<Integer> visited = new HashSet<>();
        int[] parent = new int[n];
        Arrays.fill(parent, -1);
        int[] distance = new int[n];
        for (int i = 0; i < n; i++) {
            if (!visited.contains(i)) {
                if (isOddCycle_bfs(i, adjList, parent, distance, visited)) {
                    return false;
                }
            }
        }
        return true;
    }

    //this solution is not working , hence deep seek provided a alternate using color indication
    static boolean isOddCycle(int src, ArrayList<ArrayList<Integer>> adjList, int[] parent, int[] distance, Set<Integer> visited) {
        visited.add(src);
        ArrayList<Integer> neighbours = adjList.get(src);
        for (int neighbour : neighbours) {
            if (!visited.contains(neighbour)) {
                parent[neighbour] = src;
                distance[neighbour] = distance[src] + 1;
                if(isOddCycle(neighbour, adjList, parent, distance, visited)){
                    return true;
                }
            } else if (parent[src] != neighbour && distance[neighbour] == distance[src]) {
                return true;
            }
        }
        return false;
    }

    static boolean isOddCycle_bfs(int src, ArrayList<ArrayList<Integer>> adjList, int[] parent, int[] distance, Set<Integer> visited) {
        Queue<Integer> queue = new LinkedList<>();
        queue.add(src);
        visited.add(src);

        while (!queue.isEmpty()) {
            int current = queue.poll();
            ArrayList<Integer> neighbours = adjList.get(current);
            for (int neighbour : neighbours) {
                if (!visited.contains(neighbour)) {
                    queue.add(neighbour);
                    visited.add(neighbour);
                    parent[neighbour] = current;
                    distance[neighbour] = distance[current] + 1;
                } else if (parent[current] != neighbour && distance[neighbour] == distance[current]) {
                    return true;
                }
            }
        }
        return false;
    }
}
