package lc_offer;

/**
 * 给定两个排序后的数组 A 和 B，其中 A 的末端有足够的缓冲空间容纳 B。 编写一个方法，将 B 合并入 A 并排序。
 * 初始化 A 和 B 的元素数量分别为 m 和 n。
 * <p>
 * 示例:
 * 输入:
 * A = [1,2,3,0,0,0], m = 3
 * B = [2,5,6],       n = 3
 * <p>
 * 输出: [1,2,2,3,5,6]
 * 说明:
 * <p>
 * A.length == n + m
 *
 * @author lx
 */
public class Lc_merge {
    public void merge(int[] A, int m, int[] B, int n) {
        int index = A.length - 1, left = m - 1, right = n - 1;

        while (right >= 0) {
            //left可能为-1,即A中没有元素,全为0
            if (left < 0 || B[right] >= A[left]) {
                A[index] = B[right];
                right--;

            } else {
                A[index] = A[left];
                left--;
            }
            index--;
        }
    }
}
