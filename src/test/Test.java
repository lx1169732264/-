package test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Test {


    public static void main(String[] args) {
        String s = "aaabbsdasd";
        char[] chars = s.toCharArray();
        System.out.println(chars);
        System.out.println(String.valueOf(chars));
        System.out.println(Arrays.toString(chars));
        System.out.println(chars.toString());
    }
}
