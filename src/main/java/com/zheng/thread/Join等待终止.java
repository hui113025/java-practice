package com.zheng.thread;

/**
 * Created by zhenghui on 2017/9/7.
 * Thread.join()：等待该线程终止。
 * 场景：主线程生成并起动了子线程，子线程进行大量的耗时运算，主线程于子线程之前结束，主线程处理完其他的事务后，需用到子线程的处理结果，
 * 也就是主线程需要等待子线程执行完成之后再结束，这个时候就要用到join()方法了。
 */
public class Join等待终止 {
    /**
     * join()方法后面的代码，只有等到子线程结束了才能执行。
     * @param args
     * @throws InterruptedException
     */
    public static void main(String[] args) throws InterruptedException {
        JoinThread j = new JoinThread();
        Thread t = new Thread(j);//子线程
        t.start();
        for (int i = 0; i < 100; i++) {
            if (i == 10) t.join();
            System.out.println("main:" + i);
        }
    }
}

class JoinThread implements Runnable {
    public void run() {
        for (int i = 0; i < 100; i++) {
            System.out.println(Thread.currentThread().getName() + ":Thread running....");
        }
    }
}
