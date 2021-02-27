package lc_1100;

import java.util.ArrayDeque;

/**
 * 最大连续1的个数
 * 给定一个由若干 0 和 1 组成的数组 A，我们最多可以将 K 个值从 0 变成 1 。
 * 返回仅包含 1 的最长（连续）子数组的长度。
 * <p>
 * 示例 1：
 * 输入：A = [1,1,1,0,0,0,1,1,1,1,0], K = 2
 * 输出：6
 * 解释：
 * [1,1,1,0,0,1,1,1,1,1,1]
 * 粗体数字从 0 翻转到 1，最长的子数组长度为 6
 * <p>
 * 示例 2：
 * 输入：A = [0,0,1,1,0,0,1,1,1,0,1,1,0,0,0,1,1,1,1], K = 3
 * 输出：10
 * 解释：
 * [0,0,1,1,1,1,1,1,1,1,1,1,0,0,0,1,1,1,1]
 * 粗体数字从 0 翻转到 1，最长的子数组长度为 10。
 * <p>
 * 提示：
 * 1 <= A.length <= 20000
 * 0 <= K <= A.length
 * A[i] 为 0 或 1
 */
public class Lc_1004_longestOnes {

    public int longestOnes2(int[] A, int K) {
        int n = A.length, left = 0, lsum = 0, rsum = 0, res = 0;
        for (int right = 0; right < n; ++right) {
            rsum += 1 - A[right];
            while (lsum < rsum - K) {
                lsum += 1 - A[left];
                ++left;
            }
            res = Math.max(res, right - left + 1);
        }
        return res;
    }

    public int longestOnes(int[] A, int K) {
        int res = 0, index = 0;
        ArrayDeque<Integer> deque = new ArrayDeque();
        while (index < A.length) {
            if (K < 0 && deque.size() > res && 0 == deque.poll()) {
                K++;
                continue;
            }

            if (0 == A[index]) {
                K--;
            }
            deque.offer(A[index]);
            if (K >= 0) {
                res = Math.max(deque.size(), res);
            }
            index++;
        }
        return res;
    }
}
