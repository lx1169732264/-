package lc_0700;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ListIterator;

/**
 * 分割数组为连续子序列
 * 给你一个按升序排序的整数数组 num（可能重复），请你将它们分割成一个或多个子序列，其中每个子序列连续有序且长度>= 3
 * 如果可以完成上述分割，则返回 true ；否则，返回 false 。
 * <p>
 * 示例 1：
 * 输入: [1,2,3,3,4,5]
 * 输出: True
 * 解释:
 * 你可以分割出这样两个连续子序列 :
 * 1, 2, 3
 * 3, 4, 5
 * <p>
 * 示例 2：
 * 输入: [1,2,3,3,4,4,5,5]
 * 输出: True
 * 解释:
 * 你可以分割出这样两个连续子序列 :
 * 1, 2, 3, 4, 5
 * 3, 4, 5
 * <p>
 * 示例 3：
 * 输入: [1,2,3,4,4,5]
 * 输出: False
 * <p>
 * 提示：
 * 输入的数组长度范围为 [1, 10000]
 *
 * @author lx
 */
public class Lc_0659_isPossible {
    /**
     * 关注最小的数从哪个索引开始和结束，其次是第二小的数、第三小的数。。。直到最大的数
     * 同样对于子序列，我们只关注他的长度和它是否可以添加新数字,数组中的数字优先加在长度较小的子序列末尾

     * eg 1：
     * 1   2   3   4
     *      2   3   4   5   6
     *              4   5   6   7   8
     *                              8   9   10
     * 虽然也可以这样排列：
     * eg 2：
     * 1   2   3   4
     *      2   3   4   5   6   7   8
     *              4   5   6
     *                              8   9   10
     * 但任何一个符合题目规则的数列，虽然可以像eg2这样组合，但一定可以写成eg1的形式
     * 因为如eg2，第三行截止到 6 时已经满足长度为3，则它上面的子序列截止到 6 时的长度一定大于3
     * 如果第3行合规，则上面的子序列一定合规，接下来的7，8可以优先加在最低行的末尾。也可以理解为优先加在较短序列的末尾
     * 若数组中某个数（n）与它前面的数的差大于1，则可以使用递归：求解原数组相当于求解这个数组从n往后的子数组
     * <p>
     * 如下面这个例子eg3，解决[1,2,2,3,3,4,6,7,8,9]可递归解决[6,7,8,9]
     * eg 3
     * 1   2   3
     *      2   3   4
     *                  6   7   8   9
     * 对数组中每个数（记为nums[i]）遍历，根据nums[i]的值维护一个记录子序列长度的列表rec。
     * 而recHead、recTail和recIndex是rec上的三个索引
     * recHead与recIndex之间的子序列以nums[i]-1结尾（等待添加一个nums[i]）
     * recIndex与recTail之间以nums[i]结尾（已将添加过了）
     * <p>
     * recHead与recTail之间的序列是我们需要研究的
     * recIndex也只能在这两个端点之间移动。recIndex初始位于研究区间末尾（recTail-1），然后rec[recIndex]++后向前移动一位，超过recHead后再返回区间末尾。
     */
    public boolean isPossible(int[] nums) {
        if (nums.length <= 2) {
            return false;
        }
        int[] rec = new int[nums.length / 3 + 1];
        int recHead = 0, recTail = 1;
        int recIndex = 0;
        rec[0] = 1;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] == nums[i - 1]) {
                if (recIndex != recTail - 1) {
                    rec[recIndex--]++;
                    if (recIndex < recHead) {
                        recIndex = recTail - 1;
                    }
                } else if (recTail >= rec.length) {
                    return false;
                } else {
                    rec[recTail] = 1;
                    recIndex = (++recTail) - 1;
                }
            } else if (nums[i] != nums[i - 1] + 1) {
                for (int j = recHead; j < recTail; j++) {
                    if (rec[j] < 3) {
                        return false;
                    }
                }
                return isPossible(Arrays.copyOfRange(nums, i, nums.length));
            } else if (recIndex == recTail - 1) {
                rec[recIndex--]++;
                if (recIndex < recHead) {
                    recIndex = recTail - 1;
                }
            } else {
                while (recHead != recIndex + 1) {
                    if (rec[recHead] < 3) {
                        return false;
                    }
                    recHead++;
                }
                recIndex = recTail - 1;
                rec[recIndex--]++;
                if (recIndex < recHead) {
                    recIndex = recTail - 1;
                }
            }
        }
        for (int j = recTail - 1; j >= recHead; j--) {
            if (rec[j] < 3) {
                return false;
            }
        }
        return true;
    }
}