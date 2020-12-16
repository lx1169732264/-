package other;

import org.junit.Assert;
import org.junit.Test;

/**
 * 给定⼀个⾮空字符串 s 和⼀个缩写 abbr，请校验它们是否匹配。 假设字符串中只包含⼩写字⺟，缩写中只包含⼩写字⺟和数字。
 * 缩写中的数字表示其缩略的字符数；连续多位数字表示⼀个多位数。
 * 例如，字符串 “word” 的缩写有且仅有以下这些：
 * [“word", “1ord”, “w1rd”,
 * “wo1d”, “wor1”, “2rd”, “w2d”, “wo2”, “1o1d”, “1or1”, “w1r1”, “1o2”, “2r1”,
 * “3d”, “w3”, “4"]
 * <p>
 * 例 1：输⼊：s = “internationalization"，abbr = "i12iz4n" 返回：true
 * 解释：abbr 中的 12 表示有⼗⼆个字符被缩略了。
 * <p>
 * 例 2：输⼊：s = “apple"，abbr = “a2e" 返回：false
 * 需要实现的⽅法原型：boolean valid(String word, String abbr)
 */
public class DaXi_Valid {
    boolean valid(String word, String abbr) {
        int i = 0, j = 0, n;
        while (j < abbr.length()) {
            n = 0;

            //不可能出现0的情况
            if (abbr.charAt(j) == '0') {
                return false;
            }

            //如果为数字,以n记录需要跳过的个数
            while (j < abbr.length() && !Character.isLetter(abbr.charAt(j))) {
                n = n * 10 + abbr.charAt(j) - '0';
                j++;
            }
            i += n;

            // 字符相同,各+1,下次循环
            if (word.charAt(i) == abbr.charAt(j)) {
                i++;
                j++;

            } else {
                //字符不相同,不匹配
                return false;
            }
        }
        return i == word.length() && j == abbr.length();
    }

    @Test
    public void test() {
        Assert.assertFalse(valid("apple", "a2e"));
        Assert.assertTrue(valid("word", "1ord"));
        Assert.assertTrue(valid("internationalization", "i12iz4n"));
    }
}
