package dsapatterns.fastslowpointer;

//https://leetcode.com/problems/find-the-duplicate-number/
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
