package dsapatterns.fastslowpointer;

/**
 * =====================================================================================
 * REMOVE LINKED LIST CYCLE - Floyd's Algorithm + Cycle Removal
 * =====================================================================================
 * GFG: https://www.geeksforgeeks.org/dsa/detect-and-remove-loop-in-a-linked-list/
 * Educative: https://www.educative.io/interview-prep/coding/linked-list-cycle-iv
 *
 * =====================================================================================
 * INTUITION:
 * =====================================================================================
 * Three phases:
 *   Phase 1: Detect cycle using Floyd's (slow/fast pointers meet inside cycle)
 *   Phase 2: Find cycle start (move one pointer to head, both move 1 step until meet)
 *   Phase 3: Find last node of cycle (traverse cycle until reaching node before start)
 *            Set last.next = null to break the cycle
 *
 * =====================================================================================
 * EXAMPLE: 3 → 2 → 0 → -4 → (back to 2) - 100% CODE COVERAGE
 * =====================================================================================
 *
 *   List structure:
 *       3 → 2 → 0 → -4
 *           ↑________↓
 *
 * ┌─────────────────────────────────────────────────────────────────────────────────┐
 * │ PHASE 1: Detect Cycle (Floyd's Tortoise and Hare)                               │
 * ├─────────────────────────────────────────────────────────────────────────────────┤
 * │ Initial: slow = 3, fast = 3                                                     │
 * │                                                                                 │
 * │ Step 1: slow = slow.next = 2                                                    │
 * │         fast = fast.next.next = 0                                               │
 * │         slow(2) != fast(0), continue                                            │
 * │                                                                                 │
 * │ Step 2: slow = slow.next = 0                                                    │
 * │         fast = fast.next.next = 2  (wrapped: 0→-4→2)                            │
 * │         slow(0) != fast(2), continue                                            │
 * │                                                                                 │
 * │ Step 3: slow = slow.next = -4                                                   │
 * │         fast = fast.next.next = -4  (wrapped: 2→0→-4)                           │
 * │         slow(-4) == fast(-4) ✓ CYCLE DETECTED! Break.                           │
 * │                                                                                 │
 * │ Meeting point: node with value -4                                               │
 * └─────────────────────────────────────────────────────────────────────────────────┘
 *
 * ┌─────────────────────────────────────────────────────────────────────────────────┐
 * │ CHECK: fast != null && fast.next != null?                                       │
 * ├─────────────────────────────────────────────────────────────────────────────────┤
 * │ fast = -4 (not null), fast.next = 2 (not null) → Cycle exists, continue         │
 * │ If this check fails → No cycle, return head as-is                               │
 * └─────────────────────────────────────────────────────────────────────────────────┘
 *
 * ┌─────────────────────────────────────────────────────────────────────────────────┐
 * │ PHASE 2: Find Cycle Start                                                       │
 * ├─────────────────────────────────────────────────────────────────────────────────┤
 * │ Reset: slow = head = 3, fast = -4 (meeting point)                               │
 * │                                                                                 │
 * │ Step 1: slow(3) != fast(-4)                                                     │
 * │         slow = slow.next = 2                                                    │
 * │         fast = fast.next = 2                                                    │
 * │                                                                                 │
 * │ Step 2: slow(2) == fast(2) ✓ CYCLE START FOUND!                                 │
 * │                                                                                 │
 * │ Cycle starts at node with value 2                                               │
 * └─────────────────────────────────────────────────────────────────────────────────┘
 *
 * ┌─────────────────────────────────────────────────────────────────────────────────┐
 * │ PHASE 3: Find Last Node & Remove Cycle                                          │
 * ├─────────────────────────────────────────────────────────────────────────────────┤
 * │ Now: slow = 2 (cycle start), fast = 2 (cycle start)                             │
 * │ Goal: Find node whose .next == slow (cycle start)                               │
 * │                                                                                 │
 * │ Step 1: fast.next(0) != slow(2), fast = fast.next = 0                           │
 * │ Step 2: fast.next(-4) != slow(2), fast = fast.next = -4                         │
 * │ Step 3: fast.next(2) == slow(2) ✓ LAST NODE FOUND!                              │
 * │                                                                                 │
 * │ fast = -4, fast.next = 2 (points back to cycle start)                           │
 * │ Set fast.next = null → Cycle removed!                                           │
 * │                                                                                 │
 * │ Final list: 3 → 2 → 0 → -4 → null                                               │
 * └─────────────────────────────────────────────────────────────────────────────────┘
 *
 * =====================================================================================
 * TEST CASES FOR 100% COVERAGE:
 * =====================================================================================
 *
 *   Test 1: Normal cycle (covers all 3 phases)
 *       Input:  3 → 2 → 0 → -4 → (back to 2)
 *       Output: 3 → 2 → 0 → -4 → null
 *
 *   Test 2: No cycle (covers early return at line 29-31)
 *       Input:  1 → 2 → 3 → null
 *       Output: 1 → 2 → 3 → null (unchanged)
 *
 *   Test 3: Null/single node (covers early return at line 17-19)
 *       Input:  null OR single node
 *       Output: same as input
 *
 *   Test 4: Cycle at head (covers edge case where slow==fast immediately in phase 2)
 *       Input:  1 → 2 → 3 → (back to 1)
 *       Output: 1 → 2 → 3 → null
 *
 *   Test 5: Two-node cycle
 *       Input:  1 → 2 → (back to 1)
 *       Output: 1 → 2 → null
 *
 * =====================================================================================
 * COMPLEXITY:
 * =====================================================================================
 *   Time:  O(n) - Each phase traverses at most n nodes
 *   Space: O(1) - Only pointer variables
 *
 * =====================================================================================
 * EDGE CASES:
 * =====================================================================================
 *   1. Null head → return null immediately
 *   2. Single node no cycle → return head
 *   3. Single node self-loop → handled by phase 3
 *   4. Cycle starts at head → phase 2 returns immediately
 *   5. No cycle exists → detected by fast==null check, return head
 */
public class RemoveLinkedListCycle {
    public static void main(String[] args) {
        RemoveLinkedListCycle obj = new RemoveLinkedListCycle();
        
        // Test 1: Normal cycle - 3 → 2 → 0 → -4 → (back to 2)
        System.out.println("Test 1: Normal cycle");
        ListNode head = new ListNode(3);
        head.next = new ListNode(2);
        head.next.next = new ListNode(0);
        head.next.next.next = new ListNode(-4);
        head.next.next.next.next = head.next;  // cycle to node 2
        obj.removeCycle(head);
        printList(head);  // Expected: 3 → 2 → 0 → -4 → null
        
        // Test 2: No cycle
        System.out.println("Test 2: No cycle");
        ListNode head2 = new ListNode(1);
        head2.next = new ListNode(2);
        head2.next.next = new ListNode(3);
        obj.removeCycle(head2);
        printList(head2);  // Expected: 1 → 2 → 3 → null
        
        // Test 3: Null head
        System.out.println("Test 3: Null head");
        System.out.println(obj.removeCycle(null));  // Expected: null
        
        // Test 4: Cycle at head - 1 → 2 → 3 → (back to 1)
        System.out.println("Test 4: Cycle at head");
        ListNode head4 = new ListNode(1);
        head4.next = new ListNode(2);
        head4.next.next = new ListNode(3);
        head4.next.next.next = head4;  // cycle to head
        obj.removeCycle(head4);
        printList(head4);  // Expected: 1 → 2 → 3 → null
    }
    
    private static void printList(ListNode head) {
        ListNode curr = head;
        int count = 0;
        while (curr != null && count < 10) {  // limit to prevent infinite loop if bug
            System.out.print(curr.val + " → ");
            curr = curr.next;
            count++;
        }
        System.out.println("null");
    }
    
    /**
     * Removes cycle from linked list if present.
     * 
     * Algorithm:
     *   Phase 1: Detect cycle using Floyd's algorithm
     *   Phase 2: Find cycle start node
     *   Phase 3: Find last node in cycle and break the link
     */
    public ListNode removeCycle(ListNode head) {
        // =====================================================================
        // EDGE CASE: Empty list or single node cannot have a meaningful cycle
        // =====================================================================
        if (head == null || head.next == null) {
            return head;
        }
        
        // =====================================================================
        // PHASE 1: Detect cycle using Floyd's Tortoise and Hare
        // =====================================================================
        // slow moves 1 step, fast moves 2 steps
        // If cycle exists, they will meet inside the cycle
        ListNode slow = head;
        ListNode fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) {
                break;  // Cycle detected, exit to find cycle start
            }
        }
        
        // =====================================================================
        // CHECK: Did we exit because of cycle or end of list?
        // =====================================================================
        // If fast reached null, there's no cycle
        if (fast == null || fast.next == null) {
            return head;  // No cycle, return as-is
        }
        
        // =====================================================================
        // PHASE 2: Find cycle start node
        // =====================================================================
        // Mathematical property: Distance from head to cycle start equals
        // distance from meeting point to cycle start (moving in cycle direction)
        slow = head;
        while (slow != fast) {
            slow = slow.next;
            fast = fast.next;
        }
        // Now slow (and fast) point to cycle start
        
        // =====================================================================
        // PHASE 3: Find last node in cycle and break the link
        // =====================================================================
        // Traverse cycle until we find node whose .next is cycle start (slow)
        while (fast.next != slow) {
            fast = fast.next;
        }
        // fast is now the last node in cycle, pointing back to cycle start
        fast.next = null;  // Break the cycle!
        
        return head;
    }
}
