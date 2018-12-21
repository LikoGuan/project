package javabase.multithread;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by likoguan on 13/11/18.
 */
public class NewConcurrency {
    public static class Producer implements Runnable {
        private int num;
        private Queue queue;
        public Producer(int num, Queue queue) {
            this.num = num;
            this.queue = queue;
        }

        @Override
        public void run() {
            try {
                for (int m = 1; m <= 100000; m++) {
                    this.queue.put(0);
                    if (m % 10000 == 0) {
                        System.out.println("Producer " + this.num + " put " + m);
                    }
                }
                System.out.println("Producer " + this.num + " finished!");
            } catch (InterruptedException ex) {
                System.out.println("Producer " + this.num + " interrupted");
            }
        }
    }

    public static class Consumer implements Runnable {
        private int num;
        private Queue queue;
        public Consumer(int num, Queue queue) {
            this.num = num;
            this.queue = queue;
        }

        @Override
        public void run() {
            try {
                int n = 0;
                while (true) {
                    queue.get();
                    if (++n % 10000 == 0) {
                        System.out.println("Consumer " + this.num + " get " + n);
                        Thread.sleep(300);
                    }
                }
            } catch (InterruptedException ex) {
                System.out.println("Consumer " + this.num + " interrupted");
            }
        }
    }

    public static class Queue {
        private int size;
        private int index;
        private int[] array;
        private ReentrantLock lock;
        private Condition cond;
        public Queue(int size) {
            this.size = size;
            this.index = -1;
            array = new int[size];
            lock = new ReentrantLock();
            cond = lock.newCondition();
        }

        public void put(int x) throws InterruptedException {
            lock.lock();
            try {
                while (index + 1 >= size) {
                    cond.await();
                    System.out.println("put wake from notify");
                }

                if (index == -1) {
                    cond.signalAll();
                }

                array[++index] = x;
            } finally {
                lock.unlock();
            }
        }

        public int get() throws InterruptedException {
            lock.lock();
            try {
                while (index < 0) {
                    cond.await();
                    System.out.println("get wake from notify");
                }

                if (index == size - 1) {
                    cond.signalAll();
                }

                return array[index--];
            } finally {
                lock.unlock();
            }
        }
    }

    public static void main(String[] args) {
        Queue queue = new Queue(100);

        Thread[] pts = new Thread[5];
        for (int x=0; x<pts.length; x++) {
            pts[x] = new Thread(new Producer(x, queue));
        }

        Thread[] cts = new Thread[5];
        for (int y=0; y<cts.length; y++) {
            cts[y] = new Thread(new Consumer(y, queue));
        }

        for (int m=0; m<pts.length; m++) {
            pts[m].start();
        }
        for (int n=0; n<cts.length; n++) {
            cts[n].start();
        }

        try {
            while (true) {
                Thread.sleep(10000);
            }
        } catch (InterruptedException ex) {

        }
        System.out.println("main thread exit");
    }
}


