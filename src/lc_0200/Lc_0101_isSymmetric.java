package lc_0200;

import util.TreeNode;

/**
 * 给定一个二叉树，检查它是否是镜像对称的。
 * 例如，[1,2,2,3,4,4,3] 是对称的。
 * 1
 * / \
 * 2   2
 * / \ / \
 * 3  4 4  3
 * 但是下面这个[1,2,2,null,3,null,3] 则不是镜像对称的:
 * 1
 * / \
 * 2   2
 * \   \
 * 3    3
 */
public class Lc_0101_isSymmetric {
    /**
     * 镜像法
     * 复制root,将root1的左与root2的右相比较
     */
    public boolean isSymmetric(TreeNode root) {
        return mirror(root, root);
    }

    private boolean mirror(TreeNode root, TreeNode root1) {
        if (root == null && root1 == null) {
            return true;
        } else if (root == null | root1 == null) {
            return false;
        }
        //分别比较两个根结点是否相等,再递归判断左右结点,拿root的left与root1的right相比较
        return root.val == root1.val && mirror(root.left, root1.right) && mirror(root.right, root1.left);
    }
}
