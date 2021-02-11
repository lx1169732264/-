package lc_0700;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.stream.Collectors;

/**
 * 子数组的最大平均数 I
 * 给定 n 个整数，找出平均数最大且长度为 k 的连续子数组，并输出该最大平均数。
 * <p>
 * 示例：
 * 输入：[1,12,-5,-6,50,3], k = 4
 * 输出：12.75
 * 解释：最大平均数 (12-5-6+50)/4 = 51/4 = 12.75
 * <p>
 * 提示：
 * 1 <= k <= n <= 30,000。
 * 所给数据范围 [-10,000，10,000]。
 */
public class Lc_0643_findMaxAverage {
    public double findMaxAverage(int[] nums, int k) {
        Deque<Integer> deque = Arrays.stream(Arrays.copyOf(nums, k)).boxed().collect(Collectors.toCollection(ArrayDeque::new));
        int count = deque.stream().reduce(0, Integer::sum);
        int res = count, index = k;
        while (index < nums.length) {
            count = count + nums[index] - deque.poll();
            deque.offer(nums[index]);
            res = Math.max(res, count);
            index++;
        }
        return BigDecimal.valueOf(res).divide(BigDecimal.valueOf(k), 5, RoundingMode.HALF_UP).doubleValue();
    }
}

