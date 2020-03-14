package lc_0100;

/**
 * 给定一个 haystack 字符串和一个 needle 字符串，在 haystack 字符串中找出 needle 字符串出现的第一个位置 (从0开始)。如果不存在，则返回  -1。
 * <p>
 * 示例 1:
 * 输入: haystack = "hello", needle = "ll"
 * 输出: 2
 */
public class Lc_0028_strStr {
    public static int strStr(String haystack, String needle) {
        if (needle.equals("")) {
            return 0;
        }
        if (haystack.equals("")) {
            return -1;
        }
        int count = needle.length();
        for (int i = 0; i <= haystack.length() - 1; i++) {
            if (haystack.charAt(i) == needle.charAt(0)) {
                if (((haystack.length() - i) >= count) && haystack.substring(i, i + count).equals(needle)) {
                    return i;
                }
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        System.out.println(strStr("asas", ""));
    }
}
