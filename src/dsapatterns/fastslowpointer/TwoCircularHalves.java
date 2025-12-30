package dsapatterns.fastslowpointer;

//https://www.geeksforgeeks.org/dsa/split-a-circular-linked-list-into-two-halves/
//https://www.educative.io/interview-prep/coding/split-a-circular-linked-list
public class TwoCircularHalves {


    public static void main(String[] args) {

        ListNode head1 = null;
        ListNode head2 = null;

        // Created linked list will be 1->2->3->4
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        head.next.next.next.next = head;

        ListNode[] result = splitCircularLinkedList(head);
        head1 = result[0];
        head2 = result[1];

        printList(head1);
        printList(head2);
    }

    static void printList(ListNode head) {
        ListNode curr = head;
        if (head != null) {
            do {
                System.out.print(curr.val + " ");
                curr = curr.next;
            } while (curr != head);
            System.out.println();
        }
    }

    public static ListNode[] splitCircularLinkedList(ListNode head) {

        // Placeholder for actual implementation
        ListNode slow=head;
        ListNode fast=head;
        while(fast.next!=head && fast.next.next!=head){
            slow=slow.next;
            fast=fast.next.next;
        }

        if(fast.next.next==head){
            fast=fast.next;
        }
        ListNode head1=head;
        ListNode head2=slow.next;
        fast.next=slow.next;
        slow.next=head;
        return new ListNode[]{head1, head2}; // Return two empty lists as placeholders
    }
}
