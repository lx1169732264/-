package lc_0100;

import java.util.ArrayList;
import java.util.List;

/**
 * 给你一个包含 n 个整数的数组 nums，判断 nums 中是否存在三个元素 a，b，c ，使得 a + b + c = 0 ？请你找出所有满足条件且不重复的三元组。
 * 注意：答案中不可以包含重复的三元组。
 * <p>
 * 示例：
 * 给定数组 nums = [-1, 0, 1, 2, -1, -4]，
 * 满足要求的三元组集合为：
 * [
 * [-1, 0, 1],
 * [-1, -1, 2]
 * ]

public class Lc_0015_threeSum {
    public List<List<Integer>> threeSum(int[] nums) {
        //负数
        List<Integer> negatives = new ArrayList<>();
        //正数
        List<Integer> positives = new ArrayList<>();
        for (int num : nums) {
            if (num >= 0) {
                positives.add(num);
            } else {
                negatives.add(num);
            }
        }

        //1.负数两个,正数一个
        for (int i = 0; i < positives.size(); i++) {
            for (int j = 1; j < positives.size(); j++) {
                int sum = positives.get(i)+positives.get(j);
                
            }
        }


    }

        }*/