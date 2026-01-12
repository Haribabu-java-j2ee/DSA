package trees.practice;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Stack;

/**
 * ============================================================================
 * INORDER ITERATOR - Implement BST Iterator with next() and has_next()
 * ============================================================================
 * 
 * PROBLEM STATEMENT:
 * ------------------
 * Implement an iterator over a BST that supports:
 * - next(): Returns the next smallest element
 * - has_next(): Returns true if there are more elements
 * 
 * Elements should be returned in INORDER sequence (ascending order for BST).
 * 
 * INTUITION:
 * ----------
 * TWO APPROACHES:
 * 
 * APPROACH 1: Precompute (shown here)
 * - Do full inorder traversal upfront
 * - Store all elements in a list
 * - next() and has_next() use the list
 * - Simple but uses O(n) extra space
 * 
 * APPROACH 2: On-demand with Stack (more space efficient)
 * - Use stack to simulate recursion
 * - Push all left children initially
 * - On next(): pop, process, push right subtree's left children
 * - Uses O(h) space where h = height
 * 
 * ITERATIVE INORDER TRAVERSAL:
 * ----------------------------
 * Using a stack to simulate recursion:
 * 1. Go left as far as possible, pushing nodes onto stack
 * 2. Pop a node, process it (add to result)
 * 3. Go to right child and repeat step 1
 * 
 * VISUAL REPRESENTATION:
 * ----------------------
 * Tree:
 *      200
 *     /   \
 *   100   300
 * 
 * Inorder: [100, 200, 300]
 * 
 * Operations: ["next", "has_next", "next", "next", "has_next", "has_next", "next"]
 * 
 * | Operation | Action                    | Output |
 * |-----------|---------------------------|--------|
 * | next      | Return 100, advance index | 100    |
 * | has_next  | Index < size? Yes         | 1      |
 * | next      | Return 200, advance index | 200    |
 * | next      | Return 300, advance index | 300    |
 * | has_next  | Index < size? No          | 0      |
 * | has_next  | Index < size? No          | 0      |
 * | next      | No more elements          | 0      |
 * 
 * TIME COMPLEXITY:
 * ----------------
 * Initialization: O(n) - full traversal
 * next(): O(1) - just index access
 * has_next(): O(1) - just comparison
 * 
 * SPACE COMPLEXITY:
 * -----------------
 * O(n) for storing all elements
 * 
 * DRY RUN (Iterative Inorder):
 * ----------------------------
 * Tree:
 *      200
 *     /   \
 *   100   300
 * 
 * | Step | Stack State | Current | Action           | Result List |
 * |------|-------------|---------|------------------|-------------|
 * | 1    | []          | 200     | Push, go left    | []          |
 * | 2    | [200]       | 100     | Push, go left    | []          |
 * | 3    | [200,100]   | null    | Pop 100, process | [100]       |
 * | 4    | [200]       | null    | 100.right=null   | [100]       |
 * | 5    | [200]       | null    | Pop 200, process | [100,200]   |
 * | 6    | []          | 300     | Push, go left    | [100,200]   |
 * | 7    | [300]       | null    | Pop 300, process | [100,200,300]|
 * | 8    | []          | null    | Done!            | [100,200,300]|
 */
public class InOrderIterativeWithTweaks {
    
    public static void main(String[] args) {
        // Build tree
        BinaryTreeNode root = new BinaryTreeNode(200);
        root.left = new BinaryTreeNode(100);
        root.right = new BinaryTreeNode(300);
        
        // Define operations
        String[] str = {"next", "has_next", "next", "next", "has_next", "has_next", "next"};
        ArrayList<String> operations = new ArrayList<>();
        Arrays.stream(str).forEach(operations::add);
        
        // Execute operations
        System.out.println("Results:");
        implement_tree_iterator(root, operations).forEach(System.out::println);
    }
    
    /**
     * IMPLEMENT BST ITERATOR
     * 
     * This implementation precomputes the full inorder traversal,
     * then processes operations using the precomputed list.
     * 
     * Time Complexity:
     * - Initialization: O(n)
     * - Each operation: O(1)
     * 
     * Space Complexity: O(n) for storing traversal
     * 
     * @param root Root of BST
     * @param operations List of "next" and "has_next" operations
     * @return Results of each operation
     */
    static ArrayList<Integer> implement_tree_iterator(BinaryTreeNode root, ArrayList<String> operations) {
        ArrayList<Integer> result = new ArrayList<>();
        ArrayList<Integer> inorderList = new ArrayList<>();
        
        // ═══════════════════════════════════════════════════════════════════
        // ITERATIVE INORDER TRAVERSAL using Stack
        // ═══════════════════════════════════════════════════════════════════
        BinaryTreeNode current = root;
        Stack<BinaryTreeNode> stack = new Stack<>();
        
        // Process all nodes
        while (current != null || !stack.isEmpty()) {
            // Step 1: Go left as far as possible, pushing onto stack
            while (current != null) {
                stack.push(current);
                current = current.left;
            }
            
            // Step 2: Pop and process the node
            current = stack.pop();
            inorderList.add(current.value);  // This is the "visit" step
            
            // Step 3: Move to right subtree (if exists, will be processed next)
            current = current.right;
        }

        // ═══════════════════════════════════════════════════════════════════
        // PROCESS OPERATIONS
        // ═══════════════════════════════════════════════════════════════════
        int n = inorderList.size();
        int currentIndex = 0;  // Track current position
        
        for (String operation : operations) {
            if (currentIndex < n) {
                if (operation.equals("next")) {
                    // Return current element and advance
                    result.add(inorderList.get(currentIndex));
                    currentIndex++;
                } else if (operation.equals("has_next")) {
                    // Check if there are more elements
                    result.add(1);  // 1 = true
                }
            } else {
                // No more elements
                result.add(0);  // 0 = false / no element
            }
        }
        
        return result;
    }
}
