package algorithm.list;

/**
 * @Author: 杨德石
 * @Date: 2020/6/14 22:49
 * @Version 1.0
 */
public class LinkList {

    /**
     * 记录首结点
     */
    private Node head;

    /**
     * 链表长度
     */
    private int n;

    public LinkList() {
        // 初始化链表
        n = 0;
        head = new Node(null, null);
    }

    /**
     * 置空线性表
     */
    public void clear() {
        head.item = null;
        head.next = null;
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
        Node item = head.next;
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
        if(index<0||index>=n) {
            System.out.println("位置不合法");
            return;
        }
        // 寻找index之前的节点
        // pre是我们要插入元素位置的上一个节点
        Node pre = head;
        for (int i = 0; i < index; i++) {
            // 取出下一个元素赋值给pre
            pre = pre.next;
        }
        // 到这里，插入下标的上一个节点就找到了
        // 取出index下一个位置的元素
        Node next = pre.next;
        // 构建新的Node
        Node newNode = new Node(v, next);
        pre.next = newNode;
        // 长度+1
        n++;
    }

    /**
     * 向线性表中添加一个元素t
     *
     * @param v
     */
    public void insert(String v) {
        // 找到最后一个节点
        Node node = head;
        // 只要node的下一个节点不为null，说明还有下一个元素
        while (node.next != null) {
            node = node.next;
        }
        // 到这里，node就是最后一个节点
        // 创建一个新的节点
        Node newNode = new Node(v, null);
        // 直接将最后一个节点的下一个结点指向新结点
        node.next = newNode;
        // 链表长度+1
        n++;
    }

    /**
     * 删除并返回线性表中第index个元素
     *
     * @param index
     * @return
     */
    public String remove(int index) {
        if(index < 0 || index >= n) {
            System.out.println("下标位置不合法");
            return null;
        }
        // 寻找index之前的元素
        Node pre = head;
        for (int i = 0; i < index; i++) {
            // 取出下一个元素赋值给pre
            pre = pre.next;
        }
        // 到这里就找到了之前的元素
        // 获取当前index位置的结点
        Node current = pre.next;
        // 获取当前index位置的下一个节点
        Node next = current.next;
        // 前一个节点指向下一个节点
        pre.next = next;
        // 长度-1
        n--;
        return current.item;
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
        public Node next;

        public Node(String item, Node next) {
            this.item = item;
            this.next = next;
        }
    }
}

class Test3 {
    public static void main(String[] args) {
        LinkList2 list = new LinkList2();
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
