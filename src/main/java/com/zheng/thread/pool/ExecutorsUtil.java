package com.zheng.thread.pool;

import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;

/**
 * Created by zhenghui on 2017/9/8.
 * java并发编程--Runnable Callable及Future
 * Runnable任务：不返回任务执行结果且不能抛出异常。
 * Callable任务：返回线程的执行结果，且能在无法正常计算时抛出异常。
 * 参考：http://www.cnblogs.com/MOBIN/p/6185387.html
 */
public class ExecutorsUtil {
    public static Random random = new Random();

    /**
     * Runnable任务
     * @param executor
     * @param taskId
     * @return
     */
    public static ExecutorService getExecutor(ExecutorService executor, final int taskId) {
        executor.execute(new Runnable() {
            public void run() {
                for (int j = 1; j <= 10; j++) {
                    try {
                        Thread.sleep(random.nextInt(1000));// 为了测试出效果，让每次任务执行都需要一定时间
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(Thread.currentThread().getName() + " is looping of " + j + " for task of " + taskId);
                }
            }
        });
        return executor;
    }

    /**
     * Callable
     * @return
     */
    public static Callable<Integer> getCallable() {
        Callable<Integer> callable = new Callable<Integer>() {
            public Integer call() {
                int sum = 0;
                for (int j = 1; j <= 10; j++) {
                    try {
                        Thread.sleep(random.nextInt(1000));// 为了测试出效果，让每次任务执行都需要一定时间
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(Thread.currentThread().getName() + " is looping of " + j);
                    sum += j;
                }
                return sum;
            }
        };
        return callable;
    }
}
