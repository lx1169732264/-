package lc_0200;

import util.ListNode;

/**
 * 给你链表的头结点 head ，请将其按 升序 排列并返回 排序后的链表 。
 * 进阶：
 * 你可以在 O(n log n) 时间复杂度和常数级空间复杂度下，对链表进行排序吗？
 * <p>
 * 输入：head = [4,2,1,3]
 * 输出：[1,2,3,4]
 * <p>
 * 输入：head = [-1,5,3,4,0]
 * 输出：[-1,0,3,4,5]
 * <p>
 * 输入：head = []
 * 输出：[]
 * <p>
 * 提示
 * 链表中节点的数目在范围 [0, 5 * 104] 内
 * -105 <= Node.val <= 105
 *
 * @author lx
 */
public class Lc_0148_sortList {
    public ListNode sortList(ListNode head) {
        if (null == head || null == head.next) {
            return head;
        }

        ListNode dump = new ListNode(Integer.MIN_VALUE), curr, pre = head;
        dump.next = head;
        while (null != pre.next) {
            curr = pre.next;
            if (curr.val < pre.val) {
                ListNode temp = dump;
                while (curr.val > temp.next.val) {
                    temp = temp.next;
                }
                pre.next = curr.next;
                curr.next = temp.next;
                temp.next = curr;
                continue;
            }
            pre = curr;
        }
        return dump.next;
    }
}
