package lc_0200;

import org.junit.Test;

/**
 * 给定一个字符串，逐个翻转字符串中的每个单词。
 * 无空格字符构成一个 单词 。
 * 输入字符串可以在前面或者后面包含多余的空格，但是反转后的字符不能包括。
 * 如果两个单词间有多余的空格，将反转后单词间的空格减少到只含一个。
 * <p>
 * 示例 2：
 * 输入："  hello world!  "
 * 输出："world! hello"
 * 解释：输入字符串可以在前面或者后面包含多余的空格，但是反转后的字符不能包括。
 * <p>
 * 示例 3：
 * 输入："a good   example"
 * 输出："example good a"
 * 解释：如果两个单词间有多余的空格，将反转后单词间的空格减少到只含一个。
 * <p>
 * 示例 4：
 * 输入：s = "  Bob    Loves  Alice   "
 * 输出："Alice Loves Bob"
 * <p>
 * 提示：
 * 1 <= s.length <= 104
 * s 包含英文大小写字母、数字和空格 ' '
 * s 中 至少存在一个 单词
 * <p>
 * 进阶：
 * 请尝试使用 O(1) 额外空间复杂度的原地解法。
 */
public class Lc_0151_reverseWords {
    public String reverseWords(String s) {
        StringBuilder sb = new StringBuilder();
        //trim去掉首尾空格  \\s+ 匹配多个空格
        String[] s1 = s.trim().split("\\s+");
        for (int i = s1.length - 1; i >= 0; i--) {
            sb.append(s1[i]).append(" ");
        }
        sb.deleteCharAt(sb.length() - 1);
        return sb.toString();
    }
}
