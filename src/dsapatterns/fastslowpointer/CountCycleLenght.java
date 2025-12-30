package dsapatterns.fastslowpointer;

public class CountCycleLenght {

    public static void main(String[] args) {
        CountCycleLenght obj = new CountCycleLenght();
        ListNode head = new ListNode(3);
        head.next = new ListNode(2);
        head.next.next = new ListNode(0);
        head.next.next.next = new ListNode(-4);
        //cycle
        head.next.next.next.next = head.next;
        System.out.println(obj.countCycleLength(head));
    }
    public int countCycleLength(ListNode head) {

        // Replace this placeholder return statement with your code
        ListNode slow = head;
        ListNode fast = head;
        while (fast != null && fast.next != null) {

            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) {
                return countNodes(slow);
            }
        }
        return 0;
    }

    private int countNodes(ListNode node) {
        ListNode current = node;
        int count = 1;
        while (current.next != node) {
            count++;
            current = current.next;
        }
        return count;
    }
}
