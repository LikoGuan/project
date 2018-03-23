package javabase.grammar;

/**
 * Created by huazhao on 17/2/9.
 */
public class TestExtendSub extends TestExtend {
    public void func1() {
        System.out.print("TestExtendSub::func1()");
    }

    public static void main(String[] args) {
        TestExtendSub tes = new TestExtendSub();
        tes.func1();
    }
}
