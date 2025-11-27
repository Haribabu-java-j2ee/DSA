package dsapatterns.fastslowpointer;

//https://leetcode.com/problems/intersection-of-two-linked-lists/
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
