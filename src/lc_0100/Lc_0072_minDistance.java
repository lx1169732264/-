package lc_0100;

/**
 * 给你两个单词word1 和word2，请你计算出将word1转换成word2 所使用的最少操作数
 * 你可以对一个单词进行如下三种操作：
 * 插入一个字符   删除一个字符  替换一个字符
 * 示例1：
 * 输入：word1 = "horse", word2 = "ros"
 * 输出：3
 * 解释：
 * horse -> rorse (将 'h' 替换为 'r')
 * rorse -> rose (删除 'r')
 * rose -> ros (删除 'e')
 * <p>
 * 示例2：
 * 输入：word1 = "intention", word2 = "execution"
 * 输出：5
 * 解释：
 * intention -> inention (删除 't')
 * inention -> enention (将 'i' 替换为 'e')
 * enention -> exention (将 'n' 替换为 'x')
 * exention -> exection (将 'n' 替换为 'c')
 * exection -> execution (插入 'u')
 * <p>
 * 提示：
 * 0 <= word1.length, word2.length <= 500
 * word1 和 word2 由小写英文字母组成
 */
public class Lc_0072_minDistance {
    /**
     * 如果有单词 A 和单词 B：
     * 对A删除 == 对B插入     对A替换 == 对B替换
     * 本质不同的操作实际上只有三种：
     * 在 A 插入   在 B 插入  修改A
     * <p>
     * 紧接着拆分子问题
     * <p>
     * 在A中插入：如果horse->ro的编辑距离 a，那么horse->ros编辑距离<=a + 1
     * 在B中插入：如果hors->ros的编辑距离 b，那么horse->ros编辑距离<=b + 1
     * 修改A：如果hors->ro编辑距离 c，那么horse->ros编辑距离<= c + 1
     * 所以horse->ros 的编辑距离为 min(a + 1, b + 1, c + 1)
     * <p>
     * 并且操作顺序不影响最终结果。例如对于单词 cat，我们希望在 c 和 a 之间添加字符 d 并且将字符 t 修改为字符 b，那么这两个操作无论为什么顺序，都会得到最终的结果 cdab
     * 对于这个问题拆分出来的所有子问题，我们也可以继续拆分，直到：
     * <p>
     * 字符串 A/B 为空，如从 horse 转换到 ，显然编辑距离为字符串 A 的长度，这里是 5。
     * 因此用动态规划来解决这个问题。用 D[i][j] 表示 A 的前 i 个字母和 B 的前 j 个字母之间的编辑距离
     * 如上所述，获得 D[i][j-1]，D[i-1][j] 和 D[i-1][j-1] 的值之后就可以计算出 D[i][j]。
     * <p>
     * D[i][j-1] 为 A 的前 i 个字符和 B 的前 j - 1 个字符编辑距离的子问题。即对于 B 的第 j 个字符，在 A 的末尾添加了一个相同的字符，那么 D[i][j] 最小可以为 D[i][j-1] + 1；
     * D[i-1][j] 为 A 的前 i - 1 个字符和 B 的前 j 个字符编辑距离的子问题。即对于 A 的第 i 个字符，在 B 的末尾添加了一个相同的字符，那么 D[i][j] 最小可以为 D[i-1][j] + 1；
     * D[i-1][j-1] 为 A 前 i - 1 个字符和 B 的前 j - 1 个字符编辑距离的子问题。即对于 B 的第 j 个字符，修改 A 的第 i 个字符使它们相同，那么 D[i][j] 最小可以为 D[i-1][j-1] + 1。特别地，如果 A 的第 i 个字符和 B 的第 j 个字符原本就相同，那么我们实际上不需要进行修改操作。在这种情况下，D[i][j] 最小可以为 D[i-1][j-1]。
     * <p>
     * 若 A 和 B 的最后一个字母相同：
     * D[i][j] = min(D[i][j - 1] + 1, D[i - 1][j]+1, D[i - 1][j - 1]) = 1 + min(D[i][j - 1], D[i - 1][j], D[i - 1][j - 1] - 1)
     * D[i][j] = min(D[i][j−1]+1,D[i−1][j]+1,D[i−1][j−1]) = 1+min(D[i][j−1],D[i−1][j],D[i−1][j−1]−1)
     * 若 A 和 B 的最后一个字母不同：
     * D[i][j] = 1 + min(D[i][j - 1], D[i - 1][j], D[i - 1][j - 1])
     * D[i][j]= 1+min(D[i][j−1],D[i−1][j],D[i−1][j−1])
     * 对于边界情况，一个空串和一个非空串的编辑距离为 D[i][0] = i 和 D[0][j] = j，D[i][0] 相当于对 word1 执行 i 次删除操作，D[0][j] 相当于对 word1执行 j 次插入操作。
     */
    public int minDistance(String word1, String word2) {
        int n = word1.length(), m = word2.length();

        // 有一个字符串为空串
        if (n * m == 0) {
            return n + m;
        }

        // DP 数组
        int[][] D = new int[n + 1][m + 1];

        // 边界状态初始化
        for (int i = 0; i < n + 1; i++) {
            D[i][0] = i;
        }
        for (int j = 0; j < m + 1; j++) {
            D[0][j] = j;
        }

        // 计算所有 DP 值
        for (int i = 1; i < n + 1; i++) {
            for (int j = 1; j < m + 1; j++) {
                int left = D[i - 1][j] + 1;
                int down = D[i][j - 1] + 1;
                int left_down = D[i - 1][j - 1];
                if (word1.charAt(i - 1) != word2.charAt(j - 1)) {
                    left_down += 1;
                }
                D[i][j] = Math.min(left, Math.min(down, left_down));
            }
        }
        return D[n][m];

    }
}
