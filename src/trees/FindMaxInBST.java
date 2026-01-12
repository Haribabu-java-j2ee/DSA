package trees;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * ============================================================================
 * FIND MAXIMUM IN BST - Find the largest value in Binary Search Tree
 * ============================================================================
 * 
 * PROBLEM STATEMENT:
 * ------------------
 * Given a BST, find the maximum value stored in it.
 * 
 * BST PROPERTY LEVERAGED:
 * -----------------------
 * In a BST, for every node:
 * - All values in left subtree < node
 * - All values in right subtree > node
 * 
 * Therefore, the MAXIMUM value is always at the RIGHTMOST node!
 * 
 * INTUITION:
 * ----------
 * Since larger values are always stored in the right subtree:
 * 1. Start at root
 * 2. Keep going RIGHT until we can't go anymore
 * 3. The last node we reach is the maximum
 * 
 * Similarly, MINIMUM would be found by going LEFT until we can't.
 * 
 * VISUAL REPRESENTATION:
 * ----------------------
 *         2
 *        / \
 *       1   5
 *          / \
 *         4   6  ← Maximum is here (rightmost node)
 * 
 * Path to maximum: 2 → 5 → 6
 * 
 * Another example (skewed tree):
 *     1
 *      \
 *       2
 *        \
 *         3
 *          \
 *           4  ← Maximum
 * 
 * TIME COMPLEXITY:
 * ----------------
 * O(h) where h = height of tree
 * - Best case (balanced): O(log n)
 * - Worst case (right-skewed): O(n)
 * 
 * SPACE COMPLEXITY:
 * -----------------
 * O(1) - only using a single pointer, no extra space
 * 
 * DRY RUN:
 * --------
 * Tree:
 *         2
 *        / \
 *       1   5
 *          / \
 *         4   6
 * 
 * | Step | Current Node | Right Child | Action    |
 * |------|--------------|-------------|-----------|
 * | 1    | 2            | 5           | Go right  |
 * | 2    | 5            | 6           | Go right  |
 * | 3    | 6            | null        | Stop!     |
 * 
 * Maximum = 6 ✓
 * 
 * EDGE CASES:
 * -----------
 * 1. Empty tree (root = null) → Return 0 (or throw exception)
 * 2. Single node tree → Return root value
 * 3. No right children → Root is maximum
 * 4. All right children → Last right child is maximum
 */
public class FindMaxInBST {
    
    public static void main(String[] args) {
        // Build test BST
        Integer[] val = {2, 1, 5, null, null, 4, 6};
        ArrayList<Integer> values = new ArrayList<>();
        Arrays.stream(val).forEach(values::add);
        BinaryTreeNode root = BSTInsert.build_a_bst(values);

        // Find maximum
        Integer max = get_maximum_value(root);
        System.out.println("Maximum value in BST: " + max);
    }
    
    /**
     * FIND MAXIMUM VALUE IN BST
     * 
     * Algorithm:
     * 1. Start at root
     * 2. Keep traversing right child until we reach a node with no right child
     * 3. Return that node's value
     * 
     * Why this works:
     * - In BST, right subtree always contains larger values
     * - So the rightmost node has the largest value
     * 
     * Time Complexity: O(h) - traverse from root to rightmost node
     * Space Complexity: O(1) - no extra space needed
     * 
     * @param root Root of the BST
     * @return Maximum value in the BST, or 0 if tree is empty
     */
    static Integer get_maximum_value(BinaryTreeNode root) {
        // Edge case: empty tree
        // Could also throw an exception or return null
        if (root == null) {
            return 0;
        }
        
        // Keep going right until we can't go anymore
        // The rightmost node contains the maximum value
        while (root.right != null) {
            root = root.right;  // Move to right child
        }
        
        // We've reached the rightmost node
        return root.value;
    }
    
    /**
     * FIND MINIMUM VALUE IN BST (Bonus - opposite of max)
     * 
     * Algorithm: Keep going LEFT until no more left children
     * 
     * @param root Root of the BST
     * @return Minimum value in the BST, or 0 if tree is empty
     */
    static Integer get_minimum_value(BinaryTreeNode root) {
        // Edge case: empty tree
        if (root == null) {
            return 0;
        }
        
        // Keep going left until we can't go anymore
        // The leftmost node contains the minimum value
        while (root.left != null) {
            root = root.left;  // Move to left child
        }
        
        // We've reached the leftmost node
        return root.value;
    }
}
