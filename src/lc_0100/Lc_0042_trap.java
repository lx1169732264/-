package lc_0100;

/**
 * 给定 n 个非负整数表示每个宽度为 1 的柱子的高度图，计算按此排列的柱子，下雨之后能接多少雨水。
 * <p>
 * 输入: [0,1,0,2,1,0,1,3,2,1,2,1]
 * 输出: 6
 *
 * @author lx
 */
public class Lc_0042_trap {
    /**
     * 能否接住雨水取决于左右两侧的"柱子"都要>0
     * 雨水数量由左右的最小值决定    "水桶定理"
     */
    int max = -1, manIndex = 0, result = 0;
    public int trap(int[] height) {
        //首先找到最大值和它的下标
        for (int i = 0; i < height.length; i++) {
            if (height[i] >= max) {
                max = height[i];
                manIndex = i;
            }
        }
        for (int left = 0; left < manIndex; left++) {
            for (int i = left + 1; i <= manIndex; i++) {
                //对于左边到中间,i<left就能接到雨水,此时left>i<maxAddr
                if (height[i] < height[left]) {
                    result += (height[left] - height[i]);
                    //left<i<maxAddr,就会形成陡坡,接不住雨水,将left移至i
                } else {
                    left = i;
                }
            }
        }
        for (int right = height.length - 1; right > manIndex; right--) {
            for (int i = right - 1; i >= manIndex; i--) {
                if (height[i] < height[right]) {
                    result += (height[right] - height[i]);
                } else {
                    right = i;
                }
            }
        }
        return result;
    }
}
