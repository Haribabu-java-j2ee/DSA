package trees.practice;

import java.util.ArrayList;

/**
 * ============================================================================
 * MAXIMUM HEIGHT OF N-ARY TREE
 * ============================================================================
 * 
 * PROBLEM STATEMENT:
 * ------------------
 * Given an n-ary tree, find its maximum height (depth).
 * Height is defined as the number of NODES on the longest path from root to leaf.
 * 
 * NOTE: If counting EDGES instead of nodes, subtract 1 from the result.
 * 
 * TWO APPROACHES:
 * ---------------
 * 1. Path tracking (using backtracking)
 *    - Track current path and update max when reaching leaf
 *    
 * 2. Recursive (simpler and cleaner)
 *    - Height = 1 + max(height of all children)
 * 
 * INTUITION:
 * ----------
 * The height of a tree is:
 * - 0 if tree is empty
 * - 1 if tree has only root (no children)
 * - 1 + maximum height among all children subtrees
 * 
 * VISUAL REPRESENTATION:
 * ----------------------
 *          1          Height = 3
 *        / | \
 *       2  3  5       Height of subtree(2) = 1
 *             |       Height of subtree(3) = 1
 *             4       Height of subtree(5) = 2
 * 
 * Max height = 1 + max(1, 1, 2) = 1 + 2 = 3
 * 
 * TIME COMPLEXITY:
 * ----------------
 * Both approaches: O(n) - visit each node once
 * 
 * SPACE COMPLEXITY:
 * -----------------
 * O(h) for recursion stack where h = height of tree
 * Approach 1 also uses O(h) for the path tracking list
 * 
 * DRY RUN (Approach 2 - Recursive):
 * ---------------------------------
 *          1
 *        / | \
 *       2  3  5
 *             |
 *             4
 * 
 * | Node | Children Heights | Calculation     | Return |
 * |------|------------------|-----------------|--------|
 * | 4    | []               | 0 + 1 = 1       | 1      |
 * | 2    | []               | 0 + 1 = 1       | 1      |
 * | 3    | []               | 0 + 1 = 1       | 1      |
 * | 5    | [1]              | max(1) + 1 = 2  | 2      |
 * | 1    | [1, 1, 2]        | max(1,1,2) + 1  | 3      |
 * 
 * Result: 3 ✓
 */
public class MaxHeightInNArrTree {
    
    public static void main(String[] args) {
        // Build n-ary tree
        //          1
        //        / | \
        //       2  3  5
        //             |
        //             4
        TreeNode root = new TreeNode(1);
        ArrayList<TreeNode> list = new ArrayList<>();
        
        TreeNode child1 = new TreeNode(2);
        TreeNode child2 = new TreeNode(3);
        TreeNode subchild = new TreeNode(4);
        
        ArrayList<TreeNode> sublist = new ArrayList<>();
        sublist.add(subchild);
        
        TreeNode child3 = new TreeNode(5);
        child3.children = sublist;  // 5 has child 4
        
        list.add(child1);
        list.add(child2);
        list.add(child3);
        root.children = list;

        // Test both approaches
        System.out.println("Height (Approach 1 - Path tracking): " + find_height(root));
        System.out.println("Height (Approach 2 - Recursive): " + find_height_helper1(root));
    }

    // ═══════════════════════════════════════════════════════════════════════
    // APPROACH 1: Path Tracking with Backtracking
    // Time: O(n), Space: O(h)
    // ═══════════════════════════════════════════════════════════════════════
    
    // Global variable to track maximum height found
    static int max_height;
    
    /**
     * Find height using path tracking approach.
     * 
     * Algorithm:
     * 1. Maintain a list representing current path
     * 2. At each leaf, compare path length with max
     * 3. Backtrack when returning from recursion
     * 
     * @param root Root of n-ary tree
     * @return Maximum height (number of nodes)
     */
    static Integer find_height(TreeNode root) {
        if (root == null) {
            return 0;
        }
        
        max_height = 0;
        find_height_helper(root, new ArrayList<>());
        return max_height;
    }
    
    /**
     * Recursive helper with path tracking.
     * 
     * @param root Current node
     * @param temp Current path from root to this node
     */
    static void find_height_helper(TreeNode root, ArrayList<Integer> temp) {
        // Check if this is a leaf node (no children)
        if (root.children == null || root.children.isEmpty()) {
            // Add current node to path
            temp.add(root.value);
            
            // Update max height if current path is longer
            max_height = Math.max(max_height, temp.size());
            
            // Backtrack: remove current node before returning
            temp.remove(temp.size() - 1);
            return;
        }

        // Add current node to path
        temp.add(root.value);
        
        // Recurse for all children
        for (TreeNode child : root.children) {
            find_height_helper(child, temp);
        }
        
        // Backtrack: remove current node
        temp.remove(temp.size() - 1);
    }

    // ═══════════════════════════════════════════════════════════════════════
    // APPROACH 2: Simple Recursive (Recommended)
    // Time: O(n), Space: O(h)
    // ═══════════════════════════════════════════════════════════════════════
    
    /**
     * Find height using simple recursive approach.
     * 
     * Algorithm:
     * height(node) = 1 + max(height(child) for all children)
     * 
     * This is cleaner and more intuitive than path tracking.
     * 
     * @param root Current node
     * @return Height of subtree rooted at this node
     */
    static int find_height_helper1(TreeNode root) {
        // Base case: null node has height 0
        if (root == null) {
            return 0;
        }
        
        // Track maximum height among all children
        int height = 0;

        // Find max height among all children
        for (TreeNode child : root.children) {
            height = Math.max(height, find_height_helper1(child));
        }
        
        // Current node adds 1 to the height
        return height + 1;
    }
}
