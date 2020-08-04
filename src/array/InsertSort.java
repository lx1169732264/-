package array;

import java.util.Arrays;

/**
 * 插入排序 n^2
 * 前面n-1(其中n>=2)个数已经是排好顺序的，将第n个数插到合适位置，使得插入后序列有序
 *
 * @author boss
 */
public class InsertSort {
    public static void insertSort(int[] arr) {
        int i, j, temp;
        for (i = 0; i < arr.length; i++) {
            //假定arr[i]比前面小,将arr[i]与前面相比较,,temp=arr[i],即为本轮要排序的数
            temp = arr[i];
            for (j = i; j > 0 && temp < arr[j - 1]; j--) {
                //将较大的数arr[j - 1]后移
                arr[j] = arr[j - 1];
            }
            //结束for循环后,arr[j]左边所有数比它小,右边数比它大(到达合适位置),将temp放入
            arr[j] = temp;
        }
    }

    public static void main(String[] args) {
        int[] arr = new int[]{3, 4, 6, 7, 2, 7, 2, 8, 0, 9, 1};
        insertSort(arr);
        System.out.println(Arrays.toString(arr));
    }
}
