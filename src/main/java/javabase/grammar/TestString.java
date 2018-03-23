package javabase.grammar;

/**
 * Created by huazhao on 16/7/1.
 */

/**
 * String a = "hello"; JVM将其放入常量池,所以a和b其实指向同一个地址--常量池中"hello"的地址
 * String c = new String("hello"); 动态创建的对象,c指向运行时对象的地址
 * ==比较的是对象的地址(常量,内建非对象类型如int除外)
 * 对String的操作,比如取字串,字符串相加等都是生成一个新的String,原String不会改变,这就是String的不可变性
 */
public class TestString {
    public static void main(String[] argv) {
        String a = "hello";
        String b = "hello";
        System.out.println("a == b ? " + (a == b));

        String c = new String("hello");
        System.out.println("a == c ? " + (a == c));

        String d = new String("hello");
        System.out.println("d == c ? " + (d == c));

        String e = "hello" + "hello";
        String f = a + a;
        System.out.println("e == f ? " + (e == f));

        final String g = "hello";
        String h = g + "hello";
        System.out.println("e == h ? " + (e == h));

        System.out.println("*******************************");

        String str1 = "world";
        String str2 = str1;
        str1 = str1 + "hehe";
        System.out.println("str1 == str2 ? " + (str1 == str2));
    }
}
