package algorithm;

/**
 * @author lx
 */
public class Singleton {
    //1.静态变量    调用了非静态的构造器  将优先加载非静态,跳过静态
    //4.构造方法结束 ,赋值
    private static Singleton instance = new Singleton();

    //3.构造方法    此时x=y=1
    public Singleton() {
        x++;
        y++;
    }

    private static int x;
    //5.静态变量赋值  此时x=1,y=0
    private static int y = 0;

    //2.非静态变量
    private int z = 1;

    public static Singleton getInstance() {
        return instance;
    }

    public static void main(String[] args) {
        //6.main方法体     结果x=1,y=0
        getInstance();
    }
}