package dsapatterns.cyclicsort;

//https://leetcode.com/problems/set-mismatch/
public class SetMismatch {
    public static void main(String[] args) {
        SetMismatch obj=new SetMismatch();
        int[] nums={1,2,2,4};
        int[] result=obj.findErrorNums(nums);
        for(int res:result){
            System.out.print(res+" ");
        }
    }
    public int[] findErrorNums(int[] nums) {
        int missing=-1, duplicate=-1, index=-1;
        for(int i=0;i<nums.length;i++){
            if(nums[i]<0){
                index=(nums[i]*-1)-1;
            }else{
                index=nums[i]-1;
            }

            if(nums[index]<0){
                duplicate=index+1;
            }else{
                nums[index]=-nums[index];
            }
        }

        for(int i=0;i<nums.length;i++){
            if(nums[i]>0){
                missing=i+1;
            }
        }
        return new int[]{duplicate,missing};
    }
}
