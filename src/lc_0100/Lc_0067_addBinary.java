package lc_0100;

import org.junit.Test;

/**
 * 给定两个二进制字符串，返回他们的和（用二进制表示）。
 * 输入为非空字符串且只包含数字1和0。
 * <p>
 * 示例1:
 * 输入: a = "11", b = "1"
 * 输出: "100"
 */
public class Lc_0067_addBinary {

    public String addBinary(String a, String b) {
        if (a.length() < b.length()) {
            return addBinary(b, a);
        }

        char[] arrayA = a.toCharArray();
        char[] arrayB = b.toCharArray();

        StringBuilder sb = new StringBuilder();
        int carry = 0;
        for (int i = arrayA.length - 1; i >= 0; i--) {

            int count = carry;
            count += arrayA[i] == '0' ? 0 : 1;

            int index = i - arrayA.length + arrayB.length;
            if (index >= 0) {
                count += arrayB[index] == '0' ? 0 : 1;
            }

            sb.insert(0, (char) (count % 2 + '0'));
            carry = count >= 2 ? 1 : 0;
        }

        if (carry == 1) {
            sb.insert(0, '1');
        }
        return sb.toString();
    }

    @Test
    public void test() {
        System.out.println(addBinary("1101", "11"));
    }
}
