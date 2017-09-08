package com.zheng.thread;

/**
 * Created by zhenghui on 2017/9/7.
 * wait()：导致当前的线程等待,直到其他线程调用此对象的 wait与notify()方法或 notifyAll()方法。
 * wait与notify()：唤醒在此对象监视器上等待的某一个线程.具体是哪一个可以认为是不确定的。
 * notifyAll()：唤醒在此对象监视器上等待的所有线程。
 */
public class wait与notify {
    /**
     * 当num为0时，decrease线程阻塞，increase线程就绪执行
     * 当num不为0时，increase线程阻塞，decrease线程就绪执行
     */
    public static void main(String[] args) {
        final Math math = new Math();
        for (int i = 0; i < 3; i++) {
            new Thread(new Runnable() {
                public void run() {
                    for (int i = 0; i < 10; i++) {
                        math.increase();
                        System.out.println(Thread.currentThread().getName() + ":increase");
                    }
                }
            }).start();

            new Thread(new Runnable() {
                public void run() {
                    for (int i = 0; i < 10; i++) {
                        math.decrease();
                        System.out.println(Thread.currentThread().getName() + ":decrease");
                    }
                }
            }).start();
        }
    }
}

class Math {
    private int num;

    public synchronized void increase() {
        while (num != 0) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        num++;
        System.out.println("[" + num + "]");
        notify();
    }

    public synchronized void decrease() {
        while (num == 0) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        num--;
        System.out.println("{" + num + "}");
        notify();
    }

}
