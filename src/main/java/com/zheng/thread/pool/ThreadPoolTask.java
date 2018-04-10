package com.zheng.thread.pool;

import java.util.concurrent.TimeUnit;

/**
 * @author wanghl
 * @Title: ${file_name}
 * @date 2018/4/8
 */
public class ThreadPoolTask implements Runnable {

    public ThreadPoolTask() {

    }

    @Override
    public void run() {

        try {
            TimeUnit.SECONDS.sleep(1);
            System.out.println("处理调用");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
