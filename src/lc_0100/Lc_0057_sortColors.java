package lc_0100;

/**
 * 给定一个包含红色、白色和蓝色，一共 n 个元素的数组，原地对它们进行排序，使得相同颜色的元素相邻，并按照红色、白色、蓝色顺序排列。
 * 此题中，我们使用整数 0、 1 和 2 分别表示红色、白色和蓝色。
 * <p>
 * 进阶：
 * 你可以不使用代码库中的排序函数来解决这道题吗？
 * 你能想出一个仅使用常数空间的一趟扫描算法吗？
 * <p>
 * 示例 1：
 * 输入：nums = [2,0,2,1,1,0]
 * 输出：[0,0,1,1,2,2]
 * <p>
 * 示例 2：
 * 输入：nums = [2,0,1]
 * 输出：[0,1,2]
 * <p>
 * 示例 3：
 * 输入：nums = [0]
 * 输出：[0]
 * <p>
 * 示例 4：
 * 输入：nums = [1]
 * 输出：[1]
 * <p>
 * 提示：
 * n == nums.length
 * 1 <= n <= 300
 * nums[i] 为 0、1 或 2
 *
 * @author lx
 */
public class Lc_0057_sortColors {

    public void sortColors(int[] nums) {
        //双指针记录0/2应当存放的位置   index为当前节点下标
        int left = 0, right = nums.length - 1, index = 0;

        while (index <= right) {
            //所有的0被替换到左边    存在index和left重叠的情况,跳过
            if (0 == nums[index] && index != left) {
                nums = swap(nums, index, left);
                left++;

                //所有的2被替换到右边
            } else if (2 == nums[index]) {
                nums = swap(nums, index, right);
                right--;

                //遇到1,不作处理
            } else {
                index++;
            }
        }
    }

    private int[] swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
        return nums;
    }
}
