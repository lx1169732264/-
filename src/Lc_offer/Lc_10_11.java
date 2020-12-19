package Lc_offer;

/**
 * 在一个整数数组中，“峰”是大于或等于相邻整数的元素，相应地，“谷”是小于或等于相邻整数的元素。例如，在数组{5, 8, 4, 2, 3, 4, 6}中，{8, 6}是峰， {5, 2}是谷。现在给定一个整数数组，将该数组按峰与谷的交替顺序排序。
 * <p>
 * 示例:
 * 输入: [5, 3, 1, 2, 3]
 * 输出: [5, 1, 3, 2, 3]
 * 提示：
 * <p>
 * nums.length <= 10000
 */
public class Lc_10_11 {

    public void wiggleSort(int[] nums) {
        for (int i = 1; i < nums.length; i++) {
            //判断奇偶时，取余%速度慢。位运算快。i&1==1即奇数，i&1==0即偶数
            if (((i & 1) == 0 && nums[i] < nums[i - 1]) || ((i & 1) == 1 && nums[i] > nums[i - 1])) {
                int temp = nums[i];
                nums[i] = nums[i - 1];
                nums[i - 1] = temp;
            }
        }
    }
}
