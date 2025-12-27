package dsapatterns.twopointer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * =====================================================================================
 * 3SUM (TRIPLET SUM) - Two Pointer Approach
 * =====================================================================================
 * LeetCode: https://leetcode.com/problems/3sum/
 *
 * =====================================================================================
 * INTUITION:
 * =====================================================================================
 * Sort array first. Fix one element, then use two pointers on remaining subarray
 * to find pairs that sum to negative of fixed element. Skip duplicates to avoid
 * duplicate triplets.
 *
 * =====================================================================================
 * EXAMPLE: [-1,0,1,2,-1,-4] → [[-1,-1,2],[-1,0,1]]
 * =====================================================================================
 *
 *   Sorted: [-4,-1,-1,0,1,2]
 *
 *   i=0: nums[i]=-4, target=4
 *        left=1, right=5: -1+2=1 < 4 → left++
 *        ...no triplet found for -4
 *
 *   i=1: nums[i]=-1, target=1
 *        left=2, right=5: -1+2=1 ✓ → Add [-1,-1,2]
 *        left=3, right=4: 0+1=1 ✓ → Add [-1,0,1]
 *
 *   i=2: nums[i]=-1, SKIP (duplicate of i=1)
 *
 *   i=3: nums[i]=0 > 0, BREAK (positive can't sum to 0 with larger positives)
 *
 *   Result: [[-1,-1,2],[-1,0,1]]
 *
 * =====================================================================================
 * COMPLEXITY:
 * =====================================================================================
 *   Time:  O(n²) - O(n log n) sort + O(n²) two-pointer for each element
 *   Space: O(1) - Ignoring output space (O(log n) for sorting in some implementations)
 *
 * =====================================================================================
 * EDGE CASES:
 * =====================================================================================
 *   1. n < 3 → return empty list
 *   2. All positives or all negatives → return empty list
 *   3. All zeros [0,0,0] → return [[0,0,0]]
 *   4. Duplicates [-1,-1,2,-1] → skip duplicates to avoid duplicate triplets
 *
 * =====================================================================================
 * OPTIMIZATION NOTE: Code is optimal. Early break when nums[i] > 0 is a good
 * optimization (positive numbers can't sum to 0 with larger positives).
 * =====================================================================================
 */
public class TrippletSum {
    public static void main(String[] args) {
        int[] nums = new int[]{-1,-1,2,-1,-1,2};
        TrippletSum obj = new TrippletSum();
        System.out.println(obj.threeSum(nums));
    }
    
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        int n = nums.length;
        
        if (n < 3) {
            return result;
        }

        Arrays.sort(nums);

        for (int i = 0; i < n - 2; i++) {
            // Skip duplicate elements
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            // Positive numbers can't sum to 0 with larger positives
            if (nums[i] > 0) {
                break;
            }
            
            int left = i + 1, right = n - 1;
            int target = -nums[i];
            
            while (left < right) {
                int sum = nums[left] + nums[right];
                
                if (sum == target) {
                    result.add(Arrays.asList(nums[i], nums[left], nums[right]));
                    left++;
                    right--;
                    
                    // Skip duplicates
                    while (left < right && nums[left] == nums[left - 1]) {
                        left++;
                    }
                    while (left < right && nums[right] == nums[right + 1]) {
                        right--;
                    }
                } else if (sum < target) {
                    left++;
                } else {
                    right--;
                }
            }
        }
        
        return result;
    }
}
