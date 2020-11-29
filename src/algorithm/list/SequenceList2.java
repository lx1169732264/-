package algorithm.list;

/**
 * 可变容量的顺序表
 *
 * @Author: 杨德石
 * @Date: 2020/6/14 21:57
 * @Version 1.0
 */
public class SequenceList2 {

    /**
     * 存储元素的数组
     */
    private String[] eles;

    /**
     * 当前线性表元素的个数
     */
    private int n;

    public SequenceList2(int capacity) {
        // 如果传入的个数小于1，则默认为1
        if(capacity < 1) {
            capacity = 1;
        }
        // 构造一个长度为capacity的顺序表
        eles = new String[capacity];
        n = 0;
    }

    /**
     * 置空线性表
     */
    public void clear() {
        n = 0;
        // 将数据整个置空
        eles = new String[eles.length];
    }

    /**
     * 判断线性表是否为空
     *
     * @return
     */
    public boolean isEmpty() {
        return n == 0;
    }

    /**
     * 获取线性表中元素个数
     *
     * @return
     */
    public int length() {
        return n;
    }

    /**
     * 读取并返回线性表中的第index个元素的值
     *
     * @param index
     * @return
     */
    public String get(int index) {
        // 判断一下下标是否合法
        if (index < 0 || index >= n) {
            System.err.println("当前元素不存在");
            return null;
        }
        return eles[index];
    }

    /**
     * 在线性表的第i个元素之前插入一个值为t的数据元素
     *
     * @param index
     * @param v
     */
    public void insert(int index, String v) {
        // 判断一下i是否合法
        if (index < 0 || index > n) {
            System.err.println("下标位置非法");
            return;
        }
        if (n == eles.length) {
            resize(eles.length * 2);
        }
        // 把index位置空出来，index位置以及后面的元素依次向后移动一位
        for (int i = n; i > index; i--) {
            eles[i] = eles[i - 1];
        }
        // 把v放到index处
        eles[index] = v;
        n++;
    }

    /**
     * 向线性表中添加一个元素t
     *
     * @param v
     */
    public void insert(String v) {
        // 判断一下元素个数是否已经超过了数组的最大容量
        if (n == eles.length) {
            resize(eles.length * 2);
        }
        eles[n++] = v;
    }

    /**
     * 删除并返回线性表中第index个元素
     *
     * @param index
     * @return
     */
    public String remove(int index) {
        if (index < 0 || index >= n) {
            System.out.println("当前要删除的元素不存在");
            return null;
        }
        // 获取要删除的元素
        String result = eles[index];
        // 把index后面的元素都向前移动一格
        for (int i = index; i < n - 1; i++) {
            eles[i] = eles[i + 1];
        }
        // 元素个数-1
        n--;
        // 判断一下当前元素数量已经不足数组大小的1/4，则重置数组大小
        if (n > 0 && n < eles.length / 4) {
            resize(eles.length / 2);
        }
        return result;
    }

    /**
     * 将现有的数组改变为容量为newSize的新数组
     *
     * @param newSize
     */
    private void resize(int newSize) {
        // 记录旧数组
        String[] temp = eles;
        // 创建新数组
        eles = new String[newSize];
        // 把就旧组中的元素拷贝到新数组
        for (int i = 0; i < n; i++) {
            eles[i] = temp[i];
        }
    }

    /**
     * 返回线性表中首次出现的指定的元素数据的索引
     *
     * @param v
     * @return
     */
    public int indexOf(String v) {
        return 0;
    }

}

class Test2 {
    public static void main(String[] args) {
        SequenceList2 list = new SequenceList2(0);
        list.insert("刘备");
        System.out.println(list.length());
        list.insert("张飞");
        System.out.println(list.length());
        list.insert("关羽");
        System.out.println(list.length());
        list.insert("曹操");
        System.out.println(list.length());
    }
}
