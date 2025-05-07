package trees.practice;

public class IsBST {
    public static void main(String[] args) {
        BinaryTreeNode root = new BinaryTreeNode(100);
        root.left = new BinaryTreeNode(200);
        root.right = new BinaryTreeNode(300);
        System.out.println(is_bst(root));
        root = new BinaryTreeNode(200);
        root.left = new BinaryTreeNode(100);
        root.right = new BinaryTreeNode(300);
        System.out.println(is_bst(root));
    }

    static Boolean is_bst(BinaryTreeNode root) {
        // Write your code here.
        if (root == null) {
            return true;
        }
        return is_bst_util(root, Integer.MIN_VALUE, Integer.MAX_VALUE);

    }

    static Boolean is_bst_util(BinaryTreeNode root, int min, int max) {
        if (root == null) {
            return true;
        }
        if (root.value < min || root.value > max) {
            return false;
        }

        return is_bst_util(root.left, min, root.value) && is_bst_util(root.right, root.value, max);
    }

}
