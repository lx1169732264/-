package lc_1300;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author lx
 */
public class Lc_1207_uniqueOccurrences {
    public boolean uniqueOccurrences(int[] arr) {
        int count = 1;
        Map<Integer, Integer> map = new HashMap<>();
        Arrays.sort(arr);
        for (int i = 0; i < arr.length; i++) {
            if (i == arr.length - 1) {
                return !map.containsKey(count);
            }
            
            if (arr[i] == arr[i + 1]) {
                count++;
            } else if (map.containsKey(count)) {
                return false;
            } else {
                map.put(count, arr[i]);
                count = 1;
            }
        }
        return true;
    }
}
