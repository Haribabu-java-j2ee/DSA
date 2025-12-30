package dsapatterns.fastslowpointer;

//https://leetcode.com/problems/palindrome-linked-list/
public class PalindromicLinkedList {
    public static void main(String[] args) {
        PalindromicLinkedList obj=new PalindromicLinkedList();
        ListNode head=new ListNode(1);
        head.next=new ListNode(2);
        head.next.next=new ListNode(2);
        head.next.next.next=new ListNode(1);
        System.out.println(obj.isPalindrome(head));
    }
    public boolean isPalindrome(ListNode head) {
        ListNode slow=head;
        ListNode fast=head;
        while(fast!=null && fast.next!=null){
            slow=slow.next;
            fast=fast.next.next;
        }
        ListNode current=slow;
        ListNode previous=null;
        while(current!=null){
            ListNode temp=current.next;
            current.next=previous;
            previous=current;
            current=temp;
        }
        current=head;
        while(previous!=null){
            if(current.val!=previous.val){
                return false;
            }
            current=current.next;
            previous=previous.next;
        }
        return true;
    }
}
