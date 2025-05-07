package trees.practice;

import java.util.ArrayList;

public class KthSmallestInBST {
    public static void main(String[] args) {
        BinaryTreeNode root = new BinaryTreeNode(2);
        root.left = new BinaryTreeNode(1);
        root.right = new BinaryTreeNode(3);
        System.out.println(kth_smallest_element(root, 3));
    }
    static ArrayList<Integer> inorder=new ArrayList<>();
    static Integer kth_smallest_element(BinaryTreeNode root, Integer k) {
        // Write your code here.
        if(root==null){
            return 0;
        }
        dfs_inorder(root);
        return inorder.get(k-1);
    }

    static void dfs_inorder(BinaryTreeNode root){
        if(root==null){
            return;
        }
        dfs_inorder(root.left);
        inorder.add(root.value);
        dfs_inorder(root.right);
    }
}
