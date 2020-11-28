package algorithm.sort;

/**
 * 归并排序
 *
 * @Author: 杨德石
 * @Date: 2020/6/13 23:24
 * @Version 1.0
 */
public class Merge {

    /**
     * 辅助数组
     */
    private static int[] assets;

    /**
     * 对数组内的元素进行排序
     *
     * @param a
     */
    public static void sort(int[] a) {
        // 初始化辅助数组
        assets = new int[a.length];
        // 获取lo和hi
        int lo = 0;
        int hi = a.length - 1;
        // 进入排序流程
        sort(a, lo, hi);

    }

    /**
     * 对数组a中从索引lo到索引hi之间的元素进行排序
     * 假设lo是6 hi是11
     * 9
     *
     * @param a
     * @param lo
     * @param hi
     */
    private static void sort(int[] a, int lo, int hi) {
        if (hi <= lo) {
            return;
        }
        // 计算mid
        int mid = lo + (hi - lo) / 2;
        // 对lo和mid之间的数据进行排序
        sort(a, lo, mid);
        // 对mid+1到hi之间的数据进行排序
        sort(a, mid + 1, hi);
        // 从lo到mid这组数据和mid+1到hi这组数据进行归并
        merge(a, lo, mid, hi);
    }

    /**
     * 从索引lo到索引mid为一个子组
     * 从索引mid+1到hi为另一个子组，把数组a中的这两个子组数据合并成一个有序的大组
     *
     * @param a
     * @param lo
     * @param mid
     * @param hi
     */
    private static void merge(int[] a, int lo, int mid, int hi) {
        // 从lo到mid这组数据和mid+1到hi这组数据归并到assets对应的所引出
        int i = lo;
        int p1 = lo;
        int p2 = mid + 1;
        // 比较左边小组和右边小组中的元素大小，哪个小，就把哪个数组填充到assets中
        while (p1 <= mid && p2 <= hi) {
            if (less(a[p1], a[p2])) {
                assets[i++] = a[p1++];
            } else {
                assets[i++] = a[p2++];
            }
        }
        // 上面的循环结束后，如果退出循环的条件是p1<=mid，则说明左边小组中的数据已经归并完毕
        // 如果退出循环的条件是p2<=hi，则说明右边小组中的数据已经填充完毕.
        while (p1 <= mid) {
            // 说明左子组没有排序完
            assets[i++] = a[p1++];
        }

        while (p2 <= hi) {
            // 说明右子组没有排序完
            assets[i++] = a[p2++];
        }
        // 到现在为止，assets中的数据，从lo到hi是有序的
        for (int j = lo; j <= hi; j++) {
            a[j] = assets[j];
        }
    }

    /**
     * 判断v是否小于w
     *
     * @param v
     * @param w
     * @return
     */
    private static boolean less(int v, int w) {
        return v < w;
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

class Test5 {
//    public static void main(String[] args) {
//        int[] a = {8, 4, 5, 7, 1, 3, 6, 2};
//        Merge.algorithm.sort(a);
//        System.out.println(Arrays.toString(a));
//    }

    public static void main(String[] args) {
        int[] a1 = new int[100000];
        int[] a2 = new int[100000];
        for (int i = 0; i < 100000; i++) {
            // 生成随机数，1-10000
            int num = (int) Math.random() * 100000 + 1;
            a1[i] = num;
            a2[i] = num;
        }

        long start = System.nanoTime();
        Merge.sort(a1);
        long end = System.nanoTime();
        System.out.println(end - start);

        start = System.nanoTime();
        Shell.sort(a2);
        end = System.nanoTime();
        System.out.println(end - start);
    }
}
