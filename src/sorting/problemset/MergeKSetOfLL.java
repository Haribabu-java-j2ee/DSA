package sorting.problemset;

import java.util.*;

/**
 * [1, 3, 3, 4, 5, 7]
 */
public class MergeKSetOfLL {


    static LinkedListNode insert(LinkedListNode root, int item) {
        LinkedListNode temp = new LinkedListNode();
        temp.value = item;
        temp.next = root;
        root = temp;
        return root;
    }

    public static void main(String[] args) {
        Integer[][] arr = {
                {1, 3, 5},
                {3, 4},
                {7}
        };


        ArrayList<LinkedListNode> list = new ArrayList<>();
        for (int i = 0; i < arr.length; i++) {
            LinkedListNode root = null;
            for (int j = arr[i].length - 1; j >= 0; j--) {
                root = insert(root, arr[i][j]);
            }
            list.add(root);
        }
        LinkedListNode node = merge_k_lists(list);
        while (node != null) {
            System.out.println(node.value);
            node = node.next;
        }

        list = new ArrayList<>();
        for (int i = 0; i < arr.length; i++) {
            LinkedListNode root = null;
            for (int j = arr[i].length - 1; j >= 0; j--) {
                root = insert(root, arr[i][j]);
            }
            list.add(root);
        }
        System.out.println("divide & conquer");
        node = merge_k_lists_div(list);
        while (node != null) {
            System.out.println(node.value);
            node = node.next;
        }

        System.out.println("priority queue");
        list = new ArrayList<>();
        for (int i = 0; i < arr.length; i++) {
            LinkedListNode root = null;
            for (int j = arr[i].length - 1; j >= 0; j--) {
                root = insert(root, arr[i][j]);
            }
            list.add(root);
        }
        node = merge_k_lists_pq(list);
        while (node != null) {
            System.out.println(node.value);
            node = node.next;
        }

    }

    //naive approach
    //o(n*k*k)
    static LinkedListNode merge_k_lists(ArrayList<LinkedListNode> lists) {
        LinkedListNode head = null;
        LinkedListNode tail = null;

        int min_index;
        while (true) {
            min_index = -1;

            // Finding the list index with the minimum value of head node.
            for (int i = 0; i < lists.size(); i++) {
                if (lists.get(i) != null) {
                    if (min_index == -1 || lists.get(i).value < lists.get(min_index).value) {
                        min_index = i;
                    }
                }
            }
            if (min_index == -1) {
                break;
            }

            if (head == null) {
                head = lists.get(min_index);
                tail = lists.get(min_index);
            } else {
                tail.next = lists.get(min_index);
                tail = tail.next;
            }

            // Updating the node at min_index with its next node.
            lists.set(min_index, lists.get(min_index).next);
            tail.next = null;
        }
        return head;
    }


    //divide and conquer
    //O(n * log(k)).
    static LinkedListNode merge_k_lists_div(ArrayList<LinkedListNode> lists) {
        if (lists.size() == 0) {
            return null;
        }

        int low, high, last = lists.size() - 1;
        while (last > 0) {
            low = 0;
            high = last;
            while (low < high) {
                lists.set(low, merge_two_lists(lists.get(low), lists.get(high)));
                low++;
                high--;
            }
            last = high;
        }

        return lists.get(0);
    }

    static LinkedListNode merge_two_lists(LinkedListNode head1, LinkedListNode head2) {
        if (head1 == null) {
            return head2;
        }
        if (head2 == null) {
            return head1;
        }
        LinkedListNode dummy = new LinkedListNode(0);
        LinkedListNode tail = dummy;

        while (head1 != null || head2 != null) {
            if (head1 == null) {
                tail.next = head2;
                head2 = head2.next;
            } else if (head2 == null) {
                tail.next = head1;
                head1 = head1.next;
            } else {
                if (head1.value < head2.value) {
                    tail.next = head1;
                    head1 = head1.next;
                } else {
                    tail.next = head2;
                    head2 = head2.next;
                }
            }
            tail = tail.next;
        }
        return dummy.next;
    }

//using min heap
//O(n * log(k))
    static LinkedListNode merge_k_lists_pq(ArrayList<LinkedListNode> lists) {
        PriorityQueue<Pair> pq = new PriorityQueue();

        for (int i = 0; i < lists.size(); i++) {
            if (lists.get(i) != null) {
                pq.add(new Pair(lists.get(i).value, i));
            }
        }

        LinkedListNode head = null;
        LinkedListNode tail = null;
        while (!pq.isEmpty()) {
            int minIndex = pq.peek().index;
            pq.poll();

            if (head == null) {
                head = lists.get(minIndex);
                tail = lists.get(minIndex);
            } else {
                tail.next = lists.get(minIndex);
                tail = tail.next;
            }
            lists.set(minIndex, lists.get(minIndex).next);
            tail.next = null;
            if (lists.get(minIndex) != null) {
                pq.add(new Pair(lists.get(minIndex).value, minIndex));
            }
        }
        return head;
    }


    static class Pair implements Comparable<Pair> {
        int value;
        int index;

        public Pair(int value, int index) {
            this.value = value;
            this.index = index;
        }

        public int compareTo(Pair p) {
            return this.value - p.value;
        }
    }

}

class LinkedListNode {
    Integer value;
    LinkedListNode next;

    LinkedListNode() {
    }

    LinkedListNode(Integer value) {
        this.value = value;
    }
}