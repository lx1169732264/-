package lc_0400;

import algorithm.QuickSort;

import java.util.*;

/**
 * 给定两个数组，编写一个函数来计算它们的交集。
 * <p>
 * 示例 1：
 * 输入：nums1 = [1,2,2,1], nums2 = [2,2]
 * 输出：[2]
 * <p>
 * 输出结果中的每个元素一定是唯一的。我们可以不考虑输出结果的顺序。
 *
 * @author lx
 */
public class Lc_0349_intersection {
    public int[] intersection(int[] nums1, int[] nums2) {
        if (nums1.length > nums2.length) {
            intersection(nums2, nums1);
        }
        quickSort(nums1, 0, nums1.length - 1);
        quickSort(nums2, 0, nums2.length - 1);

        int i = 0, j = 0;
        Set<Integer> set=new HashSet<>();
        while (i < nums1.length && j < nums2.length) {
            if (nums1[i] == nums2[j] ) {
                set.add(nums1[i]);
                i++;
                j++;
            } else if (nums1[i] < nums2[j]) {
                i++;
            } else {
                j++;
            }
        }
        return set.stream().mapToInt(Integer::valueOf).toArray();
    }

    public void quickSort(int[] arr, int start, int end) {
        if (start < end) {
            //数组中第0个数为标准数
            int stard = arr[start], low = start, high = end;
            //循环找比标准数大的数和比标准数小的数
            while (low < high) {
                //高位>低位 && 高位>标准数	不用动,只移下标
                while (low < high && stard <= arr[high]) {
                    high--;
                }
                //小于标准数的被移到左边
                arr[low] = arr[high];
                //低位<标准数	不用动,只移下标
                while (low < high && arr[low] <= stard) {
                    low++;
                }
                //大于标准数的移动到右边
                arr[high] = arr[low];
            }
            //第一轮排序结束,高低位下标重叠,把标准数赋给当前下标
            arr[low] = stard;
            //处理所有的小的数字
            quickSort(arr, start, low);
            //处理所有的大的数字
            quickSort(arr, low + 1, end);
        }
    }
}