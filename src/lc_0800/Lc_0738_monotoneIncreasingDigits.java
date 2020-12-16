package lc_0800;

import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;

/**
 * 给定一个非负整数 N，找出小于或等于 N 的最大的整数，同时这个整数需要满足其各个位数上的数字是单调递增。
 * （当且仅当每个相邻位数上的数字 x 和 y 满足 x <= y 时，我们称这个整数是单调递增的。）
 * <p>
 * 示例 1:
 * 输入: N = 10
 * 输出: 9
 * <p>
 * 示例 2:
 * 输入: N = 1234
 * 输出: 1234
 * <p>
 * 示例 3:
 * 输入: N = 332
 * 输出: 299
 * 说明: N 是在 [0, 10^9] 范围内的一个整数。
 *
 * @author lx
 */
public class Lc_0738_monotoneIncreasingDigits {
    /**
     * 从后往前遍历，如果前面的值大于后面的值就把当前位数减一然后把后面的值变成9
     */
    public int monotoneIncreasingDigits(int N) {
        if (N == 0) return 0;
        int r = N % 10;

        N = N / 10;
        int l = N % 10,old = N;
        if (r < l) {
            N--;
        }

        int ans = monotoneIncreasingDigits(N);
        if (old > ans) {

            //前面的值大于后面的值,个位数为9
            ans = ans * 10 + 9;
        } else {

            ans = ans * 10 + r;
        }
        return ans;
    }

    @Test
    public void test() {
//        Assert.assertEquals(9, monotoneIncreasingDigits(10));
//        Assert.assertEquals(1234, monotoneIncreasingDigits(1234));
        Assert.assertEquals(299, monotoneIncreasingDigits(332));
    }
}
