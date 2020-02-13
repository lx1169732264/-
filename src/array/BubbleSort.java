package array;

import java.util.Arrays;

/**
 * 冒泡排序
 * 需要比较length-1轮
 */
public class BubbleSort {
    public static void main(String[] args) {
        int[] arr = new int[]{5, 7, 2, 9, 4, 1, 0, 5, 7};
        bubbleSort(arr);
        System.out.println("排序前"+Arrays.toString(arr));
        System.out.println("排序后"+Arrays.toString(arr));
    }

    public static void bubbleSort(int[] arr) {
        //控制共比较多少轮
        for (int i = 0; i < arr.length - 1; i++) {
            //控制比较的次数
            for (int j = 0; j < arr.length - 1 - i; j++) {
                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
    }
}
