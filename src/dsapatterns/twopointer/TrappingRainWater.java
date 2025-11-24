package dsapatterns.twopointer;
//https://leetcode.com/problems/trapping-rain-water/description/
public class TrappingRainWater {
    public static void main(String[] args) {
        int[] input = new int[]{0,1,0,2,1,0,1,3,2,1,2,1,1,0};
        TrappingRainWater obj = new TrappingRainWater();
        int result = obj.trap(input);
        System.out.println("Total trapped water: " + result);
    }
    public int trap(int[] height) {
        int n=height.length;
        int left=0;
        int right=n-1;
        int totalWater=0;
        int leftMax=0;
        int rightMax=0;
        while(left<right){
            if(height[left]<height[right]){
                leftMax=Math.max(leftMax,height[left]);
                totalWater+=leftMax-height[left];
                left++;
            }else{
                rightMax=Math.max(rightMax,height[right]);
                totalWater+=rightMax-height[right];
                right--;
            }
        }
        return totalWater;
    }
}
