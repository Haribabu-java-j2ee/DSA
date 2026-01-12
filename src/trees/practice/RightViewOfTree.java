package trees.practice;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

/**
 * ============================================================================
 * RIGHT VIEW OF BINARY TREE
 * ============================================================================
 * 
 * PROBLEM STATEMENT:
 * ------------------
 * Given a binary tree, return the values of nodes visible when the tree
 * is viewed from the RIGHT side (rightmost node at each level).
 * 
 * INTUITION:
 * ----------
 * Use level order traversal (BFS):
 * - At each level, the LAST node processed is the rightmost
 * - Only add the last node of each level to result
 * 
 * ALTERNATIVE (DFS):
 * - Traverse right subtree first
 * - Add first node encountered at each new level
 * 
 * VISUAL REPRESENTATION:
 * ----------------------
 * Tree:
 *         0
 *        / \
 *       1   2
 *      / \
 *     3   4
 * 
 * Right view: [0, 2, 4]
 * 
 * Level 0: Rightmost = 0
 * Level 1: Rightmost = 2
 * Level 2: Rightmost = 4 (not 3!)
 * 
 * LEFT VIEW (for comparison):
 * Level 0: Leftmost = 0
 * Level 1: Leftmost = 1
 * Level 2: Leftmost = 3
 * Left view: [0, 1, 3]
 * 
 * TIME COMPLEXITY:
 * ----------------
 * O(n) - visit each node exactly once
 * 
 * SPACE COMPLEXITY:
 * -----------------
 * O(w) where w = maximum width of tree
 * For complete binary tree: O(n/2) = O(n)
 * 
 * DRY RUN:
 * --------
 * Tree:
 *         0
 *        / \
 *       1   2
 *      / \
 *     3   4
 * 
 * | Step | Level | Queue       | Processing | Is Last? | Result  |
 * |------|-------|-------------|------------|----------|---------|
 * | 1    | 0     | [0]         | 0          | Yes      | [0]     |
 * | 2    | 1     | [1, 2]      | 1          | No       | [0]     |
 * | 3    | 1     | [2, 3, 4]   | 2          | Yes      | [0, 2]  |
 * | 4    | 2     | [3, 4]      | 3          | No       | [0, 2]  |
 * | 5    | 2     | [4]         | 4          | Yes      | [0,2,4] |
 * 
 * Result: [0, 2, 4] ✓
 */
public class RightViewOfTree {
    
    public static void main(String[] args) {
        // Build tree
        BinaryTreeNode root = new BinaryTreeNode(0);
        root.left = new BinaryTreeNode(1);
        root.right = new BinaryTreeNode(2);
        root.left.left = new BinaryTreeNode(3);
        root.left.right = new BinaryTreeNode(4);
        
        System.out.println("Right view: ");
        right_view(root).forEach(System.out::println);  // [0, 2, 4]
    }

    /**
     * GET RIGHT VIEW OF BINARY TREE
     * 
     * Algorithm:
     * 1. Use BFS (level order traversal)
     * 2. For each level, only add the LAST node to result
     * 3. Check if current is last using index == size - 1
     * 
     * Time Complexity: O(n)
     * Space Complexity: O(w) for queue
     * 
     * @param root Root of tree
     * @return List of rightmost values at each level
     */
    static ArrayList<Integer> right_view(BinaryTreeNode root) {
        // Edge case: empty tree
        if (root == null) {
            return new ArrayList<>();
        }
        
        // BFS using queue
        Queue<BinaryTreeNode> queue = new LinkedList<>();
        queue.add(root);
        ArrayList<Integer> result = new ArrayList<>();
        
        while (!queue.isEmpty()) {
            // Number of nodes at current level
            int size = queue.size();

            // Process all nodes at current level
            for (int i = 0; i < size; i++) {
                BinaryTreeNode current = queue.poll();
                
                // Add to result ONLY if this is the LAST node in level
                if (i == size - 1) {
                    result.add(current.value);
                }
                
                // Add children for next level (left first, then right)
                if (current.left != null) {
                    queue.add(current.left);
                }
                if (current.right != null) {
                    queue.add(current.right);
                }
            }
        }
        
        return result;
    }
    
    /**
     * LEFT VIEW OF BINARY TREE (Bonus)
     * 
     * Same approach but add FIRST node of each level.
     * 
     * @param root Root of tree
     * @return List of leftmost values at each level
     */
    static ArrayList<Integer> left_view(BinaryTreeNode root) {
        if (root == null) {
            return new ArrayList<>();
        }
        
        Queue<BinaryTreeNode> queue = new LinkedList<>();
        queue.add(root);
        ArrayList<Integer> result = new ArrayList<>();
        
        while (!queue.isEmpty()) {
            int size = queue.size();

            for (int i = 0; i < size; i++) {
                BinaryTreeNode current = queue.poll();
                
                // Add to result ONLY if this is the FIRST node in level
                if (i == 0) {
                    result.add(current.value);
                }
                
                if (current.left != null) queue.add(current.left);
                if (current.right != null) queue.add(current.right);
            }
        }
        
        return result;
    }
}
