package trees.practice;

/**
 * ============================================================================
 * CLONE TREE - Create a deep copy of a binary tree
 * ============================================================================
 * 
 * PROBLEM STATEMENT:
 * ------------------
 * Given a binary tree, create a complete clone (deep copy) of it.
 * Each node in the new tree should be a new object with the same value
 * and structure as the original.
 * 
 * INTUITION:
 * ----------
 * Use PREORDER traversal approach (Root → Left → Right):
 * 1. Create a new node with current node's value (process root)
 * 2. Recursively clone left subtree and attach as left child
 * 3. Recursively clone right subtree and attach as right child
 * 
 * WHY PREORDER?
 * - We need to create parent before we can attach children
 * - Root must exist before we can set its left/right pointers
 * 
 * VISUAL REPRESENTATION:
 * ----------------------
 * Original:            Clone:
 *      200                 200' (new object)
 *     /   \               /   \
 *   100   300           100'  300'
 * 
 * Each node in clone is a NEW object (different memory address)
 * but with the SAME value and structure.
 * 
 * TIME COMPLEXITY:
 * ----------------
 * O(n) - we visit each node exactly once and create one new node
 * 
 * SPACE COMPLEXITY:
 * -----------------
 * O(h) for recursion stack + O(n) for the cloned tree
 * - Recursion stack: O(h) where h = height
 * - Cloned tree storage: O(n)
 * 
 * DRY RUN:
 * --------
 * Original Tree:
 *      200
 *     /   \
 *   100   300
 * 
 * | Call Stack        | Action                          | Result                    |
 * |-------------------|---------------------------------|---------------------------|
 * | clone(200)        | Create node 200'                | 200' created              |
 * | clone(100)        | Create node 100'                | 100' created              |
 * | clone(null)       | Return null (100's left)        | 100'.left = null          |
 * | clone(null)       | Return null (100's right)       | 100'.right = null         |
 * | Back to clone(100)| Return 100'                     | 200'.left = 100'          |
 * | clone(300)        | Create node 300'                | 300' created              |
 * | clone(null)       | Return null (300's left)        | 300'.left = null          |
 * | clone(null)       | Return null (300's right)       | 300'.right = null         |
 * | Back to clone(300)| Return 300'                     | 200'.right = 300'         |
 * | Back to clone(200)| Return 200'                     | Complete clone returned   |
 * 
 * Final Cloned Tree:
 *      200'
 *     /   \
 *   100'  300'
 * 
 * SHALLOW vs DEEP COPY:
 * ---------------------
 * SHALLOW COPY: Copies references only
 *   - clone = original; // Same object!
 *   - Modifying clone affects original
 * 
 * DEEP COPY: Creates new objects (what we're doing)
 *   - Each node is a new object
 *   - Modifying clone does NOT affect original
 */
public class CloneTree {

    public static void main(String[] args) {
        // Build original tree
        BinaryTreeNode root = new BinaryTreeNode(200);
        root.left = new BinaryTreeNode(100);
        root.right = new BinaryTreeNode(300);
        
        // Create clone
        BinaryTreeNode cloned = clone_tree(root);
        
        // Verify clone is independent
        System.out.println("Original root: " + root.value);
        System.out.println("Cloned root: " + cloned.value);
        System.out.println("Are they same object? " + (root == cloned));  // false
    }
    
    /**
     * CLONE TREE - Create deep copy of binary tree
     * 
     * Algorithm (Preorder traversal):
     * 1. Base case: if root is null, return null
     * 2. Create new node with same value as root
     * 3. Recursively clone left subtree → assign to new node's left
     * 4. Recursively clone right subtree → assign to new node's right
     * 5. Return the new node
     * 
     * Time Complexity: O(n) - visit each node once
     * Space Complexity: O(h) stack + O(n) for cloned tree
     * 
     * @param root Root of original tree
     * @return Root of cloned tree (new objects)
     */
    static BinaryTreeNode clone_tree(BinaryTreeNode root) {
        // Base case: null node clones to null
        if (root == null) {
            return root;
        }
        
        // Step 1: Create NEW node with same value (this is the key to deep copy!)
        BinaryTreeNode cloned_tree = new BinaryTreeNode(root.value);
        
        // Step 2: Recursively clone left subtree and attach
        cloned_tree.left = clone_tree(root.left);
        
        // Step 3: Recursively clone right subtree and attach
        cloned_tree.right = clone_tree(root.right);
        
        // Return the cloned subtree rooted at this node
        return cloned_tree;
    }
}
