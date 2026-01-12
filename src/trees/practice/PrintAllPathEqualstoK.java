package trees.practice;

import java.util.ArrayList;

/**
 * ============================================================================
 * PRINT ALL ROOT-TO-LEAF PATHS WITH SUM K
 * ============================================================================
 * 
 * PROBLEM STATEMENT:
 * ------------------
 * Given a binary tree and an integer k, find all root-to-leaf paths where
 * the sum of node values equals k.
 * 
 * INTUITION:
 * ----------
 * Use DFS with backtracking:
 * 1. Maintain current path and running sum
 * 2. At each leaf, check if sum equals k
 * 3. If yes, add path to result
 * 4. Backtrack after processing (remove current node from path)
 * 
 * KEY INSIGHT:
 * - Only check sum at LEAF nodes (complete root-to-leaf paths)
 * - Use backtracking to reuse path list efficiently
 * 
 * VISUAL REPRESENTATION:
 * ----------------------
 * Tree:
 *         7
 *        / \
 *      -7  -7
 *      / \   \
 *     7   7   7
 *        / \   \
 *      -7  -7  -7
 * 
 * Target sum k = 0
 * 
 * Valid paths with sum 0:
 * - 7 → -7 → 7 = 7 (not 0)
 * - 7 → -7 → 7 → -7 = 0 ✓
 * - 7 → -7 → 7 → -7 = 0 ✓
 * - 7 → -7 → 7 → -7 = 0 ✓
 * 
 * TIME COMPLEXITY:
 * ----------------
 * O(n * log n) to O(n * n)
 * - O(n) to visit all nodes
 * - O(path length) to copy path at each leaf
 * - Path length is O(log n) for balanced, O(n) for skewed
 * 
 * SPACE COMPLEXITY:
 * -----------------
 * O(h) for recursion + O(h) for current path
 * O(result) for storing all valid paths
 * 
 * DRY RUN:
 * --------
 * Tree:
 *         7
 *        / \
 *      -7  -7
 *      / \   
 *     7   7  
 * 
 * Target k = 0
 * 
 * | Node | Path So Far | Sum | Action              |
 * |------|-------------|-----|---------------------|
 * | 7    | [7]         | 7   | Go left             |
 * | -7   | [7, -7]     | 0   | Go left             |
 * | 7    | [7, -7, 7]  | 7   | Leaf, sum≠0, skip   |
 * | -7   | [7, -7]     | 0   | Go right            |
 * | 7    | [7, -7, 7]  | 7   | Leaf, sum≠0, skip   |
 * | 7    | [7]         | 7   | Go right            |
 * | -7   | [7, -7]     | 0   | Leaf, sum=0, ADD!   |
 * 
 * Result: [[7, -7]]
 */
public class PrintAllPathEqualstoK {
    
    public static void main(String[] args) {
        // Build tree
        BinaryTreeNode root = new BinaryTreeNode(7);
        root.left = new BinaryTreeNode(-7);
        root.right = new BinaryTreeNode(-7);
        root.left.left = new BinaryTreeNode(7);
        root.left.right = new BinaryTreeNode(7);
        root.right.right = new BinaryTreeNode(7);
        root.left.right.left = new BinaryTreeNode(-7);
        root.left.right.right = new BinaryTreeNode(-7);
        root.right.right.right = new BinaryTreeNode(-7);
        
        System.out.println("Paths with sum 0:");
        all_paths_sum_k(root, 0).forEach(System.out::println);
    }

    /**
     * FIND ALL PATHS WITH SUM K
     * 
     * @param root Root of tree
     * @param k Target sum
     * @return List of paths that sum to k
     */
    static ArrayList<ArrayList<Integer>> all_paths_sum_k(BinaryTreeNode root, Integer k) {
        ArrayList<ArrayList<Integer>> result = new ArrayList<>();
        
        // DFS with backtracking
        all_paths_sum_k_util(root, k, 0L, result, new ArrayList<>());
        
        // If no paths found, return [-1] as per problem requirement
        if (result.isEmpty()) {
            ArrayList<Integer> temp = new ArrayList<>();
            temp.add(-1);
            result.add(temp);
        }
        
        return result;
    }

    /**
     * DFS HELPER with backtracking
     * 
     * Algorithm:
     * 1. If node is null, return
     * 2. If node is leaf:
     *    - Check if sum + node.value = k
     *    - If yes, add path to result
     * 3. Otherwise, recurse on children
     * 4. BACKTRACK: remove current node from path before returning
     * 
     * @param root Current node
     * @param k Target sum
     * @param currentSum Sum of path so far
     * @param result Accumulator for valid paths
     * @param temp Current path (modified in place)
     */
    static void all_paths_sum_k_util(BinaryTreeNode root, Integer k, long currentSum,
                                      ArrayList<ArrayList<Integer>> result, 
                                      ArrayList<Integer> temp) {
        // Base case: null node
        if (root == null) {
            return;
        }

        // LEAF NODE: Check if path sum equals k
        if (root.left == null && root.right == null) {
            // Add current node to sum and check
            currentSum += root.value;
            
            if (currentSum == k) {
                // Found a valid path! Add to result
                temp.add(root.value);
                result.add(new ArrayList<>(temp));  // Create copy of path
                temp.remove(temp.size() - 1);        // Backtrack
            }
            return;
        }

        // INTERNAL NODE: Add to path and recurse
        temp.add(root.value);
        
        // Recurse on left child
        if (root.left != null) {
            all_paths_sum_k_util(root.left, k, currentSum + root.value, result, temp);
        }

        // Recurse on right child
        if (root.right != null) {
            all_paths_sum_k_util(root.right, k, currentSum + root.value, result, temp);
        }
        
        // BACKTRACK: Remove current node from path
        temp.remove(temp.size() - 1);
    }
}
