package graphs;

import java.util.ArrayList;

public class CountConnectedComponents {
    public static void main(String[] args) {
        int[][] edges = {{0,1},{1,2},{3,4}};
        int n=5;
        System.out.println(countComponents(n,edges));
    }

    static int countComponents(int n, int[][] edges) {
        ArrayList<ArrayList<Integer>> adjList = new ArrayList<>();
        boolean[] visited=new boolean[n];
        for(int i=0;i<n;i++){
            adjList.add(new ArrayList<>());
        }
        for(int[] edge : edges) {
            int src=edge[0];
            int dest=edge[1];

            adjList.get(src).add(dest);
            adjList.get(dest).add(src);
        }
        int count=0;
        for(int i=0;i<n;i++) {
            if(!visited[i]) {
                dfs(i,visited,adjList);
                count++;
            }
        }
        return count;
    }

    static void dfs(int src,boolean[] visited,ArrayList<ArrayList<Integer>> adjList) {
        visited[src]=true;
        ArrayList<Integer> neighbours=adjList.get(src);

        for(int i=0;i<neighbours.size();i++) {
            if(!visited[neighbours.get(i)]) {
                dfs(neighbours.get(i),visited,adjList);
            }
        }
    }
}
