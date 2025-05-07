package trees.practice;

public class FlipTreeUpsideDown {
    public static void main(String[] args) {
        BinaryTreeNode root = new BinaryTreeNode(1);
        root.left = new BinaryTreeNode(2);
        root.right = new BinaryTreeNode(3);
        root.left.left = new BinaryTreeNode(4);
        root.left.right = new BinaryTreeNode(5);
        flipUpsideDown1(root);
    }

    static BinaryTreeNode flipUpsideDown(BinaryTreeNode root) {
        if (root == null) {
            return null;
        }
        if (root.left == null && root.right == null) {
            return root;
        }
        BinaryTreeNode current = flipUpsideDown(root.left);
        root.left.left = root.right;
        root.left.right = root;

        root.left = null;
        root.right = null;
        return current;
    }

    static BinaryTreeNode flipUpsideDown1(BinaryTreeNode root) {
        if (root == null) {
            return null;
        }
        BinaryTreeNode assign_left = null;
        BinaryTreeNode assign_right = null;
        while (root != null) {
            BinaryTreeNode current= root.left;
            BinaryTreeNode next_assign_left = root.right;
            BinaryTreeNode next_assign_right = root;
            root.left = assign_left;
            root.right = assign_right;
            if(current!=null){
                root=current;
                assign_left=next_assign_left;
                assign_right=next_assign_right;
            }else{
                return root;
            }
        }
        return null;
    }
}
