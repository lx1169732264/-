package algorithm.tree;

/**
 * 红黑树
 *
 * @Author: 杨德石
 * @Date: 2020/7/12 23:27
 * @Version 1.0
 */
public class RedBlackTree<Key extends Comparable<Key>, Value> {

    private Node<Key, Value> root;
    private int n;
    /**
     * 红色标识
     */
    private static final boolean RED = true;
    private static final boolean BLACK = false;

    public RedBlackTree() {
        n = 0;
    }

    /**
     * 判断当前节点的父指向链接是否为红色
     *
     * @return
     */
    private boolean isRed(Node<Key, Value> node) {
        if (node == null) {
            return false;
        }
        // 非空节点
        return node.color == RED;
    }

    /**
     * 左旋调整
     *
     * @param h
     * @return
     */
    private Node<Key, Value> rotateLeft(Node<Key, Value> h) {
        // 找出h的右节点
        Node<Key, Value> hRight = h.right;
        // 找出右节点的左子节点
        Node<Key, Value> lhRight = hRight.left;
        // 让当前节点h的右子节点的左子节点成为当前结点的右子节点
        h.right = lhRight;
        // 让当前结点h成为右子节点的左子节点
        hRight.left = h;
        // 让当前节点h的color变成右子节点的color
        hRight.color = h.color;
        // 让当前节点h的color变为red
        h.color = RED;
        return hRight;
    }

    /**
     * 右旋调整
     *
     * @param h
     * @return
     */
    private Node<Key, Value> rotateRight(Node<Key, Value> h) {
        // 获取当前节点h的左子节点
        Node<Key, Value> hLeft = h.left;
        // 找出当前节点h的左子节点的右子节点
        Node<Key, Value> rhLeft = hLeft.right;
        // 让当前节点h的左子节点的右子节点成为当前节点的左子节点
        h.left = rhLeft;
        // 让当前节点成为左子节点的右子节点
        hLeft.right = h;
        // 让当前节点h的color值变为左子节点的color值
        hLeft.color = h.color;
        // 让当前节点h的color变为RED
        h.color = RED;
        return hLeft;
    }

    /**
     * 颜色反转，相当于完成拆分4-节点
     *
     * @param h
     */
    private void flipColors(Node<Key, Value> h) {
        // 当前节点的color变为RED
        h.color = RED;
        h.left.color = BLACK;
        h.right.color = BLACK;
    }

    /**
     * 获取树中元素的个数
     *
     * @return
     */
    public int size() {
        return n;
    }

    public Value get(Key key) {
        return get(root, key);
    }

    /**
     * 从指定的树x中找到key对应的值
     *
     * @param x
     * @param key
     * @return
     */
    private Value get(Node<Key, Value> x, Key key) {
        if (x == null) {
            return null;
        }
        int cmp = key.compareTo(x.key);
        if (cmp < 0) {
            return get(x.left, key);
        } else if (cmp > 0) {
            return get(x.right, key);
        } else {
            return x.value;
        }
    }

    /**
     * 在整个树上完成插入操作
     *
     * @param key
     * @param value
     */
    public void put(Key key, Value value) {
        root = put(root, key, value);
        root.color = BLACK;
    }

    /**
     * 在指定的树中，完成插入操作，并返回添加元素后的新树
     *
     * @param h
     * @param key
     * @param value
     * @return
     */
    private Node<Key, Value> put(Node<Key, Value> h, Key key, Value value) {
        if (h == null) {
            // 标准的插入操作，和父节点用红链接相连
            n++;
            return new Node<>(null, null, key, value, RED);
        }
        // 比较要插入的键和当前节点的键
        int cmp = key.compareTo(h.key);
        // 当前插入的key小于当前节点的key
        if (cmp < 0) {
            // 继续寻找左子树插入
            h.left = put(h.left, key, value);
        } else if (cmp > 0) {
            // 当前插入的key大于当前节点的key
            // 继续寻找节点的右子树插入
            h.right = put(h.right, key, value);
        } else {
            // key相等，直接替换
            h.value = value;
        }
        // 如果当前节点的右连接是红色，左链接是黑色，需要左旋
        if (isRed(h.right) && !isRed(h.left)) {
            h = rotateLeft(h);
        }
        // 如果当前结点的左子节点和左子节点的左子节点都是红色，就需要右旋
        if (isRed(h.left) && isRed(h.left.left)) {
            h = rotateRight(h);
        }
        // 如果当前结点的左链接和右连接都是红色，则需要变色
        if (isRed(h.left) && isRed(h.right)) {
            flipColors(h);
        }
        // 返回当前节点
        return h;
    }

    private static class Node<Key, Value> {
        public Node<Key, Value> left;
        public Node<Key, Value> right;
        public Key key;
        public Value value;
        /**
         * 如果指向它的链接是红色的，那么该变量的值为true，
         * 如果链接是黑色 的，那么该变量的值为false
         */
        public boolean color;

        public Node(Node<Key, Value> left, Node<Key, Value> right, Key key, Value value, boolean color) {
            this.left = left;
            this.right = right;
            this.key = key;
            this.value = value;
            this.color = color;
        }
    }

}

class Test15 {
    public static void main(String[] args) {
        RedBlackTree<Integer, String> tree = new RedBlackTree<>();
        tree.put(4, "二哈");
        tree.put(1, "张三");
        tree.put(3, "李四");
        tree.put(5, "王五");
        System.out.println(tree.size());
    }

}
