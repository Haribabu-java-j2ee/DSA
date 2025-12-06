package dsapatterns.monotonicstack;

import java.util.Stack;

//https://leetcode.com/problems/trapping-rain-water/description/
//this is non optimal approach but alternative for 2 pointer approach with extra space
public class TrappingRainWater {
    public static void main(String[] args) {
        TrappingRainWater obj=new TrappingRainWater();
        int[] height={0,1,0,2,1,0,1,3,2,1,2,1};
        System.out.println(obj.trap(height));
    }
    public int trap(int[] height) {
        int result=0;
        int n=height.length;
        Stack<Integer> stack=new Stack<>();
        for(int i=0;i<n;i++){
            while(!stack.isEmpty() && height[stack.peek()]<height[i]){
                int popHeight=height[stack.pop()];
                if(stack.isEmpty()){
                    break;
                }
                int distance=i-stack.peek()-1;
                int water=Math.min(height[stack.peek()],height[i]);
                water-=popHeight;
                result+=distance*water;
            }
            stack.push(i);
        }
        return result;
    }
}
