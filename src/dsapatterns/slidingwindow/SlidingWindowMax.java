package dsapatterns.slidingwindow;

import java.util.*;

/**
 * =====================================================================================
 * SLIDING WINDOW MAXIMUM - Monotonic Deque
 * =====================================================================================
 * LeetCode: https://leetcode.com/problems/sliding-window-maximum/
 *
 * =====================================================================================
 * INTUITION:
 * =====================================================================================
 * Maintain a decreasing deque (front has max). For each new element:
 *   1. Remove indices outside window from front
 *   2. Remove smaller elements from back (they'll never be max)
 *   3. Add current index
 *   4. Front of deque is always the maximum
 *
 * =====================================================================================
 * EXAMPLE: nums = [1,3,1,2,0,5], k = 3 → returns [3,3,2,5]
 * =====================================================================================
 *
 *   i | nums[i] | deque (indices) | deque (values) | window    | result
 *   --|---------|-----------------|----------------|-----------|-------
 *   0 |    1    | [0]             | [1]            | [1]       |  -
 *   1 |    3    | [1]             | [3]            | [1,3]     |  -
 *         ↑ Remove 0 (1<3)
 *   2 |    1    | [1,2]           | [3,1]          | [1,3,1]   |  3
 *   3 |    2    | [1,3]           | [3,2]          | [3,1,2]   |  3
 *         ↑ Remove 2 (1<2)
 *   4 |    0    | [3,4]           | [2,0]          | [1,2,0]   |  2
 *         ↑ Remove 1 (out of window)
 *   5 |    5    | [5]             | [5]            | [2,0,5]   |  5
 *         ↑ Remove 3,4 (2<5, 0<5)
 *
 *   Result: [3, 3, 2, 5]
 *
 * =====================================================================================
 * WHY DECREASING DEQUE?
 * =====================================================================================
 *   - Front always has index of maximum in current window
 *   - When new element is larger, smaller elements will never be max → remove them
 *   - Elements are removed from back if smaller (monotonic property)
 *   - Elements are removed from front if outside window
 *
 * =====================================================================================
 * COMPLEXITY:
 * =====================================================================================
 *   Time:  O(n) - Each element added and removed from deque at most once
 *   Space: O(k) - Deque stores at most k indices
 *
 * =====================================================================================
 * EDGE CASES:
 * =====================================================================================
 *   1. Empty array → return empty array
 *   2. k = 1 → return copy of input array
 *   3. k = n → return single element (max of entire array)
 *   4. All same elements → return array of same element
 *   5. Strictly decreasing → deque always full (all elements stay)
 *   6. Strictly increasing → deque always has 1 element (last one)
 */
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
    
    /**
     * Using LinkedList as Deque. Stores INDICES, not values.
     * Maintains decreasing order of values (front = max).
     */
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

    //decreasing queue and get the first element as max
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
