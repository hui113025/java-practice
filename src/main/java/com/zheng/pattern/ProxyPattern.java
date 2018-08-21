package com.zheng.pattern;

/**
 * Created by zhenghui on 2018/8/21.
 * 参考：http://blog.anxpp.com/index.php/archives/489/
 * 代理模式
 */
public class ProxyPattern {

    public static void main(String args[]) {
        AbstractObject object = new ProxyObject();
    }

}

//抽象对象
interface AbstractObject {
    void method1();

    int method2();
}

//具体对象
class TargetObject implements AbstractObject {
    @Override
    public void method1() {
        System.out.println("具体对象方法");
    }

    @Override
    public int method2() {
        System.out.println("具体对象方法，有返回值");
        return 0;
    }
}

//代理对象
class ProxyObject implements AbstractObject {
    AbstractObject targetObject = new TargetObject();

    @Override
    public void method1() {
        targetObject.method1();
    }

    @Override
    public int method2() {
        return targetObject.method2();
    }
}
