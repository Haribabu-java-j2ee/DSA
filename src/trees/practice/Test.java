package trees.practice;

/**
 * ============================================================================
 * TEST FILE - Max Depth of N-ary Tree
 * ============================================================================
 * 
 * PROBLEM STATEMENT:
 * ------------------
 * Find the maximum depth (height) of an n-ary tree.
 * 
 * DEFINITION:
 * -----------
 * The maximum depth is the number of nodes along the longest path 
 * from the root node down to the farthest leaf node.
 * 
 * This is identical to MaxHeightInNArrTree.java
 * See that file for detailed documentation.
 * 
 * TIME COMPLEXITY:
 * ----------------
 * O(n) - visit each node once
 * 
 * SPACE COMPLEXITY:
 * -----------------
 * O(h) for recursion stack where h = height
 */
public class Test {
    
    public static void main(String[] args) {
        // Build n-ary tree
        //          1
        //        / | \
        //       2  3  5
        //             |
        //             4
        TreeNode root = new TreeNode(1);
        
        TreeNode child1 = new TreeNode(2);
        TreeNode child2 = new TreeNode(3);
        TreeNode subchild = new TreeNode(4);
        
        TreeNode child3 = new TreeNode(5);
        child3.children.add(subchild);  // 5 has child 4
        
        root.children.add(child1);
        root.children.add(child2);
        root.children.add(child3);

        System.out.println("Max depth: " + maxDepth(root));  // Expected: 3
    }

    /**
     * FIND MAXIMUM DEPTH OF N-ARY TREE
     * 
     * Algorithm:
     * 1. If node is null, return 0
     * 2. Find max depth among all children
     * 3. Return 1 + max child depth
     * 
     * Base case: Leaf node → returns 1 (only itself)
     * 
     * @param root Current node
     * @return Depth of subtree rooted at this node
     */
    static int maxDepth(TreeNode root) {
        // Base case: null node has depth 0
        if (root == null) {
            return 0;
        }

        int depth = 0;

        // Recur for all children and find the maximum depth
        for (TreeNode child : root.children) {
            depth = Math.max(depth, maxDepth(child));
        }

        // Add 1 to include the current node in the depth count
        return depth + 1;
    }
}
