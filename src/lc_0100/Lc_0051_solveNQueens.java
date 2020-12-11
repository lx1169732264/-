package lc_0100;

import java.util.*;

/**
 * n 皇后问题研究的是如何将 n 个皇后放置在 n×n 的棋盘上，并且使皇后彼此之间不能相互攻击。
 * 皇后彼此不能相互攻击，也就是说：任何两个皇后都不能处于同一条横行、纵行或斜线上。
 * 图为 8 皇后问题的一种解法。
 * <p>
 * 给定一个整数 n，返回所有不同的 n 皇后问题的解决方案。
 * 每一种解法包含一个明确的 n 皇后问题的棋子放置方案，该方案中 'Q' 和 '.' 分别代表了皇后和空位。
 * <p>
 * 输入：4
 * 输出：[
 * [".Q..",  // 解法 1
 * "...Q",
 * "Q...",
 * "..Q."],
 * ["..Q.",  // 解法 2
 * "Q...",
 * "...Q",
 * ".Q.."]
 * ]
 * 解释: 4 皇后问题存在两个不同的解法
 */
public class Lc_0051_solveNQueens {
    /**
     * 用columns列、diagonals1斜方向1(左上到右下) 和diagonals2斜方向2(右上到左下) 记录是否有皇后
     * <p>
     * 斜方向1 行与列之差相等,例如 (0,0)和 (3,3),列行差都为3
     * 斜方向2 行与列之和相等，例如 (3,0)和 (1,2)
     */
    public List<List<String>> solveNQueens(int n) {
        List<List<String>> res = new ArrayList<>();
        int[] queens = new int[n];
        Arrays.fill(queens, -1);
        Set<Integer> columns = new HashSet<>(), diagonals1 = new HashSet<>(), diagonals2 = new HashSet<>();
        backtrack(res, queens, n, 0, columns, diagonals1, diagonals2);
        return res;
    }

    public void backtrack(List<List<String>> res, int[] queens, int n, int row,
                          Set<Integer> columns, Set<Integer> diagonals1, Set<Integer> diagonals2) {
        if (row == n) {
            res.add(generateBoard(queens, n));
            return;
        }
        for (int i = 0; i < n; i++) {

            //当前列已存在皇后/方向1/2已存在,不能插入
            int diagonal1 = row - i, diagonal2 = row + i;
            if (columns.contains(i) || diagonals1.contains(diagonal1) || diagonals2.contains(diagonal2)) {
                continue;
            }

            //在合适的位置放入皇后,并记录列/方向1/方向2
            queens[row] = i;
            columns.add(i);
            diagonals1.add(diagonal1);
            diagonals2.add(diagonal2);

            backtrack(res, queens, n, row + 1, columns, diagonals1, diagonals2);

            //剪枝
            queens[row] = -1;
            columns.remove(i);
            diagonals1.remove(diagonal1);
            diagonals2.remove(diagonal2);
        }
    }

    public List<String> generateBoard(int[] queens, int n) {
        List<String> board = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            char[] row = new char[n];
            Arrays.fill(row, '.');
            row[queens[i]] = 'Q';
            board.add(new String(row));
        }
        return board;
    }
}
