package dsapatterns.monotonicstack;

import java.util.Arrays;
import java.util.Stack;

//https://leetcode.com/problems/next-greater-element-ii
public class NextGreaterElementII {
    public static void main(String[] args) {
        NextGreaterElementII obj=new NextGreaterElementII();
        int[] nums={1,2,1};
        int[] result=obj.nextGreaterElements(nums);
        for(int res:result){
            System.out.print(res+" ");
        }
    }
    public int[] nextGreaterElements(int[] nums) {
        int n=nums.length;
        int[] result=new int[n];
        Arrays.fill(result,-1);
        Stack<Integer> stack=new Stack<>();
        for(int i=0;i<2*n;i++){
            int num=nums[i%n];
            while(!stack.isEmpty() && nums[stack.peek()]<num){
                result[stack.pop()]=num;
            }
            if(i<n){
                stack.push(i);
            }
        }
        return result;
    }
}
