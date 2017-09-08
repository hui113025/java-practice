package com.zheng.thread;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by zhenghui on 2017/9/7.
 * ReentrantLock：手动锁
 * synchronized：自动锁
 * synchronized是在JVM层面实现的，系统可以监控锁的释放与否，只对需要同步的使用，与wait()/wait与notify()/nitifyAll()一起使用时，比较方便。
 * ReentrantLock使用代码实现的，系统无法自动释放锁，需要在代码中finally子句中显式释放锁lock.unlock()，并发量比较高的情况。
 *
 */
public class 锁2ReentrantLock {
    public static void main(String[] args) {
        final Outputter1 output = new Outputter1();

        new Thread() {
            public void run() {
                output.output("abcdefghijklmnopqrstuvwxyz");
            }

            ;
        }.start();

        new Thread() {
            public void run() {
                output.output("0123456789");
            }

            ;
        }.start();
    }
}

class Outputter1 {
    private Lock lock = new ReentrantLock();// 锁对象

    public void output(String name) {
        lock.lock();// 得到锁
        try {
            for (int i = 0; i < name.length(); i++) {
                System.out.print(name.charAt(i));
            }
        } finally {
            lock.unlock();// 释放锁
        }
    }
}
