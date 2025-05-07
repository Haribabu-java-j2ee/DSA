package graphs.practice;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

//output: 1, 3, 4
public class LongestPathInDAG {
    public static void main(String[] args) {
        int dag_nodes=4;
        int[] dag_from= {1, 1, 1, 3};
        int[] dag_to= {2, 3, 4, 4};
        int[] dag_weight= {2, 2, 4, 3};
        int from_node=1;
        int to_node=4;

        ArrayList<Integer> dag_fromList = new ArrayList<>();
        Arrays.stream(dag_from).forEach(dag_fromList::add);
        ArrayList<Integer> dag_toList = new ArrayList<>();
        Arrays.stream(dag_to).forEach(dag_toList::add);

        ArrayList<Integer> dag_weightList = new ArrayList<>();
        Arrays.stream(dag_weight).forEach(dag_weightList::add);
        System.out.println(find_longest_path(dag_nodes,dag_fromList,dag_toList,dag_weightList,from_node,to_node));

    }

    static ArrayList<Integer> find_longest_path(Integer dag_nodes, ArrayList<Integer> dag_from, ArrayList<Integer> dag_to,
                                               ArrayList<Integer> dag_weight, Integer from_node, Integer to_node){
        ArrayList<ArrayList<int[]>> adjList = new ArrayList<>();
        boolean[] visited = new boolean[dag_nodes+1];
        for(int i=0;i<dag_nodes+1;i++){
            adjList.add(new ArrayList<>());
        }

        for(int i=0;i<dag_from.size();i++){
            adjList.get(dag_from.get(i)).add(new int[]{dag_to.get(i),dag_weight.get(i)});
        }

        ArrayList<Integer> topologicalOrder = new ArrayList<>();
        for(int i=1;i<=dag_nodes;i++){
            if(!visited[i]){
                dfs(i,visited,adjList,topologicalOrder);
            }
        }
        Collections.reverse(topologicalOrder);
        ArrayList<Long> longestPath = new ArrayList<>(Collections.nCopies(dag_nodes+1,-1l));
        ArrayList<Integer> parent = new ArrayList<>(Collections.nCopies(dag_nodes+1,0));
        longestPath.set(from_node,0l);
        for(int i=0;i<dag_nodes;i++){
            int from=topologicalOrder.get(i);
            if(longestPath.get(from)>=0){
                if(from==to_node){
                    break;
                }
                for(int j=0;j<adjList.get(from).size();j++){
                    int to=adjList.get(from).get(j)[0];
                    int weight=adjList.get(from).get(j)[1];
                    if(longestPath.get(to)<=longestPath.get(from)+weight){
                        longestPath.set(to,longestPath.get(from)+weight);
                        parent.set(to,from);
                    }
                }
            }
        }
        ArrayList<Integer> path = new ArrayList<>();
        path.add(to_node);
        while(to_node!=from_node){
            to_node=parent.get(to_node);
            path.add(to_node);
        }
        Collections.reverse(path);
        return path;
    }

    private static void dfs(int src, boolean[] visited, ArrayList<ArrayList<int[]>> adjList, ArrayList<Integer> topologicalOrder) {
        visited[src] = true;
        for(int[] neighbor:adjList.get(src)){
            if(!visited[neighbor[0]]){
                dfs(neighbor[0],visited,adjList,topologicalOrder);
            }
        }
        topologicalOrder.add(src);
    }
}
