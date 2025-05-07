package sorting;

import java.util.ArrayList;
import java.util.Arrays;

public class RadixSort {
    public static void main(String[] args) {
        int arr[] = { 170, 45, 75, 90, 802, 24, 2, 66 };
       /* radixSort(arr);
        Arrays.stream(arr).forEach(System.out::println);
*/
        System.out.println("-------------");

        ArrayList<Integer> arrayList = new ArrayList<>();
        arrayList.add(170);
        arrayList.add(45);
        arrayList.add(75);
        arrayList.add(90);
        arrayList.add(802);
        arrayList.add(24);
        arrayList.add(2);
        arrayList.add(66);
        radix_sort(arrayList);
        arrayList.forEach(System.out::println);
    }



    static void radixSort(int[] arr){
        int max=arr[0];
        for(int i=1;i<arr.length;i++){
            max=Math.max(max,arr[i]);
        }

        for(int exp=1;max/exp>0;exp*=10){
            countSort(arr,arr.length,exp);
        }
    }

    static void countSort(int[] arr,int n,int exp){
        int[] count = new int[10];
        int[] output=new int[n];
        Arrays.fill(count,0);

        for(int i=0;i<n;i++){
            count[(arr[i]/exp)%10]++;
        }

        for(int i=1;i<10;i++){
            count[i]+=count[i-1];
        }

        for(int i=n-1;i>=0;i--){
            output[count[(arr[i]/exp)%10]-1]=arr[i];
            count[(arr[i]/exp)%10]--;
        }

        for(int i=0;i<n;i++){
            arr[i]=output[i];
        }
    }



    static ArrayList<Integer> radix_sort(ArrayList<Integer> arr) {
        int max=arr.get(0);

        for(int i=1;i<arr.size();i++){
            max=Math.max(max,arr.get(i));
        }

        for(int exp=1;max/exp>0;exp*=10){
            countSort(arr,arr.size(),exp);
        }
        return arr;
    }

    static void countSort(ArrayList<Integer> arr, int n, int exp){
        int[] count=new int[10];
        int[] output=new int[n];

        Arrays.fill(count,0);
        for(int i=0;i<n;i++){
            count[(arr.get(i)/exp)%10]++;
        }

        for(int i=1;i<10;i++){
            count[i]+=count[i-1];
        }

        for(int i=n-1;i>=0;i--){
            output[count[(arr.get(i)/exp)%10]-1]=arr.get(i);
            count[(arr.get(i)/exp)%10]--;
        }

        for(int i=0;i<n;i++){
            arr.set(i,output[i]);
        }
    }

}
