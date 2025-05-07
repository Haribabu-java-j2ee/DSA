package trees.practice;

import java.util.ArrayList;

/*
 * Asymptotic complexity in terms of the total number of nodes in the given tree `n`:
 * Time: O(n * log(n)).
 * Auxiliary space: O(n).
 * Total space: O(n * log(n)).
 */
//need to revisit, since it seems to be o(n) for me
public class PrintAllPathEqualstoK {
    public static void main(String[] args) {
        BinaryTreeNode root = new BinaryTreeNode(7);
        root.left = new BinaryTreeNode(-7);
        root.right = new BinaryTreeNode(-7);
        root.left.left = new BinaryTreeNode(7);
        root.left.right = new BinaryTreeNode(7);
        root.right.right = new BinaryTreeNode(7);
        root.left.right.left = new BinaryTreeNode(-7);
        root.left.right.right = new BinaryTreeNode(-7);
        root.right.right.right = new BinaryTreeNode(-7);
        all_paths_sum_k(root,0).forEach(System.out::println);
    }

    static ArrayList<ArrayList<Integer>> all_paths_sum_k(BinaryTreeNode root, Integer k) {
        // Write your code here.
        ArrayList<ArrayList<Integer>> result=new ArrayList<>();
        all_paths_sum_k_util(root,k,0l,result,new ArrayList<>());
        if(result.isEmpty()){
            if(result.isEmpty()){
                ArrayList<Integer> temp=new ArrayList<>();
                temp.add(-1);
                result.add(temp);
            }
        }
        return result;
    }

    static void all_paths_sum_k_util(BinaryTreeNode root, Integer k, long currentSum, ArrayList<ArrayList<Integer>> result, ArrayList<Integer> temp){
        if(root==null){
            return;
        }

        if(root.left==null && root.right==null){
            currentSum+=root.value;
            if(currentSum==k){
                temp.add(root.value);
                result.add(new ArrayList(temp));
                temp.remove(temp.size()-1);
            }
            return;
        }

        temp.add(root.value);
        if(root.left!=null){
            all_paths_sum_k_util(root.left,k,currentSum+root.value,result,temp);
        }

        if(root.right!=null){
            all_paths_sum_k_util(root.right,k,currentSum+root.value,result,temp);
        }
        temp.remove(temp.size()-1);
    }

}
