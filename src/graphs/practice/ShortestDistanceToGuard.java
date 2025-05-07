package graphs.practice;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.stream.Collectors;

public class ShortestDistanceToGuard {
    public static void main(String[] args) {
        char[][] grid = {{'O', 'O', 'O', 'O', 'G'},
                {'O', 'W', 'W', 'O', 'O'},
                {'O', 'O', 'O', 'W', 'O'},
                {'G', 'W', 'W', 'W', 'O'},
                {'O', 'O', 'O', 'O', 'G'}};
        ArrayList<ArrayList<Character>> adjList = new ArrayList<>();
        Arrays.stream(grid).forEach(arr -> {
            ArrayList<Character> list = new String(arr).chars().mapToObj(c -> (char)c).collect(Collectors.toCollection(ArrayList::new));

            adjList.add(list);
        });
        System.out.println(find_shortest_distance_from_a_guard(adjList));
    }

    static char GUARD = 'G';
    static char OPEN = 'O';

    static ArrayList<ArrayList<Integer>> find_shortest_distance_from_a_guard(ArrayList<ArrayList<Character>> grid) {
        // Write your code here.
        int n = grid.size();
        int m = grid.get(0).size();
        ArrayList<ArrayList<Integer>> distance = new ArrayList();
        boolean[][] visited = new boolean[n][m];
        for (int i = 0; i < n; i++) {
            distance.add(new ArrayList());
            for (int j = 0; j < m; j++) {
                distance.get(i).add(-1);
            }
        }

        Queue<Node> queue = new LinkedList();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (grid.get(i).get(j) == GUARD) {
                    queue.add(new Node(i, j, 0));
                }
            }
        }

        while (!queue.isEmpty()) {
            Node removedNode = queue.poll();

            if (visited[removedNode.x][removedNode.y]) {
                continue;
            }
            visited[removedNode.x][removedNode.y] = true;
            distance.get(removedNode.x).set(removedNode.y, removedNode.distance);
            for (Node neighbor : getNeighbor(n, m, removedNode, grid)) {

                neighbor.distance = removedNode.distance + 1;
                queue.add(neighbor);

            }
        }

        return distance;
    }

    static ArrayList<Node> getNeighbor(int n, int m, Node node, ArrayList<ArrayList<Character>> grid) {
        ArrayList<Node> nodeList = new ArrayList();
        if (isValid(node.x + 1, node.y, n, m, grid)) {
            nodeList.add(new Node(node.x + 1, node.y, 0));
        }
        if (isValid(node.x - 1, node.y, n, m, grid)) {
            nodeList.add(new Node(node.x - 1, node.y, 0));
        }
        if (isValid(node.x, node.y + 1, n, m, grid)) {
            nodeList.add(new Node(node.x, node.y + 1, 0));
        }
        if (isValid(node.x, node.y - 1, n, m, grid)) {
            nodeList.add(new Node(node.x, node.y - 1, 0));
        }
        return nodeList;
    }

    static boolean isValid(int x, int y, int n, int m, ArrayList<ArrayList<Character>> grid) {
        if (x < 0 || y < 0 || x >= n || y >= m || grid.get(x).get(y) != OPEN) {
            return false;
        } else {
            return true;
        }
    }

    static class Node {
        int x;
        int y;
        int distance;

        Node(int x, int y, int distance) {
            this.x = x;
            this.y = y;
            this.distance = distance;
        }
    }

}
