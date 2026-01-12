package trees.practice;

import java.util.ArrayList;

/**
 * ============================================================================
 * MERGE TWO BSTs INTO A BALANCED BST
 * ============================================================================
 * 
 * PROBLEM STATEMENT:
 * ------------------
 * Given two Binary Search Trees, merge them into a single BALANCED BST.
 * The resulting tree should contain all nodes from both trees.
 * 
 * INTUITION:
 * ----------
 * KEY INSIGHTS:
 * 1. Inorder traversal of BST gives SORTED order
 * 2. Merging two sorted arrays is O(n + m)
 * 3. Building balanced BST from sorted array is O(n)
 * 
 * ALGORITHM:
 * 1. Get inorder (sorted) traversal of both BSTs
 * 2. Merge the two sorted lists (like merge in merge sort)
 * 3. Build a balanced BST from the merged sorted list
 * 
 * WHY THIS WORKS:
 * - Inorder of BST is sorted: [left values] < [root] < [right values]
 * - Merging sorted arrays maintains sorted order
 * - Picking middle element as root creates balanced tree
 * 
 * VISUAL REPRESENTATION:
 * ----------------------
 * BST1:           BST2:
 *       5           8
 *      / \         / \
 *     3   6       1   9
 *    / \   \
 *   2   4   7
 * 
 * Inorder BST1: [2, 3, 4, 5, 6, 7]
 * Inorder BST2: [1, 8, 9]
 * Merged:       [1, 2, 3, 4, 5, 6, 7, 8, 9]
 * 
 * Build balanced BST from merged list:
 *            5
 *          /   \
 *         2     7
 *        / \   / \
 *       1   3 6   8
 *            \     \
 *             4     9
 * 
 * TIME COMPLEXITY:
 * ----------------
 * O(n + m) where n and m are sizes of the two BSTs
 * - Inorder traversal: O(n) + O(m)
 * - Merge: O(n + m)
 * - Build BST: O(n + m)
 * 
 * SPACE COMPLEXITY:
 * -----------------
 * O(n + m) for storing the merged list
 * O(h) for recursion where h = height of result tree
 * 
 * DRY RUN:
 * --------
 * BST1:           BST2:
 *       5           8
 *      / \         / \
 *     3   6       1   9
 *    / \   \
 *   2   4   7
 * 
 * Step 1: Inorder BST1
 * Traversal: 2 → 3 → 4 → 5 → 6 → 7
 * Result: [2, 3, 4, 5, 6, 7]
 * 
 * Step 2: Inorder BST2
 * Traversal: 1 → 8 → 9
 * Result: [1, 8, 9]
 * 
 * Step 3: Merge sorted lists
 * [2, 3, 4, 5, 6, 7] + [1, 8, 9]
 * 
 * | i | j | Compare | Add | Merged So Far       |
 * |---|---|---------|-----|---------------------|
 * | 0 | 0 | 2 > 1   | 1   | [1]                 |
 * | 0 | 1 | 2 < 8   | 2   | [1, 2]              |
 * | 1 | 1 | 3 < 8   | 3   | [1, 2, 3]           |
 * | 2 | 1 | 4 < 8   | 4   | [1, 2, 3, 4]        |
 * | 3 | 1 | 5 < 8   | 5   | [1, 2, 3, 4, 5]     |
 * | 4 | 1 | 6 < 8   | 6   | [1, 2, 3, 4, 5, 6]  |
 * | 5 | 1 | 7 < 8   | 7   | [1,2,3,4,5,6,7]     |
 * | 6 | 1 | -       | 8   | [1,2,3,4,5,6,7,8]   |
 * | 6 | 2 | -       | 9   | [1,2,3,4,5,6,7,8,9] |
 * 
 * Step 4: Build balanced BST
 * Mid = 4, element = 5 → root
 * Left half [1,2,3,4], Right half [6,7,8,9]
 * Continue recursively...
 */
public class Merge2BST {
    
    public static void main(String[] args) {
        // Build BST1
        BinaryTreeNode root1 = new BinaryTreeNode(5);
        root1.left = new BinaryTreeNode(3);
        root1.right = new BinaryTreeNode(6);
        root1.left.left = new BinaryTreeNode(2);
        root1.left.right = new BinaryTreeNode(4);
        root1.right.right = new BinaryTreeNode(7);
        
        // Build BST2
        BinaryTreeNode root2 = new BinaryTreeNode(8);
        root2.left = new BinaryTreeNode(1);
        root2.right = new BinaryTreeNode(9);
        
        // Merge
        BinaryTreeNode merged = merge_two_binary_search_trees(root1, root2);
        System.out.println("Merged BST created successfully!");
    }

    /**
     * MERGE TWO BSTs INTO BALANCED BST
     * 
     * @param root1 Root of first BST
     * @param root2 Root of second BST
     * @return Root of merged balanced BST
     */
    static BinaryTreeNode merge_two_binary_search_trees(BinaryTreeNode root1, BinaryTreeNode root2) {
        // Edge cases: one tree is empty
        if (root1 == null) {
            return root2;
        }
        if (root2 == null) {
            return root1;
        }
        
        // Step 1: Get inorder (sorted) traversal of both trees
        ArrayList<Integer> inorder_root1 = new ArrayList<>();
        dfs(root1, inorder_root1);
        
        ArrayList<Integer> inorder_root2 = new ArrayList<>();
        dfs(root2, inorder_root2);
        
        // Step 2: Merge the two sorted lists
        ArrayList<Integer> mergedList = merge_sorted_list(inorder_root1, inorder_root2);
        
        // Step 3: Build balanced BST from merged sorted list
        BinaryTreeNode root = build_bst(mergedList, 0, mergedList.size() - 1);
        
        return root;
    }

    /**
     * INORDER DFS - Get sorted elements from BST
     * 
     * @param root Current node
     * @param inorder List to collect elements
     */
    static void dfs(BinaryTreeNode root, ArrayList<Integer> inorder) {
        if (root == null) {
            return;
        }
        
        // Inorder: Left → Root → Right (gives sorted order for BST)
        dfs(root.left, inorder);    // Process left subtree
        inorder.add(root.value);    // Add current node
        dfs(root.right, inorder);   // Process right subtree
    }

    /**
     * MERGE TWO SORTED LISTS
     * 
     * Standard merge algorithm from merge sort.
     * 
     * @param inorder_root1 Sorted list from BST1
     * @param inorder_root2 Sorted list from BST2
     * @return Merged sorted list
     */
    static ArrayList<Integer> merge_sorted_list(ArrayList<Integer> inorder_root1, 
                                                 ArrayList<Integer> inorder_root2) {
        ArrayList<Integer> mergedList = new ArrayList<>();
        int i = 0;  // Pointer for first list
        int j = 0;  // Pointer for second list

        // Compare and add smaller element
        while (i < inorder_root1.size() && j < inorder_root2.size()) {
            if (inorder_root1.get(i) < inorder_root2.get(j)) {
                mergedList.add(inorder_root1.get(i));
                i++;
            } else if (inorder_root1.get(i) > inorder_root2.get(j)) {
                mergedList.add(inorder_root2.get(j));
                j++;
            } else {
                // Equal elements - add both
                mergedList.add(inorder_root1.get(i));
                mergedList.add(inorder_root2.get(j));
                i++;
                j++;
            }
        }

        // Add remaining elements from first list
        while (i < inorder_root1.size()) {
            mergedList.add(inorder_root1.get(i));
            i++;
        }

        // Add remaining elements from second list
        while (j < inorder_root2.size()) {
            mergedList.add(inorder_root2.get(j));
            j++;
        }

        return mergedList;
    }

    /**
     * BUILD BALANCED BST FROM SORTED ARRAY
     * 
     * Algorithm:
     * 1. Pick middle element as root (ensures balance)
     * 2. Recursively build left subtree from left half
     * 3. Recursively build right subtree from right half
     * 
     * @param inorder_list Sorted array
     * @param low Start index (inclusive)
     * @param high End index (inclusive)
     * @return Root of balanced BST
     */
    static BinaryTreeNode build_bst(ArrayList<Integer> inorder_list, int low, int high) {
        // Base case: invalid range
        if (low > high) {
            return null;
        }

        // Pick middle element as root (ensures balance)
        int mid = low + (high - low) / 2;
        BinaryTreeNode node = new BinaryTreeNode(inorder_list.get(mid));
        
        // Recursively build left subtree from left half
        node.left = build_bst(inorder_list, low, mid - 1);
        
        // Recursively build right subtree from right half
        node.right = build_bst(inorder_list, mid + 1, high);
        
        return node;
    }
}
