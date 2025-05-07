package trees.practice;

import java.util.LinkedList;
import java.util.Queue;

public class MirrorImage {
    public static void main(String[] args) {
        BinaryTreeNode root = new BinaryTreeNode(0);
        root.left = new BinaryTreeNode(1);
        root.right = new BinaryTreeNode(2);
        root.left.left = new BinaryTreeNode(3);
        root.left.right = new BinaryTreeNode(4);
        root.right.left = new BinaryTreeNode(5);
        root.right.right = new BinaryTreeNode(6);
        mirror_image(root);
       // mirror_image_dfs(root);
    }

    //bfs
    static void mirror_image(BinaryTreeNode root) {
        if (root == null) return;
        // Write your code here.
        Queue<BinaryTreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            BinaryTreeNode current = queue.poll();
            BinaryTreeNode temp = current.left;
            current.left = current.right;
            current.right = temp;
            if (current.left != null) {
                queue.add(current.left);
            }
            if (current.right != null) {
                queue.add(current.right);
            }
        }

    }

//dfs
    public static void mirror_image_dfs(BinaryTreeNode root) {
        root = mirror_image_util(root);
    }

    private static BinaryTreeNode mirror_image_util(BinaryTreeNode root) {

        if (root == null) return null;
        BinaryTreeNode left = mirror_image_util(root.left);
        BinaryTreeNode right = mirror_image_util(root.right);
        root.left = right;
        root.right = left;
        return root;
    }

}
