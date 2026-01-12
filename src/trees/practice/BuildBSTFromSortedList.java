package trees.practice;

import java.util.ArrayList;

/**
 * ============================================================================
 * BUILD BST FROM SORTED LIST - Convert sorted linked list to balanced BST
 * ============================================================================
 * 
 * PROBLEM STATEMENT:
 * ------------------
 * Given a sorted singly linked list, convert it to a height-balanced BST.
 * A height-balanced BST has the property that for every node, the heights
 * of left and right subtrees differ by at most 1.
 * 
 * INTUITION:
 * ----------
 * To create a balanced BST from sorted data:
 * 1. Find the MIDDLE element → becomes ROOT
 * 2. Elements before middle → LEFT subtree
 * 3. Elements after middle → RIGHT subtree
 * 4. Recursively apply to sublists
 * 
 * This is similar to how binary search works!
 * 
 * TWO APPROACHES:
 * ---------------
 * 
 * APPROACH 1: Find middle each time (O(n log n))
 * - Use slow-fast pointer to find middle
 * - Recursively build left and right subtrees
 * - Finding middle takes O(n) per level, log n levels = O(n log n)
 * 
 * APPROACH 2: Convert to array first (O(n))
 * - Convert linked list to array: O(n)
 * - Build BST from array using indices: O(n)
 * - Array allows O(1) access to middle element
 * 
 * VISUAL REPRESENTATION:
 * ----------------------
 * Sorted List: -1 → 2 → 3 → 5 → 6 → 7 → 10
 * 
 * Step 1: Find middle (5)
 *         5 becomes root
 *         
 *            5
 *           / \
 *          ?   ?
 * 
 * Step 2: Left sublist [-1, 2, 3], middle is 2
 *         Right sublist [6, 7, 10], middle is 7
 * 
 *            5
 *           / \
 *          2   7
 *         / \ / \
 *        ?  ? ?  ?
 * 
 * Step 3: Continue recursively...
 * 
 * Final balanced BST:
 *            5
 *           / \
 *          2   7
 *         / \ / \
 *       -1  3 6  10
 * 
 * TIME COMPLEXITY:
 * ----------------
 * Approach 1 (Find middle): O(n log n)
 *   - Finding middle: O(n/2) at each level
 *   - Number of levels: O(log n)
 *   - Total: O(n log n)
 * 
 * Approach 2 (Array conversion): O(n)
 *   - Convert to array: O(n)
 *   - Build BST: O(n) - each element processed once
 *   - Total: O(n)
 * 
 * SPACE COMPLEXITY:
 * -----------------
 * Approach 1: O(log n) for recursion stack (balanced tree)
 * Approach 2: O(n) for array + O(log n) for recursion = O(n)
 * 
 * DRY RUN (Approach 2):
 * ---------------------
 * Array: [-1, 2, 3, 5, 6, 7, 10]
 * 
 * | Call                | low | high | mid | Node Created |
 * |---------------------|-----|------|-----|--------------|
 * | build(0, 6)         | 0   | 6    | 3   | 5 (root)     |
 * | build(0, 2)         | 0   | 2    | 1   | 2            |
 * | build(0, 0)         | 0   | 0    | 0   | -1           |
 * | build(2, 2)         | 2   | 2    | 2   | 3            |
 * | build(4, 6)         | 4   | 6    | 5   | 7            |
 * | build(4, 4)         | 4   | 4    | 4   | 6            |
 * | build(6, 6)         | 6   | 6    | 6   | 10           |
 */
public class BuildBSTFromSortedList {
    
    public static void main(String[] args) {
        // Build sorted linked list: -1 → 2 → 3 → 5 → 6 → 7 → 10
        LinkedListNode head = new LinkedListNode(-1);
        head.next = new LinkedListNode(2);
        head.next.next = new LinkedListNode(3);
        head.next.next.next = new LinkedListNode(5);
        head.next.next.next.next = new LinkedListNode(6);
        head.next.next.next.next.next = new LinkedListNode(7);
        head.next.next.next.next.next.next = new LinkedListNode(10);
        
        // Approach 1: Find middle method
        System.out.println("Approach 1 (Find Middle):");
        System.out.println(sorted_list_to_bst(head));
        
        // Rebuild list for second approach
        head = new LinkedListNode(-1);
        head.next = new LinkedListNode(2);
        head.next.next = new LinkedListNode(3);
        head.next.next.next = new LinkedListNode(5);
        head.next.next.next.next = new LinkedListNode(6);
        head.next.next.next.next.next = new LinkedListNode(7);
        head.next.next.next.next.next.next = new LinkedListNode(10);
        
        // Approach 2: Array conversion method
        System.out.println("Approach 2 (Array Conversion):");
        BinaryTreeNode root = sorted_list_to_bst1(head);
        System.out.println(root);
    }

    // ═══════════════════════════════════════════════════════════════════════
    // APPROACH 1: Find middle each time - O(n log n)
    // ═══════════════════════════════════════════════════════════════════════
    
    /**
     * Convert sorted list to BST by finding middle element each time.
     * 
     * Time Complexity: O(n log n)
     *   - log n levels in the tree
     *   - At each level, finding middle takes O(n/2) total
     * 
     * Space Complexity: O(log n) for recursion stack
     * 
     * @param head Head of sorted linked list
     * @return Root of balanced BST
     */
    static BinaryTreeNode sorted_list_to_bst(LinkedListNode head) {
        // Base case: empty list
        if (head == null) {
            return null;
        }

        // Base case: single node
        if (head.next == null) {
            return new BinaryTreeNode(head.value);
        }
        
        // Step 1: Find middle node (will become root)
        LinkedListNode middle_node = get_middle(head);
        BinaryTreeNode root = new BinaryTreeNode(middle_node.value);
        
        // Step 2: Set up pointers for left and right sublists
        LinkedListNode head_left = head;              // Left sublist starts from head
        LinkedListNode head_right = middle_node.next; // Right sublist starts after middle
        
        // Step 3: Disconnect at middle (list is split in get_middle)
        middle_node.next = null;
        
        // Step 4: Recursively build left and right subtrees
        root.left = sorted_list_to_bst(head_left);    // Build from elements before middle
        root.right = sorted_list_to_bst(head_right);  // Build from elements after middle
        
        return root;
    }

    /**
     * Find middle node and split list.
     * Uses slow-fast pointer technique.
     * 
     * After this function:
     * - Returns middle node
     * - List is split: everything before middle is disconnected
     * 
     * @param head Head of linked list
     * @return Middle node
     */
    static LinkedListNode get_middle(LinkedListNode head) {
        // Edge cases
        if (head == null || head.next == null) {
            return head;
        }
        
        LinkedListNode slow_prev = head;  // Node before slow (to split list)
        LinkedListNode slow = head;        // Will be at middle when fast reaches end
        LinkedListNode fast = head;        // Moves 2x speed of slow

        // Move fast 2 steps, slow 1 step until fast reaches end
        while (fast != null && fast.next != null) {
            slow_prev = slow;       // Track node before slow
            slow = slow.next;       // Move slow by 1
            fast = fast.next.next;  // Move fast by 2
        }

        // Split list: disconnect before slow (middle)
        slow_prev.next = null;
        
        return slow;  // Return middle node
    }

    // ═══════════════════════════════════════════════════════════════════════
    // APPROACH 2: Array conversion - O(n)
    // ═══════════════════════════════════════════════════════════════════════
    
    /**
     * Convert sorted list to BST by first converting to array.
     * 
     * Time Complexity: O(n)
     *   - Convert to array: O(n)
     *   - Build BST: O(n)
     * 
     * Space Complexity: O(n) for array storage
     * 
     * @param head Head of sorted linked list
     * @return Root of balanced BST
     */
    static BinaryTreeNode sorted_list_to_bst1(LinkedListNode head) {
        // Edge cases
        if (head == null) {
            return null;
        }
        if (head.next == null) {
            return new BinaryTreeNode(head.value);
        }
        
        // Step 1: Convert linked list to ArrayList for O(1) access
        ArrayList<Integer> list = new ArrayList<>();
        while (head != null) {
            list.add(head.value);
            head = head.next;
        }
        
        // Step 2: Build BST from array using divide and conquer
        BinaryTreeNode root = build_tree_util(list, 0, list.size() - 1);
        return root;
    }

    /**
     * Build balanced BST from sorted array using divide and conquer.
     * 
     * Algorithm:
     * 1. Pick middle element as root
     * 2. Recursively build left subtree from left half
     * 3. Recursively build right subtree from right half
     * 
     * @param list Sorted array
     * @param low Start index (inclusive)
     * @param high End index (inclusive)
     * @return Root of subtree
     */
    private static BinaryTreeNode build_tree_util(ArrayList<Integer> list, int low, int high) {
        // Base case: invalid range
        if (low > high) {
            return null;
        }
        
        // Base case: single element
        if (low == high) {
            return new BinaryTreeNode(list.get(low));
        }
        
        // Step 1: Find middle index (root of this subtree)
        int mid = low + (high - low) / 2;
        
        // Step 2: Create root node with middle element
        BinaryTreeNode root = new BinaryTreeNode(list.get(mid));
        
        // Step 3: Recursively build left subtree (elements before mid)
        root.left = build_tree_util(list, low, mid - 1);
        
        // Step 4: Recursively build right subtree (elements after mid)
        root.right = build_tree_util(list, mid + 1, high);
        
        return root;
    }
}

/**
 * LINKED LIST NODE CLASS
 */
class LinkedListNode {
    Integer value;       // Node value
    LinkedListNode next; // Pointer to next node

    LinkedListNode(Integer value) {
        this.value = value;
        this.next = null;
    }
}
