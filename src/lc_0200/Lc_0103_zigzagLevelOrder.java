package lc_0200;

import util.TreeNode;

import java.util.*;

/**
 * @author lx
 */
public class Lc_0103_zigzagLevelOrder {
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> res = new LinkedList<>();
        if (root == null) {
            return res;
        }

        //true:从左到右
        boolean order = true;
        //双端队列存储要处理的TreeNode,以order决定推入队列的头还是尾
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        //当queue没有元素 -> 没有要处理的TreeNode -> 操作完成
        while (!queue.isEmpty()) {

            //temp存储当前层的处理结果
            Deque<Integer> temp = new LinkedList<>();
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();

                //从左到右的顺序,则加到尾部
                if (order) {
                    temp.addLast(node.val);
                } else {
                    temp.addFirst(node.val);
                }

                //维护queue,用于处理下一层的TreeNode,queue保持左->右的顺序即可,用order决定顺序
                if (null != node.left) {
                    queue.offer(node.left);
                }
                if (null != node.right) {
                    queue.offer(node.right);
                }
            }
            //重置顺序
            order = !order;
            res.add(new LinkedList<>(temp));
        }

        return res;
    }
}
