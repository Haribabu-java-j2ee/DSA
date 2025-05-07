package graphs.practice;

import java.util.ArrayList;

public class ZombieCluster {
    public static void main(String[] args) {
        ArrayList<String> zombies = new ArrayList<>();
        zombies.add("1100");
        zombies.add("1110");
        zombies.add("0110");
        zombies.add("0001");
        System.out.println(zombie_cluster(zombies));
        System.out.println(zombie_cluster1(zombies));
    }

    static int rows;
    static int cols;

    //have some issue, need to check with class
    static Integer zombie_cluster(ArrayList<String> zombies) {
        // Write your code here.
        cols = zombies.get(0).length();
        rows = zombies.size();
        char[][] adjMatrix = new char[rows][cols];
        boolean[][] visited = new boolean[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                adjMatrix[i][j] = zombies.get(i).charAt(j);
            }
        }
        int count = 0;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (!visited[i][j] && adjMatrix[i][j] == '1') {
                    count++;
                    dfs(i, j,  visited, adjMatrix);
                }

            }
        }

        return count;
    }

    static void dfs(int i, int j, boolean[][] visited, char[][] adjMatrix) {

        if (i < 0 || j < 0 || i >= rows || j >= cols ||visited[i][j]) {
            return;
        }

        visited[i][j]=true;
        if(adjMatrix[i][j] == '1') {
            dfs(i + 1, j, visited, adjMatrix);
            dfs(i - 1, j, visited, adjMatrix);
            dfs(i, j + 1, visited, adjMatrix);
            dfs(i, j - 1, visited, adjMatrix);
        }
    }


    //working solution
    static Integer zombie_cluster1(ArrayList<String> zombies) {
        // Write your code here.
        int cluster=0;
        int n=zombies.size();
        boolean[] visited=new boolean[n];
        for(int i=0;i<n;i++){
            if(!visited[i]){
                cluster++;
                dfs(i,n,visited,zombies);
            }
        }
        return cluster;
    }

    static void dfs(int x, int n, boolean[] visited,ArrayList<String> zombies ){
        visited[x]=true;
        for(int i=0;i<n;i++){
            if(!visited[i]){
                if(zombies.get(x).charAt(i)=='1'){
                    dfs(i,n,visited,zombies);
                }
            }
        }

    }


}
