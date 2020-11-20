package lc_0200;

import java.util.LinkedList;
import java.util.List;

/**
 * 给定一个非负整数 numRows，生成杨辉三角的前 numRows 行。
 * 在杨辉三角中，每个数是它左上方和右上方的数的和。
 * <p>
 * 示例:
 * 输入: 5
 * 输出:
 * [
 * [1],
 * [1,1],
 * [1,2,1],
 * [1,3,3,1],
 * [1,4,6,4,1]
 * ]
 *
 * @author lx
 */
public class Lc_0118_generate {
    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> res = new LinkedList<>();
        if (0 == numRows) {
            return res;
        }
        res.add(new LinkedList<>());
        res.get(0).add(1);

        for (int i = 1; i < numRows; i++) {
            List<Integer> pre = res.get(i - 1);
            LinkedList<Integer> linkedList = new LinkedList<>();
            linkedList.add(1);
            for (int j = 0; j < pre.size() - 1; j++) {
                linkedList.add(pre.get(j) + pre.get(j + 1));
            }
            linkedList.add(1);
            res.add(linkedList);
        }
        return res;
    }
}
