package trees.practice;

import java.util.LinkedList;
import java.util.Queue;

public class PopulateSiblings {
    public static void main(String[] args) {
        BinaryTreeNode root = new BinaryTreeNode(100);
        root.left = new BinaryTreeNode(200);
        root.right = new BinaryTreeNode(300);
        root.left.left = new BinaryTreeNode(400);
        root.left.right = new BinaryTreeNode(500);
        root.right.left = new BinaryTreeNode(600);
        root.right.right = new BinaryTreeNode(700);
        populate_sibling_pointers(root);
    }
    static BinaryTreeNode populate_sibling_pointers(BinaryTreeNode root) {
        if(root==null){
            return null;
        }
        // Write your code here.
        Queue<BinaryTreeNode> queue=new LinkedList<>();
        queue.add(root);

        while(!queue.isEmpty()){
            BinaryTreeNode prev=null;
            int size=queue.size();
            for(int i=0;i<size;i++){
                BinaryTreeNode current=queue.poll();
                if(prev!=null){
                    prev.next_right=current;
                }
                if(current.left!=null){
                    queue.add(current.left);
                }

                if(current.right!=null){
                    queue.add(current.right);
                }
                prev=current;
            }
        }
        return root;
    }
}
