package graphs.practice;

import java.util.*;

/**
 * [
 * [2, 0],
 * [1, 0],
 * [1, 1],
 * [0, 1],
 * [0, 2],
 * [0, 3],
 * [1, 3],
 * [2, 3],
 * [2, 2]
 * ]
 * */
public class ShortestPathin2DGrid {

    static int rows;
    static int cols;
    private static final int[] rowDirections = {-1, 1, 0, 0};
    private static final int[] colDirections = {0, 0, -1, 1};
    // @ and + are the start and goal cells
    public static void main(String[] args) {
        String[] grid = {"...A", ".a#.", "@#+."};
        ArrayList<String> gridList = new ArrayList<>(Arrays.asList(grid));
        find_shortest_path(gridList).stream().forEach(System.out::println);
    }

    private static ArrayList<ArrayList<Integer>> find_shortest_path(ArrayList<String> gridList) {
        rows = gridList.size();
        cols = gridList.get(0).length();
        int[] start=findPosition(gridList,'@');
        int[] end=findPosition(gridList,'+');
        if(start==null ||end==null){
            return new ArrayList<>();
        }
        List<int[]> paths=findShortestPathWithKeys(gridList, start, end);
        ArrayList<ArrayList<Integer>> result=new ArrayList<>();
        if(!paths.isEmpty()){
            for(int i=0; i<paths.size(); i++){
                int[] coordinates=paths.get(i);
                int row=coordinates[0];
                int col=coordinates[1];
                result.add(new ArrayList());
                result.get(result.size()-1).add(row);
                result.get(result.size()-1).add(col);
            }
        }
        return result;
    }

    private static List<int[]> findShortestPathWithKeys(ArrayList<String> gridList, int[] start, int[] end) {
        Queue<Node> queue = new LinkedList<>();
        Set<String> visited = new HashSet<>();
        List<int[]> initPath=new ArrayList<>();
        initPath.add(new int[]{start[0],start[1]});
        queue.add(new Node(start[0],start[1],0,0,initPath));
        visited.add(start[0]+","+start[1]+",0");
        while(!queue.isEmpty()){
            Node currentNode = queue.poll();
            if(currentNode.row==end[0] && currentNode.col==end[1]){
                return currentNode.path;
            }
            for(int i=0;i<4;i++){
                int newRow=currentNode.row+rowDirections[i];
                int newCol=currentNode.col+colDirections[i];
                if(newRow<0||newRow>=rows||newCol<0||newCol>=cols){
                    continue;
                }
                char cell=gridList.get(newRow).charAt(newCol);
                int newKey=currentNode.keys;
                //creating new object is necessary or else it will modify the existing reference
                List<int[]> newPath=new ArrayList<>(currentNode.path);
                newPath.add(new int[]{newRow,newCol});
                if(cell=='#'){
                    continue;
                }
                if(cell>='A' && cell<='Z'){
                    int keyNeeded=1<<(cell-'A');
                    if((currentNode.keys & keyNeeded)==0){
                        continue;
                    }
                }

                if(cell>='a' && cell<='z'){
                    newKey|=(1<<(cell-'a'));
                }
                String state=newRow+","+newCol+","+newKey;
                if(!visited.contains(state)){
                    visited.add(state);
                    queue.add(new Node(newRow,newCol,newKey,currentNode.distance+1,newPath));
                }
            }
        }
        return null;
    }

    private static int[] findPosition(ArrayList<String> gridList, char target) {
        for(int i=0;i<rows;i++) {
            for(int j=0;j<cols;j++) {
                if(gridList.get(i).charAt(j)==target) {
                    return new int[]{i,j};
                }
            }
        }
        return null;
    }

    static class Node {
        int row, col;
        int keys; // Bitmask of collected keys
        int distance;
        List<int[]> path;

        public Node(int row, int col, int keys, int distance, List<int[]> path) {
            this.row = row;
            this.col = col;
            this.keys = keys;
            this.distance = distance;
            this.path = new ArrayList<>(path);
        }
    }
}
