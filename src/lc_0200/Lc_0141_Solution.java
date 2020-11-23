package lc_0200;

import util.ListNode;

import java.util.HashSet;
import java.util.Set;

/**
 * 给定一个链表，判断链表中是否有环。
 * 如果链表中有某个节点，可以通过连续跟踪 next 指针再次到达，则链表中存在环。
 * 为了表示给定链表中的环，我们使用整数 pos 来表示链表尾连接到链表中的位置（索引从 0 开始）。
 * 如果 pos 是 -1，则在该链表中没有环。注意：pos 不作为参数进行传递，仅仅是为了标识链表的实际情况。
 * 如果链表中存在环，则返回 true 。 否则，返回 false 。
 * <p>
 * 进阶：
 * 你能用 O(1)（即，常量）内存解决此问题吗？
 *
 * @author lx
 */
public class Lc_0141_Solution {
    /**
     * 快慢指针
     * 快指针fast每次next2次, 如果是环型,快指针将追上慢指针
     * 当fast或fast.next为null,说明到了尾部,线性链表
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