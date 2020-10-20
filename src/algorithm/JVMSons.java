package algorithm;

/**
 * @author lx
 */
public class JVMSons extends JVMParent {

    //4.子类静态变量赋值
    public static int count1 = 5;

    //5.子类静态代码块
    static {
        System.out.println("son static" + count1);
    }

    //9.子类代码块
    {
        System.out.println("son no static code block :" + count);
    }

    //10.子类构造器
    JVMSons() {
        System.out.println("son init:" + count);
    }

    //1.找到main方法,但先不执行main的方法体,先加载main所在的类,JVMSons extends JVMParent,需要先加载JVMParent
    public static void main(String[] args) {
        //6.main方法体 调用了子类构造器将先创建父类
        JVMParent a = new JVMSons();
    }
}