package javabase.RTTI;

/**
 * Created by huazhao on 17/2/12.
 */

interface A{

}
class B implements A{

}
class C extends B{

}
class D extends B{

}
class E {
}
public class TestInstanceOf {

    public static void main(String[] args) {
        A ab=new B();
        A ac=new C();
        B bc=new C();
        B bb=new B();
        C cc=new C();
        D dd=new D();
        //对象实现一个接口，用这个对象和这个接口进行instanceof判断，都为true。
        System.out.println("ab instanceof A="+(ab instanceof A));
        System.out.println("ac instanceof A="+(ac instanceof A));
        System.out.println("bc instanceof A="+(bc instanceof A));
        System.out.println("bb instanceof A="+(bb instanceof A));
        System.out.println("cc instanceof A="+(cc instanceof A));
        //对象和父类进行instanceof判断，都为true
        System.out.println("ab instanceof B="+(ab instanceof B));
        System.out.println("ac instanceof B="+(ac instanceof B));
        System.out.println("bc instanceof B="+(bc instanceof B));
        System.out.println("bb instanceof B="+(bb instanceof B));
        System.out.println("cc instanceof B="+(cc instanceof B));
        //对象和他的子类进行instanceof判断为false
        System.out.println("ab instanceof C="+(ab instanceof C));
        System.out.println("ac instanceof C="+(ac instanceof C));
        System.out.println("bc instanceof C="+(bc instanceof C));
        System.out.println("bb instanceof C="+(bb instanceof C));
        System.out.println("cc instanceof C="+(cc instanceof C));
        //不是一类 编译不通过
        //System.out.println("cc instanceof E="+(cc instanceof E));

        //向下转换
        B x=(B)ab;
        B y=(B)dd;
        D w=(D)y;
        //E z=(E)y;
    }
}