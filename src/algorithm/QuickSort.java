package algorithm;

/**
 * 快速排序(递归思路)
 * <p>
 * 设定标准数，将数组分成左右两部分
 * 将>=标准数的移动到右边，<标准数的到数组的左边
 * 然后，左边和右边的数据可以独立排序(递归)
 *
 * @author lx
 */
public class QuickSort {
    public static void quickSort(int[] arr, int start, int end) {
        if (start < end) {
            //第0个数为标准数
            int stard = arr[start], low = start, high = end;
            //循环找比标准数大的数和比标准数小的数
            while (low < high) {
                while (low < high && stard <= arr[high]) {
                    high--;
                }
                //小于标准数的被移到左边
                arr[low] = arr[high];

                while (low < high && arr[low] <= stard) {
                    low++;
                }
                //大于标准数的移动到右边
                arr[high] = arr[low];
            }
            //第一轮排序结束,高低位下标重叠,把标准数赋给当前下标
            arr[low] = stard;
            quickSort(arr, start, low);
            quickSort(arr, low + 1, end);
        }
    }
}