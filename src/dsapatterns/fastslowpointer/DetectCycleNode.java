package dsapatterns.fastslowpointer;

/**
 * =====================================================================================
 * DETECT CYCLE START NODE - Floyd's Algorithm Phase 2
 * =====================================================================================
 * LeetCode: https://leetcode.com/problems/linked-list-cycle-ii/
 *
 * =====================================================================================
 * INTUITION:
 * =====================================================================================
 * Phase 1: Find meeting point using slow/fast pointers.
 * Phase 2: Move one pointer to head, both move 1 step. They meet at cycle start.
 * Math: If distance to cycle start = x, meeting point is x steps into cycle.
 *
 * =====================================================================================
 * EXAMPLE: 3 → 1 → 0 → -4 → (back to 1) → returns node with value 1
 * =====================================================================================
 *
 *   Phase 1 - Find meeting point:
 *     slow: 3→1→0→-4→1→0
 *     fast: 3→0→1→-4→0→-4
 *     Meet at node 0
 *   
 *   Phase 2 - Find cycle start:
 *     head: 3→1
 *     slow: 0→-4→1
 *     Meet at node 1 → CYCLE START
 *
 * =====================================================================================
 * COMPLEXITY:
 * =====================================================================================
 *   Time:  O(n) - Two traversals of the list
 *   Space: O(1) - Only pointers
 *
 * =====================================================================================
 * EDGE CASES:
 * =====================================================================================
 *   1. No cycle → fast reaches null, return null
 *   2. Cycle at head → head == slow after phase 2, return head immediately
 */
public class DetectCycleNode {
    public static void main(String[] args) {
        DetectCycleNode obj = new DetectCycleNode();
        ListNode head = new ListNode(3);
        head.next = new ListNode(1);
        head.next.next = new ListNode(0);
        head.next.next.next = new ListNode(-4);
        //cycle
        head.next.next.next.next = head.next;
        System.out.println(obj.detectCycle(head).val);
    }
    public ListNode detectCycle(ListNode head) {
        ListNode slow=head, fast=head;
        while(fast!=null && fast.next!=null){
            slow=slow.next;
            fast=fast.next.next;
            if(slow==fast){
                break;
            }
        }

        if(fast==null || fast.next==null){
            return null;
        }
        while(head!=slow){
            slow=slow.next;
            head=head.next;
        }
        return head;
    }
}
