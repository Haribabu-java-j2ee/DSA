import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

public class SortCheck {
    public static void main(String[] args) {
        Character[] colors={'G', 'B', 'G', 'G', 'R', 'B', 'R', 'G'};
        ArrayList<Character> balls = new ArrayList<>();
        for (Character color : colors) {
            balls.add(color);
        }
        dutch_flag_sort(balls);
        balls.forEach(s->System.out.print(s+","));
    }

    public static void dutch_flag_sort(ArrayList<Character> balls){
        int redIndex=0;
        int blueIndex=balls.size()-1;
        int currentIndex=0;
        while(currentIndex<=blueIndex){
            if(balls.get(currentIndex)=='R'){
                Collections.swap(balls,currentIndex,redIndex);
                currentIndex++;
                redIndex++;
            }else if(balls.get(currentIndex)=='G'){
                currentIndex++;
            }else{
                Collections.swap(balls,currentIndex,blueIndex);
                blueIndex--;
            }
        }
    }
}
