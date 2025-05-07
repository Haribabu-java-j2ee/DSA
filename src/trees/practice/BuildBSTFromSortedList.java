package trees.practice;

import java.util.ArrayList;

//condition to generate balanced tree
public class BuildBSTFromSortedList {
    public static void main(String[] args) {
        LinkedListNode head = new LinkedListNode(-1);
        head.next = new LinkedListNode(2);
        head.next.next = new LinkedListNode(3);
        head.next.next.next = new LinkedListNode(5);
        head.next.next.next.next = new LinkedListNode(6);
        head.next.next.next.next.next = new LinkedListNode(7);
        head.next.next.next.next.next.next = new LinkedListNode(10);
        System.out.println(sorted_list_to_bst(head));
        head = new LinkedListNode(-1);
        head.next = new LinkedListNode(2);
        head.next.next = new LinkedListNode(3);
        head.next.next.next = new LinkedListNode(5);
        head.next.next.next.next = new LinkedListNode(6);
        head.next.next.next.next.next = new LinkedListNode(7);
        head.next.next.next.next.next.next = new LinkedListNode(10);
        BinaryTreeNode root = sorted_list_to_bst1(head);
        System.out.println(root);
    }

    //O (nlogn)
    // logn is for find mid element of linked list every time, n/2, n/4, n/8 ..... 0
    static BinaryTreeNode sorted_list_to_bst(LinkedListNode head) {
        // Write your code here.
        if (head == null) {
            return null;
        }

        if (head.next == null) {
            return new BinaryTreeNode(head.value);
        }
        LinkedListNode middle_node = get_middle(head);
        BinaryTreeNode root = new BinaryTreeNode(middle_node.value);
        LinkedListNode head_left = head;
        LinkedListNode head_right = middle_node.next;

        middle_node.next = null;
        root.left = sorted_list_to_bst(head_left);
        root.right = sorted_list_to_bst(head_right);
        return root;
    }

    static LinkedListNode get_middle(LinkedListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        LinkedListNode slow_prev = head;
        LinkedListNode slow = head;
        LinkedListNode fast = head;

        while (fast != null && fast.next != null) {
            slow_prev = slow;
            slow = slow.next;
            fast = fast.next.next;
        }

        slow_prev.next = null;
        return slow;

    }


    //array conversion O(n)
    static BinaryTreeNode sorted_list_to_bst1(LinkedListNode head) {
        if (head == null) {
            return null;
        }

        if (head.next == null) {
            return new BinaryTreeNode(head.value);
        }
        ArrayList<Integer> list = new ArrayList<>();
        while (head != null) {
            list.add(head.value);
            head = head.next;
        }
        BinaryTreeNode root = build_tree_util(list, 0, list.size() - 1);
        return root;
    }

    private static BinaryTreeNode build_tree_util(ArrayList<Integer> list, int low, int high) {
        if (low > high) {
            return null;
        }
        if (low == high) {
            return new BinaryTreeNode(list.get(low));
        }
        int mid = low + (high - low) / 2;
        BinaryTreeNode root = new BinaryTreeNode(list.get(mid));
        root.left = build_tree_util(list, low, mid - 1);
        root.right = build_tree_util(list, mid + 1, high);
        return root;
    }

}

class LinkedListNode {
    Integer value;
    LinkedListNode next;

    LinkedListNode(Integer value) {
        this.value = value;
        this.next = null;
    }
}