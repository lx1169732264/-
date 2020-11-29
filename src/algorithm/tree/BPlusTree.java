package algorithm.tree;

import java.util.ArrayList;
import java.util.List;

/**
 * B+树
 *
 * @Author: 杨德石
 * @Date: 2020/7/27 20:29
 * @Version 1.0
 */
public class BPlusTree<K extends Comparable<K>, V> {

    /**
     * 根节点
     */
    public BPlusNode<K, V> root;

    /**
     * 阶数，M值
     */
    public int order;

    /**
     * 叶子节点的链表头
     */
    public BPlusNode<K, V> head;

    /**
     * 树高
     */
    public int height = 0;

    public BPlusTree(int order) {
        this.order = order;
        root = new BPlusNode<>(true, true);
        head = root;
    }

    public V get(K key) {
        return root.get(key);
    }

    public void insertOrUpdate(K key, V value) {
        root.insertOrUpdate(key, value, this);
    }

    public void printBPlusTree() {
        this.root.printBPlusTree(0);
    }

    private static class BPlusNode<K extends Comparable<K>, V> {

        /**
         * 是否为叶子结点
         */
        private boolean isLeaf;

        /**
         * 是否为根节点
         */
        private boolean isRoot;

        /**
         * 父节点
         */
        private BPlusNode<K, V> parent;
        /**
         * 叶节点的前节点
         */
        private BPlusNode<K, V> previous;
        /**
         * 叶节点的后节点
         */
        private BPlusNode<K, V> next;
        /**
         * 节点的关键字列表
         */
        private List<Entry<K, V>> entries;
        /**
         * 子节点列表
         */
        private List<BPlusNode<K, V>> children;

        public BPlusNode(boolean isLeaf) {
            this.isLeaf = isLeaf;
            entries = new ArrayList<>();
            if (!isLeaf) {
                children = new ArrayList<>();
            }
        }

        public BPlusNode(boolean isLeaf, boolean isRoot) {
            this(isLeaf);
            this.isRoot = isRoot;
        }

        private V get(K key) {
            // 什么情况下需要结束递归？遍历到叶子节点的时候不需要递归
            // 如果是叶子结点
            if (isLeaf) {
                int low = 0;
                int high = entries.size() - 1;
                int mid = 0;
                int comp;
                while (low <= high) {
                    mid = (low + high) / 2;
                    comp = entries.get(mid).key.compareTo(key);
                    if (comp == 0) {
                        return entries.get(mid).value;
                    } else if (comp < 0) {
                        low = mid + 1;
                    } else {
                        high = mid + 1;
                    }
                }
            }

            // 如果传入的key小于节点最左边的key，则沿第一个子节点继续搜索
            if (key.compareTo(entries.get(0).key) < 0) {
                return children.get(0).get(key);
            } else if (key.compareTo(entries.get(entries.size() - 1).key) >= 0) {
                // 如果key大于等于节点最右边的key，沿最后一个子节点继续搜索
                return children.get(children.size() - 1).get(key);
            } else {
                // 否则。沿比key大的第一个子节点继续搜索
                int low = 0;
                int high = entries.size() - 1;
                int mid = 0;
                int comp;
                while (low <= high) {
                    // 计算中间下标
                    mid = (low + high) / 2;
                    // 判断中间结点的的key是否大于给定的key
                    comp = entries.get(mid).key.compareTo(key);
                    if (comp == 0) {
                        // 找着了，但是不一定是叶子结点
                        return children.get(mid + 1).get(key);
                    } else if (comp < 0) {
                        low = mid + 1;
                    } else {
                        high = mid - 1;
                    }
                }
                // 循环结束后，直接从low取出
                return children.get(low).get(key);
            }
        }

        /**
         * 向指定的树中插入键值对，如果不存在，则插入，如果存在，则更新
         *
         * @param key
         * @param value
         * @param tree
         */
        public void insertOrUpdate(K key, V value, BPlusTree<K, V> tree) {
            // 如果是叶子结点
            if (isLeaf) {
                // 不需要分裂，直接插入或更新
                if (contains(key) != -1 || entries.size() < tree.order - 1) {
                    // key存在并且不需要分裂
                    // 直接插入或更新
                    insertOrUpdate(key, value);
                    if (tree.height == 0) {
                        tree.height = 1;
                    }
                    return;
                }
                // 需要分裂
                // 分裂成左右两个节点
                BPlusNode<K, V> left = new BPlusNode<>(true);
                BPlusNode<K, V> right = new BPlusNode<>(true);
                // 设置连接
                if (previous != null) {
                    previous.next = left;
                    left.previous = previous;
                }
                if (next != null) {
                    next.previous = right;
                    right.next = next;
                }
                if (previous == null) {
                    tree.head = left;
                }
                // 构造左右节点的关系
                left.next = right;
                right.previous = left;
                previous = null;
                next = null;
                // 左右节点构造完毕
                // 复制原节点关键字到分裂出来的新节点
                copy2Nodes(key, value, left, right, tree);
                // 如果存在父节点
                if (parent != null) {
                    // 调整子父级关系
                    int index = parent.children.indexOf(this);
                    parent.children.remove(this);
                    left.parent = parent;
                    right.parent = parent;
                    parent.children.add(index, left);
                    parent.children.add(index + 1, right);
                    parent.entries.add(right.entries.get(0));
                    entries = null;
                    children = null;

                    // 从父节点插入或更新
                    parent.updateInsert(tree);
                    parent = null;
                } else {
                    // 没有父节点
                    isRoot = false;
                    BPlusNode<K, V> parent = new BPlusNode<>(false, true);
                    tree.root = parent;
                    left.parent = parent;
                    right.parent = parent;
                    parent.children.add(left);
                    parent.children.add(right);
                    parent.entries.add(right.entries.get(0));
                    entries = null;
                    children = null;
                }
                return;
            }
            // 如果不是叶子结点
            // 如果key小于节点最左边的key，沿第一个子节点继续搜索
            if (key.compareTo(entries.get(0).key) < 0) {
                children.get(0).insertOrUpdate(key, value, tree);
            } else if (key.compareTo(entries.get(entries.size() - 1).key) >= 0) {
                // 如果key大于等于最右边的节点，沿最后一个子节点继续搜索
                children.get(children.size() - 1).insertOrUpdate(key, value, tree);
            } else {
                int low = 0;
                int high = entries.size() - 1;
                int mid = 0;
                int comp;
                while (low <= high) {
                    mid = (low + high) / 2;
                    comp = entries.get(mid).key.compareTo(key);
                    if (comp == 0) {
                        children.get(mid).insertOrUpdate(key, value, tree);
                        break;
                    } else if (comp < 0) {
                        low = mid + 1;
                    } else {
                        high = mid - 1;
                    }
                }
                if (low > high) {
                    children.get(low).insertOrUpdate(key, value, tree);
                }
            }
        }

        /**
         * 复制原结点到新节点的左右节点
         *
         * @param key
         * @param value
         * @param left
         * @param right
         * @param tree
         */
        private void copy2Nodes(K key, V value, BPlusNode<K, V> left, BPlusNode<K, V> right, BPlusTree<K, V> tree) {
            // 记录左节点要复制到的位置
            int leftSize = tree.order / 2;
            boolean b = false;
            for (int i = 0; i < entries.size(); i++) {
                if (leftSize != 0) {
                    leftSize--;
                    if (!b && entries.get(i).key.compareTo(key) > 0) {
                        // 没有被插入，并且当前的key比传入的key大
                        left.entries.add(new Entry<>(key, value));
                        b = true;
                        i--;
                    } else {
                        left.entries.add(entries.get(i));
                    }
                } else {
                    if (!b && entries.get(i).key.compareTo(key) > 0) {
                        // 没有被插入，并且当前的key比传入的key大
                        right.entries.add(new Entry<>(key, value));
                        b = true;
                        i--;
                    } else {
                        right.entries.add(entries.get(i));
                    }
                }
            }
            if (!b) {
                right.entries.add(new Entry<>(key, value));
            }
        }

        /**
         * 插入节点后中间结点的更新
         *
         * @param tree
         */
        private void updateInsert(BPlusTree<K, V> tree) {
            // 如果子节点数超出阶数吗，则需要分裂
            if (children.size() > tree.order) {
                // 分裂成左右两个节点
                BPlusNode<K, V> left = new BPlusNode<>(false);
                BPlusNode<K, V> right = new BPlusNode<>(false);
                // 计算一下分裂后两个节点的长度
                int leftSize = (tree.order + 1) / 2;
                int rightSize = (tree.order + 1) / 2;
                // 复制子节点到分裂出来的新结点，并更新key
                for (int i = 0; i < leftSize; i++) {
                    left.children.add(children.get(i));
                    children.get(i).parent = left;
                }
                for (int i = 0; i < rightSize; i++) {
                    right.children.add(children.get(leftSize + i));
                    children.get(leftSize + i).parent = right;
                }
                for (int i = 0; i < leftSize - 1; i++) {
                    left.entries.add(entries.get(i));
                }
                for (int i = 0; i < rightSize - 1; i++) {
                    right.entries.add(entries.get(leftSize + i));
                }

                if (parent != null) {
                    // 调整子父级关系
                    int index = parent.children.indexOf(this);
                    parent.children.remove(this);
                    left.parent = parent;
                    right.parent = parent;
                    parent.children.add(index, left);
                    parent.children.add(index + 1, right);
                    parent.entries.add(index, entries.get(leftSize - 1));
                    entries = null;
                    children = null;

                    parent.updateInsert(tree);
                    parent = null;
                } else {
                    // 没有父节点
                    // 没有父节点
                    isRoot = false;
                    BPlusNode<K, V> parent = new BPlusNode<>(false, true);
                    tree.root = parent;
                    tree.height = tree.height + 1;
                    left.parent = parent;
                    right.parent = parent;
                    parent.children.add(left);
                    parent.children.add(right);
                    parent.entries.add(right.entries.get(rightSize - 1));
                    entries = null;
                    children = null;
                }
            }
        }

        /**
         * 判断是否包含指定的key
         *
         * @param key
         * @return
         */
        private int contains(K key) {
            int low = 0;
            int high = entries.size() - 1;
            int mid = 0;
            int comp;
            while (low <= high) {
                mid = (low + high) / 2;
                comp = entries.get(mid).key.compareTo(key);
                if (comp == 0) {
                    return mid;
                } else if (comp < 0) {
                    low = mid + 1;
                } else {
                    high = mid - 1;
                }
            }
            return -1;
        }

        /**
         * 插入或更新当前键值对
         *
         * @param key
         * @param value
         */
        private void insertOrUpdate(K key, V value) {
            int low = 0;
            int high = entries.size() - 1;
            int mid = 0;
            int comp;
            while (low <= high) {
                mid = (low + high) / 2;
                comp = entries.get(mid).key.compareTo(key);
                if (comp == 0) {
                    entries.get(mid).value = value;
                } else if (comp < 0) {
                    low = mid + 1;
                } else {
                    high = mid - 1;
                }
            }
            if (low > high) {
                entries.add(low, new Entry<>(key, value));
            }
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append("isRoot: ");
            sb.append(isRoot);
            sb.append(", ");
            sb.append("isLeaf: ");
            sb.append(isLeaf);
            sb.append(", ");
            sb.append("keys: ");
            for (Entry<K, V> entry : entries) {
                sb.append(entry.key);
                sb.append(", ");
            }
            sb.append(", ");
            return sb.toString();
        }

        public void printBPlusTree(int index) {
            if (this.isLeaf) {
                System.out.print("层级：" + index + ",叶子节点，keys为: ");
                for (int i = 0; i < entries.size(); ++i) {
                    System.out.print(entries.get(i) + " ");
                }
                System.out.println();
            } else {
                System.out.print("层级：" + index + ",非叶子节点，keys为: ");
                for (int i = 0; i < entries.size(); ++i) {
                    System.out.print(entries.get(i) + " ");
                }
                System.out.println();
                for (int i = 0; i < children.size(); ++i) {
                    children.get(i).printBPlusTree(index + 1);
                }
            }
        }

    }

    private static class Entry<K, V> {
        public K key;
        public V value;

        public Entry(K key, V value) {
            this.key = key;
            this.value = value;
        }

        @Override
        public String toString() {
            return key + ":" + value;
        }
    }
}

class Test17 {
    public static void main(String[] args) {
        int order = 3;
        BPlusTree<String, String> tree = new BPlusTree<>(order);
        System.out.println("插入C");
        tree.insertOrUpdate("C", "C");
        System.out.println("插入N");
        tree.insertOrUpdate("N", "N");
        System.out.println("插入G");
        tree.insertOrUpdate("G", "G");
        System.out.println("插入A");
        tree.insertOrUpdate("A", "A");
        System.out.println("插入H");
        tree.insertOrUpdate("H", "H");
        System.out.println("插入E");
        tree.insertOrUpdate("E", "E");
        System.out.println("插入完毕");
        tree.printBPlusTree();
    }
}
