package Lc_offer;

/**
 * 把一个数组最开始的若干个元素搬到数组的末尾，我们称之为数组的旋转。
 * 输入一个递增排序的数组的一个旋转，输出旋转数组的最小元素。
 * <p>
 * 示例 1：
 * <p>
 * 输入：[3,4,5,1,2]
 * 输出：1
 * 示例 2：
 * <p>
 * 输入：[2,2,2,0,1]
 * 输出：0
 *
 * @author boss
 */
public class Lc_offer_11 {
    public int findMin(int[] nums) {
        int left = 0, right = nums.length - 1;
        while (right > left) {
            int mid = (left + right) / 2;
            //右节点大于中间值->旋转点在中间值与左节点之间
            if (nums[mid] < nums[right]) {
                right = mid;
                //右节点小于中间值->旋转点在中间值与右节点之间
            } else if (nums[mid] > nums[right]) {
                left = mid + 1;
                //右节点=中间值->原数组的后半部分相等,导致右节点与中间节点相等,不断左移右节点
            } else {
                right--;
            }
        }
        return nums[right];
    }
}
