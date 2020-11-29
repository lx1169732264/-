package algorithm.list;

/**
 * 约瑟夫环问题
 *
 * @Author: 杨德石
 * @Date: 2020/6/15 22:46
 * @Version 1.0
 */
public class Joseph {

    public static void main(String[] args) {
        // 构建循环链表
        Node first = new Node(1, null);
        // 记录前一个节点
        Node pre = first;
        // 构造一个41节点的循环链表
        for (int i = 2; i <= 41; i++) {
            // 每一次循环，构建一个Node
            Node node = new Node(i, null);
            pre.next = node;
            // 记录当前节点为上一个节点
            pre = node;
            if (i == 41) {
                // 说明到最后了，让最后一个节点指向第一个节点
                pre.next = first;
            }
        }
        // 计数器count
        int count = 0;
        // 遍历链表，每次循环count++
        Node n = first;
        // 记录上一个节点
        Node back = null;
        // 循环链表
        while (n != n.next) {
            // 报数
            count++;
            // 判断count是否为3，如果是，删除当前节点
            if (count == 3) {
                back.next = n.next;
                System.out.println("当前死亡的人：" + n.item);
                // 计数器清零
                count = 0;
                // 下一个人继续
                n = n.next;
            } else {
                // 记录上一个节点
                back = n;
                // 下一个人继续
                n = n.next;
            }
        }
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
