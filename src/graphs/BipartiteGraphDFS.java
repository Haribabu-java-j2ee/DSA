package graphs;

public class BipartiteGraphDFS {
    public boolean isBipartite(int[][] graph) {
        int n = graph.length;
        int[] color = new int[n]; // 0: uncolored, 1: color A, -1: color B

        for (int i = 0; i < n; i++) {
            if (color[i] == 0) {
                if (!dfs(i, 1, graph, color)) {
                    return false;
                }
            }
        }
        return true;
    }
    
    private boolean dfs(int node, int expectedColor, int[][] graph, int[] color) {
        // If node is already colored, check if it matches expected color
        if (color[node] != 0) {
            return color[node] == expectedColor;
        }
        
        // Color the node
        color[node] = expectedColor;
        
        // Visit all neighbors with opposite color
        for (int neighbor : graph[node]) {
            if (!dfs(neighbor, -expectedColor, graph, color)) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        BipartiteGraphDFS checker = new BipartiteGraphDFS();
        
        // Test cases
        int[][] graph1 = {{1,3},{0,2},{1,3},{0,2}}; // Bipartite
        System.out.println("Graph 1 is bipartite: " + checker.isBipartite(graph1));
        
        int[][] graph2 = {{1,2,3},{0,2},{0,1,3},{0,2}}; // Non-bipartite (triangle)
        System.out.println("Graph 2 is bipartite: " + checker.isBipartite(graph2));
        
        int[][] graph3 = {{1},{0},{3},{2},{5},{4}}; // Disconnected bipartite
        System.out.println("Graph 3 is bipartite: " + checker.isBipartite(graph3));
        
        int[][] graph4 = {{1,2},{0,2},{0,1}}; // Non-bipartite (triangle)
        System.out.println("Graph 4 is bipartite: " + checker.isBipartite(graph4));
    }
}