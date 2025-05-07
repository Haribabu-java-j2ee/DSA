package graphs.practice;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class KnightsTourOnChess {
    public static void main(String[] args) {
        System.out.println(find_minimum_number_of_moves(5, 5, 0, 0, 4, 1));
    }

    static int no_of_possible_moves = 8;
    static int[] dx = {-2, 2, -1, 1, -1, 1, -2, 2};
    static int[] dy = {-1, 1, -2, 2, 2, -2, 1, -1};

    static Integer find_minimum_number_of_moves(Integer rows, Integer cols, Integer start_row,
                                                Integer start_col, Integer end_row, Integer end_col) {

        if(start_row.equals(end_row) && start_col.equals(end_col)){
            return 0;
        }
        Integer[][] distances=new Integer[rows][cols];
        for(Integer[] row:distances){
            Arrays.fill(row,-1);
        }
        distances[start_row][start_col]=0;
        Queue<Integer[]> distanceQueue=new LinkedList();
        distanceQueue.add(new Integer[]{start_row,start_col});
        while(!distanceQueue.isEmpty()){
            Integer[] current=distanceQueue.poll();
            Integer current_row=current[0];
            Integer current_col=current[1];
            for(int i=0;i<no_of_possible_moves;i++){
                Integer next_row=current_row+dx[i];
                Integer next_col=current_col+dy[i];
                if(isValid(next_row,next_col,rows,cols) && distances[next_row][next_col].equals(-1)){
                    distances[next_row][next_col]=distances[current_row][current_col]+1;
                    if(next_row.equals(end_row) && next_col.equals(end_col)){
                        return distances[next_row][next_col];
                    }
                    distanceQueue.add(new Integer[]{next_row,next_col});
                }
            }
        }
        return -1;
    }

    private static boolean isValid(int nextRow, int nextColumn, Integer rows, Integer columns) {
        return nextRow >= 0 && nextRow < rows && nextColumn >= 0 && nextColumn < columns;
    }
}
