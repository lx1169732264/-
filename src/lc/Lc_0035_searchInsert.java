package lc;

/**
 * 给定一个排序数组和一个目标值，在数组中找到目标值，并返回其索引。如果目标值不存在于数组中，返回它将会被按顺序插入的位置。
 * 你可以假设数组中无重复元素。
 * <p>
 * 示例 1:
 * 输入: [1,3,5,6], 5
 * 输出: 2
 */
public class Lc_0035_searchInsert {
    //方法1   循环  不推荐
    public int searchInsert(int[] nums, int target) {
        if (nums == null | target <= nums[0]) {
            return 0;
        }
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] >= target && nums[i - 1] < target) {
                return i;
            }
        }
        return nums.length;
    }

    //二分法查找
    public int searchInsert2(int[] nums, int target) {
        int left = 0, right = nums.length - 1;
        while (left <= right) {
            int i = (left + right) / 2;
            if (target == nums[i]) {
                return i;
            } else if (nums[i] < target) {
                left = i + 1;
            } else {
                right = i - 1;
            }
        }
        return left;
    }
}
