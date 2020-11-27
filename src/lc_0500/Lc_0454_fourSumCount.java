package lc_0500;

import java.util.HashMap;
import java.util.Map;

/**
 * 给定四个包含整数的数组列表 A , B , C , D ,计算有多少个元组 (i, j, k, l) ，使得 A[i] + B[j] + C[k] + D[l] = 0
 * 为了使问题简单化，所有的 A, B, C, D 具有相同的长度 N，且 0 ≤ N ≤ 500 。所有整数的范围在 -228 到 228 - 1 之间，最终结果不会超过 231 - 1
 * <p>
 * 输入:
 * A = [ 1, 2]
 * B = [-2,-1]
 * C = [-1, 2]
 * D = [ 0, 2]
 * 输出:2
 * 解释:
 * 两个元组如下:
 * 1. (0, 0, 0, 1) -> A[0] + B[0] + C[0] + D[1] = 1 + (-2) + (-1) + 2 = 0
 * 2. (1, 1, 0, 0) -> A[1] + B[1] + C[0] + D[0] = 2 + (-1) + (-1) + 0 = 0
 *
 * @author lx
 */
public class Lc_0454_fourSumCount {
    /**
     * hashMap
     * 枚举A+B,存入map map中记录值出现的次数
     * 枚举C+D,查看-(C+D)是否存在于map中,存在则res+map的value
     */
    public int fourSumCount2(int[] A, int[] B, int[] C, int[] D) {
        Map<Integer, Integer> countAB = new HashMap<>();
        for (int u : A) {
            for (int v : B) {
                countAB.put(u + v, countAB.getOrDefault(u + v, 0) + 1);
            }
        }
        int ans = 0;
        for (int u : C) {
            for (int v : D) {
                if (countAB.containsKey(-u - v)) {
                    ans += countAB.get(-u - v);
                }
            }
        }
        return ans;
    }


    /**
     * 数组+hashMap
     * 枚举A+B,C+D,分别存入数组和map map中记录C+D出现的次数
     * 遍历A+B,查看-(A+B)是否存在于map中,存在则res+map的value
     */
    public int fourSumCount(int[] A, int[] B, int[] C, int[] D) {
        int res = 0, index = 0, len = A.length;
        Map<Integer, Integer> map = new HashMap<>(2048);
        Integer[] AB = new Integer[len * len];
        for (int i = 0; i < len; i++) {
            for (int j = 0; j < len; j++) {
                AB[index] = A[i] + B[j];
                if (map.containsKey(C[i] + D[j])) {
                    map.put(C[i] + D[j], map.get(C[i] + D[j]) + 1);
                } else {
                    map.put(C[i] + D[j], 1);
                }
                index++;
            }
        }
        for (Integer i : AB) {
            res += map.getOrDefault(-i, 0);
        }
        return res;
    }
}
