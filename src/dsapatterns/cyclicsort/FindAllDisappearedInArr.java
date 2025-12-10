package dsapatterns.cyclicsort;

import java.util.ArrayList;
import java.util.List;

//https://leetcode.com/problems/find-all-numbers-disappeared-in-an-array/description/
public class FindAllDisappearedInArr {
    public static void main(String[] args) {
        FindAllDisappearedInArr obj=new FindAllDisappearedInArr();
        int[] nums={4,3,2,7,8,2,3,1};
        List<Integer> result=obj.findDisappearedNumbers1(nums);
        for(int res:result){
            System.out.print(res+" ");
        }
    }

    public List<Integer> findDisappearedNumbers(int[] nums) {
        int n=nums.length;
        boolean[] visited=new boolean[n+1];
        List<Integer> missingNumbers=new ArrayList<>();
        if(n==0){
            return missingNumbers;
        }
        for(int i=0;i<n;i++){
            visited[nums[i]]=true;
        }
        for(int i=1;i<=n;i++){
            if(!visited[i]){
                missingNumbers.add(i);
            }
        }
        return missingNumbers;
    }

    public List<Integer> findDisappearedNumbers1(int[] nums) {
        int index=-1;
        List<Integer> missingNums=new ArrayList<>();
        for(int i=0;i<nums.length;i++){
            if(nums[i]<0){
                index=(nums[i]*-1)-1;
            }else{
                index=nums[i]-1;
            }

            if(nums[index]>0){
                nums[index]=-nums[index];
            }
        }

        for(int i=0;i<nums.length;i++){
            if(nums[i]>0){
                missingNums.add(i+1);
            }
        }
        return missingNums;
    }
}
