package dsapatterns.fastslowpointer;

/**
 * =====================================================================================
 * FIND INTERSECTION OF TWO LINKED LISTS - Two Pointer Technique
 * =====================================================================================
 * LeetCode: https://leetcode.com/problems/intersection-of-two-linked-lists/
 *
 * =====================================================================================
 * INTUITION:
 * =====================================================================================
 * Two pointers traverse both lists. When one reaches end, redirect to other list's head.
 * This equalizes path lengths: both travel (lenA + lenB) steps before meeting at
 * intersection or both reaching null if no intersection.
 *
 * =====================================================================================
 * EXAMPLE: A: 4→1→8→4→5, B: 5→0→1→8→4→5, intersection at 8
 * =====================================================================================
 *
 *   nodeA: 4→1→8→4→5→null→5→0→1→8
 *   nodeB: 5→0→1→8→4→5→null→4→1→8
 *                              ↑
 *   Both meet at node 8 after traveling lenA + lenB steps
 *   
 *   Path A: 4→1→8→4→5→(switch to B)→5→0→1→8 = 9 steps
 *   Path B: 5→0→1→8→4→5→(switch to A)→4→1→8 = 9 steps
 *
 * =====================================================================================
 * COMPLEXITY:
 * =====================================================================================
 *   Time:  O(m + n) - Each pointer travels both lists once
 *   Space: O(1) - Only two pointers
 *
 * =====================================================================================
 * EDGE CASES:
 * =====================================================================================
 *   1. No intersection → both reach null simultaneously, return null
 *   2. One list empty → immediate switch, eventually both null
 *   3. Same length lists → no switch needed, meet directly or both null
 */
public class FindIntersection {
    public static void main(String[] args) {
        FindIntersection obj = new FindIntersection();
        ListNode headA = new ListNode(4);
        headA.next = new ListNode(1);
        ListNode intersection = new ListNode(8);
        intersection.next = new ListNode(4);
        intersection.next.next = new ListNode(5);
        headA.next.next = intersection;

        ListNode headB = new ListNode(5);
        headB.next = new ListNode(0);
        headB.next.next = new ListNode(1);
        headB.next.next.next = intersection;
        ListNode result = obj.getIntersectionNode(headA, headB);
        if (result != null) {
            System.out.println(result.val);
        } else {
            System.out.println("No intersection");
        }
    }

    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        ListNode nodeA = headA, nodeB = headB;

        while (nodeA != nodeB) {
            nodeA = (nodeA != null) ? nodeA.next : headB;
            nodeB = (nodeB != null) ? nodeB.next : headA;
        }
        return nodeA;
    }
}
