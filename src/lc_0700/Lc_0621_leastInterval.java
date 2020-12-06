package lc_0700;

import java.util.*;

/**
 * 任务调度器
 * 给你一个用字符数组 tasks 表示的 CPU 需要执行的任务列表。其中每个字母表示一种不同种类的任务。任务可以以任意顺序执行，并且每个任务都可以在 1 个单位时间内执行完。
 * 在任何一个单位时间，CPU 可以完成一个任务，或者处于待命状态。
 * 然而，两个 相同种类 的任务之间必须有长度为整数 n 的冷却时间，因此至少有连续 n 个单位时间内 CPU 在执行不同的任务，或者在待命状态。
 * 你需要计算完成所有任务所需要的 最短时间 。
 * <p>
 * 示例 1：
 * 输入：tasks = ["A","A","A","B","B","B"], n = 2
 * 输出：8
 * 解释：A -> B -> (待命) -> A -> B -> (待命) -> A -> B
 * 在本示例中，两个相同类型任务之间必须间隔长度为 n = 2 的冷却时间，而执行一个任务只需要一个单位时间，所以中间出现了（待命）状态
 * <p>
 * 示例 2：
 * 输入：tasks = ["A","A","A","B","B","B"], n = 0
 * 输出：6
 * 解释：在这种情况下，任何大小为 6 的排列都可以满足要求，因为 n = 0
 * ["A","A","A","B","B","B"]
 * ["A","B","A","B","A","B"]
 * ["B","B","B","A","A","A"]
 * ...
 * 诸如此类
 * <p>
 * 示例 3：
 * 输入：tasks = ["A","A","A","A","A","A","B","C","D","E","F","G"], n = 2
 * 输出：16
 * 解释：一种可能的解决方案是：
 * A -> B -> C -> A -> D -> E -> A -> F -> G -> A -> (待命) -> (待命) -> A -> (待命) -> (待命) -> A
 * <p>
 * 提示：
 * 1 <= task.length <= 104
 * tasks[i] 是大写英文字母
 * n 的取值范围为 [0, 100]
 *
 * @author lx
 */
public class Lc_0621_leastInterval {
    /**
     * 创建max[出现个数]个能存放[n,+无穷大)的链表
     * 对链表依次插入
     * 统计链表
     * 链表长度<n,需要空闲
     * 链表长度>n不需要空闲,因为里面的元素各不相同
     */
    public int leastInterval(char[] tasks, int n) {
        Map<Character, Integer> map = new HashMap<>(32);
        // 字符的最多出现次数
        int maxExec = 0;
        for (char ch : tasks) {
            int exec = map.getOrDefault(ch, 0) + 1;
            map.put(ch, exec);
            maxExec = Math.max(maxExec, exec);
        }

        // 最多出现次数的字符数量
        int maxCount = 0;
        Set<Map.Entry<Character, Integer>> entrySet = map.entrySet();
        for (Map.Entry<Character, Integer> entry : entrySet) {
            int value = entry.getValue();
            if (value == maxExec) {
                ++maxCount;
            }
        }
        //(maxExec - 1) * (n + 1)链表总长度(不包括行尾)      行尾(最多出现次数的字符)元素个数为maxCount
        // tasks.length将在链表中间部分被填充到一定程度时>(maxExec - 1) * (n + 1) + maxCount
        return Math.max((maxExec - 1) * (n + 1) + maxCount, tasks.length);
    }
}
