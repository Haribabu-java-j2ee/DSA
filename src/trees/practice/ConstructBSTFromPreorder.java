package trees.practice;

import java.util.ArrayList;
import java.util.Collections;

public class ConstructBSTFromPreorder {
    public static void main(String[] args) {
        ArrayList<Integer> preorder = new ArrayList<>();
        preorder.add(2);
        preorder.add(0);
        preorder.add(1);
        preorder.add(3);
        preorder.add(5);
        preorder.add(4);
        build_binary_search_tree(preorder);
    }

    static int preorder_index;
    static BinaryTreeNode build_binary_search_tree(ArrayList<Integer> preorder) {
        // Write your code here.
        ArrayList<Integer> inorder = new ArrayList<>();
        inorder.addAll(preorder);
        Collections.sort(inorder);
        BinaryTreeNode root= build_binary_search_tree_util(preorder,inorder, 0 , inorder.size()-1);
        return root;
    }

    static BinaryTreeNode build_binary_search_tree_util(ArrayList<Integer> preorder,ArrayList<Integer> inorder, int low, int high){
        if(low>high){
            return null;
        }
        BinaryTreeNode node = new BinaryTreeNode(preorder.get(preorder_index++));
        if(low==high){
            return node;
        }

        int index=getMidIndex(inorder,low,high, node.value);
        node.left=build_binary_search_tree_util(preorder, inorder,low,index-1);
        node.right=build_binary_search_tree_util(preorder, inorder,index+1,high);
        return node;
    }

    static int getMidIndex(ArrayList<Integer> inorder,int low, int high, int root){
        if(low>high){
            return -1;
        }
        int mid = low+(high-low)/2;
        if(inorder.get(mid)==root){
            return mid;
        }else if(root >inorder.get(mid)){
            return getMidIndex(inorder,mid+1,high,root);
        }else{
            return getMidIndex(inorder,low,mid-1,root);
        }
    }

    static BinaryTreeNode build_binary_search_tree1(ArrayList<Integer> preorder) {

        // Write your code here.
        preorder_index=0;
        return build_binary_search_tree_util(preorder,-1000000000,1000000000);
    }

    static BinaryTreeNode build_binary_search_tree_util(ArrayList<Integer> preorder, int low, int high){
        if(preorder_index==preorder.size()){
            return null;
        }

        if(preorder.get(preorder_index)<low || preorder.get(preorder_index)>high ){
            return null;
        }

        BinaryTreeNode node=new BinaryTreeNode(preorder.get(preorder_index++));
        node.left=build_binary_search_tree_util(preorder,low,node.value-1);
        node.right=build_binary_search_tree_util(preorder,node.value+1,high);
        return node;
    }
}
