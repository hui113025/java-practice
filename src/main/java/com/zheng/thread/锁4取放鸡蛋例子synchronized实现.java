package com.zheng.thread;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhenghui on 2017/9/7.
 * wait()：导致当前的线程等待,直到其他线程调用此对象的 wait与notify()方法或 notifyAll()方法。
 * wait与notify()：唤醒在此对象监视器上等待的某一个线程.具体是哪一个可以认为是不确定的。
 * notifyAll()：唤醒在此对象监视器上等待的所有线程。
 */
public class 锁4取放鸡蛋例子synchronized实现 {
    public static void main(String args[]) {
        final Plate plate = new Plate();
        for (int i = 0; i < 10; i++) {
            new Thread(new Runnable() {
                private Object egg = new Object();

                public void run() {
                    plate.putEgg(egg);
                }
            }).start();

            new Thread(new Runnable() {
                public void run() {
                    plate.getEgg();
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
class Plate {
    /**
     * 装鸡蛋的盘子
     */
    List<Object> eggs = new ArrayList<Object>();

    /**
     * 放鸡蛋
     */
    public synchronized void putEgg(Object egg) {
        while (eggs.size() == 1) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        eggs.add(egg);// 往盘子里放鸡蛋
        notify();// 唤醒阻塞队列的某线程到就绪队列
        System.out.println("放入鸡蛋");
    }

    /**
     * 取鸡蛋
     */
    public synchronized Object getEgg() {
        while (eggs.size() == 0) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        Object egg = eggs.get(0);
        eggs.clear();// 清空盘子
        notify();// 唤醒阻塞队列的某线程到就绪队列
        System.out.println("拿到鸡蛋");
        return egg;
    }
}