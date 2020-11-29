package algorithm;

/**
 * 基数排序
 * 将所有待比较数值（正整数）统一为同样的数位长度，数位较短的数前面补零
 * 然后，从最低位开始，依次进行一次排序。这样从最低位排序一直到最高位排序完成以后, 数列就变成一个有序序列
 * 基数排序的方式可以采用LSD（Least significant digital）或MSD（Most significant digital）
 * LSD的排序方式由键值的最右边开始，而MSD则相反，由键值的最左边开始
 *
 * @author lx
 */
public class RadixSort {

    //d表示最大的数有多少位
    public static void sort(int[] number, int d) {
        int k = 0;
        int n = 1;
        //控制排序依据在哪一位(个十百千)
        int m = 1;
        //数组的第一维表示余数0-9
        int[][] temp = new int[10][number.length];
        //orderp[i] 表示该位是i的数的个数
        int[] order = new int[10];
        while (m <= d) {
            for (int value : number) {
                int lsd = ((value / n) % 10);
                temp[lsd][order[lsd]] = value;
                order[lsd]++;
            }

            for (int i = 0; i < 10; i++) {
                if (order[i] != 0) {
                    for (int j = 0; j < order[i]; j++) {
                        number[k] = temp[i][j];
                        k++;
                    }
                }
                order[i] = 0;
            }
            n *= 10;
            k = 0;
            m++;
        }
    }

    public static void main(String[] args) {
        int[] data = {73, 22, 93, 43, 55, 14, 28, 65, 39, 81, 33, 100};
        RadixSort.sort(data, 3);
        for (int datum : data) {
            System.out.print(datum + "");
        }
    }
}