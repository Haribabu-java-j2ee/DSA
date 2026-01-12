package trees.practice;

/**
 * ============================================================================
 * UNIVAL (UNI-VALUE) SUBTREES - Count subtrees with all same value
 * ============================================================================
 * 
 * PROBLEM STATEMENT:
 * ------------------
 * A unival tree is a tree where all nodes have the same value.
 * Given a binary tree, count the number of unival subtrees.
 * 
 * DEFINITION:
 * -----------
 * A subtree is unival if:
 * - It's a single node (always unival), OR
 * - All nodes in the subtree have the same value
 * 
 * TWO APPROACHES:
 * ---------------
 * 
 * APPROACH 1: Top-down (O(n²) worst case)
 * - For each node, check if its subtree is unival
 * - Checking requires traversing entire subtree
 * - In worst case (all same values): n + (n-1) + (n-2) + ... = O(n²)
 * 
 * APPROACH 2: Bottom-up (O(n) - Optimal)
 * - Postorder traversal
 * - Each node checks its children and decides in O(1)
 * - Total: O(n)
 * 
 * VISUAL REPRESENTATION:
 * ----------------------
 * Tree:
 *         5
 *        / \
 *       1   5
 *      / \   \
 *     5   5   5
 * 
 * Unival subtrees:
 * 1. Node 5 (left-left leaf) ✓
 * 2. Node 5 (left-right leaf) ✓
 * 3. Node 5 (right-right leaf) ✓
 * 4. Node 5 with child 5 (right subtree) ✓
 * 
 * Not unival:
 * - Node 1 with children 5, 5 (1 ≠ 5)
 * - Root 5 (left child is 1, not 5)
 * 
 * Count = 4
 * 
 * TIME COMPLEXITY:
 * ----------------
 * Approach 1: O(n²) worst case
 * Approach 2: O(n)
 * 
 * SPACE COMPLEXITY:
 * -----------------
 * O(h) for recursion stack
 * 
 * DRY RUN (Approach 2):
 * ---------------------
 *         5
 *        / \
 *       1   5
 *      / \   \
 *     5   5   5
 * 
 * Postorder processing:
 * | Node | Left Unival? | Right Unival? | Is Unival? | Count |
 * |------|--------------|---------------|------------|-------|
 * | 5(LL)| null(yes)    | null(yes)     | YES        | 1     |
 * | 5(LR)| null(yes)    | null(yes)     | YES        | 2     |
 * | 1    | 5≠1, NO      | 5≠1, NO       | NO         | 2     |
 * | 5(RR)| null(yes)    | null(yes)     | YES        | 3     |
 * | 5(R) | null(yes)    | 5=5, YES      | YES        | 4     |
 * | 5(root)| NO         | YES           | NO         | 4     |
 * 
 * Result: 4 ✓
 */
public class UnivalSubtree {
    
    public static void main(String[] args) {
        // Build tree
        BinaryTreeNode root = new BinaryTreeNode(5);
        root.left = new BinaryTreeNode(1);
        root.right = new BinaryTreeNode(5);
        root.left.left = new BinaryTreeNode(5);
        root.left.right = new BinaryTreeNode(5);
        root.right.right = new BinaryTreeNode(5);
        
        System.out.println("Unival count (O(n²)): " + findUnivalSubtree(root));
        System.out.println("Unival count (O(n)): " + find_single_value_trees(root));
    }

    // ═══════════════════════════════════════════════════════════════════════
    // APPROACH 1: Top-down - O(n²) worst case
    // ═══════════════════════════════════════════════════════════════════════
    
    /**
     * Count unival subtrees using top-down approach.
     * 
     * Algorithm:
     * 1. Check if current subtree is unival
     * 2. Recursively count in left and right subtrees
     * 3. Sum up the counts
     * 
     * Why O(n²)?
     * - For each of n nodes, we check entire subtree
     * - Checking subtree takes O(subtree size)
     * - Worst case: O(n + (n-1) + (n-2) + ...) = O(n²)
     * 
     * @param root Root of tree
     * @return Number of unival subtrees
     */
    static int findUnivalSubtree(BinaryTreeNode root) {
        if (root == null) {
            return 0;
        }
        
        // Count = (1 if this subtree is unival) + count in left + count in right
        return findUnivalSubtreeUtil(root, root.value) + 
               findUnivalSubtree(root.left) + 
               findUnivalSubtree(root.right);
    }

    /**
     * Check if subtree rooted at 'root' is unival with given value.
     * 
     * @param root Current node
     * @param value Expected value
     * @return 1 if unival, 0 otherwise
     */
    private static int findUnivalSubtreeUtil(BinaryTreeNode root, Integer value) {
        // Base case: null is considered unival
        if (root == null) {
            return 1;
        }
        
        // If current node doesn't match expected value
        if (!root.value.equals(value)) {
            return 0;
        }
        
        // Check if left subtree is unival with same value
        if (findUnivalSubtreeUtil(root.left, value) == 0) {
            return 0;
        }
        
        // Check if right subtree is unival with same value
        if (findUnivalSubtreeUtil(root.right, value) == 0) {
            return 0;
        }
        
        // Both subtrees are unival with same value
        return 1;
    }

    // ═══════════════════════════════════════════════════════════════════════
    // APPROACH 2: Bottom-up - O(n) - Optimal
    // ═══════════════════════════════════════════════════════════════════════
    
    // Global counter
    static int count;

    /**
     * Count unival subtrees using bottom-up approach (optimal).
     * 
     * Uses postorder traversal to process children before parent.
     * Each node is processed exactly once → O(n).
     * 
     * @param root Root of tree
     * @return Number of unival subtrees
     */
    static int find_single_value_trees(BinaryTreeNode root) {
        count = 0;  // Reset counter
        recursiveHelper(root);
        return count;
    }

    /**
     * Recursive helper using postorder traversal.
     * 
     * Algorithm:
     * 1. Process left subtree, get if it's unival
     * 2. Process right subtree, get if it's unival
     * 3. Current is unival if:
     *    - Left is unival (or null) AND left value matches (or null)
     *    - Right is unival (or null) AND right value matches (or null)
     * 4. If current is unival, increment count
     * 5. Return whether current subtree is unival
     * 
     * @param root Current node
     * @return true if subtree rooted at this node is unival
     */
    private static boolean recursiveHelper(BinaryTreeNode root) {
        // Base case: null is considered unival
        if (root == null) {
            return true;
        }
        
        // Step 1: Recursively process left subtree
        boolean is_left_unival = recursiveHelper(root.left);
        
        // Step 2: Recursively process right subtree
        boolean is_right_unival = recursiveHelper(root.right);
        
        // Step 3: Check if current node forms unival subtree
        // Conditions:
        // - Left subtree is unival (or doesn't exist)
        // - Left value matches current (or doesn't exist)
        // - Right subtree is unival (or doesn't exist)
        // - Right value matches current (or doesn't exist)
        boolean leftOK = (root.left == null) || 
                         (is_left_unival && root.left.value.equals(root.value));
        boolean rightOK = (root.right == null) || 
                          (is_right_unival && root.right.value.equals(root.value));
        
        if (leftOK && rightOK) {
            count++;    // This is a unival subtree!
            return true;
        } else {
            return false;
        }
    }
}
