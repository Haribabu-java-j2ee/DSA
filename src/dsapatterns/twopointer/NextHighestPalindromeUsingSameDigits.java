package dsapatterns.twopointer;

//https://www.geeksforgeeks.org/dsa/next-higher-palindromic-number-using-set-digits/
public class NextHighestPalindromeUsingSameDigits {
    public static void main(String[] args) {
        String num = "12321";

        System.out.println(findNextPalindrome(num));
    }
    public static String findNextPalindrome(String numStr) {
        char[] nums=numStr.toCharArray();
        int n=nums.length;
        //121 is example
        if(n<=3){
            return "";
        }
        int i,j;
        int mid=n/2-1;
        for(i=mid-1;i>=0;i--){
            if(nums[i]<nums[i+1]){
                break;
            }
        }
        //numbers are in strictly decreasing order in 1st half not possible for next number
        if(i<0){
            return "";
        }
        int smallest=i+1;
        for(j=i+2;j<=mid;j++){
            if(nums[j]>nums[i] && nums[j]<=nums[smallest]){
                smallest=j;
            }
        }
        char temp=nums[i];
        nums[i]=nums[smallest];
        nums[smallest]=temp;

        temp=nums[n-i-1];
        nums[n-i-1]=nums[n-smallest-1];
        nums[n-smallest-1]=temp;
        reverse(nums, i+1,mid);
        if(n%2==0){
            reverse(nums,mid+1,n-i-2);
        }else{
            reverse(nums,mid+2,n-i-2);
        }
        return new String(nums);
    }

    private static void reverse(char[] nums, int i,int j){
        while(i<j){
            char temp=nums[i];
            nums[i++]=nums[j];
            nums[j--]=temp;
        }
    }
}
