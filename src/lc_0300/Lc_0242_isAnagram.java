package lc_0300;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 给定两个字符串 s 和 t ，编写一个函数来判断 t 是否是 s 的字母异位词。
 * <p>
 * 示例 1:
 * 输入: s = "anagram", t = "nagaram"
 * 输出: true
 * <p>
 * 示例 2:
 * 输入: s = "rat", t = "car"
 * 输出: false
 * 说明:
 * 你可以假设字符串只包含小写字母。
 * <p>
 * 进阶:
 * 如果输入字符串包含 unicode 字符怎么办？你能否调整你的解法来应对这种情况？
 */
public class Lc_0242_isAnagram {
    /**
     * hash表
     * 统计字符出现次数
     * 时间复杂度O(n)，空间复杂度O(n)
     */
    public boolean isAnagram2(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }
        Map<Character, Integer> map = new HashMap<>();
        char[] cs = s.toCharArray();
        char[] ts = t.toCharArray();
        for (int i = 0; i < cs.length; i++) {
            Integer scount = map.get(cs[i]);
            map.put(cs[i], scount == null ? 1 : ++scount);
            Integer tcount = map.get(ts[i]);
            map.put(ts[i], tcount == null ? -1 : --tcount);
        }
        for (Integer count : map.values()) {
            if (count != 0) {
                return false;
            }
        }
        return true;
    }

    /**
     * 排序
     * 时间复杂度：O(nlogn)，其中 nn 为 ss 的长度。排序时间复杂O(nlogn)，比较两个字符串是否相等时间复杂度为 O(n)
     * 空间复杂度：O(logn)
     */
    public boolean isAnagram(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }
        char[] charS = s.toCharArray();
        char[] charT = t.toCharArray();
        Arrays.sort(charS);
        Arrays.sort(charT);
        return Arrays.toString(charS).equals(Arrays.toString(charT));
    }

}
