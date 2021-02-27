package lc_0800;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * N 对情侣坐在连续排列的 2N 个座位上，想要牵到对方的手。 计算最少交换座位的次数，以便每对情侣可以并肩坐在一起。一次交换可选择任意两人，让他们站起来交换座位
 * 人和座位用 0 到 2N-1 的整数表示，情侣们按顺序编号，第一对是 (0, 1)，第二对是 (2, 3)，以此类推，最后一对是 (2N-2, 2N-1)。
 * 这些情侣的初始座位  row[i] 是由最初始坐在第 i 个座位上的人决定的。
 * <p>
 * 示例 1:
 * 输入: row = [0, 2, 1, 3]
 * 输出: 1
 * 解释: 我们只需要交换row[1]和row[2]的位置即可
 * <p>
 * 示例 2:
 * 输入: row = [3, 2, 0, 1]
 * 输出: 0
 * 解释: 无需交换座位，所有的情侣都已经可以手牵手了。
 * 说明:
 * <p>
 * len(row) 是偶数且数值在 [4, 60]范围内。
 * 可以保证row 是序列 0...len(row)-1 的一个全排列。
 */
public class Lc_0765_minSwapsCouples {
    /**
     * 假定第一对情侣的男生与第二对情侣的女生坐在了一起，而第二对情侣的男生与第三对情侣的女生坐在了一起。
     * 根据题意，要想让第二对情侣之间能够成功牵手，要么交换第一对情侣的男生与第二对情侣的男生，要么交换第二对情侣的女生与第三对情侣的女生。
     * 无论采用了两种交换方式中的哪一种，最后的结局都是「第二对情侣坐在了一起，且第一对情侣的男生与第三对情侣的女生坐在了一起」，因此两种交换方式是等价的。
     * 因此，我们将 NN 对情侣看做图中的 N 个节点；对于每对相邻的位置，如果是第 i 对与第 j 对坐在了一起，则在 i 号节点与 j 号节点之间连接一条边，代表需要交换这两对情侣的位置。
     * 如果图中形成了一个大小为 k 的环：i->j->k...-→i，则沿环方向，先交换 i,j，再交换 j,k，以此类推。在进行了 k−1 次交换后，这 k 对情侣就都能牵手了
     * 故我们只需要利用并查集求出图中的每个连通分量；对于每个连通分量而言，其大小 -1 就是需要交换的次数
     */
    public int minSwapsCouples(int[] row) {
        int len = row.length, tot = len / 2;
        int[] f = new int[tot];
        for (int i = 0; i < tot; i++) {
            f[i] = i;
        }

        for (int i = 0; i < len; i += 2) {
            int l = row[i] / 2;
            int r = row[i + 1] / 2;
            add(f, l, r);
        }

        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < tot; i++) {
            int fx = getf(f, i);
            map.put(fx, map.getOrDefault(fx, 0) + 1);
        }

        int ret = 0;
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            ret += entry.getValue() - 1;
        }
        return ret;
    }

    public int getf(int[] f, int x) {
        if (f[x] == x) {
            return x;
        }
        int newf = getf(f, f[x]);
        f[x] = newf;
        return newf;
    }

    public void add(int[] f, int x, int y) {
        int fx = getf(f, x);
        int fy = getf(f, y);
        f[fx] = fy;
    }

    @Test
    public void test() {
        int[] test1 = new int[]{0, 2, 1, 3};
        int[] test2 = new int[]{3, 2, 0, 1};
        minSwapsCouples(test1);
    }
}
