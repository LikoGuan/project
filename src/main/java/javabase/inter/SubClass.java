package javabase.inter;

/**
 * Created by huazhao on 17/2/10.
 */
public class SubClass extends SuperClass {
    @Override
    public SubClass common(Object obj) {
        System.out.print("SubClass::common()");
        return null;
    }

    public static void main(String[] args) {
        SuperClass superClass = new SubClass();
        superClass.common("");
    }
}
