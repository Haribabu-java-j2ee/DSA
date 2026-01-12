package trees.practice;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * ============================================================================
 * CONSTRUCT BINARY TREE FROM INORDER AND PREORDER TRAVERSALS
 * ============================================================================
 * 
 * PROBLEM STATEMENT:
 * ------------------
 * Given inorder and preorder traversals of a binary tree, construct the tree.
 * Assume all values are unique.
 * 
 * INTUITION:
 * ----------
 * KEY INSIGHTS:
 * 1. PREORDER: First element is always the ROOT
 * 2. INORDER: Elements left of root → LEFT subtree
 *             Elements right of root → RIGHT subtree
 * 
 * ALGORITHM:
 * 1. Take first element from preorder → this is ROOT
 * 2. Find this element in inorder
 * 3. Elements to LEFT of root in inorder → LEFT subtree
 * 4. Elements to RIGHT of root in inorder → RIGHT subtree
 * 5. Recursively build left and right subtrees
 * 
 * WHY THIS WORKS:
 * ---------------
 * - Preorder visits root first, so we always know the root
 * - Inorder splits the tree at root, so we know left/right boundaries
 * 
 * VISUAL REPRESENTATION:
 * ----------------------
 * Preorder: [1, 2, 3]
 * Inorder:  [2, 1, 3]
 * 
 * Step 1: preorder[0] = 1 → ROOT is 1
 * Step 2: Find 1 in inorder: index 1
 *         Left of 1: [2]
 *         Right of 1: [3]
 * 
 *         1
 *        / \
 *       2   3
 * 
 * MORE COMPLEX EXAMPLE:
 * Preorder: [3, 9, 20, 15, 7]
 * Inorder:  [9, 3, 15, 20, 7]
 * 
 * Step 1: Root = 3 (first in preorder)
 * Step 2: 3 at index 1 in inorder
 *         Left: [9]
 *         Right: [15, 20, 7]
 * 
 *           3
 *          / \
 *         9  20
 *           /  \
 *          15   7
 * 
 * TIME COMPLEXITY:
 * ----------------
 * WITHOUT HashMap: O(n²) - indexOf takes O(n) per call
 * WITH HashMap: O(n) - lookup is O(1), each node processed once
 * 
 * SPACE COMPLEXITY:
 * -----------------
 * O(n) for HashMap + O(h) for recursion stack
 * - HashMap stores all n indices: O(n)
 * - Recursion depth: O(h) where h = height
 * 
 * DRY RUN:
 * --------
 * Preorder: [1, 2, 3]
 * Inorder:  [2, 1, 3]
 * InorderMap: {2→0, 1→1, 3→2}
 * 
 * | Call                     | preorder_idx | Root | Inorder Range | Result      |
 * |--------------------------|--------------|------|---------------|-------------|
 * | build(0, 2)              | 0            | 1    | [0, 2]        | Create 1    |
 * | build_left(0, 0)         | 1            | 2    | [0, 0]        | Create 2    |
 * | build_left(0, -1)        | -            | -    | invalid       | Return null |
 * | build_right(1, 0)        | -            | -    | invalid       | Return null |
 * | build_right(2, 2)        | 2            | 3    | [2, 2]        | Create 3    |
 * | build_left(2, 1)         | -            | -    | invalid       | Return null |
 * | build_right(3, 2)        | -            | -    | invalid       | Return null |
 * 
 * Final Tree:
 *     1
 *    / \
 *   2   3
 */
public class ConstructBinaryTree {
    
    public static void main(String[] args) {
        ArrayList<Integer> inorder = new ArrayList<>();
        inorder.add(2);
        inorder.add(1);
        inorder.add(3);
        
        ArrayList<Integer> preorder = new ArrayList<>();
        preorder.add(1);
        preorder.add(2);
        preorder.add(3);
        
        BinaryTreeNode root = construct_binary_tree(inorder, preorder);
        System.out.println("Tree constructed successfully!");
    }

    // Global index to track current position in preorder array
    static int preorder_index;

    /**
     * MAIN FUNCTION - Construct binary tree from inorder and preorder
     * 
     * OPTIMIZATION: Use HashMap to store inorder indices
     * - Without HashMap: O(n) to find each element → O(n²) total
     * - With HashMap: O(1) lookup → O(n) total
     * 
     * Time Complexity: O(n)
     * Space Complexity: O(n) for HashMap + O(h) for recursion
     * 
     * @param inorder Inorder traversal of tree
     * @param preorder Preorder traversal of tree
     * @return Root of constructed tree
     */
    static BinaryTreeNode construct_binary_tree(ArrayList<Integer> inorder, ArrayList<Integer> preorder) {
        // Edge case: empty traversals
        if (inorder.isEmpty() && preorder.isEmpty()) {
            return null;
        }
        
        // Build HashMap for O(1) index lookup in inorder
        // Key: node value, Value: index in inorder array
        Map<Integer, Integer> inorderMap = new HashMap<>();
        for (int i = 0; i < inorder.size(); i++) {
            inorderMap.put(inorder.get(i), i);
        }
        
        // Reset preorder index
        preorder_index = 0;
        
        // Build tree recursively
        BinaryTreeNode root = construct_binary_tree_util(inorder, preorder, 0, inorder.size() - 1, inorderMap);
        return root;
    }

    /**
     * RECURSIVE HELPER - Build subtree from given range
     * 
     * @param inorder Inorder traversal
     * @param preorder Preorder traversal
     * @param low Start index in inorder (inclusive)
     * @param high End index in inorder (inclusive)
     * @param inorderMap HashMap for O(1) index lookup
     * @return Root of subtree
     */
    static BinaryTreeNode construct_binary_tree_util(ArrayList<Integer> inorder, 
                                                      ArrayList<Integer> preorder,
                                                      int low, int high,
                                                      Map<Integer, Integer> inorderMap) {
        // Base case: invalid range (no elements in this subtree)
        if (low > high) {
            return null;
        }
        
        // Step 1: Get root value from preorder (and advance index)
        // Preorder visits root first, so current index gives us the root
        Integer rootValue = preorder.get(preorder_index++);
        
        // Step 2: Create root node
        BinaryTreeNode root = new BinaryTreeNode(rootValue);
        
        // Base case: single element subtree (no children)
        if (low == high) {
            return root;
        }
        
        // Step 3: Find root's position in inorder (O(1) with HashMap)
        int rootIndexInInorder = inorderMap.get(rootValue);
        
        // Step 4: Build left subtree (elements before root in inorder)
        // Range: [low, rootIndex - 1]
        root.left = construct_binary_tree_util(inorder, preorder, low, rootIndexInInorder - 1, inorderMap);
        
        // Step 5: Build right subtree (elements after root in inorder)
        // Range: [rootIndex + 1, high]
        root.right = construct_binary_tree_util(inorder, preorder, rootIndexInInorder + 1, high, inorderMap);
        
        return root;
    }
}
