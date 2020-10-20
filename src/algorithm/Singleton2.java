package algorithm;

/**
 * @author lx
 */
public class Singleton2 {

    //3.构造方法    此时x=y=1
    public Singleton2() {
        x++;
        y++;
    }

    //1.静态变量赋值  此时x=y=0
    private static int x = 0;
    private static int y;
    //2.静态变量调用构造方法
    //4.构造器返回值赋值
    private static Singleton2 instance = new Singleton2();

    public static Singleton2 getInstance() {
        return instance;
    }

    public static void main(String[] args) {
        //5.main方法体       结果x=y=1
        getInstance();
    }
}
