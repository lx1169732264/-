package lc_0100;

/**
 * 给出两个非空的链表用来表示两个非负的整数。其中，它们各自的位数是按照逆序的方式存储的，
 * 并且它们的每个节点只能存储一位数字
 * 如果，我们将这两个数相加起来，则会返回一个新的链表来表示它们的和。
 * 您可以假设除了数字 0 之外，这两个数都不会以 0开头。

 * 示例：
 * 输入：(2 -> 4 -> 3) + (5 -> 6 -> 4)
 * 输出：7 -> 0 -> 8
 * 原因：342 + 465 = 807
 * @author lx
 */
public class Lc_0002_addTwoNumbers {
    /**
     * 方法1
     * 两数相加,遇10进1
     * 时间复杂度：O(max(m, n)),假设 mm 和 nn 分别表示 l1l1 和 l2l2 的长度，上面的算法最多重复max(m,n) 次。
     * 空间复杂度：O(\max(m, n))， 新列表的长度最多为 \max(m,n) + 1max(m,n)+1。
     */
    public static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) { val = x; }
    }

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        //dummyHead哑结点/虚拟头结点  第一个元素就是dummyhead的next所对应的节点元素
        ListNode dummyHead = new ListNode(0);

        //将当前结点初始化为返回列表的哑结点。
        ListNode currNode = dummyHead;
        ListNode p = l1, q = l2;
        //进位,取值0/1
        int carry = 0;
        /**
         * ||只要满足第一个条件，后面的条件就不再判断,优化性能
         *
         * 需要注意的是,两个链表可能长度不相等,当其中一方的下标不动的时候(没数字了),它的值就要设置为0
         */
        while (p != null || q != null) {
            int x = (p != null) ? p.val : 0;
            int y = (q != null) ? q.val : 0;
            int sum = carry + x + y;
            //取模得到进位
            carry = sum / 10;
            currNode.next = new ListNode(sum % 10);

            //将当前结点指向下一结点
            currNode = currNode.next;
            if (p != null) { p = p.next; }
            if (q != null) { q = q.next; }
        }
        if (carry > 0) {
            currNode.next = new ListNode(carry);
        }
        return dummyHead.next;
    }
}

