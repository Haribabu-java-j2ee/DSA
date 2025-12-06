package dsapatterns.mergeintervals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//https://leetcode.com/problems/interval-list-intersections/description/
public class IntervalListIntersections {
    public static void main(String[] args) {
        IntervalListIntersections obj=new IntervalListIntersections();
        int[][] firstList={{0,2},{5,10},{13,23},{24,25}};
        int[][] secondList={{1,5},{8,12},{15,24},{25,26}};

        Arrays.stream(obj.intervalIntersection(firstList,secondList)).forEach(element->{
            System.out.println();
            Arrays.stream(element).forEach(e-> System.out.print(e+" "));
        });
    }
    public int[][] intervalIntersection(int[][] firstList, int[][] secondList) {
        int i=0, j=0;
        int n=firstList.length;
        int m=secondList.length;
        List<int[]> result=new ArrayList<>();
        while(i<n && j<m){
            int left=Math.max(firstList[i][0],secondList[j][0]);
            int right=Math.min(firstList[i][1],secondList[j][1]);
            if(left<=right){
                result.add(new int[]{left,right});
            }

            if(firstList[i][1]<secondList[j][1]){
                i++;
            }else{
                j++;
            }
        }
        return result.toArray(new int[0][]);
    }
}
