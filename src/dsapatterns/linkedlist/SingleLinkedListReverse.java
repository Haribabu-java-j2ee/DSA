package dsapatterns.linkedlist;

//https://leetcode.com/problems/reverse-linked-list
public class SingleLinkedListReverse {

    public static void main(String[] args) {
        SingleLinkedListReverse obj=new SingleLinkedListReverse();
        ListNode head=new ListNode(1);
        head.next=new ListNode(2);
        head.next.next=new ListNode(3);
        head.next.next.next=new ListNode(4);
        head.next.next.next.next=new ListNode(5);

        ListNode reverseNode=obj.reverseList(head);
        System.out.println(reverseNode);
    }
    public ListNode reverseList(ListNode head) {
        ListNode prev=null;
        ListNode current=head;
        while(current!=null){
            ListNode temp=current.next;
            current.next=prev;
            prev=current;
            current=temp;
        }
        return prev;
    }
    static class ListNode {
      int val;
      ListNode next;
     ListNode() {}
      ListNode(int val) { this.val = val; }
      ListNode(int val, ListNode next) { this.val = val; this.next = next; }
  }
}
