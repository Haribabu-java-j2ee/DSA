package sorting.problemset;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * Given coordinates of a point p and n other points on a two-dimensional surface, find k points out of n which are the nearest to point p.
 * <p>
 * Distance is measured by the standard Euclidean method.
 * <p>
 * input:
 * {
 * "p_x": 1,
 * "p_y": 1,
 * "k": 1,
 * "n_points": [
 * [0, 0],
 * [1, 0]
 * ]
 * }
 * <p>
 * Output:
 * <p>
 * [
 * [1, 0]
 * ]
 * <p>
 * The distance of point {0, 0} from point p{1, 1} is sqrt(2) and that of point {1, 0} is 1.
 * We need to choose 1(k) point having the minimum distance from point p. So it is {1, 0}.
 * <p>
 * distance formula
 * <p>
 * sqrt((x2-x1)^2+ (y2-y1)^2)
 * ref: https://www.calculatorsoup.com/calculators/geometry-plane/distance-two-points.php
 */
public class NearestNeighbours {
    public static void main(String[] args) {
        int px = 1000000000;
        int py = 1000000000;
        int k = 3;
        int[][] arr = {
                {-1000000000, -1000000000},
                {0, 0},
                {-1000000000, 1000000000},
                {1000000000, -1000000000}
        };
        ArrayList<ArrayList<Integer>> nearestNeighbours = new ArrayList<>(); //'array' is two-dimensional
        for (int i = 0; i < arr.length; i++) {
            ArrayList<Integer> subArray = new ArrayList<>();
            for (int j = 0; j < arr[i].length; j++) {
                subArray.add(arr[i][j]);
            }
            nearestNeighbours.add(subArray);
        }
        nearest_neighbours(px, py, k, nearestNeighbours).forEach(System.out::println);
    }

    static ArrayList<ArrayList<Integer>> nearest_neighbours(Integer p_x, Integer p_y, Integer k, ArrayList<ArrayList<Integer>> n_points) {
        // Write your code here.
        Point[] points = new Point[n_points.size()];

        for (int i = 0; i < n_points.size(); i++) {
            points[i] = new Point(i, Math.sqrt((p_x - n_points.get(i).get(0)) * 1l * (p_x - n_points.get(i).get(0)) + (p_y - n_points.get(i).get(1)) * 1l * (p_y - n_points.get(i).get(1))));
        }

        Arrays.sort(points);

        ArrayList<ArrayList<Integer>> result = new ArrayList<>();
        for (int i = 0; i < k; i++) {
            int index = points[i].index;
            result.add(n_points.get(index));
        }
        return result;
    }


}

class Point implements Comparable<Point> {
    public int index;
    public double distance;

    public Point(int index, double distance) {
        this.index = index;
        this.distance = distance;
    }

    @Override
    public int compareTo(Point point) {
        return Double.compare(this.distance, point.distance);
    }
}
