package lc_0100;

/**
 * 给定一个haystack 字符串和一个 needle 字符串，在 haystack 字符串中找出 needle 字符串出现的第一个位置 (从0开始)。如果不存在，则返回 -1。
 * <p>
 * 示例 1:
 * 输入: haystack = "hello", needle = "ll"
 * 输出: 2
 */
public class Lc_0028_strStr {
    /**
     * 滑动窗口     适合字符串遍历时,寻找一段长度固定的字符
     * needle的第一个字符为startChar
     * 每次找到startChar在字符串中出现的下标start ,截取start+needle.length长度的字符串,比较是否相等
     */
    public int strStr(String haystack, String needle) {
        if ("".equals(needle)) {
            return 0;
        }

        char startChar = needle.charAt(0);
        int start = haystack.indexOf(startChar);
        int length = needle.length();

        while (-1 != start && start + length <= haystack.length()) {
            if (haystack.substring(start, start + length).equals(needle)) {
                return start;
            }
            start = haystack.indexOf(startChar, start + 1);
        }
        return -1;
    }
}