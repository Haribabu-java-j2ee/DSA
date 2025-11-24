package dsapatterns.twopointer;

//https://leetcode.com/problems/container-with-most-water/
public class ContainerWithMostWater {
    public static void main(String[] args) {
        int[] height = new int[]{1,8,6,2,5,4,8,3,7};
        ContainerWithMostWater obj = new ContainerWithMostWater();
        System.out.println(obj.maxArea(height));
    }
    public int maxArea(int[] height) {
        int n=height.length;
        int maxArea=0;
        if(n==0){
            return maxArea;
        }
        int left=0;
        int right=n-1;
        while(left<right){
            maxArea=Math.max(maxArea, (right-left)* Math.min(height[left],height[right]));
            if(height[left]<height[right]){
                left++;
            }else{
                right--;
            }
        }
        return maxArea;
    }
}
