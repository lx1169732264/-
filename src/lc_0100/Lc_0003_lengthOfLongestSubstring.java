package lc_0100;

import java.util.ArrayList;
import java.util.List;

/**
 * 给定一个字符串，请你找出其中不含有重复字符的 最长子串 的长度。
 * <p>
 * 示例 1
 * 输入: "abcabcbb"
 * 输出: 3
 * 解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
 * <p>
 * 示例 3:
 * 输入: "pwwkew"
 * 输出: 3
 * 解释: 因为无重复字符的最长子串是 "wke"，所以其长度为 3。
 * 请注意，你的答案必须是 子串 的长度，"pwke" 是一个子序列，不是子串。
 */
public class Lc_0003_lengthOfLongestSubstring {
    public static int lengthOfLongestSubstring(String s) {
        int n = s.length(), ans = 0;
        //假设字符集为ASCII 128
        int[] index = new int[128];
        for (int j = 0, i = 0; j < n; j++) {
            i = Math.max(index[s.charAt(j)], i);
            //无重复字符的最长子串ans
            ans = Math.max(ans, j - i + 1);
            //将下标保存在数组中
            index[s.charAt(j)] = j + 1;
        }
        return ans;
    }

    public static void main(String[] args) {
        String s = "sddfc";
        System.out.println(lengthOfLongestSubstring(s));
    }
}
