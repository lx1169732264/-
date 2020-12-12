package offer;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * 给定⼀个按开始时间从⼩到⼤排序的时间区间集合，请将重叠的区间合并。
 * 时间 区间集合⽤⼀个⼆维数组表示，⼆维数组的每⼀⾏表示⼀个时间区间（闭区 间）， 其中 0 位置元素表示时间区间开始，1 位置元素表示时间区间结束。
 * <p>
 * 例 1：
 * 输⼊：[ [1, 3], [2, 6], [8, 10], [15, 18] ]
 * 返回： [ [1, 6], [8, 10], [15, 18]]
 * 解释：时间区间 [1, 3] 和 [2, 6] 有部分重叠，合并之后为 [1, 6]
 * <p>
 * 例 2：
 * 输⼊：[[1, 4], [4, 5]]
 * 返回：[[1, 5]]
 * 解释：时间区间[1，4] 和 [4，5]重叠了⼀个时间点，合并之后为 [1，5]
 */
public class DaXi_merge {
    int[][] merge(int[][] intervals) {
        if (null == intervals) {
            return new int[0][];
        }

        if (intervals.length <= 1) {
            return intervals;
        }

        Arrays.sort(intervals, Comparator.comparingInt(o -> o[0]));
        List<int[]> res = new ArrayList<>();

        //index当前下标 start开始区间   end结束区间
        int index = 0, start, end;
        while (index < intervals.length) {
            start = intervals[index][0];
            end=intervals[index][1];

            //每次向后寻找是否可以拓展为更大的区间
            while (index< intervals.length) {

                //下个区间的左边界大于上个区间的最右边
                if (intervals[index][0] > end) {
                    break;
                }
                end = Math.max(end, intervals[index][1]);
                index++;
            }

            res.add(new int[]{start, end});
        }
        return res.toArray(new int[0][]);
    }

    @Test
    public void test() {
        int[][] test1 = new int[][]{{1, 3}, {2, 6}, {8, 10}, {15, 18}};
        int[][] res1 = new int[][]{{1, 6}, {8, 10}, {15, 18}};

        int[][] test2 = new int[][]{{1, 4}, {4, 5}};
        int[][] res2 = new int[][]{{1, 5}};

        int[][] test3 = new int[][]{{1, 4}};
        int[][] res3 = new int[][]{{1, 4}};

        int[][] test4 = new int[][]{};
        int[][] res4 = new int[][]{};

        int[][] test5 =null;
        Assert.assertEquals(merge(test1), res1);
        Assert.assertEquals(merge(test2), res2);
        Assert.assertEquals(merge(test3), res3);
        Assert.assertEquals(merge(test4), res4);
        Assert.assertEquals(merge(test5), res4);
    }
}