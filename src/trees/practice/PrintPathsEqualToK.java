package trees.practice;

import java.util.ArrayList;

/**
 * ============================================================================
 * PATH SUM - Check if any root-to-leaf path sums to K
 * ============================================================================
 * 
 * PROBLEM STATEMENT:
 * ------------------
 * Given a binary tree and an integer k, determine if there exists a 
 * root-to-leaf path where the sum of all node values equals k.
 * 
 * DIFFERENCE FROM PrintAllPathEqualstoK:
 * - This problem only checks EXISTENCE (returns boolean)
 * - PrintAllPathEqualstoK finds ALL such paths
 * 
 * INTUITION:
 * ----------
 * Use DFS with running sum:
 * 1. Track cumulative sum as we traverse down
 * 2. At each leaf, check if sum equals k
 * 3. Return true immediately if any path works (short-circuit)
 * 4. Use OR to combine left and right subtree results
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
 * k = 4
 * 
 * Paths:
 * - 0 → 1 → 3 = 4 ✓ (Found!)
 * - 0 → 1 → 4 = 5 ✗
 * - 0 → 2 = 2 ✗
 * 
 * Result: true (path 0→1→3 exists)
 * 
 * TIME COMPLEXITY:
 * ----------------
 * O(n) - visit each node once in worst case
 * Best case: O(h) if valid path found early (leftmost path)
 * 
 * SPACE COMPLEXITY:
 * -----------------
 * O(h) for recursion stack where h = height
 * - Best case (balanced): O(log n)
 * - Worst case (skewed): O(n)
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
 * k = 4
 * 
 * | Node | Running Sum | Is Leaf? | Sum = k? | Return |
 * |------|-------------|----------|----------|--------|
 * | 0    | 0           | No       | -        | Recurse|
 * | 1    | 1           | No       | -        | Recurse|
 * | 3    | 4           | Yes      | 4 = 4 ✓  | true   |
 * 
 * Short-circuits and returns true immediately!
 * 
 * For k = 6:
 * | Node | Running Sum | Is Leaf? | Sum = k? | Return     |
 * |------|-------------|----------|----------|------------|
 * | 0    | 0           | No       | -        | Recurse    |
 * | 1    | 1           | No       | -        | Recurse    |
 * | 3    | 4           | Yes      | 4 ≠ 6    | false      |
 * | 4    | 5           | Yes      | 5 ≠ 6    | false      |
 * | 1    | -           | -        | -        | false OR false = false |
 * | 2    | 2           | Yes      | 2 ≠ 6    | false      |
 * | 0    | -           | -        | -        | false OR false = false |
 * 
 * Result: false (no path sums to 6)
 */
public class PrintPathsEqualToK {
    
    public static void main(String[] args) {
        // Build tree
        BinaryTreeNode root = new BinaryTreeNode(0);
        root.left = new BinaryTreeNode(1);
        root.right = new BinaryTreeNode(2);
        root.left.left = new BinaryTreeNode(3);
        root.left.right = new BinaryTreeNode(4);
        
        System.out.println("Path sum = 4 exists: " + path_sum(root, 4));  // true
        System.out.println("Path sum = 5 exists: " + path_sum(root, 5));  // true (0→1→4)
        System.out.println("Path sum = 6 exists: " + path_sum(root, 6));  // false

        // Test with all zeros
        root = new BinaryTreeNode(0);
        root.left = new BinaryTreeNode(0);
        root.right = new BinaryTreeNode(0);
        System.out.println("All zeros, path sum = 0 exists: " + path_sum(root, 0));  // true
    }

    /**
     * CHECK IF PATH SUM EXISTS
     * 
     * Wrapper function that starts DFS with initial sum of 0.
     * 
     * @param root Root of tree
     * @param k Target sum
     * @return true if any root-to-leaf path sums to k
     */
    static Boolean path_sum(BinaryTreeNode root, Integer k) {
        return path_sum_util(root, k, 0L);
    }

    /**
     * DFS HELPER - Recursive search for path with target sum
     * 
     * Algorithm:
     * 1. If null node, return false (no path)
     * 2. If leaf node, check if running sum + value = k
     * 3. Otherwise, recurse on children with updated sum
     * 4. Return true if EITHER child returns true (OR logic)
     * 
     * @param root Current node
     * @param k Target sum
     * @param currentsum Sum accumulated so far
     * @return true if any path from this node to leaf sums to k
     */
    static boolean path_sum_util(BinaryTreeNode root, Integer k, long currentsum) {
        // Base case: null node (no path)
        if (root == null) {
            return false;
        }
        
        // LEAF NODE: Check if path sum equals k
        if (root.left == null && root.right == null) {
            return currentsum + root.value == k;
        }
        
        // INTERNAL NODE: Check left OR right subtree
        // Short-circuits if left returns true
        return path_sum_util(root.left, k, currentsum + root.value) || 
               path_sum_util(root.right, k, currentsum + root.value);
    }
}
