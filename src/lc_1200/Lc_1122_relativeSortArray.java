package lc_1200;

import org.junit.Test;

import java.util.*;

/**
 * 给你两个数组，arr1 和 arr2，arr2 中的元素各不相同arr2 中的每个元素都出现在 arr1 中
 * 对 arr1 中的元素进行排序，使 arr1 中项的相对顺序和 arr2 中的相对顺序相同。
 * 未在 arr2 中出现过的元素需要按照升序放在 arr1 的末尾。
 * <p>
 * 示例：
 * 输入：arr1 = [2,3,1,3,2,4,6,7,9,2,19], arr2 = [2,1,4,3,9,6]
 * 输出：[2,2,2,1,4,3,3,9,6,7,19]
 * <p>
 * 提示：
 * arr1.length, arr2.length <= 1000
 * 0 <= arr1[i], arr2[i] <= 1000
 * arr2 中的元素 arr2[i] 各不相同
 * arr2 中的每个元素 arr2[i] 都出现在 arr1 中
 *
 * @author lx
 */
public class Lc_1122_relativeSortArray {
    public int[] relativeSortArray(int[] arr1, int[] arr2) {
        Map<Integer, Integer> map = new HashMap<>(arr2.length * 2);
        for (int i : arr1) {
            if (map.containsKey(i)) {
                map.put(i, map.get(i) + 1);
            } else {
                map.put(i, 1);
            }
        }

        int[] res = new int[arr1.length];
        int index = 0;
        for (int i : arr2) {
            for (Integer j = map.get(i); j > 0; j--) {
                res[index] = i;
                index++;
            }
            map.remove(i);
        }

        LinkedList<Integer> list = new LinkedList<>();
        map.forEach((i, j) -> {
            for (Integer z = map.get(i); z > 0; z--) {
                list.add(i);
            }
        });

        list.sort(Integer::compareTo);
        for (Integer i : list) {
            res[index] = i;
            index++;
        }
        return res;
    }

    public int[] relativeSortArray2(int[] arr1, int[] arr2) {
        Arrays.sort(arr1);
        int[] res = new int[arr1.length];
        int index = 0;
        for (int i : arr2) {
            int j = 0;
            while (arr1[j] <= i) {
                if (arr1[j] == i) {
                    res[index] = arr1[j];
                    index++;
                }
                j++;
            }
        }
        return res;
    }

    @Test
    public void test() {
        int[] arr1 = new int[]{2, 3, 1, 3, 2, 4, 6, 7, 9, 2, 19};
        int[] arr2 = new int[]{2, 1, 4, 3, 9, 6};

        relativeSortArray(arr1, arr2);
    }
}
