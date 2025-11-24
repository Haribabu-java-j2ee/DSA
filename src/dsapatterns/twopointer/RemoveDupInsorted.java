package dsapatterns.twopointer;

import java.util.Arrays;

public class RemoveDupInsorted {
    public static void main(String[] args) {
        int[] nums = new int[]{1,1,2};
        RemoveDupInsorted obj = new RemoveDupInsorted();
        int k = obj.removeDuplicates(nums);
        System.out.println("Length after removing duplicates: " + k);
        System.out.println();
        Arrays.stream(nums).forEach(n->System.out.print(n+" "));
    }

    public int removeDuplicates(int[] nums) {
        int n=nums.length;
        if(n==0){
            return 0;
        }else if(n==1){
            return 1;
        }
        int j=0;
        for(int i=0;i<n-1;i++){
            if(nums[j]!=nums[i+1]){
                nums[++j]=nums[i+1];
            }
        }

        return j+1;
    }
}
