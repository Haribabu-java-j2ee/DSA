package sorting.problemset;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Given some balls of three colors arranged in a line, rearrange them such that all the red balls go first, then green and then blue ones.
 *
 * Do rearrange the balls in place. A solution that simply counts colors and overwrites the array is not the one we are looking for.
 *
 * This is an important problem in search algorithms theory proposed by Dutch computer scientist Edsger Dijkstra. Dutch national flag has three colors (albeit different from ones used in this problem).
 *
 * input
 * {
 * "balls": ["G", "B", "G", "G", "R", "B", "R", "G"]
 * }
 *
 * Output:
 *
 * ["R", "R", "G", "G", "G", "G", "B", "B"]
 *
 * */
public class DutchNationalFlag {
    public static void main(String[] args) {
        Character[] colors={'G', 'B', 'G', 'G', 'R', 'B', 'R', 'G'};
        ArrayList<Character> balls = new ArrayList<>();
        for (Character color : colors) {
            balls.add(color);
        }
        dutch_flag_sort(balls);
        balls.forEach(s->System.out.print(s+","));
    }

    static ArrayList<Character> dutch_flag_sort(ArrayList<Character> balls) {
        // Write your code here.
        int[] count=new int[3];
        int[] output=new int[balls.size()];
        for(int i=0;i<balls.size();i++){
            count[getIntValue(balls.get(i))]++;
        }

        for(int i=1;i<3;i++){
            count[i]+=count[i-1];
        }

        for(int i=0;i<balls.size();i++){
            output[count[getIntValue(balls.get(i))]-1]=getIntValue(balls.get(i));
            count[getIntValue(balls.get(i))]--;
        }

        for(int i=0;i<balls.size();i++){
            balls.set(i,getColorVal(output[i]));
        }
        return balls;
    }

    static int getIntValue(Character color){
        int val=0;
        switch(color){
            case 'R':
                val=0;
                break;
            case 'G':
                val=1;
                break;
            case 'B':
                val=2;
                break;
        }

        return val;
    }

    static Character getColorVal(int val){
        Character color='R';
        switch(val){
            case 0:
                color='R';
                break;
            case 1:
                color='G';
                break;
            case 2:
                color='B';
                break;
        }

        return color;
    }




    /*
    Asymptotic complexity in terms of size of `balls` `n`:
    * Time: O(n).
    * Auxiliary space: O(1).
    * Total space: O(n).
    */

    static ArrayList<Character> dutch_flag_sort1(ArrayList<Character> balls) {
        int red_index=0;

        int blue_index=balls.size()-1;

        int currentIndex=0;

        while(currentIndex<=blue_index){
            if(balls.get(currentIndex)=='R'){
                Collections.swap(balls,currentIndex,red_index);
                currentIndex++;
                red_index++;
            }else if(balls.get(currentIndex)=='G'){
                currentIndex++;
            }else{
                Collections.swap(balls,currentIndex,blue_index);
                blue_index--;
            }
        }
        return balls;
    }

}
