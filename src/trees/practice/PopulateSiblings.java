package trees.practice;

import java.util.LinkedList;
import java.util.Queue;

/**
 * ============================================================================
 * POPULATE SIBLING POINTERS (Next Right Pointer)
 * ============================================================================
 * 
 * PROBLEM STATEMENT:
 * ------------------
 * Given a binary tree with an extra pointer 'next_right', populate each node's
 * next_right to point to its next right node on the same level.
 * If there's no next right node, next_right should be null.
 * 
 * INTUITION:
 * ----------
 * Use level order traversal (BFS):
 * - At each level, nodes are processed left to right
 * - Connect each node to the next node in the queue (at same level)
 * - Last node at each level has next_right = null (default)
 * 
 * VISUAL REPRESENTATION:
 * ----------------------
 * Before:
 *        100
 *       /   \
 *     200   300
 *     / \   / \
 *   400 500 600 700
 * 
 * After (→ represents next_right):
 *        100 → null
 *       /   \
 *     200 → 300 → null
 *     / \   / \
 *   400→500→600→700 → null
 * 
 * Level 0: 100 → null
 * Level 1: 200 → 300 → null
 * Level 2: 400 → 500 → 600 → 700 → null
 * 
 * TIME COMPLEXITY:
 * ----------------
 * O(n) - visit each node exactly once
 * 
 * SPACE COMPLEXITY:
 * -----------------
 * O(w) where w = maximum width of tree
 * For a complete binary tree, last level has n/2 nodes, so O(n)
 * 
 * DRY RUN:
 * --------
 *        100
 *       /   \
 *     200   300
 *     / \   / \
 *   400 500 600 700
 * 
 * | Step | Level | Nodes in Queue | Connections Made     |
 * |------|-------|----------------|----------------------|
 * | 1    | 0     | [100]          | -                    |
 * | 2    | 0     | [200, 300]     | -                    |
 * | 3    | 1     | [200, 300]     | 200.next = 300       |
 * | 4    | 1     | [400,500,600,700] | 200.next = 300    |
 * | 5    | 2     | [400,500,600,700] | 400→500→600→700  |
 * 
 * ALTERNATIVE: O(1) Space Solution
 * ---------------------------------
 * If the tree is PERFECT (all levels filled):
 * - Use previously established next_right pointers
 * - Process level by level without a queue
 * - Connect children using parent's next_right
 */
public class PopulateSiblings {
    
    public static void main(String[] args) {
        // Build tree
        BinaryTreeNode root = new BinaryTreeNode(100);
        root.left = new BinaryTreeNode(200);
        root.right = new BinaryTreeNode(300);
        root.left.left = new BinaryTreeNode(400);
        root.left.right = new BinaryTreeNode(500);
        root.right.left = new BinaryTreeNode(600);
        root.right.right = new BinaryTreeNode(700);
        
        // Populate sibling pointers
        populate_sibling_pointers(root);
        
        // Verify
        System.out.println("200's next_right: " + 
            (root.left.next_right != null ? root.left.next_right.value : "null"));  // Should be 300
        System.out.println("400's next_right: " + 
            (root.left.left.next_right != null ? root.left.left.next_right.value : "null"));  // Should be 500
    }
    
    /**
     * POPULATE SIBLING POINTERS using BFS
     * 
     * Algorithm:
     * 1. Use queue for level order traversal
     * 2. At each level, track previous node
     * 3. Connect previous node's next_right to current node
     * 4. Reset previous for each new level
     * 
     * Time Complexity: O(n)
     * Space Complexity: O(w) for queue
     * 
     * @param root Root of tree
     * @return Root with populated next_right pointers
     */
    static BinaryTreeNode populate_sibling_pointers(BinaryTreeNode root) {
        // Edge case: empty tree
        if (root == null) {
            return null;
        }
        
        // BFS using queue
        Queue<BinaryTreeNode> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            // Track previous node at this level (for connecting)
            BinaryTreeNode prev = null;
            
            // Number of nodes at current level
            int size = queue.size();
            
            // Process all nodes at current level
            for (int i = 0; i < size; i++) {
                // Dequeue current node
                BinaryTreeNode current = queue.poll();
                
                // Connect previous node's next_right to current
                if (prev != null) {
                    prev.next_right = current;
                }
                
                // Add children to queue for next level
                if (current.left != null) {
                    queue.add(current.left);
                }
                if (current.right != null) {
                    queue.add(current.right);
                }
                
                // Update prev for next iteration
                prev = current;
            }
            // Note: Last node's next_right remains null (default)
        }
        
        return root;
    }
    
    /**
     * ALTERNATIVE: O(1) Space for Perfect Binary Tree
     * 
     * Uses previously established next_right pointers to traverse level.
     * Only works for PERFECT binary trees (all levels fully filled).
     * 
     * @param root Root of perfect binary tree
     * @return Root with populated next_right pointers
     */
    static BinaryTreeNode populate_sibling_pointers_constant_space(BinaryTreeNode root) {
        if (root == null) return null;
        
        // Start at root level
        BinaryTreeNode levelStart = root;
        
        while (levelStart.left != null) {  // While not at leaf level
            BinaryTreeNode current = levelStart;
            
            while (current != null) {
                // Connect left child to right child
                current.left.next_right = current.right;
                
                // Connect right child to next node's left child
                if (current.next_right != null) {
                    current.right.next_right = current.next_right.left;
                }
                
                // Move to next node at this level using next_right
                current = current.next_right;
            }
            
            // Move to next level
            levelStart = levelStart.left;
        }
        
        return root;
    }
}
