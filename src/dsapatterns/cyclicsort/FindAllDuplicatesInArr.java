package dsapatterns.cyclicsort;

import java.util.ArrayList;
import java.util.List;
//https://leetcode.com/problems/find-all-duplicates-in-an-array/
public class FindAllDuplicatesInArr {
    public static void main(String[] args) {
        FindAllDuplicatesInArr obj=new FindAllDuplicatesInArr();
        int[] nums={4,3,2,7,8,2,3,1};
        List<Integer> result=obj.findDuplicates(nums);
        for(int res:result){
            System.out.print(res+" ");
        }
    }
    public List<Integer> findDuplicates(int[] nums) {
        List<Integer> duplicates=new ArrayList<>();
        int n=nums.length;
        if(n==1){
            return duplicates;
        }
        int index=0;
        for(int i=0;i<n;i++){
            if(nums[i]<0){
                index=(nums[i]*-1)-1;
            }else{
                index=nums[i]-1;
            }

            if(nums[index]<0){
                duplicates.add(index+1);
            }else{
                nums[index]=-nums[index];
            }
        }

        return duplicates;
    }
}
