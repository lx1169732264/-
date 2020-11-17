package lc_0100;

import org.junit.Test;

import java.util.*;
import java.util.function.BiConsumer;
import java.util.zip.CheckedOutputStream;

/**
 * 给定一个字符串数组，将字母异位词组合在一起。字母异位词指字母相同，但排列不同的字符串。
 * <p>
 * 示例:
 * 输入: ["eat", "tea", "tan", "ate", "nat", "bat"]
 * 输出:
 * [
 * ["ate","eat","tea"],
 * ["nat","tan"],
 * ["bat"]
 * ]
 *
 * @author lx
 */
public class Lc_0049_groupAnagrams {
    @Test
    public List<List<String>> groupAnagrams(String[] strs) {
        List<List<String>> res = new LinkedList<>();
        Map<String, List<String>> map = new HashMap<>();
        for (String str : strs) {
            char[] chars = str.toCharArray();
            Arrays.sort(chars);
            if (map.containsKey(String.valueOf(chars))) {
                map.get(String.valueOf(chars)).add(str);
            } else {
                List<String> list = new LinkedList<>();
                list.add(str);
                map.put(Arrays.toString(chars), list);
            }
        }
        map.forEach((s, strings) -> res.add(strings));
        return res;
    }
}
