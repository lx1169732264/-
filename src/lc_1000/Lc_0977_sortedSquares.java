package lc_1000;

import java.util.Arrays;

/**
 * 给定一个按非递减顺序排序的整数数组 A，返回每个数字的平方组成的新数组，要求也按非递减顺序排序。
 * <p>
 * 示例 1：
 * 输入：[-4,-1,0,3,10]
 * 输出：[0,1,9,16,100]
 * <p>
 * 示例 2：
 * 输入：[-7,-3,2,3,11]
 * 输出：[4,9,9,49,121]
 * <p>
 * 提示：
 * 1 <= A.length <= 10000
 * -10000 <= A[i] <= 10000
 * A 已按非递减顺序排序。
 *
 * @author lx
 */
public class Lc_0977_sortedSquares {
    public static int[] sortedSquares(int[] A) {
        return Arrays.stream(A).map(i -> i * i).sorted().toArray();
    }

    public static void main(String[] args) {
        int[] A = new int[]{-4, -1, 0, 3, 10};
        sortedSquares(A);
        Arrays.stream(sortedSquares(A)).asLongStream().forEach(System.out::println);
    }
}
