package com.zheng.pattern;

/**
 * Created by zhenghui on 2017/10/11.
 * 工厂方法模式:定义了一个创建对象的接口，但由子类决定要实例化的类是哪一个。工厂方法让类把实例化推迟到子类。
 * AbstractProduct：抽象产品
 * ConcreteProduct：抽象具体产品
 * Creator：含有抽象的工厂方法的类
 * factoryMethod()：abstract Product factoryMethod()
 * ConcreteCreator：实现factoryMethod()制造产品
 */
public class FactoryMethodPattern {

    public static void main(String[] args) {
        PizzaStore2 store = new NyPizzaStore();
        //定做NyCheesePizza披萨
        Pizza2 pizza = store.orderPizza("cheese");
        System.out.println(pizza.name + "定做成功！");
    }

}

/**
 * AbstractProduct:产品，不具体实现
 */
abstract class Pizza2 {
    String name;
    String dough; //面团
    String sauce; //作料

    void prepare() {
        System.out.println("开始准备" + name + " pizza 材料：" + dough + "，" + sauce + "！");
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
class NyCheesePizza extends Pizza2 {
    public NyCheesePizza() {
        name = "NyCheesePizza";
        dough = "面团";
        sauce = "作料";
    }

    @Override
    void cut() {
        System.out.println("NyCheesePizza 开始切片！");
    }
}


/**
 * Creator:一个类，实现所有操纵产品的方法，但不实现工厂方法
 */
abstract class PizzaStore2 {

    Pizza2 orderPizza(String type) {
        Pizza2 pizza = creatPizza(type);
        pizza.prepare();
        pizza.bake();
        pizza.cut();
        pizza.box();
        return pizza;
    }

    /**
     * 工厂方法 抽象的，依赖子类处理实现
     * abstract Product factoryMethod(String type);
     * 参数type可有可无
     *
     * @param type
     * @return
     */
    protected abstract Pizza2 creatPizza(String type);

}

/**
 * ConcreteCreator:实现factoryMethod(),以实际制造产品
 */
class NyPizzaStore extends PizzaStore2 {
    public NyPizzaStore() {
    }

    @Override
    Pizza2 orderPizza(String type) {
        return super.orderPizza(type);
    }

    @Override
    protected Pizza2 creatPizza(String type) {
        Pizza2 pizza = null;
        if (type.equals("cheese")) {
            pizza = new NyCheesePizza();
        }
        return pizza;
    }
}

