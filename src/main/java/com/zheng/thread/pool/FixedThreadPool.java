package com.zheng.thread.pool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by zhenghui on 2017/9/8.
 * Executors.newFixedThreadPool(int nThreads)：创建一个定长线程池，可控制线程最大并发数，超出的线程会在队列中等待。
 * shutdown()：允许之前已经提交但未执行或未完成的任务继续完成它，平滑的关闭ExecutorService，
 * 当此方法被调用时，ExecutorService停止接收新的任务并且等待已经提交的任务（包含提交正在执行和提交未执行）执行完成。
 * 当所有提交任务执行完毕，线程池即被关闭。
 */
public class FixedThreadPool {
    public static void main(String[] args) {
        /*该方法返回一个固定数量的线程池，该方法的线程池数始终不变，当有一个任务提交时，
         * 若线程池中空闲，则立即执行，若没有，则会被暂缓在一个任务队列总等待有空闲的线程去执行 */
        ExecutorService executor = Executors.newFixedThreadPool(3);
        for (int i = 1; i <= 10; i++) {
            final int taskId = i;
            executor = ExecutorsUtil.getExecutor(executor, taskId);
        }
        executor.shutdown();// 任务执行完毕，关闭线程池
    }
}
