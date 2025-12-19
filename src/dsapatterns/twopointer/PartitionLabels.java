package dsapatterns.twopointer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//https://leetcode.com/problems/partition-labels/
public class PartitionLabels {
    public static void main(String[] args) {
        String s="ababcbacadefegdehijhklij";
        PartitionLabels obj=new PartitionLabels();
        System.out.println(obj.partitionLabels(s));
    }
    public List<Integer> partitionLabels(String s) {
        int[] lastIndexChar=new int[26];
        int end=-1;
        List<Integer> result=new ArrayList<>();
        Arrays.fill(lastIndexChar,-1);
        for(int i=0;i<s.length();i++){
            lastIndexChar[s.charAt(i)-'a']=i;
        }
        int j=0;
        for(int i=0;i<s.length();i++){
            end=Math.max(end, lastIndexChar[s.charAt(i)-'a']);
            if(end==i){
                result.add(i+1-j);
                j=i+1;
            }
        }
        return result;
    }
}
