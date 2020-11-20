package test;

import lc_0200.Lc_0134_canCompleteCircuit;

import java.util.ArrayList;
import java.util.List;

public class Test {


    public static void main(String[] args) {
//
//        int[] ints = {2, 3, 4};
//        int[] ints2 = {3, 4, 3};
//        int[] ints = {1,2,3,4,5};
//        int[] ints2 = {3,4,5,1,2};
        int[] ints = {5,1,2,3,4};
        int[] ints2 = {4,4,1,5,1};
        System.out.println( new Lc_0134_canCompleteCircuit().canCompleteCircuit(ints, ints2));
    }

}
