package graphs.practice;

import java.util.ArrayList;
import java.util.Arrays;

public class CriticalConnections {
    static int timestamp;
    static Integer[] arrivals;
    static Integer[] departure;

    public static void main(String[] args) {
        int[][] connections = {{0, 1},
                {0, 2},
                {0, 4},
                {1, 2},
                {1, 3}};
        ArrayList<ArrayList<Integer>> connectionList = new ArrayList<>();
        Arrays.stream(connections).forEach(arr -> {
            ArrayList<Integer> list = new ArrayList<>();
            Arrays.stream(arr).forEach(list::add);
            connectionList.add(list);
        });
        find_critical_connections(5, connectionList).forEach(System.out::println);
    }

    static ArrayList<ArrayList<Integer>> find_critical_connections(int number_of_servers, ArrayList<ArrayList<Integer>> connections) {
        ArrayList<ArrayList<Integer>> adjList = new ArrayList<>();
        ArrayList<ArrayList<Integer>> result = new ArrayList<>();
        Integer[] parent = new Integer[number_of_servers];
        Arrays.fill(parent, -1);
        boolean[] visited = new boolean[number_of_servers];
        for (int i = 0; i < number_of_servers; i++) {
            adjList.add(new ArrayList<>());
        }
        for (ArrayList<Integer> connection : connections) {
            adjList.get(connection.get(0)).add(connection.get(1));
            adjList.get(connection.get(1)).add(connection.get(0));
        }
        arrivals = new Integer[number_of_servers];
        departure = new Integer[number_of_servers];
        dfs(0,-1, visited,  adjList, result);
        if (result.isEmpty()) {
            result.add(new ArrayList<>(Arrays.asList(-1, -1)));
        }
        return result;
    }

    private static void dfs(int src,int parentNode, boolean[] visited, ArrayList<ArrayList<Integer>> adjList, ArrayList<ArrayList<Integer>> result) {
        visited[src] = true;
        arrivals[src] = timestamp++;
        departure[src] = timestamp;
        for(int neighbor : adjList.get(src)) {
            if(parentNode == neighbor) {
                continue;
            }
            if(!visited[neighbor]) {
                dfs(neighbor, src, visited, adjList, result);
                departure[src] = Math.min(departure[src], departure[neighbor]);
                if(departure[neighbor] >arrivals[src]) {
                    result.add(new ArrayList<>(Arrays.asList(neighbor, src)));
                }
            }else {
                departure[src] = Math.min(departure[src], arrivals[neighbor]);
            }
        }
    }
}
