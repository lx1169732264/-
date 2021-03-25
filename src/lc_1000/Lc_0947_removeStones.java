package lc_1000;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * n 块石头放置在二维平面中的一些整数坐标点上。每个坐标点上最多只能有一块石头。
 * 如果一块石头的 同行或者同列 上有其他石头存在，那么就可以移除这块石头。
 * 给你一个长度为 n 的数组 stones ，其中 stones[i] = [xi, yi] 表示第 i 块石头的位置，返回 可以移除的石子 的最大数量。
 * <p>
 * 示例 1：
 * 输入：stones = [[0,0],[0,1],[1,0],[1,2],[2,1],[2,2]]
 * 输出：5
 * 解释：一种移除 5 块石头的方法如下所示：
 * 1. 移除石头 [2,2] ，因为它和 [2,1] 同行。
 * 2. 移除石头 [2,1] ，因为它和 [0,1] 同列。
 * 3. 移除石头 [1,2] ，因为它和 [1,0] 同行。
 * 4. 移除石头 [1,0] ，因为它和 [0,0] 同列。
 * 5. 移除石头 [0,1] ，因为它和 [0,0] 同行。
 * 石头 [0,0] 不能移除，因为它没有与另一块石头同行/列
 * <p>
 * 示例 2：
 * 输入：stones = [[0,0],[0,2],[1,1],[2,0],[2,2]]
 * 输出：3
 * 解释：一种移除 3 块石头的方法如下所示：
 * 1. 移除石头 [2,2] ，因为它和 [2,0] 同行。
 * 2. 移除石头 [2,0] ，因为它和 [0,0] 同列。
 * 3. 移除石头 [0,2] ，因为它和 [0,0] 同行。
 * 石头 [0,0] 和 [1,1] 不能移除，因为它们没有与另一块石头同行/列
 * <p>
 * 示例 3：
 * 输入：stones = [[0,0]]
 * 输出：0
 * 解释：[0,0] 是平面上唯一一块石头，所以不可以移除它。
 *
 * 提示：
 * 1 <= stones.length <= 1000
 * 0 <= xi, yi <= 104
 * 不会有两块石头放在同一个坐标点上
 */
public class Lc_0947_removeStones {
    /**
     * 方法：并查集
     * 同行/列上有其他石头，就可以移除这块石头 -> 可以把连通图里的所有顶点根据这个规则删到只剩一个顶点
     * 删到最后，留在图中的顶点一定位于不同的行和不同的列。
     * 因此，并查集里的元素是 描述「横坐标」和「纵坐标」的数值
     * <p>
     * 所有横坐标为 x 的石头和所有纵坐标为 y 的石头都属于同一个连通分量。
     * 石头的位置是「有序数对（二维）」，并查集的底层是「一维数组」，需要在并查集里区分横纵坐标
     * 可以把其中一个坐标 映射 到另一个与 [0, 10000] 不重合的区间，可以的做法是把横坐标全减去 10001 或全加上 10001
     * 在并查集里维护连通分量的个数，新创建顶点的时候连通分量加 1；合并不在同一个连通分量中的两个并查集的时候，连通分量减 1。
     */
    public int removeStones(int[][] stones) {
        UnionFind unionFind = new UnionFind();

        for (int[] stone : stones) {
            unionFind.union(stone[0] + 10001, stone[1]);
        }
        return stones.length - unionFind.getCount();
    }

    private class UnionFind {

        private Map<Integer, Integer> parent;
        private int count;

        public UnionFind() {
            this.parent = new HashMap<>();
            this.count = 0;
        }

        public int find(int x) {
            if (!parent.containsKey(x)) {
                parent.put(x, x);
                // 新加入结点，结点的父亲结点是它自己，连通分量的总数 +1
                count++;
            } else if (x != parent.get(x)) {
                parent.put(x, find(parent.get(x)));
            }
            return parent.get(x);
        }

        public void union(int x, int y) {
            int rootX = find(x), rootY = find(y);
            if (rootX == rootY) {
                return;
            }

            parent.put(rootX, rootY);
            // 两个连通分量合并成为一个，连通分量的总数 -1
            count--;
        }

        public int getCount() {
            return count;
        }
    }

    @Test
    public void test() {
        //        int[][] test1=new int[][]{{0,0},{0,1},{1,0},{1,1},{2,1},{2,2},{3,2},{3,3},{3,4},{4,3},{4,4}};
        int[][] test2 = new int[][]{{0, 0}, {0, 1}, {1, 0}, {1, 2}, {2, 1}, {2, 2}};
        //        removeStones(test1);
        removeStones(test2);
    }
}
