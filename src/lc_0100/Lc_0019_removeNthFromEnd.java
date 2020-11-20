package lc_0100;

/**
 * 给定一个链表，删除链表的倒数第 n 个节点，并且返回链表的头结点。
 * <p>
 * 示例：
 * 给定一个链表: 1->2->3->4->5, 和 n = 2.
 * 当删除了倒数第二个节点后，链表变为 1->2->3->5.
 * 说明：
 * 给定的 n 保证是有效的。
 */
public class Lc_0019_removeNthFromEnd {
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode pre = new ListNode(0);
        pre.next = head;
        ListNode start = pre, end = pre;
        //让start与end保持n的距离
        while (n != 0) {
            start = start.next;
            n--;
        }
        //start没到末尾
        while (start.next != null) {
            start = start.next;
            end = end.next;
        }
        //跳跃,删除节点
        end.next = end.next.next;
        return pre.next;
    }

    public static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }
}
