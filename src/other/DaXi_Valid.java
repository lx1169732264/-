package other;

public class DaXi_Valid {
    public boolean valid(String word, String abbr) {
        int left = 0, right = 0;
        while (left < word.length() && right < abbr.length()) {
            //abbr[right]为数字,继续向后遍历
            char c = abbr.charAt(right);

            //不可能出现0的情况
            if (c == '0') {
                return false;
            }

            if (Character.isDigit(c)) {
                //遇到数字时,可能有多位数,统计得出结果
                int count = c - '0';
                while (right < abbr.length() && Character.isLetter(c)) {
                    count = count * 10 + c - '0';
                    right++;
                }
                //跳过对应数量的字符
                left += count;

                //都为字符
            } else if (word.charAt(left) == c) {
                left++;
                right++;

                //左右不相同,返回false
            } else if (word.charAt(left) != abbr.charAt(right)) {
                return false;
            }
        }

        return (left == word.length()) && (right == abbr.length());
    }
}