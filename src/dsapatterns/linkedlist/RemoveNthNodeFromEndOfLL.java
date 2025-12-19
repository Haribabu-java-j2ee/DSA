package dsapatterns.linkedlist;

//https://leetcode.com/problems/remove-nth-node-from-end-of-list/
public class RemoveNthNodeFromEndOfLL {
    public static void main(String[] args) {
        RemoveNthNodeFromEndOfLL obj = new RemoveNthNodeFromEndOfLL();
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        int n = 4;
        ListNode result = obj.removeNthFromEnd(head, n);
        while (result != null) {
            System.out.print(result.val + " ");
            result = result.next;
        }
    }

    public ListNode removeNthFromEnd(ListNode head, int n) {
        int k = 0;
        ListNode current = head;
        while (current != null) {
            current = current.next;
            k++;
        }
        if (k - n == 0) {
            return head.next;
        }
        current = head;
        for (int i = 1; i < k - n; i++) {
            current = current.next;
        }
        current.next = current.next.next;
        return head;
    }

    public ListNode removeNthFromEnd1(ListNode head, int n) {
        ListNode fast=head;
        ListNode slow=head;
        for(int i=0;i<n;i++){
            fast=fast.next;
        }
        if(fast==null){
            return head.next;
        }
        while(fast.next!=null){
            fast=fast.next;
            slow=slow.next;
        }
        slow.next=slow.next.next;
        return head;
    }

    static class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }
    }
}
