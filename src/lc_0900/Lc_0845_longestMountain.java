package lc_0900;

/**
 * 数组中的最长山脉
 * 我们把数组 A 中符合下列属性的任意连续子数组 B 称为 “山脉”：
 * B.length >= 3,存在 0 < i < B.length - 1 使得 B[0] < B[1] < ... B[i-1] < B[i] > B[i+1] > ... > B[B.length - 1]
 * （注意：B 可以是 A 的任意子数组，包括整个数组 A。）
 * <p>
 * 给出一个整数数组 A，返回最长 “山脉” 的长度。如果不含有 “山脉” 则返回 0。
 * <p>
 * 示例 1：
 * 输入：[2,1,4,7,3,2,5]
 * 输出：5
 * 解释：最长的 “山脉” 是 [1,4,7,3,2]，长度为 5。
 * <p>
 * 示例 2：
 * 输入：[2,2,2]
 * 输出：0
 * 解释：不含 “山脉”。
 * <p>
 * 提示：
 * 0 <= A.length <= 10000
 * 0 <= A[i] <= 10000
 */
public class Lc_0845_longestMountain {
    /**
     * 枚举山脚 山脚下标之差即为山的长度
     * 两个山脚为left,right
     * right-left>=1
     * 当right=A.length - 1,排除还在递增或到达山顶,则无法形成山脉的情况
     */
    public static int longestMountain(int[] A) {
        int n = A.length, ans = 0, left = 0;
        while (left + 2 < n) {
            int right = left + 1;
            if (A[left] < A[left + 1]) {
                while (right + 1 < n && A[right] < A[right + 1]) {
                    ++right;
                }
                if (right < n - 1 && A[right] > A[right + 1]) {
                    while (right + 1 < n && A[right] > A[right + 1]) {
                        ++right;
                    }
                    ans = Math.max(ans, right - left + 1);
                } else {
                    ++right;
                }
            }
            left = right;
        }
        return ans;
    }

    public static void main(String[] args) {
        int[] A = new int[]{0, 1, 2, 3, 4, 5, 4, 3, 2, 1, 0};
        System.out.println(longestMountain(A));
    }
}
