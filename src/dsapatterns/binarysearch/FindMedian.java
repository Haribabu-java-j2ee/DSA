package dsapatterns.binarysearch;

//https://leetcode.com/problems/median-of-two-sorted-arrays
public class FindMedian {
    public static void main(String[] args) {
        FindMedian obj = new FindMedian();
        int[] nums1 = {1, 2,3,4};
        int[] nums2 = {5,6,7,9,10,11};
        System.out.println(obj.findMedianSortedArrays(nums1, nums2));
        System.out.println(obj.findMedianSortedArrays1(nums1, nums2));

    }
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int n=nums1.length;
        int m=nums2.length;
        int i=0,j=0,m1=0,m2=0;

        for(int count=0;count<=(n+m)/2;count++){
            m2=m1;
            if(i!=n && j!=m){
                if(nums1[i]>nums2[j]){
                    m1=nums2[j++];
                }else{
                    m1=nums1[i++];
                }
            }else if(i<n){
                m1=nums1[i++];
            }else{
                m1=nums2[j++];
            }
        }

        if((n+m)%2==1){
            return (double)m1;
        }else{
            int ans=m1+m2;
            return ans/2.0;
        }
    }

    public double findMedianSortedArrays1(int[] nums1, int[] nums2) {
        int n1 = nums1.length, n2 = nums2.length;

        // Ensure nums1 is the smaller array for simplicity
        if (n1 > n2)
            return findMedianSortedArrays1(nums2, nums1);

        int n = n1 + n2;
        int left = (n1 + n2 + 1) / 2; // Calculate the left partition size
        int low = 0, high = n1;

        while (low <= high) {
            int mid1 = (low + high) >> 1; // Calculate mid index for nums1
            int mid2 = left - mid1; // Calculate mid index for nums2

            int l1 = Integer.MIN_VALUE, l2 = Integer.MIN_VALUE, r1 = Integer.MAX_VALUE, r2 = Integer.MAX_VALUE;

            // Determine values of l1, l2, r1, and r2
            if (mid1 < n1)
                r1 = nums1[mid1];
            if (mid2 < n2)
                r2 = nums2[mid2];
            if (mid1 - 1 >= 0)
                l1 = nums1[mid1 - 1];
            if (mid2 - 1 >= 0)
                l2 = nums2[mid2 - 1];

            if (l1 <= r2 && l2 <= r1) {
                // The partition is correct, we found the median
                if (n % 2 == 1)
                    return Math.max(l1, l2);
                else
                    return ((double)(Math.max(l1, l2) + Math.min(r1, r2))) / 2.0;
            }
            else if (l1 > r2) {
                // Move towards the left side of nums1
                high = mid1 - 1;
            }
            else {
                // Move towards the right side of nums1
                low = mid1 + 1;
            }
        }

        return 0; // If the code reaches here, the input arrays were not sorted.
    }
}
