package algorithm;

/**
 * @author lx
 */
public class JVMParent {
    //2.父类静态变量width     count没有被赋值,只是进行了地址分配,跳过count
    public static int width = 100;

    public static int count;

    //3.父类静态代码块
    static {
        System.out.println("parent static's count:" + width);
    }

    //7.父类代码块
    {
        System.out.println("parent no static code block :" + count);
    }

    //8.父类构造器
    JVMParent() {
        System.out.println("parent init");
    }
}
