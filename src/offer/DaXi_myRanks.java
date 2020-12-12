package offer;

import org.junit.Assert;
import org.junit.Test;

/***
 * 一帮朋友一起玩射箭游戏, 每人玩 m 轮，然后轮到下一人玩。游戏中有一个总分排行榜，记录每个人得到的总积分及其对应排名。排行榜按总分从大到小排序，名次计算方式如下：
 * 1.最高积分的用户为第1名
 * 2.积分相同则名次相同，否则名次为前一个名次加1
 *
 * 给定某人每一轮的分数，请给出每一轮结束以后，他在总积分排行榜中对应的名次。
 *
 * 比如, 小王开始玩游戏之前，积分排行榜=[100, 90, 90, 80]，小王的每轮积分=[70, 10, 25]
 * 那么小王开始玩之前，积分榜中原排名是1, 2, 2, 3.
 * 小王每轮结束后的总积分分别是70, 80, 105. 那么，每轮结束后其排名分别是4, 3, 1。
 * 也就是返回结果应为 [4,3,1]
 *
 * 算法描述：
 * 给定参数：
 * int[n] ranked: 积分排行榜,单调递减(有可能相等)且大于等于0
 * Int[m] scores: 用户每轮获得的积分, 每轮积分大于等于0
 * 返回值：
 * int[m]: 每轮结束后用户在积分排行榜中的排名
 *
 * 示例一：
 *    ranked=[100, 100, 50 , 40, 40 , 20, 10]
 *    scores=[5, 20, 25, 70]
 *    返回int数组: [6, 4, 2,1]
 * 示例二:
 *    ranked=[100, 90,90, 80,75, 60]
 *    scores=[50, 15, 12, 13, 12]
 *    返回int数组: [6, 5, 4, 2,1]
 *
 *
 * 需要实现的方法原型：
 */
public class DaXi_myRanks {
    int[] myRanks(int[] rankedScores, int[] myScores) {

        //小王玩的总轮数
        int len = myScores.length;
        int[] res = new int[len];

        //杨辉三角,计算小王每轮获得的积分总额
        for (int i = 1; i < len; i++) {
            myScores[i] = myScores[i - 1] + myScores[i];
        }

        //当前的排行榜
        int[] rankArr = new int[rankedScores.length];
        int count = 1;
        for (int i = 0; i < rankedScores.length; i++) {
            if (i > 0 && rankedScores[i - 1] != rankedScores[i]) {
                count++;
            }
            rankArr[i] = count;
        }

        //分数趋势递增,排名不断靠前
        int j = rankedScores.length - 1, index = 0;
        //对小王的成绩进行挨个计算排名
        for (int myScore : myScores) {
            //从后往前遍历排行榜,找到合适的排名位置
            while (j > 0 && rankedScores[j] < myScore) {
                j--;
            }
            if (rankedScores[j] <= myScore) {
                res[index] = rankArr[j];
            } else {
                res[index] = rankArr[j] + 1;
            }
            index++;
        }
        return res;
    }


    @Test
    public void test() {
        int[] ranked1 = {100, 100, 50, 40, 40, 20, 10};
        int[] scores1 = {5, 20, 25, 70};
        int[] res1 = {6, 4, 2, 1};

        int[] ranked2 = {100, 90, 90, 80, 75, 60};
        int[] scores2 = {50, 15, 12, 13, 12};
        int[] res2 = {6, 5, 4, 2, 1};

        Assert.assertArrayEquals(myRanks(ranked1, scores1), res1);
        Assert.assertArrayEquals(myRanks(ranked2, scores2), res2);
    }
}
