package trees.practice;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;

public class Test {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        ArrayList<TreeNode> list = new ArrayList<>();
        TreeNode child1 = new TreeNode(2);
        TreeNode child2 = new TreeNode(3);
        TreeNode subchild=new TreeNode(4);
        ArrayList<TreeNode> sublist = new ArrayList<>();
        sublist.add(subchild);
        TreeNode child3 = new TreeNode(5);
        child3.children = sublist;
        list.add(child1);
        list.add(child2);
        list.add(child3);
        root.children = list;

        System.out.println( maxDepth(root));
    }

    // a simple approach
    static int maxDepth(TreeNode root) {

        // If the node is null, depth is 0
        if (root == null) {
            return 0;
        }

        int depth = 0;

        // Recur for all children and find
        // the maximum depth
        for (TreeNode child : root.children) {
            depth = Math.max(depth, maxDepth(child));
        }

        // Add 1 to include the current node
        // in the depth count
        return depth + 1;
    }
}
