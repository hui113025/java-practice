package com.zheng.pattern;

/**
 * Created by zhenghui on 2017/9/28.
 * 策略模式
 * 定义算法族，分别封装起来，让他们之间可以互相替换，此模式让算法的变化独立与使用算法的客户。
 */
public class StrategyPattern {

    public static void main(String[] args) {
        //调用MallardDuck继承的performQuack、performFly方法，委托FlyBehavior、QuackBehavior对象处理，调用继承来的引用对象的quack()和fly()方法。
        Duck mallard = new MallardDuck();
        mallard.performQuack();
        mallard.performFly();

        //动态设定
        Duck model = new ModelDuck();
        model.performQuack();
        model.setFlyBehavior(new FlyRocketPowered());//运行时调用setter方法改变鸭子的行为
        model.performFly();
    }

}

class Duck {
    FlyBehavior flyBehavior;
    QuackBehavior quackBehavior;

    public void display() {

    }

    public void performFly() {
        flyBehavior.fly();
    }

    public void performQuack() {
        quackBehavior.quack();
    }

    public void swim() {
        System.out.println("游泳");
    }

    public Duck() {
    }

    public FlyBehavior getFlyBehavior() {
        return flyBehavior;
    }

    public void setFlyBehavior(FlyBehavior flyBehavior) {
        this.flyBehavior = flyBehavior;
    }

    public QuackBehavior getQuackBehavior() {
        return quackBehavior;
    }

    public void setQuackBehavior(QuackBehavior quackBehavior) {
        this.quackBehavior = quackBehavior;
    }
}

interface FlyBehavior {
    void fly();
}

interface QuackBehavior {
    void quack();
}

class FlyWithwings implements FlyBehavior {
    @Override
    public void fly() {
        System.out.println("我在飞！");
    }
}

class FlyNoWay implements FlyBehavior {
    @Override
    public void fly() {
        System.out.println("我不能飞！");
    }
}

/**
 * 会呱呱叫
 */
class Quack implements QuackBehavior {
    @Override
    public void quack() {
        System.out.println("呱呱叫！");
    }
}

/**
 * 不会叫
 */
class MuteQuack implements QuackBehavior {
    @Override
    public void quack() {
        System.out.println("不会叫！");
    }
}

/**
 * 吱吱叫
 */
class Squeak implements QuackBehavior {
    @Override
    public void quack() {
        System.out.println("吱吱叫！");
    }
}


/**
 * 设置绿头鸭
 */
class MallardDuck extends Duck {

    public MallardDuck() {
        flyBehavior = new FlyNoWay();
        quackBehavior = new Quack();
    }

    @Override
    public void display() {
        System.out.println("我是绿头鸭！");
    }

}

/**
 * 设置模型鸭
 */
class ModelDuck extends Duck {

    public ModelDuck() {
        quackBehavior = new Squeak();
        flyBehavior = new FlyNoWay();
    }

    @Override
    public void display() {
        System.out.println("我是模型鸭！");
    }

}

/**
 * 建立FlyBehavior类型
 */
class FlyRocketPowered implements FlyBehavior {
    @Override
    public void fly() {
        System.out.println("火箭动力飞行！");
    }
}

