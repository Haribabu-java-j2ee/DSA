package dynamicprogramming.problemset;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class JumpGame {
    public static void main(String[] args) throws FileNotFoundException {
        int[] heights={3, 1, 1, 0, 2, 4};
        int[] heights1={10, 1, 4, 0, 0, 6, 5, 3, 2, 0, 0, 0, 4, 0, 8, 9, 0, 4, 0, 0, 8, 0, 2};

        ArrayList<Integer> maximum_jump_lengths=new ArrayList<>();
        Arrays.stream(heights).forEach(maximum_jump_lengths::add);
        System.out.println(can_reach_last_house1(maximum_jump_lengths));

        System.out.println(can_reach_last_house3(maximum_jump_lengths));
        System.out.println(can_reach_last_house4(maximum_jump_lengths));


        maximum_jump_lengths=new ArrayList<>();
        Arrays.stream(heights1).forEach(maximum_jump_lengths::add);
        System.out.println(can_reach_last_house2(maximum_jump_lengths));

        System.out.println(can_reach_last_house3(maximum_jump_lengths));
        System.out.println(can_reach_last_house4(maximum_jump_lengths));
    }


    //plain recursive O(2^n)
    static Boolean can_reach_last_house3(ArrayList<Integer> maximum_jump_lengths) {
        return can_reach_last_house_recursive(0,maximum_jump_lengths);
    }

    private static Boolean can_reach_last_house_recursive(int index, ArrayList<Integer> maximumJumpLengths) {
        int size=maximumJumpLengths.size();
        if(index==size-1){
            return true;
        }
        for(int i=1;i<=maximumJumpLengths.get(index) && index+i<size;i++){
            if(can_reach_last_house_recursive(index+i,maximumJumpLengths)){
                return true;
            }
        }
        return false;
    }

    static Boolean can_reach_last_house4(ArrayList<Integer> maximum_jump_lengths) {
        int[] dp=new int[maximum_jump_lengths.size()];
        Arrays.fill(dp,-1);
        return can_reach_last_house_recursivedp(0,maximum_jump_lengths,dp);
    }

    private static Boolean can_reach_last_house_recursivedp(int index, ArrayList<Integer> maximumJumpLengths,int[] dp) {
        int size=maximumJumpLengths.size();
        if(index==size-1){
            return true;
        }
        if(dp[index]!=-1){
            return dp[index]==1;
        }
        for(int i=1;i<=maximumJumpLengths.get(index) && index+i<size;i++){
            dp[index+i]=can_reach_last_house_recursivedp(index+i,maximumJumpLengths,dp)?1:0;
            if(dp[index+i]==1){
                return true;
            }
        }
        return false;
    }

    static Boolean can_reach_last_house1(ArrayList<Integer> maximum_jump_lengths) {
        int size = maximum_jump_lengths.size();

        // For any index i, `is_good_cached[i]` stores 1 if it is possible to reach
        // index `i` after any number of jumps and 0 if not.
        // Initialize with zeroes (bad index)
        ArrayList<Integer> is_good_cached = new ArrayList<>(Collections.nCopies(size, 0));

        is_good_cached.set(size - 1, 1);

        for (int i = size - 2; i >= 0; i--) {
            for (int j = i + 1; j <= i + maximum_jump_lengths.get(i); j++) {
                if (is_good_cached.get(j) != 0) {
                    is_good_cached.set(i, 1);
                    break;
                }
            }
        }
        return (is_good_cached.get(0) == 1);
    }


    //O(n) approach
    static Boolean can_reach_last_house2(ArrayList<Integer> maximum_jump_lengths) {
        int size = maximum_jump_lengths.size();
        int leftmost_good_index = size - 1;

        for (int i = size - 2; i >= 0; i--) {
            if (maximum_jump_lengths.get(i) + i >= leftmost_good_index) {
                leftmost_good_index = i;
            }
        }

        if (leftmost_good_index == 0) {
            return true;
        }

        return false;
    }

}
