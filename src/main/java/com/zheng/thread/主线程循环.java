package com.zheng.thread;

/**
 * Created by zhenghui on 2017/9/7.
 * 主线程：UI界面和Main函数均为主线程，除了“不包含在Thread里面的程序”均可 视为主线程。
 * 子线程：被Thread包含的“方法体”或者“委托”， Thread thread = new Thread(new ThreadStart(delegate{}));里面均视为子线程。
 */
public class 主线程循环 {
    /**
     * 子线程循环2次，主线程循环5次，如此循环5次
     *
     * @param args
     */
    public static void main(String args[]) {
        final Business business = new Business();
        new Thread(new Runnable() {
            public void run() {
                for (int i = 0; i < 5; i++) {//如此循环5次
                    try {
                        business.sub(i);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();

        for (int i = 0; i < 5; i++) {//如此循环5次
            try {
                business.main(i);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

class Business {
    private boolean bool = true;

    /**
     * 接替子线程运行后执行，打印5次
     * @param loop
     * @throws InterruptedException
     */
    public synchronized void main(int loop) throws InterruptedException {
        while (bool) {
            wait();
        }
        for (int i = 0; i < 5; i++) {//主线程循环5次
            System.out.println("main thread seq of " + i + ", loop of " + loop);
            //打印五次
        }
        bool = true;
        notify();
    }

    /**
     * 先执行子线程，打印2次后，bool变为false线程阻塞等待，此时由主线程接替
     * @param loop
     * @throws InterruptedException
     */
    public synchronized void sub(int loop) throws InterruptedException {
        while (!bool) {
            wait();
        }
        for (int i = 0; i < 2; i++) {//子线程循环2次
            System.out.println("sub thread seq of " + i + ", loop of " + loop);
        }
        bool = false;
        notify();
    }

}
