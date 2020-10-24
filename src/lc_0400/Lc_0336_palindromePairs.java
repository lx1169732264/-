package lc_0400;

import java.util.*;

/**
 * 给定一组互不相同的单词， 找出所有不同的索引对(i, j)，使得列表中的两个单词， words[i] + words[j] ，可拼接成回文串。
 * <p>
 * 示例 1：
 * 输入：["abcd","dcba","lls","s","sssll"]
 * 输出：[[0,1],[1,0],[3,2],[2,4]]
 * 解释：可拼接成的回文串为 ["dcbaabcd","abcddcba","slls","llssssll"]
 * <p>
 * 示例 2：
 * 输入：["bat","tab","cat"]
 * 输出：[[0,1],[1,0]]
 * 解释：可拼接成的回文串为 ["battab","tabbat"]
 */
public class Lc_0336_palindromePairs {
    /**
     * 组成回文对,如 abcddc ba,则长字符串必定内含一个回文子串 cddc
     * 这样在与短字符串进行拼接时,才能构成回文串
     * <p>
     * 可以将长字符串中的回文子串扣掉,剩余的字符串用来拼接回文串
     * 有2种情况:回文子串偏右,则拼接的字符串在右边,偏左则左,中间的话,又因为单词之间互不相同,不能回文对
     */
    Map<String, Integer> indices = new HashMap<>();

    public List<List<Integer>> palindromePairs(String[] words) {
        int n = words.length;
        for (int i = 0; i < n; i++) {
            indices.put(words[i], i);
        }
        List<List<Integer>> ret = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            String word = words[i];
            int m = word.length();
            if (m == 0) {
                continue;
            }
            for (int j = 0; j <= m; j++) {
                if (isPalindrome(word, j, m - 1)) {
                    int leftId = findWord(word, 0, j - 1);
                    if (leftId != -1 && leftId != i) {
                        ret.add(Arrays.asList(i, leftId));
                    }
                }
                if (j != 0 && isPalindrome(word, 0, j - 1)) {
                    int rightId = findWord(word, j, m - 1);
                    if (rightId != -1 && rightId != i) {
                        ret.add(Arrays.asList(rightId, i));
                    }
                }
            }
        }
        return ret;
    }

    //在mao中寻找是否能构成回文对
    public int findWord(String s, int left, int right) {
        String tmp = new StringBuilder(s.substring(left, right + 1)).reverse().toString();
        return indices.getOrDefault(tmp, -1);
    }

    //判断是否回文
    public boolean isPalindrome(String s, int left, int right) {
        int len = right - left + 1;
        for (int i = 0; i < len / 2; i++) {
            if (s.charAt(left + i) != s.charAt(right - i)) {
                return false;
            }
        }
        return true;
    }
}
