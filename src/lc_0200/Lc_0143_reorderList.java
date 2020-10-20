package lc_0200;

import java.util.*;

/**
 * 给定一个单链表 L：L0→L1→…→Ln-1→Ln ，
 * 将其重新排列后变为： L0→Ln→L1→Ln-1→L2→Ln-2→…
 * <p>
 * 你不能只是单纯的改变节点内部的值，而是需要实际的进行节点交换。
 * <p>
 * 示例 1:
 * 给定链表 1->2->3->4, 重新排列为 1->4->2->3.
 * <p>
 * 示例 2:
 * 给定链表 1->2->3->4->5, 重新排列为 1->5->2->4->3.
 *
 * @author lx
 */
public class Lc_0143_reorderList {

    /**
     * List,让每个节点具有下标,从而能够快速地定位到要改变指向的结点
     */
    public void reorderList(ListNode head) {
        if (null == head) {
            return;
        }

        List<ListNode> list = new ArrayList<>();
        ListNode node = head;
        while (node != null) {
            list.add(node);
            node = node.next;
        }

        int i = 0, j = list.size() - 1;
        while (j - i > 1) {
            list.get(i).next = list.get(j);
            list.get(j).next = list.get(i + 1);
            i++;
            j--;
        }
        list.get(j).next = null;
    }

    /**
     * 双向队列,元素可以从头或尾弹出
     */
    public void reorderList2(ListNode head) {
        if (null == head) {
            return;
        }

        Deque<ListNode> deque = new LinkedList<>();
        ListNode next = head.next;
        while (null != next) {
            deque.add(next);
            next = next.next;
        }

        while (!deque.isEmpty()) {
            head.next = deque.pollLast();
            head = head.next;
            if (!deque.isEmpty()) {
                head.next = deque.pollFirst();
                head = head.next;
            }
        }
        head.next = null;
    }

    /**
     * 递归
     * 然而单向链表无法回头,递归需要改变队尾元素tail与倒数第二个队尾tail-1  让tail-1指向null   tail指向head
     * 如果是普通的递归,需要不停地进行遍历
     * <p>
     * 巧妙的是,每次递归都只交换了头尾2各元素,未被递归的长度是每次-2的,从而可以使用未被递归的节点个数作为递归出口
     * 当最后只剩2个以下的元素时,将停止递归
     */
    public void reorderList3(ListNode head) {
        if (head == null || head.next == null || head.next.next == null) {
            return;
        }
        int len = 0;
        ListNode node = head;
        while (null != node) {
            len++;
            node = node.next;
        }
        reorderListHelper(head, len);

    }

    private ListNode reorderListHelper(ListNode head, int len) {
        if (len == 1) {
            ListNode outTail = head.next;
            head.next = null;
            return outTail;
        } else if (len == 2) {
            ListNode outTail = head.next.next;
            head.next.next = null;
            return outTail;
        }
        //递归得到对应的尾节点，之后处理头尾节点的重排
        ListNode tail = reorderListHelper(head.next, len - 2);
        //中间链表的头结点
        ListNode subHead = head.next;
        head.next = tail;
        //上一层 head 对应的 tail
        ListNode outTail = tail.next;
        tail.next = subHead;
        return outTail;
    }


    public class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }
}