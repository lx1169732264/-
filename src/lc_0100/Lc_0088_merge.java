package lc_0100;

/**
 * 合并2个有序数组
 * 给你两个有序整数数组nums1 和 nums2，请你将 nums2 合并到nums1中，使 num1 成为一个有序数组。
 * 初始化nums1 和 nums2 的元素数量分别为m 和 n 。
 * 你可以假设nums1有足够的空间（空间大小大于或等于m + n）来保存 nums2 中的元素。
 * <p>
 * 示例:
 * 输入:
 * nums1 = [1,2,3,0,0,0], m = 3
 * nums2 = [2,5,6],       n = 3
 * 输出:[1,2,2,3,5,6]
 */
public class Lc_0088_merge {
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int p1 = m - 1, p2 = n - 1;
        int tail = m + n - 1;
        int cur;
        while (p1 >= 0 || p2 >= 0) {
            if (p1 == -1) {
                cur = nums2[p2--];
            } else if (p2 == -1) {
                cur = nums1[p1--];
            } else if (nums1[p1] > nums2[p2]) {
                cur = nums1[p1--];
            } else {
                cur = nums2[p2--];
            }
            nums1[tail--] = cur;
        }
    }
}
