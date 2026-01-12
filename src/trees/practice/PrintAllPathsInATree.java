package trees.practice;

import java.util.ArrayList;

/**
 * ============================================================================
 * PRINT ALL ROOT-TO-LEAF PATHS IN A BINARY TREE
 * ============================================================================
 * 
 * PROBLEM STATEMENT:
 * ------------------
 * Given a binary tree, print all root-to-leaf paths.
 * 
 * DEFINITION:
 * -----------
 * - ROOT: The topmost node of the tree
 * - LEAF: A node with no children (both left and right are null)
 * - PATH: Sequence of nodes from root to a leaf
 * 
 * INTUITION:
 * ----------
 * Use DFS with backtracking:
 * 1. Start from root, maintain current path
 * 2. At each leaf node, save the current path
 * 3. Backtrack after processing each node
 * 
 * BACKTRACKING PATTERN:
 * 1. Add current node to path
 * 2. If leaf, save path to result
 * 3. Recurse on children
 * 4. Remove current node from path (backtrack)
 * 
 * VISUAL REPRESENTATION:
 * ----------------------
 *         1
 *        / \
 *       2   3
 *      / \ / \
 *     4  5 6  7
 * 
 * All paths:
 * - 1 → 2 → 4
 * - 1 → 2 → 5
 * - 1 → 3 → 6
 * - 1 → 3 → 7
 * 
 * TIME COMPLEXITY:
 * ----------------
 * O(n * h) where n = number of nodes, h = height
 * - O(n) to visit all nodes
 * - At each leaf, we copy path of length O(h)
 * - Number of leaves = O(n/2) = O(n)
 * - Total: O(n) visits + O(n * h) copies = O(n * h)
 * 
 * SPACE COMPLEXITY:
 * -----------------
 * O(h) for recursion stack + O(h) for current path
 * O(n * h) for storing all paths in result
 * 
 * DRY RUN:
 * --------
 *         1
 *        / \
 *       2   3
 *      / \
 *     4   5
 * 
 * | Step | Node | Path      | Action                    | Result          |
 * |------|------|-----------|---------------------------|-----------------|
 * | 1    | 1    | [1]       | Not leaf, go left         | []              |
 * | 2    | 2    | [1,2]     | Not leaf, go left         | []              |
 * | 3    | 4    | [1,2,4]   | Leaf! Add to result       | [[1,2,4]]       |
 * | 4    | 2    | [1,2]     | Backtracked, go right     | [[1,2,4]]       |
 * | 5    | 5    | [1,2,5]   | Leaf! Add to result       | [[1,2,4],[1,2,5]] |
 * | 6    | 1    | [1]       | Backtracked, go right     | [[1,2,4],[1,2,5]] |
 * | 7    | 3    | [1,3]     | Leaf! Add to result       | [[1,2,4],[1,2,5],[1,3]] |
 */
public class PrintAllPathsInATree {
    
    public static void main(String[] args) {
        // Build tree
        BinaryTreeNode root = new BinaryTreeNode(1);
        root.left = new BinaryTreeNode(2);
        root.right = new BinaryTreeNode(3);
        root.left.left = new BinaryTreeNode(4);
        root.left.right = new BinaryTreeNode(5);
        root.right.left = new BinaryTreeNode(6);
        root.right.right = new BinaryTreeNode(7);
        
        System.out.println("All root-to-leaf paths:");
        all_paths_of_a_binary_tree(root).forEach(System.out::println);
    }

    /**
     * FIND ALL ROOT-TO-LEAF PATHS
     * 
     * @param root Root of tree
     * @return List of all paths
     */
    static ArrayList<ArrayList<Integer>> all_paths_of_a_binary_tree(BinaryTreeNode root) {
        ArrayList<ArrayList<Integer>> result = new ArrayList<>();
        
        // Edge case: empty tree
        if (root == null) {
            return result;
        }
        
        // Start DFS with empty path
        ArrayList<Integer> temp = new ArrayList<>();
        all_paths_of_a_binary_tree_util(result, root, temp);
        
        return result;
    }

    /**
     * DFS HELPER with backtracking
     * 
     * Algorithm:
     * 1. If current node is a leaf:
     *    - Add node to path
     *    - Save copy of path to result
     *    - Remove node (backtrack)
     *    - Return
     * 2. Add current node to path
     * 3. Recurse on left child (if exists)
     * 4. Recurse on right child (if exists)
     * 5. Remove current node from path (backtrack)
     * 
     * @param result Accumulator for all paths
     * @param root Current node
     * @param temp Current path (modified in place)
     */
    static void all_paths_of_a_binary_tree_util(ArrayList<ArrayList<Integer>> result, 
                                                 BinaryTreeNode root, 
                                                 ArrayList<Integer> temp) {
        // LEAF NODE: This completes a path!
        if (root.left == null && root.right == null) {
            // Add leaf to complete the path
            temp.add(root.value);
            
            // Save a COPY of current path to result
            // (Must copy because temp will be modified later)
            result.add(new ArrayList<>(temp));
            
            // BACKTRACK: Remove leaf before returning
            temp.remove(temp.size() - 1);
            return;
        }
        
        // INTERNAL NODE: Add to path and recurse on children
        temp.add(root.value);
        
        // Recurse on left child (if exists)
        if (root.left != null) {
            all_paths_of_a_binary_tree_util(result, root.left, temp);
        }
        
        // Recurse on right child (if exists)
        if (root.right != null) {
            all_paths_of_a_binary_tree_util(result, root.right, temp);
        }
        
        // BACKTRACK: Remove current node before returning to parent
        temp.remove(temp.size() - 1);
    }
}

/**
 * BINARY TREE NODE CLASS
 * 
 * Contains:
 * - value: Integer stored in node
 * - left, right: Child pointers
 * - next_right: Sibling pointer (for level-order connection problems)
 */
class BinaryTreeNode {
    Integer value;
    BinaryTreeNode left;
    BinaryTreeNode right;
    BinaryTreeNode next_right;  // For sibling problems

    BinaryTreeNode(Integer value) {
        this.value = value;
        this.left = null;
        this.right = null;
        this.next_right = null;
    }
}
