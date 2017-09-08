package com.zheng.thread.pool;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Created by zhenghui on 2017/9/8.
 * Executors.newScheduledThreadPool(int corePoolSize)：创建一个定长线程池，
 * 支持定时（scheduleWithFixedDelay（）函数的initdelay 参数）及周期（delay 参数）任务执行。
 */
public class ScheduledThreadPool {

    public static void main(String[] args) {
        ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);
        // 5秒后执行任务  
        executor.schedule(new Runnable() {
            public void run() {
                System.out.println("爆炸");
            }
        }, 5, TimeUnit.SECONDS);
        // 5秒后执行任务，以后每2秒执行一次
        executor.scheduleAtFixedRate(new Runnable() {
            public void run() {
                System.out.println("#爆炸#");
            }
        }, 5, 2, TimeUnit.SECONDS);
    }
}
