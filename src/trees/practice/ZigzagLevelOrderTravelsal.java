package trees.practice;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;

public class ZigzagLevelOrderTravelsal {
    public static void main(String[] args) {
        BinaryTreeNode root = new BinaryTreeNode(0);
        root.left = new BinaryTreeNode(1);
        root.right = new BinaryTreeNode(2);
        root.left.left = new BinaryTreeNode(3);
        root.left.right = new BinaryTreeNode(4);
        zigzag_level_order_traversal(root).forEach(System.out::println);
    }

    static ArrayList<ArrayList<Integer>> zigzag_level_order_traversal(BinaryTreeNode root) {
        // Write your code here.
        if(root==null){
            return new ArrayList();
        }
        Queue<BinaryTreeNode> queue=new LinkedList<>();
        queue.add(root);
        ArrayList<ArrayList<Integer>> result=new ArrayList<>();
        int j=0;
        while(!queue.isEmpty()){
            ArrayList<Integer> level=new ArrayList<>();
            int size=queue.size();
            for(int i=0;i<size;i++){
                BinaryTreeNode current=queue.poll();
                level.add(current.value);
                if(current.left!=null){
                    queue.add(current.left);
                }
                if(current.right!=null){
                    queue.add(current.right);
                }
            }
            if(j%2==0){
                result.add(level);
            }else{
                Collections.reverse(level);
                result.add(level);
            }
            j++;
        }
        return result;
    }
}
