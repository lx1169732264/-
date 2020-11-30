package lc_0800;

/**
 * 重构字符串
 * 给定一个字符串S，检查是否能重新排布其中的字母，使得两相邻的字符不同。
 * 若可行，输出任意可行的结果。若不可行，返回空字符串。
 * <p>
 * 示例 1:
 * 输入: S = "aab"
 * 输出: "aba"
 * <p>
 * 示例 2:
 * 输入: S = "aaab"
 * 输出: ""
 * <p>
 * 注意:
 * S 只包含小写字母并且长度在[1, 500]区间内。
 *
 * @author lx
 */
public class Lc_0767_reorganizeString {

    /**
     * 首先统计每个字母的出现次数，然后根据每个字母的出现次数重构字符串。
     * 当 n 是奇数且出现最多的字母的出现次数是(n+1)/2时，出现次数最多的字母必须全部放置在偶数下标，否则一定会重复。其余情况下，每个字母放置在偶数下标或者奇数下标都可行
     * 维护偶数下标evenIndex和奇数下标oddIndex，初始值分别为0和1。遍历每个字母，根据每个字母的出现次数判断字母应该放置在偶数下标还是奇数下标。
     * 首先考虑是否可以放置在奇数下标。只要字母的出现次数<= n/2，就可以放置在奇数下标，只有当字母的出现次数>n/2，才必须放置在偶数下标。
     * 字母的出现次数超过字符串的长度的一半只可能发生在 n 是奇数的情况下，且最多只有一个字母的出现次数会超过字符串的长度的一半。
     * <p>
     * 因此通过如下操作在重构的字符串中放置字母。
     * 如果字母的出现次数> 0 且<=n/2，且oddIndex没有超出数组下标范围，则将字母放置在oddIndex，然后将oddIndex+2
     * 如果字母的出现次数>n/2，或oddIndex 超出数组下标范围，则将字母放置在evenIndex，然后将evenIndex+2
     * <p>
     * 如果一个字母出现了多次，则重复上述操作，直到该字母全部放置完毕。
     * 那么上述做法是否可以确保相邻的字母都不相同？考虑以下三种情况。
     * **如果 n 是奇数且存在一个字母的出现次数为 (n+1)/2(n+1)/2，则该字母全部被放置在偶数下标，其余的 (n-1)/2(n−1)/2 个字母都被放置在奇数下标，因此相邻的字母一定不相同。
     * **如果同一个字母全部被放置在奇数下标或全部被放置在偶数下标，则该字母不可能在相邻的下标出现。
     * **如果同一个字母先被放置在奇数下标直到奇数下标越界，然后被放置在偶数下标，由于该字母的出现次数不会超过n/2，因此该字母的最小奇数下标与最大偶数下标之差>= 3，不可能在相邻的下标出现
     */
    public String reorganizeString2(String S) {
        if (S.length() < 2) {
            return S;
        }
        int[] counts = new int[26];
        int maxCount = 0;
        int length = S.length();
        for (int i = 0; i < length; i++) {
            char c = S.charAt(i);
            counts[c - 'a']++;
            maxCount = Math.max(maxCount, counts[c - 'a']);
        }
        if (maxCount > (length + 1) / 2) {
            return "";
        }
        char[] reorganizeArray = new char[length];
        int evenIndex = 0, oddIndex = 1;
        int halfLength = length / 2;
        for (int i = 0; i < 26; i++) {
            char c = (char) ('a' + i);
            while (counts[i] > 0 && counts[i] <= halfLength && oddIndex < length) {
                reorganizeArray[oddIndex] = c;
                counts[i]--;
                oddIndex += 2;
            }
            while (counts[i] > 0) {
                reorganizeArray[evenIndex] = c;
                counts[i]--;
                evenIndex += 2;
            }
        }
        return new String(reorganizeArray);
    }


    public String reorganizeString(String S) {
        StringBuilder sb = new StringBuilder();
        int[] count = new int[26];
        for (int i = 0; i < S.length(); i++) {
            count[S.charAt(i) - 'a']++;
        }

        while (sb.length() != S.length()) {
            int min = Integer.MAX_VALUE, index = 0;
            for (int i = 25; i >= 0; i--) {
                if (count[i] != 0 && count[i] < min) {
                    min = count[i];
                    index = i;
                }
            }
            if (min == 0) {
                break;
            }

            char s = (char) ('a' + index);
            int temp = 0;
            while (count[index] != 0) {
                if (temp < sb.length()) {
                    sb.insert(temp, s);
                    temp += 2;
                } else {
                    sb.append(s);
                }
                count[index]--;
            }
        }

        for (int i = 0; i < sb.length() - 1; i++) {
            if (sb.charAt(i) == sb.charAt(i + 1)) {
                return "";
            }
        }
        return sb.toString();
    }
}
