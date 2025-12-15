package dsapatterns.twopointer;

import java.util.Arrays;

//https://leetcode.com/problems/merge-sorted-array/
public class mergeSortedArr {
    public static void main(String[] args) {
        int[] nums1= {0};
        int[] nums2= {1};
        int m=0,n=1;
        mergeSortedArr obj=new mergeSortedArr();
        obj.merge(nums1, m, nums2, n);
        Arrays.stream(nums1).forEach(num->System.out.print(num+" "));
    }

    public void merge(int[] nums1, int m, int[] nums2, int n) {
        if(n==0){
            return;
        }
        int lastIndex=m+n-1;
        while(m>0 && n>0){
            if(nums1[m-1]>=nums2[n-1]){
                nums1[lastIndex]=nums1[m-1];
                m--;
            }else if(nums1[m-1]<nums2[n-1]){
                nums1[lastIndex]=nums2[n-1];
                n--;
            }
            lastIndex--;
        }

        while(n>0){
            nums1[lastIndex]=nums2[n-1];
            n--;
            lastIndex--;
        }
    }
}
