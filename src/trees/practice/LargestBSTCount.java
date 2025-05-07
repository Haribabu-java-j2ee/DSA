package trees.practice;

public class LargestBSTCount {
    public static void main(String[] args) {
        BinaryTreeNode root = new BinaryTreeNode(100);
        root.left = new BinaryTreeNode(300);
        root.right = new BinaryTreeNode(500);
        root.left.left = new BinaryTreeNode(200);
        root.left.right = new BinaryTreeNode(400);
        root.right.left = new BinaryTreeNode(600);
        root.right.right = new BinaryTreeNode(700);
        System.out.println(find_largest_bst(root));
    }


    static Integer find_largest_bst(BinaryTreeNode root) {
        // Write your code here.
        if (root == null) {
            return 0;
        }
        BstInfo bstInfo = find_largest_bst_util(root);
        return bstInfo.maxSize;
    }

    static BstInfo find_largest_bst_util(BinaryTreeNode root) {

        BstInfo currentTreeInfo=new BstInfo();
        if (root == null) {
            return currentTreeInfo;
        }

        if(root.left == null && root.right == null) {
            currentTreeInfo.mn=root.value;
            currentTreeInfo.mx=root.value;
            currentTreeInfo.maxSize=1;
            currentTreeInfo.size=1;
            currentTreeInfo.isBst=true;
        }else if(root.left==null){
            BstInfo rightTree=find_largest_bst_util(root.right);
            if(rightTree.isBst && root.value<=rightTree.mn){
                currentTreeInfo.mn=root.value;
                currentTreeInfo.mx= rightTree.mx;
                currentTreeInfo.isBst=true;
                currentTreeInfo.size=1+rightTree.size;
                currentTreeInfo.maxSize=Math.max(currentTreeInfo.size,rightTree.maxSize);
            }else{
                currentTreeInfo.isBst=false;
                currentTreeInfo.maxSize= rightTree.maxSize;
            }
        }else if(root.right==null){
            BstInfo leftTree=find_largest_bst_util(root.left);
            if(leftTree.isBst && root.value>=leftTree.mx){
                currentTreeInfo.mn=leftTree.mn;
                currentTreeInfo.mx= root.value;
                currentTreeInfo.isBst=true;
                currentTreeInfo.size=1+leftTree.size;
                currentTreeInfo.maxSize=Math.max(currentTreeInfo.size,leftTree.maxSize);
            }else{
                currentTreeInfo.isBst=false;
                currentTreeInfo.maxSize= leftTree.maxSize;
            }
        }else{
            BstInfo rightTree=find_largest_bst_util(root.right);
            BstInfo leftTree=find_largest_bst_util(root.left);
            if(rightTree.isBst && root.value<=rightTree.mn &&
            leftTree.isBst && root.value>=leftTree.mx){
                currentTreeInfo.mn=leftTree.mn;
                currentTreeInfo.mx= rightTree.mx;
                currentTreeInfo.isBst=true;
                currentTreeInfo.size=1+leftTree.size+rightTree.size;
                currentTreeInfo.maxSize=Math.max(currentTreeInfo.size,Math.max(leftTree.maxSize,rightTree.maxSize));
            }else{
                currentTreeInfo.isBst=false;
                currentTreeInfo.maxSize= Math.max(leftTree.maxSize,rightTree.maxSize);
            }
        }
        return currentTreeInfo;
    }

    static class BstInfo {
        int mn=Integer.MIN_VALUE;
        int mx=Integer.MAX_VALUE;
        boolean isBst=true;
        int size;
        int maxSize;
    }
}
