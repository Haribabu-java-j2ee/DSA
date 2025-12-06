package dsapatterns.twopointer;

public class ValidPalindrome {
    public static void main(String[] args) {
        String s="A man, a plan, a canal: Panama";
        ValidPalindrome obj=new ValidPalindrome();
        System.out.println(obj.isPalindrome(s));
    }
    public boolean isPalindrome(String s) {
        s=s.toLowerCase().replaceAll("[^a-z0-9]","");
        if(s.length()==0){
            return true;
        }
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

    //go with this instead of replace all
    public boolean isPalindromeMoreOptimal(String s) {
        s=s.toLowerCase();
        int n=s.length();
        if(n==1){
            return true;
        }
        int left=0;
        int right=n-1;
        while(left<right){
            if(!Character.isLetterOrDigit(s.charAt(left))){
                left++;
                continue;
            }
            if(!Character.isLetterOrDigit(s.charAt(right))){
                right--;
                continue;
            }
            if(s.charAt(left)!=s.charAt(right)){
                return false;
            }
            left++;
            right--;
        }
        return true;
    }
}
