package trees;

import java.util.*;

public class LevelOrderTraversal {
    public static void main(String[] args) {
        Integer[] arr =  {4,
                0, 3,
                2, 5, null, null,
                6, null, null, null,
                1};
        ArrayList<Integer> list = new ArrayList<>();
        Arrays.stream(arr).forEach(list::add);
        BinaryTreeNode root = BSTInsert.build_a_bst(list);
        level_order_traversal(root).forEach(System.out::println);
    }

    //BFS only , check for dfs approach also
    static ArrayList<ArrayList<Integer>> level_order_traversal(BinaryTreeNode root) {
        // Write your code here.
        ArrayList<ArrayList<Integer>> result = new ArrayList<>();
        Queue<BinaryTreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            ArrayList<Integer> level = new ArrayList<>();
            int temp = queue.size();
            for (int i = 0; i < temp; ++i) {
                BinaryTreeNode current = queue.poll();
                level.add(current.value);
                if (current.left != null) {
                    queue.add(current.left);
                }
                if (current.right != null) {
                    queue.add(current.right);
                }
            }
            result.add(level);
        }
        return result;
    }

}
