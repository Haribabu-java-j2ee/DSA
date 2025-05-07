package trees;

import java.util.ArrayList;
import java.util.Arrays;

public class BSTDelete {
    public static void main(String[] args) {
        int[] arr = {4, 2, 6, 1, 3, 5, 7};
        ArrayList<Integer> list = new ArrayList<>();
        Arrays.stream(arr).forEach(x -> list.add(x));
        BinaryTreeNode root = BSTInsert.build_a_bst(list);
        int[] tobedeleted = {5, 6};
        ArrayList<Integer> deletelist = new ArrayList<>();
        Arrays.stream(tobedeleted).forEach(x -> deletelist.add(x));
        root = delete_from_bst(root, deletelist);
        System.out.println(root);

    }

    //iterative not working
    private static BinaryTreeNode delete_elements(BinaryTreeNode root, ArrayList<Integer> deletelist) {
        if (root == null) {
            return root;
        }
        for (int element : deletelist) {
            BinaryTreeNode curr = root;
            BinaryTreeNode prev = null;
            BinaryTreeNode child = null;
            while (curr != null) {
                if (element == curr.value) {
                    break;
                } else if (element < curr.value) {
                    prev = curr;
                    curr = curr.left;
                } else if (element > curr.value) {
                    prev = curr;
                    curr = curr.right;
                }
            }
            if (curr == null) {
                continue;
            }
            if (curr.left == null && curr.right == null) {
                if (curr == prev.left) {
                    prev.left = null;
                } else if (curr == prev.right) {
                    prev.right = null;
                }
                continue;
            }
            if (curr.left == null && curr.right != null) {
                child = curr.right;
            } else if (curr.left != null && curr.right == null) {
                child = curr.left;
            }

            if (curr == prev.left) {
                prev.left = child;
            } else if (curr == prev.right) {
                prev.right = child;
            }
            if (child != null) {
                continue;
            }

            if (curr.left != null && curr.right != null) {
                BinaryTreeNode successor = curr.right;
                prev = curr;
                while (successor.left != null) {
                    prev = successor;
                    successor = successor.left;
                }
                curr.value = successor.value;
                if (successor == prev.left) {
                    prev.left = successor.right;
                } else if (successor == prev.right) {
                    prev.right = successor.right;
                }
            }

        }
        return root;
    }

    //working solution
    static BinaryTreeNode delete_from_bst(BinaryTreeNode root, ArrayList<Integer> values_to_be_deleted) {
        // Write your code here.
        if (root == null || values_to_be_deleted.isEmpty()) {
            return root;
        }
        for (int element : values_to_be_deleted) {
            root = delete_from_bst_util(root, element);
        }
        return root;
    }

    static BinaryTreeNode delete_from_bst_util(BinaryTreeNode root, int element) {
        if (root == null) {
            return root;
        }
        if (element < root.value) {
            root.left = delete_from_bst_util(root.left, element);
        } else if (element > root.value) {
            root.right = delete_from_bst_util(root.right, element);
        } else {
            if (root.right == null && root.left == null) {
                return null;
            }

            if (root.right == null) {
                BinaryTreeNode temp = root.left;
                root = null;
                return temp;
            }

            if (root.left == null) {
                BinaryTreeNode temp = root.right;
                root = null;
                return temp;
            }

            BinaryTreeNode temp = root.right;
            while (temp.left != null) {
                temp = temp.left;
            }
            root.value = temp.value;
            root.right = delete_from_bst_util(root.right, temp.value);

        }
        return root;
    }

}
