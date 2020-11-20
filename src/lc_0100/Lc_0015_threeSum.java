package lc_0100;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 给你一个包含 n 个整数的数组 nums，判断 nums 中是否存在三个元素 a，b，c ，使得 a + b + c = 0 ？请你找出所有满足条件且不重复的三元组。
 * 注意：答案中不可以包含重复的三元组。
 * <p>
 * 示例：
 * 给定数组 nums = [-1, 0, 1, 2, -1, -4]，
 * 满足要求的三元组集合为：
 * [[-1, 0, 1],
 * [-1, -1, 2]]
 *
 * @author lx
 */
public class Lc_0015_threeSum {

    /**
     * 三数之和视为nums1,nums2,nums3三层之和
     * 为了确保不重复,需要先将nums进行排序,并让a<=b<=c
     * 对于同一层nums,在取下一个元素时,不能与上一个相同 ,例如-5,2,2,3,上一次用了2,组成[-5,2,3] ,下一次再取第二个2将导致重复
     * <p>
     * 三次循环是基本的框架 ,然而在b->b`时,b增大,从而c->c`需要变小
     * 从而可以简化nums2和nums3的循环次数
     */
    public List<List<Integer>> threeSum(int[] nums) {
        int n = nums.length;
        Arrays.sort(nums);
        List<List<Integer>> ans = new ArrayList<>();

        for (int a = 0; a < n; ++a) {
            // 需要和上一次枚举的数不相同
            if (a > 0 && nums[a] == nums[a - 1]) {
                continue;
            }
            // c初始指向数组的最右端
            int c = n - 1;

            //目标是得到-a=b+c
            int target = -nums[a];
            for (int b = a + 1; b < n; ++b) {
                // 需要和上一次枚举的数不相同
                if (b > a + 1 && nums[b] == nums[b - 1]) {
                    continue;
                }
                // b< c
                while (b < c && nums[b] + nums[c] > target) {
                    --c;
                }
                // 指针重合，退出循环
                if (b == c) {
                    break;
                }
                if (nums[b] + nums[c] == target) {
                    List<Integer> list = new ArrayList<>();
                    list.add(nums[a]);
                    list.add(nums[b]);
                    list.add(nums[c]);
                    ans.add(list);
                }
            }
        }
        return ans;
    }
}
