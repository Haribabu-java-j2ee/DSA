package trees.practice;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Stack;

public class InOrderIterativeWithTweaks {
    public static void main(String[] args) {
        BinaryTreeNode root = new BinaryTreeNode(200);
        root.left = new BinaryTreeNode(100);
        root.right = new BinaryTreeNode(300);
        String[] str={"next", "has_next", "next", "next", "has_next", "has_next", "next"};
        ArrayList<String> operations=new ArrayList<>();
        Arrays.stream(str).forEach(operations::add);
        implement_tree_iterator(root,operations).forEach(System.out::println);
    }
    static ArrayList<Integer> implement_tree_iterator(BinaryTreeNode root, ArrayList<String> operations) {
        // Write your code here.
        ArrayList<Integer> result = new ArrayList<>();
        ArrayList<Integer> tempList = new ArrayList<>();
        BinaryTreeNode current = root;
        Stack<BinaryTreeNode> stack = new Stack<>();
        while (current != null || !stack.isEmpty()) {
            while (current != null) {
                stack.push(current);
                current = current.left;
            }
            current = stack.pop();
            tempList.add(current.value);
            current = current.right;

        }

        int n = tempList.size();
        int j = 0;
        for (String str : operations) {
            if (j < n) {
                if (str.equals("next")) {
                    result.add(tempList.get(j));
                    j++;
                } else if (str.equals("has_next")) {
                    result.add(1);
                }
            } else {
                result.add(0);
            }
        }
        return result;
    }
}
