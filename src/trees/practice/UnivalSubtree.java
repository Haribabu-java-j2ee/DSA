package trees.practice;

public class UnivalSubtree {
    public static void main(String[] args) {
        BinaryTreeNode root = new BinaryTreeNode(5);
        root.left = new BinaryTreeNode(1);
        root.right = new BinaryTreeNode(5);
        root.left.left = new BinaryTreeNode(5);
        root.left.right = new BinaryTreeNode(5);
        root.right.right = new BinaryTreeNode(5);
        System.out.println(findUnivalSubtree(root));
        System.out.println(find_single_value_trees(root));

    }

    //O(n^2)
    //In the worst case (think a tree where all nodes have the same value and only left child), when checking every node/subtree, the algorithm will visit all the nodes below it.
    // n+n-1+n-2+n-3 ....1 in this case
    static int findUnivalSubtree(BinaryTreeNode root) {
        if (root == null) {
            return 0;
        }
        return findUnivalSubtreeUtil(root, root.value) + findUnivalSubtree(root.left) + findUnivalSubtree(root.right);
    }

    private static int findUnivalSubtreeUtil(BinaryTreeNode root, Integer value) {
        if (root == null) {
            return 1;
        }
        if (!root.value.equals(value)) {
            return 0;
        }
        if (findUnivalSubtreeUtil(root.left, value) == 0) {
            return 0;
        }
        if (findUnivalSubtreeUtil(root.right, value) == 0) {
            return 0;
        }
        return 1;
    }


    //O(n)
    static int count;

    static int find_single_value_trees(BinaryTreeNode root) {
        recursiveHelper(root);
        return count;
    }

    private static boolean recursiveHelper(BinaryTreeNode root) {
        if (root == null) {
            return true;
        }
        boolean is_left_unival = recursiveHelper(root.left);
        boolean is_right_unival = recursiveHelper(root.right);
        if ((root.left == null || (is_left_unival && root.left.value.equals(root.value)))
                && (root.right == null || (is_right_unival && root.right.value.equals(root.value)))) {
            count++;
            return true;
        } else {
            return false;
        }
    }
}
