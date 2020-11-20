package lc_0100;

import java.util.ArrayList;
import java.util.List;

/**
 * 给出一个无重叠的 ，按照区间起始端点排序的区间列表。
 * 在列表中插入一个新的区间，你需要确保列表中的区间仍然有序且不重叠（如果有必要的话，可以合并区间）。
 * <p>
 * 示例 1：
 * 输入：intervals = [[1,3],[6,9]], newInterval = [2,5]
 * 输出：[[1,5],[6,9]]
 * <p>
 * 示例 2：
 * 输入：intervals = [[1,2],[3,5],[6,7],[8,10],[12,16]], newInterval = [4,8]
 * 输出：[[1,2],[3,10],[12,16]]
 * 解释：这是因为新的区间 [4,8] 与 [3,5],[6,7],[8,10] 重叠。
 *
 * @author lx
 */
public class Lc_0057_insert {

    public int[][] insert(int[][] intervals, int[] newInterval) {
        int i = newInterval[0], j = newInterval[1];
        boolean placed = false;
        List<int[]> resList = new ArrayList<>();
        for (int[] interval : intervals) {
            if (interval[0] > j) {
                //在插入区间右侧,无交集
                if (!placed) {
                    resList.add(new int[]{i, j});
                    placed = true;
                }
                resList.add(interval);
            } else if (interval[1] > i) {
                //在插入区间左侧,无交集
                resList.add(interval);
            } else {
                i = Math.min(i, interval[0]);
                j = Math.max(j, interval[1]);
            }
        }
        if (!placed) {
            resList.add(new int[]{i, j});
        }
        int[][] res = new int[resList.size()][2];
        for (int i1 = 0; i1 < resList.size(); i1++) {
            res[i1] = resList.get(i1);
        }
        return res;
    }
}
