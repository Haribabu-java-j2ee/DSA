package dsapatterns.mergeintervals;

import java.util.Arrays;
import java.util.Comparator;

//https://leetcode.com/problems/minimum-number-of-arrows-to-burst-balloons/description/
public class BurstingBallons {
    public static void main(String[] args) {
        BurstingBallons obj=new BurstingBallons();
        int[][] points={{10,16},{2,8},{1,6},{7,12}};
        System.out.println(obj.findMinArrowShots(points));
    }
    public int findMinArrowShots(int[][] points) {
        Arrays.sort(points, Comparator.comparingInt(p->p[1]));
        int noOfArrows=1;
        int last=points[0][1];
        for(int i=1;i<points.length;i++){
            if(points[i][0]<=last){
                continue;
            }else{
                noOfArrows++;
                last= points[i][1];
            }
        }
        return noOfArrows;
    }
}
