package lc_0100;

/**
 * 给定两个二叉树，编写一个函数来检验它们是否相同。
 * 如果两个树在结构上相同，并且节点具有相同的值，则认为它们是相同的。
 * <p>
 * 输入:      1          1
 * /           \
 * 2             2
 * [1,2],     [1,null,2]
 * 输出: false
 */
public class Lc_0100_isSameTree {
    /**
     * 方法1 递归:  递归判断左右子树的值是否相等
     */
    public boolean isSameTree(TreeNode p, TreeNode q) {
        //二者都为空,返回true
        if (p == null && q == null) {
            return true;
        }
        //其中一个为空,false
        else if (p == null | q == null) {
            return false;
        }
        //值不相等,false
        else if (p.val != q.val) {
            return false;
        }
        //递归判断左右子树
        return isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
    }

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }
}
