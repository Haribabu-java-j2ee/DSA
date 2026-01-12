package trees.practice;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

/**
 * ============================================================================
 * LEVEL ORDER TRAVERSAL - N-ARY TREE
 * ============================================================================
 * 
 * PROBLEM STATEMENT:
 * ------------------
 * Given an n-ary tree, return the level order traversal of its nodes' values.
 * An n-ary tree is a tree where each node can have any number of children.
 * 
 * DIFFERENCE FROM BINARY TREE:
 * ----------------------------
 * - Binary tree: Each node has at most 2 children (left, right)
 * - N-ary tree: Each node has a LIST of children (can be 0, 1, 2, 3, ... n children)
 * 
 * INTUITION:
 * ----------
 * Same BFS approach as binary tree level order:
 * 1. Use a queue to process nodes level by level
 * 2. For each node, enqueue ALL its children (instead of just left/right)
 * 
 * VISUAL REPRESENTATION:
 * ----------------------
 *          1
 *        / | \
 *       3  4  2
 *      / \
 *     5   6
 * 
 * Level 0: [1]
 * Level 1: [3, 4, 2]
 * Level 2: [5, 6]
 * 
 * Result: [[1], [3, 4, 2], [5, 6]]
 * 
 * TIME COMPLEXITY:
 * ----------------
 * O(n) - visit each node exactly once
 * 
 * SPACE COMPLEXITY:
 * -----------------
 * O(w) for queue where w = maximum width (maximum children at any level)
 * Worst case: O(n) if all nodes are at same level
 * 
 * DRY RUN:
 * --------
 *          1
 *        / | \
 *       3  4  2
 *      / \
 *     5   6
 * 
 * | Step | Queue       | Level Processed | Result                    |
 * |------|-------------|-----------------|---------------------------|
 * | 1    | [1]         | -               | []                        |
 * | 2    | [3, 4, 2]   | [1]             | [[1]]                     |
 * | 3    | [5, 6]      | [3, 4, 2]       | [[1], [3, 4, 2]]          |
 * | 4    | []          | [5, 6]          | [[1], [3, 4, 2], [5, 6]]  |
 * 
 * Final: [[1], [3, 4, 2], [5, 6]] ✓
 */
public class LevelOrderNArrayTree {
    
    public static void main(String[] args) {
        // Build n-ary tree
        //          1
        //        / | \
        //       3  4  2
        //      / \
        //     5   6
        TreeNode root = new TreeNode(1);
        
        TreeNode child1 = new TreeNode(3);
        TreeNode child2 = new TreeNode(4);
        TreeNode child3 = new TreeNode(2);
        
        // Child1 (node 3) has two children: 5 and 6
        TreeNode child2Sub1 = new TreeNode(5);
        TreeNode child2Sub2 = new TreeNode(6);
        child1.children.add(child2Sub1);
        child1.children.add(child2Sub2);
        
        // Add children to root
        root.children.add(child1);
        root.children.add(child2);
        root.children.add(child3);

        // Level order traversal
        System.out.println("Level order of N-ary tree:");
        level_order(root).forEach(System.out::println);
    }
    
    /**
     * LEVEL ORDER TRAVERSAL FOR N-ARY TREE
     * 
     * Algorithm:
     * 1. Initialize queue with root
     * 2. While queue not empty:
     *    a. Process all nodes at current level
     *    b. For each node, add ALL its children to queue
     * 3. Return collected levels
     * 
     * Time Complexity: O(n)
     * Space Complexity: O(w) for queue
     * 
     * @param root Root of n-ary tree
     * @return List of levels
     */
    static ArrayList<ArrayList<Integer>> level_order(TreeNode root) {
        ArrayList<ArrayList<Integer>> result = new ArrayList<>();
        
        // Edge case: empty tree
        if (root == null) {
            return result;
        }
        
        // BFS using queue
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        
        // Process level by level
        while (!queue.isEmpty()) {
            // Create list for current level
            ArrayList<Integer> level = new ArrayList<>();
            
            // Number of nodes at this level
            int size = queue.size();
            
            // Process all nodes at current level
            for (int i = 0; i < size; i++) {
                // Dequeue node
                TreeNode current = queue.poll();
                
                // Add value to current level
                level.add(current.value);
                
                // KEY DIFFERENCE FROM BINARY TREE:
                // Enqueue ALL children (not just left/right)
                for (TreeNode child : current.children) {
                    queue.add(child);
                }
            }
            
            // Add completed level to result
            result.add(level);
        }

        return result;
    }
}

/**
 * N-ARY TREE NODE CLASS
 * 
 * Unlike binary tree node, this has a LIST of children
 * instead of just left/right pointers.
 */
class TreeNode {
    Integer value;                  // Node value
    ArrayList<TreeNode> children;   // List of child nodes

    /**
     * Constructor - creates node with given value
     * Initializes empty children list
     */
    TreeNode(Integer value) {
        this.value = value;
        this.children = new ArrayList<>(3);  // Initial capacity 3
    }
}
