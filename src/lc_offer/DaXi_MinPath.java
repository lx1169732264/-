package lc_offer;

import org.junit.Assert;
import org.junit.Test;

import java.util.*;

/**
 * 给定⼀个 ⽆向图 包含 N 个节点和 M 条边, 每条边 Mi 的代价为 Ci 。图中⼀条路径的惩罚是指对该路径上所有边的代价 Ci 执⾏位运算或（bitwise OR）操作得到的。
 * 假如⼀条路径上包含了边 M1，M2，M3 … … ，Mk，那么对应的惩罚就是 C1 OR C2 OR C3 OR … OR Ck。（OR代表位运算或，即 “|” ）
 * <p>
 * 问题：给定图上两个节点 start 和 end，找到从 start 到 end 的所有路径中惩罚值最⼩的路径，对应的最⼩惩罚值作为结果返回。如果路径不存在就返回 -1。
 * 注意：任意两个节点之间最多存在⼀条边，图中可能存在有环路。
 * 限制条件: 1 <= n <=1000
 * 1 <= edges.length <= 10000
 * 1 <= Ci <=1024
 * <p>
 * 例：edges = [ [1，2，1]，[2，3，3]，[1，3，100] ]，对应的图如下：
 * 当 start = 1，end = 3 时，其最⼩惩罚路径是 1->2->3, C(1,2)=1并且C(2,3)=3,
 * 对应的惩罚值为 1 | 3 = 3。
 */
public class DaXi_MinPath {

    @Test
    public void test() {
        int[][] array = new int[][]{
                {1, 2, 1},
                {2, 3, 3},
                {1, 3, 100},
        };
        Assert.assertEquals(3, beautifulPath(3, array, 1, 3));
    }

    /**
     * @param n     节点总数；节点编号从 1 开始，⼀直到 n，共有 n 个；
     * @param nums ⽆向图的边；nums[i] 表示边Mi，其中 nums[i][0] 和 nums[i][1] 是Mi的两个节点的编号，nums[i][2] 是Mi对应的代价 Ci
     * @param start 路径的开始节点编号
     * @param end   路径的结束节点编号
     */
    int beautifulPath(int n, int[][] nums, int start, int end) {
        Map<Integer, Map<Integer, Integer>> edgesMap = getEdgesMap(n, nums);
        int result = minPath(edgesMap, start, end);
        return result == Integer.MAX_VALUE ? -1 : result;
    }

    //构建图
    private Map<Integer, Map<Integer, Integer>> getEdgesMap(int n, int[][] nums) {
        Map<Integer, Map<Integer, Integer>> map = new HashMap<>(2 * n);
        List<int[]> list = new ArrayList<>(n);
        Collections.addAll(list, nums);
        list.forEach(e -> {
            int start = e[0],end = e[1],distance = e[2];
            map.computeIfAbsent(start, k -> new HashMap<>());
            map.get(start).merge(end, distance, (a, b) -> b < a ? b : a);
        });
        return map;
    }

    //dfs求最小的路径
    private int minPath(Map<Integer, Map<Integer, Integer>> edgesMap, int start, int end) {
        Map<Integer, Integer> startMap = edgesMap.get(start);
        int result = Integer.MAX_VALUE;
        for (Map.Entry<Integer, Integer> e : startMap.entrySet()) {
            int next = e.getKey();
            int path = e.getValue();
            if (next != end) {
                edgesMap.remove(start);
                path = path | minPath(edgesMap, next, end);
                edgesMap.put(start, startMap);
            }
            if (path < result) {
                result = path;
            }
        }
        return result;
    }
}

