package javabase.multithread;

import java.io.IOException;

/**
 * Created by likoguan on 20/11/18.
 */
public class TestSynchronized {
    public static int share = 0;

    public static synchronized void increase() {
        ++share;
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {

        }
        System.out.println("**share: " + share);
    }

    public static void decrease() {
        synchronized (TestSynchronized.class) {
            --share;
            System.out.println("share: " + share);
        }
    }

    public static void main(String[] args) {
        Runnable taskOne = new Runnable() {
            @Override
            public void run() {
                while (true) {
                    increase();
                    try {
                        Thread.sleep(5000);
                    } catch (InterruptedException e) {

                    }
                }
            }
        };

        Runnable taskTwo = new Runnable() {
            @Override
            public void run() {
                while (true) {
                    decrease();
                    try {
                        Thread.sleep(5000);
                    } catch (InterruptedException e) {

                    }
                }
            }
        };

        Thread t1 = new Thread(taskOne);
        Thread t2 = new Thread(taskTwo);
        t1.start();
        t2.start();

        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {

        }
    }
}
