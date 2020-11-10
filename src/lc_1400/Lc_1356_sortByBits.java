package lc_1400;

import java.util.*;

/**
 * 给你一个整数数组 arr 。请你将数组中的元素按照其二进制表示中数字 1 的数目升序排序。
 * 如果存在多个数字二进制中 1 的数目相同，则必须将它们按照数值大小升序排列。
 * 请你返回排序后的数组。
 * <p>
 * 示例 1：
 * 输入：arr = [0,1,2,3,4,5,6,7,8]
 * 输出：[0,1,2,4,8,3,5,6,7]
 * 解释：[0] 是唯一一个有 0 个 1 的数。
 * [1,2,4,8] 都有 1 个 1 。
 * [3,5,6] 有 2 个 1 。
 * [7] 有 3 个 1 。
 * 按照 1 的个数排序得到的结果数组为 [0,1,2,4,8,3,5,6,7]
 * <p>
 * 示例 2：
 * 输入：arr = [1024,512,256,128,64,32,16,8,4,2,1]
 * 输出：[1,2,4,8,16,32,64,128,256,512,1024]
 * 解释：数组中所有整数二进制下都只有 1 个 1 ，所以你需要按照数值大小将它们排序。
 * <p>
 * 示例 3：
 * 输入：arr = [10000,10000]
 * 输出：[10000,10000]
 * <p>
 * 示例 4：
 * 输入：arr = [2,3,5,7,11,13,17,19]
 * 输出：[2,3,5,17,7,11,13,19]
 * <p>
 * 示例 5：
 * 输入：arr = [10,100,1000,10000]
 * 输出：[10,100,10000,1000]
 *
 * @author lx
 */
public class Lc_1356_sortByBits {

    public static int[] sortByBits(int[] arr) {
        List<Sort> list = new ArrayList<>();
        for (int a : arr) {
            int count = 0;
            int i = 0;
            while (true) {
                if (isOdd(a >> i)) {
                    count++;
                } else if (a >> i == 0) {
                    list.add(new Sort(count, a));
                    break;
                }
                i++;
            }
        }

        list.sort((s1, s2) -> {
            int flag;
            // 首选按1的个数升序排序
            flag = s1.getCount() - s2.getCount();
            if (flag == 0) {
                // 再按值进行升序排序
                flag = s1.getValue() - s2.getValue();
            }
            return flag;
        });

        int[] res = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            res[i] = list.get(i).getValue();
        }
        return res;
    }


    static class Sort {
        private Integer count;
        private Integer value;

        public Integer getCount() {
            return count;
        }

        public Integer getValue() {
            return value;
        }

        public Sort(Integer count, Integer value) {
            this.count = count;
            this.value = value;
        }
    }

    //奇数返回true
    public static boolean isOdd(int a) {
        return (a & 1) == 1;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{0, 1, 2, 3, 4, 5, 6, 7, 8};
        int[] ints = sortByBits(nums);
        for (int num : ints) {
            System.out.println(num);
        }
    }
}
