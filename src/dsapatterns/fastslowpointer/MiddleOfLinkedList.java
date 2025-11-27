package dsapatterns.fastslowpointer;

//https://leetcode.com/problems/middle-of-the-linked-list/
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
