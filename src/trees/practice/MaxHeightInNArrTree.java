package trees.practice;

import java.util.ArrayList;
//if its edges, return maxhight-1
public class MaxHeightInNArrTree {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        ArrayList<TreeNode> list = new ArrayList<>();
        TreeNode child1 = new TreeNode(2);
        TreeNode child2 = new TreeNode(3);
        TreeNode subchild=new TreeNode(4);
        ArrayList<TreeNode> sublist = new ArrayList<>();
        sublist.add(subchild);
        TreeNode child3 = new TreeNode(5);
        child3.children = sublist;
        list.add(child1);
        list.add(child2);
        list.add(child3);
        root.children = list;

        System.out.println( find_height(root));
        System.out.println( find_height_helper1(root));
    }


    static int max_height;
    static Integer find_height(TreeNode root) {
        // Write your code here.
        if(root==null){
            return 0;
        }

        find_height_helper(root,new ArrayList());
        return max_height;
    }
    static void find_height_helper(TreeNode root, ArrayList<Integer> temp){
        if(root.children==null || root.children.isEmpty()){
            temp.add(root.value);
            max_height=Math.max(max_height,temp.size());
            temp.remove(temp.size()-1);
            return;
        }

        temp.add(root.value);
        for(TreeNode child: root.children){
            find_height_helper(child,temp);
        }
        temp.remove(temp.size()-1);
    }

    // a simple approach
    static int find_height_helper1(TreeNode root){
        if(root==null){
            return 0;
        }
        int height=0;

        for(TreeNode child: root.children){
            height=Math.max(height,find_height_helper1(child));
        }
        return height+1;
    }

}
