package intuit.practice;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ContinuousSubArrSum {
    public static void main(String[] args) {
        int[] arr = { 7 ,31, 14 ,19 ,1, 42 ,13, 6, 11, 10, 25, 38, 49, 34 ,46, 42 ,3 ,1 };
        System.out.println(arr.length);
        subarraySum(arr,42).forEach(System.out::println);
    }
    static ArrayList<Integer> subarraySum(int[] arr, int target) {
        // code here
        ArrayList<Integer> result=new ArrayList();
        ArrayList<Integer> temp=new ArrayList();
        Map<Integer,Integer> indexMap=new HashMap<>();
        int i=0;
        for(int ele:arr){
            indexMap.put(ele,i);
        }
        subarraySumUtil(arr,target,0,temp);
        if(result.isEmpty()){
            result.add(-1);
        }else{
            i=indexMap.get(temp.get(0));
            int j=indexMap.get(temp.size()-1);
            result=new ArrayList();
            result.add(i+1);
            result.add(j+1);
        }
        return result;
    }

    static void subarraySumUtil( int[] arr,int target,int index, ArrayList<Integer> temp){
        if(target==0 && temp.size()>0){
            return;
        }
        if( index>=arr.length){
            return;
        }
        temp.add(arr[index]);
        subarraySumUtil(arr,target-arr[index],index+1,temp);
        temp.remove(temp.size()-1);
        subarraySumUtil(arr,target,index+1,temp);
    }
}
