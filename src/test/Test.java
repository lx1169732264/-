package test;

import java.util.ArrayList;
import java.util.List;

public class Test {


    public static void main(String[] args) {
        System.out.println(isOdd(0));

        System.out.println(-13>>0);
        System.out.println(-13>>2);
        System.out.println(-13>>3);
        System.out.println(-13>>4);
        System.out.println("--------------------");
        System.out.println(14>>1);
        System.out.println(14>>2);
        System.out.println(14>>3);
        System.out.println(14>>4);
        System.out.println("--------------------");
        System.out.println(15>>1);
        System.out.println(15>>2);
        System.out.println(15>>3);
        System.out.println(15>>4);

        System.out.println("--------------------");


        System.out.println(16>>1);
        System.out.println(16>>2);
        System.out.println(16>>3);
        System.out.println(16>>4);
        System.out.println(16>>5);


    }
    public static boolean isOdd(int a) {
        return (a & 1) == 1;
    }
}
