package trees;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * ============================================================================
 * BST DELETE - Delete nodes from a Binary Search Tree
 * ============================================================================
 * 
 * PROBLEM STATEMENT:
 * ------------------
 * Given a BST and a list of values to delete, remove all those values from the BST
 * while maintaining the BST property.
 * 
 * INTUITION:
 * ----------
 * Deleting from a BST has THREE cases:
 * 
 * 1. LEAF NODE (no children):
 *    - Simply remove the node (set parent's pointer to null)
 *    
 * 2. ONE CHILD:
 *    - Replace the node with its only child
 *    - The child can be either left or right
 *    
 * 3. TWO CHILDREN (most complex):
 *    - Find the INORDER SUCCESSOR (smallest node in right subtree)
 *    - Copy successor's value to current node
 *    - Delete the successor from right subtree (recursively)
 *    - Alternative: Could use INORDER PREDECESSOR (largest in left subtree)
 * 
 * WHY INORDER SUCCESSOR?
 * ----------------------
 * - Successor is the smallest value greater than current node
 * - Replacing with successor maintains BST property:
 *   - All left subtree values < successor < all right subtree values
 * 
 * VISUAL REPRESENTATION:
 * ----------------------
 *         4
 *        / \
 *       2   6
 *      / \ / \
 *     1  3 5  7
 * 
 * Delete 6:
 * - Has two children (5 and 7)
 * - Find successor: Go right, then leftmost = 7 (no left child of 7)
 * - Actually, successor of 6 is the minimum in right subtree = 7
 * - But wait, 6's right child is 7, and 7 has no left child
 * - So we copy 7 to 6's position, delete old 7
 * 
 * Result:
 *         4
 *        / \
 *       2   7
 *      / \ /
 *     1  3 5
 * 
 * TIME COMPLEXITY:
 * ----------------
 * - Per deletion: O(h) where h = height of tree
 * - For n deletions: O(n * h)
 * - Best case (balanced): O(n * log n) where n = number of nodes
 * - Worst case (skewed): O(n * n)
 * 
 * SPACE COMPLEXITY:
 * -----------------
 * - Recursive approach: O(h) for recursion stack
 * - Best case (balanced): O(log n)
 * - Worst case (skewed): O(n)
 * 
 * DRY RUN:
 * --------
 * Input: BST = [4, 2, 6, 1, 3, 5, 7], Delete = [5, 6]
 * 
 * Initial Tree:
 *         4
 *        / \
 *       2   6
 *      / \ / \
 *     1  3 5  7
 * 
 * Step 1: Delete 5
 * - Search for 5: 4 -> 6 -> 5 (found)
 * - 5 is a LEAF node (no children)
 * - Simply remove: set 6.left = null
 * 
 * After deleting 5:
 *         4
 *        / \
 *       2   6
 *      / \   \
 *     1  3    7
 * 
 * Step 2: Delete 6
 * - Search for 6: 4 -> 6 (found)
 * - 6 has ONE CHILD (right = 7)
 * - Replace 6 with its child 7
 * 
 * Final Tree:
 *         4
 *        / \
 *       2   7
 *      / \
 *     1  3
 */
public class BSTDelete {
    
    public static void main(String[] args) {
        // Build test BST: [4, 2, 6, 1, 3, 5, 7]
        int[] arr = {4, 2, 6, 1, 3, 5, 7};
        ArrayList<Integer> list = new ArrayList<>();
        Arrays.stream(arr).forEach(x -> list.add(x));
        BinaryTreeNode root = BSTInsert.build_a_bst(list);
        
        // Values to delete: [5, 6]
        int[] tobedeleted = {5, 6};
        ArrayList<Integer> deletelist = new ArrayList<>();
        Arrays.stream(tobedeleted).forEach(x -> deletelist.add(x));
        
        // Perform deletion
        root = delete_from_bst(root, deletelist);
        System.out.println(root);
    }

    /**
     * ITERATIVE APPROACH (Not fully working - kept for reference)
     * 
     * Issues with iterative approach:
     * - Complex pointer management
     * - Edge cases for root deletion
     * - Harder to handle all three deletion cases
     */
    private static BinaryTreeNode delete_elements(BinaryTreeNode root, ArrayList<Integer> deletelist) {
        // Base case: empty tree or nothing to delete
        if (root == null) {
            return root;
        }
        
        // Process each element to be deleted
        for (int element : deletelist) {
            BinaryTreeNode curr = root;   // Current node being examined
            BinaryTreeNode prev = null;   // Parent of current node
            BinaryTreeNode child = null;  // Child to replace deleted node
            
            // Step 1: Search for the element to delete
            while (curr != null) {
                if (element == curr.value) {
                    break;  // Found the node to delete
                } else if (element < curr.value) {
                    prev = curr;
                    curr = curr.left;  // Search in left subtree
                } else if (element > curr.value) {
                    prev = curr;
                    curr = curr.right; // Search in right subtree
                }
            }
            
            // Element not found in tree
            if (curr == null) {
                continue;
            }
            
            // Case 1: LEAF NODE (no children)
            if (curr.left == null && curr.right == null) {
                if (curr == prev.left) {
                    prev.left = null;  // Remove left child reference
                } else if (curr == prev.right) {
                    prev.right = null; // Remove right child reference
                }
                continue;
            }
            
            // Case 2: ONE CHILD - determine which child exists
            if (curr.left == null && curr.right != null) {
                child = curr.right;  // Only right child exists
            } else if (curr.left != null && curr.right == null) {
                child = curr.left;   // Only left child exists
            }

            // Replace deleted node with its only child
            if (curr == prev.left) {
                prev.left = child;
            } else if (curr == prev.right) {
                prev.right = child;
            }
            if (child != null) {
                continue;  // Deletion complete for one-child case
            }

            // Case 3: TWO CHILDREN - find inorder successor
            if (curr.left != null && curr.right != null) {
                BinaryTreeNode successor = curr.right;  // Start from right child
                prev = curr;
                
                // Find leftmost node in right subtree (minimum value)
                while (successor.left != null) {
                    prev = successor;
                    successor = successor.left;
                }
                
                // Copy successor's value to current node
                curr.value = successor.value;
                
                // Delete the successor node (it has at most one right child)
                if (successor == prev.left) {
                    prev.left = successor.right;
                } else if (successor == prev.right) {
                    prev.right = successor.right;
                }
            }
        }
        return root;
    }

    /**
     * MAIN RECURSIVE APPROACH (Working solution)
     * 
     * Time Complexity: O(n * h) for n deletions, h = tree height
     * Space Complexity: O(h) for recursion stack
     * 
     * @param root Root of BST
     * @param values_to_be_deleted List of values to delete
     * @return Root of modified BST
     */
    static BinaryTreeNode delete_from_bst(BinaryTreeNode root, ArrayList<Integer> values_to_be_deleted) {
        // Base case: empty tree or empty delete list
        if (root == null || values_to_be_deleted.isEmpty()) {
            return root;
        }
        
        // Delete each element one by one
        for (int element : values_to_be_deleted) {
            root = delete_from_bst_util(root, element);
        }
        return root;
    }

    /**
     * RECURSIVE HELPER - Deletes a single element from BST
     * 
     * ALGORITHM:
     * 1. If element < root.value, recurse on left subtree
     * 2. If element > root.value, recurse on right subtree
     * 3. If element == root.value, handle the three deletion cases
     * 
     * @param root Current subtree root
     * @param element Value to delete
     * @return Root of modified subtree
     */
    static BinaryTreeNode delete_from_bst_util(BinaryTreeNode root, int element) {
        // Base case: reached null (element not found)
        if (root == null) {
            return root;
        }
        
        // SEARCH PHASE: Navigate to the node to be deleted
        if (element < root.value) {
            // Element is in left subtree - recurse left
            // Update left child with result of deletion
            root.left = delete_from_bst_util(root.left, element);
        } else if (element > root.value) {
            // Element is in right subtree - recurse right
            // Update right child with result of deletion
            root.right = delete_from_bst_util(root.right, element);
        } else {
            // FOUND THE NODE TO DELETE - handle three cases
            
            // Case 1: LEAF NODE (no children)
            // Simply return null to remove this node
            if (root.right == null && root.left == null) {
                return null;
            }

            // Case 2a: ONLY LEFT CHILD exists
            // Replace node with its left child
            if (root.right == null) {
                BinaryTreeNode temp = root.left;
                root = null;  // Help garbage collection
                return temp;
            }

            // Case 2b: ONLY RIGHT CHILD exists
            // Replace node with its right child
            if (root.left == null) {
                BinaryTreeNode temp = root.right;
                root = null;  // Help garbage collection
                return temp;
            }

            // Case 3: TWO CHILDREN
            // Find inorder successor (smallest in right subtree)
            BinaryTreeNode temp = root.right;
            while (temp.left != null) {
                temp = temp.left;  // Go to leftmost node
            }
            
            // Copy successor's value to current node
            root.value = temp.value;
            
            // Delete the successor from right subtree
            // Successor has at most one child (right), so it's simpler to delete
            root.right = delete_from_bst_util(root.right, temp.value);
        }
        return root;
    }
}
