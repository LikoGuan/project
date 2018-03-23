package javabase.grammar;

/**
 * Created by huazhao on 16/7/1.
 */

/**
 * 类加载时,执行静态代码块和静态成员变量的初始化,只执行一次; 按声明顺序执行
 * 类初始化时,先初始化声明的成员变量(按声明顺序执行),再调用构造函数
 */
public class TestStatic {
    //静态代码块
    static {
        objCount = 0;
    }

    //静态成员
    private static int objCount = -1;

    private String strVar;

    private Integer integerVar = new Integer(10);

    TestStatic() {
        strVar = "test_static";
        integerVar = 1;
        objCount ++;
    }

    //静态方法
    private static void printStatic() {
        System.out.println("objCount: " + objCount);
        //System.out.println("strVar: " + strVar); error
    }

    public void printMember() {
        System.out.println("objCount: " + objCount);
        System.out.println("strVar: " + strVar);
    }

    public static void main(String[] argv) {
        TestStatic ts1 = new TestStatic();
        ts1.printMember();
        ts1.printStatic();

        TestStatic ts2 = new TestStatic();
        ts2.printStatic();
    }
}
