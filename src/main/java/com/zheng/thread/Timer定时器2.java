package com.zheng.thread;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by zhenghui on 2017/9/7.
 * Timer：jdk中提供的一个定时器工具
 * Timer timer = new Timer().schedule(new TimerTask(){public void run(){System.out.println("Time's up!"); timer.cancel();}}, sec*1000);
 * 定时器实现简易红绿灯
 */
public class Timer定时器2 {
    static int i = 5;
    static int j = 0;

    /**
     * @param args
     */
    public static void main(String args[]) {
        Timer timer = new Timer();
        timer.schedule(new MyTimerTask(), 1000);
    }

    static class MyTimerTask extends TimerTask {
        public void run() {
            String name = j == 0 ? "红" : j == 1 ? "绿" : j == 2 ? "黄" : "红";
            System.out.println(name + "亮了" + i + "秒");
            i--;
            if (i == 0) {
                j = (j == 0) ? 1 : (j == 1) ? 2 : (j == 2) ? 0 : j;
                i = 5;
            }
            new Timer().schedule(new MyTimerTask(), 1000);
        }
    }
}