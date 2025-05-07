package trees.practice;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class RightViewOfTree {
    public static void main(String[] args) {
        BinaryTreeNode root = new BinaryTreeNode(0);
        root.left = new BinaryTreeNode(1);
        root.right = new BinaryTreeNode(2);
        root.left.left = new BinaryTreeNode(3);
        root.left.right = new BinaryTreeNode(4);
        right_view(root).forEach(System.out::println);
    }

    static ArrayList<Integer> right_view(BinaryTreeNode root) {
        // Write your code here.
        if(root==null){
            return new ArrayList();
        }
        Queue<BinaryTreeNode> queue=new LinkedList();
        queue.add(root);
        ArrayList<Integer> result=new ArrayList<>();
        while(!queue.isEmpty()){
            int size=queue.size();

            for(int i=0;i<size;i++){
                BinaryTreeNode current=queue.poll();
                if(i==size-1){
                    result.add(current.value);
                }
                if(current.left!=null){
                    queue.add(current.left);
                }
                if(current.right!=null){
                    queue.add(current.right);
                }
            }

        }
        return result;
    }
}
