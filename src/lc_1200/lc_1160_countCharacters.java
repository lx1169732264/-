package lc_1200;

import java.util.HashMap;
import java.util.Map;

/**
 * 给你一份『词汇表』（字符串数组） words 和一张『字母表』（字符串） chars。
 * 假如你可以用 chars 中的『字母』（字符）拼写出 words 中的某个『单词』（字符串），那么我们就认为你掌握了这个单词。
 * 注意：每次拼写时，chars 中的每个字母都只能用一次。
 * 返回词汇表 words 中你掌握的所有单词的 长度之和。
 * <p>
 * 示例 1：
 * 输入：words = ["cat","bt","hat","tree"], chars = "atach"
 * 输出：6
 * 解释：
 * 可以形成字符串 "cat" 和 "hat"，所以答案是 3 + 3 = 6。
 * <p>
 * 示例 2：
 * 输入：words = ["hello","world","leetcode"], chars = "welldonehoneyr"
 * 输出：10
 * 解释：
 * 可以形成字符串 "hello" 和 "world"，所以答案是 5 + 5 = 10。
 */
public class lc_1160_countCharacters {
    /**
     * 方法2:数组      字母是26位,属于固定长度,用数组的下标代表字母,从而让数组发挥出map的效果
     * 数组的效率高于map,这个是改良方法
     * 对于"已知长度"的问题,都可以采用"数组代替map"的思路
     */
    public static int countCharacters2(String[] words, String chars) {
        // 统计字母表的字母出现次数
        int[] chars_count = count(chars);
        int res = 0;
        for (String word : words) {
            // 统计单词的字母出现次数
            int[] word_count = count(word);
            if (contains(chars_count, word_count)) {
                res = res + word.length();
            }
        }
        return res;
    }

    // 检查字母表的字母出现次数是否覆盖单词的字母出现次数
    static boolean contains(int[] chars_count, int[] word_count) {
        for (int i = 0; i < 26; i++) {
            if (chars_count[i] < word_count[i]) {
                return false;
            }
        }
        return true;
    }

    // 统计 26 个字母出现的次数
    static int[] count(String word) {
        int[] counter = new int[26];
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            counter[c - 'a']++;
        }
        return counter;
    }


    /**
     * 方法1:hashmap   用map分别记录chars中每个字符的出现次数
     * 然后遍历words,对于map中存在的字符,值-1
     * 对于不存在的话,跳出循环
     */
    public static int countCharacters(String[] words, String chars) {
        /**
         * value的值代表了chars中剩余的数量,在遍历中会改变值,需要clone一个map,currmap用于进行遍历
         * map的赋值是传址,会导致currmap的改变使得map的值也改变
         * map.putAll是传值    这是java为map设计的clone方法
         */
        Map<Character, Integer> map = new HashMap<>();
        Map<Character, Integer> currmap = new HashMap<>();
        //count为合格的字符串长度
        int count = 0;
        for (int i = 0; i <= chars.length() - 1; i++) {
            char c = chars.charAt(i);
            if (map.containsKey(c)) {
                map.replace(c, map.get(c) + 1);
            } else {
                map.put(c, 1);
            }
        }

        for (String word : words) {
            //map的传值是传址!!!!不能用
            currmap.putAll(map);
            for (int i = 0; i <= word.length() - 1; i++) {
                char c = word.charAt(i);
                if (currmap.containsKey(c) && currmap.get(c) > 0) {
                    //处理最后一个字符时,在count中统计字符串长度
                    if (i == word.length() - 1) {
                        count = count + word.length();
                    }
                    currmap.replace(c, currmap.get(c) - 1);
                } else {
                    //word中存在chars中没有的字符,跳出循环
                    break;
                }
            }
        }
        return count;
    }

    public static void main(String[] args) {
        String[] str = new String[]{"cat", "bt", "hat", "tree"};
        String chars = "atach";
        System.out.println(countCharacters2(str, chars));
    }
}
