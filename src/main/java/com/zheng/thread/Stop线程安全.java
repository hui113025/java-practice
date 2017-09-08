package com.zheng.thread;

/**
 * Created by zhenghui on 2017/9/7.
 *Thread.stop()：线程停止，不安全，因此重写Stop类
 *Thread.interrupt()：线程中断，对于执行了sleep、wait、join方法的线程有效，否则无效，同时会抛出InterruptedException异常。
 */
public class Stop线程安全 {
    /**
     * 条件成立时，剩主线程
     * @param args
     */
    public static void main(String[] args) {
        Stop stop = new Stop();
        new Thread(stop).start();
        for (int i = 1; i <= 100; i++) {
            if (i == 10) stop.stop();
            System.out.println("main:" + i);
        }
    }
}

/**
 * 线程停止类
 */
class Stop implements Runnable {
    private volatile boolean flag = true;

    public void run() {
        int count = 0;
        while (flag) {
            System.out.println(Thread.currentThread().getName() + ":Thread running...." + count++);
        }
    }

    public void stop() {
        this.flag = false;
    }
}