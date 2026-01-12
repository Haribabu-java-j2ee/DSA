package trees.practice;

import java.util.ArrayList;

/**
 * ============================================================================
 * CONVERT BINARY TREE TO CIRCULAR DOUBLY LINKED LIST
 * ============================================================================
 * 
 * PROBLEM STATEMENT:
 * ------------------
 * Convert a binary tree to a circular doubly linked list (CDLL) in-place.
 * The left pointer should point to the previous node.
 * The right pointer should point to the next node.
 * The list should be in INORDER sequence.
 * 
 * INTUITION:
 * ----------
 * Since we want inorder sequence, we process nodes in inorder.
 * 
 * TWO APPROACHES:
 * 
 * APPROACH 1: Collect nodes first, then link
 * 1. Do inorder traversal and collect all nodes in a list
 * 2. Link nodes in sequence (right = next, left = prev)
 * 3. Make it circular (last.right = first, first.left = last)
 * 
 * APPROACH 2: In-place conversion during traversal
 * 1. Use inorder traversal
 * 2. Track previous node
 * 3. Link current node with previous
 * 4. Make circular at the end
 * 
 * VISUAL REPRESENTATION:
 * ----------------------
 * Binary Tree:
 *         4
 *        / \
 *       2   5
 *      / \
 *     1   3
 * 
 * Inorder: 1 → 2 → 3 → 4 → 5
 * 
 * Circular DLL:
 * ┌────────────────────────────────────────────┐
 * │                                            │
 * ↓                                            │
 * 1 ⟷ 2 ⟷ 3 ⟷ 4 ⟷ 5 ────────────────────────────┘
 * ↑                    │
 * └────────────────────┘
 * 
 * After conversion:
 * - node.left = previous node in inorder
 * - node.right = next node in inorder
 * - head.left = tail (circular)
 * - tail.right = head (circular)
 * 
 * TIME COMPLEXITY:
 * ----------------
 * Both approaches: O(n) - visit each node once
 * 
 * SPACE COMPLEXITY:
 * -----------------
 * Approach 1: O(n) for storing nodes + O(h) for recursion
 * Approach 2: O(h) for recursion only (in-place)
 * 
 * DRY RUN (Approach 1):
 * ---------------------
 * Tree:
 *         4
 *        / \
 *       2   5
 *      / \
 *     1   3
 * 
 * Step 1: Inorder traversal → list = [1, 2, 3, 4, 5]
 * 
 * Step 2: Link nodes
 * | i | Node | Right (next) | Left (prev) |
 * |---|------|--------------|-------------|
 * | 0 | 1    | 2            | (set later) |
 * | 1 | 2    | 3            | 1           |
 * | 2 | 3    | 4            | 2           |
 * | 3 | 4    | 5            | 3           |
 * | 4 | 5    | (set later)  | 4           |
 * 
 * Step 3: Make circular
 * - 5.right = 1
 * - 1.left = 5
 * 
 * Result: 1 ⟷ 2 ⟷ 3 ⟷ 4 ⟷ 5 (circular)
 */
public class CovertBTIntoDoubleLinkedList {
    
    public static void main(String[] args) {
        // Build tree
        BinaryTreeNode root = new BinaryTreeNode(4);
        root.left = new BinaryTreeNode(2);
        root.right = new BinaryTreeNode(5);
        root.left.left = new BinaryTreeNode(1);
        root.left.right = new BinaryTreeNode(3);

        // Convert using Approach 1
        BinaryTreeNode head = binary_tree_to_cdll(root);
        System.out.println("Head of CDLL: " + head.value);
    }

    // List to store nodes in inorder sequence (Approach 1)
    static ArrayList<BinaryTreeNode> inorder = new ArrayList<>();

    // ═══════════════════════════════════════════════════════════════════════
    // APPROACH 1: Collect nodes first, then link
    // Time: O(n), Space: O(n)
    // ═══════════════════════════════════════════════════════════════════════
    
    /**
     * Convert binary tree to CDLL by collecting nodes first.
     * 
     * Algorithm:
     * 1. Inorder DFS to collect all nodes
     * 2. Link consecutive nodes
     * 3. Make it circular
     * 
     * @param root Root of binary tree
     * @return Head of CDLL
     */
    static BinaryTreeNode binary_tree_to_cdll(BinaryTreeNode root) {
        // Clear previous state
        inorder.clear();
        
        // Step 1: Collect nodes in inorder sequence
        binary_tree_to_cdll_dfs(root);

        // Step 2: Link consecutive nodes
        for (int i = 0; i < inorder.size() - 1; i++) {
            // Current node's right points to next node
            inorder.get(i).right = inorder.get(i + 1);
            // Next node's left points to current node
            inorder.get(i + 1).left = inorder.get(i);
        }
        
        // Step 3: Make it circular
        // Last node's right points to first
        inorder.get(inorder.size() - 1).right = inorder.get(0);
        // First node's left points to last
        inorder.get(0).left = inorder.get(inorder.size() - 1);
        
        // Return head (first node)
        return inorder.get(0);
    }

    /**
     * Inorder DFS to collect nodes.
     * 
     * @param root Current node
     */
    static void binary_tree_to_cdll_dfs(BinaryTreeNode root) {
        // Base case
        if (root == null) {
            return;
        }
        
        // Inorder: Left → Root → Right
        binary_tree_to_cdll_dfs(root.left);   // Process left subtree
        inorder.add(root);                     // Add current node
        binary_tree_to_cdll_dfs(root.right);  // Process right subtree
    }

    // ═══════════════════════════════════════════════════════════════════════
    // APPROACH 2: In-place conversion during traversal
    // Time: O(n), Space: O(h) - more space efficient!
    // ═══════════════════════════════════════════════════════════════════════
    
    // Track previous node in inorder traversal
    static BinaryTreeNode prev = null;
    // Track head of the list
    static BinaryTreeNode head = null;

    /**
     * Convert binary tree to CDLL in-place (optimal).
     * 
     * Algorithm:
     * 1. Inorder traversal
     * 2. At each node, link with previous node
     * 3. Track head (first node encountered)
     * 4. At the end, make circular
     * 
     * @param root Root of binary tree
     * @return Head of CDLL
     */
    static BinaryTreeNode binaryTreeToCDLL(BinaryTreeNode root) {
        if (root == null) return null;

        // Reset static variables
        prev = null;
        head = null;
        
        // Convert to DLL using inorder traversal
        convert(root);

        // Make it circular
        head.left = prev;   // First's prev = last
        prev.right = head;  // Last's next = first

        return head;
    }

    /**
     * Recursive helper for in-place conversion.
     * 
     * Uses inorder traversal with pointer manipulation.
     * 
     * @param curr Current node being processed
     */
    static void convert(BinaryTreeNode curr) {
        // Base case
        if (curr == null) return;

        // Step 1: Recursively process left subtree (smaller values first)
        convert(curr.left);

        // Step 2: Process current node - link with previous
        if (prev == null) {
            // This is the first node (leftmost = smallest in BST)
            head = curr;
        } else {
            // Link: prev ⟷ curr
            prev.right = curr;  // Previous node's next = current
            curr.left = prev;   // Current node's prev = previous
        }
        
        // Update prev for next iteration
        prev = curr;

        // Step 3: Recursively process right subtree (larger values)
        convert(curr.right);
    }
}
