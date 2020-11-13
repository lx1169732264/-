package lc_0100;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 * 给定一个 没有重复 数字的序列，返回其所有可能的全排列。
 * <p>
 * 示例:
 * 输入: [1,2,3]
 * 输出:
 * [
 * [1,2,3],
 * [1,3,2],
 * [2,1,3],
 * [2,3,1],
 * [3,1,2],
 * [3,2,1]
 * ]
 *
 * @author lx
 */
public class Lc_0046_permute {
    public List<List<Integer>> permute(int[] nums) {
        Deque<List<Integer>> queue = new LinkedList<>();
        queue.offer(new ArrayList<>());
        for (int i : nums) {
            for (int j = queue.size(); j > 0; j--) {
                List<Integer> layer = queue.poll();
                for (int k = layer.size(); k > 0; k--) {
                    List<Integer> copy = new LinkedList<>(layer);
                    copy.add(k, i);
                    queue.offer(copy);
                }
                // 为了让这个poll的layer不至于每次都被回收
                layer.add(0, i);
                queue.offer(layer);
            }
        }
        return new ArrayList<>(queue);
    }
}
