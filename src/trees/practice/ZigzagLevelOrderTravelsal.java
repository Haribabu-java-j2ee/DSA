package trees.practice;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;

/**
 * ============================================================================
 * ZIGZAG (SPIRAL) LEVEL ORDER TRAVERSAL
 * ============================================================================
 * 
 * PROBLEM STATEMENT:
 * ------------------
 * Given a binary tree, return the zigzag level order traversal of its nodes.
 * (i.e., from left to right, then right to left for the next level, alternating)
 * 
 * INTUITION:
 * ----------
 * Same as regular level order traversal, but:
 * - Even levels (0, 2, 4...): Left to Right
 * - Odd levels (1, 3, 5...): Right to Left
 * 
 * TWO APPROACHES:
 * 1. BFS + Reverse odd levels (simpler)
 * 2. BFS with alternating insertion order
 * 
 * Approach 1 is shown here (simpler to understand and implement).
 * 
 * VISUAL REPRESENTATION:
 * ----------------------
 * Tree:
 *         0
 *        / \
 *       1   2
 *      / \
 *     3   4
 * 
 * Regular level order: [[0], [1, 2], [3, 4]]
 * 
 * Zigzag:
 * - Level 0 (even): [0]       → Left to Right
 * - Level 1 (odd):  [2, 1]    → Right to Left (reversed!)
 * - Level 2 (even): [3, 4]    → Left to Right
 * 
 * Result: [[0], [2, 1], [3, 4]]
 * 
 * TIME COMPLEXITY:
 * ----------------
 * O(n) - visit each node once
 * Reversing takes O(level size), but total reversing is O(n/2) = O(n)
 * 
 * SPACE COMPLEXITY:
 * -----------------
 * O(w) for queue where w = maximum width
 * O(n) for result storage
 * 
 * DRY RUN:
 * --------
 * Tree:
 *         0
 *        / \
 *       1   2
 *      / \
 *     3   4
 * 
 * | Level | Queue After | Collected | After Zigzag | Result        |
 * |-------|-------------|-----------|--------------|---------------|
 * | 0     | [1, 2]      | [0]       | [0] (even)   | [[0]]         |
 * | 1     | [3, 4]      | [1, 2]    | [2, 1] (odd) | [[0],[2,1]]   |
 * | 2     | []          | [3, 4]    | [3, 4] (even)| [[0],[2,1],[3,4]] |
 * 
 * Final: [[0], [2, 1], [3, 4]] ✓
 * 
 * ALTERNATIVE APPROACH (Deque):
 * -----------------------------
 * Use a deque and alternate between:
 * - Adding to end for even levels
 * - Adding to front for odd levels
 * This avoids the reverse step but is more complex to implement.
 */
public class ZigzagLevelOrderTravelsal {
    
    public static void main(String[] args) {
        // Build tree
        BinaryTreeNode root = new BinaryTreeNode(0);
        root.left = new BinaryTreeNode(1);
        root.right = new BinaryTreeNode(2);
        root.left.left = new BinaryTreeNode(3);
        root.left.right = new BinaryTreeNode(4);
        
        System.out.println("Zigzag level order:");
        zigzag_level_order_traversal(root).forEach(System.out::println);
    }

    /**
     * ZIGZAG LEVEL ORDER TRAVERSAL
     * 
     * Algorithm:
     * 1. Standard BFS level order traversal
     * 2. Track current level number
     * 3. Reverse the level list for odd-numbered levels
     * 
     * Time Complexity: O(n)
     * Space Complexity: O(n)
     * 
     * @param root Root of tree
     * @return Zigzag level order traversal
     */
    static ArrayList<ArrayList<Integer>> zigzag_level_order_traversal(BinaryTreeNode root) {
        // Edge case: empty tree
        if (root == null) {
            return new ArrayList<>();
        }
        
        // BFS using queue
        Queue<BinaryTreeNode> queue = new LinkedList<>();
        queue.add(root);
        ArrayList<ArrayList<Integer>> result = new ArrayList<>();
        
        // Track current level (0 = even, 1 = odd, ...)
        int levelNumber = 0;
        
        while (!queue.isEmpty()) {
            // Collect all nodes at current level
            ArrayList<Integer> level = new ArrayList<>();
            int size = queue.size();
            
            for (int i = 0; i < size; i++) {
                // Dequeue and process
                BinaryTreeNode current = queue.poll();
                level.add(current.value);
                
                // Enqueue children (always left to right)
                if (current.left != null) {
                    queue.add(current.left);
                }
                if (current.right != null) {
                    queue.add(current.right);
                }
            }
            
            // ZIGZAG LOGIC:
            // Even levels (0, 2, 4...): Keep as is (left to right)
            // Odd levels (1, 3, 5...): Reverse (right to left)
            if (levelNumber % 2 == 0) {
                result.add(level);  // Even: no change
            } else {
                Collections.reverse(level);  // Odd: reverse
                result.add(level);
            }
            
            levelNumber++;
        }
        
        return result;
    }
    
    /**
     * ALTERNATIVE: Using LinkedList for O(1) front insertion
     * 
     * Instead of reversing, add to front for odd levels.
     * 
     * @param root Root of tree
     * @return Zigzag level order traversal
     */
    static ArrayList<ArrayList<Integer>> zigzag_alternative(BinaryTreeNode root) {
        if (root == null) return new ArrayList<>();
        
        Queue<BinaryTreeNode> queue = new LinkedList<>();
        queue.add(root);
        ArrayList<ArrayList<Integer>> result = new ArrayList<>();
        boolean leftToRight = true;
        
        while (!queue.isEmpty()) {
            LinkedList<Integer> level = new LinkedList<>();  // Use LinkedList!
            int size = queue.size();
            
            for (int i = 0; i < size; i++) {
                BinaryTreeNode current = queue.poll();
                
                // Add based on direction
                if (leftToRight) {
                    level.addLast(current.value);   // Add to end
                } else {
                    level.addFirst(current.value);  // Add to front
                }
                
                if (current.left != null) queue.add(current.left);
                if (current.right != null) queue.add(current.right);
            }
            
            result.add(new ArrayList<>(level));
            leftToRight = !leftToRight;  // Toggle direction
        }
        
        return result;
    }
}
