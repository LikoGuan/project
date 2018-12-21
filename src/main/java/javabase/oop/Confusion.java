package javabase.oop;

/**
 * Created by likoguan on 11/12/18.
 */
public class Confusion {
    public static void main(String[] args) {
        SuperA x = new SubB();
        x.funcA();
    }
}

class SuperA {
    public void funcA() {
        System.out.println("SuperA.funcA()");
        this.funcB();
    }

    public void funcB() {
        System.out.println("SuperA.funcB()");
    }
}

class SubB extends SuperA {
    @Override
    public void funcB() {
        System.out.println("SubB.funcB()");
    }
}
