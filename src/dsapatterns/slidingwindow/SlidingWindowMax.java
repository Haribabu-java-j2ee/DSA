package dsapatterns.slidingwindow;

import java.util.*;
//https://leetcode.com/problems/sliding-window-maximum/
public class SlidingWindowMax {
    public static void main(String[] args) {
        int[] nums={1,3,1,2,0,5};
        int k=3;
        SlidingWindowMax obj=new SlidingWindowMax();
        int[] result=obj.maxSlidingWindow(nums, k);
        for(int num:result){
            System.out.print(num+" ");
        }
        System.out.println();
        result=obj.maxSlidingWindow1(nums, k);
        for(int num:result){
            System.out.print(num+" ");
        }
    }
    public int[] maxSlidingWindow(int[] nums, int k) {
        int n=nums.length;
        if(n==0){
            return new int[0];
        }
        int[] result=new int[n-k+1];
        int resultIndex=0;
        LinkedList<Integer> linkedList=new LinkedList();
        for(int i=0;i<n;i++){
            while(!linkedList.isEmpty() && linkedList.getFirst()<i-k+1){
                linkedList.removeFirst();
            }
            while(!linkedList.isEmpty() && nums[linkedList.getLast()]<nums[i]){
                linkedList.removeLast();
            }
            linkedList.add(i);
            if(i>=k-1){
                result[resultIndex++]=nums[linkedList.getFirst()];
            }
        }
        return result;
    }

    public int[] maxSlidingWindow1(int[] nums, int k) {
        int n=nums.length;
        if(n==0){
            return new int[0];
        }
        int[] result=new int[n-k+1];
        int resultIndex=0;
        Deque<Integer> queue=new ArrayDeque();
        for(int i=0;i<n;i++){
            while(!queue.isEmpty() && queue.peek()<i-k+1){
                queue.poll();
            }
            while(!queue.isEmpty() && nums[queue.peekLast()]<nums[i]){
                queue.pollLast();
            }
            queue.offer(i);
            if(i>=k-1){
                result[resultIndex++]=nums[queue.peek()];
            }
        }
        return result;
    }
}
