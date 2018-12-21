package javabase.multithread;

/**
 * Created by likoguan on 12/11/18.
 */
public class OldConcurrency {
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
        public Queue(int size) {
            this.size = size;
            this.index = -1;
            array = new int[size];
        }

        public synchronized void put(int x) throws InterruptedException {
            while (index + 1 >= size) {
                wait();
                System.out.println("put wake from notify");
            }

            if (index == -1) {
                notifyAll();
                //notify();
            }

            array[++index] = x;
        }

        public synchronized int get() throws InterruptedException {
            while (index < 0) {
                wait();
                System.out.println("get wake from notify");
            }

            if (index == size - 1) {
                notifyAll();
                //notify();
            }

            return array[index--];
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


