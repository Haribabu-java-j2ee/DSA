package trees;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * ============================================================================
 * BST INSERT - Build a Binary Search Tree by inserting values
 * ============================================================================
 * 
 * PROBLEM STATEMENT:
 * ------------------
 * Given a list of values, build a BST by inserting each value one by one.
 * 
 * BST PROPERTY:
 * -------------
 * For every node:
 * - All values in LEFT subtree < node value
 * - All values in RIGHT subtree > node value
 * 
 * INTUITION:
 * ----------
 * To insert a value into a BST:
 * 1. Start at the root
 * 2. If value < current node, go LEFT
 * 3. If value > current node, go RIGHT
 * 4. When we reach a null position, insert the new node there
 * 
 * VISUAL REPRESENTATION:
 * ----------------------
 * Insert sequence: [5, 3, 7, 2, 4, 6, 8]
 * 
 * Step 1: Insert 5 (root)
 *     5
 * 
 * Step 2: Insert 3 (3 < 5, go left)
 *     5
 *    /
 *   3
 * 
 * Step 3: Insert 7 (7 > 5, go right)
 *     5
 *    / \
 *   3   7
 * 
 * Step 4: Insert 2 (2 < 5, 2 < 3, go left-left)
 *       5
 *      / \
 *     3   7
 *    /
 *   2
 * 
 * Step 5: Insert 4 (4 < 5, 4 > 3, go left-right)
 *       5
 *      / \
 *     3   7
 *    / \
 *   2   4
 * 
 * Final Tree after inserting 6, 8:
 *         5
 *        / \
 *       3   7
 *      / \ / \
 *     2  4 6  8
 * 
 * TIME COMPLEXITY:
 * ----------------
 * - Per insertion: O(h) where h = height of tree
 * - For n insertions: O(n * h)
 * - Best case (balanced): O(n * log n)
 * - Worst case (sorted input): O(n²) - creates a skewed tree
 * 
 * SPACE COMPLEXITY:
 * -----------------
 * - Iterative: O(1) auxiliary space
 * - Recursive: O(h) for recursion stack
 * - Total: O(n) for storing n nodes
 * 
 * DRY RUN (Iterative):
 * --------------------
 * Insert [1, 2, 3, 4, 5]:
 * 
 * Insert 1: root = 1
 * Insert 2: 2 > 1, attach as right child
 *     1
 *      \
 *       2
 * 
 * Insert 3: 3 > 1 -> 3 > 2, attach as right child of 2
 *     1
 *      \
 *       2
 *        \
 *         3
 * 
 * Insert 4: 4 > 1 -> 4 > 2 -> 4 > 3, attach as right child of 3
 *     1
 *      \
 *       2
 *        \
 *         3
 *          \
 *           4
 * 
 * This creates a RIGHT-SKEWED tree (worst case)!
 */
public class BSTInsert {
    
    public static void main(String[] args) {
        int[] val = {1, 2, 3, 4, 5};  // Sorted input - creates skewed tree
        ArrayList<Integer> values = new ArrayList<>();
        Arrays.stream(val).forEach(v -> values.add(v));
        BinaryTreeNode tree = build_a_bst1(values);
        System.out.println(tree);
    }

    /**
     * BUILD BST - ITERATIVE APPROACH
     * 
     * Time Complexity: O(n * h) where n = number of values, h = tree height
     * Space Complexity: O(1) auxiliary space (excluding the tree itself)
     * 
     * @param values List of values to insert
     * @return Root of the constructed BST
     */
    static BinaryTreeNode build_a_bst(ArrayList<Integer> values) {
        BinaryTreeNode root = null;
        
        // Insert each value one by one
        for (Integer value : values) {
            root = buildBST(root, value);
        }
        return root;
    }

    /**
     * ITERATIVE INSERT HELPER
     * 
     * ALGORITHM:
     * 1. If tree is empty, create root with given value
     * 2. Otherwise, traverse down the tree:
     *    - Go left if value < current node
     *    - Go right if value > current node
     * 3. When we fall off the tree (current = null), insert at that position
     * 
     * @param root Current root of BST
     * @param value Value to insert
     * @return Root of BST (unchanged, unless tree was empty)
     */
    static BinaryTreeNode buildBST(BinaryTreeNode root, Integer value) {
        // Case 1: Empty tree - create root node
        if (root == null) {
            return new BinaryTreeNode(value);
        }
        
        BinaryTreeNode parent = root;   // Track parent for attachment
        BinaryTreeNode current = root;  // Current node being examined
        
        // Traverse to find insertion point
        while (current != null) {
            parent = current;  // Remember parent before moving down
            
            // Handle null values (edge case for helper function usage)
            if (value == null) {
                return root;
            }
            
            // BST navigation: go left or right based on value comparison
            if (value < current.value) {
                current = current.left;   // Value is smaller, go left
            } else if (value > current.value) {
                current = current.right;  // Value is larger, go right
            }
            // Note: If value == current.value, we could skip (no duplicates)
        }
        
        // Attach new node to the parent at appropriate position
        if (value < parent.value) {
            parent.left = new BinaryTreeNode(value);   // Attach as left child
        } else {
            parent.right = new BinaryTreeNode(value);  // Attach as right child
        }
        
        return root;  // Root remains unchanged
    }

    /**
     * BUILD BST - RECURSIVE APPROACH
     * 
     * Time Complexity: O(n * h) where n = number of values, h = tree height
     * Space Complexity: O(h) for recursion stack
     * 
     * @param values List of values to insert
     * @return Root of the constructed BST
     */
    static BinaryTreeNode build_a_bst1(ArrayList<Integer> values) {
        BinaryTreeNode root = null;
        
        // Insert each value using recursive helper
        for (int value : values) {
            root = buildBSTRecur(root, value);
        }
        return root;
    }

    /**
     * RECURSIVE INSERT HELPER
     * 
     * ALGORITHM:
     * 1. Base case: If current node is null, create and return new node
     * 2. If value < root, recursively insert in left subtree
     * 3. If value > root, recursively insert in right subtree
     * 4. Return the root (with updated subtree references)
     * 
     * ADVANTAGE over iterative:
     * - Cleaner code
     * - Easier to understand
     * - Natural tree traversal
     * 
     * DISADVANTAGE:
     * - Uses O(h) stack space
     * - Can cause stack overflow for very deep trees
     * 
     * @param root Current subtree root
     * @param value Value to insert
     * @return Root of modified subtree
     */
    private static BinaryTreeNode buildBSTRecur(BinaryTreeNode root, int value) {
        // Base case: Found insertion point
        if (root == null) {
            return new BinaryTreeNode(value);
        }
        
        // Recursive case: Navigate to correct subtree
        if (value < root.value) {
            // Insert in left subtree
            // Update left child with result of recursive insertion
            root.left = buildBSTRecur(root.left, value);
        } else if (value > root.value) {
            // Insert in right subtree
            // Update right child with result of recursive insertion
            root.right = buildBSTRecur(root.right, value);
        }
        // Note: If value == root.value, we do nothing (no duplicates)
        
        return root;  // Return unchanged or modified root
    }
}
