package com.zheng.pattern;

/**
 * Created by zhenghui on 2017/10/12.
 * 单例模式：确保一个类只有一个实例，并提供全局访问点。
 */
public class SingletonPattern {

    public static void main(String[] args) {

    }

}

/**
 * 急切实例化：急切创建实例，不用延迟实例化。
 */
class Singleton1 {
    private static Singleton1 uniqueInstance = new Singleton1();

    public Singleton1() {
    }

    public static Singleton1 getInstance() {
        return uniqueInstance;
    }
}

/**
 * 同步getInstance()方法：性能对应用不是很关键
 */
class Singleton2 {
    private static Singleton2 uniqueInstance;

    public Singleton2() {
    }

    public static synchronized Singleton2 getInstance() {
        if (uniqueInstance == null) {
            uniqueInstance = new Singleton2();
        }
        return uniqueInstance;
    }
}


/**
 * 双重检查加锁：在getInstance()中减少使用同步
 * 适用jdk1.4以上版本
 */
class Singleton3 {
    private volatile static Singleton3 uniqueInstance;

    public Singleton3() {
    }

    public static Singleton3 getInstance() {
        if (uniqueInstance == null) {
            synchronized (Singleton3.class) {
                if (uniqueInstance == null) {
                    uniqueInstance = new Singleton3();
                }
            }
        }
        return uniqueInstance;
    }
}