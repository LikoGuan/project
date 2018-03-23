package javabase.RTTI;

/**
 * Created by huazhao on 17/2/14.
 */
class Base {}
class Derived extends Base {}

/**
 * ClassA.isInstance(obj) equals true means that obj can be casted to the Type ClassA without exception
 */
public class TestIsInstance {
    public static void main(String[] args) {
        Base b1 = new Base();
        Derived d1 = new Derived();
        System.out.println("Base.isInstance(Base Object)"+Base.class.isInstance(b1));
        System.out.println("Base.isInstance(Derived Object)"+Base.class.isInstance(d1));
        System.out.println("Derived.isInstance(Base Object)"+Derived.class.isInstance(b1));
        System.out.println("Derived.isInstance(Derived Object)"+Derived.class.isInstance(d1));
    }
}
