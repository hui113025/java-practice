package com.zheng.design;

/**
 * Created by zhenghui on 2017/9/30.
 * 工厂模式之简单工厂：其实不是设计模式，是一种编程习惯
 */
public class SimpleFactoryModel {

    public static void main(String[] args) {
        SimplePizzaFactory simplePizzaFactory = new SimplePizzaFactory();
        PizzaStore pizzaStore = new PizzaStore(simplePizzaFactory);
    }

}

class SimplePizzaFactory {
    Pizza creatPizza(String type) {
        Pizza pizza = null;
        if (type.equals("cheese")) {
            pizza = new CheesePizza();
        }
        return pizza;
    }
}

/**
 * AbstractProduct:抽象产品，不具体实现
 */
abstract class Pizza {
    void prepare() {
        System.out.println("开始准备材料：加一些作料！");
    }

    void bake() {
        System.out.println("开始烘烤！");
    }

    void cut() {
        System.out.println("开始切片！");
    }

    void box() {
        System.out.println("开始装盒！");
    }
}

/**
 * ConcreteProduct:具体产品
 */
class CheesePizza extends Pizza {

}

class PizzaStore {
    SimplePizzaFactory factory;

    public PizzaStore(SimplePizzaFactory factory) {
        this.factory = factory;
    }

    Pizza orderPizza() {
        Pizza pizza = factory.creatPizza("cheese");
        pizza.prepare();
        pizza.bake();
        pizza.cut();
        pizza.box();
        return pizza;
    }

}

