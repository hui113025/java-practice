package com.zheng.example;


import org.apache.commons.lang3.SerializationUtils;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhenghui on 2018/8/28.
 */
public class TestExample {

    public static void main(String args[]) {
        TestExample example = new TestExample();
        example.method1();
        example.method2();
        example.method3();
        example.method4();
        example.method5();
    }

    public void method1() {
        long t1 = System.currentTimeMillis();
        int i = 100 * 64;
        System.out.println("计数：" + i + "，" + (System.currentTimeMillis() - t1));

        long t2 = System.currentTimeMillis();
        //100右移6位
        int j = 100 << 6;
        System.out.println("计数：" + j + "，" + (System.currentTimeMillis() - t2));
    }

    public void method2() {
        //数组求和 推荐使用
        int[] ints = {1, 3, 4};
        long t1 = System.currentTimeMillis();
        int sum1 = 0;
        for (int i = 0; i < ints.length; i++) {
            sum1 += ints[i];
        }
        System.out.println("求和：" + sum1 + "，" + (System.currentTimeMillis() - t1));

        //集合求和
        List<Integer> list = new ArrayList<Integer>();
        list.add(1);
        list.add(3);
        list.add(4);
        long t2 = System.currentTimeMillis();
        int sum2 = 0;
        for (int i = 0; i < list.size(); i++) {
            sum2 += list.get(i);
        }
        System.out.println("求和：" + sum2 + "，" + (System.currentTimeMillis() - t2));
    }

    public void method3() {
        System.out.println("枚举：" + House.BIG.getMsg());
    }

    public void method4() {
        try {
            Method method = TestExample.class.getMethod("reflexPrint");
            method.invoke(new TestExample());
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    public void method5() {
        StackTraceElement[] elements = new Throwable().getStackTrace();
        for (StackTraceElement element : elements) {
            System.out.println("栈：" + element.getClassName() + "->" + element.getMethodName());
        }
    }

    //反射打印
    public void reflexPrint() {
        System.out.println("反射例子");
    }

    class Person implements Serializable {
        private static final long serialVersionId = 123456789L;
        public final String name;

        public Person() {
            name = "序列化";
        }
    }

    //序列化
    void Serialize() {
        SerializationUtils.serialize(new Person());
    }

    //反序列化
    void Dserialize() {
    }

    //枚举 推荐结合注解使用
    enum House {
        BIG(1, "大"),
        SMALL(2, "小");

        House(int state, String msg) {
            this.state = state;
            this.msg = msg;
        }

        private int state;
        private String msg;

        public int getState() {
            return state;
        }

        public String getMsg() {
            return msg;
        }
    }
}
