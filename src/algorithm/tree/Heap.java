package algorithm.tree;

/**
 * 堆结构
 */
public class Heap {

    /**
     * 存储元素
     */
    private Integer[] items;

    /**
     * 记录堆中的元素个数
     */
    private int n;

    public Heap(int capacity) {
        items = new Integer[capacity + 1];
        n = 0;
    }

    /**
     * 判断堆中索引i处的元素是否小于索引j处的元素
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
    private void swap(int i, int j) {
        int temp = items[i];
        items[i] = items[j];
        items[j] = temp;
    }

    /**
     * 判断堆中最大的元素，并返回这个最大元素
     *
     * @return
     */
    public Integer delMax() {
        // 获取最大值
        Integer max = items[1];
        // 交换索引1 处和索引n处的值
        swap(1, n);
        // 删除索引n处的值
        items[n] = null;
        // 个数-1
        n--;
        // 下沉
        sink(1);
        return max;
    }

    public int size() {
        return n;
    }

    /**
     * 往堆中插入一个元素
     *
     * @param item
     */
    public void insert(Integer item) {
        items[++n] = item;
        // 上浮
        swim(n);
    }

    /**
     * 使用上浮算法，使索引k处的元素能在堆中处于一个正确的位置
     *
     * @param k
     */
    private void swim(int k) {
        // 判断k是否大于1，大于1的情况下再上浮
        while (k > 1) {
            // 比较当前节点和父节点，如果父节点比当前结点小，那么就交换
            if (less(k / 2, k)) {
                swap(k / 2, k);
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
        sink(k, n);
    }

    /**
     * 使用下沉算法，使索引k处的元素能在堆中处于一个正确的位置
     *
     * @param k
     */
    public void sink(int k, int end) {
        // 判断当前是不是数组末尾
        while (k * 2 <= end) {
            // 找到子节点中的较大者
            int maxIndex;
            if (k * 2 + 1 <= end) {
                // 存在右子结点
                if (less(k * 2, k * 2 + 1)) {
                    // 左节点比右节点小
                    maxIndex = k * 2 + 1;
                } else {
                    maxIndex = k * 2;
                }
            } else {
                // 不存在右结点
                maxIndex = k * 2;
            }
            // 比较当前节点和子节点中的较大者，如果当前结点不小，就结束循环
            if (!less(k, maxIndex)) {
                break;
            }
            // 当前节点小，交换位置
            swap(k, maxIndex);
            k = maxIndex;
        }
    }

    public Integer get(int index) {
        return items[index];
    }

    /**
     * 根据数组构造堆
     *
     * @param arr
     */
    public void initHeap(Integer[] arr) {
        // 遍历数组，将数组中的元素添加到堆中
        for (int i = 0; i < arr.length; i++) {
            items[i + 1] = arr[i];
            n++;
        }
        // 从items的n/2位置遍历到1位置
        for (int i = n / 2; i > 0 ; i--) {
            sink(i, n);
        }
    }

//    public static void main(String[] args) {
//        Heap heap = new Heap(11);
//        heap.insert(5);
//        heap.insert(1);
//        heap.insert(2);
//        heap.insert(8);
//        heap.insert(7);
//        heap.insert(9);
//        heap.insert(11);
//        heap.insert(4);
//        heap.insert(6);
//        heap.insert(10);
//        heap.insert(3);
//        while (heap.size() > 0) {
//            int delValue = heap.delMax();
//            System.out.println(delValue);
//        }
//
//    }

    public static void sort(Integer[] arr) {
        // 构造堆
        // 创建一个比原数组大1的堆
        Heap heap = new Heap(arr.length);
        heap.initHeap(arr);
        // 构造堆
        int index = heap.size();
        while (index != 1) {
            heap.swap(1, index);
            index--;
            // 交换完了，下沉
            heap.sink(1, index);
        }
        // 堆中的数据已经有序，拷贝到arr中
        for (int i = 0; i < arr.length; i++) {
            arr[i] = heap.get(i + 1);
        }
    }

    public static void main(String[] args) {
        Integer[] arr = {3, 6, 1, 2, 9, 7, 8, 4, 5, 10, 11};
        sort(arr);
        for (int i = 0; i < arr.length; i++) {
            System.out.println(arr[i]);
        }
    }
}
