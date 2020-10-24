package lc_0100;

import java.util.ArrayList;
import java.util.List;

/**
 * 将一个给定字符串根据给定的行数，以从上往下、从左到右进行 Z 字形排列。
 * <p>
 * 比如输入字符串为 "LEETCODEISHIRING" 行数为 3 时，排列如下：
 * <p>
 * L   C   I   R
 * E T O E S I I G
 * E   D   H   N
 * 之后，你的输出需要从左往右逐行读取，产生出一个新的字符串，比如："LCIRETOESIIGEDHN"。
 * <p>
 * 请你实现这个将字符串进行指定行数变换的函数：
 * <p>
 * string convert(string s, int numRows);
 * <p>
 * 示例 1:
 * 输入: s = "LEETCODEISHIRING", numRows = 3
 * 输出: "LCIRETOESIIGEDHN"
 * <p>
 * 示例 2:
 * 输入: s = "LEETCODEISHIRING", numRows = 4
 * 输出: "LDREOEIIECIHNTSG"
 * 解释:
 * L     D     R
 * E   O E   I I
 * E C   I H   N
 * T     S     G
 */
public class Lc_0006_convert {
    /**
     * Z型的字符串可以视为由若干个[0,numRows-1)组成 ,先是从上到下,再从下到上
     */
    public String convert(String s, int numRows) {
        if (numRows < 2) {
            return s;
        }
        List<StringBuilder> list = new ArrayList<>();
        for(int i = 0; i < numRows; i++) {
            list.add(new StringBuilder());
        }

        //flag=1 ,正序向list中的字符串增加   flag=-1逆序      index的变化顺序0,1..numRows-1,numRows-2....
        int index = 0, flag = -1;
        for (char c : s.toCharArray()) {
            list.get(index).append(c);
            if (index == 0 || index == numRows - 1) {
                flag = -flag;
            }
            index += flag;
        }
        StringBuilder res = new StringBuilder();
        list.forEach(res::append);
        return res.toString();
    }
}