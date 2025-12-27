package dsapatterns.greedy;

//https://leetcode.com/problems/valid-parenthesis-string/description/
public class ValidParanthesisString {
    public static void main(String[] args) {
        ValidParanthesisString obj = new ValidParanthesisString();
        String s = "(*))";
        System.out.println(obj.checkValidString(s));
    }
    public boolean checkValidString(String s) {
        // minOpen: minimum possible open '{' after current character
        // maxOpen: maximum possible open '{' after current character
        int minOpen = 0;
        int maxOpen = 0;

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);

            if (c == '(') {
                minOpen++;
                maxOpen++;
            } else if (c == ')') {
                minOpen--;
                maxOpen--;
            } else if (c == '*') {
                // If '*' is '}', minOpen decreases
                minOpen--;
                // If '*' is '{', maxOpen increases
                maxOpen++;
                // If '*' is empty, neither changes (effectively handled by range)
            }

            // If maxOpen is negative, we have more '}' than possible '{' and '*'
            if (maxOpen < 0) return false;

            // minOpen cannot be negative; if it becomes -1, reset to 0
            // (meaning we treated some '*' as empty or '{' instead of '}')
            if (minOpen < 0) minOpen = 0;
        }

        // The expression is valid if it's possible to have 0 open braces
        return minOpen == 0;
    }
}
