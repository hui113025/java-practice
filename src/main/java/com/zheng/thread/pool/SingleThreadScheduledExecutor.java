package com.zheng.thread.pool;

import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Created by zhenghui on 2017/9/8.
 * Executors.newSingleThreadScheduledExecutor()：创建一个单线程化的支持定时的线程池，
 * 可以用一个线程周期性执行任务(比如周期7天，一次任务才用1小时,使用多线程就会浪费资源)。
 * shutdown()：允许之前已经提交但未执行或未完成的任务继续完成它，平滑的关闭ExecutorService，
 * 当此方法被调用时，ExecutorService停止接收新的任务并且等待已经提交的任务（包含提交正在执行和提交未执行）执行完成。
 * 当所有提交任务执行完毕，线程池即被关闭。
 */
public class SingleThreadScheduledExecutor {

    public static void main(String[] args) {
         /*
         * scheduleWithFixedDelay 是周期性的
         * 第二个参数1，虽然是1秒为周期，但是单线程线程池，如果上一个任务没执行完，那么会等2秒或者更
         */
        ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor();
        executor.scheduleWithFixedDelay(new Runnable() {
            public void run() {
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("current Thread name is " + Thread.currentThread().getName() + " time is : " + new Date().getTime());
            }
        }, 1, 1, TimeUnit.SECONDS);
        executor.shutdown();
    }
}
