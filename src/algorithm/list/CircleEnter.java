package algorithm.list;

/**
 * 链表有环问题
 *
 * @Author: 杨德石
 * @Date: 2020/6/15 23:06
 * @Version 1.0
 */
public class CircleEnter {

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
        n7.next = n4;

        int enter = getEnter(n1);
        System.out.println(enter);

    }

    /**
     * 获取有环链表的环入口，返回入口值
     *
     * @param node
     * @return
     */
    public static int getEnter(Node node) {
        Node slow = node;
        Node fast = node;
        // 定义一个新指针
        Node temp = null;
        // 遍历链表
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
            if(fast == slow) {
                // 说明两个指针相遇了，有环，新指针开始移动
                temp = node;
                continue;
            }
            if(temp!=null) {
                // temp不为空，说明新指针已经开始移动了
                temp = temp.next;
                // 判断一下新指针与慢指针是否相等，如相遇，则是环起点
                if(temp == slow) {
                    return temp.item;
                }
            }
        }
        return 0;
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
