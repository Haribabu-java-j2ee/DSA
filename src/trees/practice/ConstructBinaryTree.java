package trees.practice;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ConstructBinaryTree {
    public static void main(String[] args) {
        ArrayList<Integer> inorder = new ArrayList<>();
        inorder.add(2);
        inorder.add(1);
        inorder.add(3);
        ArrayList<Integer> preorder = new ArrayList<>();
        preorder.add(1);
        preorder.add(2);
        preorder.add(2);
        construct_binary_tree(inorder, preorder);
    }

    static int preorder_index;

    // earlier indexof method from a list takes atmost O(n), so the total time complexity going upto n^2
    //including hashmap can reduce it to O(1) so overall time complexity is O(n)
    static BinaryTreeNode construct_binary_tree(ArrayList<Integer> inorder, ArrayList<Integer> preorder) {
        // Write your code here.
        if(inorder.isEmpty() && preorder.isEmpty()){
            return null;
        }
        Map<Integer,Integer> inorderMap=new HashMap<>();
        for(int i=0;i<inorder.size();i++){
            inorderMap.put(inorder.get(i),i);
        }
        BinaryTreeNode root=construct_binary_tree_util(inorder,preorder,0,inorder.size()-1,inorderMap);
        return root;
    }

    static BinaryTreeNode construct_binary_tree_util(ArrayList<Integer> inorder, ArrayList<Integer> preorder,int low, int high,Map<Integer,Integer> inorderMap){
        if(low>high){
            return null;
        }
        Integer rootValue=preorder.get(preorder_index++);
        BinaryTreeNode root=new BinaryTreeNode(rootValue);
        if(low==high){
            return root;
        }
        root.left=construct_binary_tree_util(inorder,preorder,low,inorderMap.get(rootValue)-1,inorderMap);
        root.right=construct_binary_tree_util(inorder,preorder,inorderMap.get(rootValue)+1,high,inorderMap);
        return root;
    }
}
