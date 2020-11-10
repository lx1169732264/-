package lc_0800;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * 字符串 S 由小写字母组成。我们要把这个字符串划分为尽可能多的片段，同一个字母只会出现在其中的一个片段。返回一个表示每个字符串片段的长度的列表。
 * <p>
 * 示例 1：
 * 输入：S = "ababcbacadefegdehijhklij"
 * 输出：[9,7,8]
 * <p>
 * 解释：
 * 划分结果为 "ababcbaca", "defegde", "hijhklij"。
 * 每个字母最多出现在一个片段中。
 * 像 "ababcbacadefegde", "hijhklij" 的划分是错误的，因为划分的片段数较少。
 * <p>
 * 提示：
 * S的长度在[1, 500]之间。
 * S只包含小写字母 'a' 到 'z' 。
 *
 * @author lx
 */
public class Lc_0763_partitionLabels {

    /**
     * 先遍历一次,得到所有字符对应的最右边的下标,存入数组
     * 再进行一次遍历
     * 设置左右指针,左指针初始0,右指针为该字符对应的最右下标
     * 对指针之间的元素进行遍历,拓展右指针的范围,使得右指针之内的元素持续包含一组独立出现的字符
     * 左右指针重叠,结束遍历,存入list
     */
    public List<Integer> partitionLabels2(String S) {
        if (null == S || 0 == S.length()) {
            return null;
        }
        int len = S.length();

        int[] ma = new int[26];
        for (int i = 0; i < len; ++i) {
            ma[S.charAt(i) - 'a'] = i;
        }

        List<Integer> ans = new ArrayList<>();
        int left = 0;
        while (left < len) {
            char curLeft = S.charAt(left);
            int right = ma[curLeft - 'a'];
            for (int i = left + 1; i < right; ++i) {
                if (ma[S.charAt(i) - 'a'] > right) {
                    right = ma[S.charAt(i) - 'a'];
                }
            }
            ans.add(right - left + 1);
            left = right + 1;
        }
        return ans;
    }

    public static List<Integer> partitionLabels(String S) {
        List<Integer> result = new LinkedList<>();
        int index = 0, last = 0;
        while (!"".equals(S)) {
            if (1 == S.length()) {
                result.add(1);
                break;
            }

            last = Math.max(last, S.lastIndexOf(S.charAt(index)));
            if (index != last) {
                index++;
                continue;
            }

            result.add(last + 1);
            S = S.substring(last + 1);
            last = 0;
            index = 0;
        }
        return result;
    }

    public static void main(String[] args) {
        List<Integer> list = partitionLabels("caedbdedda");

        list.forEach(System.out::println);
    }
}
