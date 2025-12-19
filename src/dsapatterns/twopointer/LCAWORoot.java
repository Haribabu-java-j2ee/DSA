package dsapatterns.twopointer;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;


//https://www.educative.io/interview-prep/coding/solution-lowest-common-ancestor-of-a-binary-tree-iii
public class LCAWORoot {

    public static void main(String[] args) {
        List<List<Integer>> input_trees = Arrays.asList(
                Arrays.asList(100, 50, 200, 25, 75, 350),
                Arrays.asList(100, 200, 75, 50, 25, 350),
                Arrays.asList(350, 100, 75, 50, 200, 25),
                Arrays.asList(100, 50, 200, 25, 75, 350),
                Arrays.asList(25, 50, 75, 100, 200, 350)
        );
        List<List<Integer>> input_nodes = Arrays.asList(
                Arrays.asList(25, 75),
                Arrays.asList(50, 350),
                Arrays.asList(100, 200),
                Arrays.asList(50, 25),
                Arrays.asList(350, 200)
        );

        for (int i = 0; i < input_trees.size(); i++) {
            EduBinaryTree tree = new EduBinaryTree(input_trees.get(i));
            System.out.println((i + 1) + ".\tBinary tree:");
            //Print.displayTree(tree.getRoot());
            System.out.println("\n\tp = " + input_nodes.get(i).get(0));
            System.out.println("\tq = " + input_nodes.get(i).get(1));
            EduTreeNode p = tree.find(tree.getRoot(), input_nodes.get(i).get(0));
            EduTreeNode q = tree.find(tree.getRoot(), input_nodes.get(i).get(1));
            EduTreeNode lca = LowestCommonAncestor(p, q);
            System.out.println("\n\tLowest common ancestor: " + lca.data);
            System.out.println(new String(new char[100]).replace("\0", "-"));
        }
    }
    public static EduTreeNode LowestCommonAncestor(EduTreeNode p, EduTreeNode q) {

        // Replace this placeholder with actual logic
        EduTreeNode ptr1=p;
        EduTreeNode ptr2=q;
        while(ptr1!=ptr2){
            if(ptr1.parent!=null){
                ptr1=ptr1.parent;
            }else{
                ptr1=q;
            }

            if(ptr2.parent!=null){
                ptr2=ptr2.parent;
            }else{
                ptr2=p;
            }
        }
        return ptr1;
    }
}
class EduTreeNode {
    int data;
    EduTreeNode left;
    EduTreeNode right;
    EduTreeNode parent;

    EduTreeNode(int value) {
        this.data = value;
        this.left = null;
        this.right = null;
        this.parent = null;
    }
}
class EduBinaryTree {
    private EduTreeNode root;

    private EduTreeNode createBinaryTree(List<Integer> nodes) {
        if (nodes.isEmpty() || nodes.get(0) == 0) {
            return null;
        }
        EduTreeNode root = new EduTreeNode(nodes.get(0));
        Queue<EduTreeNode> q = new LinkedList<>();
        q.offer(root);
        int i = 1;
        while (i < nodes.size()) {
            EduTreeNode curr = q.poll();
            if (i < nodes.size() && nodes.get(i) != 0) {
                curr.left = new EduTreeNode(nodes.get(i));
                curr.left.parent = curr;
                q.offer(curr.left);
            }
            i++;
            if (i < nodes.size() && nodes.get(i) != 0) {
                curr.right = new EduTreeNode(nodes.get(i));
                curr.right.parent = curr;
                q.offer(curr.right);
            }
            i++;
        }
        return root;
    }

    public EduBinaryTree(List<Integer> nodes) {
        this.root = createBinaryTree(nodes);
    }

    public EduTreeNode find(EduTreeNode root, int value) {
        if (root == null) {
            return null;
        }
        Queue<EduTreeNode> q = new LinkedList<>();
        q.offer(root);
        while (!q.isEmpty()) {
            EduTreeNode currentNode = q.poll();
            if (currentNode.data == value) {
                return currentNode;
            }
            if (currentNode.left != null) {
                q.offer(currentNode.left);
            }
            if (currentNode.right != null) {
                q.offer(currentNode.right);
            }
        }
        return null;
    }

    public EduTreeNode getRoot() {
        return root;
    }
}
