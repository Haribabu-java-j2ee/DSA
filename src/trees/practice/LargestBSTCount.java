package trees.practice;

/**
 * ============================================================================
 * LARGEST BST IN BINARY TREE - Find size of largest BST subtree
 * ============================================================================
 * 
 * PROBLEM STATEMENT:
 * ------------------
 * Given a binary tree, find the size (number of nodes) of the largest subtree 
 * that is also a Binary Search Tree (BST).
 * 
 * INTUITION:
 * ----------
 * Use POSTORDER traversal (bottom-up approach):
 * 
 * For each node, we need to know:
 * 1. Is the left subtree a valid BST?
 * 2. Is the right subtree a valid BST?
 * 3. What is the min value in subtree? (to validate right child)
 * 4. What is the max value in subtree? (to validate left child)
 * 5. What is the size of the subtree?
 * 
 * A subtree rooted at current node is a BST if:
 * - Left subtree is a BST AND max value in left < current node
 * - Right subtree is a BST AND min value in right > current node
 * 
 * WHY POSTORDER?
 * --------------
 * We need information from children before we can decide about parent.
 * Postorder processes children first, then parent.
 * 
 * VISUAL REPRESENTATION:
 * ----------------------
 *         100
 *        /   \
 *      300   500
 *      / \   / \
 *    200 400 600 700
 * 
 * Check node 300: left=200, right=400
 * - Is it BST? No! 200 < 300 < 400 looks fine locally,
 *   but we need to check all descendants.
 * - Actually, 300 > 200 and 300 < 400, so [200, 300, 400] is BST!
 * 
 * Check node 500: left=600, right=700
 * - Is it BST? No! 600 > 500 but 600 is in left subtree!
 * 
 * Check root 100:
 * - Left subtree is valid BST with size 3
 * - Right subtree is NOT a valid BST
 * - Root subtree is not a BST
 * 
 * Largest BST has size 3 (the left subtree)
 * 
 * TIME COMPLEXITY:
 * ----------------
 * O(n) - visit each node exactly once
 * 
 * SPACE COMPLEXITY:
 * -----------------
 * O(h) for recursion stack + O(1) for info per call
 * 
 * DRY RUN:
 * --------
 *         100
 *        /   \
 *      300   500
 *      / \   / \
 *    200 400 600 700
 * 
 * | Node | Left BST? | Right BST? | Current BST? | Size | Max BST Size |
 * |------|-----------|------------|--------------|------|--------------|
 * | 200  | null(yes) | null(yes)  | YES          | 1    | 1            |
 * | 400  | null(yes) | null(yes)  | YES          | 1    | 1            |
 * | 300  | yes,max=200| yes,min=400| 200<300<400 YES | 3    | 3            |
 * | 600  | null(yes) | null(yes)  | YES          | 1    | 1            |
 * | 700  | null(yes) | null(yes)  | YES          | 1    | 1            |
 * | 500  | yes,min=600| yes,min=700| 600>500 NO  | -    | 1            |
 * | 100  | yes,max=400| NO         | NO           | -    | 3            |
 * 
 * Largest BST size = 3 ✓
 */
public class LargestBSTCount {
    
    public static void main(String[] args) {
        // Build test tree
        BinaryTreeNode root = new BinaryTreeNode(100);
        root.left = new BinaryTreeNode(300);
        root.right = new BinaryTreeNode(500);
        root.left.left = new BinaryTreeNode(200);
        root.left.right = new BinaryTreeNode(400);
        root.right.left = new BinaryTreeNode(600);
        root.right.right = new BinaryTreeNode(700);
        
        System.out.println("Largest BST size: " + find_largest_bst(root));
    }

    /**
     * MAIN FUNCTION - Find largest BST subtree size
     * 
     * @param root Root of binary tree
     * @return Size of largest BST subtree
     */
    static Integer find_largest_bst(BinaryTreeNode root) {
        if (root == null) {
            return 0;
        }
        
        // Get info about tree
        BstInfo bstInfo = find_largest_bst_util(root);
        return bstInfo.maxSize;
    }

    /**
     * RECURSIVE HELPER - Returns BST info for subtree
     * 
     * Uses postorder traversal to get child info before processing parent.
     * 
     * @param root Current node
     * @return BstInfo containing min, max, isBst, size, maxSize
     */
    static BstInfo find_largest_bst_util(BinaryTreeNode root) {
        // Create info for current subtree
        BstInfo currentTreeInfo = new BstInfo();
        
        // Base case: null node is a valid BST with size 0
        if (root == null) {
            return currentTreeInfo;
        }

        // Case 1: LEAF NODE - always a valid BST
        if (root.left == null && root.right == null) {
            currentTreeInfo.mn = root.value;        // Min in subtree
            currentTreeInfo.mx = root.value;        // Max in subtree
            currentTreeInfo.maxSize = 1;            // Size = 1
            currentTreeInfo.size = 1;
            currentTreeInfo.isBst = true;
            return currentTreeInfo;
        }
        
        // Case 2: ONLY RIGHT CHILD exists
        if (root.left == null) {
            BstInfo rightTree = find_largest_bst_util(root.right);
            
            // Current subtree is BST if:
            // - Right subtree is BST
            // - Current value <= min in right subtree
            if (rightTree.isBst && root.value <= rightTree.mn) {
                currentTreeInfo.mn = root.value;              // Current is new min
                currentTreeInfo.mx = rightTree.mx;            // Right's max is max
                currentTreeInfo.isBst = true;
                currentTreeInfo.size = 1 + rightTree.size;
                currentTreeInfo.maxSize = Math.max(currentTreeInfo.size, rightTree.maxSize);
            } else {
                // Not a valid BST, but track max BST found in right subtree
                currentTreeInfo.isBst = false;
                currentTreeInfo.maxSize = rightTree.maxSize;
            }
            return currentTreeInfo;
        }
        
        // Case 3: ONLY LEFT CHILD exists
        if (root.right == null) {
            BstInfo leftTree = find_largest_bst_util(root.left);
            
            // Current subtree is BST if:
            // - Left subtree is BST
            // - Current value >= max in left subtree
            if (leftTree.isBst && root.value >= leftTree.mx) {
                currentTreeInfo.mn = leftTree.mn;             // Left's min is min
                currentTreeInfo.mx = root.value;              // Current is new max
                currentTreeInfo.isBst = true;
                currentTreeInfo.size = 1 + leftTree.size;
                currentTreeInfo.maxSize = Math.max(currentTreeInfo.size, leftTree.maxSize);
            } else {
                // Not a valid BST, but track max BST found in left subtree
                currentTreeInfo.isBst = false;
                currentTreeInfo.maxSize = leftTree.maxSize;
            }
            return currentTreeInfo;
        }
        
        // Case 4: BOTH CHILDREN exist
        BstInfo rightTree = find_largest_bst_util(root.right);
        BstInfo leftTree = find_largest_bst_util(root.left);
        
        // Current subtree is BST if:
        // - Both subtrees are BSTs
        // - Current value >= max in left subtree
        // - Current value <= min in right subtree
        if (rightTree.isBst && root.value <= rightTree.mn &&
            leftTree.isBst && root.value >= leftTree.mx) {
            
            currentTreeInfo.mn = leftTree.mn;             // Left's min is overall min
            currentTreeInfo.mx = rightTree.mx;            // Right's max is overall max
            currentTreeInfo.isBst = true;
            currentTreeInfo.size = 1 + leftTree.size + rightTree.size;
            currentTreeInfo.maxSize = Math.max(currentTreeInfo.size, 
                                      Math.max(leftTree.maxSize, rightTree.maxSize));
        } else {
            // Not a valid BST, but track max BST found in either subtree
            currentTreeInfo.isBst = false;
            currentTreeInfo.maxSize = Math.max(leftTree.maxSize, rightTree.maxSize);
        }
        
        return currentTreeInfo;
    }

    /**
     * HELPER CLASS - Stores BST information for a subtree
     */
    static class BstInfo {
        int mn = Integer.MIN_VALUE;   // Minimum value in subtree
        int mx = Integer.MAX_VALUE;   // Maximum value in subtree
        boolean isBst = true;          // Is this subtree a valid BST?
        int size;                      // Size of current subtree if BST
        int maxSize;                   // Size of largest BST found in subtree
    }
}
