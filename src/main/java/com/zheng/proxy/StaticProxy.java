package com.zheng.proxy;

/**
 * Created by zhenghui on 2017/12/9.
 * 静态代理
 */
public class StaticProxy implements Man {
    private Man man;

    public StaticProxy() {
        man = new ManImpl();
    }

    @Override
    public void look(String bookName) {
        before();
        man.look(bookName);
        after();
    }

    private void before(){
        System.out.println("before");
    }

    private void after(){
        System.out.println("after");
    }

    public static void main(String[] args){
        Man man = new StaticProxy();
        man.look("静态代理");
    }
}
