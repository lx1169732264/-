package util;

public class Node {

    public Integer key;
    public String value;
    public Node next;

    public Node(Integer key, String value, Node next) {
        this.key = key;
        this.value = value;
        this.next = next;
    }
}
