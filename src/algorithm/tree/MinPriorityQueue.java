package algorithm.tree;

/**
 * 最小优先队列
 *
 * @Author: 杨德石
 * @Date: 2020/7/9 20:48
 * @Version 1.0
 */
public class MinPriorityQueue {

    /**
     * 存储元素
     */
    private Integer[] items;

    /**
     * 记录堆中的元素个数
     */
    private int n;

    public MinPriorityQueue(int capacity) {
        items = new Integer[capacity + 1];
        n = 0;
    }

    /**
     * 判断堆中索引i处的元素是否小于索引j处的元素
     *
     * @param i
     * @param j
     * @return
     */
    private boolean less(int i, int j) {
        return items[i] < items[j];
    }

    /**
     * 交换堆中索引i处和索引j处的值
     *
     * @param i
     * @param j
     */
    private void exch(int i, int j) {
        int temp = items[i];
        items[i] = items[j];
        items[j] = temp;
    }

    public int size() {
        return n;
    }

    public boolean isEmpty() {
        return n == 0;
    }

    /**
     * 上浮算法，使索引k处的元素能在堆中处于一个正确的位置
     *
     * @param k
     */
    private void swim(int k) {
        // 如果没有父结点，就不再上浮
        while (k > 1) {
            // 如果当前节点比父结点小，就交换
            if (less(k, k / 2)) {
                exch(k, k / 2);
            }
            k = k / 2;
        }
    }

    /**
     * 下沉算法
     *
     * @param k
     */
    private void sink(int k) {
        // 如果没有子结点，就不需要下沉
        while (k * 2 <= n) {
            // 找出子结点中最小值的索引
            int minIndex = 2 * k;
            // 如果有右结点，并且右结点小于左节点
            if (k * 2 + 1 <= n && less(k * 2 + 1, k * 2)) {
                minIndex = 2 * k + 1;
            }
            // 如果当前节点小于子节点中的最小值，则结束循环
            if (less(k, minIndex)) {
                break;
            }
            // 当前节点大，交换
            exch(minIndex, k);
            ;
            k = minIndex;
        }
    }

    /**
     * 插入方法
     *
     * @param item
     */
    public void insert(Integer item) {
        items[++n] = item;
        swim(n);
    }

    public Integer delMin() {
        // 取出最小值
        Integer min = items[1];
        // 交换最小值和最后一个值
        exch(1, n);
        // 删掉最后一个元素
        items[n] = null;
        // 元素个数-1
        n--;
        // 下沉
        sink(1);
        return min;
    }

}

class Test12 {
    public static void main(String[] args) {
        MinPriorityQueue queue = new MinPriorityQueue(11);
        queue.insert(5);
        queue.insert(1);
        queue.insert(2);
        queue.insert(8);
        queue.insert(7);
        queue.insert(9);
        queue.insert(11);
        queue.insert(4);
        queue.insert(6);
        queue.insert(10);
        queue.insert(3);
        while (queue.size() > 0) {
            int delValue = queue.delMin();
            System.out.println(delValue);
        }

    }
}
