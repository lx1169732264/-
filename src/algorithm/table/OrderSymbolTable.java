package algorithm.table;

import util.Node;

/**
 * 有序符号表
 *
 * @author lx
 */
public class OrderSymbolTable {

    private Node head;

    /**
     * 键值对个数
     */
    private int n;

    public OrderSymbolTable() {
        head = new Node(null, null, null);
        n = 0;
    }

    public String get(Integer key) {
        Node node = head;
        while (node.next != null) {
            node = node.next;
            if (node.key.equals(key)) {
                return node.value;
            }
        }
        return null;
    }

    /**
     * 向符号表中插入一个键值对
     * 如果key不存在，返回null
     * 如果key存在，返回旧的value
     * TODO put方法需要有序插入
     */
    public String put(Integer key, String value) {
        // 记录当前节点
        Node currentNode = head.next;
        // 记录上一个节点
        Node pre = head;
        // 如果key大于当前节点的key，则一直找到下一个结点
        while (currentNode != null && key > currentNode.key) {
            // 把currentNode记录给上一个节点
            pre = currentNode;
            currentNode = currentNode.next;
        }
        // 遍历到了插入位置,如果key相等，替换
        if (currentNode != null && key.equals(currentNode.key)) {
            String tempV = currentNode.value;
            currentNode.value = value;
            return tempV;
        }
        // key不相等，把新的结点插入到currentNode之前
        pre.next = new Node(key, value, currentNode);
        return null;
    }

    public String delete(Integer key) {
        Node node = head;
        while (node.next != null) {
            if (node.next.key.equals(key)) {
                String value = node.next.value;
                node.next = node.next.next;
                n--;
                return value;
            }
            node = node.next;
        }
        return null;
    }

    public int size() {
        return n;
    }
}