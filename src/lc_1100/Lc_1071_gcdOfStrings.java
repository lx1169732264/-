package lc_1100;

/**
 * 对于字符串S 和T，只有在 S = T + ... + T（T与自身连接 1 次或多次）时，我们才认定“T 能除尽 S”。
 * 返回最长字符串X，要求满足X 能除尽 str1 且X 能除尽 str2。
 * <p>
 * 示例 1：
 * 输入：str1 = "ABCABC", str2 = "ABC"
 * 输出："ABC"
 * <p>
 * 示例 2：
 * 输入：str1 = "ABABAB", str2 = "ABAB"
 * 输出："AB"
 * <p>
 * 示例 3：
 * 输入：str1 = "LEET", str2 = "CODE"
 * 输出：""
 * <p>
 * 提示：
 * 1 <= str1.length <= 1000
 * 1 <= str2.length <= 1000
 * str1[i] 和str2[i] 为大写英文字母
 */
public class Lc_1071_gcdOfStrings {
    /**
     * 方法1 辗转相除法
     * 若s1和s2存在相同的除数X 则s1+s2=s2+s1
     * 将字符串做除法,所得余数一定是若干个(0~n)"除尽字符串"组成的
     * 将s1,s2中,小的一方(例如s2)再次与余数进行除法,直至除尽
     * 则此时s2刚好为余数的若干倍,就得出了结果
     *
     * @param str1
     * @param str2
     * @return
     */
    public String gcdOfStrings(String str1, String str2) {
        // 假设str1是N个x，str2是M个x，那么str1+str2肯定是等于str2+str1的。
        if (!(str1 + str2).equals(str2 + str1)) {
            return "";
        }
        // 辗转相除法求gcd。
        return str1.substring(0, gcd(str1.length(), str2.length()));
    }

    private int gcd(int a, int b) {
        return b == 0 ? a : gcd(b, a % b);
    }
}


