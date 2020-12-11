package lc_0700;


import org.junit.Assert;
import org.junit.Test;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Dota2参议院
 * Dota2 的世界里有两个阵营：Radiant(天辉)和 Dire(夜魇)
 * Dota2 参议院由来自两派的参议员组成。现在参议院希望对一个 Dota2 游戏里的改变作出决定。他们以一个基于轮为过程的投票进行。在每一轮中，每一位参议员都可以行使两项权利中的一项：
 * 禁止一名参议员的权利：参议员可以让另一位参议员在这一轮和随后的几轮中丧失所有的权利。
 * 宣布胜利：如果参议员发现有权利投票的参议员都是同一个阵营的，他可以宣布胜利并决定在游戏中的有关变化。
 * <p>
 * 给定一个字符串代表每个参议员的阵营。字母 “R” 和 “D” 分别代表了 Radiant（天辉）和 Dire（夜魇）。然后，如果有 n 个参议员，给定字符串的大小将是 n。
 * 以轮为基础的过程从给定顺序的第一个参议员开始到最后一个参议员结束。这一过程将持续到投票结束。所有失去权利的参议员将在过程中被跳过。
 * 假设每一位参议员都足够聪明，会为自己的政党做出最好的策略，你需要预测哪一方最终会宣布胜利并在 Dota2 游戏中决定改变。输出应该是 Radiant 或 Dire。
 * <p>
 * 示例 1：
 * 输入："RD"
 * 输出："Radiant"
 * 解释：第一个参议员来自 Radiant 阵营并且他可以使用第一项权利让第二个参议员失去权力，因此第二个参议员将被跳过因为他没有任何权利。
 * 然后在第二轮的时候，第一个参议员可以宣布胜利，因为他是唯一一个有投票权的人
 * <p>
 * 示例 2：
 * 输入："RDD"
 * 输出："Dire"
 * 解释：
 * 第一轮中,第一个来自 Radiant 阵营的参议员可以使用第一项权利禁止第二个参议员的权利
 * 第二个来自 Dire 阵营的参议员会被跳过因为他的权利被禁止
 * 第三个来自 Dire 阵营的参议员可以使用他的第一项权利禁止第一个参议员的权利
 * 因此在第二轮只剩下第三个参议员拥有投票的权利,于是他可以宣布胜利
 * <p>
 * 提示
 * 给定字符串的长度在 [1, 10,000] 之间.
 *
 * @author lx
 */
public class Lc_0649_predictPartyVictory {

    /**
     * 由于我们总要挑选投票顺序最早的议员，因此我们可以使用两个队列 radiant 和 dire 分别按照投票顺序存储天辉方和夜魇方每一名议员的投票时间。随后我们就可以开始模拟整个投票的过程：
     * 如果此时 radiant 或者 dire 为空，那么就可以宣布另一方获得胜利；
     * 如果均不为空，那么比较这两个队列的首元素，就可以确定当前行使权利的是哪一名议员。如果 radiant 的首元素较小，那说明轮到天辉方的议员行使权利，其会挑选 dire 的首元素对应的那一名议员。
     * 因此，我们会将 dire 的首元素永久地弹出，并将radiant 的首元素弹出，增加 n 之后再重新放回队列
     * 这里 n=senate.length，防止这一轮中他再次消掉其他字符
     */
    public String predictPartyVictory(String senate) {
            int n = senate.length();
            Queue<Integer> radiant = new LinkedList<>();
            Queue<Integer> dire = new LinkedList<>();
            for (int i = 0; i < n; ++i) {
                if (senate.charAt(i) == 'R') {
                    radiant.offer(i);
                } else {
                    dire.offer(i);
                }
            }
            while (!radiant.isEmpty() && !dire.isEmpty()) {
                int r = radiant.poll(), d = dire.poll();
                if (r < d) {
                    radiant.offer(r + n);
                } else {
                    dire.offer(d + n);
                }
            }
            return !radiant.isEmpty() ? "Radiant" : "Dire";
        }


    @Test
    public void test() {
        String s1 = "RD";
        String s2 = "RDD";
        String s3 = "DRRD";
        String s4 = "DRRDRDRDRDDRDRDR";
        String res1 = "Radiant";
        String res2 = "Dire";
        Assert.assertSame(res1, predictPartyVictory(s1));
        Assert.assertSame(res2, predictPartyVictory(s2));
        Assert.assertSame(res2, predictPartyVictory(s3));
        Assert.assertSame(res1, predictPartyVictory(s4));
    }
}
