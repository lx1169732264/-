package lc_0400;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * 给你一个字符串 s ，请你去除字符串中重复的字母，使得每个字母只出现一次。需保证 返回结果的字典序最小（要求不能打乱其他字符的相对位置）。
 * 注意：该题与 1081 相同
 * <p>
 * 示例 1：
 * 输入：s = "bcabc"
 * 输出："abc"
 * <p>
 * 示例 2：
 * 输入：s = "cbacdcbc"
 * 输出："acdb"
 * <p>
 * 提示：
 * 1 <= s.length <= 104
 * s 由小写英文字母组成
 */
public class Lc_0316_removeDuplicateLetters {
    public String removeDuplicateLetters(String s) {

        int len = s.length();
        //记录每个字符最后一次出现的下标
        int[] num = new int[26];
        for (int i = 0; i < len; i++) {
            num[s.charAt(i) - 'a'] = i;
        }

        Deque<Character> stack = new ArrayDeque<>();
        //字符在是否在栈中  默认false
        boolean[] visit = new boolean[26];

        for (int i = 0; i < len; i++) {
            if (visit[s.charAt(i) - 'a']) {
                continue;
            }

            //此时需要从栈中移除元素:
            //stack.peekLast() > s.charAt(i)    栈尾>当前
            //num[stack.peekLast() - 'a'] > i   栈尾元素最后一次出现的下标>i -> 栈尾元素在后续还会出现
            while (!stack.isEmpty() && stack.peekLast() > s.charAt(i) && num[stack.peekLast() - 'a'] > i) {
                Character top = stack.removeLast();
                //栈尾被移除后还需要 维护visit数组
                visit[top - 'a'] = false;
            }

            //visit=false,栈中不存在 入栈并维护visit数组
            stack.addLast(s.charAt(i));
            visit[s.charAt(i) - 'a'] = true;
        }


        StringBuffer sb = new StringBuffer();
        for (Character character : stack) {
            sb.append(character);
        }
        return sb.toString();
    }
}
