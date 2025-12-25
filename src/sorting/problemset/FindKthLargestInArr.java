package sorting.problemset;

import java.util.*;

public class FindKthLargestInArr {
    public static void main(String[] args) {
        int[] arr= {5, 1, 10, 3, 2};
        int k=2;
        FindKthLargestInArr obj=new FindKthLargestInArr();
        System.out.println(obj.findKthLargest(arr,k));
        ArrayList<Integer> arrList=new ArrayList<>();
        Arrays.stream(arr).forEach(arrList::add);
        System.out.println(kth_largest_in_an_array1(arrList,k));
    }

    //O(nlongn)
    static Integer kth_largest_in_an_array(ArrayList<Integer> numbers, Integer k) {
        // Write your code here.
        Collections.sort(numbers);

        return numbers.get(numbers.size()-k);
    }


    //Min Heap
    static Integer kth_largest_in_an_array1(ArrayList<Integer> numbers, Integer k) {
        // Write your code here.
        PriorityQueue<Integer> pq=new PriorityQueue<>();
        for(int i:numbers){
            pq.add(i);
            if(pq.size()>k){
                pq.poll();
            }
        }
        return  pq.peek();
    }

    static Integer kth_largest_in_an_array2(ArrayList<Integer> numbers, Integer k) {
        int low=0;
        int high=numbers.size()-1;
        while(low<high){
            int pivot=partition(numbers,low,high);
            if(pivot==numbers.size()-k){
                return numbers.get(pivot);
            }else if(pivot<numbers.size()-k){
                low=pivot+1;
            }else{
                high=pivot-1;
            }
        }
        return -1;
    }

    private static int partition(ArrayList<Integer> numbers, int low, int high) {
        int randomIndex=(int)(Math.random()*(high-low+1)+low);
        int pivot=low;
        Collections.swap(numbers,randomIndex,high);
        int pivotValue=numbers.get(high);
        for(int i=low;i<high;i++){
            if(numbers.get(i)<=pivotValue){
                Collections.swap(numbers,i,pivot);
                pivot++;
            }
        }
        Collections.swap(numbers,pivot,high);
        return pivot;
    }


    //hoare's quick select
    //https://leetcode.com/problems/kth-largest-element-in-an-array/
    public int findKthLargest(int[] nums, int k) {
        k=nums.length-k;
        quickSortHelper(nums,k,0,nums.length-1);
        return nums[k];
    }

    private void quickSortHelper(int[] nums, int k, int low, int high){
        if(low<high){
            int position=getRandomPartition(nums,low,high);
            if(position<k){
                quickSortHelper(nums,k,position+1,high);
            }else if(position>k){
                quickSortHelper(nums,k,low,position-1);
            }
        }
    }

    private int getRandomPartition(int[] nums, int low, int high){
        int randomIndex=low+new Random().nextInt(high-low+1);
        int pivotValue=nums[randomIndex];
        swap(nums,randomIndex,high);
        int start=low;
        int end=high-1;
        while(start<=end){
            if(nums[start]<pivotValue){
                start++;
            }else if(nums[end]>pivotValue){
                end--;
            }else{
                swap(nums, start,end);
                start++;
                end--;
            }
        }
        swap(nums,start,high);
        return start;
    }

    private void swap(int[] nums, int i, int j){
        int temp=nums[i];
        nums[i]=nums[j];
        nums[j]=temp;
    }
}
