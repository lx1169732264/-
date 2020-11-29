package algorithm.sort;

import java.util.Arrays;

/**
 * 希尔排序
 *
 * @Author: 杨德石
 * @Date: 2020/6/11 23:17
 * @Version 1.0
 */
public class Shell {
    public static void sort(int[] arr) {
        // 计算h
        int n = arr.length;
        int h = 1;
        while (h < n / 2) {
            h = 2 * h + 1;
        }
        // 开始排序，只要h小于1，就停止排序
        while (h >= 1) {
            // 找到待插入的元素
            for (int i = h; i < n; i++) {
                // 在这里，arr[i]就是待插入的元素
                // arr[i]分别与arr[i-h]、arr[i-2h]、arr[i-3h]....比较
                for (int j = i; j >= h; j = j - h) {
                    // arr[j]就是带插入的元素
                    // 分别于分别与arr[j-h]、arr[j-2h]、arr[j-3h]....比较
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

    /**
     * 判断v是否大于w
     *
     * @param v
     * @param w
     * @return
     */
    private static boolean greater(int v, int w) {
        return v > w;
    }

    /**
     * 交换数组a的i和j下标位置的值
     *
     * @param a
     * @param i
     * @param j
     */
    private static void exch(int[] a, int i, int j) {
        int temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }

}

class Test4 {
    public static void main(String[] args) {
        int[] arr = {9, 1, 2, 5, 7, 4, 8, 6, 3, 5};
        Shell.sort(arr);
        System.out.println(Arrays.toString(arr));
    }
}

