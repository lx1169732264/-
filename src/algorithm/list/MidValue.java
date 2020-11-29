package algorithm.list;

/**
 * 快慢指针中间值问题
 *
 * @Author: 杨德石
 * @Date: 2020/6/15 23:06
 * @Version 1.0
 */
public class MidValue {

    public static void main(String[] args) {
        Node n1 = new Node(1, null);
        Node n2 = new Node(2, null);
        Node n3 = new Node(3, null);
        Node n4 = new Node(4, null);
        Node n5 = new Node(5, null);
        Node n6 = new Node(6, null);
        Node n7 = new Node(7, null);
        n1.next = n2;
        n2.next = n3;
        n3.next = n4;
        n4.next = n5;
        n5.next = n6;
        n6.next = n7;

        int mid = getMid(n1);
        System.out.println("中间值是" + mid);
    }

    /**
     * 寻找链表的中间元素
     *
     * @param node
     * @return
     */
    private static int getMid(Node node) {
        // 给一个快指针
        Node fast = node;
        // 给一个慢指针
        Node slow = node;
        // 遍历链表，移动指针
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow.item;
    }

    private static class Node {
        public int item;
        public Node next;

        public Node(int item, Node next) {
            this.item = item;
            this.next = next;
        }
    }

}
