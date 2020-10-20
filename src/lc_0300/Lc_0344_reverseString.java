package lc_0300;

/**
 * @author lx
 */
public class Lc_0344_reverseString {
    public static void reverseString(char[] s) {
        int low = 0, high = s.length - 1;
        char temp;
        while (low < high) {
            temp = s[low];
            s[low] = s[high];
            s[high] = temp;
            low++;
            high--;
        }
    }

    public static void main(String[] args) {
        char[] s = {'h', 'e', 'l', 'l', 'o'};
        reverseString(s);
        for (char c : s) {
            System.out.println(c);
        }
    }
}
