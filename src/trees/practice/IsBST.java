package trees.practice;

/**
 * ============================================================================
 * IS BST - Validate if a Binary Tree is a Binary Search Tree
 * ============================================================================
 * 
 * PROBLEM STATEMENT:
 * ------------------
 * Given a binary tree, determine if it is a valid Binary Search Tree (BST).
 * 
 * BST DEFINITION:
 * ---------------
 * For EVERY node in a BST:
 * - ALL nodes in its LEFT subtree have values LESS than the node
 * - ALL nodes in its RIGHT subtree have values GREATER than the node
 * 
 * COMMON MISTAKE:
 * ---------------
 * Just checking if left < root < right is NOT enough!
 * 
 * Example of INVALID BST that passes simple check:
 *       10
 *      /  \
 *     5   15
 *        /  \
 *       6   20     ← 6 is less than 10, but it's in right subtree!
 * 
 * Simple check: 15 > 10 ✓, 6 < 15 ✓
 * But 6 < 10, and 6 is in right subtree of 10 → INVALID!
 * 
 * INTUITION:
 * ----------
 * Every node has a VALID RANGE:
 * - Root can be anything: [-∞, +∞]
 * - Left child: [parent's min, parent's value]
 * - Right child: [parent's value, parent's max]
 * 
 * We pass down bounds and check if each node is within its valid range.
 * 
 * ALTERNATIVE APPROACHES:
 * -----------------------
 * 1. Inorder traversal should give sorted sequence
 *    - Do inorder traversal
 *    - Check if each element > previous
 * 
 * 2. Range checking (used here) - more space efficient
 *    - Pass valid range [min, max] down the tree
 *    - Each node must be within its range
 * 
 * VISUAL REPRESENTATION:
 * ----------------------
 * Valid BST:
 *       200
 *      /   \
 *    100   300
 * 
 * Check 200: [-∞, +∞] → 200 ✓
 * Check 100: [-∞, 200] → 100 ✓
 * Check 300: [200, +∞] → 300 ✓
 * 
 * Invalid BST:
 *       100
 *      /   \
 *    200   300   ← 200 > 100 but in left subtree!
 * 
 * Check 100: [-∞, +∞] → 100 ✓
 * Check 200: [-∞, 100] → 200 ✗ (200 > 100)
 * 
 * TIME COMPLEXITY:
 * ----------------
 * O(n) - visit each node exactly once
 * 
 * SPACE COMPLEXITY:
 * -----------------
 * O(h) for recursion stack, where h = height
 * - Best case (balanced): O(log n)
 * - Worst case (skewed): O(n)
 * 
 * DRY RUN:
 * --------
 * Tree:
 *       200
 *      /   \
 *    100   300
 * 
 * | Node | Min   | Max   | Check           | Result |
 * |------|-------|-------|-----------------|--------|
 * | 200  | -∞    | +∞    | -∞ < 200 < +∞   | ✓      |
 * | 100  | -∞    | 200   | -∞ < 100 < 200  | ✓      |
 * | null | -     | -     | Base case       | true   |
 * | null | -     | -     | Base case       | true   |
 * | 300  | 200   | +∞    | 200 < 300 < +∞  | ✓      |
 * | null | -     | -     | Base case       | true   |
 * | null | -     | -     | Base case       | true   |
 * 
 * Final: true ✓
 */
public class IsBST {
    
    public static void main(String[] args) {
        // Test case 1: Invalid BST
        BinaryTreeNode root = new BinaryTreeNode(100);
        root.left = new BinaryTreeNode(200);   // Invalid! 200 > 100 in left subtree
        root.right = new BinaryTreeNode(300);
        System.out.println("Tree 1 is BST: " + is_bst(root));  // Expected: false
        
        // Test case 2: Valid BST
        root = new BinaryTreeNode(200);
        root.left = new BinaryTreeNode(100);
        root.right = new BinaryTreeNode(300);
        System.out.println("Tree 2 is BST: " + is_bst(root));  // Expected: true
    }

    /**
     * MAIN FUNCTION - Check if tree is valid BST
     * 
     * Initiates recursive check with full range [-∞, +∞]
     * 
     * Time Complexity: O(n)
     * Space Complexity: O(h) for recursion
     * 
     * @param root Root of tree to validate
     * @return true if valid BST, false otherwise
     */
    static Boolean is_bst(BinaryTreeNode root) {
        // Empty tree is a valid BST
        if (root == null) {
            return true;
        }
        
        // Start with full range
        return is_bst_util(root, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

    /**
     * RECURSIVE HELPER - Validate with range constraints
     * 
     * Each node must satisfy: min < node.value < max
     * As we go left, max becomes current node's value
     * As we go right, min becomes current node's value
     * 
     * @param root Current node to validate
     * @param min Minimum allowed value (exclusive)
     * @param max Maximum allowed value (exclusive)
     * @return true if subtree is valid BST
     */
    static Boolean is_bst_util(BinaryTreeNode root, int min, int max) {
        // Base case: null node is valid
        if (root == null) {
            return true;
        }
        
        // Check if current node violates the BST property
        // Node value must be strictly within (min, max)
        if (root.value < min || root.value > max) {
            return false;  // Violation! Node is outside valid range
        }

        // Recursively validate left and right subtrees with updated bounds
        // Left subtree: all values must be < current node
        //   → new range is [min, current value]
        // Right subtree: all values must be > current node
        //   → new range is [current value, max]
        
        boolean leftValid = is_bst_util(root.left, min, root.value);
        boolean rightValid = is_bst_util(root.right, root.value, max);
        
        // Tree is valid only if BOTH subtrees are valid
        return leftValid && rightValid;
    }
}
