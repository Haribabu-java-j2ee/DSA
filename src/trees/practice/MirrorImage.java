package trees.practice;

import java.util.LinkedList;
import java.util.Queue;

/**
 * ============================================================================
 * MIRROR IMAGE OF BINARY TREE
 * ============================================================================
 * 
 * PROBLEM STATEMENT:
 * ------------------
 * Given a binary tree, convert it to its mirror image.
 * (Swap left and right children for every node)
 * 
 * INTUITION:
 * ----------
 * For each node, swap its left and right children.
 * Do this recursively/iteratively for all nodes.
 * 
 * TWO APPROACHES:
 * 1. BFS (Level order) - Process level by level
 * 2. DFS (Recursive) - Process using recursion
 * 
 * Both have same complexity but different traversal order.
 * 
 * VISUAL REPRESENTATION:
 * ----------------------
 * Original:                Mirror:
 *       0                    0
 *      / \                  / \
 *     1   2                2   1
 *    / \ / \              / \ / \
 *   3  4 5  6            6  5 4  3
 * 
 * Notice: Left and right subtrees are swapped at every level!
 * 
 * TIME COMPLEXITY:
 * ----------------
 * O(n) - visit each node exactly once
 * 
 * SPACE COMPLEXITY:
 * -----------------
 * BFS: O(w) where w = maximum width (queue size)
 * DFS: O(h) where h = height (recursion stack)
 * 
 * DRY RUN (BFS):
 * --------------
 * Original:
 *       0
 *      / \
 *     1   2
 *    / \ / \
 *   3  4 5  6
 * 
 * | Step | Node | Before Swap         | After Swap          |
 * |------|------|---------------------|---------------------|
 * | 1    | 0    | left=1, right=2     | left=2, right=1     |
 * | 2    | 2    | left=5, right=6     | left=6, right=5     |
 * | 3    | 1    | left=3, right=4     | left=4, right=3     |
 * | 4    | 6    | left=null, right=null | unchanged          |
 * | 5    | 5    | left=null, right=null | unchanged          |
 * | 6    | 4    | left=null, right=null | unchanged          |
 * | 7    | 3    | left=null, right=null | unchanged          |
 * 
 * Result:
 *       0
 *      / \
 *     2   1
 *    / \ / \
 *   6  5 4  3
 */
public class MirrorImage {
    
    public static void main(String[] args) {
        // Build original tree
        BinaryTreeNode root = new BinaryTreeNode(0);
        root.left = new BinaryTreeNode(1);
        root.right = new BinaryTreeNode(2);
        root.left.left = new BinaryTreeNode(3);
        root.left.right = new BinaryTreeNode(4);
        root.right.left = new BinaryTreeNode(5);
        root.right.right = new BinaryTreeNode(6);
        
        // Convert to mirror using BFS
        mirror_image(root);
        System.out.println("Mirror image created (BFS approach)");
        
        // Note: Running mirror_image_dfs on the same tree would restore original!
        // mirror_image_dfs(root);
    }

    // ═══════════════════════════════════════════════════════════════════════
    // APPROACH 1: BFS (Level Order)
    // Time: O(n), Space: O(w) where w = max width
    // ═══════════════════════════════════════════════════════════════════════
    
    /**
     * Create mirror image using BFS traversal.
     * 
     * Algorithm:
     * 1. Use queue for level order traversal
     * 2. For each node, swap its left and right children
     * 3. Add children to queue for processing
     * 
     * @param root Root of tree to mirror
     */
    static void mirror_image(BinaryTreeNode root) {
        // Edge case: empty tree
        if (root == null) return;
        
        // BFS using queue
        Queue<BinaryTreeNode> queue = new LinkedList<>();
        queue.add(root);
        
        while (!queue.isEmpty()) {
            // Dequeue current node
            BinaryTreeNode current = queue.poll();
            
            // SWAP left and right children
            BinaryTreeNode temp = current.left;
            current.left = current.right;
            current.right = temp;
            
            // Add children to queue (after swap!)
            if (current.left != null) {
                queue.add(current.left);
            }
            if (current.right != null) {
                queue.add(current.right);
            }
        }
    }

    // ═══════════════════════════════════════════════════════════════════════
    // APPROACH 2: DFS (Recursive)
    // Time: O(n), Space: O(h) where h = height
    // ═══════════════════════════════════════════════════════════════════════
    
    /**
     * Create mirror image using DFS traversal (wrapper).
     * 
     * @param root Root of tree to mirror
     */
    public static void mirror_image_dfs(BinaryTreeNode root) {
        root = mirror_image_util(root);
    }

    /**
     * Recursive helper for mirror image.
     * 
     * Algorithm:
     * 1. Recursively mirror left subtree
     * 2. Recursively mirror right subtree
     * 3. Swap left and right pointers
     * 
     * This is essentially a POSTORDER approach:
     * - Process children first
     * - Then swap at current node
     * 
     * @param root Current node
     * @return Mirrored subtree root
     */
    private static BinaryTreeNode mirror_image_util(BinaryTreeNode root) {
        // Base case: null node
        if (root == null) return null;
        
        // Step 1: Recursively mirror left subtree
        BinaryTreeNode left = mirror_image_util(root.left);
        
        // Step 2: Recursively mirror right subtree
        BinaryTreeNode right = mirror_image_util(root.right);
        
        // Step 3: Swap left and right
        root.left = right;
        root.right = left;
        
        return root;
    }
}
