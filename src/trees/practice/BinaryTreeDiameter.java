package trees.practice;

/**
 * ============================================================================
 * BINARY TREE DIAMETER - Find the longest path between any two nodes
 * ============================================================================
 * 
 * PROBLEM STATEMENT:
 * ------------------
 * The diameter of a binary tree is the LENGTH of the longest path between 
 * any two nodes in the tree. This path may or may not pass through the root.
 * 
 * The LENGTH is measured by the number of EDGES between them.
 * 
 * INTUITION:
 * ----------
 * For any node, the longest path THROUGH that node is:
 *     leftHeight + rightHeight
 * 
 * The diameter is the maximum of such paths across ALL nodes.
 * 
 * KEY INSIGHT:
 * - The longest path through a node = height of left subtree + height of right subtree
 * - We need to find max of this value across all nodes
 * - We can compute this bottom-up while calculating heights
 * 
 * WHY NOT JUST ROOT?
 * ------------------
 * The diameter might not pass through root!
 * 
 *         1
 *        /
 *       2
 *      / \
 *     3   4
 *    /     \
 *   5       6
 * 
 * Diameter here is 5 → 3 → 2 → 4 → 6 (length = 4)
 * It doesn't go through root (1)!
 * 
 * VISUAL REPRESENTATION:
 * ----------------------
 *         0
 *        / \
 *       1   2
 *      / \
 *     3   4
 * 
 * For node 1: leftHeight = 1 (path to 3), rightHeight = 1 (path to 4)
 *             Diameter through 1 = 1 + 1 = 2
 * 
 * For node 0: leftHeight = 2, rightHeight = 1
 *             Diameter through 0 = 2 + 1 = 3
 * 
 * Maximum diameter = 3 (path: 3 → 1 → 0 → 2 or 4 → 1 → 0 → 2)
 * 
 * TIME COMPLEXITY:
 * ----------------
 * O(n) - we visit each node exactly once
 * 
 * SPACE COMPLEXITY:
 * -----------------
 * O(h) for recursion stack, where h = height
 * - Best case (balanced): O(log n)
 * - Worst case (skewed): O(n)
 * 
 * DRY RUN:
 * --------
 *         0
 *        / \
 *       1   2
 *      / \
 *     3   4
 * 
 * | Node | Left Height | Right Height | Diameter Through | Max So Far | Return Height |
 * |------|-------------|--------------|------------------|------------|---------------|
 * | 3    | 0           | 0            | 0                | 0          | 1             |
 * | 4    | 0           | 0            | 0                | 0          | 1             |
 * | 1    | 1           | 1            | 2                | 2          | 2             |
 * | 2    | 0           | 0            | 0                | 2          | 1             |
 * | 0    | 2           | 1            | 3                | 3          | 3             |
 * 
 * Final diameter = 3 ✓
 */
public class BinaryTreeDiameter {
    
    public static void main(String[] args) {
        // Build test tree
        BinaryTreeNode root = new BinaryTreeNode(0);
        root.left = new BinaryTreeNode(1);
        root.right = new BinaryTreeNode(2);
        root.left.left = new BinaryTreeNode(3);
        root.left.right = new BinaryTreeNode(4);
        
        System.out.println("Diameter: " + binary_tree_diameter(root));  // Expected: 3
    }

    // Global variable to track maximum diameter found
    static int result;
    
    /**
     * MAIN FUNCTION - Find diameter of binary tree
     * 
     * Time Complexity: O(n)
     * Space Complexity: O(h) for recursion stack
     * 
     * @param root Root of the binary tree
     * @return Diameter (number of edges in longest path)
     */
    static Integer binary_tree_diameter(BinaryTreeNode root) {
        // Edge case: empty tree has diameter 0
        if (root == null) {
            return 0;
        }

        // Reset result for fresh calculation
        result = 0;
        
        // Compute heights and update diameter
        binary_tree_diameter_util(root);
        
        return result;
    }

    /**
     * HELPER FUNCTION - Calculates height and updates diameter
     * 
     * Algorithm:
     * 1. Recursively get left subtree height
     * 2. Recursively get right subtree height
     * 3. Update max diameter (left + right through current node)
     * 4. Return height of current subtree (1 + max of children)
     * 
     * KEY INSIGHT:
     * - We compute height bottom-up
     * - At each node, we calculate potential diameter through that node
     * - We update global max if current path is longer
     * 
     * @param root Current node
     * @return Height of subtree rooted at this node
     */
    static Integer binary_tree_diameter_util(BinaryTreeNode root) {
        // Base case: null node has height 0
        if (root == null) {
            return 0;
        }

        // Step 1: Recursively calculate left subtree height
        int leftHeight = binary_tree_diameter_util(root.left);
        
        // Step 2: Recursively calculate right subtree height
        int rightHeight = binary_tree_diameter_util(root.right);

        // Step 3: Update diameter if path through current node is longest
        // Diameter through this node = leftHeight + rightHeight
        result = Math.max(result, leftHeight + rightHeight);

        // Step 4: Return height of current subtree
        // Height = 1 (current node) + max height of children
        return 1 + Math.max(leftHeight, rightHeight);
    }
}
