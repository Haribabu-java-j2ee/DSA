package trees.practice;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Stack;

public class PostOrderWORecursion {
    public static void main(String[] args) {
        BinaryTreeNode root = new BinaryTreeNode(100);
        root.left = new BinaryTreeNode(200);
        root.right = new BinaryTreeNode(300);
        root.left.left = new BinaryTreeNode(400);
        root.left.right = new BinaryTreeNode(500);
        postorder_traversal_MO_tree(root).forEach(s -> System.out.print(s + " "));
    }

    //without modifying tree
    static ArrayList<Integer> postorder_traversal(BinaryTreeNode root) {
        // Write your code here.

        ArrayList<Integer> result = new ArrayList<>();
        if (root == null) {
            return result;
        }
        Stack<BinaryTreeNode> stack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            BinaryTreeNode current = stack.pop();

            result.add(current.value);
            if (current.left != null) {
                stack.push(current.left);

            }
            if (current.right != null) {
                stack.push(current.right);

            }
        }
        Collections.reverse(result);
        return result;
    }

    //with modifying tree
    static ArrayList<Integer> postorder_traversal_MO_tree(BinaryTreeNode root) {
        // Write your code here.

        ArrayList<Integer> result = new ArrayList<>();
        if (root == null) {
            return result;
        }
        Stack<BinaryTreeNode> stack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            BinaryTreeNode current = stack.peek();
            if (current.left != null) {
                stack.push(current.left);
                current.left=null;
            }else if(current.right != null) {
                stack.push(current.right);
                current.right=null;
            }else{
                result.add(current.value);
                stack.pop();
            }
        }
        return result;
    }

}
