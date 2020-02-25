package lc;

/**
 * 给定一个整数数组 nums ，找到一个具有最大和的连续子数组（子数组最少包含一个元素），
 * 返回其最大和。
 * <p>
 * 示例:
 * 输入: [-2,1,-3,4,-1,2,1,-5,4],
 * 输出: 6
 * 解释: 连续子数组 [4,-1,2,1] 的和最大，为 6。
 */
public class Lc_0053_maxSubArray {
    //方法1   动态规划法
    public int maxSubArray(int[] nums) {
        int n = nums.length, maxSum = nums[0];
        for (int i = 1; i < n; ++i) {
            if (nums[i - 1] > 0) {
                //nums[i]   当前子数组最大值
                nums[i] += nums[i - 1];
                //maxSum    历史最大值
                maxSum = Math.max(nums[i], maxSum);
            }
        }
        return maxSum;
    }
}
