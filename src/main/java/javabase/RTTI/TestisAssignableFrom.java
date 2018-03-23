package javabase.RTTI;

/**
 * Created by huazhao on 17/2/14.
 */
class Father {}
class Son extends Father {}

/**
 * ClassA.isAssignableFrom(ClassB) equals true means that
 *  if ClassA is either the same as, or is a superclass or superinterface of ClassB
 */
public class TestisAssignableFrom {
    public static void main(String[] args) {
        Father f1 = new Father();
        Son s1 = new Son();
        System.out.println("Father.isAssignableFrom(Son)="+Father.class.isAssignableFrom(Son.class));
        System.out.println("Son.isAssignableFrom(Father)="+Son.class.isAssignableFrom(Father.class));
    }
}
