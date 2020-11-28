package algorithm.tree;

/**
 * @Author: 杨德石
 * @Date: 2020/7/9 21:34
 * @Version 1.0
 */
public class IndexMinPriorityQueue {

    private Double[] items;
    private int[] pq;
    private int[] qp;
    private int n;

    public IndexMinPriorityQueue(int capacity) {
        items = new Double[capacity + 1];
        pq = new int[capacity + 1];
        qp = new int[capacity + 1];
        n = 0;
        for (int i = 0; i < qp.length; i++) {
            qp[i] = -1;
        }
    }

    /**
     * 返回队列大小
     *
     * @return
     */
    public int size() {
        return n;
    }

    /**
     * 比较i和j处值的大小
     *
     * @param i
     * @param j
     * @return
     */
    private boolean less(int i, int j) {
        return items[pq[i]] < items[pq[j]];
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
     * 交换i和j处的值
     *
     * @param i
     * @param j
     */
    private void exch(int i, int j) {
        int temp = pq[i];
        pq[i] = pq[j];
        pq[j] = temp;
        qp[pq[i]] = i;
        qp[pq[j]] = j;
    }

    /**
     * 判断k对应的元素是否存在
     *
     * @param k
     * @return
     */
    public boolean contains(int k) {
        return qp[k] != -1;
    }

    /**
     * 最小元素关联的索引
     *
     * @return
     */
    public int minIndex() {
        return pq[1];
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
     * 使用下沉算法，使索引k处的元素能在堆中处于一个正确的位置
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
     * 往队列中插入一个元素，并关联索引
     *
     * @param i
     * @param t
     */
    public void insert(int i, double t) {
        // 判断一下当前索引是否存在元素
        if (contains(i)) {
            System.out.println("当前索引" + i + "已存在元素");
            return;
        }
        // 不存在，插入
        // 格式+1
        n++;
        // 把t存放到索引i处
        items[i] = t;
        // 使pq存放i这个索引
        pq[n] = i;
        // qp和pq是相反的，在qp的i处存放n这个数据
        qp[i] = n;
        // 元素存放完毕，上浮算法
        swim(n);
    }

    /**
     * 删除队列中最小元素，并返回该元素关联的索引
     *
     * @return
     */
    public Integer delMin() {
        // 最小元素的索引位置
        int minIndex = pq[1];
        // 交换pq中索引1处和索引n处的值
        exch(1, n);
        // 删除qp中索引pq[n]处的值
        qp[pq[n]] = -1;
        // 删除pq中索引n处的值
        pq[n] = -1;
        // 删除items中最小的元素
        items[minIndex] = null;
        n--;
        // 下浮
        sink(1);
        return minIndex;
    }

    /**
     * 把与索引i关联的元素修改为t
     *
     * @param i
     * @param t
     */
    public void changeItem(int i, double t) {
        items[i] = t;
        // 找到i在pq中的位置
        int k = qp[i];
        // 对pq[k]做下沉，让堆有序
        sink(k);
        // 对pq[k]做上浮，让堆有序
        swim(k);
    }

    /**
     * 删除索引i关联的元素
     *
     * @param i
     */
    public void delete(int i) {
        // 找出i在pq中的索引
        int k = qp[i];
        // 把pq中索引k处的值和索引n处的值交换
        exch(k, n);
        // 删除qp中索引pq[n]的值
        qp[pq[n]] = -1;
        // 删除pq中索引n处的值
        pq[n] = -1;
        // 删除items中i的位置的值
        items[i] = null;
        // 元素数量-1
        n--;
        sink(k);
        swim(k);
    }

}

//class Test13 {
//    public static void main(String[] args) {
//        Integer[] arr = {5, 1, 7, 8, 3, 4, 10, 2, 6, 11, 9, 0};
//        // 创建队列
//        IndexMinPriorityQueue queue = new IndexMinPriorityQueue(20);
//        // 插入
//        for (int i = 0; i < arr.length; i++) {
//            queue.insert(i, arr[i]);
//        }
////        System.out.println(queue.size());
//        // 获取最小值的索引
////        System.out.println(queue.minIndex());
//        // 测试修改
//        queue.changeItem(0, 12);
//        int minIndex = -1;
//        while (!queue.isEmpty()) {
//            minIndex = queue.delMin();
//            System.out.println(minIndex);
//        }
//    }
//}
