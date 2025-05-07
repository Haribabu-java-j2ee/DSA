package trees;

import java.util.ArrayList;
import java.util.Arrays;

public class DFSTraversal {
    public static void main(String[] args) {
        int[] arr={0,
                1, 2,
                3, 4};

        BinaryTreeNode  root = new BinaryTreeNode(0);
        root.left = new BinaryTreeNode(1);
        root.right = new BinaryTreeNode(2);
        root.left.left = new BinaryTreeNode(3);
        root.left.right = new BinaryTreeNode(4);
        preorder(root).forEach(s->System.out.print(s+" "));
        System.out.println();
        inorder(root).forEach(s->System.out.print(s+" "));
        System.out.println();
        postorder(root).forEach(s->System.out.print(s+" "));


    }

    static ArrayList<Integer> preorder(BinaryTreeNode root) {
        // Write your code here.
        ArrayList<Integer> result=new ArrayList<>();
        preorder_util(result,root);
        return result;
    }

    static void preorder_util(ArrayList<Integer> result,BinaryTreeNode root){
        if(root==null){
            return;
        }
        result.add(root.value);
        preorder_util(result,root.left);
        preorder_util(result,root.right);
    }

    static ArrayList<Integer> inorder(BinaryTreeNode root) {
        // Write your code here.
        ArrayList<Integer> result=new ArrayList<>();
        inorder_util(result,root);
        return result;
    }

    static void inorder_util(ArrayList<Integer> result,BinaryTreeNode root){
        if(root==null){
            return;
        }
        inorder_util(result,root.left);
        result.add(root.value);
        inorder_util(result,root.right);
    }

    static ArrayList<Integer> postorder(BinaryTreeNode root) {
        // Write your code here.
        ArrayList<Integer> result=new ArrayList<>();
        postorder_util(result,root);
        return result;
    }

    static void postorder_util(ArrayList<Integer> result,BinaryTreeNode root){
        if(root==null){
            return;
        }
        postorder_util(result,root.left);
        postorder_util(result,root.right);
        result.add(root.value);
    }

}
