package trees;

import java.util.ArrayList;
import java.util.Arrays;

import static trees.BSTInsert.build_a_bst;
import static trees.BSTSearch.search_node;

/**
 * ============================================================================
 * BST SUCCESSOR - Find Inorder Successor in Binary Search Tree
 * ============================================================================
 * 
 * PROBLEM STATEMENT:
 * ------------------
 * Given a BST and a node, find the INORDER SUCCESSOR of that node.
 * Inorder Successor = The node that comes NEXT in inorder traversal.
 * In a BST, this is the node with the SMALLEST value GREATER than given node.
 * 
 * INTUITION:
 * ----------
 * TWO CASES to consider:
 * 
 * CASE 1: Node has RIGHT SUBTREE
 * - Successor is the LEFTMOST node in right subtree
 * - Why? In inorder traversal, after visiting a node, we visit its right subtree
 * - The first node in right subtree's inorder is the leftmost node
 * 
 * CASE 2: Node has NO RIGHT SUBTREE
 * - Successor is the nearest ANCESTOR for which given node is in LEFT subtree
 * - We traverse from root, tracking the last node where we went LEFT
 * - Why? If we went left, current node is smaller than ancestor
 * 
 * VISUAL REPRESENTATION:
 * ----------------------
 *           44
 *          /  \
 *        17    88
 *       /  \   / \
 *      8   32 65  97
 *         /   / \  /
 *       28   54 82 93
 *         \     /
 *         29   76
 *             /
 *            80
 * 
 * Example 1: Successor of 32
 * - 32 has no right child
 * - Traverse from root: 44 > 32 (go left, save 44), 17 < 32 (go right)
 * - Found 32, return saved ancestor = 44
 * 
 * Example 2: Successor of 17
 * - 17 has right subtree (rooted at 32)
 * - Find leftmost in right subtree: 32 -> 28 (leftmost)
 * - Successor = 28
 * 
 * Example 3: Successor of 97
 * - 97 is the largest element
 * - Has no right subtree
 * - No ancestor has 97 in its left subtree
 * - Successor = null (doesn't exist)
 * 
 * TIME COMPLEXITY:
 * ----------------
 * - O(h) where h = height of tree
 * - Best case (balanced): O(log n)
 * - Worst case (skewed): O(n)
 * 
 * SPACE COMPLEXITY:
 * -----------------
 * - O(1) - only using a few pointers
 * 
 * DRY RUN:
 * --------
 * Find successor of 93 in the tree above:
 * 
 * Step 1: Check if node 93 has right subtree
 *         93.right = null (NO right subtree) → Use Case 2
 * 
 * Step 2: Find ancestor from root
 *         | Current | Node Value | Action           | Ancestor |
 *         |---------|------------|------------------|----------|
 *         | 44      | 44         | 93 > 44, go right| null     |
 *         | 88      | 88         | 93 > 88, go right| null     |
 *         | 97      | 97         | 93 < 97, go left | 97       |
 *         | 93      | 93         | Found node!      | 97       |
 * 
 * Result: Successor of 93 is 97 ✓
 * 
 * Verification: Inorder traversal: ...82, 88, 93, 97...
 * 93 comes right before 97. ✓
 */
public class BSTSuccessor {
    
    public static void main(String[] args) {
        // Build a complex BST for testing
        Integer[] arr = {44, 17, 88, 8, 32, 65, 97, null, null, 28, null, 54, 82, 93, null, 29, 76, 80};
        ArrayList<Integer> list = new ArrayList<>();
        Arrays.stream(arr).forEach(list::add);
        BinaryTreeNode root = build_a_bst(list);
        
        // Find node 93 and get its successor
        BinaryTreeNode node = search_node(root, 93);
        System.out.println(root);
        
        BinaryTreeNode successor = findSuccesor(root, node);
        System.out.println("Successor of 93: " + (successor != null ? successor.value : null));
    }

    /**
     * FIND INORDER SUCCESSOR
     * 
     * @param root Root of the BST
     * @param node Node for which to find successor
     * @return Successor node, or null if node is the largest
     */
    private static BinaryTreeNode findSuccesor(BinaryTreeNode root, BinaryTreeNode node) {
        // Edge case: empty tree
        if (root == null) {
            return null;
        }
        
        BinaryTreeNode current;
        
        // ═══════════════════════════════════════════════════════════════════
        // CASE 1: Node has RIGHT SUBTREE
        // ═══════════════════════════════════════════════════════════════════
        // Successor is the MINIMUM value in right subtree
        // i.e., the leftmost node in right subtree
        if (node.right != null) {
            current = node.right;  // Start from right child
            
            // Go as far left as possible
            while (current.left != null) {
                current = current.left;
            }
            
            return current;  // This is the successor
        }
        
        // ═══════════════════════════════════════════════════════════════════
        // CASE 2: Node has NO RIGHT SUBTREE
        // ═══════════════════════════════════════════════════════════════════
        // Successor is an ancestor where we last took a LEFT turn
        // (i.e., the nearest ancestor greater than the node)
        
        BinaryTreeNode ancestor = null;  // Track the potential successor
        current = root;                   // Start from root
        
        // Navigate to the node, tracking the last "left turn"
        while (current.value != node.value) {
            if (node.value > current.value) {
                // Node is larger, go RIGHT
                // Don't update ancestor (going right means current is smaller)
                current = current.right;
            } else if (node.value < current.value) {
                // Node is smaller, go LEFT
                // Update ancestor! Current node could be the successor
                // (we're moving to its left subtree, so current > node)
                ancestor = current;
                current = current.left;
            }
        }
        
        // Return the last ancestor where we went left
        // If we never went left, ancestor is null (node is the largest)
        return ancestor;
    }
}
