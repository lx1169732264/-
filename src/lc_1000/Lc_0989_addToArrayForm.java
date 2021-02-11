package lc_1000;

import java.util.*;

/**
 * 对于非负整数 X 而言，X 的数组形式是每位数字按从左到右的顺序形成的数组。例如，如果 X = 1231，那么其数组形式为 [1,2,3,1]。
 * 给定非负整数 X 的数组形式 A，返回整数 X+K 的数组形式。
 * <p>
 * 示例 1：
 * 输入：A = [1,2,0,0], K = 34
 * 输出：[1,2,3,4]
 * 解释：1200 + 34 = 1234
 * <p>
 * 示例 2：
 * 输入：A = [2,7,4], K = 181
 * 输出：[4,5,5]
 * 解释：274 + 181 = 455
 * <p>
 * 示例 3：
 * 输入：A = [2,1,5], K = 806
 * 输出：[1,0,2,1]
 * 解释：215 + 806 = 1021
 * <p>
 * 示例 4：
 * 输入：A = [9,9,9,9,9,9,9,9,9,9], K = 1
 * 输出：[1,0,0,0,0,0,0,0,0,0,0]
 * 解释：9999999999 + 1 = 10000000000
 * <p>
 * 提示：
 * 1 <= A.length <= 10000
 * 0 <= A[i] <= 9
 * 0 <= K <= 10000
 * 如果 A.length > 1，那么 A[0] != 0
 */
public class Lc_0989_addToArrayForm {

    /**
     * 将整个加数 K 加入数组表示的数的最低位。然后重复计算进位
     */
    public List<Integer> addToArrayForm2(int[] A, int K) {
        List<Integer> res = new ArrayList<Integer>();
        int n = A.length;
        for (int i = n - 1; i >= 0 || K > 0; --i, K /= 10) {
            if (i >= 0) {
                K += A[i];
            }
            res.add(K % 10);
        }
        Collections.reverse(res);
        return res;
    }

    public List<Integer> addToArrayForm(int[] A, int K) {
        String k = K + "";
        int left = A.length - 1, right = k.length() - 1, tmp = 0;
        List<Integer> res = new ArrayList<>(A.length);
        while (left >= 0 || right >= 0) {
            if (right >= 0) {
                tmp += Integer.parseInt(k.charAt(right) + "");
                right--;
            }
            if (left >= 0) {
                tmp += A[left];
                left--;
            }
            res.add(tmp % 10);
            tmp = tmp / 10;
        }

        if (1 == tmp) {
            res.add(tmp);
        }
        Collections.reverse(res);
        return res;
    }
}
