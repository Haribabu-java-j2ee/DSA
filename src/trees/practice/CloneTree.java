package trees.practice;

public class CloneTree {

    public static void main(String[] args) {
        BinaryTreeNode root = new BinaryTreeNode(200);
        root.left = new BinaryTreeNode(100);
        root.right = new BinaryTreeNode(300);
        clone_tree(root);
    }
    static BinaryTreeNode clone_tree(BinaryTreeNode root) {
        // Write your code here.
        if(root==null){
            return root;
        }
        BinaryTreeNode cloned_tree=new BinaryTreeNode(root.value);
        cloned_tree.left=clone_tree(root.left);
        cloned_tree.right=clone_tree(root.right);
        return cloned_tree;
    }
}
