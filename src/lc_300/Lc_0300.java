package lc_300;

/**
 * 给定一个无序的整数数组，找到其中最长上升子序列的长度。
 * <p>
 * 示例:
 * <p>
 * 输入: [10,9,2,5,3,7,101,18]
 * 输出: 4
 * 解释: 最长的上升子序列是 [2,3,7,101]，它的长度是 4。
 */
public class Lc_0300 {
    public static int lengthOfLIS(int[] nums) {
        int max = 0, LIS = 1;
        for (int i = 1; i <= (nums.length - 1); i++) {
            if (nums[i - 1] < nums[i]) {
                ++LIS;
            } else {
                max = Math.max(max, LIS);
                LIS = 1;
            }
        }
        return max;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{10, 9, 2, 5, 3, 7, 101, 18};
        System.out.println(lengthOfLIS(nums));
    }
}
