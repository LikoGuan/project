package javabase.multithread;

import java.util.concurrent.BlockingQueue;

/**
 * Created by huazhao on 16/7/1.
 */
public class TestThread {

    class MyThread extends Thread {
        @Override
        public void run() {
            //...
        }
    }

    public static void main(String[] args) {
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        System.out.println("begin");
                        Thread.sleep(5000);
                        System.out.println("end");
                    } catch (InterruptedException ex) {
                        System.out.println("catch InterruptedException");
                        System.out.println("isInterrupted: " + Thread.currentThread().isInterrupted());
                        System.out.println("interrupted: " + Thread.interrupted());
                    }

//                    int sum = 0;
//                    int a, b, c, d, e, f;
//                    for (a=0; a<2100000000; a++) {
//                        sum = sum + 1;
//                        sum = sum / 2;
//                        for (b=0; b<2100000000; b++) {
//                            sum = sum * 1;
//                            sum = sum -2;
//                            for (c=0; c<2100000000; c++) {
//                                sum = sum + sum*2 -4;
//                                //System.out.println(Thread.currentThread().isInterrupted());
//                                System.out.println(Thread.interrupted());
//                                for (d=0; d<1100000000; d++) {
//                                    sum = sum - 2 + sum*4;
//                                }
//                            }
//                        }
//                    }
                }
            }
        });
        t.start();

        try {
            Thread.sleep(2000);
            t.interrupt();
            Thread.sleep(1000000);
        } catch (InterruptedException ex) {

        }
        return;
    }
}
