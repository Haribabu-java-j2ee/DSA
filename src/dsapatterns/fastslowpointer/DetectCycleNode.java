package dsapatterns.fastslowpointer;

//https://leetcode.com/problems/linked-list-cycle-ii/description/
public class DetectCycleNode {
    public static void main(String[] args) {
        DetectCycleNode obj = new DetectCycleNode();
        ListNode head = new ListNode(3);
        head.next = new ListNode(1);
        head.next.next = new ListNode(0);
        head.next.next.next = new ListNode(-4);
        //cycle
        head.next.next.next.next = head.next;
        System.out.println(obj.detectCycle(head).val);
    }
    public ListNode detectCycle(ListNode head) {
        ListNode slow=head, fast=head;
        while(fast!=null && fast.next!=null){
            slow=slow.next;
            fast=fast.next.next;
            if(slow==fast){
                break;
            }
        }

        if(fast==null || fast.next==null){
            return null;
        }
        while(head!=slow){
            slow=slow.next;
            head=head.next;
        }
        return head;
    }
}
