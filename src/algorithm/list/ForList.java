package algorithm.list;

/**
 * 循环链表
 * @Author: 杨德石
 * @Date: 2020/6/15 22:41
 * @Version 1.0
 */
public class ForList {

    public static void main(String[] args) {
        Node n1 = new Node("1", null);
        Node n2 = new Node("2", null);
        Node n3 = new Node("3", null);
        Node n4 = new Node("4", null);
        Node n5 = new Node("5", null);
        // 构造循环链表
        n1.next = n2;
        n2.next = n3;
        n3.next = n4;
        n4.next = n5;
        n5.next = n1;
    }

    private static class Node {
        public String item;
        public Node next;

        public Node(String item, Node next) {
            this.item = item;
            this.next = next;
        }
    }
}
