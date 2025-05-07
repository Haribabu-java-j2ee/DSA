package recursion;

import java.util.ArrayList;

public class TowerOfHanoi {
    public static void main(String[] args) {
        ArrayList<ArrayList<Integer>> result = new ArrayList<>();
        moveDisc(4, 1, 3, 2, result);
        result.forEach(System.out::println);
    }

    static void moveDisc(Integer n, int source, int dest, int aux, ArrayList<ArrayList<Integer>> result) {
        if (n == 1) {
            ArrayList<Integer> temp = new ArrayList<>();
            temp.add(source);
            temp.add(dest);
            result.add(temp);
            return;
        }
        moveDisc(n - 1, source, aux, dest, result);
        ArrayList<Integer> temp = new ArrayList<>();
        temp.add(source);
        temp.add(dest);
        result.add(temp);
        moveDisc(n - 1, aux, dest, source, result);
    }
}
