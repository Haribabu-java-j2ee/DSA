package trees;

import java.util.ArrayList;
import java.util.Arrays;

import static trees.BSTInsert.build_a_bst;
import static trees.BSTSearch.search_node;

public class BSTSuccessor {
    public static void main(String[] args) {
        Integer[] arr = {44, 17, 88, 8, 32, 65, 97, null, null, 28, null, 54, 82, 93, null, 29, 76, 80};
        ArrayList<Integer> list = new ArrayList<>();
        Arrays.stream(arr).forEach(list::add);
        BinaryTreeNode root = build_a_bst(list);
        BinaryTreeNode node = search_node(root, 93);
        System.out.println(root);
        BinaryTreeNode successor = findSuccesor(root, node);
        System.out.println(successor!=null?successor.value:null);
    }

    private static BinaryTreeNode findSuccesor(BinaryTreeNode root, BinaryTreeNode node) {
        if (root == null) {
            return null;
        }
        BinaryTreeNode current;
        if (node.right != null) {
            current = node.right;
            while (current.left != null) {
                current = current.left;
            }
            return current;
        }
        BinaryTreeNode ancestor = null;
        current = root;
        while (current.value != node.value) {
            if (node.value > current.value) {
                current = current.right;
            } else if (node.value < current.value) {
                ancestor = current;
                current = current.left;
            }
        }
        return ancestor;
    }
}
