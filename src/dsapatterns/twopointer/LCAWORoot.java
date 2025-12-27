package dsapatterns.twopointer;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * =====================================================================================
 * LCA (Lowest Common Ancestor) WITHOUT Root Access - Two Pointer Approach
 * =====================================================================================
 *
 * PROBLEM: Find LCA of two nodes when you only have parent pointers, no root access.
 *
 * KEY INSIGHT: When a pointer reaches null (past root), redirect it to the OTHER
 *              node's starting point. This equalizes the total distance both travel!
 *
 * =====================================================================================
 * EXAMPLE TREE:
 * =====================================================================================
 *
 *                    6           <- root
 *                   / \
 *                  2   8
 *                 / \ / \
 *                0  4 7  9
 *                  / \
 *                 3   5
 *
 * Finding LCA of nodes 0 and 5:
 * -----------------------------
 * Path from 0 to root: 0 → 2 → 6         (length = 3)
 * Path from 5 to root: 5 → 4 → 2 → 6     (length = 4)
 * LCA = 2
 *
 * =====================================================================================
 * STEP-BY-STEP WALKTHROUGH:
 * =====================================================================================
 *
 *  Step |  p1 (starts at 0)  |  p2 (starts at 5)  |  Notes
 * ------+--------------------+--------------------+----------------------------------
 *   0   |        0           |        5           |  Initial positions
 *   1   |        2           |        4           |  Both move to parent
 *   2   |        6           |        2           |  p1 at root, p2 continues
 *   3   |       null         |        6           |  p1 past root
 *   4   |  → Switch to 5     |       null         |  p1 redirects, p2 past root
 *   5   |        4           |  → Switch to 0     |  p2 redirects
 *   6   |       [2] ✓        |       [2] ✓        |  MATCH! LCA found
 *
 * =====================================================================================
 * WHY THIS WORKS - MATHEMATICAL PROOF:
 * =====================================================================================
 *
 * Let's define:
 *   a = distance from node p to LCA        (0 → 2 = 1 step)
 *   b = distance from node q to LCA        (5 → 4 → 2 = 2 steps)
 *   c = distance from LCA to root          (2 → 6 = 1 step)
 *
 * Without switching:
 *   p1 travels: a + c = 1 + 1 = 2  (then hits null)
 *   p2 travels: b + c = 2 + 1 = 3  (then hits null)
 *
 * After switching (p1 → q's start, p2 → p's start):
 *   p1 total: (a + c) + (b + c) = a + b + 2c  →  then walks back (b) to LCA
 *   p2 total: (b + c) + (a + c) = a + b + 2c  →  then walks back (a) to LCA
 *
 * Both travel SAME total distance: a + b + 2c, meeting at LCA!
 *
 * =====================================================================================
 * VISUAL PATH TRACE:
 * =====================================================================================
 *
 *   p1: 0 → 2 → 6 → null → [switch to 5] → 4 → [2] ← MEET HERE!
 *                              ↓
 *   p2: 5 → 4 → 2 → 6 → null → [switch to 0] → [2] ← MEET HERE!
 *
 * Total steps for both: 6 steps each, meeting at node 2 (LCA)
 *
 * =====================================================================================
 * TIME & SPACE COMPLEXITY:
 * =====================================================================================
 *   Time:  O(h) where h = height of tree (worst case O(n) for skewed tree)
 *   Space: O(1) - only two pointers used
 *
 * =====================================================================================
 *
 * @see <a href="https://www.educative.io/interview-prep/coding/solution-lowest-common-ancestor-of-a-binary-tree-iii">Educative Reference</a>
 */
public class LCAWORoot {

    public static void main(String[] args) {
        List<List<Integer>> input_trees = Arrays.asList(
                Arrays.asList(100, 50, 200, 25, 75, 350),
                Arrays.asList(100, 200, 75, 50, 25, 350),
                Arrays.asList(350, 100, 75, 50, 200, 25),
                Arrays.asList(100, 50, 200, 25, 75, 350),
                Arrays.asList(25, 50, 75, 100, 200, 350)
        );
        List<List<Integer>> input_nodes = Arrays.asList(
                Arrays.asList(25, 75),
                Arrays.asList(50, 350),
                Arrays.asList(100, 200),
                Arrays.asList(50, 25),
                Arrays.asList(350, 200)
        );

        for (int i = 0; i < input_trees.size(); i++) {
            EduBinaryTree tree = new EduBinaryTree(input_trees.get(i));
            System.out.println((i + 1) + ".\tBinary tree:");
            //Print.displayTree(tree.getRoot());
            System.out.println("\n\tp = " + input_nodes.get(i).get(0));
            System.out.println("\tq = " + input_nodes.get(i).get(1));
            EduTreeNode p = tree.find(tree.getRoot(), input_nodes.get(i).get(0));
            EduTreeNode q = tree.find(tree.getRoot(), input_nodes.get(i).get(1));
            EduTreeNode lca = LowestCommonAncestor(p, q);
            System.out.println("\n\tLowest common ancestor: " + lca.data);
            System.out.println(new String(new char[100]).replace("\0", "-"));
        }
    }
    /**
     * Finds LCA using two-pointer path equalization technique.
     *
     * Algorithm:
     * 1. Start two pointers at p and q
     * 2. Move each pointer to its parent
     * 3. When a pointer reaches null (past root), redirect to OTHER node's start
     * 4. Both pointers will meet at LCA after traveling equal distances
     */
    public static EduTreeNode LowestCommonAncestor(EduTreeNode p, EduTreeNode q) {

        EduTreeNode ptr1 = p;  // Start at node p (e.g., node 0)
        EduTreeNode ptr2 = q;  // Start at node q (e.g., node 5)

        // Keep moving until both pointers meet at LCA
        while (ptr1 != ptr2) {

            // ptr1: Move to parent, or switch to q's start if past root
            if (ptr1.parent != null) {
                ptr1 = ptr1.parent;      // Normal: move up the tree
            } else {
                ptr1 = q;                // Switch: redirect to q's starting point
            }

            // ptr2: Move to parent, or switch to p's start if past root
            if (ptr2.parent != null) {
                ptr2 = ptr2.parent;      // Normal: move up the tree
            } else {
                ptr2 = p;                // Switch: redirect to p's starting point
            }
        }

        // Both pointers now point to LCA
        return ptr1;
    }
}
class EduTreeNode {
    int data;
    EduTreeNode left;
    EduTreeNode right;
    EduTreeNode parent;

    EduTreeNode(int value) {
        this.data = value;
        this.left = null;
        this.right = null;
        this.parent = null;
    }
}
class EduBinaryTree {
    private EduTreeNode root;

    private EduTreeNode createBinaryTree(List<Integer> nodes) {
        if (nodes.isEmpty() || nodes.get(0) == 0) {
            return null;
        }
        EduTreeNode root = new EduTreeNode(nodes.get(0));
        Queue<EduTreeNode> q = new LinkedList<>();
        q.offer(root);
        int i = 1;
        while (i < nodes.size()) {
            EduTreeNode curr = q.poll();
            if (i < nodes.size() && nodes.get(i) != 0) {
                curr.left = new EduTreeNode(nodes.get(i));
                curr.left.parent = curr;
                q.offer(curr.left);
            }
            i++;
            if (i < nodes.size() && nodes.get(i) != 0) {
                curr.right = new EduTreeNode(nodes.get(i));
                curr.right.parent = curr;
                q.offer(curr.right);
            }
            i++;
        }
        return root;
    }

    public EduBinaryTree(List<Integer> nodes) {
        this.root = createBinaryTree(nodes);
    }

    public EduTreeNode find(EduTreeNode root, int value) {
        if (root == null) {
            return null;
        }
        Queue<EduTreeNode> q = new LinkedList<>();
        q.offer(root);
        while (!q.isEmpty()) {
            EduTreeNode currentNode = q.poll();
            if (currentNode.data == value) {
                return currentNode;
            }
            if (currentNode.left != null) {
                q.offer(currentNode.left);
            }
            if (currentNode.right != null) {
                q.offer(currentNode.right);
            }
        }
        return null;
    }

    public EduTreeNode getRoot() {
        return root;
    }
}
