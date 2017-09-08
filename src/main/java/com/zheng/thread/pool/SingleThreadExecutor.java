package com.zheng.thread.pool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Created by zhenghui on 2017/9/8.
 * Executors.newSingleThreadExecutor()：创建一个单线程化的线程池，它只会用唯一的工作线程来执行任务，保证所有任务按照指定顺序(FIFO, LIFO, 优先级)执行。
 * shutdown()：允许之前已经提交但未执行或未完成的任务继续完成它，平滑的关闭ExecutorService，
 * 当此方法被调用时，ExecutorService停止接收新的任务并且等待已经提交的任务（包含提交正在执行和提交未执行）执行完成。
 * 当所有提交任务执行完毕，线程池即被关闭。
 * awaitTermination(long timeout, TimeUnit unit)：接收timeout和TimeUnit两个参数，用于设定超时时间及单位。
 * 当等待超过设定时间时，会监测ExecutorService是否已经关闭，若关闭则返回true，否则返回false。一般情况下会和shutdown方法组合使用。
 */
public class SingleThreadExecutor {
    public static void main(String[] args) throws InterruptedException {
        /*创建单个线程的线程池，如果当前线程在执行任务时突然中断，则会创建一个新的线程替代它继续执行任务*/
        ExecutorService executor = Executors.newSingleThreadExecutor();
        for (int i = 1; i <= 10; i++) {
            final int taskId = i;
            executor = ExecutorsUtil.getExecutor(executor, taskId);
        }
        executor.shutdown();// 任务执行完毕，关闭线程池
        while (!executor.awaitTermination(1, TimeUnit.SECONDS)) {
            System.out.println("线程池没有关闭");
        }
        System.out.println("线程池已经关闭");
    }
}
