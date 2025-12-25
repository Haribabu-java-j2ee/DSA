import java.util.*;

public class SortCheck {
    // Function using j = j + 1 update
    public static void main(String[] args) {
        int n = 10000;
        StringBuilder sb = new StringBuilder(n);
        for (int i = 0; i < n - 1; i++) {
            sb.append('a');
        }
        sb.append('b');
        String s = sb.toString();
        System.out.println("Input length: " + s.length());
        runNaive(s);
        runOptimized(s);
    }

    static void runNaive(String s) {
        int i = 0, j = 1, steps = 0;
        int n = s.length();
        System.out.println("\n--- Naive Approach ---");
        while (j < n) {
            int k = 0;
            // Compare characters at i+k and j+k
            while (j + k < n && s.charAt(i + k) == s.charAt(j + k)) {
                k++;
                steps++;
            }
            // Trace step
            if (j + k < n) {
                System.out.println("i=" + i + ", j=" + j + ", k=" + k + ", s[i+k]=" + s.charAt(i + k) + ", s[j+k]=" + s.charAt(j + k));
            } else {
                System.out.println("i=" + i + ", j=" + j + ", k=" + k + " (end of string)");
            }
            // Naive pointer update
            if (j + k < n && s.charAt(i + k) < s.charAt(j + k)) {
                i = j;
                j = j + 1; // Naive update: always just advance by 1
            } else {
                j = j + k + 1;
            }
            steps++; // Count outer loop step
        }
        System.out.println("Naive steps: " + steps);
        System.out.println("Naive result: " + s.substring(i));
    }

    static void runOptimized(String s) {
        int i = 0, j = 1, steps = 0;
        int n = s.length();
        System.out.println("\n--- Optimized Approach ---");
        while (j < n) {
            int k = 0;
            // Compare characters at i+k and j+k
            while (j + k < n && s.charAt(i + k) == s.charAt(j + k)) {
                k++;
                steps++;
            }
            // Trace step
            if (j + k < n) {
                System.out.println("i=" + i + ", j=" + j + ", k=" + k + ", s[i+k]=" + s.charAt(i + k) + ", s[j+k]=" + s.charAt(j + k));
            } else {
                System.out.println("i=" + i + ", j=" + j + ", k=" + k + " (end of string)");
            }
            // Optimized pointer update
            if (j + k < n && s.charAt(i + k) < s.charAt(j + k)) {
                int tempIndex = i;
                i = j;
                j = Math.max(j + 1, tempIndex + k + 1); // Optimized: skip ahead
            } else {
                j = j + k + 1;
            }
            steps++; // Count outer loop step
        }
        System.out.println("Optimized steps: " + steps);
        System.out.println("Optimized result: " + s.substring(i));
    }
}
