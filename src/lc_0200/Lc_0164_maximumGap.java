package lc_0200;

/**
 * 最大间距
 * 给定一个无序的数组，找出数组在排序之后，相邻元素之间最大的差值。
 * 如果数组元素个数小于 2，则返回 0。
 * <p>
 * 示例 1:
 * 输入: [3,6,9,1]
 * 输出: 3
 * 解释: 排序后的数组是 [1,3,6,9], 其中相邻元素 (3,6) 和 (6,9) 之间都存在最大差值 3
 * <p>
 * 示例 2:
 * 输入: [10]
 * 输出: 0
 * 解释: 数组元素个数小于 2，因此返回 0
 * <p>
 * 说明:
 * 你可以假设数组中所有元素都是非负整数，且数值在 32 位有符号整数范围内。
 * 请尝试在线性时间复杂度和空间复杂度的条件下解决此问题。
 *
 * @author lx
 */
public class Lc_0164_maximumGap {
    public int maximumGap(int[] nums) {
        int res = 0, division = 1, digits = 0;
        int[][] temp = new int[10][nums.length];
        int[] count = new int[10];

        for (int num : nums) {
            digits = Math.max((int) (Math.log10(num) + 1), digits);
        }

        while (division <= Math.pow(10, digits)) {
            for (int num : nums) {
                int i = (num / division) % 10;
                temp[i][count[i]] = num;
                count[i]++;
            }

            int index = 0;
            for (int i = 0; i < 10; i++) {
                for (int j = 0; j < count[i]; j++) {
                    nums[index] = temp[i][j];
                    index++;
                }
                count[i] = 0;
            }
            division *= 10;
        }

        for (int i = 0; i < nums.length - 1; i++) {
            res = Math.max(nums[i + 1] - nums[i], res);
        }
        return res;
    }
}
