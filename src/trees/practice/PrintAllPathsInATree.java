package trees.practice;

import java.util.ArrayList;

public class PrintAllPathsInATree {
    public static void main(String[] args) {
        BinaryTreeNode root = new BinaryTreeNode(1);
        root.left = new BinaryTreeNode(2);
        root.right = new BinaryTreeNode(3);
        root.left.left = new BinaryTreeNode(4);
        root.left.right = new BinaryTreeNode(5);
        root.right.left = new BinaryTreeNode(6);
        root.right.right = new BinaryTreeNode(7);
        all_paths_of_a_binary_tree(root).forEach(System.out::println);
    }

    static ArrayList<ArrayList<Integer>> all_paths_of_a_binary_tree(BinaryTreeNode root) {
        // Write your code here.

        ArrayList<ArrayList<Integer>> result = new ArrayList<>();
        if (root == null) {
            return result;
        }
        ArrayList<Integer> temp = new ArrayList<>();
        all_paths_of_a_binary_tree_util(result, root, temp);
        return result;
    }

    static void all_paths_of_a_binary_tree_util(ArrayList<ArrayList<Integer>> result, BinaryTreeNode root, ArrayList<Integer> temp) {
        if (root.left == null && root.right == null) {
            temp.add(root.value);
            result.add(new ArrayList<>(temp));
            temp.remove(temp.size() - 1);
            return;
        }
        temp.add(root.value);
        if (root.left != null) {
            all_paths_of_a_binary_tree_util(result, root.left, temp);
        }
        if (root.right != null) {
            all_paths_of_a_binary_tree_util(result, root.right, temp);
        }
        temp.remove(temp.size() - 1);
    }
}

class BinaryTreeNode {
    Integer value;
    BinaryTreeNode left;
    BinaryTreeNode right;
    //this is for printing siblings
    BinaryTreeNode next_right;

    BinaryTreeNode(Integer value) {
        this.value = value;
        this.left = null;
        this.right = null;
        this.next_right = null;
    }
}