package lc_0100;

/**
 * 给定一个仅包含大小写字母和空格' '的字符串 s，返回其最后一个单词的长度。
 * 如果不存在最后一个单词，请返回 0。
 * <p>
 * 示例:
 * 输入: "Hello World"
 * 输出: 5
 */
public class Lc_0058_lengthOfLastWord {
    public static int lengthOfLastWord(String s) {
        int end = s.length() - 1;
        //1.找到最后一个非空字符的下标
        while (end >= 0 && s.charAt(end) == ' ') {
            end--;
        }
        if (end < 0) {
            return 0;
        }
        int start = end;
        //2.找到下一个空字符的下标
        while (start >= 0 && s.charAt(start) != ' ') {
            start--;
        }
        //3.相减之后得到字符串长度
        return end - start;
    }


    public static void main(String[] args) {
        System.out.println(lengthOfLastWord("a "));
    }
}
