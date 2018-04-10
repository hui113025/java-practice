package com.zheng.jvm;

/**
 * Created by zhenghui on 2018/1/30.
 */
public class TestJvm {
    public static void main(String[] args){
        String str = new String("aa");
        Thread.dumpStack();
        System.out.println(str);
    }
}
