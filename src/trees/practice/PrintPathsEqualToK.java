package trees.practice;

import java.util.ArrayList;

public class PrintPathsEqualToK {
    public static void main(String[] args) {
        BinaryTreeNode root = new BinaryTreeNode(0);
        root.left = new BinaryTreeNode(1);
        root.right = new BinaryTreeNode(2);
        root.left.left = new BinaryTreeNode(3);
        root.left.right = new BinaryTreeNode(4);
        System.out.println(path_sum(root, 4));

        root = new BinaryTreeNode(0);
        root.left = new BinaryTreeNode(0);
        root.right = new BinaryTreeNode(0);
        System.out.println(path_sum(root, 0));
    }

    static Boolean path_sum(BinaryTreeNode root, Integer k) {
        // Write your code here.
        return path_sum_util(root, k, 0l);
    }

    static boolean path_sum_util(BinaryTreeNode root, Integer k, long currentsum) {
        if(root==null){
            return false;
        }
        if(root.left==null && root.right==null){
            return currentsum+root.value==k;
        }
        return path_sum_util(root.left,k,currentsum+root.value) || path_sum_util(root.right,k,currentsum+root.value);
    }
}
