package lc_0200;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import util.TreeNode;

/**
 * 给定一个二叉树，返回其节点值自底向上的层次遍历。（即按从叶子节点所在层到根节点所在的层，逐层从左向右遍历）
 * <p>
 * 例如：
 * 给定二叉树 [3,9,20,null,null,15,7],
 * 3
 * / \
 * 9  20
 * /  \
 * 15   7
 * 返回其自底向上的层次遍历为：
 * <p>
 * [
 * [15,7],
 * [9,20],
 * [3]
 * ]
 */
public class Lc_0107_levelOrderBottom {
    /**
     * 方法1:BFS优先广度遍历    队列
     */
    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        LinkedList<List<Integer>> res = new LinkedList<>();
        if (root == null) { return res; }
        Queue<TreeNode> q = new LinkedList<>();
        //入队
        q.offer(root);
        while (!q.isEmpty()) {
            //LinkedList在队列时才会采用 offer/poll/take等方法
            LinkedList<Integer> subList = new LinkedList<>();

            int sz = q.size();
            for (int i = 0; i < sz; i++) {
                /**
                 * 调用poll()和pop()都可以返回队首元素并将其从原队列删除
                 * 当队列为空时，调用pop()会抛出异常，而poll()会返回null
                 *
                 * poll 每次都会删除队列中的所有元素,再加入他们的子结点,使得遍历"层次化"
                 */
                TreeNode cur = q.poll();
                //将元素值存入subList,作为结果list集合
                subList.offer(cur.val);
                if (cur.left != null) {
                    q.offer(cur.left);
                }
                if (cur.right != null) {
                    q.offer(cur.right);
                }
            }
            //offerFirst/addFirst在列表的开头插入指定的元素,达到倒序输出
            res.offerFirst(subList);
        }
        return res;
    }
}
