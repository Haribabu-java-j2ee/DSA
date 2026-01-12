package trees.practice;

import java.util.*;

/**
 * ============================================================================
 * LEVEL ORDER TRAVERSAL - BOTTOM UP
 * ============================================================================
 * 
 * PROBLEM STATEMENT:
 * ------------------
 * Given a binary tree, return the bottom-up level order traversal of its nodes.
 * (i.e., from left to right, level by level from leaf to root)
 * 
 * LeetCode: https://leetcode.com/problems/binary-tree-level-order-traversal-ii/
 * 
 * INTUITION:
 * ----------
 * Same as regular level order traversal, but reverse the result at the end!
 * 
 * TWO APPROACHES:
 * 1. Standard BFS + Reverse: Do regular level order, then reverse
 * 2. Add to front: Insert each level at the beginning of result list
 * 
 * Approach 1 is simpler and shown here.
 * 
 * VISUAL REPRESENTATION:
 * ----------------------
 * Tree:
 *       3
 *      / \
 *     9  20
 *        / \
 *       15  7
 * 
 * Regular level order: [[3], [9, 20], [15, 7]]
 * Bottom-up:           [[15, 7], [9, 20], [3]]
 * 
 * TIME COMPLEXITY:
 * ----------------
 * O(n) - visit each node once + O(L) for reversing where L = number of levels
 * Overall: O(n) since L <= n
 * 
 * SPACE COMPLEXITY:
 * -----------------
 * O(n) for result storage + O(w) for queue where w = max width
 * Overall: O(n)
 * 
 * DRY RUN:
 * --------
 * Tree:
 *       3
 *      / \
 *     9  20
 *        / \
 *       15  7
 * 
 * | Step | Queue       | Level Processed | Result So Far              |
 * |------|-------------|-----------------|----------------------------|
 * | 1    | [3]         | -               | []                         |
 * | 2    | [9, 20]     | [3]             | [[3]]                      |
 * | 3    | [15, 7]     | [9, 20]         | [[3], [9, 20]]             |
 * | 4    | []          | [15, 7]         | [[3], [9, 20], [15, 7]]    |
 * 
 * After reverse: [[15, 7], [9, 20], [3]] ✓
 */
public class LevelOrderBottomUp {
    
    public static void main(String[] args) {
        // Build tree
        BinaryTreeNode root = new BinaryTreeNode(3);
        root.left = new BinaryTreeNode(9);
        root.right = new BinaryTreeNode(20);
        root.right.left = new BinaryTreeNode(15);
        root.right.right = new BinaryTreeNode(7);
        
        // Get bottom-up level order
        System.out.println("Bottom-up level order:");
        levelOrderBottom(root).forEach(System.out::println);
    }

    /**
     * BOTTOM-UP LEVEL ORDER TRAVERSAL
     * 
     * Algorithm:
     * 1. Do standard BFS level order traversal
     * 2. Reverse the result list
     * 
     * Time Complexity: O(n)
     * Space Complexity: O(n)
     * 
     * @param root Root of binary tree
     * @return Levels from bottom to top
     */
    public static List<List<Integer>> levelOrderBottom(BinaryTreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        
        // Edge case: empty tree
        if (root == null) {
            return result;
        }
        
        // Standard BFS using queue
        Queue<BinaryTreeNode> queue = new LinkedList<>();
        queue.add(root);
        
        // Process level by level
        while (!queue.isEmpty()) {
            // Create list for current level
            ArrayList<Integer> level = new ArrayList<>();
            
            // Get number of nodes at this level
            int size = queue.size();
            
            // Process all nodes at current level
            for (int i = 0; i < size; i++) {
                // Dequeue node
                BinaryTreeNode current = queue.poll();
                
                // Add to current level
                level.add(current.value);
                
                // Enqueue children for next level
                if (current.left != null) {
                    queue.add(current.left);
                }
                if (current.right != null) {
                    queue.add(current.right);
                }
            }
            
            // Add completed level to result
            result.add(level);
        }
        
        // Reverse to get bottom-up order
        Collections.reverse(result);
        
        return result;
    }
    
    /**
     * ALTERNATIVE: Add to front (LinkedList)
     * 
     * Instead of reversing at end, add each level to the front.
     * More efficient if using LinkedList (O(1) insertion at front).
     * 
     * @param root Root of binary tree
     * @return Levels from bottom to top
     */
    public static List<List<Integer>> levelOrderBottomAlternative(BinaryTreeNode root) {
        LinkedList<List<Integer>> result = new LinkedList<>();
        
        if (root == null) {
            return result;
        }
        
        Queue<BinaryTreeNode> queue = new LinkedList<>();
        queue.add(root);
        
        while (!queue.isEmpty()) {
            ArrayList<Integer> level = new ArrayList<>();
            int size = queue.size();
            
            for (int i = 0; i < size; i++) {
                BinaryTreeNode current = queue.poll();
                level.add(current.value);
                
                if (current.left != null) queue.add(current.left);
                if (current.right != null) queue.add(current.right);
            }
            
            // Add to FRONT instead of back
            result.addFirst(level);  // O(1) for LinkedList
        }
        
        return result;
    }
}
