package com.zheng.proxy;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * Created by zhenghui on 2017/12/9.
 * CGLib 动态代理
 */
public class CGLibProxy implements MethodInterceptor {
    public <T> T getProxy(Class<T> cls){
        return (T) Enhancer.create(cls,this);
    }

    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        before();
        Object result = methodProxy.invokeSuper(o,objects);
        after();
        return result;
    }

    private void before(){
        System.out.println("before");
    }

    private void after(){
        System.out.println("after");
    }

    public static void main(String[] args){
        CGLibProxy cgLibproxy = new CGLibProxy();
        Man man = cgLibproxy.getProxy(ManImpl.class);
        man.look("CGLib 动态代理");
    }
}
