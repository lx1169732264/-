package algorithm.list;

/**
 * 顺序表，这里只允许存储String类型
 *
 * @Author: 杨德石
 * @Date: 2020/6/14 21:57
 * @Version 1.0
 */
public class SequenceList {

    /**
     * 存储元素的数组
     */
    private String[] eles;

    /**
     * 当前线性表元素的个数
     */
    private int n;

    public SequenceList(int capacity) {
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
        if (n == eles.length) {
            System.err.println("当前表已满");
            return;
        }
        if (index >= eles.length) {
            System.err.println("下标位置非法");
            return;
        }
        // 判断一下i是否合法
        if (index < 0 || index > n) {
            System.err.println("下标位置非法");
            return;
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
            System.err.println("当前表已满");
            return;
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
        return result;
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

class Test1 {
    public static void main(String[] args) {
        SequenceList list = new SequenceList(10);
        list.insert("刘备");
        list.insert("张飞");
        list.insert("关羽");
        list.insert("曹操");
        System.out.println(list.get(2));
        System.out.println(list.remove(0));
        System.out.println(list.get(0));
        list.clear();
        System.out.println(list.length());
    }
}
