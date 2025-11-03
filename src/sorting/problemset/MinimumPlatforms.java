package sorting.problemset;

import java.util.Arrays;

//O(n log(n)) Time and O(1) Space
public class MinimumPlatforms {
    public static void main(String[] args) {
        int[] arr = {900, 940, 950, 1100, 1500, 1800};
        int[] dep = {910, 1200, 1120, 1130, 1900, 2000};
        System.out.println(minPlatform(arr, dep));
    }

    public static int minPlatform(int[] arr, int[] dep){
        int n=arr.length;
        int res=0;
        Arrays.sort(arr);
        Arrays.sort(dep);

        int j=0;
        int count=0;
        for(int i=0;i<n;i++){
            while(j<n && dep[j]<arr[i]){
                count--;
                j++;
            }
            count++;
            res=Math.max(res,count);
        }

        return res;
    }
}
