package trees;

import java.util.ArrayList;
import java.util.Arrays;

public class FindMaxInBST {
    public static void main(String[] args) {
        Integer[] val={2,
                1, 5,
                null, null, 4, 6};
        ArrayList<Integer> values=new ArrayList<>();
        Arrays.stream(val).forEach(values::add);
        BinaryTreeNode root=BSTInsert.build_a_bst(values);

        Integer tree=get_maximum_value(root);
        System.out.println(tree);
    }
    static Integer get_maximum_value(BinaryTreeNode root) {
        // Write your code here.
        if(root==null){
            return 0;
        }
        while(root.right!=null){
            root=root.right;
        }
        return root.value;
    }
}
