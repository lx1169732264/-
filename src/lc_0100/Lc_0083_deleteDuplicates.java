package lc_0100;

/**
 * 给定一个排序链表，删除所有重复的元素，使得每个元素只出现一次。
 * <p>
 * 示例 1:
 * 输入: 1->1->2
 * 输出: 1->2
 */
public class Lc_0083_deleteDuplicates {
    //方法1:直接法   判断当前结点的值是否等于上个节点的值,由于是有序链表
    public ListNode deleteDuplicates(ListNode head) {
        ListNode pre = null, curr = head;
        while (curr != null) {
            if (pre == null) {
                pre = curr;
            } else if (curr.val == pre.val) {
                pre.next = curr.next;
            } else {
                pre = curr;
            }
            curr = curr.next;
        }
        return head;
    }

    public class ListNode {
        int val;
        ListNode next;
        ListNode(int x) {
            val = x;
        }
    }
}
