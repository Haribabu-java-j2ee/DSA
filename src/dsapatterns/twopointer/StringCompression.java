package dsapatterns.twopointer;
//https://leetcode.com/problems/string-compression/
public class StringCompression {
    public static void main(String[] args) {
        char[] chars=new char[]{'a','a','b','b','c','c','c'};
        StringCompression obj=new StringCompression();
        int len=obj.compress(chars);
        System.out.println("Length after compression: "+len);
        System.out.println();
        for(int i=0;i<len;i++){
            System.out.print(chars[i]+" ");
        }
    }
    public int compress(char[] chars) {
        int ans=0;
        int n=chars.length;
        for(int i=0;i<n;){
            char letter=chars[i];
            int count=0;
            while(i<n && letter==chars[i]){
                count++;
                i++;
            }
            chars[ans++]=letter;
            if(count>1){
                for(char ch:String.valueOf(count).toCharArray()){
                    chars[ans++]=ch;
                }
            }
        }
        return ans;
    }
}
