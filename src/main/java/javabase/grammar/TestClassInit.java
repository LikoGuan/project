package javabase.grammar;

import java.util.Random;

/**
 * Created by huazhao on 17/2/13.
 */
class InitWithStaticFinal {
    static final int staticFinal1 = 1;
    static final int staticFinal2 = TestClassInit.random.nextInt(1000);
    static {
        System.out.println("initialize InitWithStaticFinal");
    }
}

class InitWithStaticNonFinal1 {
    static int staticNonFinal = 2;
    static {
        System.out.println("initialize InitWithStaticNonFinal1");
    }
}

class InitWithStaticNonFinal2 {
    private static int staticNonFinal = 3;
    static {
        System.out.println("initialize InitWithStaticNonFinal2");
    }
}
public class TestClassInit {
    public static Random random = new Random(34);
    public static void main(String[] args) {
        Class init1 = InitWithStaticFinal.class;
        System.out.println(InitWithStaticFinal.staticFinal1);
        System.out.println(InitWithStaticFinal.staticFinal2);

        Class init2 = InitWithStaticNonFinal1.class;
        System.out.println(InitWithStaticNonFinal1.staticNonFinal);

        try {
            Class init3 = Class.forName("InitWithStaticNonFinal2");
        } catch (ClassNotFoundException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
