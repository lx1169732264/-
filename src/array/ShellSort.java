package array;

import java.util.Arrays;

/**
 * 希尔排序     是对插入排序的优化,也称缩小增量排序
 * 1.选定增长量h,按照增长量作为分组的依据
 * 2.对分好组的每一组数据完成插入排序
 * 3.减少增长量,最小减为1,重复步骤2
 * <p>
 * 对于4, 5, 8, 7, 9, 2, 1, 3, 1, 4, 6 选定增长量h=5
 * 则6与2进行比较,不用动位置,随后2与4比较,交换位置
 * 当最后一组排序,h=1,此时已经基本实现了数组的有序
 */
public class ShellSort {
    public static void sort(int[] arr) {
        //计算增长量h
        int n = arr.length, h = 1;
        while (h < n / 2) {
            h = 2 * h + 1;
        }
        //开始排序,直到h<1,停止
        while (h >= 1) {
            //寻找待插入的元素arr[i]
            for (int i = h; i < n; i++) {
                //将arr[i]与arr[i-h]进行比较,如果进行了交换,arr[i]将再次与arr[i-2h],[i-3h]...进行比较
                for (int j = i; j >= h; j = j - h) {
                    //arr[j]就是待插入元素,与arr[j-h],[j-2h]....进行比较
                    if (greater(arr[j - h], arr[j])) {
                        exch(arr, j, j - h);
                    } else {
                        break;
                    }
                }
            }
            h = h / 2;
        }
    }

    public static void main(String[] args) {
        int[] a1 = {4, 5, 8, 7, 9, 2, 1, 3, 1, 4, 6, 7, 4, 23, 5, 34, 65, 231, 54, 77, 84, 23};
        sort(a1);
        System.out.println(Arrays.toString(a1));
    }

    /**
     * 判断v是否大于w
     */
    private static boolean greater(int v, int w) {
        return v > w;
    }

    /**
     * 交换数组a的i和j下标位置的值
     */
    private static void exch(int[] a, int i, int j) {
        int temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }
}