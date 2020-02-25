package lc;

/**
 * @author boss
 */
public class Lc_0014_longestCommonPrefix {
    //方法1 水平扫描法
    public static String longestCommonPrefix(String[] strs) {
        if (strs.length == 0) {
            return "";
        }
        //先将下标为0的字符串作为参照物
        String prefix = strs[0];
        for (int i = 1; i < strs.length; i++) {
            //indexOf方法,当未查找到时,返回-1,返回0表示prefix就是当前字符串的前缀
            while (strs[i].indexOf(prefix) != 0) {
                //不满足条件,prefix长度-1
                prefix = prefix.substring(0, prefix.length() - 1);
                //直到prefix为空,结束判断,没有公共前缀
                if (prefix.isEmpty()) {
                    return "";
                }
            }
        }
        return prefix;
    }

    public static void main(String[] args) {
        String[] str = new String[]{"aasd", "aaaab", "aaac"};
        System.out.println(longestCommonPrefix(str));
    }
}
