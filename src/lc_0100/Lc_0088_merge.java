package lc_0100;

/**
 * 合并2个有序数组
 * 给你两个有序整数数组 nums1 和 nums2，请你将 nums2 合并到 nums1 中，使 num1 成为一个有序数组。
 * 初始化 nums1 和 nums2 的元素数量分别为 m 和 n 。
 * 你可以假设 nums1 有足够的空间（空间大小大于或等于 m + n）来保存 nums2 中的元素。
 * <p>
 * 示例:
 * 输入:
 * nums1 = [1,2,3,0,0,0], m = 3
 * nums2 = [2,5,6],       n = 3
 * 输出: [1,2,2,3,5,6]
 */
public class Lc_0088_merge {
    public void merge2(int[] nums1, int m, int[] nums2, int n) {
        int i = nums1.length - 1;
        while (n > 0) {
            if (m == 0) {
                for (int n1 = n; n1 > 0; n1--) {
                    nums1[i] = nums1[n - 1];
                    n--;
                }
            } else if (nums1[m - 1] >= nums2[n - 1]) {
                nums1[i] = nums1[m - 1];
                nums1[m - 1] = 0;
                m--;
            } else {
                nums1[i] = nums2[n - 1];
                n--;
            }
            i--;
        }
    }
}
