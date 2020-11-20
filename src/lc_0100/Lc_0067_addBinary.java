package lc_0100;

/**
 * 给定两个二进制字符串，返回他们的和（用二进制表示）。
 * 输入为非空字符串且只包含数字 1 和 0。
 * <p>
 * 示例 1:
 * 输入: a = "11", b = "1"
 * 输出: "100"
 */
public class Lc_0067_addBinary {
    public static String addBinary(String a, String b) {
        int al = a.length() - 1, bl = b.length() - 1;
        StringBuilder sb = new StringBuilder();
        int count = 0;
        while (al > 0 | bl > 0) {
            int sum = ((int)a.charAt(al) + (int)b.charAt(bl) + count);
            if (al == 0) {
                for (int i = bl; i > 0; i--) {
                    sb.append(b.charAt(bl));
                }
                return sb.reverse().toString();
            }else if (bl==0){
                for (int i = al; i > 0; i--) {
                    sb.append(a.charAt(al));
                }
                return sb.reverse().toString();
            }
            if (sum == 2) {
                count = 1;
                sb.append(sum);
            } else if (sum == 3) {
                sb.append(1);
            } else {
                count = 0;
                sb.append(sum);
            }
            al--;
            bl--;
        }
        return sb.reverse().toString();
    }

    public static void main(String[] args) {
        System.out.println(addBinary("1101","11"));
    }
}
