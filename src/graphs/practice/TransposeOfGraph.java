package graphs.practice;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class TransposeOfGraph {
    static Map<Integer,GraphNode> reversedGraph=new HashMap();
    public static void main(String[] args) {
        GraphNode node=new GraphNode(1);
        node.neighbors.add(new GraphNode(2));
        node.neighbors.get(0).neighbors.add(new GraphNode(3));
        node.neighbors.get(0).neighbors.get(0).neighbors.add(new GraphNode(1));
        GraphNode reverse=create_transpose(node);
        System.out.println(reverse);
    }

    private static GraphNode create_transpose(GraphNode node) {
        dfs(node);
        return reversedGraph.get(node.value);
    }

    private static void dfs(GraphNode node) {
        GraphNode temp=new GraphNode(node.value);
        reversedGraph.put(node.value,temp);
        for(GraphNode neighbor:node.neighbors) {
            if(!reversedGraph.containsKey(neighbor.value)) {
                dfs(neighbor);
            }
            GraphNode reversedNeighbor=reversedGraph.get(neighbor.value);
            GraphNode src=reversedGraph.get(node.value);
            reversedNeighbor.neighbors.add(src);
        }
    }
}

class GraphNode{
    Integer value;
    ArrayList<GraphNode> neighbors;
    GraphNode(Integer value){
        this.value = value;
        this.neighbors = new ArrayList<>(3);
    }
}
