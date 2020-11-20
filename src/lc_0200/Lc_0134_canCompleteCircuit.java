package lc_0200;

import org.junit.Test;

/**
 * 在一条环路上有 N 个加油站，其中第 i 个加油站有汽油 gas[i] 升。
 * 你有一辆油箱容量无限的的汽车，从第 i 个加油站开往第 i+1 个加油站需要消耗汽油 cost[i] 升。你从其中的一个加油站出发，开始时油箱为空。
 * 如果你可以绕环路行驶一周，则返回出发时加油站的编号，否则返回 -1。
 * <p>
 * 说明:
 * 如果题目有解，该答案即为唯一答案。
 * 输入数组均为非空数组，且长度相同。输入数组中的元素均为非负数。
 * <p>
 * 示例 1:
 * 输入:
 * gas  = [1,2,3,4,5]
 * cost = [3,4,5,1,2]
 * 输出: 3
 * <p>
 * 解释:
 * 从 3 号加油站(索引为 3 处)出发，可获得 4 升汽油。此时油箱有 = 0 + 4 = 4 升汽油
 * 开往 4 号加油站，此时油箱有 4 - 1 + 5 = 8 升汽油
 * 开往 0 号加油站，此时油箱有 8 - 2 + 1 = 7 升汽油
 * 开往 1 号加油站，此时油箱有 7 - 3 + 2 = 6 升汽油
 * 开往 2 号加油站，此时油箱有 6 - 4 + 3 = 5 升汽油
 * 开往 3 号加油站，你需要消耗 5 升汽油，正好足够你返回到 3 号加油站。
 * 因此，3 可为起始索引。
 * <p>
 * 示例 2:
 * 输入:
 * gas  = [2,3,4]
 * cost = [3,4,3]
 * 输出: -1
 * <p>
 * 解释:
 * 你不能从 0 号或 1 号加油站出发，因为没有足够的汽油可以让你行驶到下一个加油站。
 * 我们从 2 号加油站出发，可以获得 4 升汽油。 此时油箱有 = 0 + 4 = 4 升汽油
 * 开往 0 号加油站，此时油箱有 4 - 3 + 2 = 3 升汽油
 * 开往 1 号加油站，此时油箱有 3 - 3 + 3 = 3 升汽油
 * 你无法返回 2 号加油站，因为返程需要消耗 4 升汽油，但是你的油箱只有 3 升汽油。
 * 因此，无论怎样，你都不可能绕环路行驶一周。
 *
 * @author lx
 */
public class Lc_0134_canCompleteCircuit {

    /**
     * 一次遍历
     * 如果某点ans能一直走到数组结尾，保留从数组结尾到数组第一个节点时剩余汽油数cur。
     * 并保留从数组第一个点走到该点时剩余汽油数res。
     * 比较两个数的大小，如果cur + res >= 0，说明可以从数组第一个节点走到ans。即可以绕环路行驶一周
     */
    public int canCompleteCircuit2(int[] gas, int[] cost) {
        int n = gas.length;
        int cur = 0, res = 0, ans = 0;

        for (int i = 0; i < n; i++) {
            cur += gas[i] - cost[i];
            if (cur < 0) {
                ans = i + 1;
                res += cur;
                cur = 0;
            }
        }
        return cur + res >= 0 ? ans : -1;
    }

    public int canCompleteCircuit(int[] gas, int[] cost) {
        //克隆cost,使得无需考虑边界问题
        int[] newGas = new int[gas.length * 2 + 1];
        int[] newCost = new int[cost.length * 2 + 1];
        for (int i = 0; i < cost.length; i++) {
            newGas[i] = gas[i];
            newGas[i + gas.length] = gas[i];
            newCost[i] = cost[i];
            newCost[i + cost.length] = cost[i];
        }
        newGas[gas.length * 2] = gas[0];
        newCost[gas.length * 2] = cost[0];


        for (int i = 0; i < gas.length; i++) {
            int gasSum = newGas[i], costNum = 0;
            for (int j = i; j < i + gas.length; j++) {
                costNum += newCost[j];
                if (costNum > gasSum) {
                    gasSum = 0;
                    break;
                }
                gasSum += newGas[j + 1];
            }
            if (gasSum != 0) {
                return i;
            }
        }
        return -1;
    }
}
