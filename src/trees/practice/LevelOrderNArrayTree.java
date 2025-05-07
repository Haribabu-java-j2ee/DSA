package trees.practice;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class LevelOrderNArrayTree {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        TreeNode child1 = new TreeNode(3);
        TreeNode child2 = new TreeNode(4);
        TreeNode child2Sub1 = new TreeNode(5);
        TreeNode child2Sub2 = new TreeNode(6);
        child2.children.add(child2Sub1);
        child2.children.add(child2Sub2);

        TreeNode child3 = new TreeNode(2);
        root.children.add(child1);
        root.children.add(child2);
        root.children.add(child3);

        level_order(root).forEach(System.out::println);

    }
    static ArrayList<ArrayList<Integer>> level_order(TreeNode root) {
        // Write your code here.
        ArrayList<ArrayList<Integer>> result=new ArrayList<>();
        Queue<TreeNode> queue=new LinkedList<>();
        queue.add(root);
        while(!queue.isEmpty()){
            ArrayList<Integer> level= new ArrayList<>();
            int size=queue.size();
            for(int i=0;i<size;i++){
                TreeNode current=queue.poll();
                level.add(current.value);
                for(TreeNode child:current.children){
                    queue.add(child);
                }
            }
            result.add(level);
        }

        return result;
    }

}
class TreeNode {
    Integer value;
    ArrayList<TreeNode> children;

    TreeNode(Integer value) {
        this.value = value;
        this.children = new ArrayList(3);
    }
}