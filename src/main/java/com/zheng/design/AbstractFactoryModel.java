package com.zheng.design;

/**
 * Created by zhenghui on 2017/10/12.
 * 抽象工厂模式：提供一个接口，用于创建相关或依赖对象的家族，而不需要明确指定具体类。
 * AbstractProduct：抽象产品
 * ConcreteProduct：具体产品
 * AbstractFactory：抽象工厂
 * ConcreteFactory：具体工厂
 * AbstractFamilyProduct：抽象产品族产品
 * AbstractFamilyConcreteProduct：抽象产品族具体产品
 */
public class AbstractFactoryModel {

    public static void main(String[] args) {
        PizzaIngredientFactory factory = new NyPizzaIngredientFactory();
        PizzaStore3 store = new NyPizzaStore3(factory);
        Pizza3 pizza = store.orderPizza("cheese");
        System.out.println(pizza.name + "定做成功！");
    }

}

/**
 * AbstractProduct:产品，不具体实现
 */
abstract class Pizza3 {
    String name;
    Dough dough; //面团
    Sauce sauce; //作料

    abstract void prepare();

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
class NyPizza extends Pizza3 {
    PizzaIngredientFactory factory; //原料工厂

    public NyPizza(PizzaIngredientFactory factory) {
        this.factory = factory;
    }

    @Override
    void prepare() {
        name = "NyPizza";
        dough = factory.createDough();
        sauce = factory.createSauce();
        System.out.println("开始准备" + name + "， 材料：" + dough.getName() + "，" + sauce.getName() + "！");
    }
}

/**
 * Creator:一个类，实现所有操纵产品的方法
 */
abstract class PizzaStore3 {
    abstract Pizza3 creatPizza(String type);

    Pizza3 orderPizza(String type) {
        Pizza3 pizza = creatPizza(type);
        pizza.prepare();
        pizza.bake();
        pizza.cut();
        pizza.box();
        return pizza;
    }
}

/**
 * ConcreteCreator:运用抽象工厂制造产品
 */
class NyPizzaStore3 extends PizzaStore3 {
    PizzaIngredientFactory factory = new NyPizzaIngredientFactory();

    public NyPizzaStore3(PizzaIngredientFactory factory) {
        this.factory = factory;
    }

    @Override
    Pizza3 creatPizza(String type) {
        Pizza3 pizza = new NyPizza(factory);
        return pizza;
    }
}

/**
 * AbstractFactory: 抽象工厂, 定义接口,所有具体工厂都必须实现此接口，包含一组方法用来生产产品。
 */
interface PizzaIngredientFactory {
    Dough createDough();

    Sauce createSauce();
}

/**
 * ConcreteFactory：具体工厂，实现不同的产品家族。要创建产品，只要使用一个工厂而完全不需实例化任何产品对象。
 */
class NyPizzaIngredientFactory implements PizzaIngredientFactory {
    public NyPizzaIngredientFactory() {
    }

    public Dough createDough() {
        return new MyDough();
    }

    public Sauce createSauce() {
        return new MySauce();
    }
}

/**
 * AbstractFamilyProduct：抽象产品族产品，不实现
 */
interface Dough {
    String getName();
}

/**
 * AbstractFamilyProduct：抽象产品族产品，不实现
 */
interface Sauce {
    String getName();
}

/**
 * AbstractFamilyConcreteProduct：抽象产品族具体产品
 */
class MyDough implements Dough {
    String name = "面团MyDough";

    public MyDough() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {

        this.name = name;
    }
}

/**
 * AbstractFamilyConcreteProduct：抽象产品族具体产品
 */
class MySauce implements Sauce {
    String name = "作料MySauce";

    public MySauce() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}