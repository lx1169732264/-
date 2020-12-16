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

        int i = 0;
        //递增
        while (i < A.length - 1 && A[i + 1] > A[i]) {
            i++;
        }

        //i==0:没有递增,只有递减    i == A.length - 1:没有递减,只有递增
        if (i == 0 || i == A.length - 1) {
            return false;
        }

        //递减
        while (i < A.length - 1 && A[i] > A[i + 1]) {
            i++;
        }

        //计算得到的i为单个山脉的长度,不会计算可能存在的其他山脉
        return i == A.length - 1;
    }
}

