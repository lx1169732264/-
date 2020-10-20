package lc_500;

import java.util.Arrays;

/**
 * 给定两个字符串s1和s，判断s2是否包含s1的排列。即第一个字符串的排列之一是第二个字符串的子串。
 * <p>
 * 示例1:
 * 输入: s1 = "ab" s2 = "eidbaooo"
 * 输出: True
 *  
 * 示例2:
 * 输入: s1= "ab" s2 = "eidboaoo"
 * 输出: False
 *
 * @author boss
 */
public class Lc_0567_checkInclusion {
    /**
     * 滑动窗口思维:不去关注元素具体的排序,而去关注元素的数量
     * 求子字符串是否存在,可以将s2拆分为多个s1.length的小字符串
     * 然后统计小字符串与s1中字符出现频率,转化为数组,用数组的equals方法判断是否为true
     */
    public boolean checkInclusion(String s1, String s2) {
        int l1 = s1.length();
        int l2 = s2.length();
        int[] c1 = new int[26];
        int[] c2 = new int[26];
        for (char c : s1.toCharArray()) {
            c1[c - 'a']++;
        }
        for (int i = 0; i < l2; i++) {
            if (i >= l1) {
                c2[s2.charAt(i - l1) - 'a']--;
                c2[s2.charAt(i) - 'a']++;
            }
            if (Arrays.equals(c1, c2)) {
                return true;
            }
        }
        return false;
    }
}