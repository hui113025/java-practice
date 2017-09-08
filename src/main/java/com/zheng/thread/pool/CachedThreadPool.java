package com.zheng.thread.pool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by zhenghui on 2017/9/8.
 * Executors.newCachedThreadPool()：创建一个可缓存线程池，如果线程池长度超过处理需要，可灵活回收空闲线程，若无可回收，则新建线程。
 * shutdown()：允许之前已经提交但未执行或未完成的任务继续完成它，平滑的关闭ExecutorService，
 * 当此方法被调用时，ExecutorService停止接收新的任务并且等待已经提交的任务（包含提交正在执行和提交未执行）执行完成。
 * 当所有提交任务执行完毕，线程池即被关闭。
 */
public class CachedThreadPool {
    public static void main(String[] args) {
        /*返回一个可根据实际情况调整线程个数的线程池，不限制最大线程数量，若用空闲的线程则执行任务，
         * 若无任务则不创建线程。并且每一个空闲线程会在60s后自动回收*/
        ExecutorService executor = Executors.newCachedThreadPool();
        for (int i = 1; i <= 10; i++) {
            final int taskId = i;
            executor = ExecutorsUtil.getExecutor(executor, taskId);
        }
        executor.shutdown();// 任务执行完毕，关闭线程池
    }
}
