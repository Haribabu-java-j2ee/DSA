package trees.practice;

import java.util.*;

//https://leetcode.com/problems/binary-tree-level-order-traversal-ii/
public class LevelOrderBottomUp {
    public static void main(String[] args) {
        BinaryTreeNode root = new BinaryTreeNode(3);
        root.left = new BinaryTreeNode(9);
        root.right = new BinaryTreeNode(20);
        root.right.left = new BinaryTreeNode(15);
        root.right.right = new BinaryTreeNode(7);
        levelOrderBottom(root).forEach(System.out::println);
    }

    public static List<List<Integer>> levelOrderBottom(BinaryTreeNode root) {
        List<List<Integer>> result=new ArrayList<>();
        Queue<BinaryTreeNode> queue=new LinkedList<>();
        queue.add(root);
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
            result.add(level);
        }
        Collections.reverse(result);
        return result;
    }
}
