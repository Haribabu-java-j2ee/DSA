package SystemDesignPrograms;

import java.util.*;

public class URLShortner {

    public static void main(String[] args) {
        long currentval=1000000;
        int val= (int) (currentval/100);
        System.out.println(val);
    }
    Map<String, String> urlMap=new HashMap();
    static int count;
    static char[] digits=new char[64];
    static boolean filledDigits=false;
    static void generateDigits(){
        filledDigits=true;
        int i=0;
       for(char c='a';c<='z';c++){
        digits[i]=c;
        i++;
       }
        for(char c='A';c<='Z';c++){
        digits[i]=c;
        i++;
       }
       for(int j=0;j<10;j++){
        digits[i]=Character.forDigit(j, 10);
        i++;
       }
       digits[62]='-';
       digits[63]='_';
    }

    //assumption that we are getting only the back half of the url
    // Encodes a URL to a shortened URL.
    public String encode(String longUrl) {

        if(!filledDigits){
            generateDigits();
        }
        count++;
        int currentVal=count;
        List<Character> charList=new ArrayList();
        while(currentVal>=64){
            int quotient=currentVal/64;
            int reminder=currentVal%64;
            charList.add(digits[reminder]);
            currentVal=quotient;
        }
        if(currentVal>0){
            charList.add(digits[currentVal]);
        }
        Collections.reverse(charList);
        StringBuilder shortUrl=new StringBuilder();
        for(char ch:charList){
            shortUrl.append(ch);
        }
        urlMap.put(String.valueOf(shortUrl),longUrl);
        return String.valueOf(shortUrl);
    }

    // Decodes a shortened URL to its original URL.
    public String decode(String shortUrl) {
        return urlMap.getOrDefault(shortUrl,"");
    }
}

// Your Codec object will be instantiated and called as such:
// Codec codec = new Codec();
// codec.decode(codec.encode(url));