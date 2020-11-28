package algorithm.table;

import util.Node;

/**
 * 无序符号表
 *
 * @author lx
 */
public class SymbolTable {

    private Node head;

    /**
     * 键值对个数
     */
    private int n;

    public SymbolTable() {
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
     */
    public String put(Integer key, String value) {
        // 先从符号表中查找键位key的键值对
        Node node = head;
        while (node.next != null) {
            node = node.next;
            if (node.key.equals(key)) {
                String oldValue = node.value;
                node.value = value;
                return oldValue;
            }
        }
        // key在表中不存在
        Node oldFirst = head.next;
        head.next = new Node(key, value, oldFirst);
        // 个数+1
        n++;
        return null;
    }

    /**
     * 删除键位key的键值对，并将删除的值给返回
     */
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
