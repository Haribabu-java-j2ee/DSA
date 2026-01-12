package trees.practice;

/**
 * ============================================================================
 * FLIP BINARY TREE UPSIDE DOWN
 * ============================================================================
 * 
 * PROBLEM STATEMENT:
 * ------------------
 * Given a binary tree where all right nodes are either leaf nodes or empty,
 * flip it upside down and turn it into a tree where the original right nodes 
 * turned into left leaf nodes.
 * 
 * CONSTRAINT:
 * - All right nodes are either leaf or null
 * - Essentially a left-leaning tree
 * 
 * TRANSFORMATION RULES:
 * ---------------------
 * 1. The leftmost node becomes the new root
 * 2. For each node: 
 *    - parent's RIGHT child becomes new LEFT child
 *    - parent itself becomes new RIGHT child
 * 
 * VISUAL REPRESENTATION:
 * ----------------------
 * Original:           After Flip:
 *      1                   4
 *     / \                 / \
 *    2   3               5   2
 *   / \                     / \
 *  4   5                   3   1
 * 
 * Transformation for node 2:
 * - 2's left (4) becomes new root of subtree
 * - 2's right (5) becomes 4's left child
 * - 2 becomes 4's right child
 * 
 * Then for node 1:
 * - 1's left was 2 (already processed)
 * - 1's right (3) becomes 2's left child
 * - 1 becomes 2's right child
 * 
 * INTUITION:
 * ----------
 * RECURSIVE APPROACH:
 * 1. Recurse to the leftmost node (this becomes new root)
 * 2. On the way back up:
 *    - Current node's left child's left = current's right
 *    - Current node's left child's right = current
 *    - Set current's children to null (it's now a leaf or internal)
 * 
 * ITERATIVE APPROACH:
 * 1. Traverse down the left edge
 * 2. At each step, flip the pointers
 * 3. Track what needs to be assigned next
 * 
 * TIME COMPLEXITY:
 * ----------------
 * O(h) where h = height of tree
 * Since tree is left-leaning, h = number of nodes on left path
 * 
 * SPACE COMPLEXITY:
 * -----------------
 * Recursive: O(h) for call stack
 * Iterative: O(1) - constant extra space
 * 
 * DRY RUN (Recursive):
 * --------------------
 * Original:
 *      1
 *     / \
 *    2   3
 *   / \
 *  4   5
 * 
 * | Step | Node | Action                                   | Tree State       |
 * |------|------|------------------------------------------|------------------|
 * | 1    | 1    | Call flip(1.left) = flip(2)              | -                |
 * | 2    | 2    | Call flip(2.left) = flip(4)              | -                |
 * | 3    | 4    | 4 is leftmost, return 4                  | Return 4         |
 * | 4    | 2    | 2.left.left = 2.right → 4.left = 5       | 4.left = 5       |
 * |      |      | 2.left.right = 2 → 4.right = 2           | 4.right = 2      |
 * |      |      | 2.left = null, 2.right = null            | 2 is leaf now    |
 * |      |      | Return 4                                  |                  |
 * | 5    | 1    | 1.left.left = 1.right → 2.left = 3       | 2.left = 3       |
 * |      |      | 1.left.right = 1 → 2.right = 1           | 2.right = 1      |
 * |      |      | 1.left = null, 1.right = null            | 1 is leaf now    |
 * 
 * Final:
 *      4
 *     / \
 *    5   2
 *       / \
 *      3   1
 */
public class FlipTreeUpsideDown {
    
    public static void main(String[] args) {
        // Build original tree
        BinaryTreeNode root = new BinaryTreeNode(1);
        root.left = new BinaryTreeNode(2);
        root.right = new BinaryTreeNode(3);
        root.left.left = new BinaryTreeNode(4);
        root.left.right = new BinaryTreeNode(5);
        
        // Test iterative approach
        BinaryTreeNode flipped = flipUpsideDown1(root);
        System.out.println("New root after flip: " + flipped.value);  // Should be 4
    }

    // ═══════════════════════════════════════════════════════════════════════
    // APPROACH 1: Recursive (Postorder-like)
    // Time: O(h), Space: O(h)
    // ═══════════════════════════════════════════════════════════════════════
    
    /**
     * Flip tree upside down using recursion.
     * 
     * Algorithm:
     * 1. Recurse to leftmost node (new root)
     * 2. On way back:
     *    - Attach parent's right as left child of parent's left
     *    - Attach parent as right child of parent's left
     *    - Clear parent's children
     * 
     * @param root Current node
     * @return New root of flipped subtree
     */
    static BinaryTreeNode flipUpsideDown(BinaryTreeNode root) {
        // Base case: empty tree
        if (root == null) {
            return null;
        }
        
        // Base case: leaf node (leftmost node is new root)
        if (root.left == null && root.right == null) {
            return root;
        }
        
        // Step 1: Recurse to get new root (leftmost node)
        BinaryTreeNode newRoot = flipUpsideDown(root.left);
        
        // Step 2: On the way back, flip the pointers
        // root.left's new left child = root's right sibling
        root.left.left = root.right;
        // root.left's new right child = root itself
        root.left.right = root;

        // Step 3: Clear root's children (root becomes a leaf or internal node)
        root.left = null;
        root.right = null;
        
        // Return new root (unchanged throughout recursion)
        return newRoot;
    }

    // ═══════════════════════════════════════════════════════════════════════
    // APPROACH 2: Iterative (More space efficient)
    // Time: O(h), Space: O(1)
    // ═══════════════════════════════════════════════════════════════════════
    
    /**
     * Flip tree upside down using iteration.
     * 
     * Algorithm:
     * Traverse down the left edge while keeping track of:
     * - What to assign as left child (parent's right)
     * - What to assign as right child (parent)
     * 
     * At each step:
     * 1. Save next node (current's left)
     * 2. Save what will be assigned (current's right and current)
     * 3. Assign current's new children
     * 4. Move to next
     * 
     * @param root Current root
     * @return New root of flipped tree
     */
    static BinaryTreeNode flipUpsideDown1(BinaryTreeNode root) {
        // Edge case: empty tree
        if (root == null) {
            return null;
        }
        
        // Track what to assign as left and right children at each step
        BinaryTreeNode assign_left = null;   // Will become left child
        BinaryTreeNode assign_right = null;  // Will become right child
        
        // Traverse down the left edge
        while (root != null) {
            // Step 1: Save the next node to process (current's left)
            BinaryTreeNode next = root.left;
            
            // Step 2: Save what will be assigned to next node's children
            BinaryTreeNode next_assign_left = root.right;  // Parent's right → child's left
            BinaryTreeNode next_assign_right = root;       // Parent → child's right
            
            // Step 3: Assign current node's new children
            root.left = assign_left;   // Left = previous node's right
            root.right = assign_right; // Right = previous node
            
            // Step 4: Move to next node, update tracking variables
            if (next != null) {
                root = next;
                assign_left = next_assign_left;
                assign_right = next_assign_right;
            } else {
                // Reached the leftmost node (new root)
                return root;
            }
        }
        
        return null;
    }
}
