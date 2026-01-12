package trees.practice;

import java.util.ArrayList;

/**
 * ============================================================================
 * KTH SMALLEST ELEMENT IN BST
 * ============================================================================
 * 
 * PROBLEM STATEMENT:
 * ------------------
 * Given a BST and an integer k, find the kth smallest element in the tree.
 * k is 1-indexed (1st smallest, 2nd smallest, etc.)
 * 
 * INTUITION:
 * ----------
 * KEY INSIGHT: Inorder traversal of BST gives elements in SORTED order!
 * 
 * So to find kth smallest:
 * 1. Do inorder traversal
 * 2. The kth element encountered is the answer
 * 
 * APPROACHES:
 * -----------
 * APPROACH 1: Full traversal (shown here)
 * - Do complete inorder traversal
 * - Return the (k-1)th element from the list
 * - Simple but traverses entire tree
 * 
 * APPROACH 2: Early termination
 * - Keep count during traversal
 * - Stop when count reaches k
 * - More efficient if k is small
 * 
 * APPROACH 3: Augmented BST
 * - Store subtree size at each node
 * - Navigate directly to kth element
 * - O(h) time but requires extra space per node
 * 
 * VISUAL REPRESENTATION:
 * ----------------------
 * BST:
 *       2
 *      / \
 *     1   3
 * 
 * Inorder: [1, 2, 3]
 * 
 * k=1 → 1st smallest = 1
 * k=2 → 2nd smallest = 2
 * k=3 → 3rd smallest = 3
 * 
 * MORE COMPLEX EXAMPLE:
 *         5
 *        / \
 *       3   7
 *      / \ / \
 *     2  4 6  8
 *    /
 *   1
 * 
 * Inorder: [1, 2, 3, 4, 5, 6, 7, 8]
 * k=4 → 4th smallest = 4
 * 
 * TIME COMPLEXITY:
 * ----------------
 * Approach 1: O(n) - traverse all nodes
 * Approach 2: O(k) in best case, O(n) worst case
 * Approach 3: O(h) where h = height
 * 
 * SPACE COMPLEXITY:
 * -----------------
 * Approach 1: O(n) for storing traversal + O(h) for recursion
 * Approach 2: O(h) for recursion
 * Approach 3: O(h) for recursion + O(n) for augmented info
 * 
 * DRY RUN:
 * --------
 * Tree:
 *       2
 *      / \
 *     1   3
 * 
 * k = 3 (find 3rd smallest)
 * 
 * | Step | Node | Action            | Inorder List |
 * |------|------|-------------------|--------------|
 * | 1    | 2    | Go left           | []           |
 * | 2    | 1    | Go left → null    | []           |
 * | 3    | 1    | Add 1             | [1]          |
 * | 4    | 1    | Go right → null   | [1]          |
 * | 5    | 2    | Add 2             | [1, 2]       |
 * | 6    | 3    | Go left → null    | [1, 2]       |
 * | 7    | 3    | Add 3             | [1, 2, 3]    |
 * | 8    | 3    | Go right → null   | [1, 2, 3]    |
 * 
 * k=3 → return inorder[2] = 3 ✓
 */
public class KthSmallestInBST {
    
    public static void main(String[] args) {
        // Build BST
        BinaryTreeNode root = new BinaryTreeNode(2);
        root.left = new BinaryTreeNode(1);
        root.right = new BinaryTreeNode(3);
        
        System.out.println("3rd smallest: " + kth_smallest_element(root, 3));  // Expected: 3
        System.out.println("1st smallest: " + kth_smallest_element(root, 1));  // Expected: 1
        System.out.println("2nd smallest: " + kth_smallest_element(root, 2));  // Expected: 2
    }
    
    // List to store inorder traversal
    static ArrayList<Integer> inorder = new ArrayList<>();
    
    /**
     * FIND KTH SMALLEST ELEMENT
     * 
     * Algorithm:
     * 1. Perform inorder traversal (gives sorted order)
     * 2. Return element at index k-1 (0-indexed)
     * 
     * Time Complexity: O(n)
     * Space Complexity: O(n) for storing traversal
     * 
     * @param root Root of BST
     * @param k Position to find (1-indexed)
     * @return Kth smallest element
     */
    static Integer kth_smallest_element(BinaryTreeNode root, Integer k) {
        // Edge case: empty tree
        if (root == null) {
            return 0;
        }
        
        // Clear previous results
        inorder.clear();
        
        // Step 1: Do inorder traversal to get sorted elements
        dfs_inorder(root);
        
        // Step 2: Return kth element (k-1 because list is 0-indexed)
        return inorder.get(k - 1);
    }

    /**
     * INORDER DFS - Collects elements in sorted order
     * 
     * For BST, inorder traversal visits nodes in ascending order:
     * - Visit all left descendants (smaller values)
     * - Visit current node
     * - Visit all right descendants (larger values)
     * 
     * @param root Current node
     */
    static void dfs_inorder(BinaryTreeNode root) {
        // Base case: null node
        if (root == null) {
            return;
        }
        
        // Left subtree first (smaller elements)
        dfs_inorder(root.left);
        
        // Process current node (add to list)
        inorder.add(root.value);
        
        // Right subtree last (larger elements)
        dfs_inorder(root.right);
    }
    
    /**
     * OPTIMIZED APPROACH - Early termination
     * 
     * Stop traversal as soon as we find kth element.
     * More efficient when k is small.
     * 
     * @param root Root of BST
     * @param k Position to find (1-indexed)
     * @return Kth smallest element
     */
    static int count = 0;
    static int kthResult = 0;
    
    static Integer kth_smallest_optimized(BinaryTreeNode root, int k) {
        count = 0;
        kthResult = 0;
        dfs_inorder_optimized(root, k);
        return kthResult;
    }
    
    static void dfs_inorder_optimized(BinaryTreeNode root, int k) {
        if (root == null || count >= k) {
            return;  // Early termination if already found
        }
        
        // Left subtree
        dfs_inorder_optimized(root.left, k);
        
        // Process current node
        count++;
        if (count == k) {
            kthResult = root.value;
            return;  // Found! No need to continue
        }
        
        // Right subtree
        dfs_inorder_optimized(root.right, k);
    }
}
