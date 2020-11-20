package ms_01;

/**
 * 字符串压缩。利用字符重复出现的次数，编写一种方法，实现基本的字符串压缩功能
 * 比如，字符串aabcccccaaa会变为a2b1c5a3。若“压缩”后的字符串没有变短，则返回原先的字符串
 * 你可以假设字符串中只包含小写英文字母（a至z）。
 * <p>
 * 示例1:
 * 输入："aabcccccaaa"
 * 输出："a2b1c5a3"
 * <p>
 * 示例2:
 * 输入："abbccd"
 * 输出："abbccd"
 * 解释："abbccd"压缩后为"a1b2c2d1"，比原字符串长度更长。
 */
public class ms_01_06_compressString {
    public static String compressString(String S) {
        if (S.isEmpty()) {
            return "";
        }
        int count = 1, n = S.length();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i <= n - 1; i++) {
            //在遍历到i=n-1时,已遍历完整个字符串,但是最后资格字符并没有被输出
            if (i == n - 1) {
                sb.append(S.charAt(n - 1));
                sb.append(count);
            } else if (S.charAt(i) == S.charAt(i + 1)) {
                count++;
            } else {
                sb.append(S.charAt(i));
                sb.append(count);
                count = 1;
            }
        }
        //若“压缩”后的字符串没有变短，则返回原先的字符串。
        if (sb.length() >= n) {
            return S;
        } else {
            return sb.toString();
        }
    }

    public static void main(String[] args) {
        System.out.println(compressString("aaasssffff"));
    }
}
