package dsapatterns.monotonicstack;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

//https://leetcode.com/problems/next-greater-element-i/
public class NextGreaterElementI {
    public static void main(String[] args) {

        NextGreaterElementI obj=new NextGreaterElementI();
        int[] nums1={4,1,2};
        int[] nums2={1,3,4,2};
        int[] result=obj.nextGreaterElement(nums1,nums2);
        for(int res:result){
            System.out.print(res+" ");
        }
    }
    public int[] nextGreaterElement(int[] nums1, int[] nums2) {
        Stack<Integer> numMonoStack=new Stack<>();
        Map<Integer,Integer> nextGreaterElementMap=new HashMap<>();
        int[] result=new int[nums1.length];
        for(int current:nums2){
            while(!numMonoStack.isEmpty() && current > numMonoStack.peek()){
                nextGreaterElementMap.put(numMonoStack.pop(),current);
            }
            numMonoStack.push(current);
        }

        for(int i=0;i<nums1.length;i++){
            result[i]=nextGreaterElementMap.getOrDefault(nums1[i],-1);
        }
        return result;
    }
}
