package dsapatterns.linkedlist;

//https://leetcode.com/problems/reverse-linked-list-ii
public class ReverseSubListofLL{
    public static void main(String[] args) {
        ReverseSubListofLL obj=new ReverseSubListofLL();
        ListNode head=new ListNode(1);
        head.next=new ListNode(2);
        head.next.next=new ListNode(3);
        head.next.next.next=new ListNode(4);
        head.next.next.next.next=new ListNode(5);
        //System.out.println(obj.reverseBetween(head,2,4));
        //better approach
        System.out.println(obj.reverseBetween1(head,2,4));

    }

    public ListNode reverseBetween(ListNode head, int left, int right) {
        ListNode reverse = null, reversePrevious = null, reverseEnd = null, reverseEndNext = null;
        ListNode currentNode = head;
        int i = 1;
        while (currentNode != null && i <= right) {
            if (i < left) {
                reversePrevious = currentNode;
            } else if (i == left) {
                reverse = currentNode;
            }
            //if we make it as else then it will not work for left==right case
            if (i == right) {
                reverseEnd = currentNode;
                reverseEndNext = currentNode.next;
            }
            currentNode = currentNode.next;
            i++;
        }

        if (reverse != null) {
            reverseEnd.next = null;
        }
        reverseEnd = reverseNode(reverse);
        if (reversePrevious != null) {
            reversePrevious.next = reverseEnd;
        } else {
            head = reverseEnd;
        }

        if (reverse != null) {
            reverse.next = reverseEndNext;
        }
        return head;
    }

    private ListNode reverseNode(ListNode node) {
        ListNode previous = null;
        ListNode current = node;
        while (current != null) {
            ListNode temp = current.next;
            current.next=previous;
            previous = current;
            current = temp;
        }
        return previous;
    }

    //better approach
    public ListNode reverseBetween1(ListNode head, int left, int right) {
        // 1. Create a dummy node to handle edge case where left = 1
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode prev = dummy;

        // 2. Move 'prev' to the node just before the 'left' position
        for (int i = 0; i < left - 1; i++) {
            prev = prev.next;
        }

        // 'current' points to the start of the sublist to be reversed (the 'left' node)
        ListNode current = prev.next;

        // 3. Reverse the sublist in-place by repeatedly moving the 'next' node to the front
        for (int i = 0; i < right - left; i++) {
            ListNode nextNode = current.next;
            // Detach nextNode from the list
            current.next = nextNode.next;
            // Insert nextNode right after 'prev'
            nextNode.next = prev.next;
            prev.next = nextNode;
        }

        // 4. Return the new head of the list
        return dummy.next;
    }


    static class ListNode {
          int val;
          ListNode next;
          ListNode() {}
          ListNode(int val) { this.val = val; }
          ListNode(int val, ListNode next) { this.val = val; this.next = next; }
      }
}
