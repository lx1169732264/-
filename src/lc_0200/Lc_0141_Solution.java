package lc_0200;

import java.util.HashSet;
import java.util.Set;

/**
 * @author lx
 */
public class Lc_0141_Solution {
    /**
     * 快慢指针
     * 快指针fast每次next2次, 如果是环型,快指针将追上慢指针
     * 当fast或fast.next为null,说明到了尾部,线性链表
     *
     * @param head
     * @return
     */
    public boolean hasCycle2(ListNode head) {
        if (null == head || null == head.next) {
            return false;
        }
        ListNode fast = head.next;
        ListNode slow = head;

        while (slow != fast) {
            if (fast == null || fast.next == null) {
                return false;
            }
            slow = slow.next;
            fast = fast.next.next;
        }
        return true;
    }

    /**
     * 遍历,存入set判断重复则是环型
     *
     * @param head
     * @return
     */
    public boolean hasCycle(ListNode head) {
        Set<ListNode> set = new HashSet<>();

        while (null != head) {
            if (!set.add(head)) {
                return true;
            }
            head = head.next;
        }
        return false;
    }


}

class ListNode {
    int val;
    ListNode next;

    ListNode(int x) {
        val = x;
        next = null;
    }
}