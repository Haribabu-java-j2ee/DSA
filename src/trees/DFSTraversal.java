package trees;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * ============================================================================
 * DFS TRAVERSAL - Depth First Search Traversals of Binary Tree
 * ============================================================================
 * 
 * PROBLEM STATEMENT:
 * ------------------
 * Implement the three DFS traversal methods for a binary tree:
 * 1. PREORDER:  Root → Left → Right
 * 2. INORDER:   Left → Root → Right
 * 3. POSTORDER: Left → Right → Root
 * 
 * INTUITION:
 * ----------
 * DFS explores as deep as possible before backtracking.
 * The three traversals differ only in WHEN we visit/process the root:
 * 
 * PRE = "before"  → Visit root BEFORE children
 * IN = "in between" → Visit root BETWEEN left and right children
 * POST = "after"  → Visit root AFTER children
 * 
 * VISUAL REPRESENTATION:
 * ----------------------
 *         0
 *        / \
 *       1   2
 *      / \
 *     3   4
 * 
 * PREORDER (Root-Left-Right):
 * Visit root, then recursively visit left subtree, then right subtree
 * Path: 0 → 1 → 3 → 4 → 2
 * 
 * INORDER (Left-Root-Right):
 * Recursively visit left subtree, then root, then right subtree
 * Path: 3 → 1 → 4 → 0 → 2
 * For BST, this gives SORTED ORDER!
 * 
 * POSTORDER (Left-Right-Root):
 * Recursively visit left subtree, then right subtree, then root
 * Path: 3 → 4 → 1 → 2 → 0
 * 
 * MEMORY TRICK:
 * -------------
 * The name tells you where ROOT goes:
 * - PRE-order:  ROOT comes FIRST (before both children)
 * - IN-order:   ROOT comes IN the MIDDLE (between children)
 * - POST-order: ROOT comes LAST (after both children)
 * 
 * USE CASES:
 * ----------
 * PREORDER:
 * - Copy/clone a tree
 * - Serialize tree structure
 * - Evaluate prefix expressions
 * 
 * INORDER:
 * - Get sorted order from BST
 * - Validate BST
 * - Find kth smallest element
 * 
 * POSTORDER:
 * - Delete tree (delete children before parent)
 * - Calculate tree height/size
 * - Evaluate postfix expressions
 * 
 * TIME COMPLEXITY:
 * ----------------
 * All three: O(n) - we visit each node exactly once
 * 
 * SPACE COMPLEXITY:
 * -----------------
 * O(h) for recursion stack, where h = height
 * - Best case (balanced): O(log n)
 * - Worst case (skewed): O(n)
 * - Result list: O(n)
 * 
 * DRY RUN (PREORDER):
 * -------------------
 *         0
 *        / \
 *       1   2
 *      / \
 *     3   4
 * 
 * | Call Stack      | Action        | Result So Far |
 * |-----------------|---------------|---------------|
 * | preorder(0)     | Add 0         | [0]           |
 * | preorder(1)     | Add 1         | [0,1]         |
 * | preorder(3)     | Add 3         | [0,1,3]       |
 * | preorder(null)  | Return        | [0,1,3]       |
 * | preorder(null)  | Return        | [0,1,3]       |
 * | preorder(4)     | Add 4         | [0,1,3,4]     |
 * | preorder(null)  | Return        | [0,1,3,4]     |
 * | preorder(null)  | Return        | [0,1,3,4]     |
 * | preorder(2)     | Add 2         | [0,1,3,4,2]   |
 * | preorder(null)  | Return        | [0,1,3,4,2]   |
 * | preorder(null)  | Return        | [0,1,3,4,2]   |
 * 
 * Final: [0, 1, 3, 4, 2]
 */
public class DFSTraversal {
    
    public static void main(String[] args) {
        // Build test tree
        //         0
        //        / \
        //       1   2
        //      / \
        //     3   4
        BinaryTreeNode root = new BinaryTreeNode(0);
        root.left = new BinaryTreeNode(1);
        root.right = new BinaryTreeNode(2);
        root.left.left = new BinaryTreeNode(3);
        root.left.right = new BinaryTreeNode(4);
        
        // Demonstrate all three traversals
        System.out.print("Preorder:  ");
        preorder(root).forEach(s -> System.out.print(s + " "));
        System.out.println();
        
        System.out.print("Inorder:   ");
        inorder(root).forEach(s -> System.out.print(s + " "));
        System.out.println();
        
        System.out.print("Postorder: ");
        postorder(root).forEach(s -> System.out.print(s + " "));
    }

    // ═══════════════════════════════════════════════════════════════════════
    // PREORDER TRAVERSAL: Root → Left → Right
    // ═══════════════════════════════════════════════════════════════════════
    
    /**
     * PREORDER TRAVERSAL
     * 
     * Order: Visit ROOT first, then LEFT subtree, then RIGHT subtree
     * 
     * @param root Root of the tree
     * @return List of values in preorder sequence
     */
    static ArrayList<Integer> preorder(BinaryTreeNode root) {
        ArrayList<Integer> result = new ArrayList<>();
        preorder_util(result, root);
        return result;
    }

    /**
     * PREORDER HELPER - Recursive implementation
     * 
     * @param result List to accumulate values
     * @param root Current node being processed
     */
    static void preorder_util(ArrayList<Integer> result, BinaryTreeNode root) {
        // Base case: null node, nothing to process
        if (root == null) {
            return;
        }
        
        // Step 1: VISIT ROOT FIRST (this is what makes it "pre"order)
        result.add(root.value);
        
        // Step 2: Recursively process LEFT subtree
        preorder_util(result, root.left);
        
        // Step 3: Recursively process RIGHT subtree
        preorder_util(result, root.right);
    }

    // ═══════════════════════════════════════════════════════════════════════
    // INORDER TRAVERSAL: Left → Root → Right
    // ═══════════════════════════════════════════════════════════════════════
    
    /**
     * INORDER TRAVERSAL
     * 
     * Order: LEFT subtree first, then ROOT, then RIGHT subtree
     * For BST: This gives elements in SORTED order!
     * 
     * @param root Root of the tree
     * @return List of values in inorder sequence
     */
    static ArrayList<Integer> inorder(BinaryTreeNode root) {
        ArrayList<Integer> result = new ArrayList<>();
        inorder_util(result, root);
        return result;
    }

    /**
     * INORDER HELPER - Recursive implementation
     * 
     * @param result List to accumulate values
     * @param root Current node being processed
     */
    static void inorder_util(ArrayList<Integer> result, BinaryTreeNode root) {
        // Base case: null node, nothing to process
        if (root == null) {
            return;
        }
        
        // Step 1: Recursively process LEFT subtree first
        inorder_util(result, root.left);
        
        // Step 2: VISIT ROOT IN THE MIDDLE (this is what makes it "in"order)
        result.add(root.value);
        
        // Step 3: Recursively process RIGHT subtree
        inorder_util(result, root.right);
    }

    // ═══════════════════════════════════════════════════════════════════════
    // POSTORDER TRAVERSAL: Left → Right → Root
    // ═══════════════════════════════════════════════════════════════════════
    
    /**
     * POSTORDER TRAVERSAL
     * 
     * Order: LEFT subtree first, then RIGHT subtree, then ROOT
     * Useful for: Deleting tree, calculating height/size
     * 
     * @param root Root of the tree
     * @return List of values in postorder sequence
     */
    static ArrayList<Integer> postorder(BinaryTreeNode root) {
        ArrayList<Integer> result = new ArrayList<>();
        postorder_util(result, root);
        return result;
    }

    /**
     * POSTORDER HELPER - Recursive implementation
     * 
     * @param result List to accumulate values
     * @param root Current node being processed
     */
    static void postorder_util(ArrayList<Integer> result, BinaryTreeNode root) {
        // Base case: null node, nothing to process
        if (root == null) {
            return;
        }
        
        // Step 1: Recursively process LEFT subtree first
        postorder_util(result, root.left);
        
        // Step 2: Recursively process RIGHT subtree
        postorder_util(result, root.right);
        
        // Step 3: VISIT ROOT LAST (this is what makes it "post"order)
        result.add(root.value);
    }
}
