package graphs;

//read the question carefully
// if diagnol is given , so just for +1 and -1 on diagnoal sides also
// ik has diagnoals also , but leecode only given horizontal of vertical
public class NoofIslands {
    public static void main(String[] args) {
        char[][] grid = { {'1','0'},{'0','1'}
                /*{'1', '1', '1', '1', '0'},
                {'1', '1', '0', '1', '0'},
                {'1', '1', '0', '0', '0'},
                {'0', '0', '0', '0', '0'}*/
        };

        if (grid == null) {
            System.out.println("No islands");
            return;
        }
        int count = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j] == '1') {
                    dfs(i, j, grid);
                    count++;

                }
            }
        }

        System.out.println(count);
    }

    static void dfs(int i, int j, char[][] grid) {
        if (i < 0 || j < 0 || i >= grid.length || j >= grid[0].length || grid[i][j] != '1') {
            return;
        }
        grid[i][j] = '0';
        dfs(i + 1, j, grid);
        dfs(i+1, j+1, grid);
        dfs(i+1, j-1, grid);

        dfs(i - 1, j, grid);
        dfs(i - 1, j+1, grid);
        dfs(i - 1, j-1, grid);

        dfs(i, j + 1, grid);
        dfs(i, j - 1, grid);

    }

}
