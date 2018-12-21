package javabase.multithread;

import java.util.concurrent.TimeUnit;

/**
 * Created by likoguan on 17/11/18.
 */
public class DeamonThread {
    public static void main(String[] args) {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                while (true) {
                    System.out.println("child thread is running ... ");
                    try {
                        TimeUnit.MILLISECONDS.sleep(200);
                    } catch (InterruptedException ie) {
                        System.err.println(ie);
                    }
                }
            }
        };

        //user thread 主线程退出后，子线程没有退出，因为子线程是默认的用户线程(user-thread)
//        Thread userThread = new Thread(runnable);
//        userThread.start();

        //daemon thread
        Thread daemonThread = new Thread(runnable);
        daemonThread.setDaemon(true);
        daemonThread.start();


        try {
            TimeUnit.MILLISECONDS.sleep(2000);
            System.out.println("main user-thread will exit ... ");
        } catch (InterruptedException ie) {
            System.err.println(ie);
        }
    }
}
