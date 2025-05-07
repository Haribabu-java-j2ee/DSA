package trees.practice;

import trees.BSTInsert;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class LCA {
    public static void main(String[] args) {
        Integer[] arr={1,
        2, 3,
                4, 5, 6, 7,
                null, null, 8, 9};
        ArrayList<Integer> list = new ArrayList<>();
        Arrays.stream(arr).forEach(list::add);
        BinaryTreeNode root= new BinaryTreeNode(1);
        root.left = new BinaryTreeNode(2);
        root.right = new BinaryTreeNode(3);
        root.left.left = new BinaryTreeNode(4);
        root.left.right = new BinaryTreeNode(5);
        root.right.left = new BinaryTreeNode(6);
        root.right.right = new BinaryTreeNode(7);
        root.left.right.left = new BinaryTreeNode(8);
        root.left.right.right = new BinaryTreeNode(9);

        System.out.println(lca(root,root.left.right.left,root.left.right.right));

        System.out.println(lca1(root,root.left.right.left,root.left.right.right));

        System.out.println(lca2(root,root.left.right.left,root.left.right.right));
        
    }
    static int lca(BinaryTreeNode root, BinaryTreeNode a, BinaryTreeNode b){
        if(root==null){
            return -1;
        }

        if(root==a|| root==b){
            return root.value;
        }

        int left=lca(root.left,a,b);
        int right=lca(root.right,a,b);

        if(left!=-1 && right!=-1){
            return root.value;
        }

        return left!=-1? left:right;
    }



    static int lca1(BinaryTreeNode root, BinaryTreeNode a, BinaryTreeNode b) {
        int[] par=new int[100020];
        int[] level=new int[100020];

        dfs(root,0,par,level);

        int aa=a.value;
        int bb=b.value;
        int[] answerNode=new int[1];
        int[] levelAnswer=new int[1];
        traverseAndUpdate(root,aa,bb,par,level,answerNode,levelAnswer);

        return answerNode[0];
    }

    static void dfs(BinaryTreeNode root, int parent,int[] par, int[] level){
        if(root==null){
            return;
        }
        par[root.value]=parent;
        level[root.value]=level[parent]+1;

        dfs(root.left,root.value,par,level);
        dfs(root.right,root.value,par,level);

    }

    static void traverseAndUpdate(BinaryTreeNode root, int a , int b, int[] par, int[] level,int[] answerNode, int[] levelAnswer){
        if(root==null){
            return;
        }

        int currentNode=root.value;
        if(isAncestor(currentNode,a,par) && isAncestor(currentNode,b,par)){
            if(level[currentNode]>levelAnswer[0]){
                answerNode[0]=currentNode;
                levelAnswer[0]=level[currentNode];
            }
        }
        traverseAndUpdate(root.left,a,b,par,level,answerNode,levelAnswer );
        traverseAndUpdate(root.right,a,b,par,level,answerNode,levelAnswer);
    }

    static boolean isAncestor(int currentNode, int a, int[] par){
        while(a!=0){
            if(currentNode==a){
                return true;
            }
            a=par[a];
        }
        return false;
    }



    static void dfs1(BinaryTreeNode head, int parent, int[] par) {
        if (head == null)
            return;
        par[head.value] = parent;
        dfs1(head.left, head.value, par);
        dfs1(head.right, head.value, par);
    }

    static List<Integer> getPath(int[] par, int node) {
        List<Integer> path = new ArrayList<>();
        while (par[node] != -1) {
            path.add(node);
            node = par[node];
        }
        path.add(node);
        Collections.reverse(path);
        return path;
    }

    static int lca2(BinaryTreeNode root, BinaryTreeNode a, BinaryTreeNode b) {
        int[] par = new int[100020];
        int aa = a.value, bb = b.value;
        dfs1(root, -1, par);
        List<Integer> pathOfA = getPath(par, aa);
        List<Integer> pathOfB = getPath(par, bb);

        for (int i = 0; i < Math.min(pathOfA.size(), pathOfB.size()); i++) {
            if (!pathOfA.get(i).equals(pathOfB.get(i))) {
                return pathOfA.get(i - 1);
            }
        }
        return pathOfA.get(Math.min(pathOfA.size(), pathOfB.size()) - 1);
    }

}
