package lc_0200;

import java.util.ArrayList;
import java.util.List;
import util.TreeNode;

/**
 * @author lx
 */
public class Lc_0144_preorderTraversal {
    List<Integer> list = new ArrayList<>();
    public List<Integer> preorderTraversal(TreeNode root) {
        if (null == root) {
           return list;
        }
        recursion(root);
        return list;
    }

  private void recursion(TreeNode root){
      if (null == root) {
          list.add(null);
      }else {
          list.add(root.val);
          preorderTraversal(root.left);
          preorderTraversal(root.right);
      }
  }
}
