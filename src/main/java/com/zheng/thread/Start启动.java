package com.zheng.thread;

/**
 * Created by zhenghui on 2017/9/6.
 * 线程启动3种方式
 */
public class Start启动 {
    public static void main(String[] args) {
        ThreadImplements t1 = new ThreadImplements();
        ThreadExtends t2 = new ThreadExtends();
        t1.run();
        t2.start();
        run();
    }

    public static void run(){
        new Thread(new Runnable(){
            public void run(){
                for(int i = 0; i<100;i++){
                    System.out.println("**************"+i+"**************");
                }
            }
        }).start();
    }
}

class ThreadImplements implements Runnable {
    public void run() {
        for (int i = 0; i < 100; i++) {
            System.out.println("------------" + i + "------------");
        }
    }
}

class ThreadExtends extends Thread {
    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            System.out.println("++++++++++++" + i + "++++++++++++");
        }
    }
}

