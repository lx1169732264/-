package lc_0500;

/**
 * @author lx
 */
public class Lc_463_islandPerimeter {
    public static int islandPerimeter(int[][] grid) {
        int count = 0, pre;
        for (int y = 0, x = 0; y < grid.length; y++) {
            pre = 0;
            while (x < grid[0].length) {
                if (grid[y][x] == 1 && pre != 1) {
                    count += 2;
                }
                pre = grid[y][x];
                x++;
            }
            x = 0;
        }

        for (int x = 0, y = 0; x < grid[0].length; x++) {
            pre = 0;
            while (y < grid.length) {
                if (grid[y][x] == 1 && pre != 1) {
                    count += 2;
                }
                pre = grid[y][x];
                y++;
            }
            y = 0;
        }
        return count;
    }


    public static void main(String[] args) {
//        int[][] grid = new int[][]{{0, 1, 0, 0}, {1, 1, 1, 0}, {0, 1, 0, 0}, {1, 1, 0, 0}};
        int[][] grid = new int[][]{{0, 1}};
        System.out.println(islandPerimeter(grid));
    }
}
