package algorithm.tree;


import algorithm.list.Queue;

/**
 * 二叉树
 */
public class BinaryTree {

    /**
     * 记录根结点
     */
    private Node root;

    /**
     * 记录树中的元素个数
     */
    private int n;

    public BinaryTree() {
    }

    /**
     * 向树中插入一个键值对
     *
     * @param key
     * @param value
     */
    public void put(Integer key, String value) {
        root = put(root, key, value);
    }

    /**
     * 给指定的数x上，添加一个键值对，并返回添加后的新数
     *
     * @param tree
     * @param key
     * @param value
     * @return
     */
    private Node put(Node tree, Integer key, String value) {
        if (tree == null) {
            // 直接把新结点当成根结点使用
            // 个数+1
            n++;
            return new Node(null, null, key, value);
        }
        // 新结点的key大于当前结点的key，继续找当前结点的右子结点
        if (key > tree.key) {
            tree.right = put(tree.right, key, value);
        } else if (key < tree.key) {
            // 新结点的key小于当前结点的key，继续找当前结点的左子结点
            tree.left = put(tree.left, key, value);
        } else {
            // 新结点的key等于当前结点的key
            tree.value = value;
        }
        return tree;
    }

    /**
     * 从树中找到对应的值
     *
     * @param key
     * @return
     */
    public String get(Integer key) {
        return get(root, key);
    }

    /**
     * 从指定的树x中，找出key对应的值
     *
     * @param tree
     * @param key
     * @return
     */
    private String get(Node tree, Integer key) {
        if (tree == null) {
            return null;
        }
        // 如果要查询的key大于当前节点的key。则继续查找当前节点的右子结点
        if (key > tree.key) {
            return get(tree.right, key);
        } else if (key < tree.key) {
            // 如果要查询的key小于当前节点的key。则继续查找当前节点的左子结点
            return get(tree.left, key);
        } else {
            // 要查找的key和当前结点的key相等，返回value
            return tree.value;
        }
    }

    private Node getNode(Node tree, Integer key) {
        if (tree == null) {
            return null;
        }
        // 如果要查询的key大于当前节点的key。则继续查找当前节点的右子结点
        if (key > tree.key) {
            return getNode(tree.right, key);
        } else if (key < tree.key) {
            // 如果要查询的key小于当前节点的key。则继续查找当前节点的左子结点
            return getNode(tree.left, key);
        } else {
            // 要查找的key和当前结点的key相等，返回value
            return tree;
        }
    }

    /**
     * 根据key，删除树中对应的键值对
     *
     * @param key
     */
    public void delete(Integer key) {
        root = delete(root, key);
    }

    private Node delete(Node tree, Integer key) {
        if (tree == null) {
            return null;
        }
        // 待删除的key大于当前节点的key，继续找当前节点的右子结点
        if (key > tree.key) {
            tree.right = delete(tree.right, key);
        } else if (key < tree.key) {
            tree.left = delete(tree.left, key);
        } else {
            // 待删除的key等于当前节点的key，说明当前结点就是要删除的结点
            // 1. 如果当前结点的右子树不存在，则直接返回当前结点的左子节点
            if (tree.right == null) {
                n--;
                return tree.left;
            }
            // 2. 如果当前结点的左子树不存在，则直接返回当前结点的右子节点
            if (tree.left == null) {
                n--;
                return tree.right;
            }
            // 3. 当前结点的左右子树都存在
            // 3.1 找到右子树中最小的结点
            Node minNode = tree.right;
            // 二叉查找树的左节点一定比右节点小，所以这里只需要遍历左节点
            if (minNode.left != null) {
                minNode = minNode.left;
            }
            // 到这里，就找到了当前节点右子树中最小的节点minNode
            // 3.2 删除右子树中最小的节点
            Node node = tree.right;
            while (node.left != null) {
                if (node.left.left == null) {
                    // 说明n的左节点就是我们要找的最小结点
                    node.left = null;
                } else {
                    node = node.left;
                }
            }
            // 到这里，最小结点已经被删除
            // 3.3 让被删除结点的左子树成为最小结点的左子树。让被删除结点的右子树，成为最小结点的右子树
            minNode.left = tree.left;
            minNode.right = tree.right;
            // 3.4 让被删除结点的父节点指向最小结点
            tree = minNode;
            // 个数-1
            n--;
        }
        return tree;
    }

    public int size() {
        return n;
    }

    /**
     * 前序遍历
     *
     * @return
     */
    public Queue preErgodic() {
        Queue keys = new Queue();
        preErgodic(root, keys);
        return keys;
    }

    private void preErgodic(Node tree, Queue keys) {
        if (tree == null) {
            return;
        }
        // 1.把当前结点的key放入到队列中
        keys.enqueue(tree.key + "");
        // 2.找到当前节点的左子树，如果不为空，递归遍历左子树
        if (tree.left != null) {
            preErgodic(tree.left, keys);
        }
        // 3.找到当前结点的右子树，如果不为空，递归遍历右子树
        if (tree.right != null) {
            preErgodic(tree.right, keys);
        }
    }

    /**
     * 中序遍历
     *
     * @return
     */
    public Queue midErgodic() {
        Queue keys = new Queue();
        midErgodic(root, keys);
        return keys;
    }

    private void midErgodic(Node tree, Queue keys) {
        if (tree == null) {
            return;
        }
        // 1.找到当前结点的左子树，如果不为空，递归遍历左子树
        if (tree.left != null) {
            midErgodic(tree.left, keys);
        }
        // 2.把当前结点的key放入到队列中
        keys.enqueue(tree.key + "");
        // 3.找到当前结点的右子树，如果不为空，递归遍历右子树
        if (tree.right != null) {
            midErgodic(tree.right, keys);
        }
    }

    /**
     * 后序遍历
     *
     * @return
     */
    public Queue afterErgodic() {
        Queue keys = new Queue();
        afterErgodic(root, keys);
        return keys;
    }

    private void afterErgodic(Node tree, Queue keys) {
        if (tree == null) {
            return;
        }
        // 1.找到当前结点的左子树，如果不为空，递归遍历左子树
        if (tree.left != null) {
            afterErgodic(tree.left, keys);
        }
        // 2.找到当前结点的右子树，如果不为空，递归遍历右子树
        if (tree.right != null) {
            afterErgodic(tree.right, keys);
        }
        // 3.把当前结点的key放入到队列中
        keys.enqueue(tree.key + "");
    }

//    /**
//     * 层序遍历
//     * 对于面向对象语言
//     *
//     * @return
//     */
//    public Queue layerErgodic() {
//        // 创建一个队列，存储每一层的节点
//        ArrayQueue<Node> nodes = new ArrayQueue<>(n);
//        // 创建一个队列，用于存储遍历的节点
//        Queue keys = new Queue();
//        // 将当前节点存储到nodes中
//        nodes.add(root);
//        // 遍历queue
//        while (!nodes.isEmpty()) {
//            // 出列
//            Node currentNode = nodes.remove(0);
//            // 把节点的key存入到keys中
//            keys.enqueue(currentNode.key + "");
//            // 如果当前节点的左子节点不为空，则把左子节点放入到队列中
//            if (currentNode.left != null) {
//                nodes.add(currentNode.left);
//            }
//            // 如果当前节点的右子节点不为空，把右子节点放到队列中
//            if (currentNode.right != null) {
//                nodes.add(currentNode.right);
//            }
//        }
//        return keys;
//    }

    /**
     * 层序遍历
     * 对于面向对象语言
     *
     * @return
     */
    public Queue layerErgodic() {
        // 创建一个队列，存储每一层的节点
        Queue nodes = new Queue();
        // 创建一个队列，用于存储遍历的节点
        Queue keys = new Queue();
        // 将当前节点存储到nodes中
        nodes.enqueue(root.key + "");
        // 遍历queue
        while (!nodes.isEmpty()) {
            // 出列
            String key = nodes.dequeue();
            Node currentNode = getNode(root, Integer.parseInt(key));
            // 把节点的key存入到keys中
            keys.enqueue(currentNode.key + "");
            // 如果当前节点的左子节点不为空，则把左子节点放入到队列中
            if (currentNode.left != null) {
                nodes.enqueue(currentNode.left.key + "");
            }
            // 如果当前节点的右子节点不为空，把右子节点放到队列中
            if (currentNode.right != null) {
                nodes.enqueue(currentNode.right.key + "");
            }
        }
        return keys;
    }

    /**
     * 计算最大深度
     *
     * @return
     */
    public int maxDepth() {
        return maxDepth(root);
    }

    private int maxDepth(Node tree) {
        if (tree == null) {
            return 0;
        }
        // 计算左右子树的最大深度
        int max = 0;
        int leftMax = 0;
        int rightMax = 0;
        // 计算左子树最大深度
        if (tree.left != null) {
            leftMax = maxDepth(tree.left);
        }
        // 计算右子树最大深度
        if (tree.right != null) {
            rightMax = maxDepth(tree.right);
        }
        // 将二者较大的一方赋值给max。当前树的最大深度就是max+1
        max = leftMax > rightMax ? leftMax + 1 : rightMax + 1;
        return max;
    }

    private static class Node {
        public Node left;
        public Node right;
        public Integer key;
        public String value;

        public Node(Node left, Node right, Integer key, String value) {
            this.left = left;
            this.right = right;
            this.key = key;
            this.value = value;
        }
    }
}

class Test11 {
    public static void main(String[] args) {
        /**
         * ABCDEFGH
         * 12345678
         */
        BinaryTree tree = new BinaryTree();
        tree.put(5, "E");
        tree.put(2, "B");
        tree.put(7, "G");
        tree.put(1, "A");
        tree.put(4, "D");
        tree.put(6, "F");
        tree.put(8, "H");
        tree.put(3, "C");
        Queue queue = tree.layerErgodic();
        System.out.println(tree.maxDepth());
        System.out.print(tree.get(Integer.parseInt(queue.dequeue())));
        System.out.print(tree.get(Integer.parseInt(queue.dequeue())));
        System.out.print(tree.get(Integer.parseInt(queue.dequeue())));
        System.out.print(tree.get(Integer.parseInt(queue.dequeue())));
        System.out.print(tree.get(Integer.parseInt(queue.dequeue())));
        System.out.print(tree.get(Integer.parseInt(queue.dequeue())));
        System.out.print(tree.get(Integer.parseInt(queue.dequeue())));
        System.out.print(tree.get(Integer.parseInt(queue.dequeue())));
    }
}
