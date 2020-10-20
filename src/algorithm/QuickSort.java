package algorithm;

import java.util.Arrays;

/**
 * 快速排序(递归思路)
 * <p>
 * (1)首先设定一个分界值，通过该分界值将数组分成左右两部分。
 * (2)将大于或等于分界值的数据集中到数组右边，小于分界值的数据集中到数组的左边。此时，左边部分中各元素都小于或等于分界值，而右边部分中各元素都大于或等于分界值。
 * (3)然后，左边和右边的数据可以独立排序。对于左侧的数组数据，又可以取一个分界值，将该部分数据分成左右两部分，同样在左边放置较小值，右边放置较大值。右侧的数组数据也可以做类似处理。
 * (4)重复上述过程，可以看出，这是一个递归定义。通过递归将左侧部分排好序后，再递归排好右侧部分的顺序。当左、右两个部分各数据排序完成后，整个数组的排序也就完成了。
 *
 * @author boss
 */
public class QuickSort {

    public static void main(String[] args) {
        int[] arr = new int[]{3, 4, 6, 7, 2, 7, 2, 8, 0, 9, 1};
        quickSort(arr, 0, arr.length - 1);
        System.out.println(Arrays.toString(arr));
    }

    public static void quickSort(int[] arr, int start, int end) {
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
