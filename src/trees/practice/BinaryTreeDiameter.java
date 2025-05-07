package trees.practice;

public class BinaryTreeDiameter {
    public static void main(String[] args) {
        BinaryTreeNode root = new BinaryTreeNode(0);
        root.left = new BinaryTreeNode(1);
        root.right = new BinaryTreeNode(2);
        root.left.left = new BinaryTreeNode(3);
        root.left.right = new BinaryTreeNode(4);
        System.out.println(binary_tree_diameter(root));
    }

    static int result;
    static Integer binary_tree_diameter(BinaryTreeNode root) {
        // Write your code here.
        if(root==null){
            return 0;
        }

        binary_tree_diameter_util(root);
        return result;

    }

    static Integer binary_tree_diameter_util(BinaryTreeNode root){
        if(root==null){
            return 0;
        }

        int leftHeight=binary_tree_diameter_util(root.left);
        int rightHeight=binary_tree_diameter_util(root.right);

        result=Math.max(result,leftHeight+rightHeight);

        return 1+Math.max(leftHeight,rightHeight);
    }

}
