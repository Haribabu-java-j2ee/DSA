package dsapatterns.cyclicsort;

//https://leetcode.com/problems/first-missing-positive/
public class FirstMissingPositive {
    public static void main(String[] args) {
        FirstMissingPositive obj=new FirstMissingPositive();
        int[] nums={3,4,-1,1};
        int result=obj.firstMissingPositive(nums);
        System.out.print(result);
    }
    public int firstMissingPositive(int[] nums) {
        int n=nums.length;
        int i=0;
        while(i<n){
            if(nums[i]>0 && nums[i]<n && nums[i]!=nums[nums[i]-1]){
                swap(nums,i, nums[i]-1);
            }else{
                i++;
            }
        }
        for(int j=0;j<n;j++){
            if(nums[j]!=j+1){
                return j+1;
            }
        }
        return n+1;
    }
    private void swap(int[] nums, int i, int j){
        int temp=nums[i];
        nums[i]=nums[j];
        nums[j]=temp;
    }
}
