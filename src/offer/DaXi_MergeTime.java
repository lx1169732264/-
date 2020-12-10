package offer;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

/**
 * 合并时间区间   时间复杂度 O(n)
 * 给定⼀个按开始时间从⼩到⼤排序的时间区间集合，请将重叠的区间合并。时间区间集合⽤⼀个⼆维数组表示，⼆维数组的每⼀⾏表示⼀个时间区间（闭区间）
 * 其中 0 位置元素表示时间区间开始，1 位置元素表示时间区间结束。
 * <p>
 * 例 1：输⼊：[ [1, 3], [2, 6], [8, 10], [15, 18] ]
 * 返回： [ [1, 6], [8, 10], [15, 18]]
 * 解释：时间区间 [1, 3] 和 [2, 6] 有部分重叠，合并之后为 [1, 6]
 * <p>
 * 例 2：输⼊：[[1, 4], [4, 5]]
 * 返回：[[1, 5]]
 * 解释：时间区间[1，4] 和 [4，5]重叠了⼀个时间点，合并之后为 [1，5]
 */
public class DaXi_MergeTime {

    public int[][] merge2(int[][] array) {

        if (null == array || array.length == 0) {
            return new int[0][];
        }

        ArrayList<int[]> res = new ArrayList<>();

        Arrays.sort(array, Comparator.comparingInt(o -> o[0]));

        int end = array[0][1], start = array[0][0];
        for (int i = 1; i < array.length; i++) {

            //最大下标>下个区间的左边界
            if (end >= array[i][0]) {
                end = Math.max(end, array[i][1]);

                //区间不重叠,更新返回值/start/end,进行下一次循环
            } else {
                res.add(new int[]{start, end});

                start = array[i][0];
                end = array[i][1];
            }

            //到了数组尾部,没有下个区间了,直接add
            if (i == array.length - 1) {
                res.add(new int[]{start, end});
                break;
            }
        }
        return res.toArray(new int[0][]);
    }

    @Test
    public void test() {
        int[][] test1 = new int[][]{{1, 3}, {2, 6}, {8, 10}, {15, 18}};
        int[][] res1 = new int[][]{{1, 6}, {8, 10}, {15, 18}};
        int[][] test2 = new int[][]{{1, 4}, {4, 5}};
        int[][] res2 = new int[][]{{1, 5}};

        Assert.assertArrayEquals(merge2(test1), res1);
        Assert.assertArrayEquals(merge2(test2), res2);
    }
}
