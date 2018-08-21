package com.zheng.pattern;

import java.io.*;

import static com.zheng.pattern.DecoratorModel.*;

/**
 * Created by zhenghui on 2017/9/30.
 * 装饰者模式
 * 动态的将责任附加到对象上。想要扩展功能，装饰者提供有别于继承的另一种选择。
 * java.io 用了装饰者模式
 */
public class DecoratorModel {

    // 大小写转化 ctr+shift+U
    protected static final double BEVERAGE_ESPRESSO_PRICE = 1.99;
    protected static final double BEVERAGE_HOUSEBLEND_PRICE = 0.99;
    protected static final double BEVERAGE_DARKROAST_PRICE = 3.00;
    protected static final double CONDIMENT_MOCHA_PRICE = 0.2;
    protected static final double CONDIMENT_SOY_PRICE = 1.2;
    protected static final double CONDIMENT_WHIP_PRICE = 2.2;

    public static void main(String[] args) {
        //饮料 浓缩咖啡
        Beverage beverage = new Espresso();
        System.out.println(beverage.getDescription()
                + " $" + beverage.cost());

        //饮料 黑暗烘焙
        Beverage beverage2 = new DarkRoast();
        //装饰 添加调料
        beverage2 = new Mocha(beverage2);
        beverage2 = new Mocha(beverage2);
        System.out.println(beverage2.getDescription()
                + " $" + beverage2.cost());

        Beverage beverage3 = new HouseBlend();
        beverage3 = new Soy(beverage3);
        beverage3 = new Mocha(beverage3);
        beverage3 = new Whip(beverage3);
        System.out.println(beverage3.getDescription()
                + " $" + beverage3.cost());

        //java.io 添加装饰
        try {
            String fullPath = Thread.currentThread().getContextClassLoader().getResource("design/DecoratorModel.txt").getPath();
            int c;
            InputStream in =
                    new LowerCaseInputStream(
                            new BufferedInputStream(
                                    new FileInputStream(fullPath)));
            while ((c = in.read()) >= 0) {
                System.out.println((char) c);
            }
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}

/**
 * 抽象类 饮料
 */
abstract class Beverage {

    String description = "Unknown Beverage";
    int size = 0;

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public String getDescription() {
        return description;
    }

    abstract double cost();
}

/**
 * 调料装饰类
 */
abstract class CondimentDecorator extends Beverage {
    @Override
    public abstract String getDescription();
}

/**
 * 饮料 浓缩咖啡
 */
class Espresso extends Beverage {

    //构造器 设置饮料描述
    public Espresso() {
        description = "Espresso";
    }

    @Override
    double cost() {
        return BEVERAGE_ESPRESSO_PRICE;
    }
}

/**
 * 饮料 HouseBlend
 */
class HouseBlend extends Beverage {

    //构造器 设置饮料描述
    public HouseBlend() {
        description = "House Blend Coffee";
    }

    @Override
    double cost() {
        double price = BEVERAGE_HOUSEBLEND_PRICE;
        if (getSize() == 1) {//小杯
            price = 0.99;
        } else if (getSize() == 2) {//中杯
            price = 1.00;
        } else if (getSize() == 3) {//大杯
            price = 1.01;
        }
        return price;
    }
}

/**
 * 饮料 黑暗烘焙
 */
class DarkRoast extends Beverage {

    //构造器 设置饮料描述
    public DarkRoast() {
        description = "Dark Roast Coffee";
    }

    @Override
    double cost() {
        return BEVERAGE_DARKROAST_PRICE;
    }
}

/**
 * 调料 Mocha
 */
class Mocha extends CondimentDecorator {
    Beverage beverage;

    public Mocha(Beverage beverage) {
        this.beverage = beverage;
    }

    // 饮料 追加 调料
    @Override
    public String getDescription() {
        return beverage.getDescription() + ",Mocha";
    }

    // 饮料价格 + 调料价格
    @Override
    double cost() {
        return CONDIMENT_MOCHA_PRICE + beverage.cost();
    }
}


/**
 * 调料 Soy
 */
class Soy extends CondimentDecorator {
    Beverage beverage;

    public Soy(Beverage beverage) {
        this.beverage = beverage;
    }

    // 饮料 追加 调料
    @Override
    public String getDescription() {
        return beverage.getDescription() + ",Soy";
    }

    // 饮料价格 + 调料价格
    @Override
    double cost() {
        return CONDIMENT_SOY_PRICE + beverage.cost();
    }
}

/**
 * 调料 Whip
 */
class Whip extends CondimentDecorator {
    Beverage beverage;

    public Whip(Beverage beverage) {
        this.beverage = beverage;
    }

    // 饮料 追加 调料
    @Override
    public String getDescription() {
        return beverage.getDescription() + ",Whip";
    }

    // 饮料价格 + 调料价格
    @Override
    double cost() {
        return CONDIMENT_WHIP_PRICE + beverage.cost();
    }
}

/**
 * java.io 添加装饰 转小写
 */
class LowerCaseInputStream extends FilterInputStream {

    public LowerCaseInputStream(InputStream in) {
        super(in);
    }

    @Override
    public int read() throws IOException {
        int c = super.read();
        return c == -1 ? c : Character.toLowerCase((char) c);
    }

    @Override
    public int read(byte[] b) throws IOException {
        return super.read(b);
    }

}
