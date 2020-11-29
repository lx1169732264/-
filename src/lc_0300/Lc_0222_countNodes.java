package lc_0300;

import util.TreeNode;

/**
 * 给出一个完全二叉树，求出该树的节点个数。
 * 完全二叉树的定义如下：在完全二叉树中，除了最底层节点可能没填满外，其余每层节点数都达到最大值，
 * 并且最下面一层的节点都集中在该层最左边的若干位置。若最底层为第 h 层，则该层包含 1~ 2h 个节点。
 * <p>
 * 示例:
 * 输入:
 * 1
 * / \
 * 2   3
 * / \  /
 * 4  5 6
 * 输出: 6
 *
 * @author lx
 */
public class Lc_0222_countNodes {

    /**
     *
     */
    public int countNodes2(TreeNode root) {
        return root==null?0:1+countNodes(root.left)+countNodes(root.right);
    }

    
    int res = 0;
    public int countNodes(TreeNode root) {
        if (null == root) {
            return 0;
        }
        if (null == root.left) {
            return 1;
        }

        int height = 1;
        //一直遍历左边,求得树的高度
        TreeNode temp = root;
        while (temp.left != null) {
            temp = temp.left;
            height++;
        }
        count(height, root);
        return res + (2 << (height - 2)) - 1;
    }

    private void count(int height, TreeNode node) {
        if (null != node) {
            if (1 == height) {
                res++;
                return;
            }
            height--;
            count(height, node.left);
            count(height, node.right);
        }
    }
}
