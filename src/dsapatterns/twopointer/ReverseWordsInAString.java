package dsapatterns.twopointer;

//https://leetcode.com/problems/reverse-words-in-a-string
public class ReverseWordsInAString {
    public static void main(String[] args) {
        String sentence="  hello world  ";
        ReverseWordsInAString obj=new ReverseWordsInAString();
        String reversedSentence=obj.reverseWords(sentence);
        System.out.println(reversedSentence);
        System.out.println(obj.reverseWords1(sentence));

    }
    public  String reverseWords(String sentence) {

        // Replace this placeholder return statement with your code
        String[] words= sentence.trim().split("\\s+");
        int left=0;
        int right=words.length-1;
        while(left<right){
            swap(words, left, right);
            left++;
            right--;
        }

        return String.join(" ",words);
    }
    private  void  swap(String[] words, int i, int j){
        String temp=words[i];
        words[i]=words[j];
        words[j]=temp;
    }

    public String reverseWords1(String s) {
        int n=s.length();
        char[] charArr=s.toCharArray();
        reverse(charArr,0,n-1);
        int i=0;
        int right=0;
        for(int left=0;left<n;++left){
            if(charArr[left] !=' '){
                if(i!=0){
                    charArr[i++]=' ';
                }
                right=left;
                while(right<n && charArr[right]!=' '){
                    charArr[i++]=charArr[right++];
                }
                reverse(charArr,i-(right-left),i-1);
                left=right;
            }
        }
        return new String(charArr,0,i);
    }
    private void reverse(char[] charArr, int left, int right){
        while(left<right){
            char temp=charArr[left];
            charArr[left++]=charArr[right];
            charArr[right--]=temp;
        }
    }

}
