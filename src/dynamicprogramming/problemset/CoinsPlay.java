package dynamicprogramming.problemset;

import java.util.ArrayList;
import java.util.Arrays;

public class CoinsPlay {
    public static void main(String[] args) {
        int[] coins={8, 15, 3, 7};
        ArrayList<Integer> coinList=new ArrayList<>();
        Arrays.stream(coins).forEach(coinList::add);
        System.out.println(max_win(coinList));
        System.out.println(max_win1(coinList));
    }



    static Integer max_win(ArrayList<Integer> v) {
        // Write your code here.
        int[][] dp=new int[v.size()][v.size()];
        for(int i=0;i<v.size();i++){
            Arrays.fill(dp[i],-1);
        }
        return  max_win_helper(v,0,v.size()-1,dp);

    }

    static int max_win_helper(ArrayList<Integer> v, int i, int j,int[][] dp){
        if(dp[i][j]!=-1){
            return dp[i][j];
        }

        if(i==j){
            return v.get(i);
        }

        if( i+1==j){
            return dp[i][j]=Math.max(v.get(i),v.get(j));
        }
        if(i>j){
            return Integer.MAX_VALUE;
        }



        dp[i][j]= Math.max(v.get(i)+Math.min(max_win_helper(v,i+2,j,dp),max_win_helper(v,i+1,j-1,dp)),
                v.get(j)+ Math.min(max_win_helper(v,i,j-2,dp),max_win_helper(v,i+1,j-1,dp)));
        return dp[i][j];
    }


    static Integer max_win1(ArrayList<Integer> v) {
        // Write your code here.
        int n=v.size();
        int[][] dp=new int[n][n];


        for(int length=1;length<=n;length++){
            for(int i=0;i<=n-length;i++){
                int j=i+length-1;
                if(i==j){
                    dp[i][j]=v.get(i);
                }else if(i+1==j){
                    dp[i][j]=Math.max(v.get(i),v.get(j));
                }else{
                    int choice1=v.get(i)+Math.min(dp[i+1][j-1],dp[i+2][j]);
                    int choice2=v.get(j)+Math.min(dp[i+1][j-1],dp[i][j-2]);
                    dp[i][j]=Math.max(choice1,choice2);
                }
            }
        }
        return dp[0][n-1];
    }


}
