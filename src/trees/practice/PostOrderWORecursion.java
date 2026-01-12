package trees.practice;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Stack;

/**
 * ============================================================================
 * POSTORDER TRAVERSAL WITHOUT RECURSION
 * ============================================================================
 * 
 * PROBLEM STATEMENT:
 * ------------------
 * Perform postorder traversal of a binary tree using iteration (no recursion).
 * 
 * POSTORDER: Left → Right → Root
 * 
 * CHALLENGE:
 * ----------
 * Postorder is the most complex traversal to implement iteratively because
 * we need to process children BEFORE parent, but we encounter parent first.
 * 
 * TWO APPROACHES:
 * 
 * APPROACH 1: Modified Preorder + Reverse
 * - Do a modified preorder: Root → Right → Left (reverse of postorder)
 * - Reverse the result to get Left → Right → Root
 * - Simple but uses O(n) extra space for result
 * 
 * APPROACH 2: Using stack with tree modification
 * - Mark visited children by setting them to null
 * - If children exist, push and nullify
 * - If no children, pop and process
 * - Modifies tree structure (can be avoided with extra tracking)
 * 
 * INTUITION FOR APPROACH 1:
 * -------------------------
 * Preorder:     Root → Left → Right
 * Modified:    Root → Right → Left
 * Reversed:    Left → Right → Root = POSTORDER!
 * 
 * Why does this work?
 * - In modified preorder, we visit root first, then right, then left
 * - Reversing gives us left first, then right, then root
 * - This is exactly postorder!
 * 
 * VISUAL REPRESENTATION:
 * ----------------------
 * Tree:
 *        100
 *       /   \
 *     200   300
 *     / \
 *   400 500
 * 
 * APPROACH 1 (Modified Preorder + Reverse):
 * Modified preorder (Root→Right→Left): [100, 300, 200, 500, 400]
 * Reversed:                            [400, 500, 200, 300, 100]
 * This IS postorder! ✓
 * 
 * TIME COMPLEXITY:
 * ----------------
 * Both approaches: O(n)
 * 
 * SPACE COMPLEXITY:
 * -----------------
 * Approach 1: O(n) for stack + O(n) for result
 * Approach 2: O(h) for stack but modifies tree
 * 
 * DRY RUN (Approach 1):
 * ---------------------
 *        100
 *       /   \
 *     200   300
 *     / \
 *   400 500
 * 
 * Modified Preorder (Root → Right → Left):
 * | Step | Stack     | Action         | Result         |
 * |------|-----------|----------------|----------------|
 * | 1    | [100]     | Pop 100        | [100]          |
 * | 2    | [200,300] | Pop 300        | [100, 300]     |
 * | 3    | [200]     | Pop 200        | [100, 300, 200]|
 * | 4    | [400,500] | Pop 500        | [100,300,200,500]|
 * | 5    | [400]     | Pop 400        | [100,300,200,500,400]|
 * 
 * Reverse: [400, 500, 200, 300, 100] ✓
 * 
 * This is postorder! (Left-Right-Root for each subtree)
 */
public class PostOrderWORecursion {
    
    public static void main(String[] args) {
        // Build tree
        BinaryTreeNode root = new BinaryTreeNode(100);
        root.left = new BinaryTreeNode(200);
        root.right = new BinaryTreeNode(300);
        root.left.left = new BinaryTreeNode(400);
        root.left.right = new BinaryTreeNode(500);
        
        System.out.print("Postorder (with tree modification): ");
        postorder_traversal_MO_tree(root).forEach(s -> System.out.print(s + " "));
        
        // Rebuild tree for second approach (first approach modifies it)
        root = new BinaryTreeNode(100);
        root.left = new BinaryTreeNode(200);
        root.right = new BinaryTreeNode(300);
        root.left.left = new BinaryTreeNode(400);
        root.left.right = new BinaryTreeNode(500);
        
        System.out.print("\nPostorder (reverse approach): ");
        postorder_traversal(root).forEach(s -> System.out.print(s + " "));
    }

    // ═══════════════════════════════════════════════════════════════════════
    // APPROACH 1: Modified Preorder + Reverse
    // Time: O(n), Space: O(n)
    // ═══════════════════════════════════════════════════════════════════════
    
    /**
     * Postorder without modifying tree.
     * 
     * Algorithm:
     * 1. Do modified preorder: Root → Right → Left
     *    (Push left before right, so right is popped first)
     * 2. Reverse result to get Left → Right → Root
     * 
     * Time Complexity: O(n)
     * Space Complexity: O(n) for stack and result
     * 
     * @param root Root of tree
     * @return Postorder traversal
     */
    static ArrayList<Integer> postorder_traversal(BinaryTreeNode root) {
        ArrayList<Integer> result = new ArrayList<>();
        
        // Edge case: empty tree
        if (root == null) {
            return result;
        }
        
        Stack<BinaryTreeNode> stack = new Stack<>();
        stack.push(root);
        
        // Modified preorder: Root → Right → Left
        while (!stack.isEmpty()) {
            BinaryTreeNode current = stack.pop();
            
            // Process current node (add to result)
            result.add(current.value);
            
            // Push LEFT first so RIGHT is processed first (LIFO)
            if (current.left != null) {
                stack.push(current.left);
            }
            if (current.right != null) {
                stack.push(current.right);
            }
        }
        
        // Reverse to get postorder: Left → Right → Root
        Collections.reverse(result);
        return result;
    }

    // ═══════════════════════════════════════════════════════════════════════
    // APPROACH 2: Tree Modification (sets children to null)
    // Time: O(n), Space: O(h)
    // ═══════════════════════════════════════════════════════════════════════
    
    /**
     * Postorder with tree modification.
     * 
     * Algorithm:
     * 1. Push root onto stack
     * 2. Peek top of stack:
     *    - If it has left child, push left child, set left = null
     *    - Else if it has right child, push right child, set right = null
     *    - Else (no children), pop and add to result
     * 
     * WARNING: This modifies the tree structure!
     * 
     * Time Complexity: O(n)
     * Space Complexity: O(h) for stack
     * 
     * @param root Root of tree (will be modified!)
     * @return Postorder traversal
     */
    static ArrayList<Integer> postorder_traversal_MO_tree(BinaryTreeNode root) {
        ArrayList<Integer> result = new ArrayList<>();
        
        // Edge case: empty tree
        if (root == null) {
            return result;
        }
        
        Stack<BinaryTreeNode> stack = new Stack<>();
        stack.push(root);
        
        while (!stack.isEmpty()) {
            // Peek (don't pop yet)
            BinaryTreeNode current = stack.peek();
            
            // If left child exists, process it first
            if (current.left != null) {
                stack.push(current.left);
                current.left = null;  // Mark as visited by setting to null
            }
            // Else if right child exists, process it
            else if (current.right != null) {
                stack.push(current.right);
                current.right = null;  // Mark as visited by setting to null
            }
            // Else no children left, process current node
            else {
                result.add(current.value);
                stack.pop();
            }
        }
        
        return result;
    }
}
