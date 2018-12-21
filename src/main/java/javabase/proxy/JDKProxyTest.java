package javabase.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.math.BigDecimal;

interface DepositX {
    void process(Integer account, BigDecimal money);
}

class BankX implements DepositX {
    public void process(Integer account, BigDecimal money) {
        System.out.println(account + ", " + money);
    }
}

class InvocationHandlerImpl implements InvocationHandler {

    private BankX bankX = new BankX();

    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("before ...");
        method.invoke(this.bankX, args);
        System.out.println("after ...");
        return null;
    }
}

public class JDKProxyTest {

    public static void main(String[] args) {
        InvocationHandlerImpl invocationHandlerImpl = new InvocationHandlerImpl();
        DepositX depositX = (DepositX) Proxy.newProxyInstance(invocationHandlerImpl.getClass().getClassLoader(),
                BankX.class.getInterfaces(), invocationHandlerImpl);
        depositX.process(1, new BigDecimal(0.03));
    }
}
