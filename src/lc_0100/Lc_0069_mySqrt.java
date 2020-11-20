package lc_0100;

/**
 * 计算并返回 x 的平方根，其中 x 是非负整数。
 * 由于返回类型是整数，结果只保留整数的部分，小数部分将被舍去。
 * <p>
 * 输入: 8
 * 输出: 2
 * 说明: 8 的平方根是 2.82842...,
 *      由于返回类型是整数，小数部分将被舍去。
 */
public class Lc_0069_mySqrt {
    public static int mySqrt(int x) {
        int left = 1, right = x, mid = (left + right) / 2, temp = 0;
        /**
         * int的最大值为2^31-1    开根号后最大值46339
         * 限定了right的大小,可以减少遍历次数
         */
        if (x >= (46340 * 2)) {
            right = 46341;
            mid = (left + right) / 2;
        }
        while (mid > left) {
            int sum = mid * mid;
            //当左右指针与mid相等或者sum=x,已经得出了结果
            if (sum == x | mid == left | right == mid) {
                return mid;
            } else if (sum < x) {
                temp = mid;
                mid = (mid + right) / 2;
                left = temp;
            } else {
                temp = mid;
                mid = (mid + left) / 2;
                right = temp;
            }
        }
        return mid;
    }

    public static void main(String[] args) {
        System.out.println(mySqrt(8));
    }
}
