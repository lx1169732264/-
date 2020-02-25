package lc;

/**
 * 给定一个仅包含大小写字母和空格 ' ' 的字符串 s，返回其最后一个单词的长度。
 * 如果不存在最后一个单词，请返回 0 。
 * <p>
 * 示例:
 * 输入: "Hello World"
 * 输出: 5
 */
public class Lc_0058_lengthOfLastWord {
    public static int lengthOfLastWord(String s) {
        if (s.endsWith(" ")) {
            return 0;
        }
        int i = s.lastIndexOf(' ');
        if (i == -1){
            return s.length();
        }else if(i == s.length()){
            return
        }
        return s.length()-i-1;
    }

    public static void main(String[] args) {
        System.out.println(lengthOfLastWord("a "));
    }
}
