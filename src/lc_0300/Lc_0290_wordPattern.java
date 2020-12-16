package lc_0300;

import java.util.HashMap;
import java.util.Map;

/**
 * 给定一种规律 pattern 和一个字符串 str ，判断 str 是否遵循相同的规律。
 * 这里的 遵循 指完全匹配，例如， pattern 里的每个字母和字符串 str 中的每个非空单词之间存在着双向连接的对应规律。
 * <p>
 * 示例1:
 * 输入: pattern = "abba", str = "dog cat cat dog"
 * 输出: true
 * <p>
 * 示例 2:
 * 输入:pattern = "abba", str = "dog cat cat fish"
 * 输出: false
 * <p>
 * 示例 3:
 * 输入: pattern = "aaaa", str = "dog cat cat dog"
 * 输出: false
 * <p>
 * 示例 4:
 * 输入: pattern = "abba", str = "dog dog dog dog"
 * 输出: false
 * <p>
 * 说明:
 * 你可以假设 pattern 只包含小写字母， str 包含了由单个空格分隔的小写字母
 *
 * @author lx
 */
public class Lc_0290_wordPattern {
    public boolean wordPattern(String pattern, String s) {
        Map<Character, String> map = new HashMap<>();

        //指明了由单个空格分隔,不需要用\\s+
        String[] split = s.split("");
//        String[] split = s.split("\\s+");

        if (split.length != pattern.length()) {
            return false;
        }

        for (int i = 0; i < pattern.length(); i++) {
            char key = pattern.charAt(i);
            String value = split[i];

            if (map.containsKey(key)) {

                //存在key,value不相等
                if (!map.get(key).equals(value)) {
                    return false;
                }
                //存在key,value相等
                continue;

                //不存在key,但存在value -> s的一个字符串指向了多个pattern的字符
            } else if (map.containsValue(value)) {
                return false;
            }

            //key和value都不存在,放入map
            map.put(key, value);
        }
        return true;
    }
}
