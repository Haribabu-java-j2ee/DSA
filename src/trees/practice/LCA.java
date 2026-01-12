package trees.practice;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * ============================================================================
 * LOWEST COMMON ANCESTOR (LCA) IN BINARY TREE
 * ============================================================================
 * 
 * PROBLEM STATEMENT:
 * ------------------
 * Given a binary tree and two nodes, find their Lowest Common Ancestor.
 * 
 * LCA DEFINITION:
 * ---------------
 * The lowest common ancestor is the deepest node that has both nodes as 
 * descendants (where we allow a node to be a descendant of itself).
 * 
 * THREE APPROACHES:
 * -----------------
 * 1. RECURSIVE (Optimal): O(n) time, O(h) space
 *    - Check if current node is either target
 *    - Recurse left and right
 *    - If both return non-null, current node is LCA
 * 
 * 2. PARENT POINTER + PATH: O(n) time, O(n) space
 *    - Build parent pointer for each node
 *    - Find path from root to each node
 *    - Find last common node in both paths
 * 
 * 3. PARENT POINTER + ANCESTOR CHECK: O(n) time, O(n) space
 *    - Build parent pointers
 *    - For each node, check if it's ancestor of both targets
 *    - Return deepest such node
 * 
 * VISUAL REPRESENTATION:
 * ----------------------
 *           1
 *          / \
 *         2   3
 *        / \ / \
 *       4  5 6  7
 *         / \
 *        8   9
 * 
 * LCA(8, 9) = 5 (direct parent of both)
 * LCA(8, 7) = 1 (root is common ancestor)
 * LCA(4, 5) = 2 (parent of both)
 * LCA(5, 9) = 5 (5 is ancestor of itself and 9)
 * 
 * APPROACH 1 INTUITION (Recursive):
 * ----------------------------------
 * 1. If current node is null, return null
 * 2. If current node is one of the targets, return it
 * 3. Search left subtree, search right subtree
 * 4. If both searches return non-null → current is LCA
 * 5. If only one returns non-null → that's the LCA (other target is descendant)
 * 
 * TIME COMPLEXITY:
 * ----------------
 * All approaches: O(n) - need to traverse tree
 * 
 * SPACE COMPLEXITY:
 * -----------------
 * Approach 1: O(h) for recursion
 * Approach 2 & 3: O(n) for parent pointers
 * 
 * DRY RUN (Approach 1):
 * ---------------------
 * Tree:
 *           1
 *          / \
 *         2   3
 *        / \ 
 *       4   5
 *          / \
 *         8   9
 * 
 * Find LCA(8, 9):
 * 
 * | Node | Left Result | Right Result | Return  | Reason                    |
 * |------|-------------|--------------|---------|---------------------------|
 * | 8    | null        | null         | 8       | 8 is target               |
 * | 9    | null        | null         | 9       | 9 is target               |
 * | 5    | 8           | 9            | 5       | Both non-null → LCA!      |
 * | 4    | null        | null         | null    | Neither target            |
 * | 2    | null        | 5            | 5       | Pass up non-null result   |
 * | 3    | null        | null         | null    | Neither target            |
 * | 1    | 5           | null         | 5       | Pass up non-null result   |
 * 
 * Result: LCA(8, 9) = 5 ✓
 */
public class LCA {
    
    public static void main(String[] args) {
        // Build tree
        BinaryTreeNode root = new BinaryTreeNode(1);
        root.left = new BinaryTreeNode(2);
        root.right = new BinaryTreeNode(3);
        root.left.left = new BinaryTreeNode(4);
        root.left.right = new BinaryTreeNode(5);
        root.right.left = new BinaryTreeNode(6);
        root.right.right = new BinaryTreeNode(7);
        root.left.right.left = new BinaryTreeNode(8);
        root.left.right.right = new BinaryTreeNode(9);

        BinaryTreeNode nodeA = root.left.right.left;   // Node 8
        BinaryTreeNode nodeB = root.left.right.right;  // Node 9

        System.out.println("Approach 1 (Recursive): " + lca(root, nodeA, nodeB));
        System.out.println("Approach 2 (Ancestor Check): " + lca1(root, nodeA, nodeB));
        System.out.println("Approach 3 (Path Comparison): " + lca2(root, nodeA, nodeB));
    }
    
    // ═══════════════════════════════════════════════════════════════════════
    // APPROACH 1: Recursive (Optimal)
    // Time: O(n), Space: O(h)
    // ═══════════════════════════════════════════════════════════════════════
    
    /**
     * Find LCA using recursive approach.
     * 
     * Algorithm:
     * 1. If current is null or matches a target, return current
     * 2. Recursively search left and right
     * 3. If both non-null, current is LCA
     * 4. Otherwise, return the non-null result
     * 
     * @param root Current node
     * @param a First target node
     * @param b Second target node
     * @return Value of LCA, or -1 if not found
     */
    static int lca(BinaryTreeNode root, BinaryTreeNode a, BinaryTreeNode b) {
        // Base case: null node
        if (root == null) {
            return -1;
        }

        // If current node is one of the targets, return its value
        // (We found one of the nodes we're looking for)
        if (root == a || root == b) {
            return root.value;
        }

        // Recursively search in left and right subtrees
        int left = lca(root.left, a, b);
        int right = lca(root.right, a, b);

        // If both searches found a target, current node is LCA!
        // (One target is in left subtree, other is in right)
        if (left != -1 && right != -1) {
            return root.value;
        }

        // If only one search found a target, pass it up
        // (Either both targets are in that subtree, or only one is found)
        return left != -1 ? left : right;
    }

    // ═══════════════════════════════════════════════════════════════════════
    // APPROACH 2: Parent Pointer + Ancestor Check
    // Time: O(n), Space: O(n)
    // ═══════════════════════════════════════════════════════════════════════
    
    /**
     * Find LCA using parent pointers and ancestor checking.
     * 
     * Algorithm:
     * 1. Build parent pointer array using DFS
     * 2. For each node, check if it's ancestor of both targets
     * 3. Return the deepest such node
     * 
     * @param root Root of tree
     * @param a First target node
     * @param b Second target node
     * @return Value of LCA
     */
    static int lca1(BinaryTreeNode root, BinaryTreeNode a, BinaryTreeNode b) {
        int[] par = new int[100020];    // Parent of each node
        int[] level = new int[100020];  // Level/depth of each node

        // Build parent and level arrays
        dfs(root, 0, par, level);

        int aa = a.value;
        int bb = b.value;
        int[] answerNode = new int[1];    // To store result
        int[] levelAnswer = new int[1];   // To track deepest level

        // Check each node if it's ancestor of both
        traverseAndUpdate(root, aa, bb, par, level, answerNode, levelAnswer);

        return answerNode[0];
    }

    /**
     * DFS to build parent and level arrays.
     */
    static void dfs(BinaryTreeNode root, int parent, int[] par, int[] level) {
        if (root == null) {
            return;
        }
        par[root.value] = parent;
        level[root.value] = level[parent] + 1;

        dfs(root.left, root.value, par, level);
        dfs(root.right, root.value, par, level);
    }

    /**
     * Check each node if it's ancestor of both targets.
     * Track the deepest such node.
     */
    static void traverseAndUpdate(BinaryTreeNode root, int a, int b, 
                                   int[] par, int[] level, 
                                   int[] answerNode, int[] levelAnswer) {
        if (root == null) {
            return;
        }

        int currentNode = root.value;
        
        // Check if current node is ancestor of BOTH targets
        if (isAncestor(currentNode, a, par) && isAncestor(currentNode, b, par)) {
            // If this is deeper than previous answer, update
            if (level[currentNode] > levelAnswer[0]) {
                answerNode[0] = currentNode;
                levelAnswer[0] = level[currentNode];
            }
        }
        
        traverseAndUpdate(root.left, a, b, par, level, answerNode, levelAnswer);
        traverseAndUpdate(root.right, a, b, par, level, answerNode, levelAnswer);
    }

    /**
     * Check if currentNode is ancestor of node a.
     * A node is ancestor if it appears in the path from a to root.
     */
    static boolean isAncestor(int currentNode, int a, int[] par) {
        while (a != 0) {  // Traverse up to root
            if (currentNode == a) {
                return true;
            }
            a = par[a];  // Move to parent
        }
        return false;
    }

    // ═══════════════════════════════════════════════════════════════════════
    // APPROACH 3: Path Comparison
    // Time: O(n), Space: O(n)
    // ═══════════════════════════════════════════════════════════════════════
    
    /**
     * Find LCA by comparing paths from root to each target.
     * 
     * Algorithm:
     * 1. Build parent pointers
     * 2. Get path from root to each target
     * 3. Compare paths, find last common node
     * 
     * @param root Root of tree
     * @param a First target node
     * @param b Second target node
     * @return Value of LCA
     */
    static int lca2(BinaryTreeNode root, BinaryTreeNode a, BinaryTreeNode b) {
        int[] par = new int[100020];
        int aa = a.value, bb = b.value;
        
        // Build parent pointers
        dfs1(root, -1, par);
        
        // Get paths from root to each target
        List<Integer> pathOfA = getPath(par, aa);
        List<Integer> pathOfB = getPath(par, bb);

        // Find last common node in both paths
        for (int i = 0; i < Math.min(pathOfA.size(), pathOfB.size()); i++) {
            if (!pathOfA.get(i).equals(pathOfB.get(i))) {
                // First mismatch, return previous (last common)
                return pathOfA.get(i - 1);
            }
        }
        
        // One path is prefix of other, return last node of shorter path
        return pathOfA.get(Math.min(pathOfA.size(), pathOfB.size()) - 1);
    }

    /**
     * DFS to build parent pointers (root's parent = -1).
     */
    static void dfs1(BinaryTreeNode head, int parent, int[] par) {
        if (head == null)
            return;
        par[head.value] = parent;
        dfs1(head.left, head.value, par);
        dfs1(head.right, head.value, par);
    }

    /**
     * Get path from root to given node.
     * Build path bottom-up then reverse.
     */
    static List<Integer> getPath(int[] par, int node) {
        List<Integer> path = new ArrayList<>();
        
        // Build path from node to root
        while (par[node] != -1) {
            path.add(node);
            node = par[node];
        }
        path.add(node);  // Add root
        
        // Reverse to get path from root to node
        Collections.reverse(path);
        return path;
    }
}
