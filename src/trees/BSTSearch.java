package trees;

import java.util.Stack;

/**
 * ============================================================================
 * BST SEARCH - Search for a value in Binary Search Tree
 * ============================================================================
 * 
 * PROBLEM STATEMENT:
 * ------------------
 * Given a BST and a target value, determine if the value exists in the tree.
 * 
 * BST PROPERTY LEVERAGED:
 * -----------------------
 * - All values in left subtree < current node
 * - All values in right subtree > current node
 * 
 * This property allows us to eliminate half the tree at each step!
 * 
 * INTUITION:
 * ----------
 * BST search is like binary search on a linked structure:
 * 1. Compare target with current node
 * 2. If equal, found!
 * 3. If target < current, search LEFT subtree (smaller values)
 * 4. If target > current, search RIGHT subtree (larger values)
 * 5. If we reach null, target doesn't exist
 * 
 * VISUAL REPRESENTATION:
 * ----------------------
 * Search for 4 in this BST:
 * 
 *         2
 *        / \
 *       1   5
 *          / \
 *         4   6
 * 
 * Step 1: Compare 4 with root (2)
 *         4 > 2 → Go RIGHT
 * 
 * Step 2: Compare 4 with node 5
 *         4 < 5 → Go LEFT
 * 
 * Step 3: Compare 4 with node 4
 *         4 == 4 → FOUND!
 * 
 * TIME COMPLEXITY:
 * ----------------
 * - Average case: O(log n) for balanced BST
 * - Worst case: O(n) for skewed tree (like a linked list)
 * - Best case: O(1) if target is at root
 * 
 * SPACE COMPLEXITY:
 * -----------------
 * - Iterative: O(1) - no extra space needed
 * - Recursive: O(h) for recursion stack, h = tree height
 * 
 * DRY RUN:
 * --------
 * Tree:    2
 *         / \
 *        1   5
 *           / \
 *          4   6
 * 
 * Search for 4:
 * | Step | Current Node | Comparison | Action    |
 * |------|--------------|------------|-----------|
 * | 1    | 2            | 4 > 2      | Go right  |
 * | 2    | 5            | 4 < 5      | Go left   |
 * | 3    | 4            | 4 == 4     | FOUND!    |
 * 
 * Search for 3 (not in tree):
 * | Step | Current Node | Comparison | Action    |
 * |------|--------------|------------|-----------|
 * | 1    | 2            | 3 > 2      | Go right  |
 * | 2    | 5            | 3 < 5      | Go left   |
 * | 3    | 4            | 3 < 4      | Go left   |
 * | 4    | null         | -          | NOT FOUND |
 * 
 * IMPORTANT NOTE ON INTEGER vs int:
 * ----------------------------------
 * Using Integer wrapper class with == comparison can cause issues:
 * - Integer caches values from -128 to 127
 * - Outside this range, == compares references, not values
 * - Solution: Use int primitive or .equals() for Integer
 * 
 * Example of the bug:
 * Integer a = 128;
 * Integer b = 128;
 * a == b; // FALSE! (different object references)
 * a.equals(b); // TRUE (compares values)
 */
public class BSTSearch {
    
    public static void main(String[] args) {
        // Build test tree
        BinaryTreeNode root = new BinaryTreeNode(2);
        root.left = new BinaryTreeNode(1);
        root.right = new BinaryTreeNode(5);
        root.right.left = new BinaryTreeNode(4);
        root.right.right = new BinaryTreeNode(6);
        
        // Test search
        System.out.println("Search for 4: " + search_node_in_bst(root, 4));  // true
        System.out.println("Search for 3: " + search_node_in_bst(root, 3));  // false
    }

    /**
     * ITERATIVE BST SEARCH (using Integer wrapper)
     * 
     * Time Complexity: O(h) where h = tree height
     * Space Complexity: O(1)
     * 
     * @param root Root of BST
     * @param value Value to search for
     * @return true if found, false otherwise
     */
    static Boolean search_node_in_bst(BinaryTreeNode root, Integer value) {
        // Edge case: empty tree
        if (root == null) {
            return false;
        }
        
        // Traverse down the tree
        while (root != null) {
            // Check if current node matches target
            if (root.value.equals(value)) {  // Use .equals() for Integer comparison!
                return true;  // Found!
            } else if (root.value < value) {
                // Target is larger, search in right subtree
                root = root.right;
            } else if (root.value > value) {
                // Target is smaller, search in left subtree
                root = root.left;
            }
        }
        
        // Reached null without finding target
        return false;
    }

    /**
     * SEARCH AND RETURN NODE (Helper for BSTSuccessor)
     * 
     * Returns the actual node instead of just boolean.
     * Useful when we need to work with the found node.
     * 
     * Time Complexity: O(h)
     * Space Complexity: O(1)
     * 
     * @param root Root of BST
     * @param value Value to search for
     * @return Node containing value, or null if not found
     */
    static BinaryTreeNode search_node(BinaryTreeNode root, Integer value) {
        // Edge case: empty tree
        if (root == null) {
            return null;
        }
        
        BinaryTreeNode current = root;
        
        // Traverse down the tree
        while (current != null) {
            if (current.value.equals(value)) {
                return current;  // Return the actual node
            } else if (current.value < value) {
                current = current.right;  // Go right for larger values
            } else if (current.value > value) {
                current = current.left;   // Go left for smaller values
            }
        }
        
        return null;  // Not found
    }

    /**
     * OPTIMIZED ITERATIVE SEARCH (using int primitive)
     * 
     * This version:
     * 1. Uses primitive int to avoid Integer comparison issues
     * 2. Uses ternary operator for cleaner navigation
     * 3. Combines loop conditions for efficiency
     * 
     * Time Complexity: O(h)
     * Space Complexity: O(1)
     * 
     * @param root Root of BST
     * @param value Value to search for
     * @return true if found, false otherwise
     */
    static boolean search_node_in_bst(BinaryTreeNode root, int value) {
        // Combined condition: traverse while node exists AND value not found
        while (root != null && root.value != value) {
            // Ternary operator for direction: go left if value is smaller, else right
            root = value < root.value ? root.left : root.right;
        }
        
        // If root is null, we didn't find it; otherwise we found it
        return root != null;
    }
}

/**
 * BINARY TREE NODE CLASS
 * 
 * Basic node structure for binary tree:
 * - value: The data stored in the node
 * - left: Reference to left child
 * - right: Reference to right child
 */
class BinaryTreeNode {
    Integer value;           // Node value (Integer for null support)
    BinaryTreeNode left;     // Left child
    BinaryTreeNode right;    // Right child

    /**
     * Constructor - creates a new node with given value
     * Children are initialized to null (leaf node)
     */
    BinaryTreeNode(Integer value) {
        this.value = value;
        this.left = null;
        this.right = null;
    }
}
