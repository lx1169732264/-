package lc_0100;

import org.junit.Assert;
import org.junit.Test;

import java.util.*;

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

    public int lengthOfLongestSubstring2(String s) {
        int max = 0;
        Queue<Character> queue = new LinkedList<>();

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);

            //重复时从队列弹出
            if (queue.contains(c)) {
                max = Math.max(max, queue.size());
                while (queue.peek() != c) {
                    queue.poll();
                }
                queue.poll();
            }

            queue.offer(c);
        }
        return Math.max(max, queue.size());
    }


    public int lengthOfLongestSubstring(String s) {
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

    @Test
    public void test() {
        String s = "sddfc";
        String s2 = "pwwkew";
        String s3 = "";
        String s4 = " ";
        Assert.assertEquals(3,lengthOfLongestSubstring(s));
        Assert.assertEquals(3,lengthOfLongestSubstring(s2));
        Assert.assertEquals(0,lengthOfLongestSubstring(s3));
        Assert.assertEquals(1,lengthOfLongestSubstring(s4));

    }
}
