package dsapatterns.cyclicsort;

//https://leetcode.com/problems/missing-number/
public class FindMissingNumber {
    public static void main(String[] args) {
        FindMissingNumber obj=new FindMissingNumber();
        int[] nums={3,0,1};
        int result=obj.missingNumber(nums);
        System.out.print(result);
        System.out.print(obj.missingNumber1(nums));

    }

    public int missingNumber(int[] nums) {
        int n=nums.length;
        int i=0;
        while(i<n){
            if(nums[i]<n && nums[i]!=i){
                swap(nums,i,nums[i]);
            }else{
                i++;
            }
        }

        for(int j=0;j<n;j++){
            if(nums[j]!=j){
                return j;
            }
        }
        return n;
    }

    private void swap(int[] nums, int i, int j ){
        int temp=nums[i];
        nums[i]=nums[j];
        nums[j]=temp;
    }


    public int missingNumber1(int[] nums) {
        int n=nums.length;
        int sum=n*(n+1)/2;
        int numsSum=0;
        for(int i=0;i<n;i++){
            numsSum+=nums[i];
        }

        return sum-numsSum;
    }
}
