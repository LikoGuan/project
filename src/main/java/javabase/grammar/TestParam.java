package javabase.grammar;

/**
 * Created by huazhao on 16/7/1.
 */

import javabase.MyClass;

/**
 * 函数参数传递方式: 基本类型传值, 对象传引用
 * 函数参数不定长参数: 不定长参数在函数定义中只能放在最后
 */
public class TestParam {
    void changeBasicType(int data) {
        data ++;
    }

    void changeObject(MyClass myClass) {
        myClass.setMember(100);
    }

    void print(int intVar, String... args) {
        System.out.println(intVar);
        for (String arg : args) {
            System.out.println(arg);
        }
    }

    public static void main(String[] argv) {
        TestParam tp = new TestParam();

        int intVar = 10;
        tp.changeBasicType(intVar);
        System.out.println("after passing to function, intVar: " + intVar);

        MyClass myClass = new MyClass();
        myClass.setMember(0);
        tp.changeObject(myClass);
        System.out.println("after passing to function, myClass.getMember: " + myClass.getMember());

        tp.print(1, "hello", "Mr.", "Guan");
    }
}
