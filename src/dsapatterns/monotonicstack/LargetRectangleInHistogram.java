package dsapatterns.monotonicstack;

import java.util.Stack;

//https://leetcode.com/problems/largest-rectangle-in-histogram/
public class LargetRectangleInHistogram {
    public static void main(String[] args) {
        LargetRectangleInHistogram obj=new LargetRectangleInHistogram();
        int[] heights={2,4};
        int result=obj.largestRectangleArea(heights);
        System.out.println(result);
    }
    public int largestRectangleArea(int[] heights) {
        int n=heights.length;
        int result=0;
        Stack<Integer> stack=new Stack<>();
        for(int i=0;i<n;i++){
            while(!stack.isEmpty() && heights[stack.peek()]>=heights[i]){
                int top=stack.pop();
                int width=stack.isEmpty()?i:i-stack.peek()-1;
                result=Math.max(result, heights[top]*width);
            }

            stack.push(i);
        }
        while(!stack.isEmpty()){
            int current=heights[stack.pop()];
            int width=stack.isEmpty()?n:n-stack.peek()-1;
            result=Math.max(result,current*width);
        }
        return result;
    }


}
