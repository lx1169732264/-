package lc_0100;

/**
 * 给定一个非负整数数组，你最初位于数组的第一个位置。
 * 数组中的每个元素代表你在该位置可以跳跃的最大长度。
 * 判断你是否能够到达最后一个位置。
 * <p>
 * 示例1:
 * 输入: [2,3,1,1,4]
 * 输出: true
 * 解释: 我们可以先跳 1 步，从位置 0 到达 位置 1, 然后再从位置 1 跳 3 步到达最后一个位置。
 *
 * 示例2:
 * 输入: [3,2,1,0,4]
 * 输出: false
 * 解释: 无论怎样，你总会到达索引为 3 的位置。但该位置的最大跳跃长度是 0 ， 所以你永远不可能到达最后一个位置。
 */
public class Lc_0055_canJump {
    //方法1   贪心算法
    public boolean canJump(int[] nums) {
        //动态维护可到达的最远距离max
        int max = 0, n = nums.length;
        for (int i = 0; i < n; ++i) {
            //当下标i<max,即该距离能够到达时,动态计算最远距离
            if (i <= max) {
                max = Math.max(i + nums[i], max);
                //最远距离大于数组长度,可以到达
                if (max >= n - 1) {
                    return true;
                }
            }
        }
        return false;
    }
}
