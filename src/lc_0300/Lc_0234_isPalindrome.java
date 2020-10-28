package lc_0300;

/**
 * 请判断一个链表是否为回文链表。
 * <p>
 * 示例 1:
 * 输入: 1->2
 * 输出: false
 * <p>
 * 示例 2:
 * 输入: 1->2->2->1
 * 输出: true
 * 进阶：
 * 你能否用 O(n) 时间复杂度和 O(1) 空间复杂度解决此题？
 *
 * @author lx
 */
public class Lc_0234_isPalindrome {

    /**
     * 快慢指针取中点
     * 划分两段,此时处于逆序相等的状态 如123    321
     * 以slow指针为递归起点,递归到链表尾部 此时开始与头结点进行比较
     * 每次循环,slow退到上一层,head指向下一个
     */
    ListNode head = null;
    public boolean isPalindrome(ListNode head) {
        this.head = head;
        ListNode fast = head, slow = head;
        //取链表的中点
        while (fast != null && null != fast.next) {
            fast = fast.next.next;
            slow = slow.next;
        }
        return recursion(slow);
    }

    private boolean recursion(ListNode slow) {
        if (null == slow) {
            return true;
        }
        boolean ret = recursion(slow.next);
        boolean ret2 = head.val == slow.val;
        head = head.next;
        return ret && ret2;
    }

    public class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }
}