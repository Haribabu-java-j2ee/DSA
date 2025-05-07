package graphs.practice;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class SnakesAndLadders {
    public static void main(String[] args) {
        int n=18;
        int[] moves={-1, 0, 0, 0, -1, 0, 0, 0, 0, 0, -1, 0, 0, -1, 0, 0, 0, -1};
        ArrayList<Integer> movesList = new ArrayList<>();
        Arrays.stream(moves).forEach(movesList::add);
        System.out.println(countMinMoves(n,movesList));
    }


    static Integer countMinMoves(Integer n, ArrayList<Integer> moves) {
        // Write your code here.
        Queue<Node> queue=new LinkedList();
        boolean[] visited=new boolean[n];
        Node node=new Node();
        node.index=0;
        node.distance=0;
        queue.add(node);
        while(!queue.isEmpty()){
            node=queue.poll();

            if(visited[node.index]){
                continue;
            }
            visited[node.index]=true;
            if(node.index==n-1){
                return node.distance;
            }
            for(int dice=1;dice<=6;dice++){
                int newIndex=node.index+dice;
                if(newIndex>=n){
                    break;
                }
                int snakeOrLadder=moves.get(newIndex);
                if(snakeOrLadder!=-1){
                    newIndex=snakeOrLadder;
                }
                Node nextNode=new Node();
                nextNode.index=newIndex;
                nextNode.distance=node.distance+1;
                queue.add(nextNode);
            }
        }
        return -1;
    }

    static class Node{
        int index;
        int distance;
    }

}
