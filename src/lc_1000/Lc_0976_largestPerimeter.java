package lc_1000;

import java.util.Arrays;

/**
 * 三角形最大周长
 * 给定由一些正数（代表长度）组成的数组 A，返回由其中三个长度组成的、面积不为零的三角形的最大周长。
 * 如果不能形成任何面积不为零的三角形，返回 0。
 * <p>
 * 示例 1：
 * 输入：[2,1,2]
 * 输出：5
 * <p>
 * 示例 2：
 * 输入：[1,2,1]
 * 输出：0
 * <p>
 * 示例 3：
 * 输入：[3,2,3,4]
 * 输出：10
 * <p>
 * 示例 4：
 * 输入：[3,6,2,3]
 * 输出：8
 * <p>
 * 提示：
 * 3 <= A.length <= 10000
 * 1 <= A[i] <= 10^6
 *
 * @author lx
 */
public class Lc_0976_largestPerimeter {
    public int largestPerimeter(int[] A) {
        int left = A.length - 3, right = A.length - 1, res = 0;
        Arrays.sort(A);

        while (left >= 0) {
            if (A[left] + A[right - 1] > A[right]) {
                return A[left] + A[right - 1] + A[right];
            }
            right--;
            left = right - 2;
        }
        return res;
    }
}
