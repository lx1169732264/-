package algorithm.list;

/**
 * 链表有环问题
 *
 * @Author: 杨德石
 * @Date: 2020/6/15 23:06
 * @Version 1.0
 */
public class CircleValue {

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
        // 构造一个环
        n7.next = n3;
        if (isCircle(n1)) {
            System.out.println("当前链表有环");
        }

    }

    /**
     * 判断当前链表是否有环
     *
     * @param node
     * @return
     */
    public static boolean isCircle(Node node) {
        Node slow = node;
        Node fast = node;
        // 遍历
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
            if(fast == slow) {
                return true;
            }
        }
        return false;
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
