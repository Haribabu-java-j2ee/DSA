package graphs;

import java.util.ArrayList;

public class CanCompleteCourse {
    public static void main(String[] args) {
        int[][] edges = {{1, 4}, {2, 4}, {3, 1}, {3, 2}};

        System.out.println(canFinish(5,edges));
    }

    public static boolean canFinish(int numCourses, int[][] prerequisites) {
        boolean[] arrival=new boolean[numCourses];
        boolean[] departure=new boolean[numCourses];
        ArrayList<ArrayList<Integer>> adjList=new ArrayList<>();
        for(int i=0;i<numCourses;i++){
            adjList.add(new ArrayList());
        }
        for(int[] prerequisite: prerequisites){
            int src=prerequisite[0];
            int dest=prerequisite[1];
            adjList.get(dest).add(src);
        }

        for(int i=0;i<numCourses;i++){
            if(!arrival[i] && isCycle(i, arrival, departure,adjList)){
                return false;
            }
        }
        return true;
    }

    private static boolean isCycle(int src, boolean[] arrival, boolean[] departure, ArrayList<ArrayList<Integer>> adjList) {
        arrival[src]=true;
        for(int neighbor:adjList.get(src)){
            if(arrival[neighbor]==true && departure[neighbor]==false){
                return true;
            }else if(arrival[neighbor]==false && isCycle(neighbor,arrival, departure,adjList)){
                return true;
            }

        }
        departure[src]=true;
        return false;
    }
}
