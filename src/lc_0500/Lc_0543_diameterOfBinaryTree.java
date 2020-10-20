package lc_0500;

/**
 * 给定一棵二叉树，你需要计算它的直径长度。一棵二叉树的直径长度是任意两个结点路径长度中的最大值。这条路径可能穿过根结点。
 * <p>
 * 示例 :
 * 给定二叉树
 * <p>
 * 1
 * / \
 * 2   3
 * / \
 * 4   5
 * 返回 3, 它的长度是路径 [4,2,1,3] 或者 [5,2,1,3]。
 * 注意：两结点之间的路径长度是以它们之间边的数目表示。
 */
public class Lc_0543_diameterOfBinaryTree {
    /**
     * 方法1 全局变量递归   max为最大左右深度和
     * 并不是根结点的左右深度就是最长路径,如果根结点无左子树,右子树很多,
     * 就导致右子树"茂盛",最大深度和将在右子树的某一结点出产生
     * <p>
     * 这道题的本质还是求树深度的变型
     */
    int max;

    public int diameterOfBinaryTree(TreeNode root) {
        if (root == null) {
            return 0;
        }
        max = 1;
        deepSearch(root);
        return max - 1;
    }

    private int deepSearch(TreeNode node) {
        if (node == null) {
            return 0;
        } else {
            int maxLeft = deepSearch(node.left);
            int maxRight = deepSearch(node.right);
            //对于每个结点,计算它的左右深度之和是否为最大
            max = Math.max(max, maxLeft + maxRight + 1);
            return Math.max(maxLeft, maxRight) + 1;
        }
    }

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }
}
