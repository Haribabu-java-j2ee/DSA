package dsapatterns.twopointer;

//https://leetcode.com/problems/reverse-string
public class ReverseString {
    public static void main(String[] args) {
        char[] s = {'h','e','l','l','o'};
        ReverseString rs=new ReverseString();
        rs.reverseString(s);
        System.out.println(s);
    }

    public void reverseString(char[] s) {
        int left=0;
        int right=s.length-1;
        if(right<=0){
            return;
        }
        while(left<right){
            swap(left,right,s);
            left++;
            right--;
        }
    }
    private void swap(int left,int right,char[] s){
        char temp=s[left];
        s[left]=s[right];
        s[right]=temp;
    }
}
