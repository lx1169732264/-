package lc_0100;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

/**
 * 给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串，判断字符串是否有效。
 * <p>
 * 左括号必须用相同类型的右括号闭合。
 * 左括号必须以正确的顺序闭合。
 * 注意空字符串可被认为是有效字符串。
 * <p>
 * 示例 1:
 * 输入: "()"
 * 输出: true
 */
public class Lc_0020_isValid {
    /**方法1   出栈入栈
     * 栈先入后出特点恰好与本题括号排序特点一致，即若遇到左括号入栈，
     * 遇到右括号时将对应栈顶左括号出栈，则遍历完所有括号后 stack 仍然为空；
     */
    private static final Map<Character,Character> map = new HashMap<Character,Character>(){{
        put('{','}'); put('[',']'); put('(',')'); put('?','?');
    }};
    public static boolean isValid(String s) {
        if(s.length() > 0 && !map.containsKey(s.charAt(0))) {
            return false;
        }
        LinkedList<Character> stack = new LinkedList<Character>() {{ add('?'); }};
        for(Character c : s.toCharArray()){
            if(map.containsKey(c)) {
                stack.addLast(c);
            } else if(!map.get(stack.removeLast()).equals(c)) {
                return false;
            }
        }
        return stack.size() == 1;
    }


    public static void main(String[] args) {
        System.out.println(isValid("([)]"));
    }
}
