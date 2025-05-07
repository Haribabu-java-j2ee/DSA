package graphs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class CheckEulerianCycle {
    public static void main(String[] args) {
        int n = 5;
        int[][] edges = {
                {0, 1},
                {0, 2},
                {1, 3},
                {3, 0},
                {3, 2},
                {4, 3},
                {4, 0}
        };
        ArrayList<ArrayList<Integer>> edgeLst=new ArrayList<>();
        Arrays.stream(edges).forEach(e->{
            ArrayList<Integer> edge=new ArrayList<>();
            Arrays.stream(e).forEach(edge::add);
            edgeLst.add(edge);
        });
        System.out.println(check_if_eulerian_cycle_exists(n,edgeLst));
    }

    //check for even degree for eulerian cycle
    //can implement using arraylist also , since vertices start from  0 to n
    static Boolean check_if_eulerian_cycle_exists(Integer n, ArrayList<ArrayList<Integer>> edges) {
        // Write your code here.
        Map<Integer,Integer> node_degeree=new HashMap<>();
        for(ArrayList<Integer> edge: edges){
            node_degeree.put(edge.get(0),node_degeree.getOrDefault(edge.get(0),0)+1);
            node_degeree.put(edge.get(1),node_degeree.getOrDefault(edge.get(1),0)+1);
        }

        for(Map.Entry<Integer,Integer> entry:node_degeree.entrySet()){
            if(entry.getValue()%2!=0){
                return false;
            }
        }
        return true;
    }

}
