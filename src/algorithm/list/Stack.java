package algorithm.list;

/**
 * 栈结构
 */
public class Stack {

    /**
     * 首结点
     */
    private Node head;

    /**
     * 栈中元素个数
     */
    private int n;

    public Stack() {
        head = new Node(null, null);
        n = 0;
    }

    /**
     * 判断栈是否为空
     */
    public boolean isEmpty() {
        return n == 0;
    }

    /**
     * 获取栈中元素的个数
     */
    public int size() {
        return n;
    }

    /**
     * 弹栈
     */
    public String pop() {
        // 记录第一个元素
        Node oldFirst = head.next;
        if (oldFirst == null) {
            return null;
        }
        // 删除首个元素
        head.next = head.next.next;
        // 个数-1
        n--;
        return oldFirst.item;
    }

    /**
     * 压栈
     */
    public void push(String t) {
        // 记录旧的下一个节点
        Node oldNext = head.next;
        // 创建新的节点
        Node node = new Node(t, oldNext);
        // 个数+1
        n++;
        // 新的节点连接到head的下一个节点
        head.next = node;
    }

    private class Node {
        public String item;
        public Node next;

        public Node(String item, Node next) {
            this.item = item;
            this.next = next;
        }
    }
}

class Test6 {
    public static void main(String[] args) {
        Stack stack = new Stack();
        stack.push("a");
        stack.push("b");
        stack.push("c");
        stack.push("d");
        System.out.println(stack.size());
        System.out.println(stack.pop());
        System.out.println(stack.size());
        System.out.println(stack.isEmpty());
        stack.pop();
        stack.pop();
        stack.pop();
        System.out.println(stack.isEmpty());
        System.out.println(stack.size());
    }
}

class Test7 {
    public static void main(String[] args) {
        // 待匹配括号的字符串
        String str = "(哈哈(发的(官方)发给我个( 发(发(更好)发(功德符(发顺丰的( 跟我说)(阿发) 吧)ad发)不是是)) GV) 发";
        boolean isMatch = matchBrackets(str);
        System.out.println("匹配字符串括号结果：" + isMatch);
    }

    /**
     * 判断str中左右括号是否匹配
     *
     * @param str 待匹配的字符串
     */
    private static boolean matchBrackets(String str) {
        // 1. 创建一个栈来存储左括号
        Stack stack = new Stack();
        // 2. 从右往右遍历字符串，拿到每一个字符
        for (int i = 0; i < str.length(); i++) {
            // 拿到每一个字符串
            String currentChar = str.charAt(i) + "";
            // 3.判断该字符串是不是左括号，如果是，则放入栈中
            if (currentChar.equals("(")) {
                stack.push(currentChar);
                // 4. 判断该字符串是否是右括号，如果是，从栈中弹出一个元素t
            } else if (currentChar.equals(")")) {
                String t = stack.pop();
                // 判断t是否为null。如果为null说明没有左括号匹配，否则有左括号匹配
                if (t == null) {
                    return false;
                }
                // 如果不是，继续下一个循环
            }
        }
        // 循环完毕之后，判断栈中是否还有剩余的左括号，如果有，说明不匹配，如果没有，则匹配
        return stack.size() == 0;
    }
}
