package blind75;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class UniqueEmails {
    public static void main(String[] args) {
        String[] emails =  {"test.email+alex@leetcode.com","test.email.leet+alex@code.com"};
        System.out.println(numUniqueEmails(emails));
    }

    public static int numUniqueEmails(String[] emails) {
        Set<String> uniqueEmails=new HashSet();
        Arrays.stream(emails).forEach(email->{
            String[] emailSplit=email.split("@");
            String prefix=emailSplit[0].replace(".","");
            prefix=prefix.split("\\+")[0];
            String suffix=emailSplit[1];
            uniqueEmails.add(prefix+"@"+suffix);
        });
        return uniqueEmails.size();
    }
}
