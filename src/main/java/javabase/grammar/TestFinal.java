package javabase.grammar;


/**
 * Created by huazhao on 16/7/1.
 */

import javabase.MyClass;

/**
 * final变量不能多次赋值,且必须在声明时或构造函数中赋值; final变量的含义是该变量所指向的地址不能变即不能再赋值; final变量指向的对象内容可以改变
 */
public class TestFinal {
    private final int intVar /*= n*/;

    private final MyClass classObj /*= new MyClass()*/;

    TestFinal() {
        intVar = 0;
        classObj = new MyClass();
    }

    public void change() {
        //intVar = 1; error
        //classObj = new MyClass(); error
        //intVar++; error
        classObj.setMember(2);
    }

    public void display() {
        System.out.println("intVar: " + intVar);
        System.out.println("classObj.memeber: " + classObj.getMember());
    }
}
