package lc_0200;

import util.TreeNode;

/**
 * 给定一个二叉树，它的每个结点都存放一个 0-9 的数字，每条从根到叶子节点的路径都代表一个数字。
 * 例如，从根到叶子节点路径 1->2->3 代表数字 123。
 * 计算从根到叶子节点生成的所有数字之和。
 * <p>
 * 示例 1:
 * 输入: [1,2,3]
 * 1
 * / \
 * 2   3
 * 12 + 13 = 25.
 * <p>
 * 示例 2:
 * 输入: [4,9,0,5,1]
 * 4
 * / \
 * 9   0
 * / \
 * 5   1
 * 495 + 491 + 40 = 1026.
 *
 * @author lx
 */
public class Lc_0129_sumNumbers {
    int count = 0;

    public int sumNumbers(TreeNode root) {
        recursion(root);
        return count;
    }

    private void recursion(TreeNode root) {
        if (null == root) {
            return;
        }

        if (null != root.left) {
            root.left.val += root.val * 10;
            recursion(root.left);
        }

        if (null != root.right) {
            root.right.val += root.val * 10;
            recursion(root.right);
        }
        if (null == root.left&&null == root.right) {
            count += root.val;
        }
    }
}
