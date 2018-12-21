package javabase.multithread;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * Created by huazhao on 16/7/1.
 */
public class TestCallable {
    public <T> List<T> getX(T value) {
        List<T> list = new ArrayList<>();
        list.add(value);
        return list;
    }

    public static void main(String[] args) {

        TestCallable tc = new TestCallable();
        List<Integer> list = tc.getX(null);
        Integer x = list.get(0);

//        Executor executor = Executors.newSingleThreadExecutor();
//        executor.execute(new Runnable() {
//            @Override
//            public void run() {
//                System.out.println("task is running!");
//            }
//        });
        Callable<Integer> task = new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                int sum = 0;
                for (int i=0; i<100; i++) {
                    sum += i;
                }
                try {
                    Thread.sleep(10000);
                } catch (InterruptedException e) {
                    System.out.println(e);
                }
                return sum;
            }
        };

        try {
            ExecutorService executorService = Executors.newSingleThreadExecutor();
            Future<Integer> future = executorService.submit(task);
            executorService.shutdown();
//            Future<Integer> future1 = executorService.submit(task);//shutdown后再提交新task会被拒绝

            if (!future.isDone()) {
                System.out.println(future.cancel(false));//false不会发出中断
            }
            Integer result = future.get();
        } catch (InterruptedException e) {

        } catch (CancellationException e) {//只要cancel过，get()方法立刻返回该异常
            System.out.println(e);
        } catch (ExecutionException e) {
            System.out.println(e);
        } catch (RejectedExecutionException e) {
            System.out.println(e);
        }
    }
}
