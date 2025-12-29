package dsapatterns.fastslowpointer;

/**
 * =====================================================================================
 * MIDDLE OF LINKED LIST - Fast/Slow Pointer
 * =====================================================================================
 * LeetCode: https://leetcode.com/problems/middle-of-the-linked-list/
 *
 * =====================================================================================
 * INTUITION:
 * =====================================================================================
 * Fast moves 2 steps while slow moves 1 step. When fast reaches end, slow is at middle.
 * For even length, returns second middle node.
 *
 * =====================================================================================
 * EXAMPLE: 1 → 2 → 3 → 4 → 5 → returns node 3
 * =====================================================================================
 *
 *   Step | slow | fast
 *   -----|------|------
 *   init |  1   |  1
 *    1   |  2   |  3
 *    2   |  3   |  5
 *   
 *   fast.next = null → STOP, return slow (node 3)
 *
 * =====================================================================================
 * EXAMPLE: 1 → 2 → 3 → 4 → 5 → 6 → returns node 4 (second middle)
 * =====================================================================================
 *
 *   Step | slow | fast
 *   -----|------|------
 *   init |  1   |  1
 *    1   |  2   |  3
 *    2   |  3   |  5
 *    3   |  4   |  null (fast.next.next)
 *   
 *   fast = null → STOP, return slow (node 4)
 *
 * =====================================================================================
 * COMPLEXITY:
 * =====================================================================================
 *   Time:  O(n) - Single pass, n/2 iterations
 *   Space: O(1) - Only two pointers
 *
 * =====================================================================================
 * EDGE CASES:
 * =====================================================================================
 *   1. Single node → fast.next = null, return head
 *   2. Two nodes → returns second node (second middle)
 */
public class MiddleOfLinkedList {
    public static void main(String[] args) {
        MiddleOfLinkedList obj = new MiddleOfLinkedList();
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        System.out.println(obj.middleNode(head).val);
    }
    public ListNode middleNode(ListNode head) {
        ListNode slow=head;
        ListNode fast=head;
        while(fast!=null && fast.next!=null){
            slow=slow.next;
            fast=fast.next.next;
        }
        return slow;
    }

}
