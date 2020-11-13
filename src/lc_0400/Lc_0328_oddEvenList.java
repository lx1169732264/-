package lc_0400;

import com.sun.xml.internal.bind.v2.model.core.ID;
import util.ListNode;

/**
 * 奇偶链表
 * 给定一个单链表，把所有的奇数节点和偶数节点分别排在一起。请注意，这里的奇数节点和偶数节点指的是节点编号的奇偶性，而不是节点的值的奇偶性。
 * 请尝试使用原地算法完成。你的算法的空间复杂度应为 O(1)，时间复杂度应为 O(nodes)，nodes 为节点总数。
 * <p>
 * 示例 1:
 * 输入: 1->2->3->4->5->NULL
 * 输出: 1->3->5->2->4->NULL
 * <p>
 * 示例 2:
 * 输入: 2->1->3->5->6->4->7->NULL
 * 输出: 2->3->6->7->1->5->4->NULL
 *
 * @author lx
 */
public class Lc_0328_oddEvenList {
    boolean odd = false;
    ListNode evenHead;

    public ListNode oddEvenList(ListNode head) {
        if (head == null || head.next == null || head.next.next == null) {
            return head;
        }
        evenHead = head.next;
        reverse(head);
        return head;
    }

    private void reverse(ListNode head) {
        odd = !odd;
        ListNode next = head.next;
        if (head.next.next == null) {
            if (odd) {
                head.next = evenHead;
                return;
            } else {
                head.next = head.next.next;
                next.next = evenHead;
                return;
            }
        }

        head.next = head.next.next;
        reverse(next);
    }
}
