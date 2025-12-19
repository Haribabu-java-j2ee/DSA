package dsapatterns.twopointer;

//https://leetcode.com/problems/minimum-number-of-moves-to-make-palindrome/
//O(n^2) time complexity and O(1) space complexity
public class MinMovesToMakePalindrome {
    public static void main(String[] args) {
        String s = "skwhhaaunskegmdtutlgtteunmuuludii";
        MinMovesToMakePalindrome obj = new MinMovesToMakePalindrome();
        System.out.println(obj.minMovesToMakePalindrome(s));
    }
    public int minMovesToMakePalindrome(String s) {
        char[] charArr=s.toCharArray();
        int left=0;
        int right=charArr.length-1;
        int result=0;
        while(left<right){
            int l=left;
            int r=right;
            while(charArr[l]!=charArr[r]){
                r--;
            }
            if(l==r){
                swap(charArr,r,r+1);
                result++;
                continue;
            }else{
                while(r<right){
                    swap(charArr,r,r+1);
                    result++;
                    r++;
                }
            }
            left++;
            right--;
        }
        return result;
    }

    private void swap(char[] charArr, int i, int j){
        char temp=charArr[i];
        charArr[i]=charArr[j];
        charArr[j]=temp;
    }
}
