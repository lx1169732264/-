package algorithm.tree;

/**
 * 折纸问题
 *
 * @Author: 杨德石
 * @Date: 2020/7/7 21:38
 * @Version 1.0
 */
public class PaperFold {

    public static void main(String[] args) {
        Node node = initTree(3);
        print(node);
    }

    /**
     * 使用中序遍历打印出所有的节点
     * @param tree
     */
    private static void print(Node tree) {
        if(tree == null) {
            return;
        }
        print(tree.left);
        System.out.print(tree.item+",");
        print(tree.right);
    }

    /**
     * 构建深度为N的折痕树
     *
     * @param n 需要构建的树的深度
     */
    private static Node initTree(int n) {
        // 根节点
        Node root = null;
        // 循环n次
        for (int i = 0; i < n; i++) {
            if (i == 0) {
                // 第一次对折，创建根节点
                root = new Node("down", null, null);
            } else {
                // 不是第一次
                // 创建一个队列，将根节点存放到队列中
                PaperQueue queue = new PaperQueue();
                // 根节点入列
                queue.enqueue(root);
                // 遍历队列
                while (!queue.isEmpty()) {
                    // 从队列中取出一个节点
                    Node node = queue.dequeue();
                    // 3. 获取当前树的左右子树，重复第2步骤
                    Node left = node.left;
                    Node right = node.right;
                    // 判断左右子树是否为空，如果不为空，存入队列
                    if (left != null) {
                        queue.enqueue(left);
                    }
                    if (right != null) {
                        queue.enqueue(right);
                    }
                    // 1. 如果不是第一次对折，判断当前节点左右子树是不是空
                    if (node.left == null && node.right == null) {
                        // 2. 如果是空，就给当前节点构建一个左子树（down）和一个右子树（up）
                        node.left = new Node("down", null, null);
                        node.right = new Node("up", null, null);
                    }
                }

            }
        }
        return root;
    }

    // 定义结点类
    private static class Node {
        public String item;
        public Node left;
        public Node right;

        public Node(String item, Node left, Node right) {
            this.item = item;
            this.left = left;
            this.right = right;
        }
    }

    /**
     * 存放节点的队列
     */
    private static class PaperQueue {

        /**
         * 首结点
         */
        private QueueNode head;

        /**
         * 当前队列的元素个数
         */
        private int n;

        /**
         * 记录最后一个结点
         */
        private QueueNode last;

        public PaperQueue() {
            head = new QueueNode(null, null);
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
         * 从队列中拿出一个元素
         *
         * @return
         */
        public Node dequeue() {
            if (isEmpty()) {
                return null;
            }
            // 不是空，出列
            // 获取当前的第一个元素(对应图中的1元素)
            QueueNode oldFirst = head.next;
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
        public void enqueue(Node t) {
            // 判断last是否为null
            if (last == null) {
                // last为空，要插入的元素就是last
                last = new QueueNode(t, null);
                // 让首结点指向last
                head.next = last;
            } else {
                // 不是第一个元素
                // 取出旧结点（last）
                QueueNode oldLast = last;
                // 创建新的结点给last
                last = new QueueNode(t, null);
                // 让旧的last元素指向新的结点
                oldLast.next = last;
            }
            // 个数+1
            n++;
        }

        private class QueueNode {
            public Node item;
            public QueueNode next;

            public QueueNode(Node item, QueueNode next) {
                this.item = item;
                this.next = next;
            }
        }
    }
}
