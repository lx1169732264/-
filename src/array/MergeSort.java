package array;

/**
 * 归并排序
 */
public class MergeSort {

    public static void sort(){

    }

    /**
     * 对数组内low-high之间的元素进行排序
     */
    private static void sort(int[] a,int low,int high){

    }

    /**
     * low-mid为一个子组,mid-high为另一个子组,将这两个子组合并成大组
     */
    private static void merge(int[] a,int low,int mid,int high){

    }

    /**
     * 判断v是否小于w
     */
    private static boolean less(int v,int w){
        return v<w;
    }

    private static void exch(int[] a, int i, int j) {
        int temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }
}
