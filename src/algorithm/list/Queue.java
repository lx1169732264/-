package algorithm.list;

/**
 * 队列结构
 *
 * @Author: 杨德石
 * @Date: 2020/6/21 22:36
 * @Version 1.0
 */
public class Queue {

    /**
     * 首结点
     */
    private Node head;

    /**
     * 当前队列的元素个数
     */
    private int n;

    /**
     * 记录最后一个结点
     */
    private Node last;

    public Queue() {
        head = new Node(null, null);
        last = null;
        n = 0;
    }

    /**
     * 判断队列是否为空
     *
     * @return
     */
    public boolean isEmpty() {
        return n == 0;
    }

    /**
     * 获取队列中元素的个数
     *
     * @return
     */
    public int size() {
        return n;
    }

    /**
     * 从队列中拿出一个元素
     *
     * @return
     */
    public String dequeue() {
        if (isEmpty()) {
            return null;
        }
        // 不是空，出列
        // 获取当前的第一个元素(对应图中的1元素)
        Node oldFirst = head.next;
        // 让head结点指向下一个结点（对应图中的2元素）
        head.next = head.next.next;
        // 个数-1
        n--;
        if (isEmpty()) {
            last = null;
        }
        return oldFirst.item;
    }

    /**
     * 往队列中插入一个元素
     *
     * @param t
     */
    public void enqueue(String t) {
        // 判断last是否为null
        if (last == null) {
            // last为空，要插入的元素就是last
            last = new Node(t, null);
            // 让首结点指向last
            head.next = last;
        } else {
            // 不是第一个元素
            // 取出旧结点（last）
            Node oldLast = last;
            // 创建新的结点给last
            last = new Node(t, null);
            // 让旧的last元素指向新的结点
            oldLast.next = last;
        }
        // 个数+1
        n++;
    }

    private class Node {
        public String item;
        public Node next;

        public Node(String item, Node next) {
            this.item = item;
            this.next = next;
        }
    }
}

class Test8 {
    public static void main(String[] args) {
        Queue queue = new Queue();
        queue.enqueue("a");
        queue.enqueue("b");
        queue.enqueue("c");
        queue.enqueue("d");
        System.out.println(queue.size());
        System.out.println(queue.dequeue());
        System.out.println(queue.size());
        System.out.println(queue.isEmpty());
        queue.dequeue();
        queue.dequeue();
        queue.dequeue();
        System.out.println(queue.isEmpty());
        System.out.println(queue.size());
    }
}
