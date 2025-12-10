package dsapatterns.cyclicsort;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//https://leetcode.com/problems/smallest-missing-genetic-value-in-each-subtree/
public class SmallestMissingGeneValInSubTree {
    public static void main(String[] args) {
        int[] parents={-1,0,0,2};
        int[] nums={1,2,3,4};
        SmallestMissingGeneValInSubTree obj=new SmallestMissingGeneValInSubTree();
        int[] result=obj.smallestMissingValueSubtree(parents,nums);
        Arrays.stream(result).forEach(i->System.out.print(i+" "));
    }
    private List<Integer>[] graph;
    private boolean[] visited;
    private boolean[] hasSeen;
    private int[] nums;
    public int[] smallestMissingValueSubtree(int[] parents, int[] nums) {
        int n=nums.length;
        int[] result=new int[n];
        this.nums=nums;
        graph=new List[n];
        visited=new boolean[n];
        hasSeen=new boolean[n+2];

        // --- CORRECTION 1: Initialize each index of graph with a *new* ArrayList ---
        // The previous line 'Arrays.fill(graph, new ArrayList<>());' was wrong
        // because it assigned the *same* ArrayList instance to every slot.
        Arrays.setAll(graph, i->new ArrayList<>());

        Arrays.fill(result,1);
        int nodeGeneValue=-1;
        for(int i=0;i<n;i++){
            if(i>0){
                graph[parents[i]].add(i);
            }
            if(nums[i]==1){
                nodeGeneValue=i;
            }
        }
        if(nodeGeneValue==-1){
            return result;
        }
        int missingVal=2;
        int currentNode=nodeGeneValue;

        // --- CORRECTION 2: The loop structure and update logic are now correct ---
        while(currentNode!=-1){
            dfs(currentNode);
            while(hasSeen[missingVal]){
                missingVal++;
            }
            result[currentNode]=missingVal;
            // This line was missing in your original "wrong solution",
            // but is correctly included in this revised code.
            currentNode=parents[currentNode];
        }
        return result;
    }

    private void dfs(int nodeIndex){
        if(visited[nodeIndex]){
            return;
        }
        visited[nodeIndex]=true;
        if(nums[nodeIndex]<hasSeen.length){
            hasSeen[nums[nodeIndex]]=true;
        }
        for(int child:graph[nodeIndex]){
            dfs(child);
        }
    }
}
