package dsapatterns.monotonicstack;

import java.util.Stack;

//https://leetcode.com/problems/daily-temperatures/
public class DailyTemparatures {
    public static void main(String[] args) {
        DailyTemparatures obj=new DailyTemparatures();
        int[] temperatures={73,74,75,71,69,72,76,73};
        int[] result=obj.dailyTemperatures(temperatures);
        for(int res:result){
            System.out.print(res+" ");
        }
    }
    public int[] dailyTemperatures(int[] temperatures) {
        int[] result=new int[temperatures.length];
        Stack<Integer> stack=new Stack<>();
        for(int i=0;i<temperatures.length;i++){
            while(!stack.isEmpty() && temperatures[i]>temperatures[stack.peek()] ){
                result[stack.peek()]=i-stack.pop();
            }
            stack.push(i);
        }
        return result;
    }
}
