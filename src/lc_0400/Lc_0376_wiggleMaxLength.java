package lc_0400;

import org.junit.Assert;
import org.junit.Test;

/**
 * 如果连续数字之间的差严格地在正数和负数之间交替，则数字序列称为摆动序列。第一个差（如果存在的话）可能是正数或负数。少于两个元素的序列也是摆动序列。
 * 例如， [1,7,4,9,2,5] 是一个摆动序列，因为差值 (6,-3,5,-7,3) 是正负交替出现的。
 * 相反, [1,4,7,2,5] 和 [1,7,4,5,5] 不是摆动序列，第一个序列是因为它的前两个差值都是正数，第二个序列是因为它的最后一个差值为零。
 * 给定一个整数序列，返回作为摆动序列的最长子序列的长度。 通过从原始序列中删除一些（也可以不删除）元素来获得子序列，剩下的元素保持其原始顺序。
 * <p>
 * 示例 1:
 * 输入: [1,7,4,9,2,5]
 * 输出: 6
 * 解释: 整个序列均为摆动序列
 * <p>
 * 示例 2:
 * 输入: [1,17,5,10,13,15,10,5,16,8]
 * 输出: 7
 * 解释: 这个序列包含几个长度为 7 摆动序列，其中一个可为[1,17,10,13,10,16,8]
 * <p>
 * 示例 3:
 * 输入: [1,2,3,4,5,6,7,8,9]
 * 输出: 2
 * <p>
 * 进阶:
 * 你能否用 O(n) 时间复杂度完成此题?
 */
public class Lc_0376_wiggleMaxLength {
    public int wiggleMaxLength(int[] nums) {
        if (2 > nums.length) {
            return nums.length;
        }

        //如果前两个元素相等,初始值1    否则初始值2
        int count = nums[1] == nums[0] ? 1 : 2;

        //前面的区间是升序:>0  降序<0 相等=0
        int pre = nums[1] - nums[0];

        for (int i = 2; i < nums.length; i++) {
            int temp = nums[i] - nums[i - 1];
            //当出现 升变降/降变升 时,才进行count++
            if (pre >= 0 && temp < 0) {
                count++;
            } else if (pre <= 0 && temp > 0) {
                count++;
            }

            //对于0,直接跳过(删除相等元素)  保留上个区间的pre(延长区间)
            if (temp != 0) {
                pre = temp;
            }
        }
        return count;
    }

    @Test
    public void test() {
        int[] test1 = new int[]{1, 7, 4, 9, 2, 5};
        int[] test2 = new int[]{0, 0};
        int[] test3 = new int[]{0, 0, 0};
        int[] test4 = new int[]{3, 3, 3, 2, 5};
        int res1 = 6;
        int res2 = 1;
        int res3 = 1;
        int res4 = 3;
        Assert.assertEquals(res1, wiggleMaxLength(test1));
        Assert.assertEquals(res2, wiggleMaxLength(test2));
        Assert.assertEquals(res3, wiggleMaxLength(test3));
        Assert.assertEquals(res4, wiggleMaxLength(test4));
    }
}
