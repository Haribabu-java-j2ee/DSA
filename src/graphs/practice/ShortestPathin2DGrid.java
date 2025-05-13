package graphs.practice;

import java.util.*;

/**
 * [
 * [2, 0],
 * [1, 0],
 * [1, 1],
 * [0, 1],
 * [0, 2],
 * [0, 3],
 * [1, 3],
 * [2, 3],
 * [2, 2]
 * ]
 * */
public class ShortestPathin2DGrid {

    public static void main(String[] args) {
       String[] grid={"...B",".b#.","@#+."};
       ArrayList<String> gridList = new ArrayList<>(Arrays.asList(grid));
        find_shortest_path(gridList).stream().forEach(System.out::println);
    }

    /*
     * Asymptotic complexity in terms of the number of rows in the grid rows and the number of columns in the grid columns and the total number of keys keys:
     * Time: O(rows * columns * 2^keys).
     * Auxiliary space: O(rows * columns * 2^keys).
     * Total space: O(rows * columns * 2^keys).
     */
    static class Pair<T, U> {
        private final T first;
        private U second;

        public Pair(T first, U second) {
            this.first = first;
            this.second = second;
        }

        public T getFirst() {
            return first;
        }

        public U getSecond() {
            return second;
        }

        public void setSecond(U second) {
            this.second = second;
        }
    }

    static final int MAX_KEYS = 10;
    static final int MAX_MASK = (1 << MAX_KEYS);

    static ArrayList<ArrayList<Integer>> buildPath(ArrayList<ArrayList<ArrayList<Pair<Pair<Integer, Integer>, Integer>>>> parent,
                                                   int keyring, Pair<Integer, Integer> start, Pair<Integer, Integer> goal) {
        ArrayList<ArrayList<Integer>> ans = new ArrayList<>();
        ans.add(new ArrayList<>());
        ans.get(0).add(goal.getFirst());
        ans.get(0).add(goal.getSecond());

        while (!goal.equals(start) || keyring != 0) {
            Pair<Pair<Integer, Integer>, Integer> par = parent.get(goal.getFirst()).get(goal.getSecond()).get(keyring);
            goal = par.getFirst();
            keyring = par.getSecond();
            ans.add(new ArrayList<>());
            ans.get(ans.size() - 1).add(goal.getFirst());
            ans.get(ans.size() - 1).add(goal.getSecond());
        }
        Collections.reverse(ans);
        return ans;
    }

    static boolean isStart(char ch) {
        return ch == '@';
    }

    static boolean isGoal(char ch) {
        return ch == '+';
    }

    static boolean isWater(char ch) {
        return ch == '#';
    }

    static boolean isLand(char ch) {
        return ch == '.';
    }

    static boolean isKey(char ch) {
        return ('a' <= ch && ch < ('a' + MAX_KEYS));
    }

    static boolean isDoor(char ch) {
        return ('A' <= ch && ch < ('A' + MAX_KEYS));
    }

    static boolean canOpenDoor(char door, int keyring) {
        return (keyring >> (door - 'A')) % 2 == 1;
    }

    static void addNeighborToQueue(int toRow, int toCol, int toKeyring,
                                   Pair<Pair<Integer, Integer>, Integer> from, ArrayList<ArrayList<ArrayList<Pair<Pair<Integer, Integer>, Integer>>>> parent,
                                   ArrayList<ArrayList<ArrayList<Boolean>>> visited, Queue<Pair<Pair<Integer, Integer>, Integer>> q) {
        parent.get(toRow).get(toCol).set(toKeyring, from);
        visited.get(toRow).get(toCol).set(toKeyring, true);
        q.add(new Pair<>(new Pair<>(toRow, toCol), toKeyring));
    }

    static int bfs(ArrayList<String> grid, Pair<Integer, Integer> start,
                   ArrayList<ArrayList<ArrayList<Pair<Pair<Integer, Integer>, Integer>>>> parent,
                   ArrayList<ArrayList<ArrayList<Boolean>>> visited) {
        int rows = grid.size();
        int cols = grid.get(0).length();

        Queue<Pair<Pair<Integer, Integer>, Integer>> q = new LinkedList<>();
        q.add(new Pair<>(start, 0));
        visited.get(start.getFirst()).get(start.getSecond()).set(0, true);

        while (!q.isEmpty()) {
            Pair<Pair<Integer, Integer>, Integer> from = q.poll();
            int keyring = from.getSecond();

            ArrayList<Pair<Integer, Integer>> steps = new ArrayList<>();
            steps.add(new Pair<>(-1, 0));
            steps.add(new Pair<>(0, -1));
            steps.add(new Pair<>(1, 0));
            steps.add(new Pair<>(0, 1));

            for (Pair<Integer, Integer> step : steps) {
                int toRow = from.getFirst().getFirst() + step.getFirst();
                int toCol = from.getFirst().getSecond() + step.getSecond();
                if (toRow < 0 || toRow >= rows || toCol < 0 || toCol >= cols) {
                    continue;
                }
                if (isGoal(grid.get(toRow).charAt(toCol))) {
                    addNeighborToQueue(toRow, toCol, keyring, from, parent, visited, q);
                    return keyring;
                } else if (isLand(grid.get(toRow).charAt(toCol)) || isStart(grid.get(toRow).charAt(toCol))) {
                    if (!visited.get(toRow).get(toCol).get(keyring)) {
                        addNeighborToQueue(toRow, toCol, keyring, from, parent, visited, q);
                    }
                } else if (isKey(grid.get(toRow).charAt(toCol))) {
                    int newKeyring = keyring | (1 << (grid.get(toRow).charAt(toCol) - 'a'));
                    if (!visited.get(toRow).get(toCol).get(newKeyring)) {
                        addNeighborToQueue(toRow, toCol, newKeyring, from, parent, visited, q);
                    }
                } else if (isDoor(grid.get(toRow).charAt(toCol)) &&
                        canOpenDoor(grid.get(toRow).charAt(toCol), keyring) &&
                        !visited.get(toRow).get(toCol).get(keyring)) {
                    addNeighborToQueue(toRow, toCol, keyring, from, parent, visited, q);
                }
            }
        }
        throw new RuntimeException("Something went wrong!");
    }

    static Pair<Integer, Integer> findStartCell(ArrayList<String> grid) {
        int rows = grid.size();
        int cols = grid.get(0).length();

        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                if (isStart(grid.get(r).charAt(c))) {
                    return new Pair<>(r, c);
                }
            }
        }
        return null; // If start cell not found
    }

    static Pair<Integer, Integer> findGoalCell(ArrayList<String> grid) {
        int rows = grid.size();
        int cols = grid.get(0).length();

        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                if (isGoal(grid.get(r).charAt(c))) {
                    return new Pair<>(r, c);
                }
            }
        }
        return null; // If goal cell not found
    }

    static ArrayList<ArrayList<Integer>> find_shortest_path(ArrayList<String> grid) {
        int rows = grid.size();
        int cols = grid.get(0).length();
        Pair<Integer, Integer> start = findStartCell(grid);
        Pair<Integer, Integer> goal = findGoalCell(grid);

        if (start == null || goal == null) {
            return new ArrayList<>(); // If start or goal cell not found
        }

        ArrayList<ArrayList<ArrayList<Pair<Pair<Integer, Integer>, Integer>>>> parent = new ArrayList<>();
        for (int i = 0; i < rows; i++) {
            parent.add(new ArrayList<>());
            for (int j = 0; j < cols; j++) {
                parent.get(i).add(new ArrayList<>());
                for (int k = 0; k < MAX_MASK; k++) {
                    parent.get(i).get(j).add(new Pair<>(new Pair<>(-1, -1), -1));
                }
            }
        }

        ArrayList<ArrayList<ArrayList<Boolean>>> visited = new ArrayList<>();
        for (int i = 0; i < rows; i++) {
            visited.add(new ArrayList<>());
            for (int j = 0; j < cols; j++) {
                visited.get(i).add(new ArrayList<>(Collections.nCopies(MAX_MASK, false)));
            }
        }

        int lastKeyring = bfs(grid, start, parent, visited);

        return buildPath(parent, lastKeyring, start, goal);
    }
}
