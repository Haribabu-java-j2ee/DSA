package dsa.categories.strings;

public class ReverseString {
    public static void main(String[] args) {
        char[] s = {'h','e','l','l','o'};
        ReverseString rs=new ReverseString();
        rs.reverseString(s);
        System.out.println(s);
        String str = "abdcfe";
        System.out.println(rs.reverseStringapp1(str));
    }

    private String reverseStringapp1(String str) {
        StringBuilder rs=new StringBuilder();
        for(int i=str.length()-1;i>=0;i--){
            rs.append(str.charAt(i));
        }
        return rs.toString();
    }

    //https://leetcode.com/problems/reverse-string/description/
    public void reverseString(char[] s) {
        int left=0;
        int right=s.length-1;
        while(left<right){
            char temp=s[left];
            s[left]=s[right];
            s[right]=temp;
            left++;
            right--;
        }
    }


}
