package lc_0100;

/**
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
    /**
     * 这题只求合并,不需要去重
     *
     * @param nums1
     * @param m
     * @param nums2
     * @param n
     */
    public static void merge(int[] nums1, int m, int[] nums2, int n) {
        int i = 0, j = 0, temp = 0, change = 0;
        while (i <= (m + n - 1) && j <= (n - 1)) {
            if ((m - i) == (n - j)) {
                nums1[i] = nums2[j];
                j++;
            } else if (temp != 0) {
                if (nums2[j] > temp) {
                    change = temp;
                    temp = nums2[j];
                    nums2[j] = change;
                }
                if (nums1[i] > nums2[j]) {
                    temp = nums1[i];
                    nums1[i] = nums2[j];
                }
            } else if (nums1[i] > nums2[j]) {
                temp = nums1[i];
                nums1[i] = nums2[j];
                j++;
            }
            i++;
        }
    }

    public static void main(String[] args) {
        int[] nums1 = new int[]{4, 5, 6, 0, 0, 0};
        int[] nums2 = new int[]{1, 2, 3};
        merge(nums1, 3, nums2, 3);
        System.out.println(nums1);
    }
}
