package algorithm.list;

/**
 * @Author: 杨德石
 * @Date: 2020/6/14 22:49
 * @Version 1.0
 */
public class TowWayLinkList {

    /**
     * 记录首结点
     */
    private Node first;

    /**
     * 记录尾结点
     */
    private Node last;

    /**
     * 链表长度
     */
    private int n;

    public TowWayLinkList() {
        last = null;
        first = new Node(null, null, null);
        n = 0;
    }

    /**
     * 置空线性表
     */
    public void clear() {
        last = null;
        first.next = null;
        first.item = null;
        first.pre = null;
        n = 0;
    }

    /**
     * 判断线性表是否为空
     *
     * @return
     */
    public boolean isEmpty() {
        return n == 0;
    }

    /**
     * 获取线性表中元素个数
     *
     * @return
     */
    public int length() {
        return n;
    }

    /**
     * 读取并返回线性表中的第index个元素的值
     *
     * @param index
     * @return
     */
    public String get(int index) {
        if (index < 0 || index >= n) {
            System.out.println("下标不合法");
            return null;
        }
        // 获取第一个元素节点
        Node item = first.next;
        for (int i = 0; i < index; i++) {
            item = item.next;
        }
        return item.item;
    }

    /**
     * 在线性表的第i个元素之前插入一个值为t的数据元素
     *
     * @param index
     * @param v
     */
    public void insert(int index, String v) {
        if (index < 0 || index >= n) {
            System.out.println("下标不合法");
            return;
        }
        // 获取头结点
        Node pre = first;
        for (int i = 0; i < index; i++) {
            pre = pre.next;
        }
        // 到这里之后，我们就找到了待插入下标位置的前一个元素
        Node next = pre.next;
        // 构建一个新的节点
        Node newNode = new Node(v, pre, next);
        pre.next = newNode;
        next.pre = newNode;
        // 长度+1
        n++;
    }

    /**
     * 向线性表中添加一个元素t
     *
     * @param v
     */
    public void insert(String v) {
        if (last == null) {
            // 如果last为空说明链表刚被初始化
            // 那么第一个元素，就是last
            last = new Node(v, first, null);
            first.next = last;
        } else {
            // 说明链表已经有了元素
            Node oldLast = last;
            Node node = new Node(v, oldLast, null);
            oldLast.next = node;
            last = node;
        }
        // 长度+1
        n++;
    }

    /**
     * 删除并返回线性表中第index个元素
     *
     * @param index
     * @return
     */
    public String remove(int index) {
        if (index < 0 || index >= n) {
            System.out.println("下标不合法");
            return null;
        }
        // 获取头结点
        Node pre = first;
        for (int i = 0; i < index; i++) {
            pre = pre.next;
        }
        // 到这里，pre就是我们要删除下标位置的前一个节点
        // 获取待删除的当前节点
        Node current = pre.next;
        // 获取待删除节点的下一个节点
        Node next = current.next;
        pre.next = next;
        next.pre = pre;
        // 长度-1
        n--;
        return current.item;
    }

    /**
     * 获取第一个元素
     *
     * @return
     */
    public String getFirst() {
        if (isEmpty()) {
            return null;
        }
        return first.next.item;
    }

    /**
     * 获取最后一个元素
     *
     * @return
     */
    public String getLast() {
        if (isEmpty()) {
            return null;
        }
        return last.item;
    }

    /**
     * 返回线性表中首次出现的指定的元素数据的索引
     *
     * @param v
     * @return
     */
    public int indexOf(String v) {
        return 0;
    }

    private class Node {
        public String item;
        /**
         * 指向下一个节点
         */
        public Node next;
        /**
         * 指向上一个节点
         */
        public Node pre;

        public Node(String item, Node pre, Node next) {
            this.item = item;
            this.pre = pre;
            this.next = next;
        }
    }
}

class Test4 {
    public static void main(String[] args) {
        TowWayLinkList list = new TowWayLinkList();
        list.insert("刘备");
        System.out.println(list.get(0));
        list.insert("关羽");
        System.out.println(list.get(1));
        list.insert(0, "张飞");
        System.out.println(list.get(0)+list.get(1));
        System.out.println(list.remove(1));
        System.out.println(list.get(1));
        System.out.println(list.length());
        list.clear();
        System.out.println(list.length());
        System.out.println(list.isEmpty());
    }
}
