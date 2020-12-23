package lc_0400;

/**
 * 字符串中的第一个唯一字符
 * 给定一个字符串，找到它的第一个不重复的字符，并返回它的索引。如果不存在，则返回 -1。
 * <p>
 * 示例：
 * s = "leetcode"
 * 返回 0
 * <p>
 * s = "loveleetcode"
 * 返回 2
 *
 * @author lx
 */
public class Lc_0387_firstUniqChar {
    public int firstUniqChar(String s) {
        int[] count = new int[26];
        char[] chars = s.toCharArray();
        for (char c : chars) {
            count[c - 'a']++;
        }

        int res = -1;
        for (int i = 0; i < chars.length; i++) {
            if (1 == count[chars[i] - 'a']) {
                return i;
            }
        }
        return res;
    }
}