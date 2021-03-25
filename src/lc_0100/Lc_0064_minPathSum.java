package lc_0100;

import java.util.Arrays;

/**
 * 给定一个包含非负整数的 mxn网格，请找出一条从左上角到右下角的路径，使得路径上的数字总和为最小。
 * 说明：每次只能向下或者向右移动一步。
 * <p>
 * 输入:
 * [[1,3,1],
 * [1,5,1],
 * [4,2,1]]
 * 输出: 7
 * 解释: 因为路径 1→3→1→1→1 的总和最小。
 *
 * @author lx
 */
public class Lc_0064_minPathSum {
    public int minPathSum(int[][] grid) {
        int rows = grid.length;
        int cols = grid[0].length;
        //此时下标定位在网格的右下角,接下来以右下角为基准
        int[][] dp = new int[rows + 1][cols + 1];
        for (int[] line : dp) {
            Arrays.fill(line, Integer.MAX_VALUE);
            dp[rows - 1][cols] = 0;
            dp[rows][cols - 1] = 0;
            for (int x = rows - 1; x >= 0; x--) {
                for (int y = cols - 1; y >= 0; y--) {
                    dp[x][y] = Math.min(dp[x + 1][y], dp[x][y + 1]) + grid[x][y];
                }
            }
        }
        return dp[0][0];
    }
}