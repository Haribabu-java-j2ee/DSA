package dsapatterns.twopointer;

//https://www.educative.io/interview-prep/coding/valid-word-abbreviation
public class ValidWordAbbrevation {
    public static void main(String[] args) {
        String word="internationalization";
        String abbr="i12iz4n";
        System.out.println(validWordAbbreviation(word, abbr));
    }

    public static boolean validWordAbbreviation(String word, String abbr) {
        int wordIndex=0, abbrIndex=0;
        while(abbrIndex<abbr.length()){
            if(Character.isDigit(abbr.charAt(abbrIndex))){
                if(abbr.charAt(abbrIndex)=='0'){
                    return false;
                }
                int num=0;
                while(abbrIndex<abbr.length() && Character.isDigit(abbr.charAt(abbrIndex))){
                    num=num*10+(abbr.charAt(abbrIndex)-'0');
                    abbrIndex++;
                }
                wordIndex+=num;
            }else{
                if(wordIndex>=word.length() || word.charAt(wordIndex) != abbr.charAt(abbrIndex)){
                    return false;
                }
                wordIndex++;
                abbrIndex++;
            }
        }
        return wordIndex==word.length()&& abbrIndex==abbr.length();
    }
}
