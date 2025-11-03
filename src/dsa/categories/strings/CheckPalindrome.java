package dsa.categories.strings;

public class CheckPalindrome {
    public static void main(String[] args) {
        CheckPalindrome ch=new CheckPalindrome();
        System.out.println(ch.isPalindrome("A man, a plan, a canal: Panama"));
        String str = "haribabu";
        int left = 0;
        int right = str.length() - 1;
        boolean isValid = true;
        while (left < right) {
            if (str.charAt(left) != str.charAt(right)) {
                isValid = false;
                break;
            }
            left++;
            right--;
        }
        if (isValid) {
            System.out.println("valid palindrome");
        } else {
            System.out.println("not a valid palindrome");
        }
    }

    //https://leetcode.com/problems/valid-palindrome/submissions/1819129592/
    public boolean isPalindrome(String s) {
        s=s.toLowerCase().replaceAll("[^a-z0-9]","");
        int left=0;
        int right=s.length()-1;
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
