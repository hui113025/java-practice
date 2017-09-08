package com.zheng.thread;

/**
 * Created by zhenghui on 2017/9/6.
 * 线程睡眠sleep run毫秒级 顺序随机 同启动同睡眠
 */
public class Sleep睡眠 {
    public static void main(String[] args) {
        Runner runner = new Runner();
        Thread thread1 = new Thread(runner);
        Thread thread2 = new Thread(runner);
        Thread thread3 = new Thread(runner);
        thread1.start();
        thread2.start();
        thread3.start();
    }
}

class Runner implements Runnable{
    int i ;
    public void run(){
	    /*int i = 0;*/
        while(true){
            System.out.println(Thread.currentThread().getName() + "|number: "+i++);
            try{
                /**
                 * 这里出所以能出现一盹一盹0.5s的停顿，是因为每个线程启动后都是判断执行的，比如这里的while语句中循环
                 * 线程基本上是"同时"（实际上肯定有先后顺序，但都是毫秒级的甚至更小）启动，并且启动顺序是随机的
                 * 如果不是一个线程不断的睡眠，别说3个就算是1W个也是很快的全部执行完（见例子ThreadSleep）
                 * 因为"同时"启动，"同时"睡眠
                 */
                Thread.sleep(500);
            }
            catch (InterruptedException e){
                e.printStackTrace();
            }
            if(50 == i){
                break;
            }
        }
    }
}
