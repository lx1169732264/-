package lc_0100;

import util.ListNode;

/**
 * 给定一个排序链表，删除所有含有重复数字的节点，只保留原始链表中 没有重复出现的数字。
 * <p>
 * 示例 1:
 * 输入: 1->2->3->3->4->4->5
 * 输出: 1->2->5
 * <p>
 * 示例 2:
 * 输入: 1->1->1->2->3
 * 输出: 2->3
 */
public class Lc_0082_deleteDuplicates {
    public ListNode deleteDuplicates(ListNode head) {
        if (head == null) {
            return head;
        }

        //头结点可能被删除,需要哑结点
        ListNode dummy = new ListNode(0, head);
        ListNode cur = dummy;

        while (cur.next != null && cur.next.next != null) {
            if (cur.next.val == cur.next.next.val) {
                int x = cur.next.val;
                while (cur.next != null && cur.next.val == x) {
                    cur.next = cur.next.next;
                }
            } else {
                cur = cur.next;
            }
        }

        return dummy.next;
    }
}