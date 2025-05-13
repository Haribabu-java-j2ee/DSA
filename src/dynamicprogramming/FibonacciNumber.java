package dynamicprogramming;

import java.util.Arrays;

public class FibonacciNumber {
    static int[] fibArr;
    public static void main(String[] args) {
        int n=6;
        System.out.println(nthFibonacciNumberRecursive(n));

        //topdown
        fibArr=new int[n+1];
        Arrays.fill(fibArr,-1);
        fibArr[0]=0;
        fibArr[1]=1;
        System.out.println(nthFibonacciNumberTopDown(n));


        //bottom up
        System.out.println(nthFibonacciNumberBottomUp(n));

        //optimised bottom up
        System.out.println(nthFibonacciNumberBottomUpOtimisedSpace(n));

    }

    private static int nthFibonacciNumberBottomUp(int n) {
        int[] fibArrBottomUp=new int[n+1];
        fibArrBottomUp[0]=0;
        fibArrBottomUp[1]=1;
        for(int i=2;i<=n;i++){
            fibArrBottomUp[i]=fibArrBottomUp[i-1]+fibArrBottomUp[i-2];
        }
        return fibArrBottomUp[n];
    }

    //more optimised space O(1)
    private static int nthFibonacciNumberBottomUpOtimisedSpace(int n) {
        int[] fibArrBottomUp=new int[3];
        fibArrBottomUp[0]=0;
        fibArrBottomUp[1]=1;
        for(int i=2;i<=n;i++){
            if(i%3==0){
                fibArrBottomUp[0]=fibArrBottomUp[1]+fibArrBottomUp[2];
            }else if(i%3==1){
                fibArrBottomUp[1]=fibArrBottomUp[2]+fibArrBottomUp[0];
            }else if(i%3==2){
                fibArrBottomUp[2]=fibArrBottomUp[0]+fibArrBottomUp[1];
            }
        }

        return fibArrBottomUp[n%3];
    }


    private static int nthFibonacciNumberTopDown(int n) {
        if(fibArr[n]!=-1){
            return fibArr[n];
        }
        fibArr[n]=nthFibonacciNumberTopDown(n-1)+nthFibonacciNumberTopDown(n-2);
        return fibArr[n];
    }

    private static int nthFibonacciNumberRecursive(int n) {
        if(n==0 || n==1){
            return n;
        }
        return nthFibonacciNumberRecursive(n-1)+nthFibonacciNumberRecursive(n-2);
    }


}

