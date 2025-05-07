package trees.practice;

import java.util.ArrayList;

public class CovertBTIntoDoubleLinkedList {
    public static void main(String[] args) {
        BinaryTreeNode root = new BinaryTreeNode(4);
        root.left = new BinaryTreeNode(2);
        root.right = new BinaryTreeNode(5);
        root.left.left = new BinaryTreeNode(1);
        root.left.right = new BinaryTreeNode(3);

        BinaryTreeNode temp= binary_tree_to_cdll(root);
        System.out.println(temp);
    }

    static ArrayList<BinaryTreeNode> inorder=new ArrayList<>();

    static BinaryTreeNode binary_tree_to_cdll(BinaryTreeNode root) {
        // Write your code here.
        binary_tree_to_cdll_dfs(root);

        for(int i=0;i<inorder.size()-1;i++){
            inorder.get(i).right=inorder.get(i+1);
            inorder.get(i+1).left=inorder.get(i);
        }
        inorder.get(inorder.size()-1).right=inorder.get(0);
        inorder.get(0).left=inorder.get(inorder.size()-1);
        return inorder.get(0);
    }

    static void binary_tree_to_cdll_dfs(BinaryTreeNode root){
        if(root==null){
            return;
        }
        binary_tree_to_cdll_dfs(root.left);
        inorder.add(root);
        binary_tree_to_cdll_dfs(root.right);
    }

    //approach 2 inplace, from chat gpt
    static BinaryTreeNode prev = null;
    static BinaryTreeNode head = null;

    // Convert Binary Tree to Circular Doubly Linked List
    static BinaryTreeNode binaryTreeToCDLL(BinaryTreeNode root) {
        if (root == null) return null;

        // Convert to DLL using in-order traversal
        convert(root);

        // Make it circular
        head.left = prev;
        prev.right = head;

        return head;
    }

    // Helper function for in-order traversal & pointer manipulation
    static void convert(BinaryTreeNode curr) {
        if (curr == null) return;

        // 1. Recursively process left subtree
        convert(curr.left);

        // 2. Process current node
        if (prev == null) {
            head = curr; // First node in in-order traversal
        } else {
            prev.right = curr;
            curr.left = prev;
        }
        prev = curr;

        // 3. Recursively process right subtree
        convert(curr.right);
    }

}
