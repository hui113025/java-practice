package com.zheng.thread;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by zhenghui on 2017/9/7.
 * await()：导致当前线程等待，直到其他线程调用该Condition的signal()或signalAll()方法唤醒该线程。
 * signal()：唤醒在此Lock对象上等待的单个线程。
 * signalAll()：唤醒在此Lock对象上等待的所有线程。
 */
public class 锁3取放鸡蛋例子ReentrantLock实现 {
    public static void main(String args[]) {
        final Dish dish = new Dish();
        for (int i = 0; i < 10; i++) {
            new Thread(new Runnable() {
                private Object egg = new Object();

                public void run() {
                    dish.putEgg(egg);
                }
            }).start();

            new Thread(new Runnable() {
                public void run() {
                    dish.getEgg();
                }
            }).start();
        }
    }
}

/**
 * A线程专门往盘子里放鸡蛋
 * B线程专门从盘子里取鸡蛋
 * 每次放鸡蛋是互斥的，每次取鸡蛋也是互斥的
 * 情况1：盘子里没鸡蛋时，A线程就绪，B线程阻塞
 * 情况2：盘子里有鸡蛋时，B线程就绪，A线程阻塞
 */
class Dish {
    /**
     * 装鸡蛋的盘子
     */
    List<Object> eggs = new ArrayList<Object>();
    private Lock lock = new ReentrantLock();// 锁对象
    private Condition condition = lock.newCondition();

    /**
     * 放鸡蛋
     */
    public void putEgg(Object egg) {
        lock.lock();
        try {
            while (eggs.size() == 1) {
                try {
                    condition.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            eggs.add(egg);// 往盘子里放鸡蛋
            condition.signalAll();// 唤醒阻塞队列的某线程到就绪队列
            System.out.println("放入鸡蛋");
        } finally {
            lock.unlock();
        }
    }

    /**
     * 取鸡蛋
     */
    public Object getEgg() {
        lock.lock();
        try {
            while (eggs.size() == 0) {
                try {
                    condition.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            Object egg = eggs.get(0);
            eggs.clear();// 清空盘子
            condition.signalAll();// 唤醒阻塞队列的某线程到就绪队列
            System.out.println("拿到鸡蛋");
            return egg;
        } finally {
            lock.unlock();
        }
    }
}

