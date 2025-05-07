package graphs;

import java.util.ArrayList;
import java.util.Arrays;

public class FindLargestIsland {
    public static void main(String[] args) {
        int[][] grid = {
                {1, 1, 0},
                {1, 1, 0},
                {0, 0, 1}
        };
        ArrayList<ArrayList<Integer>> gridList = new ArrayList<>();

        Arrays.stream(grid).forEach(row -> {
            ArrayList<Integer> rows = new ArrayList<>();
            Arrays.stream(row).forEach(rows::add);
            gridList.add(rows);
        });
        System.out.println(max_island_size(gridList));
    }
    static int count;
    static Integer max_island_size(ArrayList<ArrayList<Integer>> grid) {
        // Write your code here.
        int max_count=0;
        for(int i=0;i<grid.size();i++){
            for(int j=0;j<grid.get(i).size();j++){
                if(grid.get(i).get(j)==1){
                    dfs(i,j,grid);
                    max_count=Math.max(count,max_count);
                    count=0;
                }
            }
        }
        return max_count;
    }

    static void dfs(int i , int j, ArrayList<ArrayList<Integer>> grid){
        if(i<0 || j<0 || i>= grid.size() || j>=grid.get(i).size() || grid.get(i).get(j)!=1){
            return;
        }
        grid.get(i).set(j,0);
        count++;
        dfs(i+1,j,grid);
        dfs(i-1,j,grid);
        dfs(i,j+1,grid);
        dfs(i,j-1,grid);
    }

}
