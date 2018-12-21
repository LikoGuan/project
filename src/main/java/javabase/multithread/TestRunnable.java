package javabase.multithread;

import java.util.concurrent.TimeUnit;

/**
 * Created by huazhao on 16/7/1.
 */
public class TestRunnable {
    public static void main(String[] args) {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                //Runnable无法抛出线程，无法直接返回结果
                System.out.println("child thread is running!");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {

                }
            }
        });
        thread.start();

        try {
            TimeUnit.MILLISECONDS.sleep(2000);
        } catch (InterruptedException e) {

        }
    }
}
