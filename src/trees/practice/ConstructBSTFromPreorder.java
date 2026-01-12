package trees.practice;

import java.util.ArrayList;
import java.util.Collections;

/**
 * ============================================================================
 * CONSTRUCT BST FROM PREORDER TRAVERSAL
 * ============================================================================
 * 
 * PROBLEM STATEMENT:
 * ------------------
 * Given the preorder traversal of a BST, construct the BST.
 * 
 * KEY INSIGHT:
 * ------------
 * Unlike a general binary tree, a BST can be uniquely constructed from
 * JUST the preorder traversal! Why?
 * - BST property defines structure: left < root < right
 * - Preorder gives us the root first
 * - We can determine what goes left/right based on values
 * 
 * TWO APPROACHES:
 * ---------------
 * 
 * APPROACH 1: Sort to get inorder, then use standard construction
 * - Sort preorder → gives inorder (BST property: inorder is sorted!)
 * - Use binary search to find root position in inorder
 * - Time: O(n log n) due to sorting and binary search
 * 
 * APPROACH 2: Use value bounds (more efficient)
 * - Each node has a valid range [min, max]
 * - Root can have any value: [-∞, +∞]
 * - Left child must be in range [min, parent-1]
 * - Right child must be in range [parent+1, max]
 * - Time: O(n) - each element processed exactly once
 * 
 * VISUAL REPRESENTATION:
 * ----------------------
 * Preorder: [2, 0, 1, 3, 5, 4]
 * 
 * Step-by-step (Approach 2):
 * 
 * 1. Root = 2, range [-∞, +∞]
 *    Left child range: [-∞, 1]
 *    Right child range: [3, +∞]
 * 
 * 2. Next = 0, check: 0 ∈ [-∞, 1]? YES → Left child of 2
 *          0
 *         /
 *    Left of 0: [-∞, -1], Right of 0: [1, 1]
 * 
 * 3. Next = 1, check: 1 ∈ [-∞, -1]? NO
 *              check: 1 ∈ [1, 1]? YES → Right child of 0
 * 
 * 4. Continue building...
 * 
 * Final Tree:
 *         2
 *        / \
 *       0   3
 *        \   \
 *         1   5
 *            /
 *           4
 * 
 * TIME COMPLEXITY:
 * ----------------
 * Approach 1: O(n log n) - sorting + binary search per node
 * Approach 2: O(n) - each element processed exactly once
 * 
 * SPACE COMPLEXITY:
 * -----------------
 * Approach 1: O(n) for sorted array + O(h) for recursion
 * Approach 2: O(h) for recursion stack only
 * 
 * DRY RUN (Approach 2):
 * ---------------------
 * Preorder: [2, 0, 1, 3, 5, 4]
 * 
 * | Index | Value | Range Check      | Action           |
 * |-------|-------|------------------|------------------|
 * | 0     | 2     | [-∞, +∞]         | Create root      |
 * | 1     | 0     | [-∞, 1] ✓        | Left of 2        |
 * | 2     | 1     | [-∞, -1] ✗       | -                |
 * | 2     | 1     | [1, 1] ✓         | Right of 0       |
 * | 3     | 3     | [2, 1] ✗         | Backtrack        |
 * | 3     | 3     | [3, +∞] ✓        | Right of 2       |
 * | 4     | 5     | [4, +∞] ✓        | Right of 3       |
 * | 5     | 4     | [4, 4] ✓         | Left of 5        |
 */
public class ConstructBSTFromPreorder {
    
    public static void main(String[] args) {
        ArrayList<Integer> preorder = new ArrayList<>();
        preorder.add(2);
        preorder.add(0);
        preorder.add(1);
        preorder.add(3);
        preorder.add(5);
        preorder.add(4);
        
        // Test both approaches
        System.out.println("Approach 1 (Sort + Binary Search):");
        build_binary_search_tree(preorder);
        
        System.out.println("Approach 2 (Value Bounds):");
        build_binary_search_tree1(preorder);
    }

    // Global index to track current position in preorder
    static int preorder_index;
    
    // ═══════════════════════════════════════════════════════════════════════
    // APPROACH 1: Sort to get inorder, use binary search
    // Time: O(n log n), Space: O(n)
    // ═══════════════════════════════════════════════════════════════════════
    
    /**
     * Build BST using sorting approach.
     * 
     * Key insight: Inorder traversal of BST is sorted!
     * So we can get inorder by sorting preorder.
     * 
     * @param preorder Preorder traversal
     * @return Root of BST
     */
    static BinaryTreeNode build_binary_search_tree(ArrayList<Integer> preorder) {
        // Reset index
        preorder_index = 0;
        
        // Step 1: Create inorder by sorting (BST inorder = sorted)
        ArrayList<Integer> inorder = new ArrayList<>();
        inorder.addAll(preorder);
        Collections.sort(inorder);
        
        // Step 2: Build tree using both traversals
        BinaryTreeNode root = build_binary_search_tree_util(preorder, inorder, 0, inorder.size() - 1);
        return root;
    }

    /**
     * Recursive helper using both preorder and inorder.
     * 
     * @param preorder Preorder traversal
     * @param inorder Sorted inorder traversal
     * @param low Start of current range in inorder
     * @param high End of current range in inorder
     * @return Root of subtree
     */
    static BinaryTreeNode build_binary_search_tree_util(ArrayList<Integer> preorder,
                                                         ArrayList<Integer> inorder, 
                                                         int low, int high) {
        // Base case: invalid range
        if (low > high) {
            return null;
        }
        
        // Step 1: Create node with next preorder element
        BinaryTreeNode node = new BinaryTreeNode(preorder.get(preorder_index++));
        
        // Base case: single element
        if (low == high) {
            return node;
        }

        // Step 2: Find this node's position in inorder using binary search
        int index = getMidIndex(inorder, low, high, node.value);
        
        // Step 3: Build subtrees
        node.left = build_binary_search_tree_util(preorder, inorder, low, index - 1);
        node.right = build_binary_search_tree_util(preorder, inorder, index + 1, high);
        
        return node;
    }

    /**
     * Binary search to find element in sorted inorder array.
     * 
     * @param inorder Sorted array
     * @param low Start index
     * @param high End index
     * @param root Value to find
     * @return Index of root in inorder
     */
    static int getMidIndex(ArrayList<Integer> inorder, int low, int high, int root) {
        if (low > high) {
            return -1;
        }
        
        int mid = low + (high - low) / 2;
        
        if (inorder.get(mid) == root) {
            return mid;  // Found!
        } else if (root > inorder.get(mid)) {
            return getMidIndex(inorder, mid + 1, high, root);  // Search right
        } else {
            return getMidIndex(inorder, low, mid - 1, root);    // Search left
        }
    }

    // ═══════════════════════════════════════════════════════════════════════
    // APPROACH 2: Use value bounds (Optimal)
    // Time: O(n), Space: O(h)
    // ═══════════════════════════════════════════════════════════════════════
    
    /**
     * Build BST using value bounds approach (optimal).
     * 
     * Key insight: Each node must be within a valid range.
     * We maintain [low, high] bounds as we traverse preorder.
     * 
     * @param preorder Preorder traversal
     * @return Root of BST
     */
    static BinaryTreeNode build_binary_search_tree1(ArrayList<Integer> preorder) {
        // Reset index
        preorder_index = 0;
        
        // Start with full range
        return build_binary_search_tree_util(preorder, -1000000000, 1000000000);
    }

    /**
     * Recursive helper using value bounds.
     * 
     * Algorithm:
     * 1. Check if current preorder element is within [low, high]
     * 2. If yes, create node and recurse:
     *    - Left child range: [low, node.value - 1]
     *    - Right child range: [node.value + 1, high]
     * 3. If no, return null (this subtree doesn't exist)
     * 
     * @param preorder Preorder traversal
     * @param low Minimum allowed value
     * @param high Maximum allowed value
     * @return Root of subtree
     */
    static BinaryTreeNode build_binary_search_tree_util(ArrayList<Integer> preorder, 
                                                         int low, int high) {
        // Base case: processed all elements
        if (preorder_index == preorder.size()) {
            return null;
        }

        // Get current value (don't increment index yet!)
        int currentValue = preorder.get(preorder_index);
        
        // Check if current value is within valid range
        if (currentValue < low || currentValue > high) {
            // This value doesn't belong in this subtree
            return null;
        }

        // Value is valid, create node and increment index
        BinaryTreeNode node = new BinaryTreeNode(preorder.get(preorder_index++));
        
        // Build left subtree: values must be < current
        node.left = build_binary_search_tree_util(preorder, low, node.value - 1);
        
        // Build right subtree: values must be > current
        node.right = build_binary_search_tree_util(preorder, node.value + 1, high);
        
        return node;
    }
}
