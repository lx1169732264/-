package lc_0100;

import java.util.ArrayList;
import java.util.List;

/**
 * 数字 n代表生成括号的对数，请你设计一个函数，用于能够生成所有可能的并且 有效的 括号组合。
 * <p>
 * 示例：
 * 输入：n = 3
 * 输出：[
 * "((()))",
 * "(()())",
 * "(())()",
 * "()(())",
 * "()()()"
 * ]
 */
public class Lc_0022_generateParenthesis {
    /**
     * 左括号数量不能多于右
     * 当左右都剩下0,递归结束
     */
    public static List<String> generateParenthesis(int n) {
        List<String> list = new ArrayList<>();
        if (n==0){
            return list;
        }
        recursive("", n, n,list);
        return list;
    }

    public static void recursive(String currStr, int left, int right,List<String> list) {
        if (left == 0 && right == 0) {
            list.add(currStr);
        }
        if (left > right) {
            return;
        }
        if (left > 0) {
            recursive(currStr + '(', left - 1, right,list);
        }
        if (right > 0) {
            recursive(currStr + ')', left, right - 1,list);
        }
    }

    public static void main(String[] args) {
        generateParenthesis(3);
    }
}
