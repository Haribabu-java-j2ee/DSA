package dsapatterns.twopointer;

//https://leetcode.com/problems/valid-palindrome-ii/
public class ValidPalindromeII {
    public static void main(String[] args) {
        String s="abca";
        ValidPalindromeII obj=new ValidPalindromeII();
        System.out.println(obj.validPalindrome(s));
    }
    public boolean validPalindrome(String s) {
        int left=0;
        int right=s.length()-1;
        while(left<right){
            if(s.charAt(left)!=s.charAt(right)){
                return isPalindrome(s, left+1,right)||isPalindrome(s, left,right-1);
            }
            left++;
            right--;
        }
        return true;
    }

    private boolean isPalindrome(String s, int left, int right){
        while(left<right){
            if(s.charAt(left)!=s.charAt(right)){
                return false;
            }
            left++;
            right--;
        }
        return true;
    }
}
