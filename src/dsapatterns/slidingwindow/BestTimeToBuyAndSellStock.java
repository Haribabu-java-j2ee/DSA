package dsapatterns.slidingwindow;
//https://leetcode.com/problems/best-time-to-buy-and-sell-stock/submissions/1878626896/
public class BestTimeToBuyAndSellStock {
    public static void main(String[] args) {
        int[] prices={7,1,5,3,6,4};
        System.out.println(maxProfit(prices));
    }
    public static int maxProfit(int[] prices) {

        // Replace this placeholder return statement with your code
        int maxProfit=0;
        int minSoFar=prices[0];
        for(int i=1;i<prices.length;i++){
            minSoFar=Math.min(minSoFar,prices[i]);
            maxProfit=Math.max(maxProfit,prices[i]-minSoFar);
        }
        return maxProfit;
    }
}
