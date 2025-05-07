package trees.practice;

import java.util.ArrayList;

//merge 2 bsts and form a balanced binary tree
public class Merge2BST {
    public static void main(String[] args) {
        BinaryTreeNode root1 = new BinaryTreeNode(5);
        root1.left = new BinaryTreeNode(3);
        root1.right = new BinaryTreeNode(6);
        root1.left.left = new BinaryTreeNode(2);
        root1.left.right = new BinaryTreeNode(4);
        root1.right.right = new BinaryTreeNode(7);
        BinaryTreeNode root2 = new BinaryTreeNode(8);
        root2.left = new BinaryTreeNode(1);
        root2.right = new BinaryTreeNode(9);
        BinaryTreeNode node =merge_two_binary_search_trees(root1, root2);
        System.out.println(node);
    }

    static BinaryTreeNode merge_two_binary_search_trees(BinaryTreeNode root1, BinaryTreeNode root2) {
        // Write your code here.
        if(root1==null){
            return root2;
        }
        if(root2==null){
            return root1;
        }
        ArrayList<Integer> inorder_root1=new ArrayList<>();
        dfs(root1,inorder_root1);
        ArrayList<Integer> inorder_root2=new ArrayList<>();
        dfs(root2,inorder_root2);
        ArrayList<Integer> mergedList=merge_sorted_list(inorder_root1,inorder_root2);
        BinaryTreeNode root=build_bst(mergedList, 0, mergedList.size()-1);
        return root;
    }

    static void dfs(BinaryTreeNode root, ArrayList<Integer> inorder){
        if(root==null){
            return;
        }
        dfs(root.left,inorder);
        inorder.add(root.value);
        dfs(root.right,inorder);
    }

    static ArrayList<Integer> merge_sorted_list(ArrayList<Integer> inorder_root1, ArrayList<Integer> inorder_root2){
        ArrayList<Integer> mergedList=new ArrayList<>();
        int i=0;
        int j=0;

        while (i<inorder_root1.size() && j<inorder_root2.size()){
            if(inorder_root1.get(i)<inorder_root2.get(j)){
                mergedList.add(inorder_root1.get(i));
                i++;
            }else if(inorder_root1.get(i)>inorder_root2.get(j)){
                mergedList.add(inorder_root2.get(j));
                j++;
            }else{
                mergedList.add(inorder_root1.get(i));
                mergedList.add(inorder_root2.get(j));
                i++;
                j++;
            }
        }

        while(i<inorder_root1.size()){
            mergedList.add(inorder_root1.get(i));
            i++;
        }

        while(j<inorder_root2.size()){
            mergedList.add(inorder_root2.get(j));
            j++;
        }

        return mergedList;
    }

    static BinaryTreeNode build_bst(ArrayList<Integer> inorder_list, int low, int high){
        if(low>high){
            return null;
        }

        int mid=low+(high-low)/2;
        BinaryTreeNode node=new BinaryTreeNode(inorder_list.get(mid));
        node.left=build_bst(inorder_list,low,mid-1);
        node.right=build_bst(inorder_list,mid+1,high);
        return node;
    }
}
