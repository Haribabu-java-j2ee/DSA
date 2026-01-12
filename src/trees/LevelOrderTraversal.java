package trees;

import java.util.*;

/**
 * ============================================================================
 * LEVEL ORDER TRAVERSAL - BFS Traversal of Binary Tree
 * ============================================================================
 * link: https://leetcode.com/problems/binary-tree-level-order-traversal/
 * PROBLEM STATEMENT:
 * ------------------
 * Given a binary tree, return its level order traversal.
 * (i.e., visit nodes level by level, from left to right at each level)
 * 
 * This is also known as Breadth-First Search (BFS) traversal.
 * 
 * INTUITION:
 * ----------
 * Unlike DFS which goes deep first, BFS explores all neighbors at current level
 * before moving to the next level. This is achieved using a QUEUE:
 * 
 * 1. Add root to queue
 * 2. While queue is not empty:
 *    a. Record current queue size (= number of nodes at this level)
 *    b. Process exactly that many nodes from queue
 *    c. For each node, add its children to queue (for next level)
 *    d. Collected nodes form one level
 * 
 * WHY QUEUE?
 * ----------
 * Queue follows FIFO (First In, First Out):
 * - Nodes at current level were added first
 * - They'll be processed first (before their children)
 * - Children of current level go to the back of queue
 * - They'll be processed only after current level is done
 * 
 * VISUAL REPRESENTATION:
 * ----------------------
 *         4
 *        / \
 *       0   3
 *      /   /
 *     2   5
 *    /
 *   6
 *  /
 * 1
 * 
 * Level 0: [4]
 * Level 1: [0, 3]
 * Level 2: [2, 5]
 * Level 3: [6]
 * Level 4: [1]
 * 
 * TIME COMPLEXITY:
 * ----------------
 * O(n) - each node is visited exactly once
 * 
 * SPACE COMPLEXITY:
 * -----------------
 * O(w) where w = maximum width of the tree
 * - Best case (skewed tree): O(1)
 * - Worst case (complete binary tree): O(n/2) = O(n) at last level
 * 
 * DRY RUN:
 * --------
 * Tree:
 *         4
 *        / \
 *       0   3
 *      /   /
 *     2   5
 * 
 * | Step | Queue State    | Processing | Level Output | Action                    |
 * |------|----------------|------------|--------------|---------------------------|
 * | 1    | [4]            | -          | -            | Start with root           |
 * | 2    | [4]            | 4          | [4]          | Pop 4, add 0,3            |
 * | 3    | [0, 3]         | 0          | [0]          | Pop 0, add 2              |
 * | 4    | [3, 2]         | 3          | [0, 3]       | Pop 3, add 5              |
 * | 5    | [2, 5]         | 2          | [2]          | Pop 2 (no children)       |
 * | 6    | [5]            | 5          | [2, 5]       | Pop 5 (no children)       |
 * 
 * Result: [[4], [0, 3], [2, 5]]
 * 
 * DFS ALTERNATIVE:
 * ----------------
 * Level order can also be done with DFS by passing level number:
 * - Pre-order DFS with level tracking
 * - Add to result[level] based on current depth
 * - Less common but useful when recursion is preferred
 */
public class LevelOrderTraversal {
    
    public static void main(String[] args) {
        // Build test tree using values (BST insertion)
        Integer[] arr = {4, 0, 3, 2, 5, null, null, 6, null, null, null, 1};
        ArrayList<Integer> list = new ArrayList<>();
        Arrays.stream(arr).forEach(list::add);
        BinaryTreeNode root = BSTInsert.build_a_bst(list);
        
        // Perform level order traversal
        System.out.println("Level Order Traversal:");
        level_order_traversal(root).forEach(System.out::println);
    }

    /**
     * LEVEL ORDER TRAVERSAL (BFS)
     * 
     * Algorithm:
     * 1. Initialize queue with root
     * 2. While queue is not empty:
     *    a. Count nodes at current level (queue.size())
     *    b. Process exactly that many nodes
     *    c. Add each node's value to current level list
     *    d. Enqueue children for next level
     *    e. Add completed level to result
     * 
     * Time Complexity: O(n) - visit each node once
     * Space Complexity: O(w) - queue holds at most one level's nodes
     * 
     * @param root Root of the binary tree
     * @return List of lists, each inner list contains values at one level
     */
    static ArrayList<ArrayList<Integer>> level_order_traversal(BinaryTreeNode root) {
        ArrayList<ArrayList<Integer>> result = new ArrayList<>();
        
        // Edge case: empty tree
        if (root == null) {
            return result;
        }
        
        // Queue for BFS - stores nodes to be processed
        Queue<BinaryTreeNode> queue = new LinkedList<>();
        queue.add(root);  // Start with root
        
        // Process level by level
        while (!queue.isEmpty()) {
            // List to store current level's values
            ArrayList<Integer> level = new ArrayList<>();
            
            // CRITICAL: Capture queue size BEFORE processing
            // This tells us how many nodes are at current level
            int levelSize = queue.size();
            
            // Process exactly 'levelSize' nodes (one complete level)
            for (int i = 0; i < levelSize; ++i) {
                // Dequeue front node
                BinaryTreeNode current = queue.poll();
                
                // Add current node's value to this level
                level.add(current.value);
                
                // Enqueue left child (if exists) for next level
                if (current.left != null) {
                    queue.add(current.left);
                }
                
                // Enqueue right child (if exists) for next level
                if (current.right != null) {
                    queue.add(current.right);
                }
            }
            
            // Add completed level to result
            result.add(level);
        }
        
        return result;
    }
    
    /**
     * LEVEL ORDER USING DFS (Alternative approach)
     * 
     * Uses recursive DFS with level tracking.
     * Less intuitive but doesn't require explicit queue.
     * 
     * @param root Root of the binary tree
     * @return List of lists for level order
     */
    static ArrayList<ArrayList<Integer>> level_order_dfs(BinaryTreeNode root) {
        ArrayList<ArrayList<Integer>> result = new ArrayList<>();
        level_order_dfs_helper(root, 0, result);
        return result;
    }
    
    /**
     * DFS HELPER - Adds node value to appropriate level
     * 
     * @param node Current node
     * @param level Current depth level
     * @param result Accumulator for all levels
     */
    private static void level_order_dfs_helper(BinaryTreeNode node, int level, 
                                               ArrayList<ArrayList<Integer>> result) {
        // Base case: null node
        if (node == null) {
            return;
        }
        
        // If this is a new level, add empty list for it
        if (level >= result.size()) {
            result.add(new ArrayList<>());
        }
        
        // Add current node's value to its level
        result.get(level).add(node.value);
        
        // Recursively process children at next level
        level_order_dfs_helper(node.left, level + 1, result);
        level_order_dfs_helper(node.right, level + 1, result);
    }
}
