package trees;

/*
 * Asymptotic complexity in terms of the number of nodes `n`:
 * Time: O(n).
 * Auxiliary space: O(1).
 * Total space: O(n).
 */

import java.util.Stack;

/*I have written a basic program , and dont see a major time or space complexity differences, but the program is exceeding time limit ,
if i remove internal variable, still program failing , on further analysis converting Integer to int passing the program ,
 need some clarification, on why program is failing
static Boolean search_node_in_bst(BinaryTreeNode root, Integer value) {
    // Write your code here.
    if (root == null) {
        return false;
    }
    BinaryTreeNode current = root;
    while (current != null) {
        if (current.value == value) {
            return true;
        } else if (current.value > value) {
            current = current.right;
        } else if (current.value < value) {
            current = current.left;
        }
    }
    return false;
} */
public class BSTSearch {
    public static void main(String[] args) {
        BinaryTreeNode root = new BinaryTreeNode(2);
        root.left = new BinaryTreeNode(1);
        root.right = new BinaryTreeNode(5);
        root.right.left = new BinaryTreeNode(4);
        root.right.right = new BinaryTreeNode(6);
        System.out.println(search_node_in_bst(root, 4));

    }

    static Boolean search_node_in_bst(BinaryTreeNode root, Integer value) {
        // Write your code here.
        if (root == null) {
            return false;
        }
        while (root != null) {
            if (root.value == value) {
                return true;
            } else if (root.value < value) {
                root = root.right;
            } else if (root.value > value) {
                root = root.left;
            }
        }
        return false;
    }

    //helper for successor class
    static BinaryTreeNode search_node(BinaryTreeNode root, Integer value) {
        // Write your code here.
        if (root == null) {
            return null;
        }
        BinaryTreeNode current = root;
        while (current != null) {
            if (current.value == value) {
                return current;
            } else if (current.value < value) {
                current = current.right;
            } else if (current.value > value) {
                current = current.left;
            }
        }
        return null;
    }

    static boolean search_node_in_bst(BinaryTreeNode root, int value) {
        // Write your code here.
        while (root != null && root.value != value) {
            root = value < root.value ? root.left : root.right;
        }
        return root != null;
    }

}


class BinaryTreeNode {
    Integer value;
    BinaryTreeNode left;
    BinaryTreeNode right;

    BinaryTreeNode(Integer value) {
        this.value = value;
        this.left = null;
        this.right = null;
    }
}
