package algorithm.tree;

/**
 * @Author: 杨德石
 * @Date: 2020/7/11 21:34
 * @Version 1.0
 */
public class Tree23 {

    /**
     * 根节点
     */
    private Node root;

    /**
     * 元素个数
     */
    private int n;

    public Tree23() {
        root = new Node();
        n = 0;
    }

    /**
     * 返回树的大小
     *
     * @return
     */
    public int size() {
        return n;
    }

    /**
     * 判断树是否为空
     *
     * @return
     */
    public boolean isEmpty() {
        return n == 0;
    }

    /**
     * 查找含有key的键值对
     *
     * @param key
     * @return 返回键值对中的value
     */
    public String find(Integer key) {
        // 获取根节点
        Node currentNode = root;
        int childNum;
        while (true) {
            if ((childNum = currentNode.findItem(key)) != -1) {
                // 说明key存在
                return currentNode.itemDatas[childNum].value;
            } else if (currentNode.isLeaf()) {
                // 如果当前节点不是要查询的key，并且没有叶子结点
                return null;
            } else {
                // 当前节点不是要查询的key，并且有叶子结点
                currentNode = getNextChild(currentNode, key);
            }
        }
    }

    /**
     * 在key条件下获取指定节点的子节点。
     *
     * @param node
     * @param key
     * @return 返回子节点
     */
    private Node getNextChild(Node node, Integer key) {
        for (int i = 0; i < node.getItemNum(); i++) {
            if (node.getData(i).key > key) {
                // 指定节点的key大于我们的key
                return node.getChild(i);
            } else if (node.getData(i).key.equals(key)) {
                // 直接返回node
                return node;
            }
        }
        // 没有找到，直接返回子节点的右节点/中节点/左节点
        return node.getChild(node.getItemNum());
    }

    /**
     * 插入数据到2-3查找树
     *
     * @param key
     * @param value
     */
    public void insert(Integer key, String value) {
        // 创建一个要插入的节点
        Data data = new Data(key, value);
        // 获取根节点
        Node currentNode = root;
        while (true) {
            if (currentNode.isLeaf()) {
                break;
            } else {
                // 不是叶子节点
                // 获取下一个子节点
                currentNode = getNextChild(currentNode, key);
                // 遍历当前节点，找到需要替换的位置
                for (int i = 0; i < currentNode.getItemNum(); i++) {
                    if (currentNode.getData(i).key.equals(key)) {
                        // 赋值
                        currentNode.getData(i).value = value;
                        return;
                    }
                }
            }
        }
        // 遍历完了，执行到了这里，说明key不存在
        // 如果要插入的key的节点已经满了，即3-结点插入
        if (currentNode.isFull()) {
            split(currentNode, data);
        } else {
            // 2-结点插入
            currentNode.insertData(data);
        }
    }

    /**
     * 裂变方法，用于裂变节点
     * 该过程并不是要基于现有节点改造
     * 而是构造一个新的节点去替换
     *
     * @param node 被裂变的结点
     * @param data 要保存的键值对
     */
    private void split(Node node, Data data) {
        Node parent = node.getParent();
        // 用于存放最大节点
        Node maxNode = new Node();
        // 用于存放最小结点
        Node midNode = new Node();
        if (data.key < node.getData(0).key) {
            maxNode.insertData(node.removeItem());
            midNode.insertData(node.removeItem());
            node.insertData(data);
        } else if (data.key < node.getData(1).key) {
            // 介于0和1之间
            maxNode.insertData(node.removeItem());
            midNode.insertData(data);
        } else {
            midNode.insertData(node.removeItem());
            maxNode.insertData(data);
        }
        if (node == root) {
            root = midNode;
        }
        midNode.connectChild(0, node);
        midNode.connectChild(1, maxNode);
        connectNode(parent, midNode);

    }

    /**
     * 连接node和parent
     * 该过程并不是要基于现有节点改造
     * 而是构造一个新的节点去替换
     *
     * @param parent
     * @param node
     */
    private void connectNode(Node parent, Node node) {
        // 要合并的节点的最左边的节点
        Data data = node.getData(0);
        if (node == root) {
            return;
        }
        // 父节点是3-节点
        if (parent.isFull()) {
            Node gParent = parent.getParent();
            Node newNode = new Node();
            Node temp1, temp2;
            Data itemData;
            if (data.key < parent.getData(0).key) {
                temp1 = parent.disconnectChild(1);
                temp2 = parent.disconnectChild(2);
                newNode.connectChild(0, temp1);
                newNode.connectChild(1, temp2);
                newNode.insertData(parent.removeItem());
                itemData = parent.removeItem();
                parent.disconnectChild(2);
                parent.insertData(itemData);
                parent.connectChild(0, node);
                parent.connectChild(1, newNode);
            } else if (data.key < parent.getData(1).key) {
                temp1 = parent.disconnectChild(0);
                temp2 = parent.disconnectChild(2);
                Node tempNode = new Node();

                newNode.insertData(parent.removeItem());
                newNode.connectChild(0, node.disconnectChild(1));
                newNode.connectChild(1, temp2);

                tempNode.insertData(parent.removeItem());
                tempNode.connectChild(0, temp1);
                tempNode.connectChild(1, node.disconnectChild(0));

                parent.insertData(node.removeItem());
                parent.connectChild(0, tempNode);
                parent.connectChild(1, newNode);
            } else {
                itemData = parent.removeItem();

                newNode.insertData(parent.removeItem());
                newNode.connectChild(0, parent.disconnectChild(0));
                newNode.connectChild(1, parent.disconnectChild(1));

                parent.insertData(itemData);
                parent.connectChild(0, newNode);
                parent.connectChild(1, node);
            }
            // 进行递归
            connectNode(gParent, parent);
        } else {
            Node temp1, temp2;
            if (data.key < parent.getData(0).key) {
                Node tempNode = parent.disconnectChild(1);
                temp1 = node.disconnectChild(0);
                temp2 = node.disconnectChild(1);
                parent.insertData(node.getData(0));

                parent.connectChild(0, temp1);
                parent.connectChild(1, temp2);
                parent.connectChild(2, tempNode);
            } else {
                parent.connectChild(1, node.disconnectChild(0));
                parent.connectChild(2, node.disconnectChild(1));
                parent.insertData(node.getData(0));
            }
        }
    }

    public void print() {
        root.print();
    }

    private static class Node {
        private static final int N = 3;
        /**
         * 父节点
         */
        public Node parent;

        /**
         * 子节点
         * 0代表左节点
         * 1代表中节点
         * 2代表右节点
         */
        public Node[] childNodes = new Node[N];

        /**
         * 结点保存的数据
         */
        public Data[] itemDatas = new Data[N - 1];

        /**
         * 节点保存的数据个数
         */
        public int itemCount = 0;

        /**
         * 输出数据
         */
        public void print() {
            for (int i = 0; i < itemCount; i++) {
                itemDatas[i].print();
            }
            System.out.println("/");
        }

        /**
         * 判断节点是否是叶子结点
         * 只要没有子节点，就是叶子结点
         *
         * @return
         */
        private boolean isLeaf() {
            return childNodes[0] == null;
        }

        /**
         * 判断节点数据是否存满
         * 只要itemCount的大小是2，那么就是存满了
         *
         * @return
         */
        private boolean isFull() {
            return itemCount == N - 1;
        }

        /**
         * 返回该节点的父节点
         *
         * @return
         */
        private Node getParent() {
            return parent;
        }

        /**
         * 连接子节点
         *
         * @param index 连接的位置（0代表左子树，1代表中子树，2代表右子树）
         * @param child 要连接的节点
         */
        private void connectChild(int index, Node child) {
            childNodes[index] = child;
            // 判断如果child不为空，指定一下父节点
            if (child != null) {
                child.parent = this;
            }
        }

        /**
         * 解除该节点与某个子节点之间的练习
         *
         * @param index 连接的位置（0代表左子树，1代表中子树，2代表右子树）
         * @return 解除的节点
         */
        private Node disconnectChild(int index) {
            Node temp = childNodes[index];
            childNodes[index] = null;
            return temp;
        }

        /**
         * 获取结点的左或右键值对
         *
         * @param index 0左 1右
         * @return
         */
        private Data getData(int index) {
            return itemDatas[index];
        }

        /**
         * 获取某位置的子树
         *
         * @param index 连接的位置（0代表左子树，1代表中子树，2代表右子树）
         * @return
         */
        private Node getChild(int index) {
            return childNodes[index];
        }

        /**
         * 返回节点中键值对的数量
         *
         * @return
         */
        public int getItemNum() {
            return itemCount;
        }

        /**
         * 寻找key在结点的位置
         *
         * @param key
         * @return 如果不存在就返回-1
         */
        private int findItem(Integer key) {
            for (int i = 0; i < itemCount; i++) {
                if (itemDatas[i] == null) {
                    break;
                } else if (itemDatas[i].key.equals(key)) {
                    return i;
                }
            }
            return -1;
        }

        /**
         * 向结点插入键值对，前提是节点未满
         *
         * @param data
         * @return 插入后的坐标
         */
        private int insertData(Data data) {
            itemCount++;
            for (int i = N - 2; i >= 0; i--) {
                if (itemDatas[i] == null) {
                    continue;
                } else {
                    if (data.key < itemDatas[i].key) {
                        // 要插入的数据小于当前的key
                        // 当前的key向右移，要插入的数据插入到现有位置
                        itemDatas[i + 1] = itemDatas[i];
                    } else {
                        // 不小于，直接插入到右边
                        itemDatas[i + 1] = data;
                        return i + 1;
                    }
                }
            }
            itemDatas[0] = data;
            return 0;
        }

        /**
         * 移除最后一个键值对（有右边的键值对就移除右边的，否则就移除左边的）
         *
         * @return 移除后的键值对
         */
        private Data removeItem() {
            // 获取最右边的数据
            Data temp = itemDatas[itemCount - 1];
            itemDatas[itemCount - 1] = null;
            itemCount--;
            return temp;

        }

    }

    /**
     * 数据节点
     */
    private static class Data {

        public Integer key;
        public String value;

        public Data(Integer key, String value) {
            this.key = key;
            this.value = value;
        }

        public void print() {
            System.out.println("/" + key + "---" + value);
        }

    }

}

class Test14 {
    public static void main(String[] args) {
        Tree23 tree = new Tree23();
        tree.insert(1, "A");
        tree.insert(2, "B");
        tree.insert(3, "C");
        tree.insert(4, "D");
        tree.insert(5, "E");
        tree.print();
    }
}
