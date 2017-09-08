package com.zheng.thread;

/**
 * Created by zhenghui on 2017/9/7.
 * Thread.yield( )：线程让步，CPU时间让掉，同起点竞赛，抢到优先执行
 * Thread.activeCount()：存活的线程数
 * 主线程：UI界面和Main函数均为主线程，除了“不包含在Thread里面的程序”均可 视为主线程。
 * 子线程：被Thread包含的“方法体”或者“委托”， Thread thread = new Thread(new ThreadStart(delegate{}));里面均视为子线程。
 */
public class 主线程 {
    public static void main(String[] args) {

        // 同时启动1000个线程，去进行i++计算，看看实际结果
        for (int i = 0; i < 10000; i++) {
            new Thread(new Runnable() {
                public void run() {
                    Counter.getInstance().inc();
                }
            }).start();
        }
        //如果还有子线程在运行，主线程就让出cpu资源
        //直到所有子线程都运行完了，主线程再继续往下执行
        while (Thread.activeCount() > 1) {
            Thread.yield();
        }
        // 这里每次运行的值都有可能不同,可能为1000
        System.out.println("运行结果:Counter.count=" + Counter.getInstance().count);
    }
}

class Counter {

    public int count = 0;

    public void inc() {
        // 这里延迟100毫秒，使得结果明显
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
        }
        count++;
    }

    private static Counter counter;

    private Counter() {
    }

    public static Counter getInstance() {
        if (counter == null) {
            counter = new Counter();
        }
        return counter;
    }
}
