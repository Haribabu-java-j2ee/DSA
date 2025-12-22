package sorting.problemset;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//https://leetcode.com/problems/intersection-of-two-arrays/
public class FindIntersection2ArrUnique {
    public static void main(String[] args) {
        int[] nums1 = {4,9,5};
        int[] nums2 = {9,4,9,8,4};
        FindIntersection2ArrUnique obj = new FindIntersection2ArrUnique();
        int[] result = obj.intersection(nums1, nums2);
        Arrays.stream(result).forEach(num -> System.out.print(num + " "));
        System.out.println();
        Arrays.stream(obj.intersection1(nums1, nums2)).forEach(num -> System.out.print(num + " "));

    }
    public int[] intersection(int[] nums1, int[] nums2) {
        List<Integer> result=new ArrayList<>();
        Arrays.sort(nums1);
        Arrays.sort(nums2);
        int i=0,j=0;
        while(i<nums1.length && j<nums2.length){
            if(i<nums1.length-1 &&  nums1[i]==nums1[i+1]){
                i++;
            }else if(nums1[i]==nums2[j]){
                result.add(nums1[i]);
                i++;
                j++;
            }else if(nums1[i]<nums2[j]){
                i++;
            }else{
                j++;
            }
        }
        return result.stream().mapToInt(k->k).toArray();
    }


    public int[] intersection1(int[] nums1, int[] nums2) {
        boolean[] intersectionFlags=new boolean[1001];
        List<Integer> result=new ArrayList<>();
        for(int element:nums1){
            intersectionFlags[element]=true;
        }

        for(int element:nums2){
            if(intersectionFlags[element]){
                result.add(element);
                intersectionFlags[element]=false;
            }
        }
        return result.stream().mapToInt(i->i).toArray();
    }
}
