package trees;

import java.util.ArrayList;
import java.util.Arrays;

public class BSTInsert {
    public static void main(String[] args) {
        int[] val={1, 2, 3, 4, 5};
        ArrayList<Integer> values=new ArrayList<>();
        Arrays.stream(val).forEach(v->values.add(v));
        BinaryTreeNode tree=build_a_bst1(values);
        System.out.println(tree);
    }


    /*
     * Asymptotic complexity in terms of the number of nodes `n`:
     * Time: O(n * n).
     * Auxiliary space: O(n).
     * Total space: O(n).
     */
    static BinaryTreeNode build_a_bst(ArrayList<Integer> values){
        BinaryTreeNode root=null;
        for(Integer value:values){
            root=buildBST(root,value);
        }
        return root;
    }

    static BinaryTreeNode buildBST(BinaryTreeNode root, Integer value) {
        if(root==null){
            return new BinaryTreeNode(value);
        }
        BinaryTreeNode parent=root;
        BinaryTreeNode current=root;
        while(current!=null){
            parent=current;
            //this is for helper function
            if(value==null){
                return root;
            }
            if(value<current.value){
                current=current.left;
            }else if(value>current.value){
                current=current.right;
            }
        }
        if(value<parent.value){
            parent.left=new BinaryTreeNode(value);
        }else{
            parent.right=new BinaryTreeNode(value);
        }
        return root;
    }

    /*
     * Asymptotic complexity in terms of the number of nodes `n`:
     * Time: O(n * n).
     * Auxiliary space: O(n).
     * Total space: O(n).
     */
    static BinaryTreeNode build_a_bst1(ArrayList<Integer> values){
        BinaryTreeNode root=null;
        for(int value:values){
            root=buildBSTRecur(root,value);
        }
        return root;
    }

    private static BinaryTreeNode buildBSTRecur(BinaryTreeNode root, int value) {
        if(root==null){
            return new BinaryTreeNode(value);
        }
            if(value<root.value){
                root.left=buildBSTRecur(root.left,value);
            }else if(value>root.value){
                root.right=buildBSTRecur(root.right,value);
            }
        return root;
    }
}

