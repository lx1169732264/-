package lc_0100;

import util.TreeNode;

import java.util.LinkedList;
import java.util.List;

/**
 * 二叉树的中序遍历
 * 给定一个二叉树的根节点 root ，返回它的 中序 遍历。
 * <p>
 * 示例 1
 * 输入：root = [1,null,2,3]
 * 输出：[1,3,2]
 * <p>
 * 示例 2：
 * 输入：root = []
 * 输出：[]
 * <p>
 * 示例 3：
 * 输入：root = [1]
 * 输出：[1]
 * <p>
 * 示例 4：
 * 输入：root = [1,2]
 * 输出：[2,1]
 * <p>
 * 示例 5：
 * 输入：root = [1,null,2]
 * 输出：[1,2]
 *
 * 提示：
 * 树中节点数目在范围 [0, 100] 内
 * -100 <= Node.val <= 100
 *
 * 进阶:递归算法很简单，你可以通过迭代算法完成吗？
 */
public class Lc_0094_inorderTraversal {
    public List<Integer> inorderTraversal(TreeNode root) {
        LinkedList<Integer> list = new LinkedList<>();
        if (null != root) {
            dfs(root, list);
        }
        return list;
    }

    public void dfs(TreeNode root, List<Integer> list) {
        if (null != root.left) {
            dfs(root.left, list);
        }
        list.add(root.val);

        if (null != root.right) {
            dfs(root.right, list);
        }
    }
}
