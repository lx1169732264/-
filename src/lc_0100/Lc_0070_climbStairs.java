package lc_0100;

import java.util.HashMap;
import java.util.Map;

/**
 * 假设你正在爬楼梯。需要 n阶你才能到达楼顶。
 * 每次你可以爬 1 或 2 个台阶。你有多少种不同的方法可以爬到楼顶呢？
 * <p>
 * 示例 1：
 * 输入： 2
 * 输出： 2
 * 解释： 有两种方法可以爬到楼顶。
 * 1.  1 阶 + 1 阶
 * 2.  2 阶
 */
public class Lc_0070_climbStairs {
    /**
     * 爬楼梯本质上是一个"斐波那契数列",对于最后一步,永远是在1和2之间选择一个
     * 那么对于climbStairs(n)
     * 假设最后走1步,那么前n-1个台阶有climbStairs(n-1)个可能
     * 假设最后走2步,那么前n-2个台阶有climbStairs(n-2)个可能
     * 这两者相加就是climbStairs(n)
     * <p>
     * 递归算法会导致重复计算,加入缓存map!!!!!!
     */
    Map<Integer, Integer> map = new HashMap<>();
    public int climbStairs(int n) {
        if (n <= 2) {
            map.put(n, n);
            return n;
        } else if (map.containsKey(n)) {
            return map.get(n);
        } else {
            map.put(n, climbStairs(n - 1) + climbStairs(n - 2));
        }
        //递归算法
        return climbStairs(n - 1) + climbStairs(n - 2);
    }
}
