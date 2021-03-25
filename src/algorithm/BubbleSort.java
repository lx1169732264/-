package algorithm;

import java.util.Arrays;

/**
 * 冒泡排序
 * 比较相邻的元素。如果第一个比第二个大，就交换他们两个   -->第一次遍历后,最后一个数就是最大数
 * 对每一对相邻元素做同样的工作，从开始第一对到结尾的最后一对
 * 针对所有的元素重复以上的步骤，除了最后一个
 * 持续每次对越来越少的元素重复上面的步骤，直到没有任何一对数字需要比较
 * <p>
 * V2.0 用flag判断当前是否有序,可以少遍历几次
 * V3.0 每次遍历记录下"最后一次交换"的位置,则后面的元素都没被交换,也就是它们都是有序的
 *
 * @author lx
 */
public class BubbleSort {
    public static void main(String[] args) {
        int[] arr = new int[]{5, 7, 2, 9, 4, 1, 0, 5, 7};
        System.out.println("排序前" + Arrays.toString(arr));
        bubbleSort3(arr);
        System.out.println("排序后" + Arrays.toString(arr));
    }

    /**
     * 冒泡排序3.0  每次记录最后一次交换的位置
     *
     * @param arr
     */
    public static void bubbleSort3(int[] arr) {
        for (int end = arr.length - 1; end > 0; end--) {
            /**index最后会赋值给end,作为下次循环的终点
             * 如果给index初始值end,在已有序的情况下,不会进入第二个for循环
             * 将导致end直接被赋值给end,即end未变化,下一次循环依然以end结束,冒泡并未得到优化
             *
             * index的目标是在有序时,在第一个for循环中end--,end>0不成立,退出循环,实现优化
             * */
            int index = 1;
            for (int begin = 1; begin <= end; begin++) {
                if (arr[end] < arr[begin - 1]) {
                    int temp = arr[end];
                    arr[end] = arr[begin - 1];
                    arr[begin - 1] = temp;
                    index = end;
                }
            }
            end = index;
        }
    }

    public static void bubbleSort2(int[] arr) {
        Boolean flag = true;
        //控制共比较多少轮
        for (int i = 0; i < arr.length - 1; i++) {
            //控制比较的次数
            for (int j = 0; j < arr.length - 1 - i; j++) {
                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                    flag = false;
                }
            }
            if (flag) {
                break;
            }
        }
    }
}
