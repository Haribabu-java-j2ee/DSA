package trees.practice;

import java.util.*;
import java.util.stream.Collectors;

//https://leetcode.com/problems/delete-nodes-and-return-forest/
public class DeleteNodesAndReturnForest {
    public static void main(String[] args) {
        DeleteNodesAndReturnForest solution=new DeleteNodesAndReturnForest();
        BinaryTreeNode root=new BinaryTreeNode(1);
        root.left=new BinaryTreeNode(2);
        root.right=new BinaryTreeNode(3);
        root.left.left=new BinaryTreeNode(4);
        root.left.right=new BinaryTreeNode(5);
        System.out.println(solution.delNodes(root,new int[]{3,5}));
    }
    List<BinaryTreeNode> forest=new ArrayList<>();
    Set<Integer> toDelete=null;

    //best approach to use hasparent boolean
    public List<BinaryTreeNode> delNodes(BinaryTreeNode root, int[] to_delete) {

        toDelete=new HashSet<>();
        for(int element:to_delete){
            toDelete.add(element);
        }
        dfsHelper(root,false);
        return forest;
    }

    private BinaryTreeNode dfsHelper(BinaryTreeNode root, boolean hasParent){
        if(root==null){
            return null;
        }
        boolean delete=toDelete.contains(root.value);
        if(!delete && !hasParent){
            forest.add(root);
        }

        root.left=dfsHelper(root.left,!delete);
        root.right=dfsHelper(root.right,!delete);
        return delete ? null:root;
    }



    //slower approach
    public List<BinaryTreeNode> delNodes_app_slow(BinaryTreeNode root, int[] to_delete) {
        List<BinaryTreeNode> forest=new ArrayList<>();
        if(root==null){
            return forest;
        }
        List<Integer> toDeleteList=Arrays.stream(to_delete).boxed().collect(Collectors.toList());
        Set<Integer> toDelete=new HashSet<>(toDeleteList);
        Stack<BinaryTreeNode> stack=new Stack<>();
        stack.push(root);
        while(!stack.isEmpty()){
            BinaryTreeNode node=stack.pop();
            if(node.left!=null){
                stack.push(node.left);
                if(toDelete.contains(node.left.value)){
                    node.left=null;
                }
            }
            if(node.right!=null){
                stack.push(node.right);
                if(toDelete.contains(node.right.value)){
                    node.right=null;
                }
            }

            if(toDelete.contains(node.value)){
                if(node.left!=null){
                    forest.add(node.left);
                }
                if(node.right!=null){
                    forest.add(node.right);
                }
            }
        }
        if(!toDelete.contains(root.value)){
            forest.add(root);
        }
        return forest;
    }
}
