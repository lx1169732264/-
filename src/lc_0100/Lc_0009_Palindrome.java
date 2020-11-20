package lc_0100;

import java.util.ArrayList;
import java.util.List;

/**
 * 判断一个整数是否是回文数。回文数是指正序（从左向右）和倒序（从右向左）读都是一样的整数。
 * <p>
 * 示例 1:
 * 输入: 121
 * 输出: true
 */
public class Lc_0009_Palindrome {
    public static boolean isPalindrome3(int x) {
        //将x的每一位保存在list中
        List<Integer> list = new ArrayList<>();
        while (x % 10 >= 1) {
            x = x / 10;
            list.add(x % 10);
        }
        //双指针比较是否相等
        int left = 0, right = list.size() - 1;
        while (left < right) {
            if (!list.get(left).equals(list.get(right))) {
                return false;
            } else {
                left++;
                right--;
            }
        }
        return true;
    }


    /**
     * 方法1:将数字本身反转，然后将反转后的数字与原始数字进行比较，如果它们是相同的，那么这个数字就是回文。
     *
     * @param x
     * @return
     */
    public static boolean isPalindrome(int x) {
        //x<0或x尾数为0时,不满足回文数
        //x=0满足回文数
        if (x < 0 || (x % 10 == 0 && x != 0)) {
            return false;
        }

        //保存x的后半部分
        int revertedNumber = 0;
        while (x > revertedNumber) {
            revertedNumber = revertedNumber * 10 + x % 10;
            x /= 10;
        }
        return x == revertedNumber || x == revertedNumber / 10;
    }

    public static void main(String[] args) {
        System.out.println(isPalindrome3(1011));
    }
}
