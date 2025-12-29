package dsapatterns.fastslowpointer;

/**
 * =====================================================================================
 * FIND DUPLICATE NUMBER - Floyd's Cycle Detection on Array
 * =====================================================================================
 * LeetCode: https://leetcode.com/problems/find-the-duplicate-number/
 *
 * =====================================================================================
 * INTUITION:
 * =====================================================================================
 * Treat array as linked list: index → nums[index]. Since values are 1 to n in array
 * of size n+1, duplicate creates a cycle. Entry point of cycle = duplicate number.
 * Use Floyd's algorithm: Phase 1 finds meeting point, Phase 2 finds cycle entry.
 *
 * =====================================================================================
 * EXAMPLE: nums = [1,3,4,2,2] → returns 2
 * =====================================================================================
 *
 *   Array as graph: 0→1→3→2→4→2 (cycle at 2)
 *                              ↑___↓
 *   
 *   Phase 1 - Find meeting point:
 *     slow: nums[0]=1 → nums[1]=3 → nums[3]=2
 *     fast: nums[nums[0]]=3 → nums[nums[3]]=4 → nums[nums[4]]=2
 *     Meet when slow=2, fast=2
 *   
 *   Phase 2 - Find cycle entry:
 *     head: nums[0]=1 → nums[1]=3 → nums[3]=2
 *     slow: nums[2]=4 → nums[4]=2
 *     Meet at value 2 → DUPLICATE FOUND
 *
 * =====================================================================================
 * COMPLEXITY:
 * =====================================================================================
 *   Time:  O(n) - Each element visited at most twice
 *   Space: O(1) - Only pointers, no extra space
 *
 * =====================================================================================
 * EDGE CASES:
 * =====================================================================================
 *   1. Duplicate at start [2,2,1] → cycle entry is 2
 *   2. All same values [1,1,1,1] → immediate cycle at 1
 */
public class FindDuplicateNum {
    public static void main(String[] args) {
        FindDuplicateNum obj=new FindDuplicateNum();
        int[] nums={1,3,4,2,2};
        System.out.println(obj.findDuplicate(nums));
    }
    public int findDuplicate(int[] nums) {
        int slow=nums[0];
        int fast=nums[0];
        while(true){
            slow=nums[slow];
            fast=nums[nums[fast]];
            if(slow==fast){
                break;
            }
        }
        int head=nums[0];
        while(head!=slow){
            head=nums[head];
            slow=nums[slow];
        }
        return head;
    }
}
