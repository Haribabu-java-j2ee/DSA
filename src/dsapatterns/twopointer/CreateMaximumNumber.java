package dsapatterns.twopointer;

//https://leetcode.com/problems/create-maximum-number/
public class CreateMaximumNumber {
    public static void main(String[] args) {
        int[] nums1=new int[]{3,4,6,5};
        int[] nums2=new int[]{9,1,2,5,8,3};
        int[] maxNumber=new CreateMaximumNumber().maxNumber(nums1,nums2,5);
        for(int num:maxNumber) {
            System.out.print(num + " ");
        }
    }

    //total O((m+n)^3) time
    public int[] maxNumber(int[] nums1, int[] nums2, int k) {
        int m=nums1.length;
        int n=nums2.length;
        int minnums1Required=Math.max(0,k-n);
        int maxnums1Required=Math.min(k,m);
        int[] result=new int[k];
        //O(k) * (O((m+n)^2) for innner)
        for(int i=minnums1Required;i<=maxnums1Required;i++){
            //O(m)
            int[] maxSubseq1=getSubsequece(nums1,i);
            //O(n)
            int[] maxSubseq2=getSubsequece(nums2,k-i);
            //O(m+n) for merge and compare both => O((m+n)^2)
            int[] mergeSubsequnce=merge(maxSubseq1,maxSubseq2);
            if(compare(mergeSubsequnce,result,0,0)){
                result=mergeSubsequnce;
            }
        }
        return result;
    }


    private int[] getSubsequece(int[] nums, int k){
        int n=nums.length;
        int[] stack=new int[k];
        int top=-1;
        int remainingToDiscard=n-k;
        for(int num: nums){
            while(top>=0 && stack[top]<num && remainingToDiscard>0){
                --top;
                --remainingToDiscard;
            }
            if(top+1<k){
                stack[++top]=num;
            }else{
                --remainingToDiscard;
            }
        }
        return stack;
    }

    private int[] merge(int[] nums1, int[] nums2){
        int m=nums1.length;
        int n=nums2.length;
        int i=0,j=0;
        int[] mergedResult=new int[m+n];
        for(int k=0;k<m+n;k++){
            if(compare(nums1, nums2, i, j)){
                mergedResult[k]=nums1[i++];
            }else{
                mergedResult[k]=nums2[j++];
            }
        }
        return mergedResult;
    }

    private boolean compare(int[] nums1, int[] nums2, int i, int j ){
        if(i>=nums1.length){
            return false;
        }
        if(j>=nums2.length){
            return true;
        }

        if(nums1[i]>nums2[j]){
            return true;
        }
        if(nums1[i]<nums2[j]){
            return false;
        }

        return compare(nums1, nums2, i+1, j+1);
    }
}
