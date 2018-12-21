package javabase.proxy;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by likoguan on 24/03/18.
 */

interface Deposit {
    void process(Integer account, BigDecimal money);
}

class Bank implements Deposit {

    private Map<Integer, BigDecimal> repo = new HashMap<Integer, BigDecimal>();

    public void process(Integer account, BigDecimal money) {
        if (checkRequest(account, money)) {
            BigDecimal saving = repo.get(account);
            if (saving == null) {
                saving = new BigDecimal(0);
            }
            repo.put(account, saving.add(money));
        } else {
            System.err.println("input account or money is illegal");
        }
    }

    private boolean checkRequest(Integer account, BigDecimal money) {
        return account > 0 && money.compareTo(BigDecimal.ZERO) > 0;
    }
}

class BankProxy implements Deposit {

    private final BigDecimal rate = new BigDecimal(0.03);

    private Bank bank = new Bank();

    public void process(Integer account, BigDecimal money) {
        money = preProcess(money);
        bank.process(account, money);
        postProcess();
    }

    private BigDecimal preProcess(BigDecimal money) {
        return money.subtract(money.multiply(rate));
    }

    private void postProcess() {
        System.out.println("success");
    }
}

public class StaticProxyTest {
    public static void main(String[] args) {
        Deposit deposit = new BankProxy();
        deposit.process(1, new BigDecimal(100));
    }
}
