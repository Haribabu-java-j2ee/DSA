package dsapatterns.twopointer;

/**
 * =====================================================================================
 * CREATE MAXIMUM NUMBER - Two Pointer + Greedy Approach
 * =====================================================================================
 * LeetCode: https://leetcode.com/problems/create-maximum-number/
 *
 * =====================================================================================
 * INTUITION:
 * =====================================================================================
 * Try all splits: take i digits from nums1 and (k-i) from nums2.
 * For each split: 1) Get max subsequence of required length from each array,
 * 2) Merge them to form largest number. Compare all possibilities.
 *
 * =====================================================================================
 * EXAMPLE: nums1=[3,4,6,5], nums2=[9,1,2,5,8,3], k=5 → [9,8,6,5,3]
 * =====================================================================================
 *
 *   Try i=0: 0 from nums1, 5 from nums2
 *            getSubseq(nums2, 5) = [9,5,8,3] - wait, need 5 elements
 *            Actually: [9,2,5,8,3] or [9,1,5,8,3]... 
 *            Best: [9,5,8,3] - no, that's 4. [9,1,2,5,8] - but we want max
 *            getSubseq(nums2, 5) = [9,2,5,8,3]
 *
 *   Try i=1: 1 from nums1, 4 from nums2
 *            getSubseq(nums1, 1) = [6]
 *            getSubseq(nums2, 4) = [9,5,8,3]
 *            merge([6], [9,5,8,3]) = [9,6,5,8,3]
 *
 *   Try i=2: 2 from nums1, 3 from nums2
 *            getSubseq(nums1, 2) = [6,5]
 *            getSubseq(nums2, 3) = [9,8,3]
 *            merge([6,5], [9,8,3]) = [9,8,6,5,3] ← BEST!
 *
 *   ...continue for other splits...
 *
 *   Result: [9,8,6,5,3]
 *
 * =====================================================================================
 * COMPLEXITY:
 * =====================================================================================
 *   Time:  O(k × (m+n)²) - k splits, each with O(m+n) subsequence + O((m+n)²) merge
 *   Space: O(k) - For storing result and subsequences
 *
 * =====================================================================================
 * EDGE CASES:
 * =====================================================================================
 *   1. k = m + n → use all digits from both arrays
 *   2. One array empty → take k from other array
 *   3. k = 0 → return empty array
 *   4. All same digits → any arrangement works
 *
 * =====================================================================================
 * OPTIMIZATION NOTE: Code is correct. The getSubsequence uses a monotonic stack
 * approach which is optimal for finding max subsequence. The recursive compare
 * for merge is correct but could use iterative approach.
 * =====================================================================================
 */
public class CreateMaximumNumber {
    public static void main(String[] args) {
        int[] nums1 = new int[]{3,4,6,5};
        int[] nums2 = new int[]{9,1,2,5,8,3};
        int[] maxNumber = new CreateMaximumNumber().maxNumber(nums1, nums2, 5);
        for (int num : maxNumber) {
            System.out.print(num + " ");
        }
    }

    /**
     * Main method: Try all valid splits and find the maximum
     * Time: O(k × (m+n)²)
     */
    public int[] maxNumber(int[] nums1, int[] nums2, int k) {
        int m = nums1.length;
        int n = nums2.length;
        int minNums1Required = Math.max(0, k - n);  // At least this many from nums1
        int maxNums1Required = Math.min(k, m);       // At most this many from nums1
        int[] result = new int[k];
        
        // Try each valid split
        for (int i = minNums1Required; i <= maxNums1Required; i++) {
            int[] maxSubseq1 = getSubsequece(nums1, i);
            int[] maxSubseq2 = getSubsequece(nums2, k - i);
            int[] mergeSubsequnce = merge(maxSubseq1, maxSubseq2);
            
            if (compare(mergeSubsequnce, result, 0, 0)) {
                result = mergeSubsequnce;
            }
        }
        
        return result;
    }

    /**
     * Get maximum subsequence of length k using monotonic stack
     * Time: O(n)
     */
    private int[] getSubsequece(int[] nums, int k) {
        int n = nums.length;
        int[] stack = new int[k];
        int top = -1;
        int remainingToDiscard = n - k;
        
        for (int num : nums) {
            // Pop smaller elements if we can still discard
            while (top >= 0 && stack[top] < num && remainingToDiscard > 0) {
                --top;
                --remainingToDiscard;
            }
            
            if (top + 1 < k) {
                stack[++top] = num;
            } else {
                --remainingToDiscard;
            }
        }
        
        return stack;
    }

    /**
     * Merge two subsequences to form maximum number
     * Time: O((m+n)²) due to recursive compare
     */
    private int[] merge(int[] nums1, int[] nums2) {
        int m = nums1.length;
        int n = nums2.length;
        int i = 0, j = 0;
        int[] mergedResult = new int[m + n];
        
        for (int k = 0; k < m + n; k++) {
            if (compare(nums1, nums2, i, j)) {
                mergedResult[k] = nums1[i++];
            } else {
                mergedResult[k] = nums2[j++];
            }
        }
        
        return mergedResult;
    }

    /**
     * Compare two arrays lexicographically from given indices
     * Returns true if nums1[i:] >= nums2[j:]
     */
    private boolean compare(int[] nums1, int[] nums2, int i, int j) {
        if (i >= nums1.length) {
            return false;
        }
        if (j >= nums2.length) {
            return true;
        }
        if (nums1[i] > nums2[j]) {
            return true;
        }
        if (nums1[i] < nums2[j]) {
            return false;
        }
        return compare(nums1, nums2, i + 1, j + 1);
    }
}
