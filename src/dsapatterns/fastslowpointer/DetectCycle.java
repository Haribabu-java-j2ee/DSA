package dsapatterns.fastslowpointer;

/**
 * =====================================================================================
 * DETECT CYCLE IN LINKED LIST - Floyd's Tortoise and Hare
 * =====================================================================================
 * LeetCode: https://leetcode.com/problems/linked-list-cycle/
 *
 * =====================================================================================
 * INTUITION:
 * =====================================================================================
 * Use two pointers: slow moves 1 step, fast moves 2 steps. If cycle exists, fast
 * will eventually catch up to slow inside the cycle. If no cycle, fast reaches null.
 *
 * =====================================================================================
 * EXAMPLE: 3 → 2 → 0 → -4 → (back to 2) → true
 * =====================================================================================
 *
 *   Initial: slow=3, fast=3
 *   
 *   Step 1: slow=2, fast=0
 *   Step 2: slow=0, fast=2  (fast wrapped around cycle)
 *   Step 3: slow=-4, fast=-4
 *   
 *   slow == fast → CYCLE DETECTED, return true
 *
 * =====================================================================================
 * COMPLEXITY:
 * =====================================================================================
 *   Time:  O(n) - Fast pointer traverses at most n + cycle_length steps
 *   Space: O(1) - Only two pointers
 *
 * =====================================================================================
 * EDGE CASES:
 * =====================================================================================
 *   1. Empty list (head=null) → fast=null, return false immediately
 *   2. Single node no cycle → fast.next=null, return false
 *   3. Single node self-loop → slow=fast after one iteration
 */
public class DetectCycle {
    public static void main(String[] args) {
        DetectCycle obj = new DetectCycle();
        ListNode head = new ListNode(3);
        head.next = new ListNode(2);
        head.next.next = new ListNode(0);
        head.next.next.next = new ListNode(-4);
        //cycle
        head.next.next.next.next = head.next;
        System.out.println(obj.hasCycle(head));
    }
    public boolean hasCycle(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) {
                return true;
            }
        }
        return false;

    }
}
 class ListNode {
    int val;
    ListNode next;

    ListNode(int x) {
        val = x;
        next = null;
    }
}