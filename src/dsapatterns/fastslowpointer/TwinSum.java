package dsapatterns.fastslowpointer;

//https://leetcode.com/problems/maximum-twin-sum-of-a-linked-list/
public class TwinSum {
    public static void main(String[] args) {
        TwinSum obj=new TwinSum();
        ListNode head=new ListNode(5);
        head.next=new ListNode(4);
        head.next.next=new ListNode(2);
        head.next.next.next=new ListNode(1);
        System.out.println(obj.pairSum(head));
    }
    public int pairSum(ListNode head) {
        int maxSum=0;
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
            maxSum=Math.max(maxSum, current.val+previous.val);
            current=current.next;
            previous=previous.next;
        }
        return maxSum;
    }
}
