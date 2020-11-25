package lc_1400;

import java.util.Arrays;

/**
 * 上升下降字符串
 * 给你一个字符串 s ，请你根据下面的算法重新构造字符串：
 * 从 s 中选出 最小 的字符，将它 接在 结果字符串的后面。
 * 从 s 剩余字符中选出 最小 的字符，且该字符比上一个添加的字符大，将它 接在 结果字符串后面。
 * 重复步骤 2 ，直到你没法从 s 中选择字符。
 * 从 s 中选出 最大 的字符，将它 接在 结果字符串的后面。
 * 从 s 剩余字符中选出 最大 的字符，且该字符比上一个添加的字符小，将它 接在 结果字符串后面。
 * 重复步骤 5 ，直到你没法从 s 中选择字符。
 * 重复步骤 1 到 6 ，直到 s 中所有字符都已经被选过。
 * 在任何一步中，如果最小或者最大字符不止一个 ，你可以选择其中任意一个，并将其添加到结果字符串。
 * 请你返回将 s 中字符重新排序后的 结果字符串 。
 * <p>
 * 示例 1：
 * 输入：s = "aaaabbbbcccc"
 * 输出："abccbaabccba"
 * 解释：第一轮的步骤 1，2，3 后，结果字符串为 result = "abc"
 * 第一轮的步骤 4，5，6 后，结果字符串为 result = "abccba"
 * 第一轮结束，现在 s = "aabbcc" ，我们再次回到步骤 1
 * 第二轮的步骤 1，2，3 后，结果字符串为 result = "abccbaabc"
 * 第二轮的步骤 4，5，6 后，结果字符串为 result = "abccbaabccba"
 * <p>
 * 示例 3：
 * 输入：s = "leetcode"
 * 输出："cdelotee"
 * <p>
 * 示例 4：
 * 输入：s = "ggggggg"
 * 输出："ggggggg"
 * <p>
 * 提示：
 * 1 <= s.length <= 500
 * s 只包含小写英文字母
 *
 * @author lx
 */
public class Lc_1370_sortString {

    /**
     * 桶
     */
    public String sortString2(String s) {
        StringBuffer sb = new StringBuffer();
        int[] bucket = new int[26];
        for (char c : s.toCharArray()) {
            bucket[c - 'a']++;
        }

        while (sb.length() != s.length()) {
            for (int i = 0; i < 26; i++) {
                if (bucket[i] > 0) {
                    sb.append((char) ('a' + i));
                    bucket[i]--;
                }
            }
            for (int i = 25; i >= 0; i--) {
                if (bucket[i] > 0) {
                    sb.append((char) ('a' + i));
                    bucket[i]--;
                }
            }
        }
        return sb.toString();
    }


    /**
     * 先排序,后左->右,右->左遍历
     */
    public String sortString(String s) {
        if (null == s || s.length() <= 0) {
            return s;
        }

        char[] chars = s.toCharArray();
        Arrays.sort(chars);
        StringBuffer sb = new StringBuffer(new String(chars)), res = new StringBuffer();
        boolean asc = true;

        int pre = -1;
        while (sb.length() > 0) {
            if (asc) {
                for (int i = 0; i < sb.length(); i++) {
                    if (sb.charAt(i) > pre) {
                        pre = sb.charAt(i);
                        res.append(sb.charAt(i));
                        sb.deleteCharAt(i);
                        i--;
                    }
                }
            } else {
                for (int i = sb.length() - 1; i > 0; i--) {
                    if (sb.charAt(i) < pre) {
                        pre = sb.charAt(i);
                        res.append(sb.charAt(i));
                        sb.deleteCharAt(i);
                    }
                }
            }
            asc = !asc;
            pre = asc ? -1 : 200;
        }
        return res.toString();
    }
}