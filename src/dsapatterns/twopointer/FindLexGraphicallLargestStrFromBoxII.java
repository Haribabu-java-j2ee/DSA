package dsapatterns.twopointer;

//https://algo.monster/liteproblems/3406
//https://www.educative.io/interview-prep/coding/find-the-lexicographically-largest-string-from-box-ii
public class FindLexGraphicallLargestStrFromBoxII {
    public static void main(String[] args) {
        FindLexGraphicallLargestStrFromBoxII obj = new FindLexGraphicallLargestStrFromBoxII();
        String word = "abaaaaaaa";
        int numFriends = 3;
        String result = obj.answerString(word, numFriends);
        System.out.println("The lexicographically largest substring is: " + result);
    }


    // Function to find the lexicographically largest string
    public String answerString(String word, int numFriends) {
        // If there's only one friend, no split is needed; return the entire string
        if (numFriends == 1) {
            return word;
        }

        int n = word.length();
        int i = 0, j = 1;  // i: start index of current best substring, j: candidate start index

        while (j < n) {
            int k = 0;
            // Compare characters at i+k and j+k as long as they're equal and in bounds
            while (j + k < n && word.charAt(i + k) == word.charAt(j + k)) {
                k++;
            }

            // If the candidate substring starting at j is better, update i
            if (j + k < n && word.charAt(i + k) < word.charAt(j + k)) {
                int tempIndex = i;  // save current i to compute safe next j
                i = j;      // move i to better candidate
                j = Math.max(j + 1, tempIndex + k + 1);  // skip redundant comparisons
            } else {
                // Otherwise, continue searching by skipping the compared section
                j = j + k + 1;
            }
        }

        // Return the best substring of required length: len(word) - numFriends + 1
        return word.substring(i, Math.min(n, i + n - numFriends + 1));
    }
}
