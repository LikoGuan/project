package javabase.multithread;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by huazhao on 16/7/1.
 */
public class TestThreadPool {

    private static final int COUNT_BITS = Integer.SIZE - 3;

    // runState is stored in the high-order bits
    private static final int RUNNING    = -1 << COUNT_BITS;
    private static final int SHUTDOWN   =  0 << COUNT_BITS;
    private static final int STOP       =  1 << COUNT_BITS;
    private static final int TIDYING    =  2 << COUNT_BITS;
    private static final int TERMINATED =  3 << COUNT_BITS;

    private static final AtomicInteger ctl = new AtomicInteger(ctlOf(RUNNING, 0));

    private static final int CAPACITY   = (1 << COUNT_BITS) - 1;



    // Packing and unpacking ctl
    private static int runStateOf(int c)     { return c & ~CAPACITY; }
    private static int workerCountOf(int c)  { return c & CAPACITY; }
    private static int ctlOf(int rs, int wc) { return rs | wc; }

    public static void main(String[] args) {
        System.out.println("running: " + RUNNING);

        int c = ctl.get();
        System.out.println(workerCountOf(c));

        ThreadPoolExecutor executor = new ThreadPoolExecutor(0,
                4,
                60,
                TimeUnit.SECONDS,
                new LinkedBlockingQueue<Runnable>(10));

            executor.execute(new Runnable() {
                public void run() {
                    try {
                        Thread.sleep(10000);
                    } catch (InterruptedException ie) {
                        ie.printStackTrace();
                    }
                }
            });

            try {
                Thread.sleep(10000);
            } catch (InterruptedException ie) {
                ie.printStackTrace();
            }
    }
}
