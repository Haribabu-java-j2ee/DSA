package trees;

import java.util.ArrayList;
import java.util.Arrays;

import static trees.BSTInsert.build_a_bst;
import static trees.BSTSearch.search_node;

/**
 * ============================================================================
 * BST SUCCESSOR - Find Inorder Successor in Binary Search Tree
 * ============================================================================
 * https://www.educative.io/interview-prep/coding/inorder-successor-in-bst
 * PROBLEM STATEMENT:
 * ------------------
 * Given a BST and a node, find the INORDER SUCCESSOR of that node.
 * Inorder Successor = The node that comes NEXT in inorder traversal.
 * In a BST, this is the node with the SMALLEST value GREATER than given node.
 * 
 * IMPORTANT NOTE:
 * ---------------
 * The node parameter might NOT be the actual node from the tree!
 * It could be just a value holder. So we must:
 * 1. First FIND the node in the tree by traversing
 * 2. Then check if THAT found node has a right subtree
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
 * Example 4: Tree [3,1,5,null,2], Successor of 1
 *         3
 *        / \
 *       1   5
 *        \
 *         2
 * - Find node 1 in tree, it has right child = 2
 * - Successor = leftmost in right subtree = 2
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
 * Tree: [3,1,5,null,2]
 *         3
 *        / \
 *       1   5
 *        \
 *         2
 * 
 * Find successor of 1:
 * 
 * | Step | current | p.value vs current.value | Action      | ancestor |
 * |------|---------|--------------------------|-------------|----------|
 * | 1    | 3       | 1 < 3                    | Go left     | 3        |
 * | 2    | 1       | 1 == 1, Found!           | Check right | 3        |
 * | 3    | -       | current.right = 2        | Has right!  | -        |
 * | 4    | 2       | 2.left = null            | Return 2    | -        |
 * 
 * Result: Successor of 1 is 2 ✓
 */
public class BSTSuccessor {
    
    public static void main(String[] args) {
        // Test case: [3,1,5,null,2], find successor of 1
        Integer[] arr = {3, 1, 5, null, 2};
        ArrayList<Integer> list = new ArrayList<>();
        Arrays.stream(arr).forEach(list::add);
        BinaryTreeNode root = build_a_bst(list);
        
        // Create a node with just value (simulating LeetCode scenario)
        BinaryTreeNode node = new BinaryTreeNode(1);  // Not connected to tree!
        
        BinaryTreeNode successor = findSuccessor(root, node);
        System.out.println("Successor of 1: " + (successor != null ? successor.value : null));
        // Expected: 2
        
        // Test case 2: Successor of 3
        node = new BinaryTreeNode(3);
        successor = findSuccessor(root, node);
        System.out.println("Successor of 3: " + (successor != null ? successor.value : null));
        // Expected: 5
    }

    /**
     * FIND INORDER SUCCESSOR (Handles case where node might not be from tree)
     * 
     * Algorithm:
     * 1. Traverse the tree to find the node matching p.value
     * 2. During traversal, track ancestor (when we go left)
     * 3. When found, check if THAT node (current) has right subtree
     * 4. If yes, return leftmost in right subtree
     * 5. If no, return the tracked ancestor
     * 
     * @param root Root of the BST
     * @param p Node for which to find successor (might just have value, not tree connection)
     * @return Successor node, or null if node is the largest
     */
    private static BinaryTreeNode findSuccessor(BinaryTreeNode root, BinaryTreeNode p) {
        // Edge case: empty tree
        if (root == null) {
            return null;
        }
        
        BinaryTreeNode ancestor = null;  // Track potential successor (last left turn)
        BinaryTreeNode current = root;   // Current node during traversal
        
        // Traverse the tree to find p
        while (current != null) {
            if (p.value > current.value) {
                // p is larger, go RIGHT
                // Don't update ancestor (current is smaller than p)
                current = current.right;
                
            } else if (p.value < current.value) {
                // p is smaller, go LEFT
                // Update ancestor! Current could be the successor
                ancestor = current;
                current = current.left;
                
            } else {
                // ═══════════════════════════════════════════════════════════════
                // FOUND THE NODE! Now check if it has right subtree
                // ═══════════════════════════════════════════════════════════════
                
                // CASE 1: Node has RIGHT SUBTREE
                // Successor is the leftmost node in right subtree
                if (current.right != null) {
                    current = current.right;
                    while (current.left != null) {
                        current = current.left;
                    }
                    return current;  // Leftmost in right subtree
                }
                
                // CASE 2: Node has NO RIGHT SUBTREE
                // Successor is the tracked ancestor
                break;
            }
        }
        
        return ancestor;
    }
    
    /**
     * ORIGINAL APPROACH (Only works when p is actual node from tree)
     * 
     * This approach assumes p.right is correctly connected to the tree.
     * Use findSuccessor() instead for general case.
     * 
     * @param root Root of the BST
     * @param node Actual node from the tree (with valid left/right pointers)
     * @return Successor node, or null if node is the largest
     */
    private static BinaryTreeNode findSuccessorWhenNodeIsFromTree(BinaryTreeNode root, BinaryTreeNode node) {
        if (root == null) {
            return null;
        }
        
        BinaryTreeNode current;
        
        // CASE 1: Node has RIGHT SUBTREE (can check directly since node is from tree)
        if (node.right != null) {
            current = node.right;
            while (current.left != null) {
                current = current.left;
            }
            return current;
        }
        
        // CASE 2: Node has NO RIGHT SUBTREE
        BinaryTreeNode ancestor = null;
        current = root;
        
        while (current.value != node.value) {
            if (node.value > current.value) {
                current = current.right;
            } else if (node.value < current.value) {
                ancestor = current;
                current = current.left;
            }
        }
        
        return ancestor;
    }
}
