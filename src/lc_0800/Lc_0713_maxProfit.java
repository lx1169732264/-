package lc_0800;

/**
 * 买卖股票的最佳时机和手续费
 * 给定一个整数数组 prices，其中第 i 个元素代表了第 i 天的股票价格 ；非负整数 fee 代表了交易股票的手续费用。
 * 你可以无限次地完成交易，但是你每笔交易都需要付手续费。如果你已经购买了一个股票，在卖出它之前你就不能再继续购买股票了。
 * 返回获得利润的最大值。
 * <p>
 * 注意：这里的一笔交易指买入持有并卖出股票的整个过程，每笔交易你只需要为支付一次手续费。
 * <p>
 * 示例 1:
 * 输入: prices = [1, 3, 2, 8, 4, 9], fee = 2
 * 输出: 8
 * 解释: 能够达到的最大利润:
 * 在此处买入 prices[0] = 1
 * 在此处卖出 prices[3] = 8
 * 在此处买入 prices[4] = 4
 * 在此处卖出 prices[5] = 9
 * 总利润: ((8 - 1) - 2) + ((9 - 4) - 2) = 8.
 * <p>
 * 注意:
 * 0 < prices.length <= 50000.
 * 0 < prices[i] < 50000.
 * 0 <= fee < 50000.
 *
 * @author lx
 */
public class Lc_0713_maxProfit {

    public int maxProfit2(int[] prices, int fee) {
        int n = prices.length;
        int sell = 0, buy = -prices[0];
        for (int i = 1; i < n; ++i) {
            sell = Math.max(sell, buy + prices[i] - fee);
            buy = Math.max(buy, sell - prices[i]);
        }
        return sell;
    }


    /**
     * 考虑到「不能同时参与多笔交易」，因此每天交易结束后只可能存在手里有一支股票或者没有股票的状态。
     * 定义状态 dp[i][0] 表示第 i 天交易完后手里没有股票的最大利润，dp[i][1] 表示第 i 天交易完后手里持有一支股票的最大利润（i 从 0 开始）
     * <p>
     * dp[i][0]的状态转移:可能前一天没股票，即 dp[i−1][0]，或前一天结束时持有股票，即 dp[i−1][1]，今天要卖出，并获得 prices[i] 的收益，支付 fee 的手续费
     * 转移方程：dp[i][0]=max{dp[i−1][0],dp[i−1][1]+prices[i]−fee}
     * <p>
     * dp[i][1]的状态转移:可能前一天已有一支股票，即 dp[i−1][1]，或前一天结束时还没有股票，即 dp[i−1][0]，这时候我们要将其买入，并减少 prices[i] 的收益
     * 转移方程：dp[i][1]=max{dp[i−1][1],dp[i−1][0]−prices[i]}
     * <p>
     * 对于初始状态，根据状态定义我们可以知道第 0 天交易结束的时候有 dp[0][0]=0 以及 dp[0][1]=−prices[0]
     * 因此，我们只要从前往后依次计算状态即可。由于全部交易结束后，持有股票的收益一定低于不持有股票的收益，因此这时候 dp[n−1][0] 的收益必然是大于 dp[n−1][1] 的，最后的答案即为 dp[n−1][0]
     */
    public int maxProfit(int[] prices, int fee) {
        int len = prices.length, n = 1;
        int[][] dp = new int[len][2];
        dp[0][0] = 0;
        dp[0][1] = -prices[0];
        while (n < len) {
            dp[n][0] = Math.max(dp[n - 1][0], dp[n - 1][1] + prices[n] - fee);
            dp[n][1] = Math.max(dp[n - 1][1], dp[n - 1][0] - prices[n]);
            n++;
        }
        return dp[len - 1][0];
    }
}