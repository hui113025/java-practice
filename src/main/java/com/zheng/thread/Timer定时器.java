package com.zheng.thread;

import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by zhenghui on 2017/9/7.
 * Timer：jdk中提供的一个定时器工具
 * Timer timer = new Timer().schedule(new TimerTask(){public void run(){System.out.println("Time's up!"); timer.cancel();}}, sec*1000);
 */
public class Timer定时器 {
    static class MyTimerTask1 extends TimerTask {
        public void run() {
            System.out.println("爆炸！！！");
            new Timer().schedule(new MyTimerTask2(), 2000);
        }
    }

    static class MyTimerTask2 extends TimerTask {
        public void run() {
            System.out.println("爆炸2！！！");
            new Timer().schedule(new MyTimerTask1(), 3000);
        }
    }

    public static void main(String[] args) {
        Timer timer = new Timer();
        timer.schedule(new MyTimerTask2(), 2000);
        while (true) {
            System.out.println(Calendar.SECOND);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
