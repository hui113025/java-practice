package com.zheng.thread.pool;

import java.util.concurrent.*;

/**
 * Created by zhenghui on 2017/9/8.
 * Callable：区别Runnable任务，有返回值，判断任务状态，设置超时时间处理
 * shutdown()：允许之前已经提交但未执行或未完成的任务继续完成它，平滑的关闭ExecutorService，
 * 当此方法被调用时，ExecutorService停止接收新的任务并且等待已经提交的任务（包含提交正在执行和提交未执行）执行完成。
 * 当所有提交任务执行完毕，线程池即被关闭。
 */
public class CallableAndFuture2 {
    public static void main(String[] args) throws InterruptedException, ExecutionException, TimeoutException {
        /* 创建单个线程的线程池，如果当前线程在执行任务时突然中断，则会创建一个新的线程替代它继续执行任务 */
        ExecutorService executor = Executors.newSingleThreadExecutor();
        Callable<Integer> callable = ExecutorsUtil.getCallable();
        Future<Integer> future = executor.submit(callable);
        Thread.sleep(10000);//模拟业务逻辑也在做自己的事情，同时开工
        int result = future.get(5000, TimeUnit.MILLISECONDS); //取得结果，同时设置超时执行时间为5s
        System.out.println(result);//
        executor.shutdown();// 任务执行完毕，关闭线程池
    }
}
