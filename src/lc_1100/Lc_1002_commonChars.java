package lc_1100;

import java.util.ArrayList;
import java.util.List;

/**
 * 给定仅有小写字母组成的字符串数组 A，返回列表中的每个字符串中都显示的全部字符（包括重复字符）组成的列表,顺序不限
 * <p>
 * 示例 1：
 * 输入：["bella","label","roller"]
 * 输出：["e","l","l"]
 * <p>
 * 示例 2：
 * 输入：["cool","lock","cook"]
 * 输出：["c","o"]
 *
 * @author lx
 */
public class Lc_1002_commonChars {
    public List<String> commonChars(String[] A) {
        if (A == null || A.length == 0 || A.length == 1) {
            return null;
        }

        //hash只需记录最小出现次数,为0则表示没有出现
        int[] hash = new int[26];
        //flag标志是否为第一次    第一次直接向hash传入char的数量    否则新建临时数组temp,将temp与hash进行统计
        boolean flag = true;
        for (String s : A) {
            char[] chars = s.toCharArray();
            if (flag) {
                for (char c : chars) {
                    hash[c - 97]++;
                }
                flag = false;
            } else {
                int[] temp = new int[26];
                for (char c : chars) {
                    temp[c - 97]++;
                }
                //新建数组后需要与原数组进行比较
                for (int i = 0; i < hash.length; ++i) {
                    hash[i] = Math.min(hash[i], temp[i]);
                }
            }
        }

        List<String> list = new ArrayList<>();
        for (int i = 0; i < hash.length; i++) {
            if (0 != hash[i]) {
                String tmp = String.valueOf((char) (i + 97));
                //对于重复出现的char进行重复add
                for (int j = 0; j < hash[i]; ++j) {
                    list.add(tmp);
                }
            }
        }
        return list;
    }
}