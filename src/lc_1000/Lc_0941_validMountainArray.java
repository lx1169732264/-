package lc_1000;

/**
 * 有效的山脉数组
 * 给定一个整数数组 A，如果它是有效的山脉数组就返回 true，否则返回 false。
 * 如果 A 满足下述条件，那么它是一个山脉数组：
 * A.length >= 3
 * 在 0 < i < A.length - 1 条件下，存在 i 使得：
 * A[0] < A[1] < ... A[i-1] < A[i]
 * A[i] > A[i+1] > ... > A[A.length - 1]
 *
 * @author lx
 */
public class Lc_0941_validMountainArray {
    public boolean validMountainArray(int[] A) {
        if (A.length < 3) {
            return false;
        }

        int i = 0, j = A.length - 1;
        while (i < A.length - 1) {
            if (A[i + 1] > A[i]) {
                i++;
            } else {
                break;
            }
        }
        while (j > 1) {
            if (A[j - 1] > A[j]) {
                j--;
            } else {
                break;
            }
        }
        return i == j && i != A.length - 1 && j != 0;
    }
}

